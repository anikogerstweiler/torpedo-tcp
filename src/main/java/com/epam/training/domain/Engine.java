package com.epam.training.domain;

import java.util.Random;

import com.epam.training.message.Fire;
import com.epam.training.message.FireAnswer;

public class Engine {

	private final int width;
	private final int height;
	private int shipECount;

	private int nextShootX;
	private int nextShootY;
	
	private Random random = new Random();
	
	public Engine(int width, int height, int shipCount) {
		this.width = width;
		this.height = height;
		this.shipECount = shipCount;
	}

	public Fire shoot() {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		
		Fire fire = new Fire(nextShootY, nextShootX);

		if (nextShootX < width - 1) {
			nextShootX++;
		} else {
			nextShootX = 0;
			nextShootY++;
		}

		return fire;
	}

	public void process(FireAnswer msg) {
		shipECount -= msg.demage();
	}

	public boolean isWon() {
		return shipECount == 0;
	}

}
