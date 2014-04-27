package com.phicomm.application.subscriber.dao;

import java.util.List;

import com.phicomm.application.subscriber.model.Cellphone;
import com.phicomm.application.subscriber.model.Outlet;

public interface IOutletDao {
	/**
	 * 添加插座
	 * @param outlet
	 */
	public void add(Outlet outlet);
	public void addBatch(final List<Outlet> os);
	/**
	 * 根据Id删除插座
	 * @param id 
	 */
	public void delete(long id);
	/**
	 * 更新插座
	 * @param outlet
	 */
	public void update(Outlet outlet);
	/**
	 * 根据id查询插座
	 * @param id
	 * @return
	 */
	public Outlet load(long id);
	/**
	 * 根据MAC查找设备
	 * @param mac
	 * @return
	 */
	public Outlet loadByMac(String mac);
	/**
	 * 列出所有插座
	 * @return
	 */
	public List<Outlet> list();
	/**
	 * 列出对应用户的所有插座
	 * @param usrId
	 * @return
	 */
	public List<Outlet> listByUsrId(long usrId);
	/**
	 * 添加插座和手机关系表
	 * @param outlet
	 * @param cellphone
	 */
	public void addOutletCellphone(Outlet outlet,Cellphone cellphone);
}
