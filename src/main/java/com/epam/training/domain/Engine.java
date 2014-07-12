package com.epam.training.domain;

import java.util.List;

public class Engine {
	private Board board;

	public Engine(Board board) {
		this.board = board;
	}

	public void shoot() {
		int width = board.getWidth();
		int height = board.getHeight();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				shootAt(j, i);
			}
		}
	}

	private void shootAt(int positionX, int positionY) {
		System.out.println("Shooting to position(" + positionX + "," + positionY + ")");

		Ship s = getShipByPosition(positionX, positionY);
		if (s != null) {
			s.setInjured(positionX, positionY);
			printShipsOnBoard();
		}
	}

	public void printShipsOnBoard() {
		int width = board.getWidth();
		int height = board.getHeight();
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

	private Ship getShipByPosition(int positionX, int positionY) {
		List<Ship> ships = board.getShips();
		Ship ship = null;
		for (Ship s : ships) {
			if (s.isShipElementAtPosition(positionX, positionY)) {
				ship = s;
			}
		}
		return ship;
	}
}
