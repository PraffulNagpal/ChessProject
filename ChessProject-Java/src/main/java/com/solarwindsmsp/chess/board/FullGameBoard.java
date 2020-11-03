package com.solarwindsmsp.chess.board;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.piece.Piece;

public class FullGameBoard extends AbstractChessBoard {

	@Override
	public void addPiece(Piece piece, int xCoordinate, int yCoordinate) {
		Assert.notNull(piece, "Piece to add cannot be null");
		Location location = new Location(xCoordinate, yCoordinate);
		Assert.isTrue(piece.isValidInitialLocation(location), "Initial location of piece should be valid");
		Assert.isTrue(locationExists(location), "Location should be valid");
		addPieceToSpot(location, piece);
	}

}
