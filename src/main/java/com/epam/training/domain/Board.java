package com.epam.training.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

	private int width;

	private int height;

	private final List<Ship> ships;

	private final Random random = new Random();

	public Board(final int width, final int height) {
		this.width = width;
		this.height = height;
		ships = new ArrayList<>();
	}

	public void createShips(ShipType shipType) {
		int piece = shipType.getPiece();
		for (int i = 0; i < piece; i++) {
			placeShip(shipType);
		}
	}

	private void placeShip(ShipType shipType) {
		boolean isPlacedOnBoard = false;

		Ship ship = null;
		int safetyCounter = 0;
		while(!isPlacedOnBoard) {
			if (safetyCounter++ == 10) {
				throw new IllegalStateException("Ship placement failed!");
			}

			int positionX = random.nextInt(width);
			int positionY = random.nextInt(height);
			ship = shipType.createShip(positionX, positionY);

			isPlacedOnBoard = isOnBoard(ship);

			if (isPlacedOnBoard) {
				isPlacedOnBoard = !isShipAtTheSamePosition(ship);
			}
		}

		ships.add(ship);
	}

	private boolean isShipAtTheSamePosition(Ship ship) {
		for (int i = 0; i < ships.size(); i++) {
			if (ship.isOverLap(ships.get(i))) {
				return true;
			}
		}

		return false;
	}

	private boolean isOnBoard(Ship ship) {
		ShipElement lastElement = ship.getLastElement();
		boolean isHorizontalGood = 0 <= lastElement.getRelativePositionX() && lastElement.getRelativePositionX() < width;
		boolean isVerticalGood = 0 <= lastElement.getRelativePositionY() && lastElement.getRelativePositionY() < width;
		return isHorizontalGood && isVerticalGood;
	}

	public List<Ship> getShips() {
		return Collections.unmodifiableList(ships);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
