package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipFactory implements AutoCloseable {

	private Scanner scanner;

	public ShipFactory(String fileName) {
		scanner = new Scanner(ClassLoader.getSystemResourceAsStream(fileName));
	}

	public List<ShipElement> createShipType() {
		char inputChar;
		String line = null;
		List<ShipElement> shipElements = new ArrayList<>();

		for (int row = 0; row < 4; row++) {
			line = scanner.nextLine();
			for (int column = 0; column < 4; column++) {
				inputChar = line.charAt(column);
				if (inputChar == 'x') {
					ShipElement shipElement = new ShipElement(column, row);
					shipElements.add(shipElement);
				}
			}
		}
		return shipElements;
	}

	@Override
	public void close() throws Exception {
		scanner.close();
	}

}
