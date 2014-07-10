package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Ship;
import com.epam.training.domain.ShipType;

public class Torpedo {
	public static void main(String[] args) {
		Board board = new Board(5);
		
		Ship ship = new Ship(ShipType.FOUR_ELEMENT, 0, 0);
		
		System.out.println(ship.toString());
		
		initShips(board);
		
		board.printShipsOnBoard();
		
		board.shoot();
	}

	private static void initShips(Board board) {
//		board.createShips(ShipType.ONE_ELEMENT, 4);
//		board.createShips(ShipType.TWO_ELEMENT, 4);
//		board.createShips(ShipType.THREE_ELEMENT, 3);
		board.createShips(ShipType.FOUR_ELEMENT, 4);
//		board.createShips(ShipType.FOUR_ELEMENT_WITH_TOP, 3);
	}
}
