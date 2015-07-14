/**
 * 
 */
package edu.concordia.app.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.concordia.app.model.*;
import edu.concordia.app.controller.*;
import edu.concordia.app.main.LanternMain;
import edu.concordia.app.components.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class to execute Test Cases for Controller Package
 * @author Team E
 */
public class TestController {

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
	 * The test checks the number of players entered by the user
	 */
	 
	public void testNoOfPlayers() {

		int noOfPlayers = lm.getInputNoOfPlayers();
		gi = new GameInstance(new GameConfiguration(noOfPlayers));
		
		assertTrue(gi.playersList.length == noOfPlayers);
	}
	}
		
		
		

