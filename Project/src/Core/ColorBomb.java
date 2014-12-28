package Core;

import java.io.IOException;

import javax.swing.ImageIcon;


public class ColorBomb extends SpecialLokum {

	
	private static final long serialVersionUID = 1L;
	
	public static ImageIcon colorBomb = new ImageIcon("res/colorbomb.png");

	/**
	 * 
	 * @requires the player brings the same colored five lokums into the same row
	 * @ensures  the color bomb lokum becomes visible
	 */
	public ColorBomb(){
		
	}
	public static Lokum colorBomb() {
		Lokum colorBomb = new BasicLokum();
		colorBomb.setLokumIcon(new ImageIcon("res/colorbomb.png"));
		colorBomb.setLokumName("ColorBomb");

		return colorBomb;
	}



	/**
	 * 
	 * @param path
	 * @param widht
	 * @return lokums which are implemented as image
	 * @throws IOException
	 * @requires a resource folder which keeps images
	 * @ensures  the image of colorBomb lokum is assigned to its name
	 */

	
	public String toString() {
		return "ColorBomb []";
	}
	
	public boolean repOK(){
		if (colorBomb.getImage()==null) {
			return false;
		}
		else if (colorBomb.getIconHeight() > GameBoard.tileSize){
			return false;	
		}
		else if (colorBomb.getIconWidth() > GameBoard.tileSize){ 
				return false;	
		}
		return false;
		}
		
	}



