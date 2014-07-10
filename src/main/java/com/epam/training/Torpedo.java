package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipType;

public class Torpedo {
	public static void main(String[] args) {
		Board board = new Board(30);

		initShips(board);

		Engine engine = new Engine(board);

		engine.printShipsOnBoard();

		engine.shoot();
	}

	private static void initShips(Board board) {
		board.createShips(ShipType.ONE_ELEMENT, 4);
		board.createShips(ShipType.TWO_ELEMENT, 4);
		board.createShips(ShipType.THREE_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT_WITH_TOP, 3);
	}
}
