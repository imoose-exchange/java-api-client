package com.imoose.api.client.model;

public class ImooseApiException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6302556796271226019L;
	private String[] errors;
	
	
	public ImooseApiException(String[] errors) {
		this.setErrors(errors);
	}


	public String[] getErrors() {
		return errors;
	}


	public void setErrors(String[] errors) {
		this.errors = errors;
	}


	@Override
	public String getMessage() {
		if(errors != null && errors.length > 0) {
			return String.join(",", errors);
		}
		return super.getMessage();
	}
	
	
	
	
}
