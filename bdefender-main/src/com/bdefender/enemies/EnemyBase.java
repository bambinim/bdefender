package com.bdefender.enemies;

import com.bdefender.Pair;

public interface EnemyBase {

	public Pair<Double, Double> getPosition();
	
	public void takeDamage(Double damage);
	
	public boolean isAlive();
	
	public void moveTo(Pair<Double, Double> pos);
	
	public double getSpeed();
	
	public Pair<Integer, Integer> getDirection();
	
	public void setDirection(Pair<Integer, Integer> dir);
	
	public double getLife();

	public Integer getTypeId();
	
}
