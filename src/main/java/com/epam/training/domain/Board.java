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

			isPlacedOnBoard = ship.isInArea(width, height);

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

	public void printShips() {
		for (int i = 0; i < height ; i++) {
			for (int j = 0; j < width; j++) {
				Ship s = getShipByPosition(j, i);
				if (s == null) {
					System.out.print(new Water().toString());
				} else {
					s.printShip(j, i);
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public Ship getShipByPosition(int positionX, int positionY) {
		Ship ship = null;
		for (Ship s : ships) {
			if (s.isShipElementAtPosition(positionX, positionY)) {
				ship = s;
			}
		}
		return ship;
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
