package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.piece.attribute.MovementType;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;

/**
 * Define basic capabilities of a piece
 */
public interface IPiece {

	/**
	 * Sets location of the piece
	 * 
	 * @param location to set
	 */
	void setLocation(Location location);

	/**
	 * Checks if given location is valid initial location for piece
	 * 
	 * @param location to check
	 * @return true if location is valid initial location for piece
	 */
	boolean isValidInitialLocation(Location location);

	/**
	 * Moves the piece to new location
	 * 
	 * @param movementType identifies if type of movement
	 * @param newX         identifies new location
	 * @param newY         identifies new location
	 */
	void move(MovementType movementType, int newX, int newY);

	/**
	 * Gets current location of the piece
	 * 
	 * @return {@link Location}
	 */
	Location getLocation();

	/**
	 * Gets piece color
	 * 
	 * @return {@link PieceColor}
	 */
	PieceColor getPieceColor();

}
