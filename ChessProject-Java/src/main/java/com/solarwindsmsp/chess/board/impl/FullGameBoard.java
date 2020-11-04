package com.solarwindsmsp.chess.board.impl;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.board.AbstractChessBoard;
import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.piece.IPiece;

/**
 *FullGameBoard to be used to create game with all pieces at initial location
 */
public class FullGameBoard extends AbstractChessBoard {

	@Override
	public void addPiece(IPiece piece, int xCoordinate, int yCoordinate) {
		Assert.notNull(piece, "Piece to add cannot be null");
		Location location = new Location(xCoordinate, yCoordinate);
		Assert.isTrue(piece.isValidInitialLocation(location), "Initial location of piece should be valid");
		Assert.isTrue(locationExists(location), "Location should be valid");
		addPieceToSquare(location, piece);
	}

}
