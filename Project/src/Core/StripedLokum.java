package Core;

import javax.swing.ImageIcon;

public class StripedLokum extends SpecialLokum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ImageIcon crossStripedPistachio = new ImageIcon(
			"res/2pist.png");
	public static ImageIcon crossStripedHazelnut = new ImageIcon(
			"res/2hazel.png");
	public static ImageIcon crossStripedRose = new ImageIcon("res/2rose.png");
	public static ImageIcon crossStripedCoconut = new ImageIcon("res/2coco.png");
	public static ImageIcon longitudinalStripedPistachio = new ImageIcon(
			"res/3pist.png");
	public static ImageIcon longitudinalStripedHazelnut = new ImageIcon(
			"res/3hazel.png");
	public static ImageIcon longitudinalStripedRose = new ImageIcon(
			"res/3rose.png");
	public static ImageIcon longitudinalStripedCoconut = new ImageIcon(
			"res/3coco.png");

	/**
	 * 
	 * @requires the player brings the same typed four lokums into the same row
	 * @ensures the striped Lokum becomes visible
	 */
	public StripedLokum() {

	}

	/**
	 * 
	 * @requires a striped lokum and two or more basic lokums, which are the
	 *           same root with the wrapped lokum, comes into the same row
	 * @ensures all the lokums which are located in the direction of strips
	 *          destroy.
	 * @modifies gameBoard, the board brings new lokums instead of the destroyed
	 *           ones.
	 */

	public static Lokum crossStripedPistachio() {
		Lokum pistachio = new BasicLokum();
		pistachio.setLokumIcon(new ImageIcon("res/2pist.png"));
		pistachio.setLokumName("crossStripedPistachio");

		return pistachio;
	}

	public static Lokum crossStripedHazelnut() {
		Lokum hazelnut = new BasicLokum();
		hazelnut.setLokumIcon(new ImageIcon("res/2hazel.png"));
		hazelnut.setLokumName("crossStripedHazelnut");

		return hazelnut;
	}

	public static Lokum crossStripedRose() {
		Lokum rose = new BasicLokum();
		rose.setLokumIcon(new ImageIcon("res/2rose.png"));
		rose.setLokumName("crossStripedRose");

		return rose;
	}
	
	public static Lokum crossStripedCoconut() {
		Lokum coconut = new BasicLokum();
		coconut.setLokumIcon(new ImageIcon("res/2coco.png"));
		coconut.setLokumName("crossStripedCoconut");

		return coconut;
	}
	
	public static Lokum longitudinalStripedPistachio() {
		Lokum pistachio = new BasicLokum();
		pistachio.setLokumIcon(new ImageIcon("res/3pist.png"));
		pistachio.setLokumName("longitudinalStripedPistachio");

		return pistachio;
	}

	public static Lokum longitudinalStripedHazelnut() {
		Lokum hazelnut = new BasicLokum();
		hazelnut.setLokumIcon(new ImageIcon("res/3hazel.png"));
		hazelnut.setLokumName("longitudinalStripedHazelnut");

		return hazelnut;
	}

	public static Lokum longitudinalStripedRose() {
		Lokum rose = new BasicLokum();
		rose.setLokumIcon(new ImageIcon("res/3rose.png"));
		rose.setLokumName("longitudinalStripedRose");

		return rose;
	}

	public static Lokum longitudinalStripedCoconut() {
		Lokum coconut = new BasicLokum();
		coconut.setLokumIcon(new ImageIcon("res/3coco.png"));
		coconut.setLokumName("longitudinalStripedCoconut");

		return coconut;
	}


	public boolean repOK() {
		if (crossStripedPistachio.getImage()==null || crossStripedCoconut.getImage()==null || crossStripedHazelnut.getImage()==null||crossStripedRose.getImage()==null
				|| longitudinalStripedPistachio.getImage()==null || longitudinalStripedCoconut.getImage()==null ||longitudinalStripedHazelnut.getImage()==null||longitudinalStripedRose.getImage()==null) {
			return false;
		}
		else if (crossStripedPistachio.getIconHeight() > GameBoard.tileSize || crossStripedCoconut.getIconHeight() > GameBoard.tileSize || crossStripedHazelnut.getIconHeight()>GameBoard.tileSize || crossStripedRose.getIconHeight()>GameBoard.tileSize ||
				longitudinalStripedPistachio.getIconHeight() > GameBoard.tileSize || longitudinalStripedCoconut.getIconHeight() > GameBoard.tileSize || longitudinalStripedHazelnut.getIconHeight()>GameBoard.tileSize || longitudinalStripedRose.getIconHeight()>GameBoard.tileSize){
			return false;	
		}
		else if (crossStripedPistachio.getIconWidth() > GameBoard.tileSize || crossStripedCoconut.getIconWidth() > GameBoard.tileSize || crossStripedHazelnut.getIconWidth()>GameBoard.tileSize || crossStripedRose.getIconWidth()>GameBoard.tileSize ||
				longitudinalStripedPistachio.getIconWidth() > GameBoard.tileSize || longitudinalStripedCoconut.getIconWidth() > GameBoard.tileSize || longitudinalStripedHazelnut.getIconWidth()>GameBoard.tileSize || longitudinalStripedRose.getIconWidth()>GameBoard.tileSize){
			return false;	
		}
		return false;
		}

	public String toString() {
		return "StripedLokum []";
	}

}
