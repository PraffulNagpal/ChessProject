package com.solarwindsmsp.chess.board;

public class Location {

	private final int xCoordinate;
	private final int yCoordinate;

	public Location(int xCoordinate, int yCoordinate) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
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
