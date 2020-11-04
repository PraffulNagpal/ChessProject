package com.solarwindsmsp.chess.board.attribute;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.impl.ReenactBoard;
import com.solarwindsmsp.chess.piece.PieceFactory;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

/**
 * Class to test {@link Square}
 */
public class SquareTest {

	/** Class under test */
	private Square testSubject;

	/** Setup for tests */
	@Before
	public void setup() {
		testSubject = new Square();
	}

	/** Test path when square is not occupied */
	@Test
	public void testIsOccupiedWhenEmpty() {
		assertFalse(testSubject.isOccupied());
	}

	/** Test path when square is occupied */
	@Test
	public void testIsOccupiedWhenOccupied() {
		testSubject.setPiece(new PieceFactory().createPiece(PieceType.PAWN, PieceColor.BLACK, new ReenactBoard()));
		assertTrue(testSubject.isOccupied());
	}

	/** Test piece is set at square */
	@Test
	public void testSetPiece() {
		testSubject.setPiece(new PieceFactory().createPiece(PieceType.PAWN, PieceColor.BLACK, new ReenactBoard()));
		assertNotNull(testSubject.getPiece());
	}

}
