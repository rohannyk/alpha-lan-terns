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
		gi = new GameInstance(new GameConfiguration());
		gc = new GameConfiguration();
	}

	@After
	public void tearDown() throws Exception {
		gi = null;
		gc = null;
	}
	


	/**
	 * To test Lantern Card Configuration for three players
	 */
	@Test
	public void testConfigWithLantern3()

	{

		GameConfiguration c3 = new GameConfiguration(3);
		GameInstance gi3 = new GameInstance(c3);

		assertEquals(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR,
				gi3.getDefaultLanternCardSize());

		// System.out.println(c3.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
	}

	/**
	 * To test Lantern Card Configuration for four players
	 */
	@Test
	public void testConfigWithLantern4()

	{

		GameConfiguration c4 = new GameConfiguration(4);
		GameInstance gi4 = new GameInstance(c4);

		assertEquals(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR,
				gi4.getDefaultLanternCardSize());

		// System.out.println(c4.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
	}

	/**
	 * To test Lantern Card Configuration for two players
	 */
	@Test
	public void testConfigWithLantern2()

	{

		GameConfiguration c2 = new GameConfiguration(2);
		GameInstance gi2 = new GameInstance(c2);

		assertEquals(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR,
				gi2.getDefaultLanternCardSize());

		// System.out.println(c2.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR);
	}


}
