package com.solarwindsmsp.chess.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.solarwindsmsp.chess.board.impl.ReenactBoard;
import com.solarwindsmsp.chess.piece.attribute.PieceColor;
import com.solarwindsmsp.chess.piece.attribute.PieceType;
import com.solarwindsmsp.chess.piece.impl.Pawn;

/** Test to {@link PieceFactory} */
public class PieceFactoryTest {

	/** Class under test */
	private PieceFactory testSubject;

	/** setup for tests */
	@Before
	public void setup() {
		testSubject = new PieceFactory();
	}

	/**Piece creation fails as color is null*/
	@Test(expected = IllegalArgumentException.class)
	public void createPieceWhenColorIsNull() {
		testSubject.createPiece(PieceType.PAWN, null, new ReenactBoard());
	}
	

	/**Piece creation fails as Type is null*/
	@Test(expected = IllegalArgumentException.class)
	public void createPieceWhenTypeIsNull() {
		testSubject.createPiece(null, PieceColor.BLACK, new ReenactBoard());
	}
	
	/**Piece creation fails as Type is null*/
	@Test(expected = IllegalArgumentException.class)
	public void createPieceWhenBoardIsNull() {
		testSubject.createPiece(PieceType.PAWN, PieceColor.BLACK, null);
	}
	
	/**Happy flow*/
	@Test
	public void createPieceSuccess() {
		IPiece piece= testSubject.createPiece(PieceType.PAWN, PieceColor.BLACK, new ReenactBoard());
		assertNotNull(piece);
		assertEquals(Pawn.class, piece.getClass());
		assertEquals(PieceColor.BLACK, piece.getPieceColor());
	}
	
	/**For not supported type*/
	@Test(expected = UnsupportedOperationException.class)
	public void createPieceFail() {
		testSubject.createPiece(PieceType.ROOK, PieceColor.BLACK, new ReenactBoard());
	}


}
