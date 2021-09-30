package com.imoose.api.client.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;


public class Market {


	public static enum Type {
		
		spot("spot"),
	    virtual("virtual");

	    public final String value;

	    private Type(String value) {
	        this.value = value;
	    }
	}
	
	private String id;
	
	private String name;
	@SerializedName("base_id") 
	private String baseId;
	@SerializedName("quote_id") 
	private String quoteId;
	@SerializedName("base_name") 
	private String baseName;
	@SerializedName("quote_name") 
	private String quoteName;
	private String type;
	@SerializedName("base_precision") 
	private int basePrecision;
	@SerializedName("quote_precision") 
	private int quotePrecision;

	@SerializedName("buy_min_volume") 
	private BigDecimal buyMinVolume;
	@SerializedName("buy_max_volume") 
	private BigDecimal buyMaxVolume;
	@SerializedName("sell_min_volume") 
	private BigDecimal sellMinVolume;
	@SerializedName("sell_max_volume") 
	private BigDecimal sellMaxVolume;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getQuoteName() {
		return quoteName;
	}
	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBasePrecision() {
		return basePrecision;
	}
	public void setBasePrecision(int basePrecision) {
		this.basePrecision = basePrecision;
	}
	public int getQuotePrecision() {
		return quotePrecision;
	}
	public void setQuotePrecision(int quotePrecision) {
		this.quotePrecision = quotePrecision;
	}
	public BigDecimal getBuyMinVolume() {
		return buyMinVolume;
	}
	public void setBuyMinVolume(BigDecimal buyMinVolume) {
		this.buyMinVolume = buyMinVolume;
	}
	public BigDecimal getBuyMaxVolume() {
		return buyMaxVolume;
	}
	public void setBuyMaxVolume(BigDecimal buyMaxVolume) {
		this.buyMaxVolume = buyMaxVolume;
	}
	public BigDecimal getSellMinVolume() {
		return sellMinVolume;
	}
	public void setSellMinVolume(BigDecimal sellMinVolume) {
		this.sellMinVolume = sellMinVolume;
	}
	public BigDecimal getSellMaxVolume() {
		return sellMaxVolume;
	}
	public void setSellMaxVolume(BigDecimal sellMaxVolume) {
		this.sellMaxVolume = sellMaxVolume;
	}
	

	
}
