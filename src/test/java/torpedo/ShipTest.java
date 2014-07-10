package torpedo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.training.domain.Ship;
import com.epam.training.domain.ShipElement;
import com.epam.training.domain.ShipType;

public class ShipTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateShipByTypeWhenShipIsOneElement() {
		List<ShipElement> elements = new ArrayList<>();
		elements.add(new ShipElement(0, 0));
		
		Ship ship = new Ship(ShipType.ONE_ELEMENT, 0, 0);
		
		assertThat(ship.getShipElements().toString(), equalTo(elements.toString()));
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
