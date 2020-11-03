package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.Location;
import com.solarwindsmsp.chess.exception.InvalidMoveException;

public abstract class AbstractPiece implements Piece {

	private IChessBoard chessBoard;
	private Location location;
	private final PieceColor pieceColor;

	public AbstractPiece(IChessBoard chessBoard, PieceColor pieceColor) {
		super();
		this.chessBoard = chessBoard;
		this.pieceColor = pieceColor;
	}

	public Location getLocation() {
		return location;
	}

	public int getXCoordinate() {
		return location.getxCoordinate();
	}

	public int getYCoordinate() {
		return location.getyCoordinate();
	}

	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	public IChessBoard getChessBoard() {
		return chessBoard;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public void move(MovementType movementType, int newX, int newY) {
		Location current = getLocation();
		if (isValidMove(current, newX, newY, movementType)) {
			getChessBoard().movePiece(movementType, this, newX, newY);
		} else {
			throw new InvalidMoveException(current,new Location(newX, newY));
		}
	}

	protected abstract boolean isValidMove(Location current, int newX, int newY,MovementType movementType);

}
