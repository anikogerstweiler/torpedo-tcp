package com.epam.training.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.epam.training.domain.Ship;
import com.epam.training.domain.ShipElement;
import com.epam.training.domain.ShipType;

public class ShipTest {

	@Test
	public void testCreateShipByTypeWhenShipIsOneElement() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		
		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		ShipElement expected = elements.get(0);
		assertEquals(expected.getRelativePositionX(), ship.getAbsolutePositionX());
		assertEquals(expected.getRelativePositionY(), ship.getAbsolutePositionY());
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsTwoElement() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		elements.add(new ShipElement(1, 0));
		
		Ship ship = new Ship(ShipType.TWO_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		for (int i = 0; i < elements.size(); i++) {
			ShipElement expected = elements.get(i);
			ShipElement actual = ship.getShipElements().get(i);
			assertEquals(expected.getRelativePositionX(), actual.getRelativePositionX());
			assertEquals(expected.getRelativePositionY(), actual.getRelativePositionY());
		}
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsThreeElement() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		elements.add(new ShipElement(1, 0));
		elements.add(new ShipElement(2, 0));
		
		Ship ship = new Ship(ShipType.THREE_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		for (int i = 0; i < elements.size(); i++) {
			ShipElement expected = elements.get(i);
			ShipElement actual = ship.getShipElements().get(i);
			assertEquals(expected.getRelativePositionX(), actual.getRelativePositionX());
			assertEquals(expected.getRelativePositionY(), actual.getRelativePositionY());
		}
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsFourElementWithTop() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		elements.add(new ShipElement(1, 0));
		elements.add(new ShipElement(2, 0));
		elements.add(new ShipElement(3, 0));
		
		Ship ship = new Ship(ShipType.FOUR_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		for (int i = 0; i < elements.size(); i++) {
			ShipElement expected = elements.get(i);
			ShipElement actual = ship.getShipElements().get(i);
			assertEquals(expected.getRelativePositionX(), actual.getRelativePositionX());
			assertEquals(expected.getRelativePositionY(), actual.getRelativePositionY());
		}
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsFourElement() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(1, 0));
		elements.add(new ShipElement(0, 1));
		elements.add(new ShipElement(1, 1));
		elements.add(new ShipElement(2, 1));
		
		Ship ship = new Ship(ShipType.FOUR_ELEMENT_WITH_TOP, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		for (int i = 0; i < elements.size(); i++) {
			ShipElement expected = elements.get(i);
			ShipElement actual = ship.getShipElements().get(i);
			assertEquals(expected.getRelativePositionX(), actual.getRelativePositionX());
			assertEquals(expected.getRelativePositionY(), actual.getRelativePositionY());
		}
	}
	
	@Test
	public void testIsOverLapWhenCheckItselfReturnsTrue() {
		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);
		
		assertTrue(ship.isOverLap(ship));
	}
	
	@Test
	public void testIsOverLapWhenOverLapReturnsTrue() {
		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);
		Ship other = new Ship(ShipType.TWO_ELEMENT, 0, 0);
		
		assertTrue(ship.isOverLap(other));
	}
	
	@Test
	public void testIsOverLapWhenNotOverLapReturnsFalse() {
		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);
		Ship other = new Ship(ShipType.THREE_ELEMENT, 1, 1);
		
		assertFalse(ship.isOverLap(other));
	}

}
