package com.natevory.kata.vending;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemModuleTest {

	private ItemModule itemModule;
	
	
	@Before
	public void createItemModule(){
		this.itemModule = new ItemModule();
	}
	
	@Test
	public void theItemModuleShouldAllowItemsToBeStocked(){
		itemModule.stockItem(ItemType.CANDY,1);
		assertEquals("ItemModule should have 1 Candy after stocking 1",1,itemModule.getStock(ItemType.CANDY));
		itemModule.stockItem(ItemType.CHIPS,2);
		assertEquals("ItemModule should have 2 Chips after stocking 2",2,itemModule.getStock(ItemType.CHIPS));
		itemModule.stockItem(ItemType.SODA,3);
		assertEquals("ItemModule should have 3 Soda after stocking 3",3,itemModule.getStock(ItemType.SODA));
	}
	
	@Test
	public void theItemModuleShouldDispenseTheRequestedItemIfItIsInStock(){
		//itemModule.dispenseItem(ItemType.CANDY);
	}
	
}
