package com.phicomm.application.subscriber.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static String dateFormat(Date date){
		final String str = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		return sdf.format(date);
	}

}
