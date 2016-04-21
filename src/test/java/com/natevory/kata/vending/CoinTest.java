package com.natevory.kata.vending;

import org.junit.Test;

public class CoinTest {

	@Test(expected=IllegalArgumentException.class)
	public void coinsShouldNotHaveANegativeWeight(){
		new Coin(-1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void coinsShouldNotHaveAZeroWeight(){
		new Coin(0,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void coinsShouldNotHaveANegativeSize(){
		new Coin(1,-1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void coinsShouldNotHaveAZeroSize(){
		new Coin(1,0);
	}
}
