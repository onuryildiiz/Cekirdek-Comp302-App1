package Core;

import static org.junit.Assert.*;
import Core.Lokum;

import org.junit.Test;

public class Lokum_test {
	 

	@Test
	
	/*The aim of the testIsSpecial to check the correctness of the method isSpecial which 
	 * determines whether a lokum is a special or is a basic lokum.
	 * Preconditions: There should be a lokum in the board, it can be null or not.
	 * Postconditions: This method returns true if isSpecial method works properly.
	 * 
	 * If we calculate the total number of types of lokum
	 *                      				Pistachio 		 Hazelnut  		Coconut 		Rose		None
	 * Null										-				-				-			  -			  1
	 * Basic Lokum								1				1				1			  1			  -
	 * Cross Striped Lokum						1				1				1			  1			  -					
	 * Longitudinal Striped Lokum				1				1				1			  1			  -
	 * Wrapped Lokum  							1				1				1			  1			  -
	 * ColorBomb Lokum							-				-				-			  -			  1
	 * TimeBased Lokum							1				1				1			  1			  -
	 * 	
	 * There are 22 different types of lokum. Therefore there are 22 possible cases to test.
	 * If we check only "Pistachio" column and "none" column we will cover all the cases.
	 * 
	 */


	
	public void testIsSpecial() {
		{
			assertTrue("Null Lokum is a special lokum.",
					Lokum.isSpecial(null));

			assertTrue("Basic Pistachio is a special lokum. ", 
					Lokum.isSpecial(BasicLokum.pistachio()));
			
			assertTrue("Cross Striped Pistachio is a special lokum. ", 
					Lokum.isSpecial(StripedLokum.crossStripedPistachio()));
			
			assertTrue("Longitudinal Striped Pistachio is a special lokum. ", 
					Lokum.isSpecial(StripedLokum.longitudinalStripedPistachio()));
			
			assertTrue("Wrapped Pistachio is a special lokum. ", 
					Lokum.isSpecial(WrappedLokum.wrappedPistachio()));
			
			assertTrue("Color bomb is a special lokum. ", 
					Lokum.isSpecial(ColorBomb.colorBomb()));
			
			assertTrue("Time Based Pistachio is a special lokum.", 
					Lokum.isSpecial(TimeBasedLokum.timeBasedPistachio()));
			
		}
		
	}
	
	
	/*The aim of the testHasSameRoot to check the correctness of the method hasSameRoot which 
	 * determines whether two lokums are the members of the same family or not.
	 * Preconditions: There should be two lokums.
	 * Postconditions: This method returns true if hasSpecial method works properly.
	 * 
	 * Following table includes the possible mathes of two lokums.
	 * Since there is no family of null lokum and ColorBomb lokum. We do not include them in the table .
	 * 
	 *   			 b_p 	b_h 	b_c 	b_r 	c_p 	c_h		 c_c 	c_r		 l_p 	l_h 	l_c 	l_r 	w_p		 w_h 	w_c 	w_r  	 t_p 	t_h		 t_c	 t_r
	 *   
	 *   b_p 		  x
	 *   b_h          x
	 *   b_c
	 *   b_r 
	 *   c_p          x                              x
	 *   c_h          x                              x
	 *   c_c
	 *   c_r
	 *   l_p          x                              x                        	      x
	 *   l_h          x                              x                        	      x
	 *   l_c
	 *   l_r
	 *   w_p          x                              x                        	      x                              x
	 *   w_h          x                              x                         	      x                              x
	 *   w_c 
	 *   w_r
	 *   t_p          x                              x                        	      x                              x                    		      x
	 *   t_h          x                              x                        	      x                              x                        	      x
	 *   t_c
	 *   t_r
	 * 
	 * There are 22^2 possible combinations for which hasSameRoot method can be applied.
	 * 
	 * A.If we check the method for basic pistachio(b_p) column, we can make a generalization for basic lokums(the flagged intersection of l_p column and all rows shows the cases 
	 *  the test codes will include.).
	 * If we work on the flagged rows we have included all possibilities for basic pistachio, there is no need to work on other basic lokums.(b_h, b_c, and, b_r)
	 * 
	 * B.If we check the method for crossStriped pistachio(c_p) column, we can generalize for this type lokum. We have already included the possibility of
	 * "basic lokum+cross striped lokum" we would not include it, we include the possibilities: cross+cross, cross+longitudinal, cross+wrap, cross+time. 
	 *  It is OK if we include two tests one of which returns true, another one returns false.
	 *  
	 * C. If we check the method for longitudinalStriped pistachio(l_p) column, we can generalize for this type lokum. We have already included the possibility of
	 * "basic lokum+longitudinal striped lokum" and "cross striped lokum+longitudinal striped lokum", we would not include them, we will include the
	 *  possibilities: longitudinal+longitudinal, longitudinal+wrap, longitudinal+time. 
	 *  It is OK if we include two tests one of which returns true, another one returns false. (the flagged intersection of l_p column and all rows shows the cases 
	 *  the test codes will include.)
	 *  
	 *  
	 * D. If we check the method for wrapped pistachio(w_p) column, we can generalize for this type lokum. We have already included the possibility of
	 * "basic lokum+wrapped lokum", "cross striped lokum+wrapped lokum", and "longitudinal lokum+wrapped lokum", we would not include them, we will include the
	 *  possibilities: wrapped+wrapped, and wrapped+time. 
	 *  It is OK if we include two tests one of which returns true, another one returns false.(the flagged intersection of w_p column and all rows shows the cases 
	 *  the test codes will include.)
	 *  
	 *  
	 *  
	 * E. If we check the method for timeBased pistachio(t_p) column, we can generalize for this type lokum. We have already included the possibility of
	 * "basic lokum+time lokum", "cross striped lokum+time lokum","longitudinal lokum+time lokum", and "wrapped lokum+time lokum" we would not include
	 *  them, we will include the possibility: time+time. 
	 *  It is OK if we include two tests one of which returns true, another one returns false.(the flagged intersection of t_p column and all rows shows the cases 
	 *  the test codes will include.)
	 *  
	 *  To sum up we can work on 30 cases instad of 22^2 cases.
	 *  
	 */
	
	
	

	
	
	@Test
	public void testHasSameRoot() {
		
		//A
		assertTrue("Basic Pistachio and Basic Pistachio has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),BasicLokum.pistachio()));
		
		assertTrue("Basic Pistachio and Basic Hazelnut  has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),BasicLokum.hazelnut()));
		
		assertTrue("Basic Pistachio and Cross Striped Pistachio has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),StripedLokum.crossStripedPistachio()));
		
		assertTrue("Basic Pistachio and Cross Striped Hazelnut has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),StripedLokum.crossStripedHazelnut()));
		
		assertTrue("Basic Pistachio and Longitudinal Striped Pistachio has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),StripedLokum.longitudinalStripedPistachio()));
		
		assertTrue("Basic Pistachio and Longitudinal Striped Hazelnut has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),StripedLokum.longitudinalStripedHazelnut()));
		
		assertTrue("Basic Pistachio and Wrapped Pistachio has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),WrappedLokum.wrappedPistachio()));
		
		assertTrue("Basic Pistachio and Wrapped Hazelnut has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),WrappedLokum.wrappedHazelnut()));
		
		assertTrue("Basic Pistachio and Time Based Pistachio has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),TimeBasedLokum.timeBasedPistachio()));
		
		assertTrue("Basic Pistachio and Time Based Hazelnut has same root. ", 
				Lokum.hasSameRoot(BasicLokum.pistachio(),TimeBasedLokum.timeBasedHazelnut()));
		
		//B
		
		assertTrue("Cross Striped Pistachio and Cross Striped Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),StripedLokum.crossStripedPistachio()));
		
		assertTrue("Cross Striped Pistachio and Cross Striped Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),StripedLokum.crossStripedHazelnut()));
		
		assertTrue("Cross Striped Pistachio and Longitudinal Striped Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),StripedLokum.longitudinalStripedPistachio()));
		
		assertTrue("Cross Striped Pistachio and Longitudinal Striped Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),StripedLokum.longitudinalStripedHazelnut()));
		
		assertTrue("Cross Striped Pistachio and Wrapped Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),WrappedLokum.wrappedPistachio()));
		
		assertTrue("Cross Striped Pistachio and Wrapped Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),WrappedLokum.wrappedHazelnut()));
		
		assertTrue("Cross Striped Pistachio and Time Based Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),TimeBasedLokum.timeBasedPistachio()));
		
		assertTrue("Cross Striped Pistachio and Time Based Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.crossStripedPistachio(),TimeBasedLokum.timeBasedHazelnut()));
		
		//C
		assertTrue("Longitudinal Striped Pistachio and Longitudinal Striped Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),StripedLokum.longitudinalStripedPistachio()));
		
		assertTrue("Longitudinal Striped Pistachio and Longitudinal Striped Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),StripedLokum.longitudinalStripedHazelnut()));
		
		assertTrue("Longitudinal Striped Pistachio and Wrapped Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),WrappedLokum.wrappedPistachio()));
		
		assertTrue("Longitudinal Striped Pistachio and Wrapped Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),WrappedLokum.wrappedHazelnut()));
		
		assertTrue("Longitudinal Striped Pistachio and Time Based Pistachio has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),TimeBasedLokum.timeBasedPistachio()));
		
		assertTrue("Longitudinal Striped Pistachio and Time Based Hazelnut has same root. ", 
				Lokum.hasSameRoot(StripedLokum.longitudinalStripedPistachio(),TimeBasedLokum.timeBasedHazelnut()));
		
		//D
		assertTrue("Wrapped Pistachio and Wrapped Pistachio has same root. ", 
				Lokum.hasSameRoot(WrappedLokum.wrappedPistachio(),WrappedLokum.wrappedPistachio()));
		
		assertTrue("Wrapped Pistachio and Wrapped Hazelnut has same root. ", 
				Lokum.hasSameRoot(WrappedLokum.wrappedPistachio(),WrappedLokum.wrappedHazelnut()));
		
		assertTrue("Wrapped Pistachio and Time Based Pistachio has same root. ", 
				Lokum.hasSameRoot(WrappedLokum.wrappedPistachio(),TimeBasedLokum.timeBasedPistachio()));
		
		assertTrue("Wrapped Pistachio and Time Based Hazelnut has same root. ", 
				Lokum.hasSameRoot(WrappedLokum.wrappedPistachio(),TimeBasedLokum.timeBasedHazelnut()));
		
		//E
		assertTrue("Time Based Pistachio and Time Based Pistachio has same root. ", 
				Lokum.hasSameRoot(TimeBasedLokum.timeBasedPistachio(),TimeBasedLokum.timeBasedPistachio()));
		
		assertTrue("Time Based Pistachio and Time Based Hazelnut has same root. ", 
				Lokum.hasSameRoot(TimeBasedLokum.timeBasedPistachio(),TimeBasedLokum.timeBasedHazelnut()));
	}
	

	
	

}
