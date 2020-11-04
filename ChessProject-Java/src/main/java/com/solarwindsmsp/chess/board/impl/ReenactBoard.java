package com.solarwindsmsp.chess.board.impl;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.piece.IPiece;

/**
 * Reenact board to be used to reenact specific games
 */
public class ReenactBoard extends AbstractChessBoard {
	
	
	@Override
	public void addPiece(IPiece piece, int xCoordinate, int yCoordinate) {
		Assert.notNull(piece, "Piece to add cannot be null");
		Location location = new Location(xCoordinate, yCoordinate);
		Assert.isTrue(locationExists(location), "Location should be valid");
		addPieceToSquare(location, piece);
	}

}
