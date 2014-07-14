package com.epam.training.message;

public class Sunk implements FireAnswer {

	@Override
	public String toString() {
		return "SUNK";
	}

	@Override
	public int demage() {
		return 1;
	}
}
