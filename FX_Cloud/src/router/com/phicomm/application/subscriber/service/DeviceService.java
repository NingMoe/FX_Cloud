package com.phicomm.application.subscriber.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IDeviceDao;
import com.phicomm.application.subscriber.model.Device;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.CloudException;

@Service("deviceService")
public class DeviceService implements IDeviceService {

	private IDeviceDao deviceDao;
	
	
	
	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	@Resource
	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	public void add(Device device) {
		Device u = deviceDao.loadByMac(device.getDeviceMac());
		if(u!=null) throw new CloudException("此设备已经被注册！");
		deviceDao.add(device);
	}

	@Override
	public void update(Device device) {
		deviceDao.update(device);
	}

	@Override
	public void delete(long id) {
		deviceDao.delete(id);
	}

	@Override
	public Device load(long id) {
		return deviceDao.load(id);
	}

	@Override
	public List<Device> list() {
		return deviceDao.list();
	}
	@Override
	public long loadByBiggerThanWanIp(String wanIp,Date deviceTime){
		return deviceDao.loadByBiggerThanWanIp(wanIp,deviceTime);
	}
	@Override
	public Pager<Device> find() {
		return deviceDao.find();
	}
	
	@Override
	public Pager<Device> search(String name,String value) {
		return deviceDao.search( name, value);
	}



	@Override
	public List<Device> loadbyuid(long uid) {
		return deviceDao.loadByuid(uid);
		
	}
	
	@Override
	public Device loadbymac(String mac) {
		return deviceDao.loadByMac(mac);
		
	}
}
