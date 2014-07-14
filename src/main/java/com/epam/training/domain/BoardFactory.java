package com.epam.training.domain;

public class BoardFactory {

	private String inputFile;

	public BoardFactory(String inputFile) {
		this.inputFile = inputFile;
	}

	public Board create(int width, int height) {
		Board board = new Board(width, height);
		try(ShipTypeReader shipTypeReader = new ShipTypeReader(inputFile)) {
			while (shipTypeReader.hasNext()) {
				ShipType shipType = shipTypeReader.readShipType();
				board.createShips(shipType);
			}

			return board;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
