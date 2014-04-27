package com.phicomm.application.subscriber.dao;

import java.util.List;

import com.phicomm.application.subscriber.model.Cellphone;


public interface ICellphoneDao {
	/**
	 * 添加手机
	 * @param outlet
	 */
	public void add(Cellphone cellphone);
	/**
	 * 根据Id删除手机
	 * @param id 
	 */
	public void delete(long id);
	/**
	 * 更新手机
	 * @param outlet
	 */
	public void update(Cellphone cellphone);
	/**
	 * 根据id查询手机
	 * @param id
	 * @return
	 */
	public Cellphone load(long id);
	/**
	 * 根据imei查询手机
	 * @param imei
	 * @return
	 */
	public Cellphone loadByImei(String imei);
	/**
	 * 列出所有手机
	 * @return
	 */
	public List<Cellphone> list();
	/**
	 * 根据插座查找绑定的手机
	 * @param outlet_id
	 * @return
	 */
	public List<Cellphone> loadByOutlet(long outlet_id);
}
