package com.bdefender.enemies.pool;
import com.bdefender.Pair;
import com.bdefender.enemies.EnemyBase;
import com.bdefender.map.Coordinates;

import java.util.ArrayList;



public class EnemiesPoolImpl implements EnemiesPoolInteractor, EnemiesPoolMover, EnemiesPoolSpawner  {
	
	private final ArrayList<EnemyBase> enemies = new ArrayList<>();
	private final MapInteractor mapInteractor;
	
	public EnemiesPoolImpl(MapInteractor mapInteractor) {
		this.mapInteractor = mapInteractor;
	}
	
	@Override
	public ArrayList<EnemyBase> getEnemies(){
		return this.enemies;
	}
	
	@Override
	public void addEnemy(EnemyBase enemy) {
		enemy.setDirection(this.mapInteractor.getStartingDirection());
		enemies.add(enemy);
	}

	@Override
	public Coordinates getSpawnPoint() {
		return mapInteractor.getSpawnPoint();
	}

	@Override
	public void applyDamageById(final int id, Double damage) {
		enemies.get(id).takeDamage(damage);
		System.out.println("Enemy " + id + " got damaged");
		if (!enemies.get(id).isAlive()) {
			this.enemies.remove(id);
			System.out.println("Enemy " + id + " died");
		}
	}

	private Pair<Double, Double> getNextPos(Pair<Integer, Integer> dir, Pair<Double, Double> currPos, Pair<Double, Double> distance){
		double newX = currPos.getX() + distance.getX() * dir.getX();
		double newY = currPos.getY() + distance.getY() * dir.getY();
		return new Pair<>(newX, newY);
	}



	private boolean isAfterKeyPoint(Pair<Double, Double> p1, Pair<Double, Double> p2, Pair<Integer, Integer> dir){
		return (((p1.getX() - p2.getX())  > 0 && dir.getX() == 1) || (p1.getX().equals(p2.getX()) && dir.getX() == 0 )) && (p1.getY() - p2.getY()) * dir.getY() >= 0;
	}

	private boolean keyPointIsAfter(Pair<Double, Double> p1, Pair<Double, Double> p2, Pair<Integer, Integer> dir){
		return (p1.getX() >= p2.getX()) && (p1.getY() - p2.getY()) * dir.getY() >= 0;
	}

	@Override
	public void moveEnemies() {
		//io suppongo che, per adesso, ci si possa muovere solo da destra a sinistra
		for(int c = 0; c < enemies.size(); c++) {
			EnemyBase enemy = enemies.get(c);
			Pair<Integer, Integer> dir = enemy.getDirection();
			Pair<Double, Double> currPos = enemy.getPosition();
			Pair<Double, Double> nxtPos = getNextPos(dir, currPos, new Pair<>(enemy.getSpeed() / 1000, enemy.getSpeed() / 1000));
			ArrayList<Pair<Double, Double>> keyPoints = new ArrayList<>(this.mapInteractor.getKeyPoints());
			boolean dirChange = false;
			for (Pair<Double, Double> keyPoint : keyPoints){
				if(keyPointIsAfter(keyPoint,currPos,dir) && isAfterKeyPoint(nxtPos,keyPoint,dir)) {
					int nextXDir = dir.getX() == 0 ? 1 : 0 ;
					int nextYDir = 0;
					if (keyPoints.indexOf(keyPoint) + 1 == keyPoints.size()) {
						System.out.println("Enemy " + c + " Reached the end");
						this.enemies.remove(c);
						//applicare danno al giocatore
					} else {
						Double nextKeyPointY = keyPoints.get(keyPoints.indexOf(keyPoint) + 1).getY();
						if (!nextKeyPointY.equals(keyPoint.getY())) {
							nextYDir = nextKeyPointY > keyPoint.getY() ? 1 : -1;
						}
						enemy.setDirection(new Pair<>(nextXDir, nextYDir));
						enemy.moveTo(getNextPos(enemy.getDirection(), keyPoint, new Pair<>(Math.abs(nxtPos.getY() - keyPoint.getY()), Math.abs(nxtPos.getX() - keyPoint.getX()))));
						System.out.println("Enemy " + c + " changed direction at " + currPos.toString());
					}
					dirChange = true;
					break;
				}
			}
			if(!dirChange){
				enemy.moveTo(nxtPos);
				System.out.println("Moving on enemy " + c);
			}
		}
	}

}
