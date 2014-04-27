package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.model.Mac;

public interface IMacService {
	public void add(Mac mac);
	public void update(Mac mac);
	public void delete(long id);
	public Mac load(long id);
	public Mac loadByMac(String mac);
	public List<Mac> list();

}
