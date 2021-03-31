package com.bdefender.enemies.pool;

import com.bdefender.enemies.EnemyBase;

import java.util.ArrayList;

public interface EnemiesPoolInteractor {
	ArrayList<EnemyBase> getEnemies();

	void applyDamageById(int id, Double damage);
}
