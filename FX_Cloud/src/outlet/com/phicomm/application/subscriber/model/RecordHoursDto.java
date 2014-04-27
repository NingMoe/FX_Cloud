package com.phicomm.application.subscriber.model;


public class RecordHoursDto {
	private long id;
	private int hours;
	private double kwh;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public double getKwh() {
		return kwh;
	}
	public void setKwh(double kwh) {
		this.kwh = kwh;
	}
	public RecordHoursDto(long id, int hours, double kwh) {
		this.id = id;
		this.hours = hours;
		this.kwh = kwh;
	}
	
}
