package com.imoose.api.client.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.imoose.api.client.ImooseApiRestClient;
import com.imoose.api.client.model.Asset;
import com.imoose.api.client.model.CancelOrderResponse;
import com.imoose.api.client.model.ImooseApiException;
import com.imoose.api.client.model.ImooseApiResponse;
import com.imoose.api.client.model.Market;
import com.imoose.api.client.model.Market.Type;
import com.imoose.api.client.model.MarketTrade;
import com.imoose.api.client.model.NewOrder;
import com.imoose.api.client.model.OhlcItem;
import com.imoose.api.client.model.Order;
import com.imoose.api.client.model.OrderBook;
import com.imoose.api.client.model.Portfolio;
import com.imoose.api.client.model.QueryOrdersResponse;
import com.imoose.api.client.model.ServerTime;
import com.imoose.api.client.model.Ticker;

import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestInterceptor;
import feign.RequestLine;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;

interface ImooseApiRestFeignMapping {
	 @RequestLine("GET /v1/public/time")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<ServerTime> GetServerTime();
	 
	 @RequestLine("GET /v1/public/market?id={marketId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Market> GetMarket(@Param("marketId") String marketId);
	 
	 @RequestLine("GET /v1/public/market?type={type}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<List<Market>> GetMarkets(@Param("type") String marketType);
	 
	 @RequestLine("GET /v1/public/asset?id={id}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Asset> GetAsset(@Param("id") String assetId);

	 @RequestLine("GET /v1/public/asset")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<List<Asset>> GetAssets();
	 
	 @RequestLine("GET /v1/public/ticker?id={marketId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Ticker> GetTicker(@Param("marketId") String marketId);
	 
	 @RequestLine("GET /v1/public/ticker?type={type}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<List<Ticker>> GetTickers(@Param("type") String marketType);
	 
	 @RequestLine("GET /v1/public/trade?id={marketId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<String[][]> GetRecentMarketTrades(@Param("marketId") String marketId);
	 
	 @RequestLine("GET /v1/public/trade?id={marketId}&limit={limit}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<String[][]> GetRecentMarketTrades(@Param("marketId") String marketId, @Param("limit") int limit);
	 
	 @RequestLine("GET /v1/public/depth?id={marketId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<OrderBook> GetMarketDepth(@Param("marketId")  String marketId);
	 
	 
	 @RequestLine("GET /v1/public/ohlc?id={marketId}&interval={interval}&since={since}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<String[][]> GetChartOHLC(@Param("marketId")String marketId, @Param("interval")int interval, @Param("since")long since);
	 
	 
	 @RequestLine("GET /v1/private/portfolio")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<List<Portfolio>> GetPortfolios();
	 
	 
	 @RequestLine("GET /v1/private/balance?id={portfolioId}&interval={interval}&since={since}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Map<String,BigDecimal>> GetPortfolioBalances(@Param("portfolioId")String portfolioId);
	 
	 @RequestLine("POST /v1/private/order")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Order> PlaceOrder(@Param("portfolio_id")String portfolioId,
			 @Param("type")String type,
			 @Param("side")String side,
			 @Param("market")String marketId,
			 @Param("price")BigDecimal price,
			 @Param("volume")BigDecimal volume);
		 
	 @RequestLine("GET /v1/private/order?id={orderId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<Order> QueryOrder(@Param("orderId")String orderId);
	 
	 @RequestLine("DELETE /v1/private/order?id={orderId}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<CancelOrderResponse> CancelOrder(@Param("orderId")String orderId);
	 
	 
	 @RequestLine("GET /v1/private/order/open?portfolio_id={portfolio_id}&from={from}&limit={limit}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<QueryOrdersResponse> QueryOpenOrders(@Param("portfolio_id")String portfolioId, @Param("from")String from, @Param("limit")int limit);
	 
	 @RequestLine("GET /v1/private/order/closed?portfolio_id={portfolio_id}&from={from}&limit={limit}")
	 @Headers("Content-Type: application/x-www-form-urlencoded")
	 ImooseApiResponse<QueryOrdersResponse> QueryClosedOrders(@Param("portfolio_id")String portfolioId, @Param("from")String from, @Param("limit")int limit);
	 
}

public class ImooseApiRestClientFeign implements ImooseApiRestClient {

	
	class ImooseApiRestClientFeignAuthInterceptor implements RequestInterceptor {
		
		private String key;
		private String secret;
		
		public ImooseApiRestClientFeignAuthInterceptor(String key, String secret) {
			this.key = key;
			this.secret = secret;
		}
		
		@Override public void apply(RequestTemplate template) {
		    
			  // set API key header.
			  template.header("Api-Key", this.key);
			  
			  // get timestamp as UNIX Milliseconds.
			  long unixTime = System.currentTimeMillis();

			  // add timestamp parameter to query string
			  template.query("timestamp", String.valueOf(unixTime));
	
			  String plainTextSignature = "";
			  
			  // sort parameters alphabetical 
			  if(template.queryLine().length() > 0) {
				  String qLine = template.queryLine().substring(1);
				  String[] splitVals = qLine.split("&");
				  Arrays.sort(splitVals);
				  plainTextSignature = String.join("&", splitVals);
			  }
			  if(template.requestBody().length() > 0) {
				  String[] splitBody = template.requestBody().asString().split("&");
				  Arrays.sort(splitBody);
				  plainTextSignature += String.join("&", splitBody);
			  }
			  	  
			  // all private methods require signature.
			  if(template.path().contains("/private")) {
				try {
				  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
				  SecretKeySpec secretKeySpec = new SecretKeySpec(this.secret.getBytes(), "HmacSHA256");
				  sha256_HMAC.init(secretKeySpec);
				  template.header("Api-Sign", new String(Hex.encodeHex(sha256_HMAC.doFinal(plainTextSignature.getBytes()))));
				} catch (Exception e ) {
					throw new RuntimeException("Unable to sign message.", e);
				}
			  }
		    
		  }
	}
	
	public class IMooseFeignResponseErrorDecoder implements ErrorDecoder {

		public Exception decode(String methodKey, Response response) {
			// TODO Auto-generated method stub
			
			String responsebody = null;
			
			try {
				responsebody = IOUtils.toString(response.body().asReader());
				if(responsebody != null && responsebody.length() > 0)
				{
					return (new Gson().fromJson(responsebody, ImooseApiException.class));
				
				}
				
			} catch (IOException e) {
				return e;
			}
				
			return new Exception("unable to parse error body");
		
		}


	 
	}

	 private ImooseApiRestFeignMapping api;

	 public ImooseApiRestClientFeign(String key, String secret, String baseApiUrl)
	 {
		api = Feign.builder()
				 .encoder(new FormEncoder())
				 .decoder(new GsonDecoder())
				 .errorDecoder(new IMooseFeignResponseErrorDecoder())
                .requestInterceptor(new ImooseApiRestClientFeignAuthInterceptor(key,secret))
                 .target(ImooseApiRestFeignMapping.class, baseApiUrl);
	}
	
	
	@Override
	public ServerTime GetServerTime() {	
		 ImooseApiResponse<ServerTime> response = api.GetServerTime();
		 return response.getData(); 
	}


	@Override
	public Market GetMarket(String marketId) {
		ImooseApiResponse<Market> response = api.GetMarket(marketId);
		return response.getData();
	}


	@Override
	public List<Market> GetMarkets(Market.Type marketType) {
		ImooseApiResponse<List<Market>> response = api.GetMarkets(marketType.value);
		return response.getData();
	}


	@Override
	public Asset GetAsset(String assetId) {
		ImooseApiResponse<Asset> response = api.GetAsset(assetId);
		return response.getData();
	}


	@Override
	public List<Asset> GetAssets() {
		ImooseApiResponse<List<Asset>> response = api.GetAssets();
		return response.getData();
	}


	@Override
	public Ticker GetTicker(String marketId) {
		ImooseApiResponse<Ticker> response = api.GetTicker(marketId);
		return response.getData();
	}


	@Override
	public List<Ticker> GetTickers(Type marketType) {
		ImooseApiResponse<List<Ticker>> response = api.GetTickers(marketType.value);
		return response.getData();
	}


	@Override
	public List<MarketTrade> GetRecentMarketTrades(String marketId) {
		return this.GetRecentMarketTrades(marketId, 0);
	}


	@Override
	public List<MarketTrade> GetRecentMarketTrades(String marketId, int limit) {
		List<MarketTrade> result = new ArrayList<MarketTrade>();
		ImooseApiResponse<String[][]> response = api.GetRecentMarketTrades(marketId,limit);
		for(String[] data : response.getData()) {
			MarketTrade trade = new MarketTrade();
			trade.setPrice(new BigDecimal(data[0]));
			trade.setVolume(new BigDecimal(data[1]));
			trade.setTime(Long.parseLong(data[2]));
			result.add(trade);
		}
		return result;
	}


	@Override
	public OrderBook GetMarketDepth(String marketId) {
		ImooseApiResponse<OrderBook> response = api.GetMarketDepth(marketId);
		return response.getData();
	}


	@Override
	public List<OhlcItem> GetChartOHLC(String marketId, int interval) {
		return this.GetChartOHLC(marketId, interval,0);
	}


	@Override
	public List<OhlcItem> GetChartOHLC(String marketId, int interval, long since) {
		List<OhlcItem> result = new ArrayList<OhlcItem>();
		ImooseApiResponse<String[][]> response = api.GetChartOHLC(marketId, interval, since);
		for(String[] data : response.getData()) {
			OhlcItem item = new OhlcItem();
			item.setTime(Long.parseLong(data[0]));
			item.setOpen(new BigDecimal(data[1]));
			item.setHigh(new BigDecimal(data[2]));
			item.setLow(new BigDecimal(data[3]));
			item.setClose(new BigDecimal(data[4]));
			item.setVolume(new BigDecimal(data[5]));
			result.add(item);
		}
		return result;
	}






	@Override
	public Order PlaceOrder(NewOrder order) {
		 ImooseApiResponse<Order> response = api.PlaceOrder(order.getPortfolioId(), order.getType(), 
				 order.getSide(), order.getMarket(), order.getPrice(), order.getVolume());
		 return response.getData();
	}


	@Override
	public int CancelOrder(String orderId) {
		ImooseApiResponse<CancelOrderResponse> response = api.CancelOrder(orderId);
		return response.getData().getCanceled();
	}


	@Override
	public Order QueryOrder(String orderId) {
		ImooseApiResponse<Order> response = api.QueryOrder(orderId);
		return response.getData();
	}


	@Override
	public List<Portfolio> GetPortfolios() {
		ImooseApiResponse<List<Portfolio>> response = api.GetPortfolios();
		return response.getData();
	}


	@Override
	public Map<String, BigDecimal> GetPortfolioBalances(String portfolioId) {
		ImooseApiResponse<Map<String,BigDecimal>> response = api.GetPortfolioBalances(portfolioId);
		return response.getData();
	}


	@Override
	public QueryOrdersResponse QueryOpenOrders(String portfolioId) {
		return this.QueryOpenOrders(portfolioId,0,"");
	}


	@Override
	public QueryOrdersResponse QueryOpenOrders(String portfolioId, int limit) {
		return this.QueryOpenOrders(portfolioId,limit,"");
	}


	@Override
	public QueryOrdersResponse QueryOpenOrders(String portfolioId, int limit, String from) {
		ImooseApiResponse<QueryOrdersResponse> response = api.QueryOpenOrders(portfolioId, from, limit);
		return response.getData();
	}


	@Override
	public QueryOrdersResponse QueryOpenOrders(String portfolioId, String from) {
		return this.QueryOpenOrders(portfolioId,0,from);
	}


	@Override
	public QueryOrdersResponse QueryClosedOrders(String portfolioId, int limit) {
		return this.QueryClosedOrders(portfolioId,limit,"");
	}

	@Override
	public QueryOrdersResponse QueryClosedOrders(String portfolioId, String from) {
		return this.QueryClosedOrders(portfolioId,0,from);
	}
	
	@Override
	public QueryOrdersResponse QueryClosedOrders(String portfolioId, int limit, String from) {
		ImooseApiResponse<QueryOrdersResponse> response = api.QueryOpenOrders(portfolioId, from, limit);
		return response.getData();
	}


	@Override
	public QueryOrdersResponse QueryClosedOrders(String portfolioId) {
		return this.QueryClosedOrders(portfolioId,0,"");
	}
	
	
}
