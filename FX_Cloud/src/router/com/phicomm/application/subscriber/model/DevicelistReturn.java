package com.phicomm.application.subscriber.model;

import javax.annotation.Resource;

import com.phicomm.application.subscriber.service.IDeviceService;


public class DevicelistReturn {
	private IDeviceService deviceService;
	
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}


	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	 
	public int retState;
	public int ifSuport;
	public int online; 
//	private byte[] ip;
	
	public String Setit(java.util.List<Device> l){
		String temp="";
		for(Device device : l){
			//byte[] ip = new byte[] { (byte) 172, (byte) 16, (byte) 158, (byte) 256 };
			//this.ip = this.get4Ip(device.getDeviceWanIp());
			if(device.getActiveStatus()!=null && device.getActiveStatus().equals("1"))
				this.online = 1;
			else
				this.online = 0;
			
			if((l.lastIndexOf(device)+1 == l.size() || l.size() == 0) && device.getAdminPassword() != null){
				temp += "{\"otherNm\":\""+device.getNickName()+"\",\"online\":\""+this.online+"\",\"MacAdd\":\""+device.getDeviceMac()+"\",\"devcTyp\":\""+device.getDeviceTyp()+"\",\"devcCntro\":\""+device.getDeviceWanIp()+"\",\"deviceName\":\""+device.getAdminName()+"\",\"devicePsd\":\""+device.getAdminPassword()+"\"},";
			}else{
				temp += "{\"otherNm\":\""+device.getNickName()+"\",\"online\":\""+this.online+"\",\"MacAdd\":\""+device.getDeviceMac()+"\",\"devcTyp\":\""+device.getDeviceTyp()+"\",\"devcCntro\":\""+device.getDeviceWanIp()+"\",\"deviceName\":\""+device.getAdminName()+"\",\"devicePsd\":\""+device.getAdminPassword()+"\"},"+"\n";
			}
		}
		return temp;
	}
	
	public String Return(String temp) {
		return "{\"deviceList\":{\"retState\":\""+this.retState+"\",\"ifSuport\":\""+this.ifSuport+"\",\"ModList\":"+"\n"
				+"["+"\n"
				+temp+"\n"
				+"]"+"\n"
				+"}}";
			
	}



	public int getRetState() {
		return retState;
	}



	public void setRetState(int retState) {
		this.retState = retState;
	}



	public int getIfSuport() {
		return ifSuport;
	}

	public byte[] get4Ip(String ipAdd){
		 	byte[] binIP = new byte[4];
		 	String[] strs = ipAdd.split("\\.");
		 	for(int i=0;i<strs.length;i++){
		 		binIP[i] = (byte) Integer.parseInt(strs[i]);
		 	}
		 	return binIP;
	 	}

	public void setIfSuport(int ifSuport) {
		this.ifSuport = ifSuport;
	}


	public int getOnline() {
		return online;
	}


	public void setOnline(int online) {
		this.online = online;
	}



	
	
	
}
