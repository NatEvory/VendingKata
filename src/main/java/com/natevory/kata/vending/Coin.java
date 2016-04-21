package com.natevory.kata.vending;


public class Coin {
	private int weight, size;
	
	public Coin(int weight, int size){
		setWeight(weight);
		setSize(size);
	}
	public int getWeight(){
		return weight;
	}
	public int getSize(){
		return size;
	}
	
	
	private void setWeight(int weight){
		if(weight<=0)
			throw new IllegalArgumentException("Coin weight must be greater than 0");
		this.weight=weight;
	}
	
	private void setSize(int size){
		if(size<=0)
			throw new IllegalArgumentException("Coin size must be greater than 0");
		this.size=size;
	}
}
