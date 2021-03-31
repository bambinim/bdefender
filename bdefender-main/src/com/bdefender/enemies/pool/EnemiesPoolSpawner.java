package com.bdefender.enemies.pool;


import com.bdefender.enemies.EnemyBase;
import com.bdefender.map.Coordinates;

public interface EnemiesPoolSpawner {
	void addEnemy(EnemyBase enemy);
	Coordinates getSpawnPoint();
}


