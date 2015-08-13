/**
 * 
 */
package edu.concordia.app.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

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
	Vector<String> playerTypes;
	
	@Before
	public void setUp(){
		gc = new GameConfiguration();
		gi = new GameInstance();
		lm = new LanternMain();
		playerTypes = new Vector<String>();
		
		playerTypes.add("Greedy");
		playerTypes.add("Random");
		playerTypes.add("Friendly");
		playerTypes.add("Human");
	}
	
	@After
	public void tearDown(){
		gc = null;
		gi = null;
		lm = null;
		playerTypes = null;
	}

	
	/**
	 * The test checks the number of players entered by the user
	 */	
	@Test
	public void testNoOfPlayers() {

		int noOfPlayers = lm.getInputNoOfPlayers();
		gi = new GameInstance(new GameConfiguration(noOfPlayers), playerTypes);
		
		//assertTrue(gi.playersList.length == noOfPlayers);
	}
	}
		
		
		

