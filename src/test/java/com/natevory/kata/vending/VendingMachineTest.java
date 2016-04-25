package com.natevory.kata.vending;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	
	@Before
	public void testSetup(){
		vendingMachine=new VendingMachine(new CoinModule());
	}
	
	@Test
	public void theVendingMachineShouldAcceptCoins(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		assertEquals("There is 1 coin in the VendingMachine",1,vendingMachine.getCoinCount());
	}
	
	@Test
	public void theVendingMachineShouldDisplayTheValueOfInsertedCoins(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.NICKEL));
		assertEquals("VendingMachine should display '0.05' after inserting a nickel","0.05",vendingMachine.getDisplayMessage());
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		assertEquals("VendingMachine should display '0.30' after inserting a nickel & quarter","0.30",vendingMachine.getDisplayMessage());
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.DIME));
		assertEquals("VendingMachine should display '0.40' after inserting a nickel,quarter & dime ","0.40",vendingMachine.getDisplayMessage());
		vendingMachine.insertCoin(new Coin(1,1));
		assertEquals("VendingMachine should display '0.40' after insterting a nickel,quarter,dime & invalid coin","0.40",vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void theVendingMachineShouldOnlyAcceptNickelsDimesandQuarters(){
		vendingMachine.insertCoin(new Coin(1,1));
		assertEquals("A vending machine should reject invalid coins",1,vendingMachine.retrieveReturnedCoins().length);
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		assertEquals("VendingMachine should accept quarters",1,vendingMachine.getCoinCount());
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.NICKEL));
		assertEquals("VendingMachine should accept nickels",2,vendingMachine.getCoinCount());
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.DIME));
		assertEquals("VendingMachine should accept quarters",3,vendingMachine.getCoinCount());
	}
	
	@Test
	public void whenVendingMachineIsEmptyItShouldDisplayInsertCoins(){
		assertEquals("An empty vending machine should display \"INSERT COIN\"","INSERT COIN",vendingMachine.getDisplayMessage());
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		assertNotEquals("A vending machine with a coin in it should NOT display \"INSERT COIN\"","INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void theVendingMachineShouldDispenseChipsWhen50CentsHaveBeenInsterted(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.requestItem(ItemType.CHIPS);
		Item[] dispensedItems =  vendingMachine.retrieveDispensedItems();
		assertEquals("VendingMachine should dispense 1 item",1,dispensedItems.length);
		assertEquals("VendingMachine should dispense chips",ItemType.CHIPS,dispensedItems[0].getItemType());
	}
	
	@Test
	public void theVendingMachineShouldDisplayThankYouAfterPurchasingAnItem(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.requestItem(ItemType.CHIPS);
		assertEquals("VendingMachine should say Thank You","Thank You",vendingMachine.getDisplayMessage());
	}
	
}
