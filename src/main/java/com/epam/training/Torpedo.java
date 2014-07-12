package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipType;
import com.epam.training.domain.ShipTypeReader;

public class Torpedo {

	private static int[][] shipShape;

	public static void main(String[] args) {
		Board board = new Board(30, 30);

		try(ShipTypeReader shipTypeReader = new ShipTypeReader("ships.txt")) {
			while (shipTypeReader.hasNext()) {
				ShipType shipType = shipTypeReader.readShipType();
				board.createShips(shipType);
			}

			Engine engine = new Engine(board);

			engine.printShipsOnBoard();

			engine.shoot();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*private static void initShips(Board board) {
		board.createShips(ShipType.ONE_ELEMENT, 4);
		board.createShips(ShipType.TWO_ELEMENT, 4);
		board.createShips(ShipType.THREE_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT, 4);
		board.createShips(ShipType.FOUR_ELEMENT_WITH_TOP, 3);
	}*/
}
