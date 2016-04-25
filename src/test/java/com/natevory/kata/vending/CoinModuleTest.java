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
		assertTrue(coinModule.insertCoin(coin));
		assertEquals("CoinModule has 1 coin in stock after inserting a coin",1,coinModule.getInsertedCoinCount());
	}
	
}
