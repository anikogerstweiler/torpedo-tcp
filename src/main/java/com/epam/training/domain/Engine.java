package com.epam.training.domain;

import com.epam.training.message.Fire;
import com.epam.training.message.FireAnswer;

public class Engine {

	private final int width;
	private final int height;
	private int shipElementCount;

	private int nextShootX;
	private int nextShootY;

	public Engine(int width, int height, int shipCount) {
		this.width = width;
		this.height = height;
		this.shipElementCount = shipCount;
	}

	public Fire shoot() {
		Fire fire = new Fire(nextShootX, nextShootY);

		if (nextShootX < width - 1) {
			nextShootX++;
		} else {
			nextShootX = 0;
			nextShootY++;
		}

		return fire;
	}

	public void process(FireAnswer msg) {
		shipElementCount -= msg.demage();
	}

	public boolean isWon() {
		return shipElementCount == 0;
	}

}
