import static org.junit.Assert.*;

import javax.management.modelmbean.XMLParseException;

import org.junit.Test;

import Core.SaveGame;


public class SaveGameJUnitTest {

	@Test
	public void testIfGameSavedBefore() throws Exception {
		assertTrue("Game name Suha Exists",SaveGame.isFileNameExists("suha"));
		assertTrue("Game name Ece Exists",SaveGame.isFileNameExists("suha"));
		assertFalse("Game name Merve doesnt Exists", SaveGame.isFileNameExists("Merve"));
			
	}
	
	@Test(expected=Exception.class)
	public void testIfGameNotExist() throws Exception {
		SaveGame.read("GameNameNotExists");
	}
	
	@Test(expected= XMLParseException.class)
	public void testIfXMLCorrupted() throws Exception{
		SaveGame.read("corruptedGame");
	}
	
}
