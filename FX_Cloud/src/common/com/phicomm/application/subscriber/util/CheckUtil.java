package com.phicomm.application.subscriber.util;

import com.phicomm.application.subscriber.model.OutletSoapAccept;

public class CheckUtil {
	public static boolean CheckNumber(String num){
		if(!num.matches("[0-9]*")) { 
			return false; 
		} 
		return true;
	}
	
	public static boolean CheckMac(String mac){
		if(!mac.matches("[0-9|A-Z|a-z|:]{17}")) { 
			return false; 
		} 
		return true;
	}
	
	public static boolean Check12Mac(String mac){
		if(!mac.matches("[0-9|A-Z|a-z]{12}")) { 
			return false; 
		} 
		return true;
	}
	
	public static StringBuilder check4AddOutlet(OutletSoapAccept osa){
		StringBuilder msg = new StringBuilder();
		if(osa.getPhoImei() == null || "".equals(osa.getPhoImei()))
			msg.append("初始化插座的手机串号不能为空");
		if(osa.getOtlAlias() == null || "".equals(osa.getOtlAlias()))
			msg.append("插座别名不能为空");
		if(osa.getOtlStatus() != null && (osa.getOtlStatus() > 2 || osa.getOtlStatus() < 0))
			msg.append("插座状态标示只能0,1,2");
		if(osa.getIsLock() != null && (osa.getIsLock() != 0 && osa.getIsLock() != 1))
			msg.append("插座加密标示只能0或1");
		if((osa.getOtlPsd() != null && !"".equals(osa.getOtlPsd()) && osa.getIsLock() == 0) || ((osa.getOtlPsd() == null || "".equals(osa.getOtlPsd())) && osa.getIsLock() == 1))
			msg.append("插座加密标示与实际不符");
		return msg;
	}
	
	public static StringBuilder check4ReportPower(OutletSoapAccept osa){
		StringBuilder msg = new StringBuilder();
		if(osa.getOtlAmps() == null || "".equals(osa.getOtlAmps().toString()))
			msg.append("上报功率时,电流不能为空");
		if(osa.getOtlVolt() == null || "".equals(osa.getOtlVolt().toString()))
			msg.append("上报功率时,电压不能为空");
		if(osa.getOtlKwh() == null || "".equals(osa.getOtlKwh().toString()))
			msg.append("上报功率时,功率不能为空");
		if(osa.getReportDate() == null || "".equals(osa.getReportDate()))
			msg.append("上报功率时,上报时间不能为空");
		return msg;
	}
	
	public static StringBuilder check4ValueChange(OutletSoapAccept osa){
		StringBuilder msg = new StringBuilder();
		if(osa.getIsLock() != null && (osa.getIsLock() != 0 && osa.getIsLock() != 1))
			msg.append("插座加密标示只能0或1");
		if((osa.getOtlPsd() != null && !"".equals(osa.getOtlPsd()) && osa.getIsLock() == 0) || ((osa.getOtlPsd() == null || "".equals(osa.getOtlPsd())) && osa.getIsLock() == 1))
			msg.append("插座加密标示与实际不符");
		return msg;
	}
	
}
