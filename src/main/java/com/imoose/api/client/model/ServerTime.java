package com.imoose.api.client.model;

import com.google.gson.annotations.SerializedName;

public class ServerTime {

	@SerializedName("unix_time") 
	private long unixTime;

	@SerializedName("unix_time_ms") 
	private long UnixTimeMs;

	@SerializedName("rfc1123") 
	private String rfc1123;
	public long getUnixTime() {
		return unixTime;
	}
	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}
	public long getUnixTimeMs() {
		return UnixTimeMs;
	}
	public void setUnixTimeMs(long unixTimeMs) {
		UnixTimeMs = unixTimeMs;
	}
	public String getRfc1123() {
		return rfc1123;
	}
	public void setRfc1123(String rfc1123) {
		this.rfc1123 = rfc1123;
	}
	
	
}
