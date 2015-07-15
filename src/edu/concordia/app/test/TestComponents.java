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
		
			
			assertEquals(c4.noOfDrawTileInStack, gi4.gameTilesDrawPile.size());
			assertFalse(gi4.gameTilesDrawPile.size()==10);
			assertNotNull(gi4.gameTilesDrawPile.size());
			
		}
	
		@Test
		/**
		 * The test checks the number of Draw tile stack with 3 players
		 */
		public void testnoOfDrawTileInStackWithParamThree() {
			
			
			
		GameConfiguration c3 = new GameConfiguration(3);
        GameInstance gi3 = new GameInstance(c3);
	    assertEquals(c3.noOfDrawTileInStack, gi3.gameTilesDrawPile.size());
		assertFalse(gi3.gameTilesDrawPile.size()==10);
		assertNotNull(gi3.gameTilesDrawPile.size());
		
		}
		
		
		
		@Test
		/**
		 * The test checks the number of Draw tile stack with 2 players
		 */
		public void testnoOfDrawTileInStackWithParamTwo() {
			
		GameConfiguration c2 = new GameConfiguration(2);
	    GameInstance gi2 = new GameInstance(c2);
	   	assertEquals(c2.noOfDrawTileInStack, gi2.gameTilesDrawPile.size());
		assertFalse(gi2.gameTilesDrawPile.size()==10);
		assertNotNull(gi2.gameTilesDrawPile.size());
		
		       
		}
		
		
		@Test
		/**
		 * The test checks the number of Lantern cards of each color with 4 players
		 */
		public void testNoOfLanternCardsForEveryColorWithParamThree()
			
		{
			
		GameConfiguration c3= new GameConfiguration(3);
	    GameInstance gi3= new GameInstance(c3);
	    
	    
	  assertEquals(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi3.defaultLanternCardSize);
		assertFalse(gi3.defaultLanternCardSize==10);
		assertNotNull(gi3.defaultLanternCardSize);
		
		
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
	    
	  assertEquals(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi4.defaultLanternCardSize);
	  assertFalse(gi4.defaultLanternCardSize==10);
		assertNotNull(gi4.defaultLanternCardSize);
		
		
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
	    
	  assertEquals(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi2.defaultLanternCardSize);
	  assertFalse(gi2.defaultLanternCardSize==10);
		assertNotNull(gi2.defaultLanternCardSize);
		
		
	   // System.out.println(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
				
}