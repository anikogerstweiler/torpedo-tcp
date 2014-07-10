package com.epam.training.domain;

public class ShipElement {

	private int relativePositionX;

	private int relativePositionY;
	
	private final String INTACT = "x";
	
	private final String INJURED = "o";

	private boolean isInjured;

	public ShipElement(int relativePositionX, int relativePositionY) {
		this.relativePositionX = relativePositionX;
		this.relativePositionY = relativePositionY;
		isInjured = false;
	}

	public boolean isInjured() {
		return isInjured;
	}

	public void setInjured(boolean isInjured) {
		this.isInjured = isInjured;
	}

	public int getRelativePositionX() {
		return relativePositionX;
	}

	public void setRelativePositionX(int relativePositionX) {
		this.relativePositionX = relativePositionX;
	}

	public int getRelativePositionY() {
		return relativePositionY;
	}

	public void setRelativePositionY(int relativePositionY) {
		this.relativePositionY = relativePositionY;
	}

	@Override
	public String toString() {
		return isInjured ? INTACT : INJURED;
	}

}
