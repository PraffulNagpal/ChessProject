package com.solarwindsmsp.chess.board.attribute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Tests {@link Location} */
public class LocationTest {

	/** to be tested */
	private Location testSubject;

	/** Setup for tests */
	@Before
	public void setup() {
		testSubject = new Location(4, 3);
	}

	/** Tests xCoordinate are set */
	@Test
	public void testGetXCoordinates() {
		assertEquals(4, testSubject.getxCoordinate());
	}

	/** Tests yCoordinate are set */
	@Test
	public void testGetYCoordinates() {
		assertEquals(3, testSubject.getyCoordinate());
	}
	
	/**Should return true*/
	@Test
	public void testIsBetweenMinMax() {
		assertTrue(testSubject.isBetweenMinMax(new Location(3, 3), new Location(5, 5)));
	}
	/**Should return False*/
	@Test
	public void testIsBetweenMinMaxFasle() {
		assertFalse(testSubject.isBetweenMinMax(new Location(7, 4), new Location(5, 5)));
	}
}
