package com.solarwindsmsp.chess.board.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.impl.FullGameBoard;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.impl.Pawn;

/**
 *Tests for class {@link FullGameBoard}
 */
public class FullGameBoardTest extends BaseChessBoardTest{


	/**
	 * Setup for tests
	 */
	@Before
	public void setUp() throws Exception {
		testSubject = new FullGameBoard();
	}

	
	/**
	 * Tests add piece happy flow
	 */
	@Test
	public void testAddPieceSuccess() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 5);
		assertEquals(6,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}

	/**
	 * Tests add piece invalid init location 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddPieceOutsideOfInitialLocation() {
		testSubject.addPiece(new Pawn(testSubject, PieceColor.BLACK), 5, 5);
	}
	
}