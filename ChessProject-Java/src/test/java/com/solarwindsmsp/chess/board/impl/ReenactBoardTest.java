package com.solarwindsmsp.chess.board.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.impl.ReenactBoard;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.impl.Pawn;

/**
 *Tests for class {@link ReenactBoard}
 */
public class ReenactBoardTest extends BaseChessBoardTest {
	

	/**
	 * Setup for tests
	 */
	@Before
	public void setUp() throws Exception {
		testSubject = new ReenactBoard();
	}
	

	/**
	 * Add piece happy flow
	 */
	@Test
	public void testAddPieceSuccess() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 5);
		assertEquals(6,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}
	
	/**
	 * Adds piece at not init location
	 */
	@Test
	public void testAddPieceOutsideOfInitialLocation() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 5, 5);
		assertEquals(5,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}
	

}
