package com.solarwindsmsp.chess.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.board.attribute.Location;
import com.solarwindsmsp.chess.board.attribute.Square;
import com.solarwindsmsp.chess.exception.SquareAlreadyAcquiredException;
import com.solarwindsmsp.chess.piece.IPiece;
import com.solarwindsmsp.chess.piece.attribute.MovementType;

/**
 * Abstract chess board with basic implementation
 */
public abstract class AbstractChessBoard implements IChessBoard {

	/** Max board width */
	public static int MAX_BOARD_WIDTH = 7;
	/** Max board height */
	public static int MAX_BOARD_HEIGHT = 7;
	/** Location of piece that are defeated */
	private static final Location DEFEATED_PIECE_LOCATION = new Location(-1, -1);
	/** Holds all the squares on the board */
	protected Map<Location, Square> squares;

	/** Initializes board with all required squares */
	public AbstractChessBoard() {
		squares = new HashMap<Location, Square>(64);
		for (int xCoordinate = 0; xCoordinate <= MAX_BOARD_WIDTH; xCoordinate++) {
			for (int yCoordinate = 0; yCoordinate <= MAX_BOARD_HEIGHT; yCoordinate++) {
				Location location = new Location(xCoordinate, yCoordinate);
				squares.put(location, new Square());
			}
		}
	}

	/**
	 * Tries to add a piece to square at given location
	 *
	 * @param location to add piece to 
	 * @param piece to be added
	 */
	protected void addPieceToSquare(Location location, IPiece piece) {
		Square square = squares.get(location);
		if (null != square && !square.isOccupied()) {
			square.setPiece(piece);
			piece.setLocation(location);
		} else {
			throw new SquareAlreadyAcquiredException(location);
		}
	}

	@Override
	public void movePiece(MovementType movementType, IPiece piece, int xCoordinate, int yCoordinate) {
		Assert.notNull(movementType, "MovementType cannot be null");
		Assert.notNull(piece, "Piece to move cannot be null");
		Assert.notNull(piece.getLocation(), "Current location of piece cannot be null");
		Assert.isTrue(locationExists(piece.getLocation()), "Piece should be located at valid board location");
		Assert.notNull(piece.getPieceColor(), "Color of piece cannot be null");
		Assert.isTrue(xCoordinate >= 0 && xCoordinate <= MAX_BOARD_WIDTH, "xCoordinate should be between 0 and 7");
		Assert.isTrue(yCoordinate >= 0 && yCoordinate <= MAX_BOARD_HEIGHT, "yCoordinate should be between 0 and 7");

		Square currentSquare = squares.get(piece.getLocation());
		Assert.isTrue(currentSquare.getPiece() == piece, "Piece position is fraudulent");

		Location newLocation = new Location(xCoordinate, yCoordinate);
		Square toMoveOnTo = squares.get(newLocation);

		if (MovementType.MOVE == movementType) {
			if (toMoveOnTo.isOccupied()) {
				throw new SquareAlreadyAcquiredException(newLocation);
			}

			currentSquare.setPiece(null);
			toMoveOnTo.setPiece(piece);
			piece.setLocation(newLocation);
		} else {
			Assert.isTrue(toMoveOnTo.isOccupied(), "Square to move to is not occupied,cannot perform a capture move");
			Assert.isTrue(piece.getPieceColor() != toMoveOnTo.getPiece().getPieceColor(),
					"Piece color of evicting piece is same as acquiring piece,move is illeagal");
			currentSquare.setPiece(null);
			toMoveOnTo.getPiece().setLocation(DEFEATED_PIECE_LOCATION);
			toMoveOnTo.setPiece(piece);
			piece.setLocation(newLocation);
		}
	}

	@Override
	public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
		return locationExists(new Location(xCoordinate, yCoordinate));
	}

	/**
	 * Checks if location exists on board or not
	 * 
	 * @param location to check 
	 * @return true if exists
	 */
	protected boolean locationExists(Location location) {
		return squares.containsKey(location);
	}

}
