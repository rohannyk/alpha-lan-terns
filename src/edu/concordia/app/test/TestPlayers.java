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
 * @author abhijit
 *
 */
public class TestPlayers {

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

/*
 * This method checks no of players  playing the game	
 */
	@Test
	public void testPlayers() {

		GameConfiguration gc1= new GameConfiguration(2);
		assertEquals(gc1.NUM_OF_PLAYERS,2);
	}

}
