package com.imoose.api.client.model;

import java.math.BigDecimal;

public class MarketTrade {

	
	private BigDecimal price;
	private BigDecimal volume;
	private long time;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	} 
	
	
	
}
