/**
* 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.hamcrest.Matcher;
//import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * @author Team E
 *
 */

public class TestStartTile {
	

	
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
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are three
	 */
	
	public void testPlayerGetsRedTileEdgeForThreePlayers() {
		
		
	GameConfiguration c3= new GameConfiguration(3);
	   GameInstance gi3= new GameInstance(c3);
	   String r ="RED";
	   //Players[] playersList ;
	    
	    Players[] checkPlayerList = gi3.getPlayersList();
	    
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
	@Test
	/**
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are two
	 */

public void testPlayerGetsRedTileEdgeForTwoPlayers() {
		
		
		GameConfiguration c2= new GameConfiguration(2);
	    GameInstance gi2= new GameInstance(c2);
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

	@Test
	/**
	 * The Test to check the red edge side of the tile is assigned to one player
	 * when number of players are three
	 */
public void testPlayerGetsRedTileEdgeForFourPlayers() {
	
	
	GameConfiguration c4= new GameConfiguration(4);
    GameInstance gi4= new GameInstance(c4);
    String r ="RED";
   //Players[] playersList ;
   // Players[] getPlayersList();
    Players[] checkPlayerList = gi4.getPlayersList();
    
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
   
}

	    
	



