package com.epam.training.domain;

import java.util.List;

public class Ship {

	private List<ShipElement> shipElements;

	public Ship(List<ShipElement> shipElements) {
		this.shipElements = shipElements;
	}

	public boolean isInArea(int width, int height) {
		boolean isInArea = true;
		for (ShipElement element : shipElements) {
			isInArea = element.getRelativePositionX() < width && element.getRelativePositionY() < height;
		}

		return isInArea;
	}

	public boolean setInjured(int positionX, int positionY) {
		ShipElement element = getElementByPosition(positionX, positionY);

		return element == null || element.injure();
	}

	public boolean isSunk() {
		boolean isSunk = true;
		for (ShipElement element : shipElements) {
			isSunk = isSunk && element.isInjured();
		}
		return isSunk;
	}

	private ShipElement getElementByPosition(int positionX, int positionY) {
		ShipElement element = null;
		for (ShipElement e : shipElements) {
			if (e.getRelativePositionX() == positionX & e.getRelativePositionY() == positionY) {
				return e;
			}
		}
		return element;
	}

	public boolean isShipElementAtPosition(int positionX, int positionY) {
		boolean isElementAtPosition = false;
		int index = 0;
		while(index < shipElements.size() && !isElementAtPosition) {
			ShipElement element = shipElements.get(index);
			isElementAtPosition = isTwoElementAreEquals(positionX, positionY, element);
			index++;
		}

		return isElementAtPosition;
	}

	private boolean isTwoElementAreEquals(int positionX, int positionY, ShipElement element) {
		return element.getRelativePositionX() == positionX && element.getRelativePositionY() == positionY;
	}

	public boolean isOverLap(Ship ship) {
		boolean isOverLap = false;

		for (int i = 0; i < ship.shipElements.size() && !isOverLap; i++) {
			for (int j = 0; j < shipElements.size() && !isOverLap; j++) {
				ShipElement firstElement = ship.shipElements.get(i);
				ShipElement secondElement = shipElements.get(j);

				isOverLap = (isTheSameByPositionX(firstElement, secondElement)
						&& isTheSameByPositionY(firstElement, secondElement));
			}
		}

		return isOverLap;
	}

	private boolean isTheSameByPositionY(ShipElement firstElement, ShipElement secondElement) {
		return firstElement.getRelativePositionY() == secondElement.getRelativePositionY();
	}

	private boolean isTheSameByPositionX(ShipElement firstElement, ShipElement secondElement) {
		return firstElement.getRelativePositionX() == secondElement.getRelativePositionX();
	}

	public void printShip(int positionX, int positionY) {
		int index = 0;
		while (index < shipElements.size()) {
			ShipElement actual = shipElements.get(index);
			if (isTwoElementAreEquals(positionX, positionY, actual)) {
				System.out.print(actual.toString());
			}
			index++;
		}
	}
}
