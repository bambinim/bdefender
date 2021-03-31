package com.bdefender.wallet;

public class WalletImpl implements Wallet {
	final int NGLIMIT = -1;
	int userMoney = 0;
	/*
	 * set the initial value of the user money
	 */
	public WalletImpl(int initValue) {
		this.userMoney = initValue;
	}
	
	/*
	 *subtract the prices at the user money  
	 */
	@Override
	public void subtractMoney(int price) {
		if(areMoneyEnough(price)) {
			this.userMoney = this.userMoney - price; 
		}
	}
	/*
	 * check if there are enough money to proceed with the purchase
	 * @return true if money are enough or false if not
	 */
	@Override
	public boolean areMoneyEnough(int price) {
		return this.userMoney - price > NGLIMIT;
	}
	
	/*
	 * @ return the user money amount
	 */
	@Override
	public int getMoney() {
		return this.userMoney;
	}

}
