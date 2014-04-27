package com.phicomm.application.subscriber.service.miiicasa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;



import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.model.MD5UTIL;
import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.miiicasa.MiiicasaResponse;
import com.phicomm.application.subscriber.util.FormatUtil;
import com.phicomm.application.subscriber.util.HttpSendUtil;
import com.phicomm.application.subscriber.util.URLBuilder;

@Service("miiicasaService")
public class MiiicasaService implements IMiiicasaService {

	//md5("pemco.phicomm.cn")
	private static final String SHARED_PRIVATE_KEY = "3041bdb7aecbe7819d902bad93a852f2";
	private static final String host = "api.pemco.phicomm.cn";

	@Override
	public MiiicasaResponse invokeUserRegisterAPI(User user) throws Exception {

		String url = "http://" + host + "/migrate/signup";
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("username", user.getUsername());
		signMap.put("phone", user.getPhone());
		signMap.put("mailAddress", user.getMailAddress());
		String signStr = "";

		signStr = buildSign(signMap);

		final Map<String, Object> params = new HashMap<String, Object>();

		params.put("id", user.getId()); // ?
		params.put("activeCode", user.getActiveCode());
		params.put("createTime", FormatUtil.dateFormat(user.getCreateTime())); // ?
		params.put("isActive", user.getIsActive());
		params.put("mailAddress", user.getMailAddress());

		params.put("onlineMac", user.getOnlineMac());
		params.put("password", encryptPassword(user.getPassword())); // ?
		params.put("phone", user.getPhone());
		params.put("zipCode", user.getZipCode());
		params.put("province", user.getProvince());

		params.put("username", user.getUsername());
		params.put("sign", signStr); // ?
		//System.out.println(params);
		String res = HttpSendUtil.sendPost(url, params);
		
		MiiicasaResponse miiicasaResp = new MiiicasaResponse();
		miiicasaResp.parse(res);
		return miiicasaResp;

	}

	@Override
	public MiiicasaResponse invokeUserStatusSyncAPI(User user) throws Exception {
		String url = "http://" + host + "/migrate/update_status";
		
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("mailAddress", user.getMailAddress());
		signMap.put("activeCode", user.getActiveCode());
		signMap.put("isActive", user.getIsActive());
		String signStr = "";

		signStr = buildSign(signMap);

		final Map<String, Object> params = new HashMap<String, Object>();

		params.put("activeCode", user.getActiveCode());
		params.put("isActive", user.getIsActive());
		params.put("mailAddress", user.getMailAddress());
		params.put("sign", signStr); // ?
		//System.out.println(params);
		String res = HttpSendUtil.sendPost(url, params);
		
		MiiicasaResponse miiicasaResp = new MiiicasaResponse();
		miiicasaResp.parse(res);
		return miiicasaResp;
	}

	/**
	 * 生成sign值
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 * 	// <?php
		// $data["username"] = "tml";
		// $data["phone"] = "1234567";
		// $data["mailAddress"] = "milo_lo@miiicasa.com";
		// ksort($data);
		// $encode_string = http_build_query($data);
		// $ts = time();
		// $private_key = "pre shared key";
		// $md5hash = md5($encode_string . $ts . $private_key);
		// $sign = $ts . "." . $md5hash;
		// echo $sign. "\n";
		// ?>

	 */
	private static String buildSign(Map<String, Object> params)
			throws Exception {

		final TreeMap<String, Object> sortedMap = new TreeMap<String, Object>();
		sortedMap.putAll(params);
		String encodedParams = URLBuilder.httpBuildQuery(sortedMap, null);
		long time = new Date().getTime() / 1000;
		String private_key = SHARED_PRIVATE_KEY;
		String toMd5Str = encodedParams + time + private_key;
		return String.valueOf(time + "." + MD5UTIL.MD5(toMd5Str));

	}

	/**
	 * 用户密码加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		char[] pass = password.toCharArray();
		int total = 0;
		for (char c : pass) {
			total += (int) c;
		}
		String salt = String.valueOf(total);
		return MD5UTIL.MD5(password + SHARED_PRIVATE_KEY + salt);
	}

}
