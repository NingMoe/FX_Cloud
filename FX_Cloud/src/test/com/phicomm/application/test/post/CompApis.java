package com.phicomm.application.test.post;

public class CompApis {
	private String name;
	private long time;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public CompApis(String name, long time) {
		super();
		this.name = name;
		this.time = time;
	}
	
}
