package com.natevory.kata.vending;

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
}
