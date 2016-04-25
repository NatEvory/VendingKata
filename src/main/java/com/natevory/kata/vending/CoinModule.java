package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoinModule {
	private static final Logger log = LoggerFactory.getLogger(CoinModule.class);
	private List<Coin> insertedCoins = new ArrayList<Coin>();
	private Map<CoinType, Integer> coinStock = new HashMap<>();
	
	
	public boolean insertCoin(Coin coin){
		if(coin == null){
			log.error("CoinModule cannot accept null coins");
			throw new IllegalArgumentException("CoinModule cannot accept null coins");
		}
		CoinType type = CoinType.getCoinType(coin);
		if(type == CoinType.UNKNOWN){
			return false;
		}
		insertedCoins.add(coin);
		return true;
	}
	
	
	public int getValueOfInsertedCoins(){
		return insertedCoins.stream()
				.map(c -> CoinType.getCoinType(c))
				.filter(ct -> ct != CoinType.UNKNOWN)
				.mapToInt(CoinType::valueInCents)
				.sum();
	}
	
	public int getInsertedCoinCount(){
		return insertedCoins.size();
	}
	
	public void stockCoins(CoinType coinType,int amount){
		validateCoinType(coinType);
		coinStock.put(coinType,getStock(coinType)+amount);
	}
	
	public int getStock(CoinType coinType){
		validateCoinType(coinType);
		Integer stock = coinStock.get(coinType);
		if(stock == null)
			stock = new Integer(0);
		return stock;
		
	}

	private void validateCoinType(CoinType coinType){
		if(coinType == null){
			log.error("CoinModule cannot stock null CoinType");
			throw new IllegalArgumentException("CoinModule cannot stock null CoinType");
		}
		if(coinType == CoinType.UNKNOWN){
			log.error("CoinModule cannot stock unknown CoinType");
			throw new IllegalArgumentException("CoinModule cannot stock unknown CoinType");
		}
	}
	
}
