package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.Piece;

public class Spot {

	private Piece piece;

	public Spot(Piece piece) {
		super();
		this.piece = piece;
	}

	public Spot() {
		super();
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public boolean isOccupied() {
		return piece!=null;
	}
	

}
