package com.solarwindsmsp.chess.exception;

import com.solarwindsmsp.chess.board.Location;

public class SpotAlreadyAcquired extends RuntimeException {

	/**Generated serial id*/
	private static final long serialVersionUID = 3650097428324992283L;
	
	/**Default error message format*/
	private static final String MESSAGE = "Location( %d , %d )  is already acquired by other piece";

	
	/**
	 * Constructor for exception 
	 * 
	 * @param location of the spot that is acquired
	 */
	public SpotAlreadyAcquired(Location location) {
		super(String.format(MESSAGE,location.getxCoordinate(),location.getyCoordinate()));
	}

}
