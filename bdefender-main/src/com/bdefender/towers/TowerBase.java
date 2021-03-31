package com.bdefender.towers;

import java.util.Set;

public interface TowerBase {
	
	public class NoEnemiesAroundException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public NoEnemiesAroundException(String errorMessage) {
			super(errorMessage);
		}
	}

	public Set<Integer> shoot();
	
	public void upgradeLevel();
	
	public long getShootSpeed();
}
