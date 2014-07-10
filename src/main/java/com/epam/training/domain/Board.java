package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private static final int SIZE = 29;

	private List<Ship> ships;

	public Board() {
		ships = new ArrayList<>();
	}

	public void createShips(ShipType shipType, int piece) {
		int counter = 0;
		boolean placed = false;
		while (counter < piece && !placed) {
			placed = placeShip(shipType);
			if (placed) {
				counter++;
			}
		}
	}

	private boolean placeShip(ShipType shipType) {
		boolean placed = false;

		int positionX = (int) (Math.random() * SIZE);
		int positionY = (int) (Math.random() * SIZE);

		Ship ship = new Ship(shipType, positionX, positionY);

		for (Ship s : ships) {
			if (!s.isOverLap(ship)) {
				ships.add(ship);
				placed = true;
			}
		}

		return placed;
	}

	public void printShips() {
		for (Ship ship : ships) {
			ship.toString();
		}
	}
}
