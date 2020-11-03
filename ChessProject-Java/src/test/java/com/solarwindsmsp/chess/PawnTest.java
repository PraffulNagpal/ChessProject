package com.solarwindsmsp.chess;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.FullGameBoard;
import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.Location;
import com.solarwindsmsp.chess.exception.InvalidMoveException;
import com.solarwindsmsp.chess.piece.MovementType;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

import org.junit.Assert;

import static org.junit.Assert.*;

public class PawnTest {

	private IChessBoard chessBoard;
	private Pawn testSubject;

	@Before
	public void setUp() {
		this.chessBoard = new FullGameBoard();
		this.testSubject = new Pawn(chessBoard, PieceColor.BLACK);
	}

	@Test
	public void testIsValidInitialLocationForAllValidBlackLocation() {
		for (int yCoordinate = 0; yCoordinate <= 7; yCoordinate++) {
			Assert.assertTrue(this.testSubject.isValidInitialLocation(new Location(6, yCoordinate)));
		}
	}
	
	@Test
	public void testAddPieceSuccess() {
		chessBoard.addPiece(testSubject, 6, 5);
		assertEquals(6,testSubject.getLocation().getxCoordinate());
		assertEquals(5,testSubject.getLocation().getyCoordinate());
	}

	@Test
	public void testIsValidInitialLocationForAllInValidBlackLocation() {
		for (int xCoordinate = 0; xCoordinate <= 7; xCoordinate++) {
			for (int yCoordinate = 0; yCoordinate <= 7; yCoordinate++) {
				if (xCoordinate != 6) {
					Assert.assertFalse(this.testSubject.isValidInitialLocation(new Location(xCoordinate, yCoordinate)));
				}
			}
		}
	}

	@Test
	public void testIsValidInitialLocationForAllValidWhiteLocation() {
		this.testSubject = new Pawn(chessBoard, PieceColor.WHITE);
		for (int yCoordinate = 0; yCoordinate <= 7; yCoordinate++) {
			Assert.assertTrue(this.testSubject.isValidInitialLocation(new Location(1, yCoordinate)));
		}
	}

	@Test
	public void testIsValidInitialLocationForAllInValidWhiteLocation() {
		this.testSubject = new Pawn(chessBoard, PieceColor.WHITE);
		for (int xCoordinate = 0; xCoordinate <= 7; xCoordinate++) {
			for (int yCoordinate = 0; yCoordinate <= 7; yCoordinate++) {
				if (xCoordinate != 1) {
					Assert.assertFalse(this.testSubject.isValidInitialLocation(new Location(xCoordinate, yCoordinate)));
				}
			}
		}
	}

	@Test
	public void testXCoordinateAreSet() {
		testSubject.setLocation(new Location(6, 7));
		assertEquals(6, testSubject.getXCoordinate());
	}

	@Test
	public void testYCoordinateAreSet() {
		testSubject.setLocation(new Location(6, 7));
		assertEquals(7, testSubject.getYCoordinate());
	}

	@Test
	public void testSetLocation() {
		testSubject.setLocation(new Location(6, 7));
		assertEquals(new Location(6, 7), testSubject.getLocation());
	}

	@Test(expected = InvalidMoveException.class)
	public void testIllegalMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 6, 3);
	}

	@Test
	public void testValidSingleMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 5, 6);
		assertEquals(6, testSubject.getYCoordinate());
		assertEquals(5, testSubject.getXCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testInValidSingleMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 6, 5);
	}

	@Test
	public void testValidDoubleMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 4, 6);
		assertEquals(4, testSubject.getXCoordinate());
		assertEquals(6, testSubject.getYCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testInValidDoubleMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 6, 4);
		assertEquals(6, testSubject.getYCoordinate());
		assertEquals(4, testSubject.getXCoordinate());
	}

	@Test
	public void testValidCaptureMoveWhite() {
		Pawn blackPawn = new Pawn(chessBoard, PieceColor.BLACK);
		chessBoard.addPiece(blackPawn, 6, 6);
		blackPawn.move(MovementType.MOVE, 4, 6);
		blackPawn.move(MovementType.MOVE, 3, 6);
		blackPawn.move(MovementType.MOVE, 2, 6);
		Pawn whitePawn = new Pawn(chessBoard, PieceColor.WHITE);
		chessBoard.addPiece(whitePawn, 1, 5);
		whitePawn.move(MovementType.CAPTURE, 2, 6);
		assertEquals(2, whitePawn.getXCoordinate());
		assertEquals(6, whitePawn.getYCoordinate());
		assertEquals(-1, blackPawn.getXCoordinate());
		assertEquals(-1, blackPawn.getYCoordinate());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInValidCaptureMoveSquareNotOccupied() {
		Pawn whitePawn = new Pawn(chessBoard, PieceColor.WHITE);
		chessBoard.addPiece(whitePawn, 5, 1);
		whitePawn.move(MovementType.CAPTURE, 6, 2);
		assertEquals(6, whitePawn.getXCoordinate());
		assertEquals(2, whitePawn.getYCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testInValidCaptureMoveSquareOccupied() {
		Pawn blackPawn = new Pawn(chessBoard, PieceColor.BLACK);
		chessBoard.addPiece(blackPawn, 6, 6);
		blackPawn.move(MovementType.MOVE, 4, 6);
		blackPawn.move(MovementType.MOVE, 3, 6);
		blackPawn.move(MovementType.MOVE, 2, 6);
		Pawn whitePawn = new Pawn(chessBoard, PieceColor.WHITE);
		chessBoard.addPiece(whitePawn, 1, 6);
		whitePawn.move(MovementType.CAPTURE, 2, 6);
	}

	@Test
	public void testValidCaptureMoveBlack() {
		Pawn blackPawn = new Pawn(chessBoard, PieceColor.BLACK);
		chessBoard.addPiece(blackPawn, 6, 0);
		Pawn whitePawn = new Pawn(chessBoard, PieceColor.WHITE);
		chessBoard.addPiece(whitePawn, 1, 1);
		whitePawn.move(MovementType.MOVE, 3, 1);
		whitePawn.move(MovementType.MOVE, 4, 1);
		whitePawn.move(MovementType.MOVE, 5, 1);
		blackPawn.move(MovementType.CAPTURE, 5, 1);
		assertEquals(5, blackPawn.getXCoordinate());
		assertEquals(1, blackPawn.getYCoordinate());
		assertEquals(-1, whitePawn.getXCoordinate());
		assertEquals(-1, whitePawn.getYCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testPawnIllegalMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 4, 3);
	}

}