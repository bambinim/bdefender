package com.bdefender.test.davide;

import com.bdefender.Pair;
import com.bdefender.enemies.EnemyBase;
import com.bdefender.enemies.EnemyFactory;
import com.bdefender.enemies.pool.EnemiesPoolImpl;
import com.bdefender.enemies.pool.EnemiesPoolMover;
import com.bdefender.enemies.view.EnemiesViewLoader;
import com.bdefender.map.Coordinates;
import com.bdefender.map.Map;
import com.bdefender.map.MapInteractorImpl;
import com.bdefender.map.MapLoader;
import com.bdefender.towers.TowerBase;
import com.bdefender.towers.TowerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Main extends Application {



	 public static void main(String[] args) {
	 	Application.launch();
	 }

	@Override
	public void start(Stage primaryStage) throws Exception {

		Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
		EnemiesPoolImpl pool = new EnemiesPoolImpl(new MapInteractorImpl(map));

		EnemyFactory eFactory = new EnemyFactory();

		pool.addEnemy(eFactory.getEnemy1(pool.getSpawnPoint()));
		pool.addEnemy(eFactory.getEnemy2(pool.getSpawnPoint()));

		TowerFactory tFactory = new TowerFactory();
		TowerBase tz1 = tFactory.getTowerZone1(pool, new Pair<>(5.0,5.0));
		TowerBase tz2 = tFactory.getTowerZone2(pool, new Pair<>(15.0,0.0));

		Thread eThread = new EnemiesThread(pool);
		Thread tThread1 = new TowerThread(tz1);
		Thread tThread2 = new TowerThread(tz2);

		eThread.start();
		//tThread1.start();
		//tThread2.start();

		primaryStage.setTitle("Map");
		Pane root = new Pane();
		root.setMaxWidth(1280);
		root.setMaxHeight(736);
		Canvas canvas = new Canvas(1280,1280);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Path path = new Path();
		path.getElements().add(new MoveTo(map.getPath().get(0).getLeftPixel(), map.getPath().get(0).getTopPixel()));
		for (int i = 0; i < map.getPath().size(); i++) {
			path.getElements().add(new LineTo(map.getPath().get(i).getLeftPixel(), map.getPath().get(i).getTopPixel()));
		}
		root.getChildren().addAll(map, path, canvas);
		primaryStage.setResizable(true);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		EnemyViewThread eViewThread = new EnemyViewThread(pool.getEnemies(),gc);
		eViewThread.start();
	}

	static class EnemyViewThread extends Thread {
	 	private List<EnemyBase> enemies;
	 	private GraphicsContext gc;

	 	public EnemyViewThread(List<EnemyBase> enemies, GraphicsContext gc){
			this.enemies = enemies;
			this.gc = gc;
		}


		@Override
		public void run(){
	 		while (true){
				try {
					sleep(10L);
					HashMap<EnemyBase, Optional<Image>> enemiesImage = EnemiesViewLoader.GetEnemiesImages(enemies);
					gc.clearRect(0, 0, 1280,1280);
					for(EnemyBase enemy : enemies){
						Coordinates enemyPos = new Coordinates(enemy.getPosition().getX() - 2, enemy.getPosition().getY() - 2);
						if(enemiesImage.get(enemy).isPresent()) {
							gc.drawImage(enemiesImage.get(enemy).get(), enemyPos.getCenterPixelX(), enemyPos.getCenterPixelY());
						}
					}

				} catch (InterruptedException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}

	}

	static class EnemiesThread extends Thread {
		 
		 private final EnemiesPoolMover mover;
		 
		 public EnemiesThread(EnemiesPoolMover pool) {
			 this.mover = pool;
		 }
		 
		 @Override
		 public void run(){
			 while(true){
				 try {
					 sleep(10L);
					 mover.moveEnemies();
				 } catch (InterruptedException ex) {
					 System.out.println(ex.getMessage());
				 }
			 }
		 }
	 }
	 
	 static class TowerThread extends Thread {
		 
		 private final TowerBase tower;
		 
		 public TowerThread(TowerBase tower){
			 this.tower = tower;
		 }
		 
		 @Override
		 public void run() {
			 while(true){
				 try {
					 sleep(1000L * tower.getShootSpeed());
					 if (tower.shoot().isEmpty()) {
						 System.out.println("No more enemies around...");
					 }
				 } catch (Exception ex) {
					 System.out.println(ex.getMessage());
				 }
			 }
		 }
		 
	 }

}
