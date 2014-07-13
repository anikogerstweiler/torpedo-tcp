package com.epam.training.domain;



public class Engine {
	private Board board;

	public Engine(Board board) {
		this.board = board;
	}

	public void shoot() {
		int width = board.getWidth();
		int height = board.getHeight();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				shootAt(j, i);
			}
		}
	}

	private void shootAt(int positionX, int positionY) {
		System.out.println("Shooting to position(" + positionX + "," + positionY + ")");

		Ship s = board.getShipByPosition(positionX, positionY);
		if (s != null) {
			s.setInjured(positionX, positionY);
			board.printShips();
		}
	}
}
