package com.natevory.kata.vending;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoinModuleTest {
	
	private CoinModule coinModule;
	
	@Before
	public void createCoinModule(){
		this.coinModule = new CoinModule();
	}
	
	@Test
	public void theCoinModuleShouldAcceptCoins(){
		Coin quarter = CoinType.createCoin(CoinType.QUARTER);
		assertTrue("CoinModule accepts quarters",coinModule.insertCoin(quarter));
		assertEquals("CoinModule has 1 coin inserted after inserting a coin",1,coinModule.getInsertedCoinCount());

		Coin dime = CoinType.createCoin(CoinType.DIME);
		assertTrue("CoinModule accepts quarters",coinModule.insertCoin(dime));
		assertEquals("CoinModule has 1 coin inserted after inserting 2 coins",2,coinModule.getInsertedCoinCount());

		Coin nickel = CoinType.createCoin(CoinType.NICKEL);
		assertTrue("CoinModule accepts quarters",coinModule.insertCoin(nickel));
		assertEquals("CoinModule has 1 coin inserted after inserting 3 coins",3,coinModule.getInsertedCoinCount());
	}
	
	@Test
	public void theCoinModuleShouldRejectUnknownCoins(){
		Coin coin = new Coin(1,1);
		assertFalse("CoinModule rejects unknown coins",coinModule.insertCoin(coin));
		assertEquals("CoinModule has 0 coins inserted after rejecting a coin",0,coinModule.getInsertedCoinCount());
	}
	
	@Test
	public void theCoinModuleShouldCalculateTheValueofInsertedCoins(){
		Coin nickel = CoinType.createCoin(CoinType.NICKEL);
		coinModule.insertCoin(nickel);
		assertEquals("CoinModule should return 5 for value after inserting a nickel",5,coinModule.getValueOfInsertedCoins());
		
		Coin dime = CoinType.createCoin(CoinType.DIME);
		coinModule.insertCoin(dime);
		assertEquals("CoinModule should return 15 for value after inserting a nickel & dime",15,coinModule.getValueOfInsertedCoins());
		
		Coin quarter = CoinType.createCoin(CoinType.QUARTER);
		coinModule.insertCoin(quarter);
		assertEquals("CoinModule should return 40 for value after inserting a nickel,dime & quarter",40,coinModule.getValueOfInsertedCoins());
	}
	
	@Test
	public void theCoinModuleShouldStockCoins(){
		coinModule.stockCoins(CoinType.NICKEL,3);
		assertEquals("CoinModule should have 3 nickels after socking 3",3,coinModule.getStock(CoinType.NICKEL));
		coinModule.stockCoins(CoinType.QUARTER,2);
		assertEquals("CoinModule should have 2 quarters after socking 2",2,coinModule.getStock(CoinType.QUARTER));
		coinModule.stockCoins(CoinType.DIME,1);
		assertEquals("CoinModule should have 1 dime after socking 1",1,coinModule.getStock(CoinType.DIME));
	}
	
	@Test
	public void theCoinModuleShouldBeAbleToMakeChange(){
		coinModule.stockCoins(CoinType.NICKEL, 2);
		coinModule.stockCoins(CoinType.DIME, 2);
		
		coinModule.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		try{
			Coin[] change = coinModule.makeChange(10);
			int value = 0;
			for(Coin c : change)
				value += CoinType.getCoinType(c).valueInCents();
			assertEquals("CoinModule should return 15 cents after making change for 10 on 25",15,value);
		} catch(InsufficientFundsException ife){
			fail("CoinModule reported insufficient funds");
		} catch(InsufficientChangeException ice){
			fail("CoinModule reproted insufficient change");
		}
		
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void theCoinModuleShouldThrowInsufficientFundsException() throws InsufficientFundsException, InsufficientChangeException{
		coinModule.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		coinModule.makeChange(30);
	}
	
	@Test(expected=InsufficientChangeException.class)
	public void theCoinModuleSHouldThrowInsufficientChangeException() throws InsufficientFundsException, InsufficientChangeException{
		coinModule.insertCoin(CoinType.createCoin(CoinType.QUARTER));
		coinModule.makeChange(10);
	}
}
