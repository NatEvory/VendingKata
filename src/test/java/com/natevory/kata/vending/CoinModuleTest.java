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
	
}
