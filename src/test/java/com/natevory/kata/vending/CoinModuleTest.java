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
		Coin coin = CoinType.createCoin(CoinType.QUARTER);
		assertTrue("CoinModule accepts quarters",coinModule.insertCoin(coin));
		assertEquals("CoinModule has 1 coin inserted after inserting a coin",1,coinModule.getInsertedCoinCount());
	}
	
	@Test
	public void theCoinModuleShouldRejectUnknownCoins(){
		Coin coin = new Coin(1,1);
		assertFalse("CoinModule rejects unknown coins",coinModule.insertCoin(coin));
		assertEquals("CoinModule has 0 coins inserted after rejecting a coin",0,coinModule.getInsertedCoinCount());
		
	}
	
}
