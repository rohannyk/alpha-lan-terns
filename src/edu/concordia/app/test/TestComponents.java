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
import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

/**
 * @author abhijit
 *
 */
public class TestComponents {
	

	
	GameInstance gi;
	GameConfiguration gc;
	LanternMain lm;
	
	@Before
	public void setUp(){
		gc = new GameConfiguration();
		gi = new GameInstance();
		lm = new LanternMain();
	}
	
	@After
	public void tearDown(){
		gc = null;
        gi = null;
		lm = null;
	}
		
	@Test
	/**
	 * The test checks the FavourTokens 
	 */
	public void testFavourTokens() {
	
		
		assertEquals(gc.TOTAL_FAVOR_TOKEN, gi.getGameFavorToken());
		assertFalse(gi.getGameFavorToken()==10);
		assertNotNull(gi.getGameFavorToken());
		
		//System.out.println(gi.getGameFavorToken());
		
	}
		@Test
		/**
		 * The test checks the number of Draw tile stack with 4 players
		 */
		public void testnoOfDrawTileInStackWithParamFour() {
			
			
			GameConfiguration c4 = new GameConfiguration(4);
			
			GameInstance gi4 = new GameInstance(c4);
			Vector<LakeTiles> vlt = gi4.getGameTilesDrawPile();
			
			assertEquals(c4.NUM_OF_TILES_IN_DRAW_STACK, vlt.size());
			assertFalse(vlt.size()==10);
			assertNotNull(vlt.size());
			
		}
	
		@Test
		/**
		 * The test checks the number of Draw tile stack with 3 players
		 */
		public void testnoOfDrawTileInStackWithParamThree() {
			
			
			
		GameConfiguration c3 = new GameConfiguration(3);
        GameInstance gi3 = new GameInstance(c3);
        Vector<LakeTiles> vlt = gi3.getGameTilesDrawPile();
	    assertEquals(c3.NUM_OF_TILES_IN_DRAW_STACK, vlt.size());
		assertFalse(vlt.size()==10);
		assertNotNull(vlt.size());
		
		}
		
		
		
		@Test
		/**
		 * The test checks the number of Draw tile stack with 2 players
		 */
		public void testnoOfDrawTileInStackWithParamTwo() {
			
		GameConfiguration c2 = new GameConfiguration(2);
	    GameInstance gi2 = new GameInstance(c2);
	    Vector<LakeTiles> vlt = gi2.getGameTilesDrawPile();
	   	assertEquals(c2.NUM_OF_TILES_IN_DRAW_STACK, vlt.size());
		assertFalse(vlt.size()==10);
		assertNotNull(vlt.size());
		
		       
		}
		
		
		@Test
		/**
		 * The test checks the number of Lantern cards of each color with 4 players
		 */
		public void testNoOfLanternCardsForEveryColorWithParamThree()
			
		{
			
		GameConfiguration c3= new GameConfiguration(3);
	    GameInstance gi3= new GameInstance(c3);
	    
	    
	  assertEquals(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi3.getDefaultLanternCardSize());
		assertFalse(gi3.getDefaultLanternCardSize() == 10);
		assertNotNull(gi3.getDefaultLanternCardSize());
		
		
	    //System.out.println(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
	
		@Test
		/**
		 * The test checks the number of Lantern cards of each color with 4 players
		 */
		public void testNoOfLanternCardsForEveryColorWithParamFour()
			
		{
			
		GameConfiguration c4= new GameConfiguration(4);
	    GameInstance gi4= new GameInstance(c4);
	    
	  assertEquals(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi4.getDefaultLanternCardSize());
	  assertFalse(gi4.getDefaultLanternCardSize() == 10);
		assertNotNull(gi4.getDefaultLanternCardSize());
		
		
	    //System.out.println(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
		@Test
		/**
		 * The test checks the number of Lantern cards of each color with 2 players
		 */
		public void testNoOfLanternCardsForEveryColorWithParamTwo()
			
		{
			
		GameConfiguration c2= new GameConfiguration(2);
	    GameInstance gi2= new GameInstance(c2);
	    
	  assertEquals(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi2.getDefaultLanternCardSize());
	  assertFalse(gi2.getDefaultLanternCardSize() == 10);
		assertNotNull(gi2.getDefaultLanternCardSize());
		
		
	   // System.out.println(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
				
}