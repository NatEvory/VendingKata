package com.natevory.kata.vending;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Item {
	private ItemType itemType;
	
	private static final Logger log = LoggerFactory.getLogger(Item.class);
	
	public Item(ItemType itemType){
		setItemType(itemType);
	}
	
	private void setItemType(ItemType itemType){
		if(itemType == null){
			log.error("Item cannot have a null itemType");
			throw new IllegalArgumentException("Item cannot have a null itemType");
		}
		this.itemType = itemType;
	}
	public ItemType getItemType(){
		return itemType;
	}
}
