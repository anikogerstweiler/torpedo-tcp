package com.epam.training.message;

public class Error implements Message {

	private String message;

	public Error(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ERROR " + message;
	}
}
