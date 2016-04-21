package com.natevory.kata.vending;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.natevory.kata.vending.CoinType.*;

import org.junit.Before;

public class CoinFilterTest {

	private CoinFilter coinFilter;
	
	@Before
	public void testSetup(){
		coinFilter = new CoinFilter();
	}
	
	@Test
	public void coinFilterShouldRecognizeQuarters(){
		Coin quarter = new Coin(QUARTER.weight(),QUARTER.size());
		assertEquals("A quarter should be recognized as a quarter",QUARTER,coinFilter.getCoinType(quarter));
	}
	
	@Test
	public void coinFilterShouldRecognizeDimes(){
		Coin dime = new Coin(DIME.weight(),DIME.size());
		assertEquals("A quarter should be recognized as a quarter",DIME,coinFilter.getCoinType(dime));
	}
	
	@Test
	public void coinFilterShouldRecognizeNickels(){
		Coin nickel = new Coin(NICKEL.weight(),NICKEL.size());
		assertEquals("A quarter should be recognized as a quarter",NICKEL,coinFilter.getCoinType(nickel));
	}
	
	
}
