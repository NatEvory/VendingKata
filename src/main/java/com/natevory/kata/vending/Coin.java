package com.natevory.kata.vending;


public class Coin {
	private double weight, size;
	
	public Coin(double weight, double size){
		setWeight(weight);
		setSize(size);
	}
	public double getWeight(){
		return weight;
	}
	public double getSize(){
		return size;
	}
	
	
	private void setWeight(double weight){
		if(weight<=0)
			throw new IllegalArgumentException("Coin weight must be greater than 0");
		this.weight=weight;
	}
	
	private void setSize(double size){
		if(size<=0)
			throw new IllegalArgumentException("Coin size must be greater than 0");
		this.size=size;
	}
}
