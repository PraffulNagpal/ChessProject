package com.solarwindsmsp.chess.piece.impl;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.piece.AbstractPiece;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;

/**
 * Basic Pawn piece
 */
public class Pawn extends AbstractPiece {
	/** Initial x coordinates for white pawn */
	private static final int INITIAL_WHITE_X_COORDINATE = 1;
	/** Initial x coordinates for Black pawn */
	private static final int INITIAL_BLACK_X_COORDINATE = 6;
	/** Min location for white pawn */
	private static final Location WHITE_INIT_LOCATION_MIN = new Location(INITIAL_WHITE_X_COORDINATE, 0);
	/** Max location for white pawn */
	private static final Location WHITE_INIT_LOCATION_MAX = new Location(INITIAL_WHITE_X_COORDINATE,
			AbstractChessBoard.MAX_BOARD_HEIGHT);
	/** Min location for Black pawn */
	private static final Location BLACK_INIT_LOCATION_MIN = new Location(INITIAL_BLACK_X_COORDINATE, 0);
	/** Max location for white pawn */
	private static final Location BLACK_INIT_LOCATION_MAX = new Location(INITIAL_BLACK_X_COORDINATE,
			AbstractChessBoard.MAX_BOARD_HEIGHT);

	/**
	 * Default constructor for class
	 * 
	 * @param chessBoard to place Pawn on
	 * @param pieceColor of pawn
	 */
	public Pawn(IChessBoard chessBoard, PieceColor pieceColor) {
		super(chessBoard, pieceColor);
	}

	@Override
	public boolean isValidInitialLocation(Location location) {
		switch (getPieceColor()) {
		case BLACK:
			return location.isBetweenMinMax(BLACK_INIT_LOCATION_MIN, BLACK_INIT_LOCATION_MAX);
		case WHITE:
			return location.isBetweenMinMax(WHITE_INIT_LOCATION_MIN, WHITE_INIT_LOCATION_MAX);
		default:
			return false;
		}
	}

	@Override
	protected boolean isValidMove(Location current, int newX, int newY, MovementType moveType) {
		if (PieceColor.WHITE == getPieceColor()) {
			return isValidWhiteMove(current, newX, newY, moveType);
		} else {
			return isValidBlackMove(current, newX, newY, moveType);
		}
	}

	/**
	 * Checks if move is valid for a Black pawn
	 * 
	 * @param current  location of pawn
	 * @param newX     to move to
	 * @param newY     to move to
	 * @param moveType of move
	 * @return true if it is a valid move
	 */
	private boolean isValidBlackMove(Location current, int newX, int newY, MovementType moveType) {
		if (MovementType.CAPTURE == moveType) {
			return isValidCapture(current, newX, newY, -1);
		} else {
			return isValidSingleMove(current, newX, newY, -1) || isValidDoubleMove(current, newX, newY, -2);
		}

	}

	/**
	 * Checks if move is valid for a White pawn
	 * 
	 * @param current  location of pawn
	 * @param newX     to move to
	 * @param newY     to move to
	 * @param moveType of move
	 * @return true if it is a valid move
	 */
	private boolean isValidWhiteMove(Location current, int newX, int newY, MovementType moveType) {
		if (MovementType.CAPTURE == moveType) {
			return isValidCapture(current, newX, newY, 1);
		} else {
			return isValidSingleMove(current, newX, newY, 1) || isValidDoubleMove(current, newX, newY, 2);
		}
	}

	/**
	 * Checks if move is valid for a pawn
	 * 
	 * @param current location of pawn
	 * @param newX    to move to
	 * @param newY    to move to
	 * @param deviant for move
	 * @return true if it is a valid move
	 */
	private boolean isValidDoubleMove(Location current, int newX, int newY, int deviant) {
		return isValidInitialLocation(current) && current.getxCoordinate() + deviant == newX
				&& current.getyCoordinate() == newY;
	}

	/**
	 * Checks if is valid capture for a pawn
	 * 
	 * @param current location of pawn
	 * @param newX    to move to
	 * @param newY    to move to
	 * @param deviant for move
	 * @return true if it is a valid move
	 */
	private boolean isValidCapture(Location current, int newX, int newY, int deviant) {
		return current.getxCoordinate() + deviant == newX
				&& (current.getyCoordinate() - deviant == newY || current.getyCoordinate() + deviant == newY);
	}

	/**
	 * Checks if is valid single for a pawn
	 * 
	 * @param current location of pawn
	 * @param newX    to move to
	 * @param newY    to move to
	 * @param deviant for move
	 * @return true if it is a valid move
	 */
	private boolean isValidSingleMove(Location current, int newX, int newY, int deviant) {
		return current.getxCoordinate() + deviant == newX && current.getyCoordinate() == newY;
	}

}
