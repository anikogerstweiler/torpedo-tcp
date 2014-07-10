package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Ship;
import com.epam.training.domain.ShipType;

public class Torpedo {
	public static void main(String[] args) {
		Board board = new Board();

		board.createShips(ShipType.ONE_ELEMENT, 2);

		board.printShips();

		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);

		Ship other = new Ship(ShipType.TWO_ELEMENT, 0, 0);

		Ship third = new Ship(ShipType.THREE_ELEMENT, 1, 1);

		System.out.println(ship.isOverLap(ship));
		System.out.println(ship.isOverLap(other));
		System.out.println(other.isOverLap(third));
	}
}
