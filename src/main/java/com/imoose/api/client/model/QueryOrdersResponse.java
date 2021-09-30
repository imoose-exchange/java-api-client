package com.imoose.api.client.model;

import java.util.List;

public class QueryOrdersResponse {

	private List<Order> orders;
	private String next;
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	
	
	
}
