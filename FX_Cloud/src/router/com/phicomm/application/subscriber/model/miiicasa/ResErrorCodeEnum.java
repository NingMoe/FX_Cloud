package com.phicomm.application.subscriber.model.miiicasa;

public enum ResErrorCodeEnum {

	Unknown(-1, "Unknown"),
	NoError(0, "NoError"),
	NotModified(300, "Not Modified."),
	InvalidInput(400, "Invalid input."),
	LackOfParam(401, "Lack of Param."),
	SignFailed(402, "Sign is failed."),
	ServerException(500, "Server exception. Register user data is fail."),
	DataError(501, "Data error. This email has been used."),

	//
	;

	private int value;
	private String display;

	private ResErrorCodeEnum(int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int getValue() {
		return value;
	}

	public String getDisplay() {
		return display;
	}

	public static ResErrorCodeEnum getByValue(int value) {
		ResErrorCodeEnum[] enums = ResErrorCodeEnum.values();
		for (ResErrorCodeEnum each : enums) {
			if (each.value == value) {
				return each;
			}
		}
		return ResErrorCodeEnum.Unknown;
	}

	public static ResErrorCodeEnum getByValue(String value) {
		ResErrorCodeEnum[] enums = ResErrorCodeEnum.values();
		for (ResErrorCodeEnum each : enums) {
			if (String.valueOf(each.value).equals(value)) {
				return each;
			}
		}
		return ResErrorCodeEnum.Unknown;
	}

	public static ResErrorCodeEnum getByDisplay(String display) {
		ResErrorCodeEnum[] enums = ResErrorCodeEnum.values();
		for (ResErrorCodeEnum each : enums) {
			if (each.display.equalsIgnoreCase(display)) {
				return each;
			}
		}
		return ResErrorCodeEnum.Unknown;
	}

}
