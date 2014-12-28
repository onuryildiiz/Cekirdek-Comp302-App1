package Core;


public class Validate {

	public static boolean validateXMLSchema(XMLObject x){
	     
		
		if(x != null){
			if(x.getLokumArray() != null &&
			    	   x.getCurrentScore() != 0 &&
			    	   x.getGameName() != null &&
			    	   x.getLevel() != 0 &&
			    	   x.getMoveCount() != 0){
			    		  return true;
			    	}
		}
		return false;
	       
 
	}

}
