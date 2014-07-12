package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;

public class ShipType {

	private List<ShipElement> shipElements;

	private int piece;

	public ShipType(List<ShipElement> elements, int piece) {
		this.shipElements = elements;
		this.piece = piece;
	}

	public Ship createShip(int positionX, int posiotionY) {
		List<ShipElement> shiftedElements = new ArrayList<>();
		for (ShipElement element : shipElements) {
			shiftedElements.add(
					new ShipElement(element.getRelativePositionX() + positionX,
							element.getRelativePositionY() + posiotionY));
		}
		return new Ship(shiftedElements);
	}

	public int getPiece() {
		return piece;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + piece;
		result = prime * result
				+ ((shipElements == null) ? 0 : shipElements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipType other = (ShipType) obj;
		if (piece != other.piece)
			return false;
		if (shipElements == null) {
			if (other.shipElements != null)
				return false;
		} else if (!shipElements.equals(other.shipElements))
			return false;
		return true;
	}

}
