package Core;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.Arrays;

public final class GameBoard extends JPanel {

	/**
	 * 
	 */
	static Lokum selectedLokum1 = null;
	static Lokum selectedLokum2 = null;
	private static final long serialVersionUID = 1L;
	public static int row = 10;
	public static int column = 10;
	public static final int tileSize = 600 / column;
	public static final Color bgColor = new Color(150, 175, 175);
	public static int count = 1;
	public static int ct = 1;
	public static Lokum ranLokum;
	public static Lokum lokumArray[][] = new Lokum[row][column];
	public static Icon tempIcon[][] = new Icon[row][column];
	GridLayout GameTable = new GridLayout(row, column, 1, 1);
	public static GameRules GameController;

	// = new Lokum[row][column];
	/**
	 * @throws IOException
	 * @requires: Requires GameRules class to call for the Game Board
	 * @ensures : Loads the basic needs for the Game Board
	 * @modifies: gameBoard
	 */
	public GameBoard(final GameRules gameController) throws IOException {

		GameController = gameController;
		fillTableWithLokum();

	}

	public void fillTableWithLokum() {
		if (repOK()) {

			this.setVisible(true);
			this.setOpaque(true);
			basics();
			// lokumArrayString();
			addLokums(lokumArray);

			// lokumArrayString();

			// lokumArrayString();
			// GameRules.slide();
		} else {
			System.out
					.println("SOMETHING IS WRONG WITH FIELDS IN GAME_BOARD CLASS");
			System.exit(0);
		}

	}

	/**
	 * @throws IOException
	 * @requires: Requires constructor to call for it
	 * @ensures : Sets grids of the Board with the desired background color
	 */
	public void basics() {

		setPreferredSize(new Dimension(getWidth(), getHeight()));
		setLayout(GameTable);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				lokumArray[i][j] = new Lokum();
				lokumArray[i][j].setOpaque(true);
				lokumArray[i][j].setBackground(bgColor);

			}

		}

	}

	/**
	 * @requires: Some lokums to be destroyed
	 * @ensures : Replaces new lokums to the open spaces that occur after
	 *          explosion of the lokums
	 * @modifies: gameBoard
	 */
	public static void replaceNewLokums() {
		int k = 0;

		for (int j = 0; j < column; j++) {
			if (lokumArray[k][j].getName() == "ali") {

				randomLokum();
				lokumArray[k][j].setName(ranLokum.getName());
				lokumArray[k][j].setIcon(ranLokum.getIcon());
				ranLokum.setXofLokum(k);
				ranLokum.setYofLokum(j);

			}

		}

		// GameRules.destroy();
	}

	/**
	 * @requires: |XofLokum1-XofLokum2|=<1 && |YofLokum1-YofLokum2|=<1 & Lokum1
	 *            & Lokum2 doesn't make a consecutive 3/4/5 after the switch
	 * @ensures : XofLokum1=old(XofLokum1) && YofLokum1=old(YofLokum1)
	 *          XofLokum2=old(XofLokum2) && YofLokum2=old(YofLokum2)
	 * @modifies: Lokum1 & Lokum2 & gameBoard
	 */
	public static void tableBack(Lokum Lokum1, Lokum Lokum2) {

		int x = 0;
		int y = 0;
		String name = "";
		Icon icon = null;

		x = Lokum1.getXofLokum();
		y = Lokum1.getYofLokum();
		name = Lokum1.getName();
		icon = Lokum1.getIcon();

		Lokum1.setXofLokum(Lokum2.getXofLokum());
		Lokum1.setYofLokum(Lokum2.getYofLokum());
		Lokum1.setName(Lokum2.getName());
		Lokum1.setIcon(Lokum2.getIcon());
		Lokum2.setXofLokum(x);
		Lokum2.setYofLokum(y);
		Lokum2.setName(name);
		Lokum2.setIcon(icon);

	}

	/**
	 * @requires: Game Board to be opened
	 * @ensures : Fills the table with new random lokums
	 * @modifies: gameBoard
	 */
	public void addLokums(final Lokum[][] lokumArray) {
		if (isTableEmpty()) {

			Lokum lokumcuk = randomLokum();
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					randomLokum();
					if (count < 2) {
						if (lokumcuk.getName() == ranLokum.getName()) {
							lokumArray[i][j] = ranLokum;
							count = 2;
						} else {
							lokumcuk = ranLokum;
							lokumArray[i][j] = ranLokum;
						}
					} else {
						while (lokumcuk.getName() == ranLokum.getName()) {
							randomLokum();
						}
						lokumcuk = ranLokum;
						lokumArray[i][j] = ranLokum;
						count = 1;
					}
					ranLokum.setXofLokum(i);
					ranLokum.setYofLokum(j);
					add(lokumArray[i][j]);
					ranLokum.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent evt) {
							// TODO Auto-generated method stub
							Lokum obj = (Lokum) evt.getSource();

							if (selectedLokum1 == null
									|| selectedLokum2 == null) {
								Border border = BorderFactory.createLineBorder(
										Color.BLUE, 2);
								obj.setBorder(border);

								int x = obj.getXofLokum();
								int y = obj.getYofLokum();
								System.out.println("selected is selected");

								if (selectedLokum1 != null
										&& x == selectedLokum1.getXofLokum()
										&& y == selectedLokum1.getYofLokum()) {
									selectedLokum1.setBorder(null);
									selectedLokum1 = null;
									return;
								}

								else if (selectedLokum2 != null
										&& x == selectedLokum2.getXofLokum()
										&& y == selectedLokum2.getYofLokum())
									return;

								if (selectedLokum1 == null) {
									selectedLokum1 = lokumArray[x][y];
								} else {
									selectedLokum2 = lokumArray[x][y];

									GameRules.swapLokums(selectedLokum1,
											selectedLokum2);
									// if (GameRules.isDestroyed){
									// SidePanel.moveCount--;
									// System.out.println(SidePanel.moveCount);
									// }
									// Remove Borders

									selectedLokum1.setBorder(null);
									selectedLokum2.setBorder(null);

									// Set null
									selectedLokum1 = null;
									selectedLokum2 = null;

								}

							}

						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}
					});

				}

			}

		} else {
			// system.out.println("Table is not initiated empty");
			// system.exit(0);
		}

	}

	public void clearTable() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				lokumArray[i][j].setIcon(null);
				remove(lokumArray[i][j]);
			}
		}

	}

	public static void repaintTable() {
		if (ct == 1) {

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					tempIcon[i][j] = lokumArray[i][j].getIcon();
					lokumArray[i][j].setIcon(null);
				}
			}
			// ct--;
			// } else{

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					lokumArray[i][j].setIcon(tempIcon[i][j]);
				}
			}
			// ct++;
		}
	}

	/**
	 * @requires: A game to be loaded
	 * @ensures : Sets the lokums the right places as they were saved
	 * @modifies: gameBoard
	 */
	public static void setLokums(Lokum[][] savedArray) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				lokumArray[i][j].setIcon(savedArray[i][j].getIcon());
				lokumArray[i][j].setName(savedArray[i][j].getName());
			}

		}

	}

	/**
	 * @requires: Game Board to be open
	 * @ensures : Checks if all the Board is filled with lokums
	 */
	public boolean isTableEmpty() {

		int ct = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (lokumArray[i][j].getIcon() == null) {
					ct++;
				}
			}

		}
		if (ct == 100) {
			return true;
		} else {
			// system.out.println("Table is not initiated empty");
			// system.exit(0);
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameBoard [lokumArray=" + Arrays.toString(lokumArray) + "]";
	}

	public static Lokum randomLokum() {

		if (SidePanel.levelIsTimeBased()) {
			int randomx = (int) (Math.random() * 4 + 1);
			if (randomx == 1) {

				ranLokum = BasicLokum.pistachio();

			} else if (randomx == 2) {
				ranLokum = BasicLokum.rose();

			} else if (randomx == 3) {
				ranLokum = BasicLokum.coconut();

			} else if (randomx == 4) {
				ranLokum = BasicLokum.hazelnut();

			} else if (randomx == 5) {

				int randomit = (int) (Math.random() * 4 + 1);

				if (randomit == 1) {
					// ranLokum = TimeBasedLokum.timeBasedPistachio();

				} else if (randomit == 2) {
					// ranLokum = TimeBasedLokum.timeBasedCoconut();

				} else if (randomit == 3) {
					// ranLokum = TimeBasedLokum.timeBasedRose();

				} else if (randomit == 4) {
					// ranLokum = TimeBasedLokum.timeBasedHazelnut();

				}
			}

			return ranLokum;
		} else {
			int randomix = (int) (Math.random() * 4 + 1);

			if (randomix == 1) {
				ranLokum = BasicLokum.pistachio();

			} else if (randomix == 2) {
				ranLokum = BasicLokum.rose();

			} else if (randomix == 3) {
				ranLokum = BasicLokum.coconut();

			} else if (randomix == 4) {
				ranLokum = BasicLokum.hazelnut();

			}

			return ranLokum;
		}
	}

	public void lokumArrayString() {
		for (int i = 0; i < 10; i++) {
			// system.out.println();
			for (int j = 0; j < 10; j++) {
				// system.out.print(lokumArray[i][j].getName() + " ");

			}
		}
		// system.out.println();
		// system.out.println();
	}

	public static boolean repOK() {
		if (row != column) {
			return false;
		}
		if (tileSize == 0) {
			return false;
		}
		if (lokumArray.length == 0) {
			return false;
		}

		return true;
	}

}