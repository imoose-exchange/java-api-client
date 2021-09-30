package com.imoose.api.client.model;

import com.google.gson.annotations.SerializedName;

public class Asset {

	private String id;
	private String name;
	private String state;
	private int precision;
	@SerializedName("class") 
	private String assetClass;
	@SerializedName("subclass") 
	private String assetSubClass;
	
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	public String getAssetSubClass() {
		return assetSubClass;
	}
	public void setAssetSubClass(String assetSubClass) {
		this.assetSubClass = assetSubClass;
	}
	
	
	
}
