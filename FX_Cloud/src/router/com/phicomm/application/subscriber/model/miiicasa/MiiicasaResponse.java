package com.phicomm.application.subscriber.model.miiicasa;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.phicomm.application.subscriber.util.JsonUtil;

public class MiiicasaResponse {

	private ResStatusEnum status = ResStatusEnum.Unknown;

	private ResErrorCodeEnum errno = ResErrorCodeEnum.NoError;

	private String errmsg = "";

	private final Map<String, Object> valueMap = new HashMap<String, Object>();

	public MiiicasaResponse() {

	}

	public synchronized ResStatusEnum getStatus() {
		return status;
	}

	public synchronized void setStatus(ResStatusEnum status) {
		this.status = status;
	}

	public synchronized ResErrorCodeEnum getErrno() {
		return errno;
	}

	public synchronized void setErrno(ResErrorCodeEnum errno) {
		this.errno = errno;
	}

	public synchronized String getErrmsg() {
		return errmsg;
	}

	public synchronized void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public synchronized void putValue(String key, Object value) {
		valueMap.put(key, value);
	}

	public synchronized void removeValue(String key) {
		valueMap.remove(key);
	}

	public synchronized Object getValue(String key) {
		return valueMap.get(key);
	}

	public synchronized String toJson() {
		try {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("status", status.getValue());
			valueMap.put("errno", errno.getValue());
			valueMap.put("errmsg", errmsg);
			valueMap.putAll(this.valueMap);
			return JsonUtil.getMapper().writeValueAsString(valueMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}

	}

	public synchronized void parse(String json) {
		errno = ResErrorCodeEnum.NoError;
		errmsg = "";
		valueMap.clear();

		try {
			JsonFactory f = new JsonFactory();
			JsonParser jp = f.createJsonParser(json);
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT && jp.nextToken() != null) {
				String fieldname = jp.getCurrentName();
				jp.nextToken();
				if ("errno".equals(fieldname)) {
					this.errno = ResErrorCodeEnum.getByValue(jp.getText());
				} else if ("status".equals(fieldname)) {
					this.status = ResStatusEnum.getByValue(jp.getText());
				} else if ("errmsg".equals(fieldname)) {
					this.errmsg = jp.getText();
				} else {
					valueMap.put(fieldname, jp.getText());
				}
			}
			jp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized String toString() {
		return toJson();
	}

	public static void main(String[] args) throws Exception {
		MiiicasaResponse res1 = new MiiicasaResponse();
		res1.putValue("uid", "00967658d31f5190d97a0c956311fbe6");
		String json = res1.toJson();
		System.out.println(json);

		String json2 = "{\"uid\":\"b229393920a1103101032770102d\",\"errno\":500,\"status\":\"ok\",\"errmsg\":\"changed to another\"}";
		res1.parse(json2);
		System.out.println(res1.getStatus());
	}

}
