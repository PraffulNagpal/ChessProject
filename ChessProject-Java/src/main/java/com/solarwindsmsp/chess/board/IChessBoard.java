package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.piece.MovementType;
import com.solarwindsmsp.chess.piece.Piece;

public interface IChessBoard {

	void addPiece(Piece piece, int xCoordinate, int yCoordinate);

	void movePiece(MovementType movementType, Piece piece, int xCoordinate, int yCoordinate);

	boolean isLegalBoardPosition(int xCoordinate, int yCoordinate);

}
