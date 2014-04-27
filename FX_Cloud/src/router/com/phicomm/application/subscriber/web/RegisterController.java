package com.phicomm.application.subscriber.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phicomm.application.subscriber.model.ACSServlet;
import com.phicomm.application.subscriber.model.ActiveReturn;
import com.phicomm.application.subscriber.util.Base64;
import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.LoginReturn;
import com.phicomm.application.subscriber.model.LogoutReturn;
import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.News;
import com.phicomm.application.subscriber.model.PhoneUser;
import com.phicomm.application.subscriber.model.ResetPwdReturn;
import com.phicomm.application.subscriber.model.RouterLoginStatusReturn;
import com.phicomm.application.subscriber.model.SendmailActiveThreadPool;
import com.phicomm.application.subscriber.model.SendmailResetThreadPool;
import com.phicomm.application.subscriber.model.SendmailThreadPool;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.UserDto;
import com.phicomm.application.subscriber.service.IDeviceService;
import com.phicomm.application.subscriber.service.IMacService;
import com.phicomm.application.subscriber.service.INewsService;
import com.phicomm.application.subscriber.service.IPhoneUserService;
import com.phicomm.application.subscriber.service.IUserService;
import com.phicomm.application.subscriber.util.PropertiesUtil;
import com.phicomm.application.subscriber.util.XMLUtil;

//手机_登入_控制器
@Controller
@RequestMapping(value = "/login/")
public class RegisterController {
	public static final Logger logger = Logger.getLogger(RegisterController.class);
	private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private IMacService macService;
	private IDeviceService deviceService;
	private IPhoneUserService phoneuserService;
	private IUserService userService;
	private INewsService newsService;
	
	public INewsService getNewsService() {
		return newsService;
	}

	@Resource
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public IMacService getMacService() {
		return macService;
	}

	@Resource
	public void setMacService(IMacService macService) {
		this.macService = macService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}
	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public IPhoneUserService getPhoneUserService() {
		return phoneuserService;
	}

	@Resource
	public void setPhoneUserService(IPhoneUserService phoneuserService) {
		this.phoneuserService = phoneuserService;
	}

	/*
	 * 路由器-状态
	 */
	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public void status(User user, Device d, HttpServletResponse res,
			HttpServletRequest req) throws IOException {

		// long time1 = System.currentTimeMillis();
		String length = null;
		res.setHeader("Content-Type", "text/xml; charset=UTF-8");
		res.setHeader("SOAPAction", "");
		res.setCharacterEncoding("utf-8");

		PrintWriter out = null;
		out = res.getWriter();

		if (req.getContentLength() == 0) { // 如果内容为空则返回204
			res.setStatus(204);
			res.setContentLength(0);
			// logger.warn("接收到空内容，直接返回204");
			return;
		} else {
			res.setHeader("Connection", "Keep-Alive");
			// 解析soap,添加admin账户，如果路由器的MAC已经存在，那么直接更改admin账户

			ACSServlet acs = new ACSServlet();
			RouterLoginStatusReturn rlsr = new RouterLoginStatusReturn();
			rlsr = acs.processRequest(req, res);

			// logger.warn("接收完TR069,消耗时间" +
			// (System.currentTimeMillis()-time1)+"毫秒");
			for (String code : rlsr.EventCode) {
				// 注册
				logger.info("evencode:" + "[" + code + "]");
				if (code.equals("0 BOOTSTRAP")) {
					/*
					 * 检查邮箱地址格式/邮箱是否真实存在 checkEmail cm = new checkEmail(); if
					 * (cm.checkemail(rlsr.getMAIL_ADDRESS()) == false) {
					 * System.out.println("邮箱不合法"); res.setStatus(400);
					 * out.write("Email is not support"); out.flush();
					 * out.close(); return; }
					 */
					// logger.warn("进入0 BOOTSTRAP,消耗时间" +
					// (System.currentTimeMillis()-time1)+"毫秒");
					if (rlsr.getMAIL_ADDRESS() != null)
						user.setMailAddress(rlsr.getMAIL_ADDRESS());
					if (rlsr.getPASSWORD() != null)
						user.setPassword(MD5UTIL.MD5(rlsr.getPASSWORD()));
					if (rlsr.getUSERNAME() != null)
						user.setUsername(rlsr.getUSERNAME());
					if (rlsr.getMP_NUMBER() != null)
						user.setPhone(rlsr.getMP_NUMBER());
					if (rlsr.getPROVINCE() != null)
						user.setProvince(rlsr.getPROVINCE());
					if (rlsr.getZIP_CODE() != null)
						user.setZipCode(rlsr.getZIP_CODE());
					user.setCreateTime(new Date()); // 注册时间为当前时间
					
					if (rlsr.getMAIL_ADDRESS() != null && rlsr.getPASSWORD() != null) {
						User actUser = userService.searchByMailPsdAct(rlsr.getMAIL_ADDRESS());
						if (actUser != null) {
							//如果已激活，自动添加时间戳
							user.setMailAddress(new Date().getTime() + "." + rlsr.getMAIL_ADDRESS());
							user.setIsActive(2);
							userService.add(user);
							logger.warn("注册失败:RegStatus=0&ErrorCode=9001");
							length = "RegStatus=0&ErrorCode=9001";
							res.setContentLength(length.length());
							res.setStatus(400);
							out.write("RegStatus=0&ErrorCode=9001");
							if (out != null)
								out.close();
							return;
						}
					}
					try {
						userService.add(user);
						// logger.warn("添加完用户,消耗时间" +
						// (System.currentTimeMillis()-time1)+"毫秒");
						// 设备的MAC和邮箱绑定，设备表里面对应MAC绑定邮箱
						d = deviceService.loadbymac(rlsr.getPubMAC());// 根据router发来的MAC查找设备
						if (d == null) {
							// 如果没有设备，则添加设备并绑定Email
							Device dr = new Device();
							dr.setDeviceMac(rlsr.getPubMAC());
							dr.setUserEmail(rlsr.getMAIL_ADDRESS());
							dr.setDeviceTyp(rlsr.getDevcTyp());
							dr.setUid(user.getId());
							if (rlsr.getWAN_IP() != null)
								dr.setDeviceWanIp(rlsr.getWAN_IP());
							if (rlsr.getAdminUsername() != null)
								dr.setAdminName(rlsr.getAdminUsername());
							if (rlsr.getAdminPassword() != null)
								dr.setAdminPassword(rlsr.getAdminPassword());
							if (rlsr.getACT_STATUS() != null)
								dr.setActiveStatus(rlsr.getACT_STATUS());
							if (rlsr.getDevcVer() != null)
								dr.setDeviceVer(rlsr.getDevcVer());
							if(rlsr.getHardVer()!=null)
								dr.setHardVer(rlsr.getHardVer());
							dr.setDeviceTime(new Date());
							deviceService.add(dr);
						} else {
							// 如果有设备了，则为其绑定Eamil
							d.setUserEmail(rlsr.getMAIL_ADDRESS());
							if (rlsr.getWAN_IP() != null)
								d.setDeviceWanIp(rlsr.getWAN_IP());
							if (rlsr.getAdminUsername() != null)
								d.setAdminName(rlsr.getAdminUsername());
							if (rlsr.getAdminPassword() != null)
								d.setAdminPassword(rlsr.getAdminPassword());
							if (rlsr.getACT_STATUS() != null)
								d.setActiveStatus(rlsr.getACT_STATUS());
							if (rlsr.getDevcVer() != null)
								d.setDeviceVer(rlsr.getDevcVer());
							if (rlsr.getDevcTyp() != null)
								d.setDeviceTyp(rlsr.getDevcTyp());
							if(rlsr.getHardVer()!=null)
								d.setHardVer(rlsr.getHardVer());
							d.setUid(user.getId());
							d.setDeviceTime(new Date());
							deviceService.update(d);
						}
						// logger.warn("添加或更新完设备,消耗时间" +
						// (System.currentTimeMillis()-time1)+"毫秒");

						SendmailThreadPool sendmail = SendmailThreadPool.getInstance(rlsr.getUSERNAME(),rlsr.getMAIL_ADDRESS());

						Thread t1 = new Thread(sendmail);
						t1.start();
						logger.info("注册邮件:"+"["+rlsr.getUSERNAME()+","+rlsr.getMAIL_ADDRESS()+"]");

					} catch (Exception ex) {
						logger.warn("用户注册失败：" + ex.toString());
						length = "RegStatus=0&ErrorCode=9009";// 异常
						res.setContentLength(length.length());
						res.setStatus(400);
						out.write("RegStatus=0&ErrorCode=9009");
					} finally {
						// logger.warn("结束0 BOOTSTRAP,消耗时间" +
						// (System.currentTimeMillis()-time1)+"毫秒");
						if (out != null)
							out.close();
					}

				}
				// 第一联网，报状态
				else if (code.equals("1 BOOT")) {
					// logger.warn("进入第一次联网，报告状态");
					try {
						System.out.println("PublicMac" + rlsr.getPubMAC());
						d = deviceService.loadbymac(rlsr.getPubMAC());
						System.out.println(d);
						if (d == null) {
							// 如果此路由器MAC没有被注册，则添加
							// logger.warn("此路由器MAC没有被注册，则添加");
							Device dr = new Device();

							if (rlsr.getAdminUsername() != null)
								dr.setAdminName(rlsr.getAdminUsername());
							if (rlsr.getAdminPassword() != null)
								dr.setAdminPassword(rlsr.getAdminPassword());
							if (rlsr.getPubMAC() != null)
								dr.setDeviceMac(rlsr.getPubMAC());
							if (rlsr.getWAN_IP() != null)
								dr.setDeviceWanIp(rlsr.getWAN_IP());
							if (rlsr.getDevcTyp() != null)
								dr.setDeviceTyp(rlsr.getDevcTyp());
							if (rlsr.getACT_STATUS() != null)
								dr.setActiveStatus(rlsr.getACT_STATUS());
							if (rlsr.getDevcVer() != null)
								dr.setDeviceVer(rlsr.getDevcVer());
							if(rlsr.getHardVer()!=null)
								dr.setHardVer(rlsr.getHardVer());

							if (rlsr.getMAIL_ADDRESS() != null && rlsr.getPASSWORD() != null) {
								user = userService.loadByMailPsw(rlsr.getMAIL_ADDRESS(),rlsr.getPASSWORD());
								if (user != null) {
									dr.setUid(user.getId());
									dr.setUserEmail(user.getMailAddress());

									if (rlsr.getUSERNAME() != null)
										user.setUsername(rlsr.getUSERNAME());
									if (rlsr.getMP_NUMBER() != null)
										user.setPhone(rlsr.getMP_NUMBER());
									if (rlsr.getPROVINCE() != null)
										user.setProvince(rlsr.getPROVINCE());
									if (rlsr.getZIP_CODE() != null)
										user.setZipCode(rlsr.getZIP_CODE());
									userService.update(user);
								} else {
									user = new User();
									if (rlsr.getMAIL_ADDRESS() != null)
										user.setMailAddress(rlsr.getMAIL_ADDRESS());
									if (rlsr.getPASSWORD() != null)
										user.setPassword(MD5UTIL.MD5(rlsr.getPASSWORD()));
									if (rlsr.getUSERNAME() != null)
										user.setUsername(rlsr.getUSERNAME());
									if (rlsr.getMP_NUMBER() != null)
										user.setPhone(rlsr.getMP_NUMBER());
									if (rlsr.getPROVINCE() != null)
										user.setProvince(rlsr.getPROVINCE());
									if (rlsr.getZIP_CODE() != null)
										user.setZipCode(rlsr.getZIP_CODE());
									userService.add(user);

									dr.setUid(user.getId());
									dr.setUserEmail(user.getMailAddress());
								}
							}

							System.out.println("状态，USERNAME：" + rlsr.getAdminUsername());
							System.out.println("状态，PSD：" + rlsr.getAdminPassword());
							System.out.println("状态，MAC：" + rlsr.getPubMAC());
							System.out.println("状态，Wan_IP：" + rlsr.getWAN_IP());
							System.out.println("状态，DevcTyp：" + rlsr.getDevcTyp());
							dr.setDeviceTime(new Date());
							deviceService.add(dr);
						} else {
							// 如果此MAC已经被注册，则update
							// logger.warn("此MAC已经被注册，则update");

							System.out.println("状态，USERNAME：" + rlsr.getAdminUsername());
							System.out.println("状态，PSD：" + rlsr.getAdminPassword());
							System.out.println("状态，MAC：" + rlsr.getPubMAC());
							System.out.println("状态，Wan_IP：" + rlsr.getWAN_IP());
							System.out.println("状态，DevcTyp：" + rlsr.getDevcTyp());

							try {
								if (rlsr.getAdminUsername() != null)
									d.setAdminName(rlsr.getAdminUsername());
								if (rlsr.getAdminPassword() != null)
									d.setAdminPassword(rlsr.getAdminPassword());
								if (rlsr.getWAN_IP() != null)
									d.setDeviceWanIp(rlsr.getWAN_IP());
								if (rlsr.getDevcTyp() != null)
									d.setDeviceTyp(rlsr.getDevcTyp());
								if (rlsr.getACT_STATUS() != null)
									d.setActiveStatus(rlsr.getACT_STATUS());
								if (rlsr.getDevcVer() != null)
									d.setDeviceVer(rlsr.getDevcVer());
								if(rlsr.getHardVer()!=null)
									d.setHardVer(rlsr.getHardVer());

								if (rlsr.getMAIL_ADDRESS() != null && rlsr.getPASSWORD() != null) {
									user = userService.loadByMailPsw(rlsr.getMAIL_ADDRESS(),rlsr.getPASSWORD());
									if (user != null) {
										d.setUid(user.getId());
										d.setUserEmail(user.getMailAddress());

										if (rlsr.getUSERNAME() != null)
											user.setUsername(rlsr.getUSERNAME());
										if (rlsr.getMP_NUMBER() != null)
											user.setPhone(rlsr.getMP_NUMBER());
										if (rlsr.getPROVINCE() != null)
											user.setProvince(rlsr.getPROVINCE());
										if (rlsr.getZIP_CODE() != null)
											user.setZipCode(rlsr.getZIP_CODE());
										userService.update(user);
									} else {
										user = new User();
										if (rlsr.getMAIL_ADDRESS() != null)
											user.setMailAddress(rlsr.getMAIL_ADDRESS());
										if (rlsr.getPASSWORD() != null)
											user.setPassword(MD5UTIL.MD5(rlsr.getPASSWORD()));
										if (rlsr.getUSERNAME() != null)
											user.setUsername(rlsr.getUSERNAME());
										if (rlsr.getMP_NUMBER() != null)
											user.setPhone(rlsr.getMP_NUMBER());
										if (rlsr.getPROVINCE() != null)
											user.setProvince(rlsr.getPROVINCE());
										if (rlsr.getZIP_CODE() != null)
											user.setZipCode(rlsr.getZIP_CODE());
										userService.add(user);
										d.setUid(user.getId());
										d.setUserEmail(user.getMailAddress());
									}
								}
								d.setDeviceTime(new Date());
								deviceService.update(d);
							} catch (Exception ex) {
								logger.warn("异常" + ex.toString(), ex);
							}
						}
					} catch (Exception ex) {
						logger.warn(ex.toString(), ex);
						res.setStatus(400);
						out = res.getWriter();
						out.write("Add Router Device Failed");
					} finally {
						out.close();
					}
				}
				// change 上报状态 用户名密码变更（本地） 激活-邮箱未改变
				else if (code.equals("4 VALUE CHANGE")) {
					// logger.warn("进入，上报状态 用户名密码变更（本地） 激活-邮箱未改变");
					try {
						System.out.println("路由器-本地管理密码变更:" + rlsr.getAdminUsername());
						System.out.println("路由器-本地管理密码变更:" + rlsr.getAdminPassword());

						d = deviceService.loadbymac(rlsr.getPubMAC());
						if (d == null) { // 如果修改的设备不存在
							res.setStatus(400);
							out.write("Your Device Is Not Exist");

						} else {
							user = userService.load(d.getUid());
							if (rlsr.getPASSWORD() != null)
								user.setPassword(MD5UTIL.MD5(rlsr.getPASSWORD()));
							if (rlsr.getUSERNAME() != null)
								user.setUsername(rlsr.getUSERNAME());
							user.setPhone(rlsr.getMP_NUMBER()==null?"":rlsr.getMP_NUMBER());
							if (rlsr.getPROVINCE() != null)
								user.setProvince(rlsr.getPROVINCE());
							user.setZipCode(rlsr.getZIP_CODE()==null?"":rlsr.getZIP_CODE());
							userService.update(user);

							if (rlsr.getAdminUsername() != null)
								d.setAdminName(rlsr.getAdminUsername());
							if (rlsr.getAdminPassword() != null)
								d.setAdminPassword(rlsr.getAdminPassword());
							if (rlsr.getWAN_IP() != null)
								d.setDeviceWanIp(rlsr.getWAN_IP());
							if (rlsr.getACT_STATUS() != null)
								d.setActiveStatus(rlsr.getACT_STATUS());
							if(rlsr.getHardVer()!=null)
								d.setHardVer(rlsr.getHardVer());
							d.setDeviceTime(new Date());
							deviceService.update(d);
						}

					} catch (Exception ex) {
						logger.warn("异常" + ex.toString(), ex);
						res.setStatus(400);
						out.write("Modify Router Device Message Failed");
					} finally {
						out.close();
					}

				} else if (code.equals("X ACTIVE")) {
					// logger.warn("用户帐号激活");
					try {
						// 1.判断请求中是否包含邮箱内容，没有返回8005
						if (rlsr.getMAIL_ADDRESS() == null) {
							res.setStatus(400);
							length = "ActiveStatus=0&ErrorCode=8005";
							res.setContentLength(length.length());
							out.write("ActiveStatus=0&ErrorCode=8005");
						}
						// 2.判断邮箱个数, 邮箱为0 表示未注册返回8002
						// 3.判断是否已激活，已激活返回8001
						// 4.未激活，发送邮件
						else {
							List<User> u = userService.loadByMail(rlsr
									.getMAIL_ADDRESS());
							List<User> ul = userService.loadByUsernamePsd(
									rlsr.getMAIL_ADDRESS(), rlsr.getPASSWORD());
							if (u.size() == 0) {
								res.setStatus(400);
								length = "ActiveStatus=0&ErrorCode=8002";
								res.setContentLength(length.length());
								out.write("ActiveStatus=0&ErrorCode=8002");
								out.close();
								return;
							} else {
								for (User us : u) {
									if (us.getIsActive() == 1) {
										res.setStatus(400);
										length = "ActiveStatus=0&ErrorCode=8001";
										res.setContentLength(length.length());
										out.write("ActiveStatus=0&ErrorCode=8001");
										out.close();
										return;
									}
								}
								String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
								String linkAddress = "";
								UserDto ud = new UserDto();
								String activeCode = randActiveCode();
								for (User us : u) {
									if (linkAddress != null) {
										linkAddress += "</br>--------------------------------</br>";
									}
									ud = userService.loadById(us.getId());
									linkAddress = linkAddress 
											+ "用户名:" + ud.getUserName() + "</br>" 
											+ "手机号:" + ud.getMobelPhone() + "</br>" 
											+ "地区:" + ud.getCity() + "</br>" 
											+ "邮编:" + ud.getPostCode() + "</br>" 
											+ "注册时间:" + new SimpleDateFormat(DATEFORMAT).format(us.getCreateTime()) + "</br>" 
											+ "<a href=\"http://" + host + "/FX_Cloud/register/" + us.getId() + "/" + "?mod=active&activecode="
											+ MD5UTIL.MD5(activeCode) + "\">"
											+ "(http://" + host + "/FX_Cloud/register/" + us.getId() + "/" + "?mod=active&activecode="
											+ MD5UTIL.MD5(activeCode) + ")</a>";
									if (ul.contains(us)) {
										us.setActiveCode(activeCode);
										userService.update(us);
									}
								}
								SendmailActiveThreadPool sendmail = SendmailActiveThreadPool.getInstance(u.get(0).getUsername(), u.get(0).getMailAddress(),linkAddress);
								Thread t1 = new Thread(sendmail);
								t1.start();
								logger.info("激活邮件:"+"["+u.get(0).getUsername()+","+u.get(0).getMailAddress()+"]");
								length = "ActiveStatus=1&ActiveCode=" + activeCode;
								res.setContentLength(length.length());
								res.setStatus(200);
								out.write("ActiveStatus=1&ActiveCode=" + activeCode);
							}
						}
					} catch (Exception ex) {
						logger.warn("异常" + ex.toString(), ex);
						res.setStatus(400);
					} finally {
						out.close();
					}
				}
				//路由器请求用户信息
				else if (code.equals("X_GET_ACCOUNT_INFO")) {
					try {
						String username = rlsr.getMAIL_ADDRESS();
						String password = rlsr.getPASSWORD();
						System.out.println("username:"+username+",password:"+password);
						if(username==null || password == null || "".equals(username.trim()) || "".equals(password.trim())){
							System.out.println("密码或用户名为空");
							length = "GetInfoSts=8006";
							res.setContentLength(length.length());
							out.write(length);
							if(out!=null) out.close();
							return;
						}
						
						User u = userService.searchByMailPsdAct(username);
						if(u==null){
							System.out.println("没找到！");
							length = "GetInfoSts=8006";
							res.setContentLength(length.length());
							out.write(length);
							if(out!=null) out.close();
							return;
						}
						String mail = u.getUsername();
						String pvc = u.getProvince();
						String ph = u.getPhone();
						String zce = u.getZipCode();

						length="GetInfoSts=0&cloud_username="+(mail==null?"":PropertiesUtil.chinaToUnicode(mail))+"&cloud_province="
								+(pvc==null?"":pvc)+"&cloud_phone="+(ph==null?"":ph)+"&cloud_zipCode="+(zce==null?"":zce);
						res.setContentLength(length.length());
						out.write(length);
					} catch (Exception e) {
						System.out.println("异常了");
						res.setStatus(200);
						length = "GetInfoSts=8006";
						res.setContentLength(length.length());
						out.write(length);
						e.printStackTrace();
					}finally{
						if(out!=null) out.close();
					}
					
				}//忘记密码
				else if (code.equals("X FORGETPWD")) {
					try {
						String loginMail = rlsr.getMAIL_ADDRESS();

						if (loginMail == null) {
							logger.warn("路由器参数错误");
							res.setStatus(400);
							return;
						}
						else {
							List<User> u = userService.loadByMail(loginMail);
							if(u.size() == 0){
								logger.warn("未找到与该邮箱匹配的记录");
								res.setStatus(400);
								return;
							}
							else {
								String activeCode = new RegisterController().randActiveCode();
								u.get(0).setActiveCode(activeCode);
								userService.update(u.get(0));
								String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
								String linkAddress = 
									"<a href=\"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode)+"\">" + 
									"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode) + "</a>";
								SendmailResetThreadPool sendmail= SendmailResetThreadPool.getInstance(u.get(0).getUsername(),u.get(0).getMailAddress(),linkAddress);
								Thread t1=new Thread(sendmail);
								t1.start();
								logger.info("密码修改邮件:"+"["+u.get(0).getUsername()+","+u.get(0).getMailAddress()+"]");
							}
						}
					} catch (Exception e) {
						logger.warn("服务器异常");
						res.setStatus(400);
					}finally{
						if(out!=null) out.close();
					}
				}
				//一键升级路由器
				else if (code.equals("X PHICOMM swUpdateCheck")) {
					System.out.println(rlsr.getDevcTyp()+","+rlsr.getDevcVer());
					String rt = "";
					try {
						String devcType = rlsr.getDevcTyp();
						if("".equals(devcType.trim()) || "".equals(rlsr.getDevcVer())) {
							//路由器参数不正确
							logger.warn("路由器参数不正确");
							rt = "UpdateStatus=1&ErrorCode=8803&VerNum=&Version=&VerDesc=&PubTime=&URL=&Size=";
							res.setContentLength(rt.length());
							out.write(rt);
							if(out!=null) out.close();
							return;
						}
						else {
							News news = newsService.loadbytime(devcType,rlsr.getHardVer());
							if(news == null) {
								//未找到版本信息，路由器软件版本是最新版本
								logger.info("未找到版本信息，路由器软件版本是最新版本");
								rt = "UpdateStatus=1&ErrorCode=&VerNum=0&Version=&VerDesc=&PubTime=&URL=&Size=";
								res.setContentLength(rt.length());
								out.write(rt);
								if(out!=null) out.close();
								return;
							}
							boolean checkNew = false;
							boolean checkFile = false;
							String ver_rt = rlsr.getDevcVer().substring(rlsr.getDevcVer().indexOf("V")+1);
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
								rt = "UpdateStatus=1&ErrorCode=&VerNum=0&Version=&VerDesc=&PubTime=&URL=&Size=";
								res.setContentLength(rt.length());
								out.write(rt);
								if(out!=null) out.close();
								return;
							} else {
								//有最新的软件版本
								//获取软件升级文件大小
								String size = "";
								Properties prop = PropertiesUtil.getIpProp();
								String uploadAddr = prop.getProperty("upload");
								String fileName = news.getUrl().substring(news.getUrl().lastIndexOf("upload/")+7);
								
								File file = new File(uploadAddr);
								File[] files = file.listFiles();
								if(files == null || file == null){
									//文件或文件夹未找到
									logger.warn("文件或文件夹未找到");
									rt = "UpdateStatus=1&ErrorCode=8802&VerNum=&Version=&VerDesc=&PubTime=&URL=&Size=";
									res.setContentLength(rt.length());
									out.write(rt);
									if(out!=null) out.close();
									return;
								}
								for(File f:files) {
									System.out.println("f.getName:"+f.getName());
									System.out.println("fileName:"+fileName);
									if(fileName.trim().equals(f.getName())) {
										//找到对应的文件，获取文件大小
										size = String.valueOf(f.length());
										checkFile = true;
										break;
									} 
								}
								if(!checkFile) {
									//未找到对应的文件，服务器报异常
									logger.warn("未找到对应的文件");
									rt = "UpdateStatus=1&ErrorCode=8802&VerNum=&Version=&VerDesc=&PubTime=&URL=&Size=";
									res.setContentLength(rt.length());
									out.write(rt);
									if(out!=null) out.close();
									return;
								}
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								System.out.println(sdf.format(news.getTime()));
								rt = "UpdateStatus=1&ErrorCode=&VerNum=1&Version=" + news.getRouterVer() + 
										"&VerDesc=" + PropertiesUtil.chinaToUnicode(news.getVerDesc()) +
										"&PubTime=" + sdf.format(news.getTime()) + 
										"&URL=" + news.getUrl() + 
										"&Size=" + size;
								res.setContentLength(rt.length());
								out.write(rt);
								if(out!=null) out.close();
								return;
							}
						}
					} catch (Exception e) {
						logger.warn("服务器异常");
						res.setStatus(200);
						rt = "UpdateStatus=1&ErrorCode=8802&VerNum=&Version=&VerDesc=&PubTime=&URL=&Size=";
						res.setContentLength(rt.length());
						out.write(rt);
						e.printStackTrace();
					}finally{
						if(out!=null) out.close();
					}
				}
				// 登录 激活-邮箱变更
				else if (code.equals("X LOGIN")) {
					logger.info("进入，激活-邮箱变更");
					if (rlsr.EventCode.size() != 1) {
						try {
							d = deviceService.loadbymac(rlsr.getPubMAC());
							if (d == null) {
								logger.warn("设备不存在");
								length = "LoginStatus=0&ErrorCode=8002";
								res.setContentLength(length.length());
								res.setStatus(400);
								out.write("LoginStatus=0&ErrorCode=8002");
								if (out != null)
									out.close();
								return;
							} else {
								user = userService.load(d.getUid());

								if (rlsr.getMAIL_ADDRESS() != null)
									user.setMailAddress(rlsr.getMAIL_ADDRESS());
								if (rlsr.getPASSWORD() != null)
									user.setPassword(MD5UTIL.MD5(rlsr.getPASSWORD()));
								if (rlsr.getUSERNAME() != null)
									user.setUsername(rlsr.getUSERNAME());
								if (rlsr.getMP_NUMBER() != null)
									user.setPhone(rlsr.getMP_NUMBER());
								if (rlsr.getPROVINCE() != null)
									user.setProvince(rlsr.getPROVINCE());
								if (rlsr.getZIP_CODE() != null)
									user.setZipCode(rlsr.getZIP_CODE());
								userService.update(user);

								d.setUserEmail(rlsr.getMAIL_ADDRESS());
								d.setActiveStatus(rlsr.getACT_STATUS());
								if (rlsr.getAdminUsername() != null)
									d.setAdminName(rlsr.getAdminUsername());
								if (rlsr.getAdminPassword() != null)
									d.setAdminPassword(rlsr.getAdminPassword());
								if (rlsr.getWAN_IP() != null)
									d.setDeviceWanIp(rlsr.getWAN_IP());
								if (rlsr.getDevcVer() != null)
									d.setDeviceVer(rlsr.getDevcVer());
								if(rlsr.getHardVer()!=null)
									d.setHardVer(rlsr.getHardVer());
								d.setDeviceTime(new Date());
								deviceService.update(d);
							}
						} catch (Exception ex) {
							logger.warn("异常" + ex.toString(), ex);
							res.setStatus(400);
							out.write("Fail to active device");
							System.out.println("激活失败");
						} finally {
							out.close();
						}
					} else {
						logger.info("进入单纯的账户登入功能！");
						try {
							// 查看用户是否存在
							java.util.List<User> u0 = userService.loadByMail(rlsr.getMAIL_ADDRESS());
							if (u0.size() == 0) {
								// logger.warn("用户名不存在");
								length = "LoginStatus=0&ErrorCode=8002";
								res.setContentLength(length.length());
								res.setStatus(400);
								out.write("LoginStatus=0&ErrorCode=8002");
								if (out != null)
									out.close();
								return;
							}
							// 查看密码是否正确
							java.util.List<User> u1 = userService.loadByUsernamePsd(rlsr.getMAIL_ADDRESS(),rlsr.getPASSWORD());
							if (u1.size() == 0) {
								// logger.warn("用户密码错误");
								length = "LoginStatus=0&ErrorCode=8004";
								res.setContentLength(length.length());
								res.setStatus(400);
								out.write("LoginStatus=0&ErrorCode=8004");
								if (out != null)
									out.close();
								return;

							}
							// 查看是否激活
							User u2 = userService.loadByMailPsdAct(rlsr.getMAIL_ADDRESS(), rlsr.getPASSWORD());
							if (u2 == null) {
								if (u0.size() > 1) {
									int tempi = 0;
									for (User u3 : u0) {
										if (u3.getIsActive() == 1)
											tempi++;
									}
									if (tempi == 0) {
										// 无效->有重复的邮箱->全部都未激活
										// logger.warn("无效->有重复的邮箱->全部都未激活");
										length = "LoginStatus=0&ErrorCode=8003";
										res.setContentLength(length.length());
										res.setStatus(400);
										out.write("LoginStatus=0&ErrorCode=8003");
										if (out != null)
											out.close();
										return;
									} else {
										// 无效->有重复的邮箱->别人激活了
										// logger.warn("无效->有重复的邮箱->别人激活了");
										length = "LoginStatus=0&ErrorCode=8001";
										res.setContentLength(length.length());
										res.setStatus(400);
										out.write("LoginStatus=0&ErrorCode=8001");
										if (out != null)
											out.close();
										return;
									}
								} else {
									// 无效->没有重复的邮箱
									// logger.warn("无效->没有重复的邮箱");
									length = "LoginStatus=0&ErrorCode=8003";
									res.setContentLength(length.length());
									res.setStatus(400);
									out.write("LoginStatus=0&ErrorCode=8003");
									if (out != null)
										out.close();
									return;
								}
							} else {
								// 登入成功
								d = deviceService.loadbymac(rlsr.getPubMAC());
								if (d == null) {
									// logger.warn("没有设备，则添加设备并绑定Email");
									Device dr = new Device();
									dr.setDeviceMac(rlsr.getPubMAC());
									dr.setUserEmail(rlsr.getMAIL_ADDRESS());
									if (rlsr.getDevcTyp() != null)
										dr.setDeviceTyp(rlsr.getDevcTyp());

									dr.setUid(u2.getId());
									if (rlsr.getWAN_IP() != null)
										dr.setDeviceWanIp(rlsr.getWAN_IP());
									if (rlsr.getAdminUsername() != null)
										dr.setAdminName(rlsr.getAdminUsername());
									if (rlsr.getAdminPassword() != null)
										dr.setAdminPassword(rlsr.getAdminPassword());
									if (rlsr.getACT_STATUS() != null)
										dr.setActiveStatus(rlsr.getACT_STATUS());
									if (rlsr.getDevcVer() != null)
										dr.setDeviceVer(rlsr.getDevcVer());
									if(rlsr.getHardVer()!=null)
										dr.setHardVer(rlsr.getHardVer());
									dr.setDeviceTime(new Date());
									deviceService.add(dr);
								} else {
									if (rlsr.getAdminUsername() != null)
										d.setAdminName(rlsr.getAdminUsername());
									if (rlsr.getAdminPassword() != null)
										d.setAdminPassword(rlsr.getAdminPassword());
									if (rlsr.getWAN_IP() != null)
										d.setDeviceWanIp(rlsr.getWAN_IP());
									if (rlsr.getACT_STATUS() != null)
										d.setActiveStatus(rlsr.getACT_STATUS());
									if (rlsr.getDevcVer() != null)
										d.setDeviceVer(rlsr.getDevcVer());
									if (rlsr.getDevcTyp() != null)
										d.setDeviceTyp(rlsr.getDevcTyp());
									if(rlsr.getHardVer()!=null)
										d.setHardVer(rlsr.getHardVer());
									// 更新关联
									d.setUserEmail(u2.getMailAddress());
									d.setUid(u2.getId());
									d.setDeviceTime(new Date());
									deviceService.update(d);
								}
								length = "LoginStatus=1";
								res.setContentLength(length.length());
								res.setStatus(200);
								out.write("LoginStatus=1");
							}
						} catch (Exception ex) {
							logger.warn("异常" + ex.toString(), ex);
							res.setStatus(400);
							out.write("Fail to login");
							System.out.println("登录失败");
						} finally {
							if (out != null)
								out.close();
						}
					}
				}
			}
			return;
		}
	}

	// 路由器-登录及退出
	@RequestMapping(value = "/loginandlogout", method = RequestMethod.POST)
	public void loginandlogout(HttpServletResponse res, HttpServletRequest req)
			throws IOException {

		if (req.getContentLength() == 0) {
			res.setHeader("Content-Type", "text/xml; charset=UTF-8");
			res.setHeader("SOAPAction", "");
			res.setStatus(204);
			return;
		} else {

			ACSServlet acs = new ACSServlet();
			RouterLoginStatusReturn rlsr = new RouterLoginStatusReturn();
			rlsr = acs.processRequest(req, res);
			System.out.println("当前路由器的登录和退出的状态为:" + rlsr.getACT_STATUS());

			res.setHeader("Content-Type", "text/xml; charset=UTF-8");
			res.setHeader("SOAPAction", "");
		}
		return;
	}

	// 手机激活
	@RequestMapping(value = "/active")
	public void login(String loginMail, String loginPsw, HttpServletResponse res)
			throws IOException {
		// logger.warn("手机：用户帐号激活");
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		try {
			loginPsw = Base64.decode64(loginPsw);// 解密密码
			ActiveReturn lr = new ActiveReturn();
			lr.setRetState(1);
			lr.setIfSuport(1);

			// 1.判断请求中是否包含邮箱内容，没有返回8005
			if (loginMail == null) {
				lr.setActiveSta(0);
			}
			// 2.判断邮箱个数, 邮箱为0 表示未注册返回8002
			// 3.判断是否已激活，已激活返回8001
			// 4.未激活，发送邮件
			else {
				List<User> u = userService.loadByMail(loginMail);
				List<User> ul = userService.loadByUsernamePsd(loginMail,loginPsw);
				if (u.size() == 0) {
					lr.setActiveSta(0);
				} else {
					for (User us : u) {
						if (us.getIsActive() == 1) {
							lr.setActiveSta(0);
							String checkActive = lr.Return();
							if (out != null){
								out.write(checkActive);
								out.close();
							}
							return;
						}
					}

					String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
					String linkAddress = "";
					UserDto ud = new UserDto();
					String activeCode = randActiveCode();
					for (User us : u) {
						if (linkAddress != null) {
							linkAddress += "</br>--------------------------------</br>";
						}
						ud = userService.loadById(us.getId());
						linkAddress = linkAddress 
								+ "用户名:" + ud.getUserName() + "</br>" 
								+ "手机号:" + ud.getMobelPhone() + "</br>" 
								+ "地区:" + ud.getCity() + "</br>" 
								+ "邮编:" + ud.getPostCode() + "</br>" 
								+ "注册时间:" + new SimpleDateFormat(DATEFORMAT).format(us.getCreateTime()) + "</br>" 
								+ "<a href=\"http://" + host + "/FX_Cloud/register/" + us.getId() + "/" + "?mod=active&activecode="
								+ MD5UTIL.MD5(activeCode) + "\">"
								+ "(http://" + host + "/FX_Cloud/register/" + us.getId() + "/" + "?mod=active&activecode="
								+ MD5UTIL.MD5(activeCode) + ")</a>";
						if (ul.contains(us)) {
							us.setActiveCode(activeCode);
							userService.update(us);
						}
					}
					SendmailActiveThreadPool sendmail = SendmailActiveThreadPool
							.getInstance(u.get(0).getUsername(), u.get(0)
									.getMailAddress(), linkAddress);
					Thread t1 = new Thread(sendmail);
					t1.start();
					logger.info("激活邮件:"+"["+u.get(0).getUsername()+","+u.get(0).getMailAddress()+"]");
					lr.setActiveSta(1);
					lr.setActiveCode(activeCode);
				}
			}
			String checkActive = lr.Return();
			if (out != null){
				out.write(checkActive);
				out.close();
			}
		} catch (Exception ex) {
			logger.warn(ex.toString(), ex);
			res.setStatus(500);
			if (out != null)
				out.close();

		}
	}

	//忘记密码
	@RequestMapping(value = "/reset")
	public void reset(String loginMail, HttpServletResponse res)
			throws IOException {
		// logger.warn("手机：用户帐号激活");
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		try {
			ResetPwdReturn lr = new ResetPwdReturn();
			lr.setRetState(1);
			lr.setIfSuport(1);

			if (loginMail == null) {
				lr.setResetSta(0);
			}
			else {
				List<User> u = userService.loadByMail(loginMail);
				if(u.size() == 0){
					lr.setResetSta(0);
					lr.setRetReason(1);
				}
				else if(u.size() > 1){
					lr.setResetSta(0);
					lr.setRetReason(2);
				}
				else if(u.size() == 1 && u.get(0).getIsActive() != 1){
					lr.setResetSta(0);
					lr.setRetReason(2);
				}
				else {
					String activeCode = new RegisterController().randActiveCode();
					u.get(0).setActiveCode(activeCode);
					userService.update(u.get(0));
					String host = XMLUtil.getVersionDocument().getRootElement().elementText("host");
					String linkAddress = 
						"<a href=\"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode)+"\">" + 
						"http://" + host + "/FX_Cloud/home/passwd/"+ u.get(0).getId() + "?" + "updatepwd=" + MD5UTIL.MD5(activeCode) + "</a>";
					SendmailResetThreadPool sendmail= SendmailResetThreadPool.getInstance(u.get(0).getUsername(),u.get(0).getMailAddress(),linkAddress);
					Thread t1=new Thread(sendmail);
					t1.start();
					logger.info("激活邮件:"+"["+u.get(0).getUsername()+","+u.get(0).getMailAddress()+"]");
					lr.setResetSta(1);
					lr.setRetReason(0);
				}
			}
			String checkActive = lr.Return();
			if (out != null){
				out.write(checkActive);
				out.close();
			}
		} catch (Exception ex) {
			logger.warn(ex.toString(), ex);
			res.setStatus(500);
			if (out != null)
				out.close();
		}
	}
	// 手机用户登录
	@RequestMapping(value = "/login")
	public void login(String ownMac, String loginMail, String loginPsw,
			String phoneType, String phoneVer, HttpServletResponse res)
			throws IOException {

		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();
		try {
			// logger.warn("进入不单纯的手机登入");
			loginPsw = Base64.decode64(loginPsw);// 解密密码
			LoginReturn lr = new LoginReturn();
			lr.setRetState(1);
			lr.setIfSuport(1);

			java.util.List<User> u0 = userService.loadByMail(loginMail);
			if (u0.size() == 0) {
				// logger.warn("用户名不存在");
				lr.setLoginSta(0);
				lr.setRetReason(1);
				String checkLogin = lr.Return();
				if (out != null){
					out.write(checkLogin);
					out.close();
				}
				return;
			}
			// 查看密码是否正确
			java.util.List<User> u1 = userService.loadByUsernamePsd(loginMail,
					loginPsw);
			if (u1.size() == 0) {
				// logger.warn("用户密码错误");
				lr.setLoginSta(0);
				lr.setRetReason(2);
				String checkLogin = lr.Return();
				if (out != null){
					out.write(checkLogin);
					out.close();
				}
				return;

			}
			// 查看是否激活
			User u2 = userService.loadByMailPsdAct(loginMail, loginPsw);
			if (u2 == null) {
				if (u0.size() > 1) {
					int tempi = 0;
					for (User u3 : u0) {
						if (u3.getIsActive() == 1)
							tempi++;
					}
					if (tempi == 0) {
						// 无效->有重复的邮箱->全部都未激活
						// logger.warn("无效->有重复的邮箱->全部都未激活");
						lr.setLoginSta(0);
						lr.setRetReason(5);
						String checkLogin = lr.Return();
						if (out != null){
							out.write(checkLogin);
							out.close();
						}
						return;
					} else {
						// 无效->有重复的邮箱->别人激活了
						// logger.warn("无效->有重复的邮箱->别人激活了");
						lr.setLoginSta(0);
						lr.setRetReason(4);
						String checkLogin = lr.Return();
						if (out != null){
							out.write(checkLogin);
							out.close();
						}
						return;
					}
				} else {
					// 无效->没有重复的邮箱
					// logger.warn("无效->没有重复的邮箱");
					lr.setLoginSta(0);
					lr.setRetReason(5);
					String checkLogin = lr.Return();
					if (out != null){
						out.write(checkLogin);
						out.close();
					}
					return;
				}
			} else {
				// 成功登陆
				// logger.warn("成功登陆");
				java.util.List<User> l = userService.loadbyonlinemac(ownMac);
				for (User user : l) {
					user.setOnlineMac(null);
					userService.update(user);
				}

				lr.setLoginSta(1);
				if (u2.getOnlineMac() != null) {
					lr.setRetReason(3);
				} else {
					lr.setRetReason(0);
				}

				// 将手机类型和手机版本存下来
				java.util.List<PhoneUser> p = phoneuserService.listUid(
						u2.getId(), phoneType, phoneVer);
				System.out.println("size:::" + p.size());
				if (p.size() == 0) {

					long tempi = phoneuserService.countUid(u2.getId());
					if (tempi < 3) {
						PhoneUser pu = new PhoneUser();
						pu.setPhoneType(phoneType);
						pu.setPhoneVer(phoneVer);
						pu.setUid(u2.getId());

						phoneuserService.add(pu);
					}
				}
			}

			String checkLogin = "";
			// 检查到用户有新的Router时，返回登录失败状态，并提示用户升级手机app版本
			if (checkNewRouter(u2.getId())) {
				lr.setLoginSta(0);
				lr.setRetReason(6);
				lr.setRetReasonText("请将手机APP升级到最新版本后再使用！");
				checkLogin = lr.ReturnAllReasonText();
			} else {
				u2.setOnlineMac(ownMac);
				userService.update(u2);
				checkLogin = lr.Return();
			}
			if (out != null){
				out.write(checkLogin);
				out.close();
			}
		} catch (Exception ex) {
			logger.warn(ex.toString(), ex);
			res.setStatus(500);
			if (out != null)
				out.close();

		}
	}

	// 手机用户退出
	@RequestMapping(value = "/logout")
	public void logout(String ownMac, HttpServletResponse res)
			throws IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = res.getWriter();

		LogoutReturn lr = new LogoutReturn();
		lr.setRetState(1);

		try {
			User u = userService.loadbymac(ownMac);
			if (u == null) {
				// logger.warn("手机退出成功,此用户登入已经失效");
				lr.setIfSuport(1);
				lr.setRetLogout(1);

			} else {
				lr.setIfSuport(1);
				u.setOnlineMac(null);
				userService.update(u);
				lr.setRetLogout(1);
				// logger.warn("手机用户退出成功");
			}

		} catch (Exception ex) {
			logger.warn("手机退出失败", ex);
			lr.setRetLogout(0);
		} finally {
			out.write(lr.Return());
			out.flush();
			out.close();
		}

	}

	public String randActiveCode() {
		StringBuffer password = new StringBuffer();
		int ran = -1;
		char c;
		for (int i = 0; i < 6; i++) {
			ran = (int) (Math.random() * 10) + 48;
			c = (char) ran;
			password.append(c);
		}
		return password.toString();
	}
	
	/**
	 * 检查帐号绑定的路由器是否有V2.0以后的版本，有则返回true
	 * 
	 * @param uid
	 * @return
	 */
	public boolean checkNewRouter(long uid) {
		List<Device> devices = deviceService.loadbyuid(uid);
		for (Device device : devices) {
			String ver = device.getDeviceVer();
			String verNumOne = ver.substring(ver.indexOf("V") + 1,
					ver.indexOf("."));
			int numOne = Integer.parseInt(verNumOne);
			if (numOne >= 2) {
				return true;
			}
		}
		return false;
	}

}
