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
	
	public List<Coin> returnInsertedCoins(){
		List<Coin> returnedCoins = new ArrayList<Coin>();
		returnedCoins.addAll(insertedCoins);
		insertedCoins.clear();
		return returnedCoins;
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


	public List<Coin> makeChange(int value) throws InsufficientFundsException, InsufficientChangeException{
		int insertedCoinValue = getValueOfInsertedCoins();
		int difference = insertedCoinValue - value;
		if(difference < 0)
			throw new InsufficientFundsException();
		List<Coin> returnCoins = new ArrayList<Coin>();
		Coin coin;
		while(difference>=CoinType.QUARTER.valueInCents() && getStock(CoinType.QUARTER)>0){
			coin = dispenseCoinFromStock(CoinType.QUARTER);
			difference -=CoinType.QUARTER.valueInCents();
			returnCoins.add(coin);
		}
		while(difference>=CoinType.DIME.valueInCents() && getStock(CoinType.DIME)>0){
			coin = dispenseCoinFromStock(CoinType.DIME);
			difference -=CoinType.DIME.valueInCents();
			returnCoins.add(coin);
		}
		while(difference>=CoinType.NICKEL.valueInCents() && getStock(CoinType.NICKEL)>0){
			coin = dispenseCoinFromStock(CoinType.NICKEL);
			difference -=CoinType.NICKEL.valueInCents();
			returnCoins.add(coin);
		}
		if(difference>0){
			for(Coin c :returnCoins){
				CoinType ct = CoinType.getCoinType(c);
				stockCoins(ct, 1);
			}
			throw new InsufficientChangeException();
		}
		return returnCoins;
	}
	
	private Coin dispenseCoinFromStock(CoinType coinType){
		int currentStock = getStock(coinType);
		if(currentStock <1)
			return null;
		coinStock.put(coinType,currentStock-1);
		return CoinType.createCoin(coinType);
	}
	
}
