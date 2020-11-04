package com.solarwindsmsp.chess.board.impl;

import com.solarwindsmsp.chess.piece.PieceFactory;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;

/**
 *Game board can be created for special games
 */
public class SpecificGameSetup extends ReenactBoard {

	public SpecificGameSetup() {
		super();

		addPiece(new PieceFactory().createPiece(PieceType.PAWN, PieceColor.BLACK, this), 1, 1);
		addPiece(new PieceFactory().createPiece(PieceType.PAWN, PieceColor.WHITE, this), 6, 6);
	}

}
