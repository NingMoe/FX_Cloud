package com.phicomm.application.subscriber.util;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.OutletSoapAccept;

public class OutletUtil {
	public static Outlet osa2Otl(OutletSoapAccept osa) throws NoSuchAlgorithmException{
		Outlet outlet = new Outlet();
		outlet.setMac(osa.getOtlMac());
		if(osa.getSwVer() != null && !"".equals(osa.getSwVer()))
			outlet.setSwVer(osa.getSwVer());
		if(osa.getHwVer() != null && !"".equals(osa.getHwVer()))
			outlet.setHwVer(osa.getHwVer());
		outlet.setCreateTime(new Date());
		outlet.setBindTime(new Date());
		outlet.setAlias(osa.getOtlAlias());
		if(osa.getOtlRemark() != null && !"".equals(osa.getOtlRemark()))
			outlet.setRemark(osa.getOtlRemark());
		if(osa.getOtlStatus() != null && !"".equals(osa.getOtlStatus().toString()))
			outlet.setStatus(osa.getOtlStatus());
		if(osa.getIsLock() != null && !"".equals(osa.getIsLock().toString()))
			outlet.setIsLock(osa.getIsLock());
		if(osa.getOtlPsd() != null && !"".equals(osa.getOtlPsd()))
			outlet.setPsd(MD5UTIL.MD5(osa.getOtlPsd()));
		outlet.setInitImei(osa.getPhoImei());
		
		return outlet;
	}
	
	
	
}
