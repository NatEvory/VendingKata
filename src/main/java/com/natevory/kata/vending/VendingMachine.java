package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

public class VendingMachine {
	
	private EventListenerList listenerList = new EventListenerList();
	
	
	public void addCoinReturnListener(CoinReturnListener listener){
		listenerList.add(CoinReturnListener.class, listener);	
	}
	
	private List<Coin> coins = new ArrayList<Coin>();
	
	public void insertCoin(Coin coin){
		coins.add(coin);
	}
	
	private void rejectCoin(Coin coin){
		fireCoinReturn(new Coin[]{coin});
	}
	
	
	private void fireCoinReturn(Coin[] coins){
		CoinReturnListener[] listeners = listenerList.getListeners(CoinReturnListener.class);
		for(CoinReturnListener l : listeners){
			l.coinsReturned(coins);
		}
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
