package Core;

import java.io.IOException;

import junit.framework.TestCase;
import Core.GameRules;


public class GameRulesTest extends TestCase {

/**
 * @author Onur Yıldız
 *
 */

	/* Testing Style : GlassBox Test
	 * 
	 * 		Aim of this test is Destroy method in GameRules class is works properly or not. 
	 * 	
	 * 	I believe that if i choose necessary lokums in different property, 
	 * that will cover all different shapes of lokums. I.E.: In testDestroyHorizontal i take
	 * 1 BasicLokum, 1 StripedLokum and 1 WrappedLokum, this is the most extreme case for destroy horizontal
	 * if this will work, then I can generalize that in all other cases that destroys ThreeLokums...
	 * 	
	 * 	I also take input only one type of lokum like Coconut because I assume that if it works for coconut, 
	 * it will also work for Rose, Pistachio, Hazelnut. 
	 * 
	 * 		I am not sure about for these first two test cases below, I thought that if I do only positive tests, 
	 * it will not be good enough and I add 2 negative test case .
	 */
	
	public void testDestroyNullLokum() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = null;
		Lokum lokum2 = null;
		Lokum lokum3 = null;

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(0);
		lokum3.setYofLokum(2);
		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertNull(lokum1);

		rules.destroyHorizontal();
		assertEquals(rules.gereksizIsDestroyed, false);

	}

	public void testDestroyNegativeCoLokum() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = BasicLokum.coconut();
		Lokum lokum3 = BasicLokum.coconut();

		lokum1.setXofLokum(-2);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(-2);
		lokum2.setYofLokum(-1);

		lokum3.setXofLokum(-2);
		lokum3.setYofLokum(-2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertNull(lokum1);

		rules.destroyHorizontal();
		assertEquals(rules.gereksizIsDestroyed, true);
	}

	public void testDestroyHorizontal() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(0);
		lokum3.setYofLokum(2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertNull(lokum1);

		rules.destroyHorizontal();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyVertical() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(0);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertNotNull(lokum1);
		rules.destroyVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyFourHorizontal() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(0);
		lokum3.setYofLokum(2);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(3);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum2, BasicLokum.coconut()));
		rules.destroyFourHorizontal();

		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyFourVertical() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(0);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(0);

		lokum4.setXofLokum(3);
		lokum4.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum2, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyFiveHorizontal() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(0);
		lokum3.setYofLokum(2);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(3);

		lokum5.setXofLokum(0);
		lokum5.setYofLokum(4);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFiveHorizontal();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyFiveVertical() throws IOException {
		GameRules rules = new GameRules();
		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(0);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(0);

		lokum4.setXofLokum(3);
		lokum4.setYofLokum(0);

		lokum5.setXofLokum(4);
		lokum5.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage1() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(2);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(0);

		lokum3.setXofLokum(1);
		lokum3.setYofLokum(0);

		lokum4.setXofLokum(2);
		lokum4.setYofLokum(1);

		lokum5.setXofLokum(2);
		lokum5.setYofLokum(2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage2() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(2);
		lokum2.setYofLokum(0);

		lokum3.setXofLokum(1);
		lokum3.setYofLokum(0);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(1);

		lokum5.setXofLokum(0);
		lokum5.setYofLokum(2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage3() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(2);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(2);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(2);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(1);

		lokum5.setXofLokum(0);
		lokum5.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage4() throws IOException {
		GameRules rules = new GameRules();
		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(2);
		lokum1.setYofLokum(2);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(2);

		lokum3.setXofLokum(0);
		lokum3.setYofLokum(2);

		lokum4.setXofLokum(2);
		lokum4.setYofLokum(1);

		lokum5.setXofLokum(2);
		lokum5.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage5() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(1);
		lokum1.setYofLokum(0);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(2);

		lokum3.setXofLokum(1);
		lokum3.setYofLokum(1);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(0);

		lokum5.setXofLokum(2);
		lokum5.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage6() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(1);
		lokum1.setYofLokum(2);

		lokum2.setXofLokum(0);
		lokum2.setYofLokum(2);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(2);

		lokum4.setXofLokum(1);
		lokum4.setYofLokum(1);

		lokum5.setXofLokum(1);
		lokum5.setYofLokum(0);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage7() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(0);
		lokum1.setYofLokum(1);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(2);
		lokum3.setYofLokum(1);

		lokum4.setXofLokum(0);
		lokum4.setYofLokum(0);

		lokum5.setXofLokum(0);
		lokum5.setYofLokum(2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));
		assertNotNull(lokum1);
		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));
		rules.destroyFourVertical();
		assertEquals(rules.gereksizIsDestroyed, true);

	}

	public void testDestroyPackage8() throws IOException {
		GameRules rules = new GameRules();

		Lokum lokum1 = BasicLokum.coconut();
		Lokum lokum2 = StripedLokum.crossStripedCoconut();
		Lokum lokum3 = WrappedLokum.wrappedCoconut();
		Lokum lokum4 = TimedLokum.timedCoconut();
		Lokum lokum5 = BasicLokum.coconut();

		lokum1.setXofLokum(2);
		lokum1.setYofLokum(1);

		lokum2.setXofLokum(1);
		lokum2.setYofLokum(1);

		lokum3.setXofLokum(1);
		lokum3.setYofLokum(0);

		lokum4.setXofLokum(2);
		lokum4.setYofLokum(0);

		lokum5.setXofLokum(2);
		lokum5.setYofLokum(2);

		assertTrue(Lokum.hasSameRoot(lokum1, lokum2));
		assertTrue(Lokum.hasSameRoot(lokum2, lokum3));
		assertTrue(Lokum.hasSameRoot(lokum3, lokum4));
		assertTrue(Lokum.hasSameRoot(lokum4, lokum5));

		assertNotNull(lokum1);

		assertTrue(Lokum.hasSameRoot(lokum3, BasicLokum.coconut()));

		rules.destroyFourVertical();

		assertEquals(rules.gereksizIsDestroyed, true);

	}

}
