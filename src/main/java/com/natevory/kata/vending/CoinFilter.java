package com.natevory.kata.vending;

import static com.natevory.kata.vending.CoinType.*;

public class CoinFilter {
	public CoinType getCoinType(Coin coin){
		final double coinSize=coin.getSize();
		final double coinWeight=coin.getWeight();
		if(coinSize==QUARTER.size() && coinWeight==QUARTER.weight())
			return QUARTER;
		if(coinSize==DIME.size() && coinWeight==DIME.weight())
			return DIME;
		else
			return null;
	}
}
