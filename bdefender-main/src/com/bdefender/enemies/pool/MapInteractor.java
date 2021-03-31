package com.bdefender.enemies.pool;


import com.bdefender.Pair;
import com.bdefender.map.Coordinates;

import java.util.List;

public interface MapInteractor {
	
	public List<Coordinates> getKeyPoints();
	
	public Pair<Integer, Integer> getStartingDirection();

	public Coordinates getSpawnPoint();

}
