package com.solarwindsmsp.chess.piece;

import com.solarwindsmsp.chess.board.Location;

public interface Piece {

	void setLocation(Location location);

	boolean isValidInitialLocation(Location location);

	void move(MovementType movementType, int newX, int newY);

	Location getLocation();
	
	PieceColor getPieceColor();
	

}
