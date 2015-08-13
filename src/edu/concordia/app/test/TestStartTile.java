/**
* 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Vector;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * Class to execute Test Cases for Start Tile
 * @author Team E
 *
 */

public class TestStartTile {
	

	
	GameInstance gi;
	GameConfiguration gc;
	LanternMain lm;
	Vector<String> playerTypes;
	
	@Before
	public void setUp(){
		
		playerTypes = new Vector<String>();
		
		playerTypes.add("Greedy");
		playerTypes.add("Random");
		playerTypes.add("Friendly");
		playerTypes.add("Human");
		gc = new GameConfiguration();
		gi = new GameInstance();
		lm = new LanternMain();
	}
	
	@After
	public void tearDown(){
		gc = null;
        gi = null;
		lm = null;
		playerTypes = null;
	}
		

	/**
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are three
	 */
	@Test
	public void testPlayerGetsRedTileEdgeForThreePlayers() {

		GameConfiguration c3 = new GameConfiguration(3);
		GameInstance gi3 = new GameInstance(c3, playerTypes);
		String r = "RED";
		// Players[] playersList ;

		Players[] checkPlayerList = gi3.getPlayersList();

		String checkColor = null;
		for (int i = 0; i < checkPlayerList.length; i++) {
			Players playerObj = checkPlayerList[i];
			String pFaceColor = playerObj.getFaceColor();

			if (pFaceColor.equals("RED")) {
				checkColor = pFaceColor;
			}
		}

		assertTrue(r.equals(checkColor));

	}
	
	/**
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are two
	 */
	@Test
	public void testPlayerGetsRedTileEdgeForTwoPlayers() {
				
		GameConfiguration c2= new GameConfiguration(2);
	    GameInstance gi2= new GameInstance(c2, playerTypes);
	    String r ="RED";
	   //Players[] playersList ;
	    Players[] checkPlayerList = gi2.getPlayersList();
	    
	    String checkColor = null;
	    for (int i = 0; i < checkPlayerList.length; i++) {
	    	Players playerObj = checkPlayerList[i];
	    	String pFaceColor = playerObj.getFaceColor();
	    	
	    	if(pFaceColor.equals("RED")){
	    		checkColor = pFaceColor;
	    	}
		}
	    	  
	    assertTrue(r.equals(checkColor));
	       
	}
	
	/**
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are three
	 */
	@Test
	public void testPlayerGetsRedTileEdgeForFourPlayers() {

		GameConfiguration c4 = new GameConfiguration(4);
		GameInstance gi4 = new GameInstance(c4, playerTypes);
		String r = "RED";
		// Players[] playersList ;
		// Players[] getPlayersList();
		Players[] checkPlayerList = gi4.getPlayersList();

		String checkColor = null;
		for (int i = 0; i < checkPlayerList.length; i++) {
			Players playerObj = checkPlayerList[i];
			String pFaceColor = playerObj.getFaceColor();

			if (pFaceColor.equals("RED")) {
				checkColor = pFaceColor;
			}
		}

		assertTrue(r.equals(checkColor));

	}
   
}

	    
	



