package com.solarwindsmsp.chess.board.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.impl.FullGameBoard;
import com.solarwindsmsp.chess.exception.SquareAlreadyAcquiredException;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.impl.Pawn;


/**
 * Class holds base tests for {@link IChessBoard}
 */
public abstract class BaseChessBoardTest {

	/** Class under test*/
	protected IChessBoard testSubject;

	/**
	 * Checks max width
	 */
	@Test
	public void checkMaxWidth() {
		assertEquals(7, AbstractChessBoard.MAX_BOARD_WIDTH);
	}
	/**
	 * Checks max height
	 */
	@Test
	public void checkMaxHeight() {
		assertEquals(7, AbstractChessBoard.MAX_BOARD_HEIGHT);
	}

	/**
	 * Test legal board position for all valid positions
	 */
	@Test
	public void testIsLegalBoardPositionForAllValidPosistion() {
		for (int x = 0; x <= FullGameBoard.MAX_BOARD_WIDTH; x++) {
			for (int y = 0; y <= FullGameBoard.MAX_BOARD_HEIGHT; y++) {
				assertTrue(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	/**
	 * Test legal board position for +ve invalid positions
	 */
	@Test
	public void testIsLegalBoardPositionForInValidPosistion() {
		for (int x = 8; x <= FullGameBoard.MAX_BOARD_WIDTH * 2; x++) {
			for (int y = 8; y <= FullGameBoard.MAX_BOARD_HEIGHT * 2; y++) {
				assertFalse(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	/**
	 * Test legal board position for -ve invalid positions
	 */
	@Test
	public void testIsLegalBoardPositionForInValidNegativePosistion() {
		for (int x = -1; x >= -FullGameBoard.MAX_BOARD_WIDTH; x--) {
			for (int y = -1; y >= -FullGameBoard.MAX_BOARD_HEIGHT; y--) {
				assertFalse(String.format("validation fails for position %d , %d", x, y),
						testSubject.isLegalBoardPosition(x, y));
			}
		}
	}

	/**
	 * Test path where add piece on acquired square
	 */
	@Test(expected = SquareAlreadyAcquiredException.class)
	public void testThrowSquareAlreadyAcquiredIfTriedToAddPieceOnSameLocation() {
		Pawn firstPawn = new Pawn(testSubject, PieceColor.BLACK);
		Pawn secondPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(firstPawn, 6, 6);
		testSubject.addPiece(secondPawn, 6, 6);
	}

	/**
	 * Test flow for legal capture using black pawn
	 */
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
		assertEquals(5, blackPawn.getLocation().getxCoordinate());
		assertEquals(1, blackPawn.getLocation().getyCoordinate());
		assertEquals(-1, whitePawn.getLocation().getxCoordinate());
		assertEquals(-1, whitePawn.getLocation().getyCoordinate());
	}

	/**
	 * Tests illlegal capture
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllleagalCaptureSpotEmpty() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 0);
		testSubject.movePiece(MovementType.CAPTURE, blackPawn, 5, 1);
	}
	/**
	 * Test flow for legal capture using white pawn
	 */
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
		assertEquals(2, whitePawn.getLocation().getxCoordinate());
		assertEquals(6, whitePawn.getLocation().getyCoordinate());
		assertEquals(-1, blackPawn.getLocation().getxCoordinate());
		assertEquals(-1, blackPawn.getLocation().getyCoordinate());
	}
	
	/**
	 * Test piece tries to acquire same color piece
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIllleagalCaptureSameColor() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		blackPawn.move(MovementType.MOVE, 5, 6);
		
		Pawn blackPawn2 = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn2, 6, 7);
		testSubject.movePiece(MovementType.CAPTURE, blackPawn2, 5,6);
		
	}

	/**
	 * Tests legal move
	 */
	@Test
	public void testLeagalMove() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn, 4, 6);
		assertEquals(4, blackPawn.getLocation().getxCoordinate());
		assertEquals(6, blackPawn.getLocation().getyCoordinate());
	}
	
	/**
	 * Tests illlegal move to acquired square
	 */
	@Test(expected = SquareAlreadyAcquiredException.class)
	public void testIllleagalMoveSquareAcquired() {
		Pawn blackPawn = new Pawn(testSubject, PieceColor.BLACK);
		Pawn blackPawn2 = new Pawn(testSubject, PieceColor.BLACK);
		testSubject.addPiece(blackPawn, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn, 4, 6);
		testSubject.addPiece(blackPawn2, 6, 6);
		testSubject.movePiece(MovementType.MOVE, blackPawn2, 4, 6);
	}

}
