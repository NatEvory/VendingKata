package com.natevory.kata.vending;

import static com.natevory.kata.vending.CoinType.DIME;
import static com.natevory.kata.vending.CoinType.NICKEL;
import static com.natevory.kata.vending.CoinType.QUARTER;
import static com.natevory.kata.vending.CoinType.UNKNOWN;

public enum CoinType {
	NICKEL(5.0,21.21),
	DIME(2.27,17.9),
	QUARTER(5.67,24.26),
	UNKNOWN(-1,-1);
	
	private final double weight;
	private final double size;
	
	CoinType(double weight, double size){
		this.weight=weight;
		this.size=size;
	}
	
	public double weight(){return weight;}
	public double size(){return size;}
	
	public static Coin createCoin(CoinType type){
		if(type == UNKNOWN)
			return null;
		return new Coin(type.weight(),type.size());
	}
	public static CoinType getCoinType(Coin coin){
		final double coinSize=coin.getSize();
		final double coinWeight=coin.getWeight();
		if(coinSize==QUARTER.size() && coinWeight==QUARTER.weight())
			return QUARTER;
		else if(coinSize==DIME.size() && coinWeight==DIME.weight())
			return DIME;
		else if(coinSize==NICKEL.size() && coinWeight==NICKEL.weight())
			return NICKEL;
		else
			return UNKNOWN;
	}
}
