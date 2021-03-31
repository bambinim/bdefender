package com.bdefender.test.map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.animation.PathTransition;
import javafx.scene.shape.Path;
import com.bdefender.map.*;

public class MapTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Map");
		Map map = MapLoader.getInstance().loadMap(Map.COUNTRYSIDE);
		Circle circle = new Circle();
		Pane root = new Pane();
		root.setMaxWidth(1280);
		root.setMaxHeight(736);
		circle.setRadius(10.0);
		circle.setCenterX(map.getPath().get(0).getX() * 32);
		circle.setCenterY(map.getPath().get(0).getY() * 32);
		// CREATE TRANSITION
		Path path = new Path();
		path.getElements().add(new MoveTo(map.getPath().get(0).getLeftPixel(), map.getPath().get(0).getTopPixel()));
		for (int i = 0; i < map.getPath().size(); i++) {
			path.getElements().add(new LineTo(map.getPath().get(i).getLeftPixel(), map.getPath().get(i).getTopPixel()));
		}
		PathTransition transition = new PathTransition();
		transition.setPath(path);
		transition.setDuration(Duration.seconds(10));
		transition.setNode(circle);
		transition.setAutoReverse(false);
		transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transition.setCycleCount(1);
		transition.play();
		// END CREATE TRANSITION
		//root.getChildren().addAll(map, path, circle);
		root.getChildren().addAll(map, path ,circle);
		primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
