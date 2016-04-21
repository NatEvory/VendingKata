package com.natevory.kata.vending;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class CoinFilterTest {

	private CoinFilter coinFilter;
	
	@Before
	public void testSetup(){
		coinFilter = new CoinFilter();
	}
	
	@Test
	public void coinFilterShouldRecognizeQuarters(){
		Coin quarter = new Coin(CoinType.QUARTER.weight(),CoinType.QUARTER.size());
		assertEquals("A quarter should be recognized as a quarter",CoinType.QUARTER,coinFilter.getCoinType(quarter));
	}
	
	
	
	
}
