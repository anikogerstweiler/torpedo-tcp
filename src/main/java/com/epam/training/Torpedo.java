package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipTypeReader;
import com.epam.training.domain.ShipType;

public class Torpedo {

	private static int[][] shipShape;

	public static void main(String[] args) {
		Board board = new Board(30);

		ShipTypeReader shipTypeReader = new ShipTypeReader("ships.txt");

		while (shipTypeReader.hasNext()) {
			ShipType shipType = shipTypeReader.readShipType();
		}

//		initShips(board);

		Engine engine = new Engine(board);

		engine.printShipsOnBoard();

		engine.shoot();
	}

	/*private static void initShips(Board board) {
		board.createShips(ShipType.ONE_ELEMENT, 4);
		board.createShips(ShipType.TWO_ELEMENT, 4);
		board.createShips(ShipType.THREE_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT_WITH_TOP, 3);
	}*/
}
