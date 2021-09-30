package com.imoose.api.client;

import com.imoose.api.client.impl.ImooseApiRestClientFeign;

public class ImooseClientFactory {

	
	  private final String baseUrl = "https://api.imoose.com";
	  private String apiKey;
	  private String secret;

	  /**
	   * Instantiates a new imoose api client factory.
	   *
	   * @param apiKey the API key
	   * @param secret the Secret
	   */
	  private ImooseClientFactory(String apiKey, String secret) {
	    this.apiKey = apiKey;
	    this.secret = secret;
	  }
	
	  
	  /**
	   * New instance.
	   *
	   * @param apiKey the API key
	   * @param secret the Secret
	   *
	   * @return the imoose api client factory
	   */
	  public static ImooseClientFactory newInstance(String apiKey, String secret) {
	    return new ImooseClientFactory(apiKey, secret);
	  }
	  
	  public  ImooseApiRestClient newRestClient() {
		    return new ImooseApiRestClientFeign(apiKey, secret, baseUrl);
	  }

	  
}
