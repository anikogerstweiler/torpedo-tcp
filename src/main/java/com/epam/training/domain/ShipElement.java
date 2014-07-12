package com.epam.training.domain;

public class ShipElement {

	private static final String INTACT = "x";

	private static final String INJURED = "o";

	private int relativePositionX;

	private int relativePositionY;

	private boolean isInjured;

	public ShipElement(int relativePositionX, int relativePositionY) {
		this.relativePositionX = relativePositionX;
		this.relativePositionY = relativePositionY;
		isInjured = false;
	}

	public void setInjured(boolean isInjured) {
		this.isInjured = isInjured;
	}

	public int getRelativePositionX() {
		return relativePositionX;
	}

	public int getRelativePositionY() {
		return relativePositionY;
	}

	@Override
	public String toString() {
		return isInjured ? INTACT : INJURED;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isInjured ? 1231 : 1237);
		result = prime * result + relativePositionX;
		result = prime * result + relativePositionY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipElement other = (ShipElement) obj;
		if (isInjured != other.isInjured)
			return false;
		if (relativePositionX != other.relativePositionX)
			return false;
		if (relativePositionY != other.relativePositionY)
			return false;
		return true;
	}
}
