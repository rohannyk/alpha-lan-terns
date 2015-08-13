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
 * Class to execute Test Cases for Lantern Cards
 * @author Team E
 *
 */
public class TestLanternCard {

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
	 * To test Total number of favor tokens initially on board for different players 
	 */
	@Test
	public void testConfigWithFavor3()

	{ 

		GameConfiguration c3 = new GameConfiguration(3);
		GameInstance gi3 = new GameInstance();

		assertEquals(c3.TOTAL_FAVOR_TOKEN,
				20);

	}

	/**
	 * To test Lantern Card Configuration for four players
	 */
	@Test
	public void testConfigWithFavor2()

	{ 

		GameConfiguration c3 = new GameConfiguration(2);
		GameInstance gi3 = new GameInstance();

		assertEquals(c3.TOTAL_FAVOR_TOKEN,
				20);

	}

	/**
	 * To test Lantern Card Configuration for two players
	 */
	@Test
	public void testConfigWithFavor4()

	{ 

		GameConfiguration c3 = new GameConfiguration(4);
		GameInstance gi3 = new GameInstance();

		assertEquals(c3.TOTAL_FAVOR_TOKEN,
				20);

	}

}