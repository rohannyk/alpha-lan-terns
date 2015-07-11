/**
 * 
 */
package edu.concordia.app.model;

import java.util.Vector;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.components.LanternCards;

/**
 * @author TEAM E
 *
 */
public class GameInstance {

	private GameConfiguration config;
	
	private int playerCurrentTurn;
	
	/**
	 * The array to store the player objects.
	 */
	private Players[] playersList;
	
	/**
	 * The vector to store the lake tiles for doing the draw.
	 */
	private Vector<LakeTiles> gameTilesDrawPile;
	
	/**
	 * The vector to store the complete 36 lake tile.
	 */
	private Vector<LakeTiles> gameTileSuite;
	
	/**
	 * The vector used to store lantern cards while saving file.
	 */
	private Vector<LanternCards> gameLanternSuite;
	
	/**
	 * 
	 */
	public GameInstance(GameConfiguration config) {
		this.config = config;
	}
	
	/**
	 * Initialize the players of the game.
	 */
	private void initializePlayers(){
		
		playersList = new Players[config.NUM_OF_PLAYERS];
	}

}
