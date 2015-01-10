package Core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class SidePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int currentScore = 0;
	public static int moveCount = 50;
	public static int level = 1;
	public static GameRules GameController;

	/**
	 * 
	 * @param GameRules
	 * @requires : game screen should be opened.
	 * @ensures: side will be seen in the screen near the game board.
	 * @modifies : target score, level, move count, current score will exists in
	 *           the side panel.
	 */
	public SidePanel(GameRules GameRules) {
		setPreferredSize(new Dimension(275, 600));
		setBackground(new Color(125, 150, 150));
		GameController = GameRules;

		this.setLayout(null);

		JButton saveButton = new JButton("Save");

		saveButton.setOpaque(true);
		saveButton.setVisible(true);
		saveButton.setBackground(new Color(125, 150, 150));
		saveButton.setForeground(new Color(128, 21, 128));
		saveButton.setFont(new Font("Jokerman", Font.BOLD, 30));

		saveButton.setBounds(15, 300, 250, 75);
		this.add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent m) {
				String gameName = JOptionPane
						.showInputDialog("Please Enter a Name for Your Save");
				saveGame(gameName);
			}
		});

		JButton retryButton = new JButton("Retry");

		retryButton.setOpaque(true);
		retryButton.setVisible(true);
		retryButton.setBackground(new Color(125, 150, 150));
		retryButton.setForeground(new Color(128, 21, 128));
		retryButton.setFont(new Font("Jokerman", Font.BOLD, 30));

		retryButton.setBounds(15, 375, 250, 75);
		this.add(retryButton);

		retryButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent m) {
				retry();
			}
		});

		JButton quitButton = new JButton("Quit");
		quitButton.setOpaque(true);
		quitButton.setVisible(true);
		quitButton.setBackground(new Color(125, 150, 150));
		quitButton.setForeground(new Color(128, 21, 128));
		quitButton.setFont(new Font("Jokerman", Font.BOLD, 30));

		quitButton.setBounds(15, 450, 250, 75);
		this.add(quitButton);

		quitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent m) {
				quit();
			}
		});

	}

	/**
	 * @param g
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();

		g.setColor(Color.white);
		g.setFont(new Font("Jokerman", Font.BOLD, 20));
		g.drawString("Target Score: " + getTargetScore(), 4, 90);
		g.drawString("Level: " + level, 4, 130);
		g.drawString("Moves Left: " + moveCount, 4, 170);
		g.drawString("Score: " + currentScore * level, 4, 210);
		g.setFont(new Font("Jokerman", Font.BOLD, 26));
		// g.setColor(new Color(128, 21, 128));
		// g.drawString("Made by Cekirdek", 4, 550);

	}

	/**
	 * @requires : 2 lokums to be switched
	 * @ensures : decreases move count by 1
	 * @modifies : while move count decreases, score increases
	 */
	public void updateMove() {

	}

	/**
	 * @requires : 2 lokums to be switched
	 * @ensures : update score according to the type of the movement.
	 * @modifies : while score increases, move count decreases
	 */

	public static boolean levelIsTimeBased() {
		if (level%3==2) {
			return true;			
		} else{	
			return false;			
		}	

	}

	/**
	 * @requires: the game should be ended.
	 * @ensures : new game screen opens from the current level.
	 * @modifies : new game screen opens,move count and target score should be
	 *           settled according to the current level
	 * 
	 */
	public void retry() {

		// ececcece
		int response = JOptionPane.showConfirmDialog(null,
				"Do you want to retry?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			System.out.println("No button clicked");
		} else {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GameBoard gameBoard = GameRules.getGameBoard();
						gameBoard.clearTable();
						GameBoard.selectedLokum1 = null;
						GameBoard.selectedLokum2 = null;
						currentScore = 0;
						moveCount = getMoveCount();

						gameBoard.fillTableWithLokum();

						GameController.initGame();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("The game is being restarted now");
				}
			});
		}

		// ecececce
	}

	/**
	 * @requires : game should be opened.
	 * @ensures : current game will be closed.
	 * @modifies : game screen will be closed, user is asked to whether to save
	 *           the game or not to.
	 */

	public void quit() {
		int response = JOptionPane.showConfirmDialog(null,
				"Do you want to save game?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			System.out.println("No button clicked");
			System.exit(0);
		} else {
			System.out.println("Yes button clicked");
			String gameName = JOptionPane
					.showInputDialog("Please Enter A Game Name");
			saveGame(gameName);
			System.exit(0);
			// ececeececece
		}

	}

	/**
	 * 
	 * @param s
	 * @requires : there should be current active game.
	 * @ensures : game screen, side panel, move count, target score, level and
	 *          current score should be saved.
	 * @modifies : it saves the mentioned informations to the XML File
	 */
	public void saveGame(String s) {

		XMLObject xml = new XMLObject();
		xml.setLokumArray(getLokumArray());
		xml.setCurrentScore(currentScore);
		xml.setMoveCount(moveCount);
		xml.setLevel(getLevel());
		xml.setGameName(s);

		try {
			SaveGame.write(xml);
		} catch (Exception e) {

		}

	}

	public Lokum[][] getLokumArray() {
		return GameBoard.lokumArray;
	}

	public static int getTargetScore() {
		return setTargetScore();
	}

	public static int getMoveCount() {
		return setMoveCount();
	}

	public int getLevel() {
		return level;

	}

	@Override
	public String toString() {
		return "SidePanel [myLevel=" + level + "]";
	}

	public boolean repOk() {
		if (moveCount == 0) {
			return false;
		}
		if (currentScore != 0) {
			return false;
		}
		return true;
	}

	public static int setMoveCount() {
		return 55 - level * 5;

	}

	public static int setTargetScore() {
		return level * 5000;

	}

}