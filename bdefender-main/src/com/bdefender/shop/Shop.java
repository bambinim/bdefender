package com.bdefender.shop;

import java.util.Optional;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.towers.TowerBase;

public interface Shop {
    boolean hasBoughtTower(Integer price);
    void buyUpgrade();
	int getTowerPrice(String twrName);
	void setTowerPrice(String twrName, Integer price);
	Optional<TowerBase> buyTowerZone1(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName);
	Optional<TowerBase> buyTowerZone2(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName);
	Optional<TowerBase> buyTowerDirect1(EnemiesPoolInteractor pool, Pair<Double, Double> pos,String twrName);

}
