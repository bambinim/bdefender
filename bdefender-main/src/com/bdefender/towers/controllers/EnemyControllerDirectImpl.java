package com.bdefender.towers.controllers;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.bdefender.Pair;
import com.bdefender.enemies.pool.EnemiesPoolInteractor;
import com.bdefender.towers.controllers.EnemyControllerDirect;

public class EnemyControllerDirectImpl implements EnemyControllerDirect {
	
	private final EnemiesPoolInteractor enemiesPool ;
	
	public EnemyControllerDirectImpl(EnemiesPoolInteractor enemiesPool) {
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
				.filter(e -> Math.hypot(center.getY() - e.getValue().getPosition().getY(), center.getX() - e.getValue().getPosition().getX()) <= radius)
				//creo una mappa con key = indice e value = posizione del nemico corrispondente all'indice
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getPosition()));
	}

	@Override
	public void applyDamageById(Integer id, Double damage) {
		this.enemiesPool.applyDamageById(id, damage);
	}

}
