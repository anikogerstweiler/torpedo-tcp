package com.epam.training;

import com.epam.training.domain.Board;
import com.epam.training.domain.Engine;
import com.epam.training.domain.ShipType;
import com.epam.training.domain.ShipTypeReader;

public class Torpedo {

	public static void main(String[] args) {
		Board board = new Board(30, 30);
		Engine engine = new Engine(board);

		try(ShipTypeReader shipTypeReader = new ShipTypeReader("ships.txt")) {
			while (shipTypeReader.hasNext()) {
				ShipType shipType = shipTypeReader.readShipType();
				board.createShips(shipType);
			}

			engine.printShipsOnBoard();

			engine.shoot();
		} catch (Exception e) {
		}
	}
}
