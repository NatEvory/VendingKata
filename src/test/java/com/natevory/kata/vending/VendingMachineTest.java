package com.natevory.kata.vending;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class VendingMachineTest {

	private VendingMachine vendingMachine;
	
	@Before
	public void TestSetup(){
		vendingMachine=new VendingMachine();
	}
	
	@Test
	public void theVendingMachineShouldAcceptCoins(){
		vendingMachine.insertCoin(new Coin(1,1));
		assertEquals("There is 1 coin in the VendingMachine",1,vendingMachine.getCoinCount());
	}
	
	@Test
	public void theVendingMachineShouldOnlyAcceptNickelsDimesandQuarters(){
	}
	
	@Test
	public void whenVendingMachineIsEmptyItShouldDisplayInsertCoins(){
		assertEquals("An empty vending machine should display \"INSERT COIN\"","INSERT COIN",vendingMachine.getDisplayMessage());
		vendingMachine.insertCoin(new Coin(1,1));
		assertNotEquals("A vending machine with a coin in it should NOT display \"INSERT COIN\"","INSERT COIN", vendingMachine.getDisplayMessage());
	}
	
}
