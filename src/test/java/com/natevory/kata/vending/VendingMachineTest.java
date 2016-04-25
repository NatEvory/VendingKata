package com.natevory.kata.vending;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	
	@Before
	public void testSetup(){
		vendingMachine=new VendingMachine(new CoinModule(),new ItemModule());
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
	public void theVendingMachineShouldDispenseChipsWhen50CentsHaveBeenInserted(){
		vendingMachine.stockItem(ItemType.CHIPS,1);
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.requestItem(ItemType.CHIPS);
		Item[] dispensedItems =  vendingMachine.retrieveDispensedItems();
		assertEquals("VendingMachine should dispense 1 item",1,dispensedItems.length);
		assertEquals("VendingMachine should dispense chips",ItemType.CHIPS,dispensedItems[0].getItemType());
	}
	
	@Test
	public void theVendingMachineShouldDisplayThankYouAfterPurchasingAnItem(){
		vendingMachine.stockItem(ItemType.CHIPS, 1);
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.requestItem(ItemType.CHIPS);
		assertEquals("VendingMachine should say Thank You","Thank You",vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void theVendingMachineShouldStockCoins(){
		vendingMachine.stockCoins(CoinType.QUARTER,3);
		vendingMachine.stockCoins(CoinType.DIME,2);
		vendingMachine.stockCoins(CoinType.NICKEL,5);
	}
	
	@Test
	public void theVendingMachineShouldDisplaySoldOutWhenItIsOutStock(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.requestItem(ItemType.CHIPS);
		assertEquals("VendingMachine should display 'Sold Out'","Sold Out",vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void theVendingMachineShouldDispenseRequestedItemAndChange(){
		vendingMachine.stockCoins(CoinType.QUARTER,3);
		vendingMachine.stockCoins(CoinType.DIME,2);
		vendingMachine.stockCoins(CoinType.NICKEL,5);
		
		vendingMachine.stockItem(ItemType.CANDY, 1);

		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		
		vendingMachine.requestItem(ItemType.CANDY);
		Item[] dispensedItems = vendingMachine.retrieveDispensedItems();
		assertEquals("VendingMachine should dispense 1 item",1,dispensedItems.length);
		assertEquals("VendingMachine should dispense chips",ItemType.CANDY,dispensedItems[0].getItemType());
		Coin[] returnedCoins = vendingMachine.retrieveReturnedCoins();
		int value = 0;
		for(Coin c:returnedCoins){
			value +=CoinType.getCoinType(c).valueInCents();
		}
		assertEquals("VendingMachine should return 10 cents change",10,value);
	}
	
	@Test
	public void theVendingMachineShouldDisplayPriceWhenThereAreInsufficientFunds(){
		vendingMachine.stockCoins(CoinType.QUARTER,3);
		vendingMachine.stockCoins(CoinType.DIME,2);
		vendingMachine.stockCoins(CoinType.NICKEL,5);
		
		vendingMachine.stockItem(ItemType.SODA, 1);
		
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		
		vendingMachine.requestItem(ItemType.SODA);
		
		assertEquals("VendingMachine should display 'Price: 1.00'", "Price: 1.00",vendingMachine.getDisplayMessage());
	}
	
	@Test
	public void theVendingMachineShouldReturnCoinsWhenRequested(){
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.DIME));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.DIME));
		vendingMachine.insertCoin(CoinType.createCoin(CoinType.NICKEL));
		
		vendingMachine.requestCoinReturn();
		
		Coin[] returnedCoins = vendingMachine.retrieveReturnedCoins();
		
		assertEquals("VendingMachine should return 6 coins",6,returnedCoins.length);
		int quarterCount=0,dimeCount=0,nickelCount=0;
		for(Coin c : returnedCoins){
			CoinType ct = CoinType.getCoinType(c);
			if(ct == CoinType.QUARTER)
				quarterCount++;
			if(ct == CoinType.DIME)
				dimeCount++;
			if(ct == CoinType.NICKEL)
				nickelCount++;
		}
		assertEquals("VendingMachine should return 3 quarters",3,quarterCount);
		assertEquals("VendingMachine should return 2 dimes",2,dimeCount);
		assertEquals("VendingMachine should return 1 nickel",1,nickelCount);
	}
	
}
