package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipType;
import com.epam.training.domain.ShipTypeReader;

public class Torpedo {

	private static final String INPUT_FILE = "ships.txt";

	private static final int BOARD_HEIGHT = 30;

	private static final int BOARD_WIDTH = 30;

	public static void main(String[] args) {
		Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		Engine engine = new Engine(board);

		try(ShipTypeReader shipTypeReader = new ShipTypeReader(INPUT_FILE)) {
			while (shipTypeReader.hasNext()) {
				ShipType shipType = shipTypeReader.readShipType();
				board.createShips(shipType);
			}

			//engine.printShips();
			board.printShips();

			engine.shoot();
		} catch (Exception e) {
		}
	}
}
