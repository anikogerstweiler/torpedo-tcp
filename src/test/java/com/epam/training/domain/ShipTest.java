package com.epam.training.domain;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

public class ShipTest {

	private static final int BOARD_HEIGHT = 30;

	private static final int BOARD_WIDTH = 30;

	@Test
	public void testIsOverLapWhenCheckItselfReturnsTrue() {
		//given
		ShipElement shipElement = mockShipElement(0, 0);
		Ship ship = new Ship(asList(shipElement));

		//when
		boolean overLapped = ship.isOverLap(ship);

		//then
		assertTrue(overLapped);
	}

	@Test
	public void testIsOverLapWhenOverLapReturnsTrue() {
		//given
		ShipElement shipElement = mockShipElement(0, 0);
		Ship ship = new Ship(asList(shipElement));
		Ship other = new Ship(asList(shipElement));

		//when
		boolean overLapped = ship.isOverLap(other);

		//then
		assertTrue(overLapped);
	}

	@Test
	public void testIsOverLapWhenNotOverLapReturnsFalse() {
		//given
		ShipElement shipElement = mockShipElement(0, 0);
		Ship ship = new Ship(asList(shipElement));

		ShipElement otherShipElement = mockShipElement(1, 1);
		Ship other = new Ship(asList(otherShipElement));

		//when
		boolean overLapped = ship.isOverLap(other);

		//then
		assertFalse(overLapped);
	}

	@Test
	public void testIsInAreaWhenShipIsInAreaReturnsTrue() {
		//given
		ShipElement shipElement = mockShipElement(10, 10);
		Ship ship = new Ship(asList(shipElement));

		//when
		boolean result = ship.isInArea(BOARD_WIDTH, BOARD_HEIGHT);

		//then
		assertTrue(result);
	}

	@Test
	public void testIsInAreaWhenShipIsInLowerBorderReturnsTrue() {
		//given
		ShipElement shipElement = mockShipElement(0, 0);
		Ship ship = new Ship(asList(shipElement));

		//when
		boolean result = ship.isInArea(BOARD_WIDTH, BOARD_HEIGHT);

		//then
		assertTrue(result);
	}

	@Test
	public void testIsInAreaWhenShipIsInUpperBorderReturnsTrue() {
		//given
		ShipElement shipElement = mockShipElement(BOARD_WIDTH - 1, BOARD_HEIGHT - 1);
		Ship ship = new Ship(asList(shipElement));

		//when
		boolean result = ship.isInArea(BOARD_WIDTH, BOARD_HEIGHT);

		//then
		assertTrue(result);
	}

	@Test
	public void testIsInAreaWhenShipIsOutOfAreReturnsFalse() {
		//given
		ShipElement shipElement = mockShipElement(BOARD_WIDTH + 1, BOARD_HEIGHT + 1);
		Ship ship = new Ship(asList(shipElement));

		//when
		boolean result = ship.isInArea(BOARD_WIDTH, BOARD_HEIGHT);

		//then
		assertFalse(result);
	}

	private ShipElement mockShipElement(int x, int y) {
		ShipElement shipElement = Mockito.mock(ShipElement.class);
		Mockito.when(shipElement.getRelativePositionX()).thenReturn(x);
		Mockito.when(shipElement.getRelativePositionY()).thenReturn(y);

		return shipElement;
	}

}
