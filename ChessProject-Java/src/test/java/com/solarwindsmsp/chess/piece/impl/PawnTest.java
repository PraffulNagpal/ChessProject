package com.solarwindsmsp.chess.piece.impl;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.board.impl.FullGameBoard;
import com.solarwindsmsp.chess.exception.InvalidMoveException;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.impl.Pawn;

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
		assertEquals(6, testSubject.getLocation().getxCoordinate());
	}

	@Test
	public void testYCoordinateAreSet() {
		testSubject.setLocation(new Location(6, 7));
		assertEquals(7, testSubject.getLocation().getyCoordinate());
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
		assertEquals(6, testSubject.getLocation().getyCoordinate());
		assertEquals(5, testSubject.getLocation().getxCoordinate());
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
		assertEquals(4, testSubject.getLocation().getxCoordinate());
		assertEquals(6, testSubject.getLocation().getyCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testInValidDoubleMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 6, 4);
		assertEquals(6, testSubject.getLocation().getyCoordinate());
		assertEquals(4, testSubject.getLocation().getxCoordinate());
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
		assertEquals(2, whitePawn.getLocation().getxCoordinate());
		assertEquals(6, whitePawn.getLocation().getyCoordinate());
		assertEquals(-1, blackPawn.getLocation().getxCoordinate());
		assertEquals(-1, blackPawn.getLocation().getyCoordinate());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInValidCaptureMoveSquareNotOccupied() {
		Pawn whitePawn = new Pawn(chessBoard, PieceColor.WHITE);
		chessBoard.addPiece(whitePawn, 5, 1);
		whitePawn.move(MovementType.CAPTURE, 6, 2);
		assertEquals(6, whitePawn.getLocation().getxCoordinate());
		assertEquals(2, whitePawn.getLocation().getyCoordinate());
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
		assertEquals(5, blackPawn.getLocation().getxCoordinate());
		assertEquals(1, blackPawn.getLocation().getyCoordinate());
		assertEquals(-1, whitePawn.getLocation().getxCoordinate());
		assertEquals(-1, whitePawn.getLocation().getyCoordinate());
	}

	@Test(expected = InvalidMoveException.class)
	public void testPawnIllegalMove() {
		chessBoard.addPiece(testSubject, 6, 6);
		testSubject.move(MovementType.MOVE, 4, 3);
	}

}