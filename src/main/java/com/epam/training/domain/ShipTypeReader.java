package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipTypeReader implements AutoCloseable {

	private Scanner scanner;

	public ShipTypeReader(String fileName) {
		scanner = new Scanner(ClassLoader.getSystemResourceAsStream(fileName));
	}

	public boolean hasNext() {
		return scanner.hasNext("[\\.x]+");
	}

	public ShipType readShipType() {
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

		int shipCount = scanner.nextInt();

		return new ShipType(shipElements, shipCount);
	}

	@Override
	public void close() throws Exception {
		scanner.close();
	}

}