package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.exception.InvalidMoveException;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;

/**
 * Basic implementation of {@link IPiece}
 */
public abstract class AbstractPiece implements IPiece {

	/**Chessboard on which piece is located */
	private IChessBoard chessBoard;
	/**Location on chessboard*/
	private Location location;
	/**Color of piece*/
	private final PieceColor pieceColor;

	/**
	 * Default constructor for class
	 * 
	 * @param chessBoard to set piece on
	 * @param pieceColor of piece
	 */
	public AbstractPiece(IChessBoard chessBoard, PieceColor pieceColor) {
		super();
		this.chessBoard = chessBoard;
		this.pieceColor = pieceColor;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	
	/**
	 * Gets {@link IChessBoard} on which piece is located
	 * @return {@link IChessBoard}
	 */
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

	/**
	 * Checks the move from current to new location is valid for movement type
	 *  
	 * @param current on which piece is located
	 * @param newX to move to
	 * @param newY to move to 
	 * @param movementType of movement
	 * @return true if it is a valid move
	 */
	protected abstract boolean isValidMove(Location current, int newX, int newY,MovementType movementType);

}
