package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.FullGameBoard;
import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.exception.SpotAlreadyAcquired;
import com.solarwindsmsp.chess.piece.MovementType;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

public abstract class BaseChessBoardTest {

	protected IChessBoard testSubject;

	@Test
	public void checkMaxWidth() {
		assertEquals(7, AbstractChessBoard.MAX_BOARD_WIDTH);
	}

	@Test
	public void checkMaxHeight() {
		assertEquals(7, AbstractChessBoard.MAX_BOARD_HEIGHT);
	}

	@Test
	public void testIsLegalBoardPositionForAllValidPosistion() {
		for (int x = 0; x <= FullGameBoard.MAX_BOARD_WIDTH; x++) {
			for (int y = 0; y <= FullGameBoard.MAX_BOARD_HEIGHT; y++) {
				assertTrue(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	@Test
	public void testIsLegalBoardPositionForInValidPosistion() {
		for (int x = 8; x <= FullGameBoard.MAX_BOARD_WIDTH * 2; x++) {
			for (int y = 8; y <= FullGameBoard.MAX_BOARD_HEIGHT * 2; y++) {
				assertFalse(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	@Test
	public void testIsLegalBoardPositionForInValidNegativePosistion() {
		for (int x = -1; x >= -FullGameBoard.MAX_BOARD_WIDTH; x--) {
			for (int y = -1; y >= -FullGameBoard.MAX_BOARD_HEIGHT; y--) {
				assertFalse(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	@Test(expected = SpotAlreadyAcquired.class)
	public void testThrowSpotAlreadyAcquiredIfTriedToAddPieceOnSameLocation() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		Pawn secondPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 6);
		testSubject.addPiece(secondPawn, 6, 6);
	}

	@Test
	public void testLeagalCaptureBlack() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 0);
		Pawn whitePawn = new Pawn(testSubject, PieceColor.WHITE);
		testSubject.addPiece(whitePawn, 1, 1);
		whitePawn.move(MovementType.MOVE, 3, 1);
		whitePawn.move(MovementType.MOVE, 4, 1);
		whitePawn.move(MovementType.MOVE, 5, 1);
		testSubject.movePiece(MovementType.CAPTURE, blackPawn, 5, 1);
		assertEquals(5, blackPawn.getXCoordinate());
		assertEquals(1, blackPawn.getYCoordinate());
		assertEquals(-1, whitePawn.getXCoordinate());
		assertEquals(-1, whitePawn.getYCoordinate());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllleagalCaptureSpotEmpty() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 0);
		testSubject.movePiece(MovementType.CAPTURE, blackPawn, 5, 1);
	}

	@Test
	public void testLeagalCaptureWhite() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		blackPawn.move(MovementType.MOVE, 4, 6);
		blackPawn.move(MovementType.MOVE, 3, 6);
		blackPawn.move(MovementType.MOVE, 2, 6);
		Pawn whitePawn = new Pawn(testSubject, PieceColor.WHITE);
		testSubject.addPiece(whitePawn, 1, 5);
		testSubject.movePiece(MovementType.CAPTURE, whitePawn, 2, 6);
		assertEquals(2, whitePawn.getXCoordinate());
		assertEquals(6, whitePawn.getYCoordinate());
		assertEquals(-1, blackPawn.getXCoordinate());
		assertEquals(-1, blackPawn.getYCoordinate());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllleagalCaptureSameColor() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		blackPawn.move(MovementType.MOVE, 5, 6);
		
		Pawn blackPawn2 = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn2, 6, 7);
		testSubject.movePiece(MovementType.CAPTURE, blackPawn2, 5,6);
		
	}

	@Test
	public void testLeagalMove() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn, 4, 6);
		assertEquals(4, blackPawn.getXCoordinate());
		assertEquals(6, blackPawn.getYCoordinate());
	}
	
	@Test(expected = SpotAlreadyAcquired.class)
	public void testIllleagalMoveSpotAcquired() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		Pawn blackPawn2 = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn, 4, 6);
		testSubject.addPiece(blackPawn2, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn2, 4, 6);
	}

}
