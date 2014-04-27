package com.phicomm.application.subscriber.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.ICellphoneDao;
import com.phicomm.application.subscriber.dao.IOutletDao;
import com.phicomm.application.subscriber.dao.IOutletReportDao;
import com.phicomm.application.subscriber.dao.IUserDao;
import com.phicomm.application.subscriber.model.AppReturn;
import com.phicomm.application.subscriber.util.AppPowerHDM;
import com.phicomm.application.subscriber.util.Base64;
import com.phicomm.application.subscriber.util.HttpSendUtil;
import com.phicomm.application.subscriber.util.STUNUtil;
import com.phicomm.application.subscriber.util.SendMailUtil;
import com.phicomm.application.subscriber.model.Cellphone;
import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.PowerHDMReturn;
import com.phicomm.application.subscriber.model.PowerReturn;
import com.phicomm.application.subscriber.model.RecordDay;
import com.phicomm.application.subscriber.model.RecordHoursDto;
import com.phicomm.application.subscriber.model.RecordMonth;
import com.phicomm.application.subscriber.model.RecordWeek;
import com.phicomm.application.subscriber.model.RecordYear;
import com.phicomm.application.subscriber.model.SendMail;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.util.SecurityUtil;

@Service("appService")
public class AppService implements IAppService {
	private IUserDao userDao;
	private ICellphoneDao cellphoneDao;
	private IOutletDao outletDao;
	private IOutletReportDao outletReportDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public ICellphoneDao getCellphoneDao() {
		return cellphoneDao;
	}
	@Resource
	public void setCellphoneDao(ICellphoneDao cellphoneDao) {
		this.cellphoneDao = cellphoneDao;
	}
	public IOutletDao getOutletDao() {
		return outletDao;
	}
	@Resource
	public void setOutletDao(IOutletDao outletDao) {
		this.outletDao = outletDao;
	}
	public IOutletReportDao getOutletReportDao() {
		return outletReportDao;
	}
	@Resource
	public void setOutletReportDao(IOutletReportDao outletReportDao) {
		this.outletReportDao = outletReportDao;
	}
	@Override
	public AppReturn appLogin(String userMail, String password,String phoneMac, String phoneTyp, String phoneVer, String phoneIMEI, String appVer) throws Exception{
		//1.检查用户是否存在
		AppReturn ar = new AppReturn();
		List<User> u0 = userDao.loadByMail(userMail);
		if (u0.size() == 0) {
			//用户不存在
			ar.setRsEc("0","8001");
			return ar;
		}
		//2.密码检查
		password = Base64.decode64(password);
		System.out.println("jilei++++++++++++"+password);
		List<User> u1 = userDao.loadByUsernamePsd(userMail,password);
		if (u1.size() == 0) {
			//用户密码错误
			ar.setRsEc("0","8002");
			return ar;
		}
		//3.检查是否已激活
		User u2 = userDao.loadByMailPsdAct(userMail, password);
		if (u2 == null) {
			//未激活
			ar.setRsEc("0","8006");
			return ar;
		} else {
			//记录手机信息
			addCellphone(phoneMac, phoneTyp, phoneVer, phoneIMEI, appVer, null);
			u2.setOnlineMac(phoneMac);
			userDao.update(u2);
			
			ar.setRsEc("1","0");
			return ar;
		}
	}

	@Override
	public AppReturn appRegister(String phoneMac, String userMail,String password, String username, String province, String phoneNo,
			String zipcode, String phoneTyp, String phoneVer, String phoneIMEI,String appVer) throws Exception {
		//检查是否已注册
		AppReturn ar = new AppReturn();
		List<User> u = userDao.loadByMail(userMail);
		if (u.size() > 0) {
			//已注册
			ar.setRsEc("0","8007");
			return ar;
		} else {
			//未注册，添加用户
			User user = new User();
			
			user.setMailAddress(userMail);
			
			user.setPassword(MD5UTIL.MD5(Base64.decode64(password)));
			user.setUsername(username);
			user.setProvince(province);
			user.setCreateTime(new Date());
			user.setIsActive(0);
			user.setZipCode(zipcode);
			user.setPhone(phoneNo);
			userDao.add(user);
			SendMailUtil.sendNoActive(user);
			//记录手机信息
			addCellphone(phoneMac, phoneTyp, phoneVer, phoneIMEI, appVer, phoneNo);
			ar.setRsEc("1", "0");
			return ar;
		}
	}
	
	@Override
	public String appGetAllDevice(String phoneMac, String phoneIMEI, String userMail) throws Exception {
		AppReturn ar = new AppReturn();
		User u = userDao.searchByMailPsdAct(userMail);
		List<Outlet> ol = outletDao.listByUsrId(u.getId());
		ar.setRsEc("1","0");
		return ar.getAllDevReturn(ol);
	}
	
	private void addCellphone(String phoneMac, String phoneTyp, String phoneVer, String phoneIMEI, String appVer,String phoneNo){
		Cellphone cp = cellphoneDao.loadByImei(phoneIMEI);
		if(cp == null){
			cp = new Cellphone();
			cp.setImei(phoneIMEI);
			cp.setVer(phoneVer);
			cp.setType(phoneTyp);
			if(phoneNo!=null) cp.setNo(phoneNo);
			cp.setMac(phoneMac);
			cellphoneDao.add(cp);
		}else {
			cp.setVer(phoneVer);
			cp.setType(phoneTyp);
			if(phoneNo!=null)  cp.setNo(phoneNo);
			cp.setMac(phoneMac);
			cellphoneDao.update(cp);
		}
	}
	@Override
	public AppReturn appBindDev(String userMail, String phoneMac,String phoneIMEI, String bindNum, String[] devPID, String[] devMac)
			throws Exception {
		AppReturn ar = new AppReturn();
		User user = userDao.searchByMailPsdAct(userMail);
		Cellphone cellphone = cellphoneDao.loadByImei(phoneIMEI);
		Outlet outlet = null;
		for(String dm:devMac){
			outlet = outletDao.loadByMac(dm);
			if(outlet == null) throw new CloudException("设备不存在");
			outlet.setUser(user);
			outletDao.addOutletCellphone(outlet, cellphone);
		}
		ar.setRsEc("1","0");
		return ar;
	}
	@Override
	public String appBindPhone(String phoneMac, String phoneIMEI, String devMac) throws Exception {
		Outlet ol = outletDao.loadByMac(devMac);
		if(ol == null) throw new CloudException("设备不存在");
		AppReturn ar = new AppReturn();
		List<Cellphone> lcp = cellphoneDao.loadByOutlet(ol.getId());
		ar.setRsEc("1","0");
		return ar.getBindPhoneReturn(lcp);
	}
	@SuppressWarnings("unused")
	@Override
	public String appPower(String phoneMac, String phoneIMEI, String devMac,AppPowerHDM appHDM) throws Exception {
		Outlet outlet = outletDao.loadByMac(devMac);
		
		if(outlet == null) {
			throw new CloudException("设备不存在");
		}
		if(!devCheck(outlet.getId(),phoneIMEI)) {
			throw new CloudException("设备未与帐号绑定");
		}
		//计算当天至请求时刻的功耗
		double tdKwh = calTdKwh(outlet);
		//计算本周至请求时刻的功耗
		double twKwh = calTwKwh(outlet,tdKwh);
		//计算本月至请求时刻的功耗
		double tmKwh = calTmKwh(outlet,tdKwh);
		//计算本年至请求时刻的功耗
		double tyKwh = calTyKwh(outlet,tmKwh);
		
		//读取前几周的功耗
		List<RecordWeek> rws = outletReportDao.loadWeeksKwh(outlet.getId(), 3);
		//读取前几个月的功耗
		List<RecordMonth> rms = outletReportDao.loadMonthsKwh(outlet.getId(), 1);
		//读取前几年的功耗
		List<RecordYear> rys = outletReportDao.loadYearsKwh(outlet.getId(), 3);
		//读取前几天的功耗
		List<RecordDay> rds = outletReportDao.loadDaysKwh(outlet.getId(), 6);
		
		StringBuilder sb = new StringBuilder();
		PowerHDMReturn phr = new PowerHDMReturn();
		switch (appHDM) {
		case Default:
			PowerReturn pReturn = new PowerReturn();
			pReturn.setRsEc("1", "0");
			return pReturn.getPowerReturn(outlet, tdKwh, tmKwh, rms, rds);
		case Hours:
			//读取当天至请求时刻每小时的功耗
			List<RecordHoursDto> rhs = outletReportDao.loadToHoursKwh(outlet.getId());
			sb.delete(0, sb.length());
			for(int i=0;i<rhs.size();i++){
				if(i == rhs.size()-1){
					sb.append(rhs.get(i).getKwh());
				}
				else {
					sb.append(rhs.get(i).getKwh()+"_");
				}
			}
			phr.setRsEc("1", "0");
			phr.setHourPow(sb.toString());
			return phr.getPowerHoursReturn(outlet);
		case Days:
			sb.delete(0, sb.length());
			for(int i=0;i<rds.size();i++){
				if(i == rds.size()-1){
					sb.append(rds.get(i).getNowKwh());
				}
				else {
					sb.append(rds.get(i).getNowKwh()+"_");
				}
			}
			phr.setRsEc("1", "0");
			phr.setDayPow(sb.toString());
			return phr.getPowerDaysReturn(outlet);
		case Month:
			//获取当年每月的消耗
			List<RecordMonth> rMonths = outletReportDao.loadToMonthKwh(outlet.getId());
			sb.delete(0, sb.length());
			for(int i=0;i<rMonths.size();i++){
				if(i == rMonths.size()-1){
					sb.append(rMonths.get(i).getNowKwh());
				}
				else {
					sb.append(rMonths.get(i).getNowKwh()+"_");
				}
			}
			phr.setRsEc("1", "0");
			phr.setMonPow(sb.toString());
			return phr.getPowerMonsReturn(outlet);
		default:
			phr.setRsEc("0", "8009");
			return phr.getPowerReturn();
		}
	}
	private boolean devCheck(long id, String phoneIMEI) {
		List<Cellphone> cellphones = cellphoneDao.loadByOutlet(id);
		if(cellphones==null){
			return false;
		}
		for(Cellphone cp:cellphones){
			if(cp.getImei().equals(phoneIMEI)){
				return true;
			}
		}
		return false;
	}
	//1.计算当天至请求时刻的功耗
	private double calTdKwh(Outlet outlet){
		List<OutletReport> outletReports = outletReportDao.loadByOtlIdToDy(outlet.getId());
		double tdKwh = 0;
		for(OutletReport olr:outletReports){
			tdKwh = tdKwh + olr.getKwh();
		}
		return tdKwh;
	}
	//2.计算本周至请求时刻的功耗
	private double calTwKwh(Outlet outlet,double tdKwh){
		List<RecordDay> rds = outletReportDao.loadTWKwh(outletReportDao.loadWeekFDByOtlId(outlet.getId()), outlet.getId());
		double twKwh = 0;
		for(RecordDay rd:rds){
			twKwh = twKwh + rd.getNowKwh();
		}
		return twKwh+tdKwh;
	}
	//3.计算本月至请求时刻的功耗
	private double calTmKwh(Outlet outlet,double tdKwh){
		List<RecordDay> rds = outletReportDao.loadTMKwh(outlet.getId());
		double tmKwh = 0;
		for(RecordDay rd:rds){
			tmKwh = tmKwh + rd.getNowKwh();
		}
		return tmKwh+tdKwh;
	}
	//4.计算本年至请求时刻的功耗
	private double calTyKwh(Outlet outlet,double tmKwh){
		List<RecordMonth> rms = outletReportDao.loadTYKwh(outlet.getId());
		double tyKwh = 0;
		for(RecordMonth rm:rms){
			tyKwh = tyKwh + rm.getNowKwh();
		}
		return tyKwh+tmKwh;
	}
	@Override
	public String appOutletState(String phoneIMEI,String devMac, String devAction,String params) throws Exception {
		Outlet outlet = outletDao.loadByMac(devMac);
		if(outlet == null) {
			throw new CloudException("设备不存在");
		}
		if(!devCheck(outlet.getId(),phoneIMEI)) {
			throw new CloudException("设备未与帐号绑定");
		}
		//获取和配置暂时按相同逻辑处理
		if("0".equals(devAction)){
			//获取
			return appHttpOutletState(outlet, params);
		} else if("1".equals(devAction)){
			//配置
			return appHttpOutletState(outlet, params);
		}
		return null;
	}
	private String appHttpOutletState(Outlet outlet,String params) throws Exception {
		String url = null;
		String reponse = null;
		if(STUNUtil.stunCheck){
			//有STUN
			url = STUNUtil.STUN_IP;
		}
		else {
			//没有STUN
			url = outlet.getIpAddr();
		}
		reponse = HttpSendUtil.sendPost(url, params);
		return reponse;
	}
	@Override
	public AppReturn appLogout(String phoneMac) {
		AppReturn ar = new AppReturn();
		User user = userDao.loadByMac(phoneMac);
		if(user!=null){
			user.setOnlineMac(null);
			userDao.update(user);
		}
		ar.setRsEc("1", "0");
		return ar;
	}
	@Override
	public List<RecordHoursDto> loadHoursKwhByOtlId(long otl_id, int hours) {
		return outletReportDao.loadHoursKwh(otl_id, hours);
	}
}
