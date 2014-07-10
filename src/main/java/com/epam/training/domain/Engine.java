package com.epam.training.domain;

import java.util.List;

public class Engine {
	private Board board;
	
	public Engine(Board board) {
		this.board = board;
	}
	
	public void shoot() {
		int size = board.getSize();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				shootAt(i, j); 
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
		int size = board.getSize();
		for (int i = 0; i < size ; i++) {
			for (int j = 0; j < size; j++) {
				Ship s = getShipByPosition(i, j);
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
