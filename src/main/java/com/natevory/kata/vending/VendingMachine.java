package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

public class VendingMachine {
	
	
	private CoinFilter coinFilter = new CoinFilter();
	private List<Coin> coins = new ArrayList<Coin>();
	private List<Coin> returnedCoins = new ArrayList<Coin>();
	
	public void insertCoin(Coin coin){
		CoinType type = coinFilter.getCoinType(coin);
		if(type == CoinType.UNKNOWN)
			rejectCoin(coin);
		else
			coins.add(coin);
	}
	
	public Coin[] retrieveReturnedCoins(){
		Coin[] coinReturn = returnedCoins.toArray(new Coin[0]);
		returnedCoins.clear();
		return coinReturn;
	}
	
	private void rejectCoin(Coin coin){
		returnedCoins.add(coin);
	}
	
	public String getDisplayMessage(){
		if(coins.size()>0)
			return "COINS";
		else
			return "INSERT COIN";
	}
	
	public int getCoinCount(){
		return coins.size();
	}
}
