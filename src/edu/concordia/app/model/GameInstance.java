/**
 * 
 */
package edu.concordia.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.FavorTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.components.LanternCards;
import edu.concordia.app.components.LanternCards.Color;
//import edu.concordia.app.components.LanternCards.Color;

@XmlRootElement
/**
 * @author TEAM E
 *
 */
public class GameInstance {

	private GameConfiguration config;
	
	
	/**
	 * the variable to hold current player
	 * 0 for game not started
	 * 1 - 4 for players
	 */
	private int playerCurrentTurn;
	
	/**
	 * The variable holds the number of players
	 */
	private int noOfPlayers;
	
	@XmlElementWrapper(name="playersList")
	@XmlElement(name="player")
	/**
	 * The array to store the player objects.
	 */
	private Players[] playersList;
	
	@XmlElementWrapper(name="drawPileTiles")
	@XmlElement(name="tile")
	/**
	 * The vector to store the lake tiles for doing the draw.
	 */
	private Vector<LakeTiles> gameTilesDrawPile;
	
	//@XmlElementWrapper(name="tileSuite")
	//@XmlElement(name="tile")
	/**
	 * The vector to store the complete 36 lake tile.
	 */
	private Vector<LakeTiles> gameTileSuite;
	
	@XmlElementWrapper(name="lanternCardSuite")
	@XmlElement(name="lanternCard")
	/**
	 * The vector used to store lantern cards while saving file.
	 */
	public Vector<LanternCards> gameLanternSuite;
	
	
	//@XmlElement(name="favorToken")
	/**
	 * 
	 */
	private FavorTokens gameFavorTokens;
	
	//@XmlElementWrapper(name="dedicationTokens")
	//@XmlElement(name="dedicationToken")
	/**
	 * 
	 */
	private DedicationTokens dedicationTokens;
	
	/**
	 * 
	 */
	public GameInstance() {
		
	}

	/**
	 * 
	 */
	public GameInstance(GameConfiguration config) {
		this.config = config;
		this.noOfPlayers = config.NUM_OF_PLAYERS;
		//gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
		gameFavorTokens = new FavorTokens();
		
		initializeGameData();
	}
	
	private void initializeGameData(){
		
		//initialize players of the game
		initializePlayers();
		
		//initialize Lantern Cards
		initializeLanternCards();
		
		//initialize favor tokens
		initializeFavorTokens();
		
		initializeGameTiles();
		
		initializeDedicationTokens();
		
		//shuffle lake tiles
		Vector<LakeTiles> shuffledTiles= shuffleTiles(gameTileSuite);
		
		gameTilesDrawPile = initializeDrawPileTiles(shuffledTiles);
		
		
		//initialize dedicated tokens
		
	}
	
	/**
	 * Initialize the players of the game.
	 */
	private void initializePlayers(){
		
		playersList = new Players[config.NUM_OF_PLAYERS];
		System.out.println("test no. of players "+ playersList.length);
	}
	
	private void initializeLanternCards(){
		
		gameLanternSuite = new Vector<LanternCards>();
		Color lanterCardColors[] = Color.values();
		
		for (int i = 0; i < lanterCardColors.length; i++) {
			gameLanternSuite.add(new LanternCards(lanterCardColors[i], config.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR));
		}
		
	}
	
	private void initializeFavorTokens(){
		//default tokens of new game
		gameFavorTokens.setFavtoken(20);
	}
	
	private void initializeDedicationTokens() {
		setDedicationTokens(config.DEDICATION_TOKENS);
	}
	
	private void initializeGameTiles(){
		this.gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
	}
	
	/**
	 * @param tiles The vector containing complete lake tiles.
	 * @return Return the vector containing shuffled lake tiles.
	 */
	private Vector<LakeTiles> shuffleTiles(Vector<LakeTiles> tiles){
		
		Collections.shuffle(tiles);
		return tiles;
	}

	private Vector<LakeTiles> initializeDrawPileTiles(Vector<LakeTiles> shuffledTiles) {
		List<LakeTiles> tileDrawList= shuffledTiles.subList(0, config.NUM_OF_TILES_IN_DRAW_STACK);
		
		//create draw pile based on number of players
		Vector<LakeTiles> tilesDrawPile = new Vector<LakeTiles>(tileDrawList);
		
		return tilesDrawPile;
	}
	
	/**
	 * @return the noOfPlayers
	 */
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	/**
	 * @param noOfPlayers the noOfPlayers to set
	 */
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	/**
	 * @return the playerCurrentTurn
	 */
	public int getPlayerCurrentTurn() {
		return playerCurrentTurn;
	}

	/**
	 * @param playerCurrentTurn the playerCurrentTurn to set
	 */
	public void setPlayerCurrentTurn(int playerCurrentTurn) {
		this.playerCurrentTurn = playerCurrentTurn;
	}

	/**
	 * @return the dedicationTokens
	 */
	public DedicationTokens getDedicationTokens() {
		return dedicationTokens;
	}

	/**
	 * @param dedicationTokens the dedicationTokens to set
	 */
	public void setDedicationTokens(DedicationTokens dedicationTokens) {
		this.dedicationTokens = dedicationTokens;
	}

//	/**
//	 * @return the gameLanternSuite
//	 */
//	public Vector<LanternCards> getGameLanternSuite() {
//		return gameLanternSuite;
//	}
//
//	/**
//	 * @param gameLanternSuite the gameLanternSuite to set
//	 */
//	public void setGameLanternSuite(Vector<LanternCards> gameLanternSuite) {
//		this.gameLanternSuite = gameLanternSuite;
//	}
	
	

}
