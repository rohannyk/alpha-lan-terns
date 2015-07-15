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
	
	private int defaultLanternCardSize;
	
	/**
	 * the variable to hold current player
	 * 0 for game not started
	 * 1 - 4 for players
	 */
	private Players playerCurrentTurn;
//	private int playerCurrentTurn;
	
	/**
	 * The variable holds the number of players
	 */
	private int noOfPlayers;
	
	@XmlElementWrapper(name="playersList")
	@XmlElement(name="player")
	/**
	 * The array to store the player objects.
	 */
	public Players[] playersList;
	
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
	
//	Lantern Card Class
//	@XmlElementWrapper(name="lanternCardSuite")
//	@XmlElement(name="lanternCard")
//	/**
//	 * The vector used to store lantern cards while saving file.
//	 */
//	public Vector<LanternCards> gameLanternSuite;
	
	private String[] playerPositions = {"SOUTH", "EAST","NORTH", "WEST"};
	//@XmlElement(name="favorToken")
	/**
	 * 
	 */
	//private FavorTokens gameFavorTokens;
	private int gameFavorToken = 20;
	
	
	//@XmlElementWrapper(name="gameDedicationTokens")
	@XmlElement(name="gameDedicationToken")
	/**
	 * 
	 */
	private DedicationTokens dedicationTokens;
	
	/**
	 * The vector contains arrangement of lake tiles that are already played.
	 */
	private Vector<LakeTiles> currentLakeTilesArrangement = new Vector<LakeTiles>();
	
	@XmlElement(name="GameRedLanterCard")
	/**
	 * 
	 */
	private static int gameRedLanternCardCount = 0;
	
	@XmlElement(name="GameBlueLanternCard")
	/**
	 * 
	 */
	private static int gameBlueLanternCardCount = 0;
	
	@XmlElement(name="GameOrangeLanternCard")
	/**
	 * 
	 */
	private static int gameOrangeLanternCardCount = 0;
	
	@XmlElement(name="GameBlackLanternCard")
	/**
	 * 
	 */
	private static int gameBlackLanternCardCount = 0;
	
	@XmlElement(name="GamePurpleLanternCard")
	/**
	 * 
	 */
	private static int gamePurpleLanternCardCount = 0;
	
	@XmlElement(name="GameWhiteLanternCard")
	/**
	 * 
	 */
	private static int gameWhiteLanternCardCount = 0;
	
	@XmlElement(name="GameGreenLanternCard")
	/**
	 * 
	 */
	private static int gameGreenLanternCardCount = 0;
	
	//next dedication token track
	
		/**
		 * The next value of dedication token with four coins logo
		 */
		private int nextDedicationTokenFour;
		
		
		/**
		 * The next value of dedication token with six coins logo
		 */
		private int nextDedicationTokenSix;
		
		
		/**
		 * The next value of dedication token with seven coins logo
		 */
		private int nextDedicationTokenSeven;
		
		
		/**
		 * The next value of generic dedication token
		 */
		private int nextGenericDedicationToken;
	
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
		this.defaultLanternCardSize = config.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;
		//gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
		//gameFavorTokens = new FavorTokens();
		
		initializeGameData();
	}
	
	private void initializeGameData(){
		
		//initialize players of the game
		initializePlayers();
		
		//initialize Lantern Cards
		initializeLanternCards();
		
		//initialize favor tokens
		//initializeFavorTokens();
		
		initializeGameTiles();
		
		initializeDedicationTokens();
		
		//shuffle lake tiles
		Vector<LakeTiles> shuffledTiles= shuffleTiles(gameTileSuite);
		
		//deal lake tiles to each player
		Vector<LakeTiles> shuffledTilesAfterDeal = dealLakeTileCards(shuffledTiles);
		
		//create draw pile of Lake tiles according to no of players
		gameTilesDrawPile = initializeDrawPileTiles(shuffledTilesAfterDeal);
		
		System.out.println("deal size"+gameTilesDrawPile.size());
		//initialize dedicated tokens
		
	}
	
	/**
	 * @param shuffledTiles The shuffled Lake Tiles
	 * @return The remaining shuffled tiles after the deal
	 */
	private Vector<LakeTiles> dealLakeTileCards(Vector<LakeTiles> shuffledTiles) {
		int totalDealCards = noOfPlayers * 3;
		System.out.println("deal count "+totalDealCards);
		int dealCount = 3;
		System.out.println("initial shuffled tiles size"+shuffledTiles.size());
		
		for (int i = 0; i < playersList.length; i++) {
			Players player = playersList[i];
			Vector<LakeTiles> playerTiles = new Vector<LakeTiles>();

			for (int j = 0; j < dealCount; j++) {
				LakeTiles lakeTile = shuffledTiles.remove(j);
				playerTiles.add(lakeTile);
			}
			player.setCurrentLakeTilesHold(playerTiles);
		}
		
		Vector<LakeTiles> shuffledTilesAfterDeal = shuffledTiles;
		int shuffledTilesSize = shuffledTiles.size();
		
		System.out.println("final shuffled tiles size"+shuffledTiles.size());
		
		/*for (int i = 0; i < playersList.length; i++) {
			Players player = playersList[i];
			System.out.println(player.getCurrentLakeTilesHold().size());			
		}*/
		
		
		
		return shuffledTiles;
	}

	/**
	 * Initialize the players of the game.
	 */
	private void initializePlayers(){
		
		playersList = new Players[config.NUM_OF_PLAYERS];
		
		if(playersList.length == 2){
			playersList[0] = new Players(config,1, playerPositions[0]);
			playersList[1] = new Players(config,2, playerPositions[2]);
		}
		
		else{
			for (int i = 0; i < playersList.length; i++) {
				playersList[i] = new Players(config,i+1, playerPositions[i]);
			}
		}		
		
		/*for (int i = 0; i < playersList.length; i++) {
			System.out.println(playersList[i]);
		}*/
		
		//System.out.println("Player positions "+ playerPositions);
		//System.out.println("test no. of players "+ playersList.length);
	}
	
	private void initializeLanternCards(){
		
		/*gameLanternSuite = new Vector<LanternCards>();
		Color lanterCardColors[] = Color.values();
		
		for (int i = 0; i < lanterCardColors.length; i++) {
			gameLanternSuite.add(new LanternCards(lanterCardColors[i], config.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR));
		}*/
		
		gameRedLanternCardCount = defaultLanternCardSize;
		
		gameBlueLanternCardCount = defaultLanternCardSize;
		
		gameOrangeLanternCardCount = defaultLanternCardSize;
		
		gameBlackLanternCardCount = defaultLanternCardSize;
		
		gamePurpleLanternCardCount = defaultLanternCardSize;
		
		gameWhiteLanternCardCount = defaultLanternCardSize;
		
		gameGreenLanternCardCount = defaultLanternCardSize;
		
	}
	
	/*private void initializeFavorTokens(){
		//default tokens of new game
		gameFavorTokens.setFavtokenCount(20);
	}*/
	
	private void initializeDedicationTokens() {
		dedicationTokens = config.DEDICATION_TOKENS;
		
		Vector<Integer> dedicationTokenFour = dedicationTokens.getDedicationTokenFour();
		Vector<Integer> dedicationTokenSix = dedicationTokens.getDedicationTokenSix();
		Vector<Integer> dedicationTokenSeven = dedicationTokens.getDedicationTokenSeven();
		Vector<Integer> genericDedicationTokens = dedicationTokens.getGenericDedicationTokens();
		
		// set the next dedication token to be taken by the player
			setNextDedicationTokenFour(dedicationTokenFour.firstElement());
			setNextDedicationTokenSix(dedicationTokenSix.firstElement());
			setNextDedicationTokenSeven(dedicationTokenSeven.firstElement());
			setNextGenericDedicationToken(genericDedicationTokens.firstElement());
		//setDedicationTokens(config.DEDICATION_TOKENS);
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
	public Players getPlayerCurrentTurn() {
		return playerCurrentTurn;
	}

	/**
	 * @param playerCurrentTurn the playerCurrentTurn to set
	 */
	public void setPlayerCurrentTurn(Players playerCurrentTurn) {
		this.playerCurrentTurn = playerCurrentTurn;
	}

//	/**
//	 * @return the dedicationTokens
//	 */
//	public DedicationTokens getDedicationTokens() {
//		return dedicationTokens;
//	}
//
//	/**
//	 * @param dedicationTokens the dedicationTokens to set
//	 */
//	public void setDedicationTokens(DedicationTokens dedicationTokens) {
//		this.dedicationTokens = dedicationTokens;
//	}

	/**
	 * @return the gameFavorToken
	 */
	public int getGameFavorToken() {
		return gameFavorToken;
	}

	/**
	 * @param gameFavorToken the gameFavorToken to set
	 */
	public void setGameFavorToken(int gameFavorToken) {
		this.gameFavorToken = gameFavorToken;
	}
	
	/**
	 * @return the nextDedicationTokenFour
	 */
	public int getNextDedicationTokenFour() {
		return nextDedicationTokenFour;
	}

	/**
	 * @param nextDedicationTokenFour the nextDedicationTokenFour to set
	 */
	public void setNextDedicationTokenFour(int nextDedicationTokenFour) {
		this.nextDedicationTokenFour = nextDedicationTokenFour;
	}

	/**
	 * @return the nextDedicationTokenSix
	 */
	public int getNextDedicationTokenSix() {
		return nextDedicationTokenSix;
	}

	/**
	 * @param nextDedicationTokenSix the nextDedicationTokenSix to set
	 */
	public void setNextDedicationTokenSix(int nextDedicationTokenSix) {
		this.nextDedicationTokenSix = nextDedicationTokenSix;
	}

	/**
	 * @return the nextDedicationTokenSeven
	 */
	public int getNextDedicationTokenSeven() {
		return nextDedicationTokenSeven;
	}

	/**
	 * @param nextDedicationTokenSeven the nextDedicationTokenSeven to set
	 */
	public void setNextDedicationTokenSeven(int nextDedicationTokenSeven) {
		this.nextDedicationTokenSeven = nextDedicationTokenSeven;
	}

	/**
	 * @return the nextGenericDedicationToken
	 */
	public int getNextGenericDedicationToken() {
		return nextGenericDedicationToken;
	}

	/**
	 * @param nextGenericDedicationToken the nextGenericDedicationToken to set
	 */
	public void setNextGenericDedicationToken(int nextGenericDedicationToken) {
		this.nextGenericDedicationToken = nextGenericDedicationToken;
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
