package com.bdefender.map;

import com.bdefender.Pair;

public class Coordinates extends Pair<Double, Double> {

	public Coordinates(Double x, Double y) {
		super(x, y);
	}

	/**
	 * Returns the number of the left pixel (X) of the coordinate square
	 * @return Pixel number
	 */
	public int getLeftPixel() {
		return (int) Math.round(super.getX() * 32);
	}

	/**
	 * Returns the number of the center pixel (X) of the coordinate square
	 * @return Pixel number
	 */
	public int getCenterPixelX() {
		return (int) Math.round(super.getX()) * 32 + 16;
	}

	/**
	 * Returns the number of the right pixel (X) of the coordinate square
	 * @return Pixel number
	 */
	public int getRightPixel() {
		return (int) Math.round(super.getX() * 32) + 32;
	}

	/**
	 * Returns the number of the top pixel (Y) of the coordinate square
	 * @return Pixel number
	 */
	public int getTopPixel() {
		return (int) Math.round(super.getY() * 32);
	}

	/**
	 * Returns the number of the center pixel (Y) of the coordinate square
	 * @return Pixel number
	 */
	public int getCenterPixelY() {
		return (int) Math.round(super.getY() * 32) + 16;
	}

	/**
	 * Returns the number of the bottom pixel (Y) of the coordinate square
	 * @return Pixel number
	 */
	public int getBottomPixel() {
		return (int) Math.round(super.getY() * 32) + 32;
	}
	
}
