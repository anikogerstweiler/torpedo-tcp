package com.epam.training.message;

public class Size implements Message {

	private int width;

	private int height;

	public Size(String input) {
		String[] splitted = input.split(" ");
		this.width = Integer.parseInt(splitted[0]);
		this.height = Integer.parseInt(splitted[1]);
	}

	@Override
	public String toString() {
		return "SIZE " + width + " " + height;
	}
}
