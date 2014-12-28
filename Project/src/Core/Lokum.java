package Core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
//import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Lokum extends JLabel {

	private static final long serialVersionUID = 1L;

	private int XofLokum;
	private int YofLokum;
	public Icon LokumIcon;
	public String LokumName;

	public Lokum() {
		// Icon LokumIcon=new ImageIcon("res/1pist.png");

	}

	public static Image loadImage(String path) throws IOException {

		BufferedImage loadImage = ImageIO.read(Lokum.class.getResource(path));
		// Image image = new Image[loadImage.getWidth()/ widht];

		return loadImage;
	}

	public int getXofLokum() {
		return XofLokum;
	}

	public void setXofLokum(int XofLokum) {
		this.XofLokum = XofLokum;
	}

	public int getYofLokum() {
		return YofLokum;
	}

	public void setYofLokum(int YofLokum) {
		this.YofLokum = YofLokum;
	}

	public String getLokumName() {
		return LokumName;
	}

	public void setLokumName(String LokumName) {
		setName(LokumName);
	}

	public Icon getLokumIcon() {
		return LokumIcon;
	}

	public void setLokumIcon(Icon LokumIcon) {
		setIcon(LokumIcon);
	}

	public static boolean isSpecial(Lokum lokum) {
		if (StripedLokum.isStripedLokum(lokum)
				|| WrappedLokum.isWrappedLokum(lokum)
				|| ColorBomb.isColorBomb(lokum)) {
			return true;
		}
		return false;
	}

	public static boolean hasSameRoot(Lokum lokum1, Lokum lokum2) {
		if ((lokum1.getName().equals(BasicLokum.coconut().getName()) || 
			 lokum1.getName().equals(StripedLokum.crossStripedCoconut().getName()) ||
			 lokum1.getName().equals(StripedLokum.longitudinalStripedCoconut().getName()) ||
		   	 lokum1.getName().equals(WrappedLokum.wrappedCoconut().getName()))
				&&
			(lokum2.getName().equals(BasicLokum.coconut().getName()) ||
			 lokum2.getName().equals(StripedLokum.crossStripedCoconut().getName()) ||
			 lokum2.getName().equals(StripedLokum.longitudinalStripedCoconut().getName()) ||
		   	 lokum2.getName().equals(WrappedLokum.wrappedCoconut().getName())))
			return true;
		
		if ((lokum1.getName().equals(BasicLokum.hazelnut().getName()) || 
				 lokum1.getName().equals(StripedLokum.crossStripedHazelnut().getName()) ||
				 lokum1.getName().equals(StripedLokum.longitudinalStripedHazelnut().getName()) ||
			   	 lokum1.getName().equals(WrappedLokum.wrappedHazelnut().getName()))
					&&
				(lokum2.getName().equals(BasicLokum.hazelnut().getName()) ||
				 lokum2.getName().equals(StripedLokum.crossStripedHazelnut().getName()) ||
				 lokum2.getName().equals(StripedLokum.longitudinalStripedHazelnut().getName()) ||
			   	 lokum2.getName().equals(WrappedLokum.wrappedHazelnut().getName())))
				return true;
		
		if ((lokum1.getName().equals(BasicLokum.pistachio().getName()) || 
				 lokum1.getName().equals(StripedLokum.crossStripedPistachio().getName()) ||
				 lokum1.getName().equals(StripedLokum.longitudinalStripedPistachio().getName()) ||
			   	 lokum1.getName().equals(WrappedLokum.wrappedPistachio().getName()))
					&&
				(lokum2.getName().equals(BasicLokum.pistachio().getName()) ||
				 lokum2.getName().equals(StripedLokum.crossStripedPistachio().getName()) ||
				 lokum2.getName().equals(StripedLokum.longitudinalStripedPistachio().getName()) ||
			   	 lokum2.getName().equals(WrappedLokum.wrappedPistachio().getName())))
				return true;
		
		if ((lokum1.getName().equals(BasicLokum.rose().getName()) || 
				 lokum1.getName().equals(StripedLokum.crossStripedRose().getName()) ||
				 lokum1.getName().equals(StripedLokum.longitudinalStripedRose().getName()) ||
			   	 lokum1.getName().equals(WrappedLokum.wrappedRose().getName()))
					&&
				(lokum2.getName().equals(BasicLokum.rose().getName()) ||
				 lokum2.getName().equals(StripedLokum.crossStripedRose().getName()) ||
				 lokum2.getName().equals(StripedLokum.longitudinalStripedRose().getName()) ||
			   	 lokum2.getName().equals(WrappedLokum.wrappedRose().getName())))
				return true;
		
		
		
		return false;
	}
	
	public static Lokum findSameTypedLokums(Lokum lokum1){
		for (int i = 0; i < GameBoard.row; i++) {
			for (int j = 0; j < GameBoard.column; j++) {
				if(hasSameRoot(lokum1, GameBoard.lokumArray[i][j])){
					return lokum1;
				}
				
			}
			}
		return null;
	}

	
	
	public static Lokum transformLokums(Lokum lokum1, Lokum lokum2){
		lokum1.setName(lokum2.getName());
	lokum1.setLokumIcon(lokum2.getLokumIcon());
	lokum1.setIcon(lokum2.getIcon());
	System.out.println(lokum1.getIcon());
	lokum1=lokum2;
	return lokum1;
	}

	public String toString() {
		return "Lokum [XofLokum=" + XofLokum + ", YofLokum=" + YofLokum + "]";
	}

	public boolean repOK() {
		Lokum lokum = new Lokum();
		if(!isSpecial(lokum) && (SpecialLokum.isColorBomb(lokum) || SpecialLokum.isCrossStripedLokum(lokum) || SpecialLokum.isLongitudinalStripedLokum(lokum) || SpecialLokum.isWrappedLokum(lokum))){
			return false;
		}
		return false;

	}

}