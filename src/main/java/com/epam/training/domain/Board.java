package com.epam.training.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.epam.training.message.Fire;
import com.epam.training.message.FireAnswer;
import com.epam.training.message.Hit;
import com.epam.training.message.Miss;
import com.epam.training.message.Sunk;

public class Board {

	private int width;

	private int height;

	private final List<Ship> ships;

	private int shipElementCount;

	private final Random random = new Random();

	private Placeholder[][] board;

	public Board(final int width, final int height) {
		this.width = width;
		this.height = height;
		ships = new ArrayList<>();
		board = new Placeholder[width][height];
		initBoard();
	}

	private void initBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[j][i] = new Water();
			}
		}

	}

	public void createShips(ShipType shipType) {
		int piece = shipType.getPiece();
		for (int i = 0; i < piece; i++) {
			placeShip(shipType);
			//piece??
			shipElementCount += shipType.countShipElements();
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

			//mod
			isPlacedOnBoard = ship.isInArea(width, height);

			if (isPlacedOnBoard) {
				isPlacedOnBoard = !isShipAtTheSamePosition(ship);
			}
		}
		addToBoard(ship);
		ships.add(ship);
	}

	private void addToBoard(Ship ship) {
		for (ShipElement element : ship.getShipElements()) {
			board[element.getRelativePositionX()][element.getRelativePositionY()] = element;
		}
	}

	private boolean isShipAtTheSamePosition(Ship ship) {
		for (int i = 0; i < ships.size(); i++) {
			if (ship.isOverLap(ships.get(i))) {
				return true;
			}
		}

		return false;
	}

	public FireAnswer process(Fire fire) {
		int coordinateX = fire.getCoordinateX();
		int coordinateY = fire.getCoordinateY();

		Ship ship = getShipByPosition(coordinateX, coordinateY);

		if (ship == null) {
			board[coordinateX][coordinateY] = new MissPlaceholder();
			return new Miss();
		}

		boolean injured = ship.setInjured(coordinateX, coordinateY);
		if (injured) {
			shipElementCount--;

			if (ship.isSunk()) {
				return new Sunk();
			}

			return new Hit();
		}

		return new Miss();
	}

	public boolean isLost() {
		return shipElementCount == 0;
	}

	public void print() {
		for (int i = 0; i < height ; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(board[j][i]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private Ship getShipByPosition(int positionX, int positionY) {
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

	public int getShipCount() {
		return shipElementCount;
	}
}
