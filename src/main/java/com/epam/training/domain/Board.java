package com.epam.training.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

	private int size;

	private List<Ship> ships;

	public Board(final int size) {
		this.size = size;
		ships = new ArrayList<>();
	}

	public void createShips(ShipType shipType, int piece) {
		int counter = 0;
		while (counter != piece) {
			boolean isPlacedOnBoard = placeShip(shipType);
			
			if (isPlacedOnBoard) {
				counter++;
			}
		}
	}

	private boolean placeShip(ShipType shipType) {
		boolean isPlacedOnBoard = false;

		while(!isPlacedOnBoard) {
			int positionX = generateRandomIndex(size);
			int positionY = generateRandomIndex(size);
			Ship ship = createShip(shipType, positionX, positionY);
			
			ship = checkIsOnBoard(shipType, ship);

			if (shipIsEmpty()) {
				ships.add(ship);
				isPlacedOnBoard = true;
			} else {
				isPlacedOnBoard = checkIfExistShipOnTheSamePositionAs(ship);
				
				addToShips(isPlacedOnBoard, ship);
			} 
		}
		
		return isPlacedOnBoard;
	}

	private void addToShips(boolean isPlacedOnBoard, Ship ship) {
		if(isPlacedOnBoard) {
			ships.add(ship);
		}
	}

	private boolean checkIfExistShipOnTheSamePositionAs(Ship ship) {
		int index = 0;
		boolean foundSimilar = false;
		boolean placedOnBoard = false;
		while(index < ships.size() && !foundSimilar) {
			Ship s = ships.get(index);
			foundSimilar = ship.isOverLap(s);
			index++;
			placedOnBoard = !foundSimilar;
		}
		return placedOnBoard;
	}

	private Ship checkIsOnBoard(ShipType shipType, Ship ship) {
		int positionX;
		int positionY;
		while (!isOnBoard(ship)) {
			positionX = generateRandomIndex(size);
			positionY = generateRandomIndex(size);
			ship = createShip(shipType, positionX, positionY);
		}
		return ship;
	}

	private Ship createShip(ShipType shipType, int positionX, int positionY) {
		return new Ship(shipType, positionX, positionY);
	}

	private int generateRandomIndex(int size) {
		Random random = new Random();
		
		return random.nextInt(size);
	}

	private boolean shipIsEmpty() {
		return ships.isEmpty();
	}

	private boolean isOnBoard(Ship ship) {
		ShipElement lastElement = ship.getLastElement(); 
		return lastElement.getRelativePositionY() < size && lastElement.getRelativePositionX() < size;
	}
	
	public int getSize() {
		return size;
	}

	public List<Ship> getShips() {
		return Collections.unmodifiableList(ships);
	}
}
