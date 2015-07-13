/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.Players;
import edu.concordia.app.controller.GameController;



/**
 * This class tests the classes of Model Package
 * @author Team E
 *
 */
public class TestModel {

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
	
	public void test() {
		fail("Not yet implemented");
	}
	
	
	 public void testGameConfiguration()
	 {
		GameConfiguration gc1= new GameConfiguration(5);
		GameConfiguration gc2= new GameConfiguration(2);
		GameConfiguration gc3= new GameConfiguration(3);
		GameConfiguration gc4= new GameConfiguration(4);
		
		assertEquals(gc1.NUM_OF_PLAYERS,4);
		assertEquals(gc2.NUM_OF_PLAYERS,2);
		assertEquals(gc3.NUM_OF_PLAYERS,3);
		assertEquals(gc4.NUM_OF_PLAYERS,4);
		
		
		
	 }	
	
}
