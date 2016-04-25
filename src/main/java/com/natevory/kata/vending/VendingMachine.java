package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendingMachine {
	
	private List<Coin> coins = new ArrayList<Coin>();
	private List<Coin> returnedCoins = new ArrayList<Coin>();
	private List<Item> dispensedItems = new ArrayList<Item>();
	private static final Logger log = LoggerFactory.getLogger(VendingMachine.class);
	
	
	public void insertCoin(Coin coin){
		CoinType type = CoinType.getCoinType(coin);
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
		log.debug("Displaying a message");
		if(coins.size()>0)
			return "COINS";
		else
			return "INSERT COIN";
	}
	
	public int getCoinCount(){
		return coins.size();
	}
	
	public void requestItem(ItemType itemType){
		if(itemType == null){
			log.error("Cannot request null ItemType");
			throw new IllegalArgumentException("Cannot request null ItemType");
		}
		dispensedItems.add(new Item(itemType));
	}
	
	public Item[] retrieveDispensedItems(){
		Item[] items = dispensedItems.toArray(new Item[0]);
		dispensedItems.clear();
		return items;
	}
}
