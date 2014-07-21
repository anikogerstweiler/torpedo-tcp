package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShipTypeReader implements AutoCloseable {

	private static final Pattern LINE_PATTERN = Pattern.compile("[\\.x]{4}");
	
	private Scanner scanner;

	public ShipTypeReader(String fileName) {
		scanner = new Scanner(ClassLoader.getSystemResourceAsStream(fileName));
	}

	public boolean hasNext() {
		return scanner.hasNext(LINE_PATTERN);
	}

	public ShipType readShipType() {
		char inputChar;
		String line = null;
		List<ShipElement> shipElements = new ArrayList<>();

		for (int row = 0; row < 4; row++) {
			line = scanner.next(LINE_PATTERN);
			for (int column = 0; column < 4; column++) {
				inputChar = line.charAt(column);
				processInput(inputChar, shipElements, row, column);
			}
		}

		int shipCount = scanner.nextInt();

		return new ShipType(shipElements, shipCount);
	}

	private void processInput(char inputChar, List<ShipElement> shipElements, int row, int column) {
		if (inputChar == 'x') {
			ShipElement shipElement = new ShipElement(column, row);
			shipElements.add(shipElement);
		}
	}

	@Override
	public void close() throws Exception {
		scanner.close();
	}

}
