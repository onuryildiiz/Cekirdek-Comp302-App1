package Core;


import javax.swing.ImageIcon;


public class TimeBasedLokum extends SpecialLokum{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @requires the player destroys two groups of three lokums at the same time
	 * @ensures the wrapped Lokum becomes visible
	 */
	public TimeBasedLokum() {
		
	}

	
	public static Lokum timeBasedPistachio() {
		Lokum pistachio = new BasicLokum();
		pistachio.setLokumIcon(new ImageIcon("res/5pist.png"));
		pistachio.setLokumName("timeBasedPistachio");

		return pistachio;
	}

	public static Lokum timeBasedHazelnut() {
		Lokum hazelnut = new BasicLokum();
		hazelnut.setLokumIcon(new ImageIcon("res/5hazel.png"));
		hazelnut.setLokumName("timeBasedHazelnut");

		return hazelnut;
	}

	public static Lokum timeBasedRose() {
		Lokum rose = new BasicLokum();
		rose.setLokumIcon(new ImageIcon("res/5rose.png"));
		rose.setLokumName("timeBasedRose");

		return rose;
	}

	public static Lokum timeBasedCoconut() {
		Lokum coconut = new BasicLokum();
		coconut.setLokumIcon(new ImageIcon("res/5coco.png"));
		coconut.setLokumName("wrappedCoconut");

		return coconut;
	}
	
	


}
