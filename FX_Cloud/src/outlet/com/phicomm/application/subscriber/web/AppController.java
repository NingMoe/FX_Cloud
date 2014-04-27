package com.phicomm.application.subscriber.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phicomm.application.subscriber.model.AppOutletReturn;
import com.phicomm.application.subscriber.model.AppReturn;
import com.phicomm.application.subscriber.model.PowerReturn;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.service.IAppService;
import com.phicomm.application.subscriber.util.AppPowerHDM;

@Controller
@RequestMapping("/app")
public class AppController {
	private IAppService appService;

	public IAppService getAppService() {
		return appService;
	}
	@Resource
	public void setAppService(IAppService appService) {
		this.appService = appService;
	}
	/**
	 * 手机登录
	 * @param phoneMac
	 * @param userMail
	 * @param password
	 * @param phoneTyp
	 * @param phoneVer
	 * @param phoneIMEI
	 * @param appVer
	 * @param res
	 * 返回json：如{"retState ":"0","errorCode":"8001"}
	 * retState：  0:登录失败 ;1:登录成功
	 * errorCode：0:登录成功;8001:用户不存在;8002:密码错误;8006:未激活;8009:服务器异常;8010:请求超时
	 * @throws IOException 
	 */
	@RequestMapping(value="/login")
	public void login(String phoneMac, String userMail, String password, String phoneTyp
				,String phoneVer, String phoneIMEI,String appVer,HttpServletResponse res) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppReturn ar = null;
		try {
			ar = appService.appLogin(userMail, password,phoneMac,phoneTyp,phoneVer,phoneIMEI,appVer);
			if(out!=null){
				out.write(ar.loginRegReturn());
			}
		} catch (Exception e) {
			ar = new AppReturn();
			ar.setRsEc("0", "8009");
			if(out!=null){
				out.write(ar.loginRegReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 注册帐号
	 * @param phoneMac
	 * @param userMail
	 * @param password
	 * @param username
	 * @param province
	 * @param phoneNo
	 * @param zipcode
	 * @param phoneTyp
	 * @param phoneVer
	 * @param phoneIMEI
	 * @param appVer
	 * @param res
	 * 返回json：如{"retState ":"0","errorCode":"8001"}
	 * retState：  0:注册失败 ;1:注册成功
	 * errorCode：0:注册成功;8007:用户已注册;8009:服务器异常;8010:请求超时
	 * @throws IOException 
	 */
	@RequestMapping(value = "/register")
	public void register(String phoneMac, String userMail, String password,String username, 
			String province, String phoneNo, String zipcode, String phoneTyp,
			String phoneVer, String phoneIMEI, String appVer, HttpServletResponse res)
			throws IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppReturn ar = null;
		try {
			ar = appService.appRegister(phoneMac, userMail, password, username, province, phoneNo, zipcode, phoneTyp, phoneVer, phoneIMEI, appVer);
			if(out!=null){
				out.write(ar.loginRegReturn());
			}
		} catch (Exception e) {
			ar = new AppReturn();
			ar.setRsEc("0", "8009");
			if(out!=null){
				out.write(ar.loginRegReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 获取用户的绑定设备
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param userMail
	 * @param res
	 * @throws IOException
	 * 返回json：如
	 * {”retState”:”1”,”errorCode”:”0”,”devices”:[{“devMac”:”FF:FF:FF:FF:FF:DD”,"devName": "餐厅吊灯", "online ":"1","devStat ": "1"},{“devMac”:”FF:FF:FF:FF:FF:DD”,"devName ": "智能插座02","online ":"0","devStat": "0"}]}
	 * retState: 0 表示获取设备失败 1 表示获取设备成功
	 * errorCode: 0：没有错误；8003：已掉线；8009：服务器异常
	 */
	@RequestMapping(value="/getAllDevice")
	public void getAllDevice(String phoneMac,String phoneIMEI,String userMail,HttpServletResponse res) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		try {
			String sr = appService.appGetAllDevice(phoneMac, phoneIMEI, userMail);
			if(out!=null){
				out.write(sr);
			}
		} catch (Exception e) {
			AppReturn ar = new AppReturn();
			ar.setRsEc("0", "8009");
			if(out!=null){
				out.write(ar.getAllDevReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 帐号绑定新设备
	 * @param userMail
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param bindNum
	 * @param devPID1
	 * @param devMac1
	 * @param res
	 * @throws IOException
	 * 返回json：如{”retState”:”1”,”errorCode”:”0”}
	 * retState：  0:失败 ;1:成功
	 * errorCode：0：没有错误；8003：已掉线；8004：设备Mac不存在；8009：服务器异常
	 */
	@RequestMapping(value="bindDev")
	public void bindDev(String userMail,String phoneMac,String phoneIMEI,String bindNum,String[] devPID,String[] devMac,HttpServletResponse res)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppReturn ar = new AppReturn();
		try {
			ar = appService.appBindDev(userMail,phoneMac,phoneIMEI,bindNum,devPID,devMac);
		} catch (CloudException ue) {
			if(ue.getMessage().equals("设备不存在")){
				ar.setRsEc("0", "8004");
			}else{
				ar.setRsEc("0", "8009");
			}
		} catch (Exception e) {
			ar.setRsEc("0", "8009");
		} finally {
			if(out!=null){
				out.write(ar.loginRegReturn());
				out.close();
				}
		}
	}
	/**
	 * 获取插座设备绑定的手机
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devMac
	 * @param res
	 * @throws IOException
	 * 返回json：如{”retStat”:”1”,”errorCode”:”0”,”phones”:[{“phoneMac”:”FF:FF:FF:FF:FF:DD”,"phoneTyp": "i813w"}]}
	 * retState：  0:失败 ;1:成功
	 * errorCode：0：没有错误；8003：已掉线；8004：设备Mac不存在；8009：服务器异常
	 */
	@RequestMapping(value="bindPhone")
	public void bindPhone(String phoneMac,String phoneIMEI,String devMac,HttpServletResponse res)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppReturn ar = new AppReturn();
		try {
			String sr = appService.appBindPhone(phoneMac,phoneIMEI,devMac);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			if(ue.getMessage().equals("设备不存在")){
				ar.setRsEc("0", "8004");
			}else{
				ar.setRsEc("0", "8009");
			}
			if(out!=null){
				out.write(ar.getBindPhoneReturn());
			}
		} catch (Exception e) {
			ar.setRsEc("0", "8009");
			if(out!=null){
				out.write(ar.getBindPhoneReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 设备功率信息的获取
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devMac
	 * @param res
	 * @throws IOException
	 * 返回json：如　　
	 * {"retState ": "1",  "errorCode ": "0", “device”:{
	 * "devMac ":"FF:FF:FF:FF:FF:FF", "devName": "客厅的", "devStat": "1",
	 * "todayPow":”10",”monthPow”:”300”,”lastMonPow”:”300”,
	 * ”day1Pow”:”10”,”day2Pow”:”20”, ”day3Pow”:”30”, ”day4Pow”:”40”, ”day5Pow”:”50”, ”day6Pow”:”60”}}
	 * retState：  0:失败 ;1:成功
	 * errorCode：0：没有错误；8003：已掉线；8004：设备不存在；8005：设备未与账号绑定；8009：服务器异常
	 */
	@RequestMapping(value="power")
	public void power(String phoneMac,String phoneIMEI,String devMac,HttpServletResponse res)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		PowerReturn pr = new PowerReturn();
		try {
			String sr = appService.appPower(phoneMac,phoneIMEI,devMac,AppPowerHDM.Default);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			if(ue.getMessage().equals("设备不存在")){
				pr.setRsEc("0", "8004");
			}else if(ue.getMessage().equals("设备未与帐号绑定")){
				pr.setRsEc("0", "8005");
			}
			if(out!=null){
				out.write(pr.getPowerReturn());
			}
		} catch (Exception e) {
			pr.setRsEc("0", "8009");
			if(out!=null){
				out.write(pr.getPowerReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	 /**
	  * 设备功率信息的获取(按小时)
	  * @param phoneMac
	  * @param phoneIMEI
	  * @param devMac
	  * @param res
	  * @throws IOException
	  * 返回json：如
	  * {"retState ": "1",  "errorCode ": "0", “device”:{
	  * "devMac ":"FF:FF:FF:FF:FF:FF","devName":"客厅的","devStat":"1",”hourPow”:”10_20_30_0_40”}}
	  * retState：  0:失败 ;1:成功
	  * errorCode：0：没有错误；8003：已掉线；8004：设备不存在；8005：设备未与账号绑定；8009：服务器异常
	  * hourPow：    0点到1点_1点到2点，向后类推至当前时间
	  */
	 @RequestMapping(value="powerHours")
		public void powerHours(String phoneMac,String phoneIMEI,String devMac,HttpServletResponse res)throws IOException{
			res.setContentType("application/json");
			res.setCharacterEncoding("utf-8");
			PrintWriter out = null;
			out = res.getWriter();
			PowerReturn pr = new PowerReturn();
			try {
				String sr = appService.appPower(phoneMac,phoneIMEI,devMac,AppPowerHDM.Hours);
				if(out!=null){
					out.write(sr);
				}
			} catch (CloudException ue) {
				if(ue.getMessage().equals("设备不存在")){
					pr.setRsEc("0", "8004");
				}else if(ue.getMessage().equals("设备未与帐号绑定")){
					pr.setRsEc("0", "8005");
				}
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} catch (Exception e) {
				pr.setRsEc("0", "8009");
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} finally {
				if(out!=null){
					out.close();
				}
			}
		}
	 /**
	  * 设备功率信息的获取(按天)
	  * @param phoneMac
	  * @param phoneIMEI
	  * @param devMac
	  * @param res
	  * @throws IOException
	  * 返回json：如
	  * {"retState ": "1",  "errorCode ": "0", “device”:{
	  * "devMac ":"FF:FF:FF:FF:FF:FF","devName":"客厅的","devStat":"1",”dayPow”:”10_20_30_0_40”}}
	  * retState：  0:失败 ;1:成功
	  * errorCode：0：没有错误；8003：已掉线；8004：设备不存在；8005：设备未与账号绑定；8009：服务器异常
	  * dayPow：    前一天的功耗_前两天的功耗，一直到前6天的功耗
	  */
	 @RequestMapping(value="powerDays")
		public void powerDays(String phoneMac,String phoneIMEI,String devMac,HttpServletResponse res)throws IOException{
			res.setContentType("application/json");
			res.setCharacterEncoding("utf-8");
			PrintWriter out = null;
			out = res.getWriter();
			PowerReturn pr = new PowerReturn();
			try {
				String sr = appService.appPower(phoneMac,phoneIMEI,devMac,AppPowerHDM.Days);
				if(out!=null){
					out.write(sr);
				}
			} catch (CloudException ue) {
				if(ue.getMessage().equals("设备不存在")){
					pr.setRsEc("0", "8004");
				}else if(ue.getMessage().equals("设备未与帐号绑定")){
					pr.setRsEc("0", "8005");
				}
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} catch (Exception e) {
				pr.setRsEc("0", "8009");
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} finally {
				if(out!=null){
					out.close();
				}
			}
		}
	 /**
	  * 设备功率信息的获取(按月)
	  * @param phoneMac
	  * @param phoneIMEI
	  * @param devMac
	  * @param res
	  * @throws IOException
	  * 返回json：如
	  * {"retState ": "1",  "errorCode ": "0", “device”:{
	  * "devMac ":"FF:FF:FF:FF:FF:FF","devName":"客厅的","devStat":"1",”monPow”:”10_20_30_0_40”}}
	  * retState：  0:失败 ;1:成功
	  * errorCode：0：没有错误；8003：已掉线；8004：设备不存在；8005：设备未与账号绑定；8009：服务器异常
	  * monPow：    今年一月的功耗_二月的功耗，一直到当前月
	  */
	 @RequestMapping(value="powerMonth")
		public void powerMonth(String phoneMac,String phoneIMEI,String devMac,HttpServletResponse res)throws IOException{
			res.setContentType("application/json");
			res.setCharacterEncoding("utf-8");
			PrintWriter out = null;
			out = res.getWriter();
			PowerReturn pr = new PowerReturn();
			try {
				String sr = appService.appPower(phoneMac,phoneIMEI,devMac,AppPowerHDM.Month);
				if(out!=null){
					out.write(sr);
				}
			} catch (CloudException ue) {
				if(ue.getMessage().equals("设备不存在")){
					pr.setRsEc("0", "8004");
				}else if(ue.getMessage().equals("设备未与帐号绑定")){
					pr.setRsEc("0", "8005");
				}
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} catch (Exception e) {
				pr.setRsEc("0", "8009");
				if(out!=null){
					out.write(pr.getPowerReturn());
				}
			} finally {
				if(out!=null){
					out.close();
				}
			}
		}
	/**
	 * 设备开关状态信息的获取/配置
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devType
	 * @param devMac
	 * @param devAction 0：获取 1：配置
	 * @param devVal 0：位置状态  1：断开状态  2：闭合状态
	 * @param res
	 * @throws IOException
	 * 返回json：如
	 * {“retSwitch”:{"retState":"1","retValid":"1","retSup":"1","devType":"Switch",
	 * "devMac":"00:00:00:00:00:01","devState":"1"}}
	 * retState：  0:通信失败；1:通信正常
	 * retValid：  0:数据无效；1:数据有效
	 * retSup：      0:配置失败；1:配置成功
	 * devState：  0:表示断开；1:表示闭合
	 */
	@RequestMapping(value="state")
	public void state(String phoneMac,String phoneIMEI,String devType,String devMac,
			String devAction,String devVal,HttpServletResponse res,HttpServletRequest req)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppOutletReturn aor = new AppOutletReturn();
		
		String params = getParams(req);
		
		try {
			String sr = appService.appOutletState(phoneIMEI,devMac,devAction,params);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} catch (Exception e) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 设备定时信息的获取/配置
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devType
	 * @param devMac
	 * @param devAction
	 * @param Tsum
	 * @param Tnum
	 * @param devTimer
	 * @param res
	 * @param req
	 * @throws IOException
	 /* 返回json：如
	 * {“retSwitch”:{"retState":"1","retValid":"1","retSup":"0","devType":" Switch","devName":"PGE140A1",
	 * "Tsum":"2","Tnum":"1","Tnum":"3","devTimer1":"201311260830","devTimer3":"201311260930"}}
	 * retState：  0:通信失败；1:通信正常
	 * retValid：  0:数据无效；1:数据有效
	 * retSup：      0:配置失败；1:配置成功
	 * TSum:定时的数目  Tnum:定时的序数   devTimer:设备的定时值
	 */
	@RequestMapping(value="timer")
	public void timer(String phoneMac,String phoneIMEI,String devType,String devMac,
			String devAction,String Tsum,String Tnum,String devTimer,
			HttpServletResponse res,HttpServletRequest req)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppOutletReturn aor = new AppOutletReturn();
		
		String params = getParams(req);
		
		try {
			String sr = appService.appOutletState(phoneIMEI,devMac,devAction,params);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} catch (Exception e) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 设备加密的获取/配置
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devMac
	 * @param devType
	 * @param devAction
	 * @param devPassword
	 * @param devFlag
	 * @param res
	 * @param req
	 * @throws IOException
	 */
	@RequestMapping(value="encryp")
	public void encryp(String phoneMac,String phoneIMEI,String devMac,String devType,
			String devAction,String devPassword,String devFlag,
			HttpServletResponse res,HttpServletRequest req)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppOutletReturn aor = new AppOutletReturn();
		
		String params = getParams(req);
		
		try {
			String sr = appService.appOutletState(phoneIMEI,devMac,devAction,params);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} catch (Exception e) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 设备信息的获取/配置
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devType
	 * @param devMac
	 * @param devAction
	 * @param devName
	 * @param devUser
	 * @param res
	 * @param req
	 * @throws IOException
	 */
	@RequestMapping(value="info")
	public void info(String phoneMac,String phoneIMEI,String devType,String devMac,
			String devAction,String devName,String devUser,
			HttpServletResponse res,HttpServletRequest req)throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppOutletReturn aor = new AppOutletReturn();
		
		String params = getParams(req);
		try {
			String sr = appService.appOutletState(phoneIMEI,devMac,devAction,params);
			if(out!=null){
				out.write(sr);
			}
		} catch (CloudException ue) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} catch (Exception e) {
			aor.setRsEc("1", "0", "0");
			if(out!=null){
				out.write(aor.appOutletReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 手机退出
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value="/logout")
	public void logout(String phoneMac,String phoneIMEI,HttpServletResponse res) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		AppReturn ar = null;
		try {
			ar = appService.appLogout(phoneMac);
			if(out!=null){
				out.write(ar.logoutRegReturn());
			}
		} catch (Exception e) {
			ar = new AppReturn();
			ar.setRsEc("0", "8009");
			if(out!=null){
				out.write(ar.logoutRegReturn());
			}
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	/**
	 * 处理app请求的参数，作为消息转发的参数
	 * @param req
	 * @return
	 */
	private String getParams(HttpServletRequest req) {
		Map<String, String[]> params = req.getParameterMap();
		Set<String> names = params.keySet();
		StringBuilder sbBuilder = new StringBuilder();
		int i=0;
		for(String name:names){
			if(names.size()-1 != i++){
				sbBuilder.append(name+"="+params.get(name)[0]+"&");
			}else{
				sbBuilder.append(name+"="+params.get(name)[0]);
			}
		}
		return sbBuilder.toString();
	}
}
