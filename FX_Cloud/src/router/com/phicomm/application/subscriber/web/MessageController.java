package com.phicomm.application.subscriber.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.Mac;
import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.NewsReturn;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.service.IDeviceService;
import com.phicomm.application.subscriber.service.IMacService;
import com.phicomm.application.subscriber.service.INewsService;
import com.phicomm.application.subscriber.service.IUserService;
import com.phicomm.application.subscriber.util.PropertiesUtil;

//手机_信息_控制器
@Controller
@RequestMapping("/message")
public class MessageController {

	public static final Logger logger = Logger.getLogger(MessageController.class);

	private IMacService macService;

	public IMacService getMacService() {
		return macService;
	}

	@Resource
	public void setMacService(IMacService macService) {
		this.macService = macService;
	}

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

	private INewsService newsService;

	public INewsService getnewsService() {
		return newsService;
	}

	@Resource
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	// 获取公司的新闻
	@RequestMapping(value = "/news")
	public void news(String ownMac, String phoneTyp, String userLong,
			String userLat, String appVer, String routerTyp, String routerVer,
			String loginSta, HttpServletResponse res) throws IOException {

		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		NewsReturn dr = new NewsReturn();
		dr.setRetState(1);

		Mac m = macService.loadByMac(ownMac);
		try {
			if (loginSta.equals("0")) {
				// 未登入->请求消息
				logger.info("未登入->请求消息");
				dr.setIfSuport(1);
				List<News> l0 = null;
				if (m != null && m.getTime() != null) // 之前有请求过消息
					l0 = newsService.listSalesByTime(m.getTime()); // 消息发布时间>上次请求时间
				else
					l0 = newsService.listSales(); // 之前没请求过消息
				String temp = dr.Setit(l0);
				out.write(dr.Return(temp));
			} else if (loginSta.equals("1")) {
				// 已登入->请求消息
				logger.info("已登入->请求消息");
				User u = userService.loadbymac(ownMac);
				if (u == null) {
					logger.info("手机-升级信息-MAC不存在");
					dr.setIfSuport(0);
					if (out != null){
						out.write(dr.Return(""));
						out.close();
					}
					return;
				} else {
					dr.setIfSuport(1);
					List<News> l1 = null;
					if (m != null && m.getTime() != null) // 之前有请求过消息
						l1 = newsService.listSalesByTime(m.getTime()); // 消息发布时间>上次请求时间
					else
						l1 = newsService.listSales(); // 之前没请求过消息

					java.util.List<Device> d = deviceService.loadbyuid(u
							.getId()); // 用户的所有设备
				//	News n = new News();
					//News nn = new News();
					News news = null;
					boolean checkNew = false;
					boolean checkFile = false;
					for (Device dd : d) {
						/**
						 * m.getTime==null-->apk第一次访问，只根据设备型号和硬件版本查询对应的最后一个版本升级信息
						 * m.getTime!=null-->apk不是第一次访问，那么还需要加条件：上次访问之后发布的
						 */
						if(m != null && m.getTime() != null)
							news = newsService.loadbytime(dd.getDeviceTyp(),dd.getHardVer(),m.getTime());
						else
							news = newsService.loadbytime(dd.getDeviceTyp(),dd.getHardVer());
						if(news == null) {
							logger.info("未找到版本信息，路由器软件版本是最新版本");
							continue;
						}
						checkNew = false;
						checkFile = false;
						String ver_rt = dd.getDeviceVer().substring(dd.getDeviceVer().indexOf("V")+1);
						String ver_ma = news.getRouterVer().substring(news.getRouterVer().indexOf("V")+1);
						String[] coms_rt = ver_rt.split("\\.");
						String[] coms_ma = ver_ma.split("\\.");
						for(int i=0;i<coms_rt.length;i++) {
							if(Integer.parseInt(coms_ma[i]) > Integer.parseInt(coms_rt[i])) {
								checkNew = true;
								break;
							}
							if(Integer.parseInt(coms_ma[i]) < Integer.parseInt(coms_rt[i])) {
								checkNew = false;
								break;
							}
						}
						
						if(!checkNew) {
							//路由器软件版本是最新版本
							logger.info("路由器软件版本是最新版本");
							continue;
						} else {
							//有最新的软件版本
							//获取软件升级文件大小
							Properties prop = PropertiesUtil.getIpProp();
							String uploadAddr = prop.getProperty("upload");
							String fileName = news.getUrl().substring(news.getUrl().lastIndexOf("upload/")+7);
							
							File file = new File(uploadAddr);
							File[] files = file.listFiles();
							if(files == null || file == null){
								//文件或文件夹未找到
								logger.warn("文件或文件夹未找到");
								continue;
							}
							for(File f:files) {
								if(fileName.trim().equals(f.getName())) {
									//找到对应的文件，获取文件大小
									checkFile = true;
									l1.add(news);
									break;
								} 
							}
							if(!checkFile) {
								//未找到对应的文件，服务器报异常
								logger.warn("未找到对应的文件");
								continue;
							}
						}
					}
							
						
						/*n = null;
						nn = null;
						n = newsService.loadbyDevcVer(dd.getDeviceVer(),
								dd.getDeviceTyp());// 根据软件版本找到对应的news
						System.out.println(dd.getDeviceVer()+","+dd.getDeviceTyp());
						System.out.println(n);
						if (n == null)
							continue;
						else {
							nn = newsService.loadbytime(n.getTime(),
									n.getRouterTyp());// 查看是否有比当前版本新的
							if (nn != null)
								l1.add(nn);// 如果有新版本，则添加
						}
					}*/
					String temp = dr.Setit(l1);
					out.write(dr.Return(temp));
				}	
			} else {
				logger.error("消息请求loginSta不合法");
				dr.setIfSuport(0);
				if (out != null){
					out.write(dr.Return(""));
					out.close();
				}
				return;
			}

			if (m == null) {
				Mac m0 = new Mac();
				m0.setTime(new Date());
				m0.setMac(ownMac);
				macService.add(m0);
			} else {
				m.setTime(new Date());
				macService.update(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
}
