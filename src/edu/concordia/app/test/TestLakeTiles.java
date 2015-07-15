/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

/**
 * @author Saleh
 *
 */
public class TestLakeTiles {

	GameInstance gi;
	GameConfiguration  gc;
		
	

	@Before
	public void setUp() throws Exception {
		gi = new GameInstance(new GameConfiguration());
		gc = new GameConfiguration();
	}

	@After
	public void tearDown() throws Exception {
		gi = null;
		gc = null;
	}
	
	@Test
	public void testConfigWithParam4() {
		
		GameConfiguration c4 = new GameConfiguration(4);
		GameInstance gi4 = new GameInstance(c4);
		assertEquals(c4.noOfDrawTileInStack, gi4.gameTilesDrawPile.size());
		
	}
	
	@Test
	public void testConfigWithParam3() {
		
		
		
	GameConfiguration c3 = new GameConfiguration(3);
    GameInstance gi3 = new GameInstance(c3);
    assertEquals(c3.noOfDrawTileInStack, gi3.gameTilesDrawPile.size());
	}
	
	
	@Test
	public void testConfigWithParam2() {
		
	GameConfiguration c2 = new GameConfiguration(2);
    GameInstance gi2 = new GameInstance(c2);
   	assertEquals(c2.noOfDrawTileInStack, gi2.gameTilesDrawPile.size());
	       
	}
	

}
