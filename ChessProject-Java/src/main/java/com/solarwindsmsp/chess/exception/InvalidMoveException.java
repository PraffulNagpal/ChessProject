package com.solarwindsmsp.chess.exception;

import com.solarwindsmsp.chess.board.Location;

public class InvalidMoveException extends RuntimeException {

	/** Generated serial id */
	private static final long serialVersionUID = 607010614660757324L;

	/** Default error message */
	private static final String MESSAGE = "Invalid move from location (%d , %d) to location ( %d , %d )";

	/**
	 * Constructor for class
	 * 
	 * @param initial represents piece's current location
	 * @param destination represents where piece tried to moves
	 */
	public InvalidMoveException(Location initial, Location destination) {
		super(String.format(MESSAGE, initial.getxCoordinate(), initial.getyCoordinate(), destination.getxCoordinate(),
				destination.getyCoordinate()));
	}

}
