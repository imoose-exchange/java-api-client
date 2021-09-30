package com.imoose.api.client.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class NewOrder {
	
	private String market;
	private String side;
	private String type;
	private String portfolioId;
	private BigDecimal price;
	private BigDecimal volume;
	
	public NewOrder(String portfolioId, String market, Order.Side side, Order.Type type, BigDecimal volume) {
		this.portfolioId = portfolioId;
		this.market = market;
		this.type = type.value;
		this.side = side.value;
		this.volume = volume;
	}
	
	public NewOrder(String portfolioId, String market, Order.Side side, Order.Type type, BigDecimal volume, BigDecimal price) {
		this(portfolioId,market,side,type,volume);
		this.price = price;
	}
	
	
	public static NewOrder LimitBuyOrder(String portfolioId, String market,BigDecimal volume, BigDecimal price) {
		NewOrder newOrder = new NewOrder(portfolioId,market,Order.Side.buy,Order.Type.limit, volume, price);
		return newOrder;
	}
	
	public static NewOrder LimitSellOrder(String portfolioId, String market,BigDecimal volume, BigDecimal price) {
		NewOrder newOrder = new NewOrder(portfolioId,market,Order.Side.sell,Order.Type.limit, volume, price);
		return newOrder;
	}
	
	public static NewOrder MarketBuyOrder(String portfolioId, String market,BigDecimal volume) {
		NewOrder newOrder = new NewOrder(portfolioId,market,Order.Side.buy,Order.Type.market, volume);
		return newOrder;
	}
	
	public static NewOrder MarketSellOrder(String portfolioId, String market,BigDecimal volume) {
		NewOrder newOrder = new NewOrder(portfolioId,market,Order.Side.sell,Order.Type.market, volume);
		return newOrder;
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
	
	
	
}
