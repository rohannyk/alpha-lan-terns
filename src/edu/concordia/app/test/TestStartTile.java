/**
* 
 */
package edu.concordia.app.test;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
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
	          String a= gi3.playersList[0].getFaceColor();
	          String b= gi3.playersList[1].getFaceColor();
			  String c= gi3.playersList[2].getFaceColor();
			  
		// assertThat(r,MatcherAssert.either(Matcher.is(a).either(Matchers.is(b).or(Matchers.is(c))));
			 System.out.println(a);
			 System.out.println(b);
			  System.out.println(c);
			  
	    assertTrue(r.equals(a) || r.equals(b)||r.equals(c));
	    
	
			
		
			
	   
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
	          String a= gi2.playersList[0].getFaceColor();
	          String b= gi2.playersList[1].getFaceColor();
			  
			  
		// assertThat(r,MatcherAssert.either(Matcher.is(a).either(Matchers.is(b).or(Matchers.is(c))));
			  System.out.println(a);
			  System.out.println(b);
			
			  
	    assertTrue(r.equals(a) || r.equals(b));
	    
		
	   
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
          String a= gi4.playersList[0].getFaceColor();
          String b= gi4.playersList[1].getFaceColor();
		  String c= gi4.playersList[2].getFaceColor();
		  String d= gi4.playersList[3].getFaceColor();
		  
		  
	// assertThat(r,MatcherAssert.either(Matcher.is(a).either(Matchers.is(b).or(Matchers.is(c))));
		  System.out.println(a);
		  System.out.println(b);
		  System.out.println(c);
		  System.out.println(d);
		  
		  
    assertTrue(r.equals(a) || r.equals(b)||r.equals(c)||r.equals(d));
    	
	
	}	
   
}

	    
	



