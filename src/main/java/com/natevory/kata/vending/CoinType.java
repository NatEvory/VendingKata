package com.natevory.kata.vending;

public enum CoinType {
	NICKEL(	5.0,	21.21,	5),
	DIME(	2.27,	17.9,	10),
	QUARTER(5.67,	24.26,	25),
	UNKNOWN(-1,		-1,		0);
	
	private final double weight;
	private final double size;
	private final int valueInCents;
	
	CoinType(double weight, double size, int valueInCents){
		this.weight=weight;
		this.size=size;
		this.valueInCents = valueInCents;
	}
	
	public double weight(){return weight;}
	public double size(){return size;}
	public int valueInCents(){return valueInCents;}
	
	public static Coin createCoin(CoinType type){
		if(type == UNKNOWN || type == null)
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
