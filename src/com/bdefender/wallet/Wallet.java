package com.bdefender.wallet;

public interface Wallet {
	void subtractMoney(int price);
	boolean areMoneyEnough(int price);
	int getMoney();

}
