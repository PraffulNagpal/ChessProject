package com.solarwindsmsp.chess.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.solarwindsmsp.chess.exception.SpotAlreadyAcquired;
import com.solarwindsmsp.chess.piece.MovementType;
import com.solarwindsmsp.chess.piece.Piece;

public abstract class AbstractChessBoard implements IChessBoard {
	
	public static int MAX_BOARD_WIDTH = 7;
	public static int MAX_BOARD_HEIGHT = 7;
	private static final Location DEFEATED_PIECE_LOCATION = new Location(-1, -1);

	protected Map<Location, Spot> spots;

	public AbstractChessBoard() {
		super();
		spots = new HashMap<Location, Spot>(64);
		for (int xCoordinate = 0; xCoordinate <= MAX_BOARD_WIDTH; xCoordinate++) {
			for (int yCoordinate = 0; yCoordinate <= MAX_BOARD_HEIGHT; yCoordinate++) {
				Location location = new Location(xCoordinate, yCoordinate);
				spots.put(location, new Spot());
			}
		}
	}
	
	protected void addPieceToSpot(Location location,Piece piece) {
		Spot square = spots.get(location);
		if (null != square && !square.isOccupied()) {
			square.setPiece(piece);
			piece.setLocation(location);
		} else {
			throw new SpotAlreadyAcquired(location);
		}
	}

	@Override
	public void movePiece(MovementType movementType, Piece piece, int xCoordinate, int yCoordinate) {
		Assert.notNull(movementType, "MovementType cannot be null");
		Assert.notNull(piece, "Piece to move cannot be null");
		Assert.notNull(piece.getLocation(), "Current location of piece cannot be null");
		Assert.isTrue(locationExists(piece.getLocation()), "Piece should be located at valid board location");
		Assert.notNull(piece.getPieceColor(), "Color of piece cannot be null");
		Assert.isTrue(xCoordinate >= 0 && xCoordinate <= MAX_BOARD_WIDTH, "xCoordinate should be between 0 and 7");
		Assert.isTrue(yCoordinate >= 0 && yCoordinate <= MAX_BOARD_HEIGHT, "yCoordinate should be between 0 and 7");

		Spot currentSquare = spots.get(piece.getLocation());
		Assert.isTrue(currentSquare.getPiece() == piece, "Piece position is fraudulent");

		Location newLocation = new Location(xCoordinate, yCoordinate);
		Spot toMoveOnTo = spots.get(newLocation);

		if (MovementType.MOVE == movementType) {
			if (toMoveOnTo.isOccupied()) {
				throw new SpotAlreadyAcquired(newLocation);
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

	public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
		return locationExists(new Location(xCoordinate, yCoordinate));
	}

	protected boolean locationExists(Location location) {
		return spots.containsKey(location);
	}

}
