package com.natevory.kata.vending;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.event.EventListenerList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendingMachine {
	
	private List<Coin> coins = new ArrayList<Coin>();
	private List<Coin> returnedCoins = new ArrayList<Coin>();
	private List<Item> dispensedItems = new ArrayList<Item>();
	private Queue<String> messageQueue = new LinkedList<String>();
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
		if(messageQueue.size() > 0){
			return messageQueue.poll();
		} else if (coins.size()>0){
			float coinsValue = calculateValueOfInsertedCoins()/100f;
			return String.format("%.2f",coinsValue);
		} else {
			return "INSERT COIN";
		}
	}
	
	private int calculateValueOfInsertedCoins(){
		return coins.stream()
				.map(c -> CoinType.getCoinType(c))
				.filter(ct -> ct != CoinType.UNKNOWN)
				.mapToInt(CoinType::valueInCents)
				.sum();
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
		messageQueue.add("Thank You");
	}
	
	public Item[] retrieveDispensedItems(){
		Item[] items = dispensedItems.toArray(new Item[0]);
		dispensedItems.clear();
		return items;
	}
}
