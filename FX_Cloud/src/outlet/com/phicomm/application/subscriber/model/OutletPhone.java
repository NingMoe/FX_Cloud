package com.phicomm.application.subscriber.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 手机和插座的关系表
 * @author boke
 *
 */
@Entity
@Table(name="cloud_otl_pho")
public class OutletPhone {
	private long id;
	private Outlet outlet;
	private Cellphone cellphone;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="otl_id")
	public Outlet getOutlet() {
		return outlet;
	}
	public void setOutlet(Outlet outlet) {
		this.outlet = outlet;
	}
	
	@ManyToOne
	@JoinColumn(name="pho_id")
	public Cellphone getCellphone() {
		return cellphone;
	}
	public void setCellphone(Cellphone cellphone) {
		this.cellphone = cellphone;
	}
}
