package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.IPiece;
import com.solarwindsmsp.chess.piece.attribute.MovementType;

/**
 *Defines basic chess board
 */
public interface IChessBoard {

	/**
	 * Adds piece at a specific location
	 * 
	 * @param piece to be added
	 * @param xCoordinate where piece needs to be added
	 * @param yCoordinate where piece needs to be added
	 */
	void addPiece(IPiece piece, int xCoordinate, int yCoordinate);

	/**
	 * Moves piece from current location to new location
	 * 
	 * @param movementType type of move
	 * @param piece to be moved
	 * @param xCoordinate where piece needs to be moved to 
	 * @param yCoordinate where piece needs to be moved to 
	 */
	void movePiece(MovementType movementType, IPiece piece, int xCoordinate, int yCoordinate);

	/**
	 * Checks if position is legal
	 * 
	 * @param xCoordinate identifies location
	 * @param yCoordinate identifies location
	 * @return true if location exists on board
	 */
	boolean isLegalBoardPosition(int xCoordinate, int yCoordinate);

}
