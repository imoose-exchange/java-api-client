package com.imoose.api.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.imoose.api.client.model.Asset;
import com.imoose.api.client.model.Market;
import com.imoose.api.client.model.MarketTrade;
import com.imoose.api.client.model.NewOrder;
import com.imoose.api.client.model.OhlcItem;
import com.imoose.api.client.model.Order;
import com.imoose.api.client.model.OrderBook;
import com.imoose.api.client.model.Portfolio;
import com.imoose.api.client.model.QueryOrdersResponse;
import com.imoose.api.client.model.ServerTime;
import com.imoose.api.client.model.Ticker;

public interface ImooseApiRestClient {

	ServerTime GetServerTime();
	
	// markets
	Market GetMarket(String marketId);
	List<Market> GetMarkets(Market.Type marketType);

	// assets
	Asset GetAsset(String assetId);
	List<Asset> GetAssets();

	// tickers
	Ticker GetTicker(String marketId);
	List<Ticker> GetTickers(Market.Type marketType);

	// trades
	List<MarketTrade> GetRecentMarketTrades(String marketId);
	List<MarketTrade> GetRecentMarketTrades(String marketId, int limit);
	
	// order book
	OrderBook GetMarketDepth(String marketId);
	
	// OHLC Chart Data 
	List<OhlcItem> GetChartOHLC(String marketId, int interval);
	List<OhlcItem> GetChartOHLC(String marketId, int interval, long since);
	
	// Query Orders
	QueryOrdersResponse QueryOpenOrders(String portfolioId);
	QueryOrdersResponse QueryOpenOrders(String portfolioId, int Limit);
	QueryOrdersResponse QueryOpenOrders(String portfolioId, int Limit, String from);
	QueryOrdersResponse QueryOpenOrders(String portfolioId, String from);
	QueryOrdersResponse QueryClosedOrders(String portfolioId);
	QueryOrdersResponse QueryClosedOrders(String portfolioId,int Limit);
	QueryOrdersResponse QueryClosedOrders(String portfolioId,int Limit, String from);
	QueryOrdersResponse QueryClosedOrders(String portfolioId,String from);
	
	// Account Methods
	List<Portfolio> GetPortfolios();
	Map<String,BigDecimal> GetPortfolioBalances(String portfolioId);
	
	// Order Management
	int CancelOrder(String id);
	Order QueryOrder(String id);
	Order PlaceOrder(NewOrder order);
	
	
}
