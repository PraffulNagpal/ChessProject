package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.impl.ReenactBoard;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.impl.Pawn;

public class ReenactBoardTest extends BaseChessBoardTest {
	

	@Before
	public void setUp() throws Exception {
		testSubject = new ReenactBoard();
	}
	

	@Test
	public void testAddPieceSuccess() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 5);
		assertEquals(6,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}
	
	@Test
	public void testAddPieceOutsideOfInitialLocationsBlack() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 5, 5);
		assertEquals(5,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}
	
	@Test
	public void testAddPieceOutsideOfInitialLocationsWhite() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.WHITE);
		testSubject.addPiece(firstPawn, 5, 5);
		assertEquals(5,firstPawn.getLocation().getxCoordinate());
		assertEquals(5,firstPawn.getLocation().getyCoordinate());
	}


}
