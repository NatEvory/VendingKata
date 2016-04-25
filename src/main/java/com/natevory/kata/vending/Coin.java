package com.natevory.kata.vending;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Coin {
	
	private static final Logger log = LoggerFactory.getLogger(Coin.class);
	
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
		if(weight<=0){
			log.error(String.format("Invalid coin weight:%f", weight));
			throw new IllegalArgumentException("Coin weight must be greater than 0");
		}
		this.weight=weight;
	}
	
	private void setSize(double size){
		if(size<=0){
			log.error(String.format("Invalid coin size:%f", size));
			throw new IllegalArgumentException("Coin size must be greater than 0");
		}
		this.size=size;
	}
}
