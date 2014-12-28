package Core;

public abstract class SpecialLokum extends Lokum {


	private static final long serialVersionUID = 1L;
	
	
	/**
	 *
	 * @ensures the special lokums(the striped, the wrapped, and the colorBomb lokum) become visible
	 */
	public SpecialLokum() {
		
	}
	

	public static boolean isColorBomb(Lokum lokum) {
		if (lokum.getName().equals(ColorBomb.colorBomb().getName()))
			return true;
		return false;
	}
	
	public static boolean isWrappedLokum(Lokum lokum) {
		if (lokum.getName().equals(WrappedLokum.wrappedCoconut().getName())
				|| lokum.getName().equals(WrappedLokum.wrappedHazelnut().getName())
				|| lokum.getName().equals(WrappedLokum.wrappedPistachio().getName())
				|| lokum.getName().equals(WrappedLokum.wrappedRose().getName())) {
			return true;
		}
		return false;
	}
	
	public static boolean isStripedLokum(Lokum lokum) {
		if (isCrossStripedLokum(lokum)==true || isLongitudinalStripedLokum(lokum)==true) {
			return true;
		}
		return false;
	}

	public static boolean isCrossStripedLokum(Lokum lokum) {
		if (	   lokum.getName().equals(StripedLokum.crossStripedCoconut().getName())
				|| lokum.getName().equals(StripedLokum.crossStripedHazelnut().getName())
				|| lokum.getName().equals(StripedLokum.crossStripedPistachio().getName())
				|| lokum.getName().equals(StripedLokum.crossStripedRose().getName())) {
			return true;
		}
		return false;
	}

	public static boolean isLongitudinalStripedLokum(Lokum lokum) {
		if (	   lokum.getName().equals(StripedLokum.longitudinalStripedCoconut().getName())
				|| lokum.getName().equals(StripedLokum.longitudinalStripedHazelnut().getName())
				|| lokum.getName().equals(StripedLokum.longitudinalStripedPistachio().getName())
				|| lokum.getName().equals(StripedLokum.longitudinalStripedRose().getName())) {
			return true;
		}
		return false;
	}
	
	
	public boolean repOK(){
		Lokum lokum =new StripedLokum();
		if((isStripedLokum(lokum) && isWrappedLokum(lokum)) || (isColorBomb(lokum) && (isStripedLokum(lokum)) || (isColorBomb(lokum)&& isWrappedLokum(lokum)))){
			return false;
		}
		if(isLongitudinalStripedLokum(lokum) && isCrossStripedLokum(lokum)){
			return false;
		}
		return false;
	
		
	}

	public String toString() {
		return "SpecialLokum []";
	}
	




}
