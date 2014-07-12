package com.epam.training.domain;

import java.util.List;

public class ShipType {

	private List<ShipElement> shipElements;

	private int piece;

	public ShipType(List<ShipElement> elements, int piece) {
		this.shipElements = elements;
		this.piece = piece;
	}


}
