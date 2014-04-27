package com.phicomm.application.subscriber.dao;

import java.util.List;

import com.phicomm.application.subscriber.model.Mac;
public interface IMacDao {
	public void add(Mac mac);
	public void update(Mac mac);
	public void delete(long id);
	public Mac load(long id);
	public Mac loadByMac(String mac);
	public List<Mac> list();

}
