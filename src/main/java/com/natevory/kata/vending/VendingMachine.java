package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendingMachine {
	
	private CoinModule coinModule;
	private List<Coin> returnedCoins = new ArrayList<Coin>();
	private List<Item> dispensedItems = new ArrayList<Item>();
	private Queue<String> messageQueue = new LinkedList<String>();
	private static final Logger log = LoggerFactory.getLogger(VendingMachine.class);
	
	public VendingMachine(CoinModule coinModule){
		setCoinModule(coinModule);
	}
	
	private void setCoinModule(CoinModule coinModule){
		if(coinModule == null){
			log.error("VendingMachine cannot have a null CoinModule");
			throw new IllegalArgumentException("VendingMachine cannot have a null CoinModule");
		}
		this.coinModule = coinModule;
	}
	
	public void insertCoin(Coin coin){
		if(!coinModule.insertCoin(coin))
			rejectCoin(coin);
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
		if(messageQueue.size() > 0){
			return messageQueue.poll();
		} else if (coinModule.getInsertedCoinCount()>0){
			float coinsValue = coinModule.getValueOfInsertedCoins()/100f;
			return String.format("%.2f",coinsValue);
		} else {
			return "INSERT COIN";
		}
	}
	
	public int getCoinCount(){
		return coinModule.getInsertedCoinCount();
	}
	
	public void requestItem(ItemType itemType){
		if(itemType == null){
			log.error("Cannot request null ItemType");
			throw new IllegalArgumentException("Cannot request null ItemType");
		}
		dispensedItems.add(new Item(itemType));
		messageQueue.add("Thank You");
	}
	
	public Item[] retrieveDispensedItems(){
		Item[] items = dispensedItems.toArray(new Item[0]);
		dispensedItems.clear();
		return items;
	}
}
