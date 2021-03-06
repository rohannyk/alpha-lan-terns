/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

/**
 * Class to execute Test Cases for Lake Tiles
 * @author Team E
 *
 */
public class TestLakeTiles {

	GameInstance gi;
	GameConfiguration  gc;
		
	

	@Before
	public void setUp() throws Exception {
		gi = new GameInstance();
		gc = new GameConfiguration();
	}

	@After
	public void tearDown() throws Exception {
		gi = null;
		gc = null;
	}
	
	/**
	 * To test lake tile configuration for three players
	 */
	@Test
	public void testConfigWithParam4() {
		
		GameConfiguration c4 = new GameConfiguration(4);
		GameInstance gi4 = new GameInstance();
		assertEquals(c4.NUM_OF_TILES_IN_DRAW_STACK, 20);
		
	}
	
	/**
	 * To test lake tile configuration for three players
	 */
	@Test
	public void testConfigWithParam3() {

		GameConfiguration c3 = new GameConfiguration(3);
		GameInstance gi3 = new GameInstance();
		assertEquals(c3.NUM_OF_TILES_IN_DRAW_STACK, 18);
	}

	/**
	 * To test lake tile configuration for two players
	 */
	@Test
	public void testConfigWithParam2() {

		GameConfiguration c2 = new GameConfiguration(2);
		GameInstance gi2 = new GameInstance();
		Vector<LakeTiles> vlt = gi2.getGameTilesDrawPile();
		assertEquals(c2.NUM_OF_TILES_IN_DRAW_STACK,16);

	}
	
}