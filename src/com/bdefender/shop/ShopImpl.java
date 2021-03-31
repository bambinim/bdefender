package com.bdefender.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.towers.TowerBase;
import com.bdefender.towers.TowerFactory;
import com.bdefender.wallet.*;

public class ShopImpl implements Shop {
	//i diversi tipi di torre dovrebbero forse avere un nome univoco o un id per distinguerle
	Wallet wallet; //to manage the money that the user has
	TowerFactory tf = new TowerFactory();
	Map<String, Integer> twrPrices = new HashMap<>(); //to set prices of tower
	Map<String, Integer> upgPrices = new HashMap<>(); //to set prices of upgrade
	
	public ShopImpl(Wallet wallet) {
		this.wallet = wallet;
	}
	
	/*
	 * @return the price of a specific tower
	 * 
	 */
	public int getTowerPrice(String twrName){
		return this.twrPrices.get(twrName);
	}
	
	/*
	 * set the price of a specific tower
	 */
	
	@Override
	public void setTowerPrice(String twrName, Integer price) {
		twrPrices.put(twrName, price);
	}
	/**
	 * check if is possible to buy a specific tower
	 * @return true if the purchase is successful  
	 */

	@Override
	public boolean hasBoughtTower(Integer price) {
		if(wallet.areMoneyEnough(price)) {
			wallet.subtractMoney(price);
			return true;
		}
		return false;
	}
	
	//if is possible to buy the tower then return a new tower which has to be added to the map
	public Optional<TowerBase> buyTowerZone1(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName) {
		int twrPrice = twrPrices.get(twrName);
		return hasBoughtTower(twrPrice)? Optional.of(tf.getTowerZone1(pool, pos)): Optional.empty();
	}
	
	public Optional<TowerBase> buyTowerZone2(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName) {
		int twrPrice = twrPrices.get(twrName);
		return hasBoughtTower(twrPrice)? Optional.of(tf.getTowerZone2(pool, pos)): Optional.empty();
	}
	
	public Optional<TowerBase> buyTowerDirect1(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName) {
		int twrPrice = twrPrices.get(twrName);
		return hasBoughtTower(twrPrice)? Optional.of(tf.getTowerDirect1(pool, pos)): Optional.empty();
	}


	@Override
	public void buyUpgrade() {
		// TODO Auto-generated method stub

	}





	

	

}
