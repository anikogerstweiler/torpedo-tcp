package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;


public class Ship {

	private ShipType shipType;

	private int absolutePositionX;

	private int absolutePositionY;

	private List<ShipElement> shipElements;

	public Ship(ShipType shipType, int absolutePositionX, int absolutePositionY) {
		this.shipType = shipType;
		this.absolutePositionX = absolutePositionX;
		this.absolutePositionY = absolutePositionY;
		shipElements = new ArrayList<>();
		createShipByType(shipType);
	}

	public void setInjured(int positionX, int positionY) {
		getElementByPosition(positionX, positionY).setInjured(true);
	}

	private ShipElement getElementByPosition(int positionX, int positionY) {
		boolean found = false;

		int i = 0;
		ShipElement element = null;
		while (i < shipElements.size() || found != true) {
			element = getShipElementByPosition(positionX, positionY);
			found = (element != null);
			i++;
		}

		return element;
	}

	private ShipElement getShipElementByPosition(int positionX, int positionY) {
		ShipElement element = null;
		for (ShipElement e : shipElements) {
			if (e.getRelativePositionX() == positionX
					&& e.getRelativePositionY() == positionY) {
				element = e;
			}
		}

		return element;
	}

	public boolean isOverLap(Ship ship) {
		boolean isOverLap = false;
		int i = 0;
		int j = 0;
		while (!isOverLap && (i < ship.shipElements.size() || j < shipElements.size())) {
			ShipElement first = ship.shipElements.get(i);
			ShipElement second = shipElements.get(j);
			isOverLap = (first.getRelativePositionX() == second.getRelativePositionX()
					&& first.getRelativePositionY() == second.getRelativePositionY());
			i++;
			j++;
		}

		return isOverLap;
	}

	private void createShipByType(ShipType shipType) {
		switch (shipType) {
		case ONE_ELEMENT:
			shipElements.add(new ShipElement(absolutePositionX, absolutePositionY));
			break;

		case TWO_ELEMENT:
			shipElements.add(new ShipElement(absolutePositionX, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 1, absolutePositionY));
			break;

		case THREE_ELEMENT:
			shipElements.add(new ShipElement(absolutePositionX, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 1, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 2, absolutePositionY));
			break;

		case FOUR_ELEMENT:
			shipElements.add(new ShipElement(absolutePositionX, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 1, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 2, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX + 3, absolutePositionY));
			break;
		case FOUR_ELEMENT_WITH_TOP:
			shipElements.add(new ShipElement(absolutePositionX + 1, absolutePositionY));
			shipElements.add(new ShipElement(absolutePositionX, absolutePositionY + 1));
			shipElements.add(new ShipElement(absolutePositionX + 1, absolutePositionY + 1));
			shipElements.add(new ShipElement(absolutePositionX + 2, absolutePositionY + 1));
			break;
		}
	}

	public void printShip() {
		for (ShipElement element : shipElements) {
			System.out.print(element.toString());
		}
	}


}
