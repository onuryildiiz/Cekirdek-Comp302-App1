package Core;


import javax.swing.ImageIcon;


public class WrappedLokum extends SpecialLokum{

	
	private static final long serialVersionUID = 1L;
	
	public static ImageIcon wrappedPistachio = new ImageIcon("res/4pist.png");
	public static ImageIcon wrappedHazelnut = new ImageIcon("res/4hazel.png");
	public static ImageIcon wrappedRose = new ImageIcon("res/4rose.png");
	public static ImageIcon wrappedCoconut = new ImageIcon("res/4coco.png");

	/**
	 * 
	 * @requires the player destroys two groups of three lokums at the same time
	 * @ensures the wrapped Lokum becomes visible
	 */
	public WrappedLokum() {
		
	}

	
	public static Lokum wrappedPistachio() {
		Lokum pistachio = new BasicLokum();
		pistachio.setLokumIcon(new ImageIcon("res/4pist.png"));
		pistachio.setLokumName("wrappedPistachio");

		return pistachio;
	}

	public static Lokum wrappedHazelnut() {
		Lokum hazelnut = new BasicLokum();
		hazelnut.setLokumIcon(new ImageIcon("res/4hazel.png"));
		hazelnut.setLokumName("wrappedHazelnut");

		return hazelnut;
	}

	public static Lokum wrappedRose() {
		Lokum rose = new BasicLokum();
		rose.setLokumIcon(new ImageIcon("res/4rose.png"));
		rose.setLokumName("wrappedRose");

		return rose;
	}

	public static Lokum wrappedCoconut() {
		Lokum coconut = new BasicLokum();
		coconut.setLokumIcon(new ImageIcon("res/4coco.png"));
		coconut.setLokumName("wrappedCoconut");

		return coconut;
	}
	/**
	 * 
	 * @requires a wrapped lokum and two or more basic lokums, which are the same root with the wrapped lokum, comes into the same row
	 * @ensures all the lokums which are the neighbors of the wrapped lokum destroy.
	 * @modifies gameBoard, the board brings new lokums instead of the destroyed ones.
	 */

	
	
	public String toString() {
		return "WrappedLokum []";
	}

	public boolean repOK(){
		if (wrappedPistachio.getImage()==null || wrappedCoconut.getImage()==null || wrappedHazelnut.getImage()==null||wrappedRose.getImage()==null) {
			return false;
		}
		else if (wrappedPistachio.getIconHeight() > GameBoard.tileSize || wrappedCoconut.getIconHeight() > GameBoard.tileSize || wrappedHazelnut.getIconHeight()>GameBoard.tileSize || wrappedRose.getIconHeight()>GameBoard.tileSize){
			return false;	
		}
		else if (wrappedPistachio.getIconWidth() > GameBoard.tileSize || wrappedCoconut.getIconWidth() > GameBoard.tileSize || wrappedHazelnut.getIconWidth()>GameBoard.tileSize || wrappedRose.getIconWidth()>GameBoard.tileSize){
			return false;	
		}
		return false;
		}


}
