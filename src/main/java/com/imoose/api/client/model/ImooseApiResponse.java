package com.imoose.api.client.model;

import java.util.List;

public class ImooseApiResponse <E> {

	private String[] errors;
	private E data; 
	private int status;
	
	public ImooseApiResponse()
	{

	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}
	

}

