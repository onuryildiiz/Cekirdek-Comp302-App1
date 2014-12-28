/**
 * 
 */
package Core;

import static org.junit.Assert.*;

import java.io.IOException;

import Core.GameBoard;
import org.junit.Test;

/**
 * @author Selcuk
 * 
 */
public class Test_GameBoard {

	/**
	 * Test method for {@link Core.GameBoard#GameBoard(Core.GameRules)}.
	 * 
	 * @throws IOException
	 *             Test method for null GameRules input to GameBoard.
	 */
	@Test
	public void testNullGameRules() throws IOException {
		GameRules GameController = new GameRules();
		GameController = null;
		assertNull("GameController is Null", GameController);
		GameBoard gameBoard = new GameBoard(GameController);
		assertEquals(
				"GameBoard(GameRules GameController) is equal to GameBoard(null)",
				gameBoard, new GameBoard(null));

	}

	/**
	 * Test method for {@link Core.GameBoard#GameBoard(Core.GameRules)}.
	 * 
	 * @throws IOException
	 *             Test method for row or column is less than zero giving us 4
	 *             test cases. And also we're testing it for column != row.
	 */
	@Test
	public void testRowColumn() {
		GameBoard.column = 10;
		GameBoard.row = 10;
		for (int i = 0; i < GameBoard.row; i++) {
			for (int j = 0; j < GameBoard.column; j++) {
				GameBoard.lokumArray[i][j] = new Lokum();
				GameBoard.lokumArray[i][j].setOpaque(true);

			}

		}

		GameBoard.column = 10;
		GameBoard.row = -10;
		for (int i = 0; i < GameBoard.row; i++) {
			for (int j = 0; j < GameBoard.column; j++) {
				GameBoard.lokumArray[i][j] = new Lokum();
				GameBoard.lokumArray[i][j].setOpaque(true);

			}

		}
		fail("row < 0");

		GameBoard.column = -10;
		GameBoard.row = 10;
		for (int i = 0; i < GameBoard.row; i++) {
			for (int j = 0; j < GameBoard.column; j++) {
				GameBoard.lokumArray[i][j] = new Lokum();
				GameBoard.lokumArray[i][j].setOpaque(true);

			}

		}
		fail("column < 0");
		GameBoard.column = -10;
		GameBoard.row = -10;
		for (int i = 0; i < GameBoard.row; i++) {
			for (int j = 0; j < GameBoard.column; j++) {
				GameBoard.lokumArray[i][j] = new Lokum();
				GameBoard.lokumArray[i][j].setOpaque(true);

			}

		}
		fail("row & column < 0");

	}

	/**
	 * Test method for {@link Core.GameBoard#basics()}.
	 */
	@Test
	public void testBasics() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#tableBack(Core.Lokum, Core.Lokum)}.
	 */
	@Test
	public void testTableBack() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#addLokums(Core.Lokum[][])}.
	 */
	@Test
	public void testAddLokums() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#clearTable()}.
	 */
	@Test
	public void testClearTable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#repaintTable()}.
	 */
	@Test
	public void testRepaintTable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#setLokums(Core.Lokum[][])}.
	 */
	@Test
	public void testSetLokums() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#isTableEmpty()}.
	 */
	@Test
	public void testIsTableEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Core.GameBoard#randomLokum()}.
	 */
	@Test
	public void testRandomLokum() {
		fail("Not yet implemented");
	}

}
