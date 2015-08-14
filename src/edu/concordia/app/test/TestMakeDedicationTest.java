/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.*;
import edu.concordia.app.components.*;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.strategy.*;

/**
 * 
 * @author Saroya
 *
 */
public class TestMakeDedicationTest {

	/*
	 * @Test public void test() {
	 * 
	 * DedicationTokens dedicationTokens = new DedicationTokens();
	 * 
	 * assertTrue(dedicationTokens.> 6); }
	 */

	@Test
	public void testNormalGameEndMode() {
		
		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Unfriendly");
		
		GameConfiguration c4 = new GameConfiguration(2);
		GameInstance gi4 = new GameInstance(c4, playerTypes);
		
		GameController gameController = new GameController(c4, gi4);
		
		PlayGame pGame = new NormalGamePlay(gi4, gameController);
		
		assertTrue(pGame.gameMode.equals("NormalGame"));
	}

	@Test
	public void testNCardGameEndMode() {
		
		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Unfriendly");
		
		GameConfiguration c4 = new GameConfiguration(2);
		GameInstance gi4 = new GameInstance(c4, playerTypes);
		
		GameController gameController = new GameController(c4, gi4);
		
		PlayGame pGame = new NCardGamePlay(gi4, gameController, 4);
		
		assertTrue(pGame.gameMode.equals("NCardGame"));
	}

	@Test
	public void testNHonorPointGameEndMode() {
		
		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Unfriendly");
		
		GameConfiguration c4 = new GameConfiguration(2);
		GameInstance gi4 = new GameInstance(c4, playerTypes);
		
		GameController gameController = new GameController(c4, gi4);
		
		PlayGame pGame = new NHonorPointsGamePlay(gi4, gameController, 4);
		
		assertTrue(pGame.gameMode.equals("NHonorPointGame"));
	}

	
}
