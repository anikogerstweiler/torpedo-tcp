package com.epam.training.message;

public class Fire implements Message {

	private int coordinateX;

	private int coordinateY;

	public Fire(String input) {
		String[] splitted = input.split(" ");
		this.coordinateX = Integer.parseInt(splitted[0]);
		this.coordinateY = Integer.parseInt(splitted[1]);
	}

	@Override
	public String toString() {
		return "FIRE " + coordinateX + " " + coordinateY;
	}

}
