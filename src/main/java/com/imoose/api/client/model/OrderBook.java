package com.imoose.api.client.model;

import java.math.BigDecimal;

public class OrderBook {
	
	private BigDecimal[][] bids;
	private BigDecimal[][] asks;
	
	public BigDecimal[][] getBids() {
		return bids;
	}
	public void setBids(BigDecimal[][] bids) {
		this.bids = bids;
	}
	public BigDecimal[][] getAsks() {
		return asks;
	}
	public void setAsks(BigDecimal[][] asks) {
		this.asks = asks;
	}
	
	
}
