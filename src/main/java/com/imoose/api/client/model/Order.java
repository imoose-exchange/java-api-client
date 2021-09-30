package com.imoose.api.client.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class Order {
	
	public static enum Side {
		
		buy("buy"),
	    sell("sell");

	    public final String value;

	    private Side(String value) {
	        this.value = value;
	    }
	}
	
	public static enum Type {
		
		limit("limit"),
	    market("market");

	    public final String value;

	    private Type(String value) {
	        this.value = value;
	    }
	}
	
	
	
	private String id;
	private String market;
	private String side;
	private String state;
	private String type;
	@SerializedName("portfolio_id") 
	private String portfolioId;
	
	private BigDecimal price;
	private BigDecimal volume;

	private BigDecimal balance;
	@SerializedName("balance_origional") 
	private BigDecimal balanceOrigional;
	private BigDecimal recivied;
	@SerializedName("trade_count") 
	private int tradeCount;
	private BigDecimal fee;
	
	
	private String[] trades;
	
	@SerializedName("created_at") 
	private long createdAt;
	@SerializedName("updated_at") 
	private long updatedAt;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getBalanceOrigional() {
		return balanceOrigional;
	}
	public void setBalanceOrigional(BigDecimal balanceOrigional) {
		this.balanceOrigional = balanceOrigional;
	}
	public BigDecimal getRecivied() {
		return recivied;
	}
	public void setRecivied(BigDecimal recivied) {
		this.recivied = recivied;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String[] getTrades() {
		return trades;
	}
	public void setTrades(String[] trades) {
		this.trades = trades;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
