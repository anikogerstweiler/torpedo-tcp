package com.epam.training.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
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
		ShipElement first = new ShipElement(0, 0);
		ShipElement second = new ShipElement(1, 0);
		elements.add(first);
		elements.add(second);
		
		Ship ship = new Ship(ShipType.TWO_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		
		ShipElement actual = ship.getShipElements().get(0);
		assertEquals(first.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(first.getRelativePositionY(), actual.getRelativePositionY());
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsThreeElement() {
		List<ShipElement> elements = new ArrayList<>();
		ShipElement first = new ShipElement(0, 0);
		elements.add(first);
		ShipElement second = new ShipElement(1, 0);
		elements.add(second);
		ShipElement third = new ShipElement(2, 0);
		elements.add(third);
		
		Ship ship = new Ship(ShipType.THREE_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		
		ShipElement actual = ship.getShipElements().get(0);
		assertEquals(first.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(first.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(1);
		assertEquals(second.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(second.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(2);
		assertEquals(third.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(third.getRelativePositionY(), actual.getRelativePositionY());
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsFourElement() {
		List<ShipElement> elements = new ArrayList<>();
		ShipElement first = new ShipElement(0, 0);
		elements.add(first);
		ShipElement second = new ShipElement(1, 0);
		elements.add(second);
		ShipElement third = new ShipElement(2, 0);
		elements.add(third);
		ShipElement fourth = new ShipElement(3, 0);
		elements.add(fourth);
		
		Ship ship = new Ship(ShipType.FOUR_ELEMENT, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		
		ShipElement actual = ship.getShipElements().get(0);
		assertEquals(first.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(first.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(1);
		assertEquals(second.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(second.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(2);
		assertEquals(third.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(third.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(3);
		assertEquals(fourth.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(fourth.getRelativePositionY(), actual.getRelativePositionY());
	}
	
	@Test
	public void testCreateShipByTypeWhenShipIsFourElementWithTop() {
		List<ShipElement> elements = new ArrayList<>();
		ShipElement first = new ShipElement(1, 0);
		elements.add(first);
		ShipElement second = new ShipElement(0, 1);
		elements.add(second);
		ShipElement third = new ShipElement(1, 1);
		elements.add(third);
		ShipElement fourth = new ShipElement(2, 1);
		elements.add(fourth);
		
		Ship ship = new Ship(ShipType.FOUR_ELEMENT_WITH_TOP, 0, 0);
		
		assertEquals(ship.getShipElements().size(), elements.size());
		
		ShipElement actual = ship.getShipElements().get(0);
		assertEquals(first.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(first.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(1);
		assertEquals(second.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(second.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(2);
		assertEquals(third.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(third.getRelativePositionY(), actual.getRelativePositionY());
		
		actual = ship.getShipElements().get(3);
		assertEquals(fourth.getRelativePositionX(), actual.getRelativePositionX());
		assertEquals(fourth.getRelativePositionY(), actual.getRelativePositionY());
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
