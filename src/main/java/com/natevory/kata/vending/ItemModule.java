package com.natevory.kata.vending;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemModule {

	private static final Logger log = LoggerFactory.getLogger(ItemModule.class);
	
	
	private Map<ItemType, Integer> itemStock = new HashMap<ItemType,Integer>();
	
	
	public Item dispenseItem(ItemType itemType){
		if(getStock(itemType)== 0)
			return null;
		itemStock.put(itemType, getStock(itemType)-1);
		return new Item(itemType);
	}
	
	public void stockItem(ItemType itemType,int amount){
		itemStock.put(itemType, getStock(itemType)+amount);
	}
	
	public int getStock(ItemType itemType){
		Integer currentStock = itemStock.get(itemType);
		if(currentStock == null)
			currentStock = new Integer(0);
		return currentStock;
	}
	
	public int getPrice(ItemType itemType){
		if(itemType == ItemType.CANDY)
			return 65;
		if(itemType == ItemType.CHIPS)
			return 50;
		if(itemType == ItemType.SODA)
			return 100;
		throw new IllegalArgumentException("Cannot get price of unkown item type");
	}
	
}
