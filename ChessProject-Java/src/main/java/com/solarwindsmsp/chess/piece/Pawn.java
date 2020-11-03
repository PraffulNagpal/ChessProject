package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.Location;

public class Pawn extends AbstractPiece {

	private static final int INITIAL_WHITE_X_COORDINATE = 1;
	private static final int INITIAL_BLACK_X_COORDINATE = 6;
	private static final Location WHITE_INIT_LOCATION_MIN = new Location(INITIAL_WHITE_X_COORDINATE, 0);
	private static final Location WHITE_INIT_LOCATION_MAX = new Location(INITIAL_WHITE_X_COORDINATE,
			AbstractChessBoard.MAX_BOARD_HEIGHT);
	private static final Location BLACK_INIT_LOCATION_MIN = new Location(INITIAL_BLACK_X_COORDINATE, 0);
	private static final Location BLACK_INIT_LOCATION_MAX = new Location(INITIAL_BLACK_X_COORDINATE,
			AbstractChessBoard.MAX_BOARD_HEIGHT);

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

	private boolean isValidBlackMove(Location current, int newX, int newY, MovementType moveType) {
		if (MovementType.CAPTURE == moveType) {
			return isValidCapture(current, newX, newY, -1);
		} else {
			return isValidSingleMove(current, newX, newY, -1) || isValidDoubleMove(current, newX, newY, -2);
		}

	}

	private boolean isValidWhiteMove(Location current, int newX, int newY, MovementType moveType) {
		if (MovementType.CAPTURE == moveType) {
			return isValidCapture(current, newX, newY, 1);
		} else {
			return isValidSingleMove(current, newX, newY, 1) || isValidDoubleMove(current, newX, newY, 2);
		}
	}

	private boolean isValidDoubleMove(Location current, int newX, int newY, int deviant) {
		return isValidInitialLocation(current) && current.getxCoordinate() + deviant == newX
				&& current.getyCoordinate() == newY;
	}

	private boolean isValidCapture(Location current, int newX, int newY, int deviant) {
		return current.getxCoordinate() + deviant == newX
				&& (current.getyCoordinate() - deviant == newY || current.getyCoordinate() + deviant == newY);
	}

	private boolean isValidSingleMove(Location current, int newX, int newY, int deviant) {
		return current.getxCoordinate() + deviant == newX && current.getyCoordinate() == newY;
	}

}
