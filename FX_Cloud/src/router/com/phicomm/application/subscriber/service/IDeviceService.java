package com.phicomm.application.subscriber.service;

import java.util.Date;
import java.util.List;

import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.Pager;

public interface IDeviceService {
	public void add(Device device);
	public void update(Device device);
	public void delete(long id);
	public Device load(long id);
	public List<Device> loadbyuid(long uid);
	public List<Device> list();
	public Pager<Device> find();
	public Pager<Device> search(String name,String value);
	public Device loadbymac(String mac);
	public long loadByBiggerThanWanIp(String wanIp,Date deviceTime);
}
