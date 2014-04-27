package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.model.AppReturn;
import com.phicomm.application.subscriber.model.RecordHoursDto;
import com.phicomm.application.subscriber.util.AppPowerHDM;

public interface IAppService {
	/**
	 * 手机登录
	 * @param userMail
	 * @param password
	 * @param phoneMac
	 * @param phoneTyp
	 * @param phoneVer
	 * @param phoneIMEI
	 * @param appVer
	 * @return
	 * @throws Exception
	 */
	public AppReturn appLogin(String userMail,String password, String phoneMac, String phoneTyp, String phoneVer, String phoneIMEI, String appVer) throws Exception;
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
	 * @return
	 * @throws Exception
	 */
	public AppReturn appRegister(String phoneMac,String userMail,String password,String username,String province,
			String phoneNo,String zipcode,String phoneTyp,String phoneVer,String phoneIMEI, String appVer) throws Exception;
	/**
	 * 获取用户的绑定设备 
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param userMail
	 * @return
	 * @throws Exception
	 */
	public String appGetAllDevice(String phoneMac, String phoneIMEI,String userMail) throws Exception;
	/**
	 * 帐号绑定新设备
	 * @param userMail
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param bindNum
	 * @param devPID
	 * @param devMac
	 * @return
	 * @throws Exception
	 */
	public AppReturn appBindDev(String userMail, String phoneMac,String phoneIMEI, String bindNum, String[] devPID, String[] devMac)throws Exception;
	/**
	 * 获取插座设备绑定的手机
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devMac
	 * @return
	 * @throws Exception
	 */
	public String appBindPhone(String phoneMac, String phoneIMEI,String devMac) throws Exception;
	/**
	 * 设备功率信息的获取(默认，小时，天，月）
	 * @param phoneMac
	 * @param phoneIMEI
	 * @param devMac
	 * @param appHDM (默认，小时，天，月）
	 * @return
	 * @throws Exception
	 */
	public String appPower(String phoneMac, String phoneIMEI, String devMac,AppPowerHDM appHDM) throws Exception;
	/**
	 * app消息转发至插座
	 * @param phoneIMEI
	 * @param devMac
	 * @param devAction
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String appOutletState(String phoneIMEI,String devMac, String devAction,String params) throws Exception;
	/**
	 * 手机退出
	 * @param phoneMac
	 * @return
	 */
	public AppReturn appLogout(String phoneMac);
	/**
	 * 根据otl_id获取每小时的功耗统计
	 * @param otl_id
	 * @param hours
	 * @return
	 */
	public List<RecordHoursDto> loadHoursKwhByOtlId(long otl_id, int hours);

}
