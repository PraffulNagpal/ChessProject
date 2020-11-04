package com.solarwindsmsp.chess.board.attribute;

/**
 * Identifies a location on board
 */
public class Location {

	/** Identifies xCoordinates */
	private final int xCoordinate;
	/** Identifies yCoordinates */
	private final int yCoordinate;

	/**
	 * Constructor of class with fields
	 * 
	 * @param xCoordinate of the location
	 * @param yCoordinate of the location
	 */
	public Location(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Checks if the location is between given min and max location
	 * 
	 * @param min to check with
	 * @param max to check with
	 * @return true if this location is between given min and max
	 */
	public boolean isBetweenMinMax(Location min, Location max) {
		return xCoordinate >= min.getxCoordinate() && xCoordinate <= max.getxCoordinate()
				&& yCoordinate >= min.getyCoordinate() && yCoordinate <= max.getyCoordinate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

}
