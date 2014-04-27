package com.phicomm.application.subscriber.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.OutletSoapAccept;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.service.IOutletService;
import com.phicomm.application.subscriber.util.AnayseSoapUtil;
import com.phicomm.application.subscriber.util.CheckUtil;
import com.phicomm.application.subscriber.util.OutletUtil;

@Controller
@RequestMapping("/outlet")
public class OutletController {
	private static final Logger logger = Logger.getLogger(OutletController.class);
	private static final String SDF = "yyyy-MM-dd HH:mm:ss";
	private StringBuilder msg;
	private IOutletService outletService;
	
	public IOutletService getOutletService() {
		return outletService;
	}
	@Inject
	public void setOutletService(IOutletService outletService) {
		this.outletService = outletService;
	}
	
	/**
	 * 插座主动上报都在此方法处理
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping(value="/report",method=RequestMethod.POST)
	public void report(HttpServletRequest req,HttpServletResponse res)  {
		//1、发送空包无需理会
		if (req.getContentLength() == 0) { 
			res.setStatus(204);
			res.setContentLength(0);
			return;
		} 
		String answer;
		PrintWriter out = null;
		msg = new StringBuilder();
		res.setHeader("Content-Type", "text/xml; charset=UTF-8");
		res.setHeader("SOAPAction", "");
		res.setCharacterEncoding("utf-8");
		res.setHeader("Connection", "Keep-Alive");
		try {
			out = res.getWriter();
			//2、解析soap包，将有效数据存入OutletSoapAccept
			OutletSoapAccept osa = AnayseSoapUtil.run(req);
			answer = checkOsa(osa);
			if(answer != null && !answer.equals(""))
				returnError(res,answer,out);
			
			for(String evenCode:osa.getEventCode()){
				logger.info("EvenCode:"+evenCode);
				//上报初始化
				if(evenCode.equals("0 BOOTSTRAP")){
					answer = bootStrap(osa);
					if(answer != null && !answer.equals(""))
						returnError(res,answer,out);
				}
				//上报开关状态
				else if(evenCode.equals("X PHICOMM STATE")){
					answer = phicommState(osa);
					if(answer != null && !answer.equals(""))
						returnError(res,answer,out);
				}
				//上报电流电压功率
				else if(evenCode.equals("X PHICOMM POWER")){
					answer = phicommPower(osa);
					if(answer != null && !answer.equals(""))
						returnError(res,answer,out);
				}
				//上报定时规则
				else if(evenCode.equals("X PHICOMM TIMER")){
					answer = phicommTimer(osa);
					if(answer != null && !answer.equals(""))
						returnError(res,answer,out);
				}
				//上报更改设置
				else if(evenCode.equals("4 VALUE CHANGE")){
					answer = valueChange(osa);
					if(answer != null && !answer.equals(""))
						returnError(res,answer,out);
				}
			}
			answer = "resStatus=1&ErrorCode=";
			res.setStatus(200);
			res.setContentLength(answer.length());
			out.write(answer);
		}catch(CloudException e){
			answer = "resStatus=0&ErrorCode=8803";
			logger.info(answer, e);
			returnError(res,answer,out);
		}catch (Exception e){
			answer = "resStatus=0&ErrorCode=8802";
			logger.info(answer, e);
			returnError(res,answer,out);
		}finally{
			if(out!=null)
				out.close();
		}
	}
	
	/**
	 * 检查soap包必须存在的属性
	 * @param osa
	 * @return
	 */
	public String checkOsa(OutletSoapAccept osa){
		if(osa.getOtlMac() == null || "".equals(osa.getOtlMac()))
			msg.append("soap包必须包含插座MAC");
		if(osa.getEventCode().size() == 0)
			msg.append("soap包的evenCode必须存在");
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		return null;
	}
	/**
	 * 专门返回400
	 * @param res
	 * @param answer
	 * @param out
	 */
	private void returnError(HttpServletResponse res,String answer,PrintWriter out){
		res.setStatus(400);
		res.setContentLength(answer.length());
		out.write(answer);
	}
	/**
	 * 处理4 VALUE CHANGE接口
	 * @param osa
	 */
	private String valueChange(OutletSoapAccept osa) throws Exception{
		msg = CheckUtil.check4ValueChange(osa);
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		outletService.reportOtlChange(osa);
		return null;
	}

	/**
	 * 处理X PHICOMM TIMER接口
	 * @param osa
	 * @throws SQLException 
	 */
	private String phicommTimer(OutletSoapAccept osa) throws SQLException {
		//1、防呆
		if(osa.getRules() == null || osa.getRules().size() <= 0)
			msg.append("定时规则内容不能为空");
		if(osa.getRuleNo() != osa.getRules().size())
			msg.append("定时规则数目与实际不符");
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		//2、处理
		outletService.reportRule(osa);
		return null;
	}

	/**
	 * 处理X PHICOMM POWER接口
	 * @param osa
	 * @throws ParseException 
	 * @throws NoSuchAlgorithmException 
	 */
	private String phicommPower(OutletSoapAccept osa) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(SDF);
		msg = CheckUtil.check4ReportPower(osa);
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		OutletReport outletReport = new OutletReport();
		outletReport.setAmps(osa.getOtlAmps());
		outletReport.setVolt(osa.getOtlVolt());
		outletReport.setKwh(osa.getOtlKwh());
		outletReport.setTotalKwh(osa.getOtlTotalKwh());
		outletReport.setCalTime(format.parse(osa.getReportDate()));
		if(osa.getClearTime() != null || !"".equals(osa.getClearTime()))
			outletReport.setClearTime(format.parse(osa.getClearTime()));
		outletReport.setReportTime(new Date());
		
		outletService.reportPower(outletReport,osa);
		return null;
	}

	/**
	 * 处理X PHICOMM STATE接口
	 * @param osa
	 * @throws NoSuchAlgorithmException 
	 */
	private String phicommState(OutletSoapAccept osa) throws Exception {
		if(osa.getOtlStatus() == null || "".equals(osa.getOtlStatus().toString()))
			msg.append("上报的插座状态必须有状态值");
		if(osa.getOtlStatus() > 2 || osa.getOtlStatus() < 0)
			msg.append("上报的插座状态值只能0,1,2");
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		outletService.reportStatus(osa.getOtlStatus(),osa.getOtlMac(),osa);
		return null;
	}

	/**
	 * 处理0 BOOTSTRAP接口
	 * @param osa
	 * @throws NoSuchAlgorithmException 
	 */
	private String bootStrap(OutletSoapAccept osa) throws Exception {
		msg = CheckUtil.check4AddOutlet(osa);
		if(msg.length() != 0){
			logger.info(msg);
			return "resStatus=0&ErrorCode=8803";
		}
		Outlet outlet = OutletUtil.osa2Otl(osa);
		
		outletService.initOutlet(outlet, osa.getPhoImei(),osa);
		return null;
	}
}
