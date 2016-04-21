package com.natevory.kata.vending;

import java.util.EventListener;

public interface CoinReturnListener extends EventListener {
	public void coinsReturned(Coin[] coins);
}
