package com.epam.training.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class ShipTypeReaderTest {

	private ShipTypeReader reader;

	@After
	public void tearDown() throws Exception {
		reader.close();
	}

	@Test
	public void testReadShipTypeWhenInputDoesNotCointainShipReturnsEmptyShipType() {
		//given
		reader = new ShipTypeReader("noship.txt");

		//when
		ShipType expected = new ShipType(Collections.<ShipElement> emptyList(), 1);
		ShipType result = reader.readShipType();

		//then
		assertEquals(expected, result);
		assertFalse(reader.hasNext());
	}

	@Test
	public void testReadShipTypeWhenInputContainsOneElementShipReturnsOneElementShipType() {
		//given
		reader = new ShipTypeReader("oneship.txt");

		//when
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		ShipType expected = new ShipType(elements, 1);
		ShipType result = reader.readShipType();

		//then
		assertEquals(expected, result);
		assertFalse(reader.hasNext());
	}

	@Test
	public void testReadShipTypeWhenInputContainsTetrisShipReturnsTetrisShipType() {
		//given
		reader = new ShipTypeReader("tetrisship.txt");

		//when
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(1, 0));
		elements.add(new ShipElement(0, 1));
		elements.add(new ShipElement(1, 1));
		elements.add(new ShipElement(2, 1));
		ShipType expected = new ShipType(elements, 1);
		ShipType result = reader.readShipType();

		//then
		assertEquals(expected, result);
		assertTrue(reader.hasNext());
	}

}
