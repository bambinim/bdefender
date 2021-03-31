package com.bdefender.towers.controllers;
import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EnemyControllerZoneImpl implements EnemyControllerZone {
	
	private final EnemiesPoolInteractor enemiesPool ;
	
	public EnemyControllerZoneImpl(EnemiesPoolInteractor enemiesPool) {
		this.enemiesPool = enemiesPool;
	}

	//nonostante sia codice ripetuto non voglio raggrupparlo dato che per adesso sono uguali ma potrebbero cambiare per motivi diversi
	//quando farò refactoring userò strategy però
	@Override
	public Map<Integer, Pair<Double, Double>> getEnemiesInZone(double radius, Pair<Double, Double> center) {
		return IntStream.range(0, this.enemiesPool.getEnemies().size()).boxed()
				//creo una mappa con key = indice e value = nemico corrispondente al valore
				.collect(Collectors.toMap(i -> i,i -> this.enemiesPool.getEnemies().get(i))).entrySet().stream()
				//controllo che la distanza dal centro sia minore dell raggio di azione
				.filter(e -> Math.hypot(e.getValue().getPosition().getY() - center.getY(),e.getValue().getPosition().getX() - center.getX()) <= radius)
				//creo una mappa con key = indice e value = posizione del nemico corrispondente all'indice
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getPosition()));
	}

	@Override
	public Set<Integer> applyDamageByZone(double radius, Pair<Double, Double> center, Double damage) {
		Set<Integer> enemiesId = getEnemiesInZone(radius,center).entrySet().stream().map(e -> e.getKey()).collect(Collectors.toSet());
		for (Integer enemyId : enemiesId){
			enemiesPool.applyDamageById(enemyId, damage);
		}
		return enemiesId;
	}

}
