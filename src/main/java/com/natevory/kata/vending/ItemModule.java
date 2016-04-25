package com.natevory.kata.vending;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemModule {

	private static final Logger log = LoggerFactory.getLogger(ItemModule.class);
	
	
	private Map<ItemType, Integer> itemStock = new HashMap<ItemType,Integer>();
	
	
	public void stockItem(ItemType itemType,int amount){
		itemStock.put(itemType, getStock(itemType)+amount);
	}
	
	public int getStock(ItemType itemType){
		Integer currentStock = itemStock.get(itemType);
		if(currentStock == null)
			currentStock = new Integer(0);
		return currentStock;
	}
	
}
