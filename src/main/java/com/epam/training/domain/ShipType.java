package com.epam.training.domain;

import java.util.ArrayList;
import java.util.List;

public enum ShipType {
	ONE_ELEMENT {
		@Override
		public List<ShipElement> createShip(int positionX, int positonY) {
			List<ShipElement> elements = new ArrayList<>();
			elements.add(new ShipElement(positionX, positonY));
			return elements;
		}
	},
	TWO_ELEMENT {
		@Override
		public List<ShipElement> createShip(int positionX, int positonY) {
			List<ShipElement> elements = new ArrayList<>();
			elements.add(new ShipElement(positionX, positonY));
			elements.add(new ShipElement(positionX + 1, positonY));
			return elements;
		}
	},
	THREE_ELEMENT {
		@Override
		public List<ShipElement> createShip(int positionX, int positonY) {
			List<ShipElement> elements = new ArrayList<>();
			elements.add(new ShipElement(positionX, positonY));
			elements.add(new ShipElement(positionX + 1, positonY));
			elements.add(new ShipElement(positionX + 2, positonY));
			return elements;
		}
	},
	FOUR_ELEMENT {
		@Override
		public List<ShipElement> createShip(int positionX, int positonY) {
			List<ShipElement> elements = new ArrayList<>();
			elements.add(new ShipElement(positionX, positonY));
			elements.add(new ShipElement(positionX + 1, positonY));
			elements.add(new ShipElement(positionX + 2, positonY));
			elements.add(new ShipElement(positionX + 3, positonY));
			return elements;
		}
	},
	FOUR_ELEMENT_WITH_TOP {
		@Override
		public List<ShipElement> createShip(int positionX, int positonY) {
			List<ShipElement> elements = new ArrayList<>();
			elements.add(new ShipElement(positionX + 1, positonY));
			elements.add(new ShipElement(positionX, positonY + 1));
			elements.add(new ShipElement(positionX + 1, positonY + 1));
			elements.add(new ShipElement(positionX + 2, positonY + 1));
			return elements;
		}
	};

	public abstract List<ShipElement> createShip(int positionX, int positonY);
}
