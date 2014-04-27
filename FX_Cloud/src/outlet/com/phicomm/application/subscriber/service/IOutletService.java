package com.phicomm.application.subscriber.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.OutletSoapAccept;
import com.phicomm.application.subscriber.model.Rule;

public interface IOutletService {
	public void add(Outlet outlet);
	public void addRuleBatch(List<Rule> rules) throws SQLException;
	/**
	 * 插座的初始化
	 * @param outlet 插座
	 * @param imei   初始化手机的串号
	 * @throws NoSuchAlgorithmException 
	 */
	public void initOutlet(Outlet outlet,String imei,OutletSoapAccept osa) throws NoSuchAlgorithmException;
	/**
	 * 插座上报开关状态
	 * @param status 开或关
	 * @throws NoSuchAlgorithmException 
	 */
	public void reportStatus(byte status,String mac,OutletSoapAccept osa) throws NoSuchAlgorithmException;
	/**
	 * 插座上报电量功耗
	 * @param outletReport
	 * @throws NoSuchAlgorithmException 
	 */
	public void reportPower(OutletReport outletReport,OutletSoapAccept osa) throws NoSuchAlgorithmException;
	
	/**
	 * 插座上报规则
	 * @param usrId
	 * @param rules
	 * @param otlIds
	 * @throws SQLException 
	 */
	public void reportRule(OutletSoapAccept osa) throws SQLException;
	
	/**
	 * 设备信息变更
	 * @param outlet
	 * @throws NoSuchAlgorithmException 
	 */
	public void reportOtlChange(OutletSoapAccept osa) throws NoSuchAlgorithmException;
	
}
