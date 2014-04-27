package com.phicomm.application.subscriber.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 插座表
 * @author boke
 *
 */
@Entity
@Table(name="cloud_outlet")
public class Outlet {
	/**
	 * 自增主键
	 */
	private long id;
	/**
	 * 插座mac地址，unique
	 */
	private String mac;
	/**
	 * 插座硬件版本
	 */
	private String hwVer;
	/**
	 * 插座软件版
	 */
	private String swVer;
	/**
	 * 插座别名
	 */
	private String alias;
	/**
	 * 插座备注
	 */
	private String remark;
	/**
	 * 插座图标
	 */
	private String icon;
	/**
	 * 插座状态
	 * 0：位置
	 * 1：断开
	 * 2：闭合
	 */
	private byte status;
	/**
	 * 插座是否有密码
	 * 0：位置
	 * 1：未加密
	 * 2：已加密
	 */
	private byte isLock;
	/**
	 * 插座的sn号
	 */
	private String sn;
	/**
	 * 插座密码
	 */
	private String psd;
	/**
	 * 插座的注册时间
	 */
	private Date createTime;
	/**
	 * 账号与插座绑定时间
	 */
	private Date bindTime;
	/**
	 * 初始化插座的IMEI
	 */
	private String InitImei;
	/**
	 * 最大支持用户数
	 */
	private int maxUsers;
	/**
	 * 外键
	 */
	private User user;
	/**
	 * 类型：
	 * 1：插座
	 * 2：带感应的插座
	 */
	private String type;
	/**
	 * 测试时候用的 ip:port
	 */
	private String ipAddr;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="otl_mac",nullable=false,length=20,unique=true)
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	@Index(name="ix_hwver")
	@Column(name="hw_ver",length=50)
	public String getHwVer() {
		return hwVer;
	}
	public void setHwVer(String hwVer) {
		this.hwVer = hwVer;
	}
	
	@Index(name="ix_swver")
	@Column(name="sw_ver",length=50)
	public String getSwVer() {
		return swVer;
	}
	public void setSwVer(String swVer) {
		this.swVer = swVer;
	}
	
	@Column(name="otl_alias")
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@Column(name="otl_remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="otl_icon")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Index(name="ix_stat")
	@Column(name="otl_status",nullable=false,columnDefinition="tinyint default 1")
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	@Column(name="otl_is_lock",nullable=false,columnDefinition="tinyint default 1")
	public byte getIsLock() {
		return isLock;
	}
	public void setIsLock(byte isLock) {
		this.isLock = isLock;
	}
	
	@Column(name="otl_sn",length=100)
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@Column(name="otl_psd",length=100)
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="bind_time")
	public Date getBindTime() {
		return bindTime;
	}
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usr_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Outlet() {
	}
	@Column(name="init_imei")
	public String getInitImei() {
		return InitImei;
	}
	public void setInitImei(String initImei) {
		InitImei = initImei;
	}
	@Column(name="max_users")
	public int getMaxUsers() {
		return maxUsers;
	}
	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}
	@Column(name="otl_type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="otl_ip")
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
