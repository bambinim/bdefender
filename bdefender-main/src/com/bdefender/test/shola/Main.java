package com.bdefender.test.shola;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.shop.Shop;
import com.bdefender.shop.ShopImpl;
import com.bdefender.map.Map;
import com.bdefender.map.MapInteractorImpl;
import com.bdefender.map.MapLoader;
import com.bdefender.wallet.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
		EnemiesPoolImpl pool = new EnemiesPoolImpl(new MapInteractorImpl(map));
		Wallet wl = new WalletImpl(300);
		Shop shop = new ShopImpl(wl);
		shop.setTowerPrice("Tower1", 20);
		shop.setTowerPrice("Tower2", 200);
		shop.setTowerPrice("Tower3", 300);
		System.out.println(shop.getTowerPrice("Tower1"));
		
		shop.buyTowerDirect1(pool, new Pair(10.0, 10.0), "Tower1");
	}

}
