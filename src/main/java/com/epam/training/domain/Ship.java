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
		ShipElement element = null;
		for (ShipElement e : shipElements) {
			if (isTwoElementAreEquals(positionX, positionY, e)) {
				element = e;
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

	//WHY IS IT NEED TO BE SWAP??
	private boolean isTwoElementAreEquals(int positionX, int positionY, ShipElement element) {
		return element.getRelativePositionX() == positionY && element.getRelativePositionY() == positionX;
	}
	
	public int getAbsolutePositionX() {
		return absolutePositionX;
	}

	public void setAbsolutePositionX(int absolutePositionX) {
		this.absolutePositionX = absolutePositionX;
	}

	public int getAbsolutePositionY() {
		return absolutePositionY;
	}

	public void setAbsolutePositionY(int absolutePositionY) {
		this.absolutePositionY = absolutePositionY;
	}
	
	public boolean isOverLap(Ship ship) {
		boolean isOverLap = false;
			
		for (int i = 0; i < ship.shipElements.size() && !isOverLap; i++) {
			for (int j = 0; j < shipElements.size() && !isOverLap; j++) {
				ShipElement first = ship.shipElements.get(i);
				ShipElement second = shipElements.get(j);
				
				isOverLap = (isTheSameAtPositionX(first, second)
						&& isTheSameAtPositionY(first, second));
			}		
		}

		return isOverLap;
	}

	private boolean isTheSameAtPositionY(ShipElement first, ShipElement second) {
		return first.getRelativePositionY() == second.getRelativePositionY();
	}

	private boolean isTheSameAtPositionX(ShipElement first, ShipElement second) {
		return first.getRelativePositionX() == second.getRelativePositionX();
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
	
	public List<ShipElement> getShipElements() {
		return shipElements;
	}

	public void printShip(int positionX, int positionY) {
		int index = 0;
		while (index < shipElements.size()) {
			ShipElement actual = shipElements.get(index); 
			if (isTwoElementAreEquals(positionY, positionX, actual)) {
				System.out.print(actual.toString());
			}
			index++;
		}
	}
	
	public ShipElement getLastElement() {
		return shipElements.get(shipElements.size() - 1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ShipElement e : shipElements) {
			builder.append("x: " + e.getRelativePositionX() + ", y: " + e.getRelativePositionY()).append("\n");
		}
		return builder.toString();
	}
}
