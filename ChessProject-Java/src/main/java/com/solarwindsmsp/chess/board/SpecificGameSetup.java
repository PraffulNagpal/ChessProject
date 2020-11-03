package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

public class SpecificGameSetup extends ReenactBoard {

	public SpecificGameSetup() {
		super();
		addPiece(new Pawn(this, PieceColor.BLACK), 1, 1);
		addPiece(new Pawn(this, PieceColor.WHITE), 6, 6);
	}

	
}
