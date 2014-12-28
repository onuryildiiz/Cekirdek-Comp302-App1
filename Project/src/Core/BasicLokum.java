package Core;


import java.io.IOException;

import javax.swing.ImageIcon;

public class BasicLokum extends Lokum {

	private static final long serialVersionUID = 1L;

	// public static Lokum lokumm;

	/**
	 * 
	 * @ensures the basic lokums(the green pistachio,the red rose, the brown
	 *          hazelnut, and the white coconut lokums) become visible
	 */
	public BasicLokum() {
		// lokumm=PistachioLokum();
	}

	/**
	 * 
	 * @param path
	 * @param widht
	 * @return lokums which are implemented as image
	 * @throws IOException
	 * @requires a resource folder which keeps images
	 * @ensures the images of basic lokums are assigned to their name
	 */

	public static Lokum pistachio() {
		Lokum pistachio = new BasicLokum();
		pistachio.setLokumIcon(new ImageIcon("res/1pist.png"));
		pistachio.setLokumName("basicPistachio");

		return pistachio;
	}

	public static Lokum hazelnut() {
		Lokum hazelnut = new BasicLokum();
		hazelnut.setLokumIcon(new ImageIcon("res/1hazel.png"));
		hazelnut.setLokumName("basicHazelnut");

		return hazelnut;
	}

	public static Lokum rose() {
		Lokum rose = new BasicLokum();
		rose.setLokumIcon(new ImageIcon("res/1rose.png"));
		rose.setLokumName("basicRose");

		return rose;
	}

	public static Lokum coconut() {
		Lokum coconut = new BasicLokum();
		coconut.setLokumIcon(new ImageIcon("res/1coco.png"));
		coconut.setLokumName("basicCoconut");

		return coconut;
	}

	public String toString() {
		return "BasicLokum []";
	}

	public boolean repOK() {
		return false;

	}

}