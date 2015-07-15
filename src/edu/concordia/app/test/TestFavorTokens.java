/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

/**
 * @author Team E
 *
 */
public class TestFavorTokens {

	GameInstance gi;
	GameConfiguration gc;
	LanternMain lm;
	
	@Before
	public void setUp(){
		gc = new GameConfiguration();
		gi = new GameInstance();
	
	}
	
	@After
	public void tearDown(){
		gc = null;
		gi = null;
		
	}
	
	@Test
public void testFavourTokens() {
	
		
		assertEquals(gc.TOTAL_FAVOR_TOKEN, gi.getGameFavorToken());
		//System.out.println(gi.getGameFavorToken());
		
	}
	

	
}
