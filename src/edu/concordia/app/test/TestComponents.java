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
	public void testFavourTokens() {
	
		
		assertEquals(gc.TOTAL_FAVOR_TOKEN, gi.getGameFavorToken());
		//System.out.println(gi.getGameFavorToken());
		
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
		
		
		@Test
		public void testConfigWithLantern3()
			
		{
			
		GameConfiguration c3= new GameConfiguration(3);
	    GameInstance gi3= new GameInstance(c3);
	    
	  assertEquals(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi3.defaultLanternCardSize);
		
	    //System.out.println(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
		@Test
		public void testConfigWithLantern4()
			
		{
			
		GameConfiguration c4= new GameConfiguration(4);
	    GameInstance gi4= new GameInstance(c4);
	    
	  assertEquals(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi4.defaultLanternCardSize);
		
	    //System.out.println(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
		@Test
		public void testConfigWithLantern2()
			
		{
			
		GameConfiguration c2= new GameConfiguration(2);
	    GameInstance gi2= new GameInstance(c2);
	    
	  assertEquals(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR, gi2.defaultLanternCardSize);
		
	   // System.out.println(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
		}
		
		
	
		public void testConfigWithDedicationTokensFour3()
		
		{
			
		GameConfiguration c3= new GameConfiguration(3);
	    GameInstance gi3= new GameInstance(c3);
	    
	    assertEquals(c3.DEDICATION_TOKENS, gi3.getNextDedicationTokenFour());
		
	   //System.out.println(c3.DEDICATION_TOKENS);
	  
		}
		
}