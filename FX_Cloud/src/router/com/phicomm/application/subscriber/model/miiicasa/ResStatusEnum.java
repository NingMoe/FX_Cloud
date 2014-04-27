package com.phicomm.application.subscriber.model.miiicasa;

public enum ResStatusEnum {

	Unknown(-1, "Unknown"), ok(1, "ok"), fail(2, "fail"),

	//
	;

	private int value;
	private String display;

	private ResStatusEnum(int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int getValue() {
		return value;
	}

	public String getDisplay() {
		return display;
	}

	public static ResStatusEnum getByValue(int value) {
		ResStatusEnum[] enums = ResStatusEnum.values();
		for (ResStatusEnum each : enums) {
			if (each.value == value) {
				return each;
			}
		}
		return ResStatusEnum.Unknown;
	}

	public static ResStatusEnum getByValue(String value) {
		ResStatusEnum[] enums = ResStatusEnum.values();
		for (ResStatusEnum each : enums) {
			if (value.equals(each.toString())) {
				return each;
			}
		}
		return ResStatusEnum.Unknown;
	}

	public static ResStatusEnum getByDisplay(String display) {
		ResStatusEnum[] enums = ResStatusEnum.values();
		for (ResStatusEnum each : enums) {
			if (each.display.equalsIgnoreCase(display)) {
				return each;
			}
		}
		return ResStatusEnum.Unknown;
	}

}
