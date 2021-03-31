package com.bdefender.enemies;


import com.bdefender.Pair;

public class EnemyFactory {
	
	public EnemyBase getEnemy1(Pair<Double, Double> pos) {
		return this.enemyFromParams(20.0, 35.0, 30.0, pos, 0);
	}
	
	public EnemyBase getEnemy2(Pair<Double, Double> pos) {
		return this.enemyFromParams(15.0, 50.0, 25.0, pos, 1);
	}
	
	private EnemyBase enemyFromParams(Double life, Double speed, Double damage, Pair<Double, Double> pos, Integer typeId) {
		return new EnemyBase() {
			
			Pair<Double, Double> enemyPos = pos;
			double enemyLife = life;
			Pair<Integer, Integer> enemyDirection = new Pair<>(0, 0); 
			
			@Override
			public Pair<Double, Double> getPosition() {
				return enemyPos;
			}

			@Override
			public void takeDamage(Double damage) {
				enemyLife -= damage; 		
			}

			@Override
			public boolean isAlive() {
				return enemyLife > 0;
			}

			@Override
			public void moveTo(Pair<Double, Double> newPos) {
				enemyPos = newPos;
			}

			@Override
			public double getSpeed() {
				return speed;
			}

			@Override
			public double getLife() {
				return this.enemyLife;
			}

			@Override
			public Integer getTypeId() {
				return typeId;
			}

			@Override
			public Pair<Integer, Integer> getDirection() {
				return this.enemyDirection;
			}

			@Override
			public void setDirection(Pair<Integer, Integer> dir) {
				this.enemyDirection = dir;				
			}
			
		};
	}
	
}
