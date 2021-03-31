package com.bdefender.map;

import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Map extends ImageView {
	
	public final static int COUNTRYSIDE = 0;
	public final static int SNOW = 1;
	
	private final List<Coordinates> path;
	
	protected Map(Image mapImage, List<Coordinates> path) {
		super(mapImage);
		this.path = path;
	}
	
	public List<Coordinates> getPath() {
		return Collections.unmodifiableList(this.path);
	}

}
