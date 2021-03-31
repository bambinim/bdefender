package com.bdefender.map;


import java.util.ArrayList;
import java.util.List;


import com.bdefender.Pair;
import com.bdefender.enemies.pool.MapInteractor;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;


public class MapInteractorImpl implements MapInteractor {
	Map map;

	public MapInteractorImpl(Map map){
		this.map = map;
	}

	@Override
	public List<Coordinates> getKeyPoints() {
		List<Coordinates> keyPoints = new ArrayList<>(map.getPath());
		keyPoints.remove(0);
		return keyPoints;
	}

	@Override
	public Pair<Integer, Integer> getStartingDirection() {
		int initialX = map.getPath().get(0).getX().equals(map.getPath().get(1).getX()) ? 0 : 1;
		int initialY = map.getPath().get(0).getY().equals(map.getPath().get(1).getY()) ? 0 : 1;
		return new Pair<>(initialX, initialY);
	}

	@Override
	public Coordinates getSpawnPoint() {
		return map.getPath().get(0);
	}


}
