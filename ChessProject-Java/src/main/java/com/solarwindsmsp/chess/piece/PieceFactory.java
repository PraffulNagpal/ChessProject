package com.solarwindsmsp.chess.piece;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.board.IChessBoard;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;
import com.solarwindsmsp.chess.piece.impl.Pawn;

/**
 * Factory to create piece of all types
 */
public class PieceFactory {

	/**
	 * Creates piece of given type , color and chessBoard
	 * 
	 * @param type of piece
	 * @param color of piece
	 * @param chessBoard to add piece on
	 * @return
	 */
	public IPiece createPiece(PieceType type, PieceColor color, IChessBoard chessBoard) {
		Assert.notNull(type, "type cannot be null");
		Assert.notNull(color, "color cannot be null");
		Assert.notNull(chessBoard, "chessBoard cannot be null");

		switch (type) {
		case PAWN:

			return new Pawn(chessBoard, color);

		default:
			throw new UnsupportedOperationException();
		}

	}

}
