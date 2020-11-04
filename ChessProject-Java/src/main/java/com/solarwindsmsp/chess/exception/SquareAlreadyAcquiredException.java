package com.solarwindsmsp.chess.exception;

import com.solarwindsmsp.chess.board.attribute.Location;

public class SquareAlreadyAcquiredException extends RuntimeException {

	/**Generated serial id*/
	private static final long serialVersionUID = 3650097428324992283L;
	
	/**Default error message format*/
	private static final String MESSAGE = "Location( %d , %d )  is already acquired by other piece";

	
	/**
	 * Constructor for exception 
	 * 
	 * @param location of the square that is acquired
	 */
	public SquareAlreadyAcquiredException(Location location) {
		super(String.format(MESSAGE,location.getxCoordinate(),location.getyCoordinate()));
	}

}
