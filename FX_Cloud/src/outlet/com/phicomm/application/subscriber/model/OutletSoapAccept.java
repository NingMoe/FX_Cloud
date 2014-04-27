package com.phicomm.application.subscriber.model;

import java.util.List;

/**
 * outlet发送的内容先存储于此
 * @author boke
 *
 */
public class OutletSoapAccept {
	/**
	 * 插座的MAC地址，16进制数，大写，冒号间隔
	 */
	private String otlMac;
	/**
	 * 插座型号
	 */
	private String otlVer;
	/**
	 * 插座识别码
	 */
	private String otlPid;
	/**
	 * 硬件版本
	 */
	private String hwVer;
	/**
	 * 软件版本
	 */
	private String swVer;
	/**
	 * 设备别名
	 */
	private String otlAlias;
	/**
	 * 设备备注
	 */
	private String otlRemark;
	/**
	 * 插座状态
	 * 0：未知
	 * 1：断开
	 * 2：闭合
	 */
	private Byte otlStatus;
	/**
	 * 加密表示
	 * 0：未加密
	 * 1：已加密
	 */
	private Byte isLock;
	/**
	 * 插座密码
	 */
	private String otlPsd;
	/**
	 * 初始化插座手机的串号
	 */
	private String phoImei;
	/**
	 * 电流
	 */
	private Double otlAmps;
	/**
	 * 电压
	 */
	private Double otlVolt;
	/**
	 * 功率
	 */
	private Double otlKwh;
	private Double otlTotalKwh;
	/**
	 * 时间点
	 */
	private String reportDate;
	/**
	 * 插座计量器重启的时间
	 */
	private String clearTime;
	/**
	 * 定时的数量
	 */
	private int ruleNo;
	/**
	 * 规则
	 */
	private List<String> rules;
	/**
	 * 最大支持绑定用户数
	 */
	private int maxUsers;
	private List<String> EventCode;//路由器接口区分
	
	


	public String getOtlMac() {
		return otlMac;
	}




	public int getMaxUsers() {
		return maxUsers;
	}




	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}




	public void setOtlMac(String otlMac) {
		this.otlMac = otlMac;
	}




	public String getOtlVer() {
		return otlVer;
	}




	public Double getOtlTotalKwh() {
		return otlTotalKwh;
	}




	public void setOtlTotalKwh(Double otlTotalKwh) {
		this.otlTotalKwh = otlTotalKwh;
	}




	public void setOtlVer(String otlVer) {
		this.otlVer = otlVer;
	}




	public String getOtlPid() {
		return otlPid;
	}




	public void setOtlPid(String otlPid) {
		this.otlPid = otlPid;
	}




	public String getClearTime() {
		return clearTime;
	}




	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}




	public String getHwVer() {
		return hwVer;
	}




	public void setHwVer(String hwVer) {
		this.hwVer = hwVer;
	}




	public String getSwVer() {
		return swVer;
	}




	public void setSwVer(String swVer) {
		this.swVer = swVer;
	}




	public String getOtlAlias() {
		return otlAlias;
	}




	public void setOtlAlias(String otlAlias) {
		this.otlAlias = otlAlias;
	}




	public String getOtlRemark() {
		return otlRemark;
	}




	public void setOtlRemark(String otlRemark) {
		this.otlRemark = otlRemark;
	}




	public Byte getOtlStatus() {
		return otlStatus;
	}




	public void setOtlStatus(Byte otlStatus) {
		this.otlStatus = otlStatus;
	}




	public Byte getIsLock() {
		return isLock;
	}




	public void setIsLock(Byte isLock) {
		this.isLock = isLock;
	}




	public String getOtlPsd() {
		return otlPsd;
	}




	public void setOtlPsd(String otlPsd) {
		this.otlPsd = otlPsd;
	}




	public String getPhoImei() {
		return phoImei;
	}




	public void setPhoImei(String phoImei) {
		this.phoImei = phoImei;
	}




	public Double getOtlAmps() {
		return otlAmps;
	}




	public void setOtlAmps(Double otlAmps) {
		this.otlAmps = otlAmps;
	}




	public Double getOtlVolt() {
		return otlVolt;
	}




	public void setOtlVolt(Double otlVolt) {
		this.otlVolt = otlVolt;
	}




	public Double getOtlKwh() {
		return otlKwh;
	}




	public void setOtlKwh(Double otlKwh) {
		this.otlKwh = otlKwh;
	}




	public String getReportDate() {
		return reportDate;
	}




	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}







	public int getRuleNo() {
		return ruleNo;
	}




	public void setRuleNo(int ruleNo) {
		this.ruleNo = ruleNo;
	}


	public List<String> getRules() {
		return rules;
	}




	public void setRules(List<String> rules) {
		this.rules = rules;
	}




	public List<String> getEventCode() {
		return EventCode;
	}




	public void setEventCode(List<String> eventCode) {
		EventCode = eventCode;
	}




	public OutletSoapAccept() {
	}		
}
