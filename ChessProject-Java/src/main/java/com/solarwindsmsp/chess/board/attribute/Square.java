package com.solarwindsmsp.chess.board.attribute;

import com.solarwindsmsp.chess.piece.IPiece;

/**
 * A square on chess board
 */
public class Square {

	/**
	 * Piece located on square
	 */
	private IPiece piece;

	/**
	 * gets piece from square
	 * 
	 * @return {@link IPiece} on square
	 */
	public IPiece getPiece() {
		return piece;
	}

	/**
	 * sets piece on square
	 * 
	 * @param piece to set
	 */
	public void setPiece(IPiece piece) {
		this.piece = piece;
	}

	/**
	 * Check if piece exists on square
	 * 
	 * @return true if there is a piece on square
	 */
	public boolean isOccupied() {
		return piece != null;
	}

}
