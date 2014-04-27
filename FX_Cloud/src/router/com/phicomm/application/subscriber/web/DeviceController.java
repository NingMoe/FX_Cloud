package com.phicomm.application.subscriber.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.DeviceaddReturn;
import com.phicomm.application.subscriber.model.DevicelistReturn;
import com.phicomm.application.subscriber.model.Ping;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.service.IDeviceService;
import com.phicomm.application.subscriber.service.IUserService;



//手机_设备_控制器
@Controller
@RequestMapping("/device")
public class DeviceController {
	public static final Logger logger = Logger.getLogger(DeviceController.class);
	
	private IUserService userService;
	
	
	public IUserService getUserService() {
		return userService;
	}


	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private IDeviceService deviceService;
	
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}


	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	//获取用户的路由设备
	@RequestMapping(value="/devcList")
	public void devcList(String ownMac,HttpServletResponse res) throws IOException{
		/* 1、是否来自合法的mac
		 * 2、获取对应mac所有的设备
		 * 3、返回
		 */
	//	long time1 = System.currentTimeMillis();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		try{
			User u = userService.loadbymac(ownMac);
			DevicelistReturn dr = new DevicelistReturn();

			dr.setRetState(1);
			if(u == null){
				//如果此mac没有对应的用户
				//logger.warn("设备列表获取失败，MAC不存在");
				dr.setIfSuport(0);
				out.write(dr.Return(""));
				out.flush();
				out.close();
				return;
			}else{
				//logger.warn("正在获取设备列表");
				dr.setIfSuport(1);
				java.util.List<Device> l = deviceService.loadbyuid(u.getId());
				//将 l传入线程池去ping
				try{
					
					l = Ping.pingRouter(l);
					for(Device d:l){
						/*
						 * 如果Socket连上了，说明在线。再查看有没有deviceTime比其大的
						 */
						if("1".equals(d.getActiveStatus())){
							long num = deviceService.loadByBiggerThanWanIp(d.getDeviceWanIp(),d.getDeviceTime());
							if(num>0) d.setActiveStatus("0");
							System.out.println("num="+num);
						}
					}
				}
				catch(Exception ex){
					logger.warn(ex,ex);
					}
				//System.out.println("消耗时间:"+(System.currentTimeMillis()-time1)/1000+"秒");
				//System.out.println("消耗时间:"+(System.currentTimeMillis()-time1)+"毫秒");
				String temp = dr.Setit(l);
				System.out.println("ke:"+dr.Return(temp));
				out.write(dr.Return(temp));
			}
			
			
		}catch(Exception ex)
		{
			logger.warn("设备列表异常"+ex.toString(),ex);
			out.write(ex.toString());
			out.flush();
			out.close();
		}
		
	}
	
	
	//添加用户的路由设备
	@RequestMapping(value="/add")
	public void add(Device device,String ownMac,String devcTyp,String devcMac,String devcName,HttpServletRequest req,HttpServletResponse res) throws IOException{
		/* 1、是否登录
		 * 2、添加
		 * 3、返回
		 */
		
		req.setCharacterEncoding("utf-8");
		////logger.warn("设备别名："+devcName);
		System.out.println("设备别名："+devcName);
		
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		DeviceaddReturn dr = new DeviceaddReturn();
		
			User u = userService.loadbymac(ownMac);
			
			if(u == null){
				dr.setRetState(1);
				dr.setIfSuport(0);
				dr.setRetAdd(0);
				dr.setRetReason(1);
				out.write(dr.Return());
				out.flush();
				out.close();
				return;
			}else{
				try{
					Device de = deviceService.loadbymac(devcMac);
					if(de == null)
					{
						res.setStatus(400);
						out.write("Device is not exist");
						out.flush();
						out.close();
						return;
					}
					de.setUid(u.getId());
					de.setUserEmail(u.getMailAddress());
					de.setNickName(devcName);	//添加路由器别名
					
					deviceService.update(de);
					dr.setRetState(1);
					dr.setIfSuport(1);
					dr.setRetAdd(1);
					dr.setRetReason(0);
				}catch(Exception ex){
					dr.setRetState(1);
					dr.setIfSuport(1);
					dr.setRetAdd(0);
					dr.setRetReason(1);
					logger.warn("异常"+ex.toString(),ex);
				}
				finally{
					out.write(dr.Return());
					out.flush();
					out.close();
				}
				
			}
			
			
		
		
	}
	
	
	
	
}
