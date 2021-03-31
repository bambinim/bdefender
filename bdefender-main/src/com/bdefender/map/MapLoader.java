package com.bdefender.map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class MapLoader {
	
	private final static String MAP_BASE_PATH = "res/maps";	
	private static MapLoader mapLoader = new MapLoader();
	
	private MapLoader() {}
	
	public static MapLoader getInstance() {
		return MapLoader.mapLoader;
	}
	
	public Map loadMap(int map) {
		Map res = new Map(this.loadMapImage(String.format("%s/%d/map.png", MAP_BASE_PATH, map)),
				this.loadPath(String.format("%s/%d/path.txt", MAP_BASE_PATH, map)));
		return res;
	}
	
	private Image loadMapImage(String imagePath) {
		Image mapImage;
		try {
			mapImage = new Image(new FileInputStream(imagePath));
		} catch (Exception e) {
			mapImage = null;
		}
		return mapImage;
	}
	
	@SuppressWarnings("resource")
	private List<Coordinates> loadPath(String coordsFilePath) {
		List<Coordinates> path = new ArrayList<Coordinates>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(coordsFilePath));
			String line;
			while ((line = reader.readLine()) != null) {
				path.add(this.parsePathLine(line));
			}
		} catch (Exception e) {
			path = List.of();
		}
		return path;
	}
	
	private Coordinates parsePathLine(String line) {
		String[] tmp = line.split("\\|");
		return new Coordinates(Double.valueOf(tmp[0]), Double.valueOf(tmp[1]));
	}
}
