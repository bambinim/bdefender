package com.bdefender.towers;
import java.util.Map;
import java.util.Set;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.towers.controllers.EnemyControllerDirect;
import com.bdefender.towers.controllers.EnemyControllerDirectImpl;
import com.bdefender.towers.controllers.EnemyControllerZone;
import com.bdefender.towers.controllers.EnemyControllerZoneImpl;


public class TowerFactory {
	
	public TowerBase getTowerZone1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerZoneByParams(3.0, 5.0, 15.0, 2l, pool, pos);
	}
	
	public TowerBase getTowerZone2(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerZoneByParams(5.0, 4.0, 10.0, 3l, pool, pos);
	}
	
	public TowerBase getTowerDirect1(final EnemiesPoolInteractor pool, final Pair<Double, Double> pos) {
		return this.towerDirectByParams(15.0, 15.0, 1l, pool, pos);
	}
	
	private TowerBase towerZoneByParams(Double damage, Double damageAreaRadius, Double rangeRadius, Long shootSpeed, EnemiesPoolInteractor pool, Pair<Double, Double> pos) {
		return new TowerBase() {

			EnemyControllerZone enemiesCtrl = new EnemyControllerZoneImpl(pool);
			
			@Override
			public Set<Integer> shoot() {
				try {
					return this.enemiesCtrl.applyDamageByZone(damageAreaRadius, getOptimalTarget().getY(), damage);
				} catch (NoEnemiesAroundException ex){
					return Set.of();
				}
			}

			@Override
			public void upgradeLevel() {
				// TODO			
			}

			public Pair<Integer, Pair<Double, Double>> getOptimalTarget() throws NoEnemiesAroundException {
								
				Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);
				
				if (enemiesInRange.isEmpty()) {
					throw new NoEnemiesAroundException("No enemies around this tower");
				}
				
				Pair<Integer, Integer> mostEnemiesAround = new Pair<>(0, 0); // la prima cifra(X) rappresenta l'id, la seconda(Y) i nemici che ha attorno

				//partendo dal presupposto che non è vuota assegno un id casuale e un numero di nemici intorno che è zero
				//perciò dovrebbe cambiare l'id con la prima entry dato che la condizione è di avere un numero di nemici maggiori o uguali
				
				//enemy.getValue() è sempre la posizione
				//enemy.getKey() è sempre l'id
 				for ( var enemy : enemiesInRange.entrySet()) {
					int enemiesAround = this.enemiesCtrl.getEnemiesInZone(damageAreaRadius, enemy.getValue()).size();
					if ( mostEnemiesAround.getY() <= enemiesAround ) {
						mostEnemiesAround = new Pair<>(enemy.getKey(), enemiesAround);
					}
				}
				
				return new Pair<>(mostEnemiesAround.getX(), enemiesInRange.get(mostEnemiesAround.getX()));
			}

			@Override
			public long getShootSpeed() {
				return shootSpeed;
			}
			
		};
	}
	
	private TowerBase towerDirectByParams(Double damage, Double rangeRadius, Long shootSpeed, EnemiesPoolInteractor pool, Pair<Double, Double> pos) {
		return new TowerBase() {
			
			EnemyControllerDirect enemiesCtrl = new EnemyControllerDirectImpl(pool);

			@Override
			public Set<Integer> shoot() {
				try {
					int targetId = this.getOptimalTarget();
					this.enemiesCtrl.applyDamageById(this.getOptimalTarget(), damage);
					return Set.of(targetId);
				} catch (NoEnemiesAroundException ex) {
					return Set.of();
				}
			}

			@Override
			public void upgradeLevel() {
				// TODO Auto-generated method stub
				
			}

			public Integer getOptimalTarget() throws NoEnemiesAroundException {
				Map<Integer, Pair<Double, Double>> enemiesInRange = this.enemiesCtrl.getEnemiesInZone(rangeRadius, pos);
				
				if (enemiesInRange.isEmpty()) {
					throw new NoEnemiesAroundException("No enemies around this tower");
				}
				
				Pair<Integer, Double> closerEnemy = new Pair<>(0, rangeRadius + 1);
				
				for ( var enemy : enemiesInRange.entrySet()) {
					double distance = Math.sqrt(Math.pow(enemy.getValue().getX() - pos.getX(), 2) + Math.pow(enemy.getValue().getY() - pos.getY(), 2));
					if (distance <= closerEnemy.getY() ) {
						closerEnemy = new Pair<>(enemy.getKey(), distance);
					}
				}
				
				return closerEnemy.getX();
			}

			@Override
			public long getShootSpeed() {
				return shootSpeed;
			}
			
		};
	}
	
}
