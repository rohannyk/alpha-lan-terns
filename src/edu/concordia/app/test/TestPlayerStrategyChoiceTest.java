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
import edu.concordia.app.strategy.*;

/**
 * 
 * @author Team E
 *
 */
  public class TestPlayerStrategyChoiceTest {

	/*
	 *  To check the strategy selected by two players 
	 */

	@Test public void testStrategyChoiceForTwoPlayers() {

		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Unfriendly");

		GameConfiguration c4 = new GameConfiguration(2);
		GameInstance gi4 = new GameInstance(c4, playerTypes);

		for (int i = 0; i < gi4.getPlayersList().length; i++) {
			Players player = gi4.getPlayersList()[i];
			assertEquals(player.getStrategy().name,(playerTypes.get(i)));
		}
	}
	/*
	 *  To check the strategy selected by three players 
	 */
	@Test public void testStrategyChoiceForThreePlayers() {

		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Greedy");
		playerTypes.add("Random");

		GameConfiguration c4 = new GameConfiguration(3);
		GameInstance gi4 = new GameInstance(c4, playerTypes);

		for (int i = 0; i < gi4.getPlayersList().length; i++) {
			Players player = gi4.getPlayersList()[i];
			
			assertEquals(player.getStrategy().name,(playerTypes.get(i)));
		}
	}

	@Test 
	/*
	 *  
	 *  To check the strategy selected by four players 
	 */
	public void testStrategyChoiceForFourPlayers() {

		Vector<String> playerTypes = new Vector<String>();

		playerTypes.add("Human");
		playerTypes.add("Greedy");
		 playerTypes.add("Random");
		 playerTypes.add("Friendly");

		GameConfiguration c4 = new GameConfiguration(4);
		GameInstance gi4 = new GameInstance(c4, playerTypes);

		for (int i = 0; i < gi4.getPlayersList().length; i++) {
			Players player = gi4.getPlayersList()[i];
			assertTrue(player.getStrategy().name.equals(playerTypes.get(i)));
		}
	}
}
