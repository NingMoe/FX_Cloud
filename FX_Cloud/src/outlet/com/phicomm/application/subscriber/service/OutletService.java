package com.phicomm.application.subscriber.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.CellphoneDao;
import com.phicomm.application.subscriber.dao.OutletDao;
import com.phicomm.application.subscriber.dao.OutletReportDao;
import com.phicomm.application.subscriber.dao.RuleDao;
import com.phicomm.application.subscriber.model.Cellphone;
import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.OutletSoapAccept;
import com.phicomm.application.subscriber.model.Rule;
import com.phicomm.application.subscriber.model.CloudException;
import com.phicomm.application.subscriber.util.CheckUtil;
import com.phicomm.application.subscriber.util.JsonUtil;
import com.phicomm.application.subscriber.util.OutletUtil;

@Service("outletService")
public class OutletService implements IOutletService {
	private OutletDao outletDao;
	private CellphoneDao cellphoneDao;
	private OutletReportDao outletReportDao;
	private RuleDao ruleDao;
	
	
	public RuleDao getRuleDao() {
		return ruleDao;
	}
	@Inject
	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	public OutletReportDao getOutletReportDao() {
		return outletReportDao;
	}
	@Inject
	public void setOutletReportDao(OutletReportDao outletReportDao) {
		this.outletReportDao = outletReportDao;
	}
	public CellphoneDao getCellphoneDao() {
		return cellphoneDao;
	}
	@Inject
	public void setCellphoneDao(CellphoneDao cellphoneDao) {
		this.cellphoneDao = cellphoneDao;
	}
	public OutletDao getOutletDao() {
		return outletDao;
	}
	@Inject
	public void setOutletDao(OutletDao outletDao) {
		this.outletDao = outletDao;
	}

	@Override
	public void initOutlet(Outlet outlet, String imei,OutletSoapAccept osa) throws NoSuchAlgorithmException {
		//1、检查设备MAC是否已经被注册
		Outlet o = outletDao.loadByMac(outlet.getMac());
		if(o != null){ 
			//如果存在，就是reset了。那么删除绑定的user_id。
			o = existOsa2Otl(o,osa);
			outletDao.update(o);
			//throw new CloudException("要添加的插座MAC已存在");
		}else
			initReport(outlet,imei);
	}

	/**
	 * 专门为插座重置后，又上报的时候，更新信息，并且将绑定的用户设为空
	 * @param outlet
	 * @param osa
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private Outlet existOsa2Otl(Outlet outlet,OutletSoapAccept osa) throws NoSuchAlgorithmException{
		outlet.setUser(null);
		if(osa.getSwVer() != null && !"".equals(osa.getSwVer()))
			outlet.setSwVer(osa.getSwVer());
		if(osa.getHwVer() != null && !"".equals(osa.getHwVer()))
			outlet.setHwVer(osa.getHwVer());
		outlet.setBindTime(new Date());
		outlet.setAlias(osa.getOtlAlias());
		if(osa.getOtlRemark() != null && !"".equals(osa.getOtlRemark()))
			outlet.setRemark(osa.getOtlRemark());
		if(osa.getOtlStatus() != null && !"".equals(osa.getOtlStatus().toString()))
			outlet.setStatus(osa.getOtlStatus());
		if(osa.getIsLock() != null && !"".equals(osa.getIsLock().toString()))
			outlet.setIsLock(osa.getIsLock());
		if(osa.getOtlPsd() != null && !"".equals(osa.getOtlPsd()))
			outlet.setPsd(MD5UTIL.MD5(osa.getOtlPsd()));
		outlet.setInitImei(osa.getPhoImei());
		
		return outlet;
	}
	
	/**
	 * 添加插座和绑定手机，不包括查询MAC
	 * @param outlet
	 * @param imei
	 */
	private void initReport(Outlet outlet, String imei){
		//2、添加设备
		outletDao.add(outlet);
		//3、为初始化插座的手机绑定插座
		Cellphone cp = cellphoneDao.loadByImei(imei);
		if(cp != null){
			//初始化插座的手机已存在，直接绑定
			outletDao.addOutletCellphone(outlet, cp);
		}else{
			//初始化插座的手机不存在，则先添加手机再绑定
			cp = new Cellphone();
			cp.setImei(imei);
			cellphoneDao.add(cp);
			outletDao.addOutletCellphone(outlet, cp);
		}
	}
	
	@Override
	public void reportStatus(byte status,String mac,OutletSoapAccept osa) throws NoSuchAlgorithmException {
		Outlet outlet = outletDao.loadByMac(mac);
		if(outlet == null){
			//上报状态的时候，插座不存在的话直接初始化
			StringBuilder msg = CheckUtil.check4AddOutlet(osa);
			if(msg.length() != 0){
				throw new CloudException("soap包参数有错误");
			}
			outlet = OutletUtil.osa2Otl(osa);
			
			initReport(outlet,osa.getPhoImei());
		}else{
			outlet.setStatus(status);
			outletDao.update(outlet);
		}
	}

	@Override
	public void reportPower(OutletReport outletReport,OutletSoapAccept osa) throws NoSuchAlgorithmException {
		//1、根据MAC找到插座
		Outlet outlet = outletDao.loadByMac(osa.getOtlMac());
		if(outlet == null){
			//上报功率的时候，插座不存在，则添加插座
			StringBuilder msg = CheckUtil.check4AddOutlet(osa);
			if(msg.length() != 0){
				throw new CloudException("soap包参数有错误");
			}
			outlet = OutletUtil.osa2Otl(osa);
			
			initReport(outlet,osa.getPhoImei());
		}else{
			//插座已存在，则上报功率
			outletReport.setOutlet(outlet);
			outletReportDao.add(outletReport);
		}
	}

	@Override
	public void reportRule(OutletSoapAccept osa) throws SQLException {
		//1、找到插座
		Outlet outlet = outletDao.loadByMac(osa.getOtlMac());
		if(outlet == null) throw new CloudException("插座不存在");
		else{
			List<Rule> rs = new ArrayList<Rule>();
			//删除原来的
			ruleDao.deleteByOtl(outlet.getId());
			//获取上报的规则
			for(String rule:osa.getRules()){
				System.out.println(rule);
				Rule r = (Rule)JsonUtil.getInstance().json2obj(rule, Rule.class);
				r.setOutlet(outlet);
				rs.add(r);
			}
			//批量插入
			ruleDao.addBatch(rs);
		}
	}


	@Override
	public void reportOtlChange(OutletSoapAccept osa) throws NoSuchAlgorithmException {
		Outlet outlet = outletDao.loadByMac(osa.getOtlMac());
		if(outlet == null){
			throw new CloudException("要更改设置的插座不存在");
		}else{
			if(osa.getIsLock() != null && !"".equals(osa.getIsLock().toString()))
				outlet.setIsLock(osa.getIsLock());
			if(osa.getOtlPsd() != null && !"".equals(osa.getOtlPsd()))
				outlet.setPsd(MD5UTIL.MD5(osa.getOtlPsd()));
			outlet.setMaxUsers(osa.getMaxUsers());
			if(osa.getOtlRemark() != null && !"".equals(osa.getOtlRemark()))
				outlet.setRemark(osa.getOtlRemark());
			if(osa.getOtlAlias() != null && !"".equals(osa.getOtlAlias()))
				outlet.setAlias(osa.getOtlAlias());
			
			outletDao.update(outlet);
		}
	}
	@Override
	public void add(Outlet outlet) {
		outletDao.add(outlet);
	}
	@Override
	public void addRuleBatch(List<Rule> rules) throws SQLException {
		ruleDao.addBatch(rules);
	}
	
	

}
