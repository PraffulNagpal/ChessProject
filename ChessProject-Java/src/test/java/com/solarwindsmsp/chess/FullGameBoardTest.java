package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.FullGameBoard;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

public class FullGameBoardTest extends BaseChessBoardTest{


	@Before
	public void setUp() throws Exception {
		testSubject = new FullGameBoard();
	}

	
	@Test
	public void testAddPieceSuccess() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 5);
		assertEquals(6,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPieceOutsideOfInitialLocationsBlack() {
		testSubject.addPiece(new Pawn(testSubject, PieceColor.BLACK), 5, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPieceOutsideOfInitialLocationsWhite() {
		testSubject.addPiece(new Pawn(testSubject, PieceColor.WHITE), 5, 5);
	}
}