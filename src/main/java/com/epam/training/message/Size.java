package com.epam.training.message;

public class Size implements Message {

	private int width;

	private int height;

	public Size(String input) {
		String[] splitted = input.split(" ");
		this.width = Integer.parseInt(splitted[0]);
		this.height = Integer.parseInt(splitted[1]);
	}

	public Size(int boardWidth, int boardHeight) {
		this.width = boardWidth;
		this.height = boardHeight;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "SIZE " + width + " " + height;
	}
}
