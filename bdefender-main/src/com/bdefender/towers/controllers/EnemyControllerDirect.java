package com.bdefender.towers.controllers;

import com.bdefender.towers.controllers.EnemyController;

public interface EnemyControllerDirect extends EnemyController {
	
	public void applyDamageById(Integer id, Double damage);
	
}
