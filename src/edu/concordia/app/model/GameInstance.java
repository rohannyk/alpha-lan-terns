/**
 * 
 */
package edu.concordia.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;

@XmlRootElement
/**
 * GameInstance class for the Board Game object
 * Creates GameConfiguration for the configuration of the game.
 * Creates GameBoard [][] for the layout of the lake tiles for the board.
 * Creates Vector<LakeTiles> for all the lake tiles, draw pile, start tile,
 * current tile suite, current arrangement of lake tiles that are already played.
 * Creates DedicationTokens object to keep the dedication token for the board.
 * Creates Players for player current turn, game start player, winner player.
 * Variables to count the Lantern Cards for the board.
 * Variable to count the favorite token for the board.
 * Getters and setters for the components of the board game. 
 * @author TEAM E
 *
 */
public class GameInstance {

	
	private GameConfiguration config;
	public int GameBoard [][] =  new int [73][73];
	private int defaultLanternCardSize;
	//tiles vector to get all tiles details by id
	private Vector<LakeTiles> allLakeTiles = new Vector<LakeTiles>();
	
	//@XmlElement
	/**
	 * The current player of the game.
	 * 0 for game not started
	 * 1 - 4 for players
	 */
	private Players playerCurrentTurn;
	
	/**
	 * The player who will start the game
	 */
	private Players gameStartPlayer;
	
	/**
	 * The player who won the game.
	 */
	private Players winnerPlayer;
	
	private LakeTiles gameStartTile;
	
	/**
	 * The variable holds the number of players
	 */
	private int noOfPlayers;
	
	//@XmlElementWrapper(name="playersList")
	//@XmlElement(name="player")
	/**
	 * The array to store the player objects.
	 */
	private Players[] playersList;
	
	/**
	 * This method is to get the list of players.
	 * @return the playersList
	 */
	public Players[] getPlayersList() {
		return playersList;
	}

	/**
	 * This method is to set the list of players.
	 * @param playersList: the playersList to set
	 */
	public void setPlayersList(Players[] playersList) {
		this.playersList = playersList;
	}

	/**
	 * The vector to store the lake tiles for doing the draw.
	 */
	private Vector<LakeTiles> gameTilesDrawPile;
	
	/**
	 * The vector to store the complete 36 lake tile and start tile.
	 */
	private Vector<LakeTiles> gameTileSuite;
		
	private String[] playerPositions = {"SOUTH", "EAST","NORTH", "WEST"};

	/**
	 * Variable for favorite token for the board.
	 */
	private int gameFavorToken = 20;
	
	
	/**
	 * Object for the dedication tokens.
	 */
	private DedicationTokens dedicationTokens;
	
	/**
	 * The vector contains arrangement of lake tiles that are already played.
	 */
	private Vector<LakeTiles> currentLakeTilesArrangement = new Vector<LakeTiles>();
	
	/**
	 * Integer variable to track Red Lantern Card count for the board.
	 */
	private  int gameRedLanternCardCount = 0;
	
	/**
	 * Integer variable to track Blue Lantern Card count for the board.
	 */
	private  int gameBlueLanternCardCount = 0;
	
	/**
	 * Integer variable to track Orange Lantern Card count for the board.
	 */
	private  int gameOrangeLanternCardCount = 0;
	
	/**
	 * Integer variable to track Black Lantern Card count for the board.
	 */
	private  int gameBlackLanternCardCount = 0;
	
	/**
	 * Integer variable to track Purple Lantern Card count for the board.
	 */
	private  int gamePurpleLanternCardCount = 0;
	
	/**
	 * Integer variable to track White Lantern Card count for the board.
	 */
	private  int gameWhiteLanternCardCount = 0;
	
	/**
	 * Integer variable to track Green Lantern Card count for the board.
	 */
	private  int gameGreenLanternCardCount = 0;
	
	
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
	 * Default Constructor 
	 */
	public GameInstance() {
		
	}

	/**
	 * @param config of GameConfiguration
	 * sets game configuration, number of players, default lantern card size
	 * initializes game data.
	 */
	public GameInstance(GameConfiguration config) {
		this.config = config;
		this.noOfPlayers = config.NUM_OF_PLAYERS;
		this.defaultLanternCardSize = config.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;
		//gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
		//gameFavorTokens = new FavorTokens();
		
		initializeGameData();
	}
	
	/**
	 * This method initializes game data.
	 * Initializes players of the game, lantern cards, game tiles,
	 * dedication tokens,shuffle lake tiles, deal lake tiles for each player,
	 * draw pile of Lake tiles according to number of players.
	 * Reveal all color of start tile and set face color to each player.
	 * Determines the start player, assign lantern cards to players 
	 * and remove from game stack.
	 */
	private void initializeGameData(){
		
		//initialize players of the game
		initializePlayers();
		
		//initialize Lantern Cards
		initializeLanternCards();
		
		initializeGameTiles();
		
		//initialize dedicated tokens
		initializeDedicationTokens();
		
		//shuffle lake tiles
		Vector<LakeTiles> shuffledTiles= shuffleTiles(gameTileSuite);
		
		//deal lake tiles to each player
		Vector<LakeTiles> shuffledTilesAfterDeal = dealLakeTileCards(shuffledTiles);
		
		//create draw pile of Lake tiles according to no of players
		gameTilesDrawPile = initializeDrawPileTiles(shuffledTilesAfterDeal);
		
		//reveal all color of start tile and set face color to each player
		playStartTile(gameStartTile);
		
		//determine the start player
		gameStartPlayer = determineStartPlayer(playersList);
		
		if(gameStartPlayer == null){
			System.out.println("start player is null.");
		}
		
		//assign lantern cards to players and remove from game stack
		assignLanternCards();
		
	}
	
	/**
	 * This method is to get all lake tiles.
	 * @return allLakeTiles of Vector<LakeTiles> type 
	 */
	public Vector<LakeTiles> getAllLakeTiles(){
		 return allLakeTiles;
	}
	
	/**
	 * This method is to assign lantern cards to players 
	 * and remove from board game according to the face position. 
	 * Increments player lantern card count for specific color.
	 * Sets board game lantern card count for specific color.
	 * Creates Players[] object for all the players.
	 * Creates Players object for the current player.
	 */
	private void assignLanternCards() {
		Players[] totalPlayers = playersList;
		
		for (int i = 0; i < totalPlayers.length; i++) {
			Players player = totalPlayers[i];
			String lanternColor = player.getFaceColor();
			
			if(lanternColor.equals("RED")){
				//increment player lantern count
				player.setPlayerRedLanternCardCount(1);
				
				//set game lantern count
				int redCount = getGameRedLanternCardCount();
				redCount = redCount - 1;
				this.setGameRedLanternCardCount(redCount);
				
			}else if(lanternColor.equals("WHITE")){
				
				//increment player lantern count
				player.setPlayerWhiteLanternCardCount(1);
				
				//set game lantern count
				int whiteCount = getGameWhiteLanternCardCount();
				whiteCount = whiteCount - 1;
				this.setGameWhiteLanternCardCount(whiteCount);
				
			}else if(lanternColor.equals("BLACK")){
				
				//increment player lantern count
				player.setPlayerBlackLanternCardCount(1);
				
				//set game lantern count
				int blackCount = getGameBlackLanternCardCount();
				blackCount = blackCount - 1;
				this.setGameBlackLanternCardCount(blackCount);
				
			}else if(lanternColor.equals("BLUE")){

				//increment player lantern count
				player.setPlayerBlueLanternCardCount(1);
				
				//set game lantern count
				int blueCount = getGameBlueLanternCardCount();
				blueCount = blueCount - 1;
				this.setGameBlueLanternCardCount(blueCount);
			}
		}
		
	}

	/**
	 * This method determines the start player and returns it.
	 * Also sets the current player.
	 * @param playersList2 of Players[] type
	 * @return Players for the start player
	 * Creates Players object for the start player.
	 */
	private Players determineStartPlayer(Players[] playersList2) {
		Players startPlayer = null;
		
		for (int i = 0; i < playersList2.length; i++) {
			Players playerCheck = playersList2[i];

			if(playerCheck.getFaceColor().equals("RED")){
			startPlayer = playerCheck;
			
			// set current player of the game
			setPlayerCurrentTurn(playerCheck);
			
			//set start player of the game.
			setGameStartPlayer(playerCheck);
			}
		}

		return startPlayer;
	}

	/**
	 * This method returns the remaining shuffled tiles after the deal.
	 * @param shuffledTiles: The shuffled Lake Tiles, Vector<LakeTiles> type
	 * @return shuffledTiles: Vector<LakeTiles> type
	 */
	private Vector<LakeTiles> dealLakeTileCards(Vector<LakeTiles> shuffledTiles) {
		int totalDealCards = noOfPlayers * 3;
		//System.out.println("deal count "+totalDealCards);
		int dealCount = 3;
		//System.out.println("initial shuffled tiles size"+shuffledTiles.size());
		
		for (int i = 0; i < playersList.length; i++) {
			Players player = playersList[i];
			Vector<LakeTiles> playerTiles = new Vector<LakeTiles>();

			for (int j = 0; j < dealCount; j++) {
				LakeTiles lakeTile = shuffledTiles.remove(j);
				playerTiles.add(lakeTile);
			}
			player.setCurrentLakeTilesHold(playerTiles);
		}
		
		return shuffledTiles;
	}

	/**
	 * This method initializes the players of the game.
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
		
}
	/**
	 * This method returns the lantern card count for each color of the board.
	 * @return gameBlackLanternCardCount, gameBlueLanternCardCount, 
	 * gameGreenLanternCardCount, gameOrangeLanternCardCount, gamePurpleLanternCardCount, 
	 * gameRedLanternCardCount, gameWhiteLanternCardCount
	 */
	public int getLanternCardCount() {
		return this.gameBlackLanternCardCount + this.gameBlueLanternCardCount + this.gameGreenLanternCardCount
				+ this.gameOrangeLanternCardCount + this.gamePurpleLanternCardCount
				+ this.gameRedLanternCardCount + this.gameWhiteLanternCardCount;
	}
	
	/**
	 * This method initializes the lantern cards of the game for each color.
	 */
	private void initializeLanternCards(){
		
		gameRedLanternCardCount = defaultLanternCardSize;
		
		gameBlueLanternCardCount = defaultLanternCardSize;
		
		gameOrangeLanternCardCount = defaultLanternCardSize;
		
		gameBlackLanternCardCount = defaultLanternCardSize;
		
		gamePurpleLanternCardCount = defaultLanternCardSize;
		
		gameWhiteLanternCardCount = defaultLanternCardSize;
		
		gameGreenLanternCardCount = defaultLanternCardSize;
		
	}
	
	/**
	 * This method initializes the dedication tokens of the game for each type.
	 * Four, six, seven and generic.
	 * Sets the next dedication token to be taken by the player
	 */
	private void initializeDedicationTokens() {
		dedicationTokens = config.DEDICATION_TOKENS;
		
		Vector<Integer> dedicationTokenFour = dedicationTokens.getDedicationTokenFour();
		Vector<Integer> dedicationTokenSix = dedicationTokens.getDedicationTokenSix();
		Vector<Integer> dedicationTokenSeven = dedicationTokens.getDedicationTokenSeven();
		Vector<Integer> genericDedicationTokens = dedicationTokens.getGenericDedicationTokens();
		
		// sets the next dedication token to be taken by the player
		setNextDedicationTokenFour(dedicationTokenFour.firstElement());
		setNextDedicationTokenSix(dedicationTokenSix.firstElement());
		setNextDedicationTokenSeven(dedicationTokenSeven.firstElement());
		setNextGenericDedicationToken(genericDedicationTokens.firstElement());

	}
	
	/**
	 * This method initializes the tiles of the board game.
	 * Sets the start tile and removes it from tile suite 
	 */
	private void initializeGameTiles(){
		this.gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
		
		allLakeTiles.addAll(gameTileSuite);
		
		//remove start tile from tile suite
		LakeTiles startTile =  gameTileSuite.remove(0);
				
		// set start tile of game
		gameStartTile = startTile;
	}
	
	/** 
	 * This method gets and sets, face colors to each player according to number of players
	 * for the start lake tile.
	 * Adds start tile to vector contains played lake tiles: currentLakeTilesArrangement
	 * @param gameStartTile: LakeTiles type
	 */
	private void playStartTile(LakeTiles gameStartTile){
		
		int playerNum = noOfPlayers;
		
		String firstColor = gameStartTile.getTopColor();
		String secondColor = gameStartTile.getLeftColor();
		String thirdColor = gameStartTile.getRightColor();
		String fourthColor = gameStartTile.getBottomColor();
		
		if (playerNum == 2) {
			int number = getRandomNumber(playerNum);

			if (number == 0) {
				playersList[number].setFaceColor(firstColor);
				playersList[1].setFaceColor(fourthColor);
			}else{
				playersList[number].setFaceColor(firstColor);
				playersList[0].setFaceColor(fourthColor);
			}
		} else if(playerNum == 3){
			int number = getRandomNumber(playerNum);
			
			if (number == 0) {
				playersList[number].setFaceColor(firstColor);
				playersList[1].setFaceColor(thirdColor);
				playersList[2].setFaceColor(fourthColor);
			}else if (number == 1) {
				playersList[0].setFaceColor(secondColor);
				playersList[number].setFaceColor(firstColor);
				playersList[2].setFaceColor(thirdColor);
			}else if (number == 2){
				playersList[0].setFaceColor(fourthColor);
				playersList[1].setFaceColor(secondColor);
				playersList[number].setFaceColor(firstColor);
			}
			
		}else if(playerNum == 4){
			int number = getRandomNumber(playerNum);
			
			if (number == 0) {
				playersList[number].setFaceColor(firstColor);
				playersList[1].setFaceColor(thirdColor);
				playersList[2].setFaceColor(fourthColor);
				playersList[3].setFaceColor(secondColor);
			}else if (number == 1) {
				playersList[0].setFaceColor(secondColor);
				playersList[number].setFaceColor(firstColor);
				playersList[2].setFaceColor(thirdColor);
				playersList[3].setFaceColor(fourthColor);
			}else if (number == 2){
				playersList[0].setFaceColor(fourthColor);
				playersList[1].setFaceColor(secondColor);
				playersList[number].setFaceColor(firstColor);
				playersList[3].setFaceColor(thirdColor);
			}else if (number == 3) {
				playersList[0].setFaceColor(thirdColor);
				playersList[1].setFaceColor(fourthColor);
				playersList[2].setFaceColor(secondColor);
				playersList[number].setFaceColor(firstColor);
			}
		}
					
		// add start tile to vector contains played lake tiles
		currentLakeTilesArrangement.addElement(gameStartTile);
		
		 setPlayersList(playersList);
	}
	
	/** 
	 * This method gets the game start player.
	 * @param playersList: Players[] type
	 * @return gameStartPlayer: Players type 
	 */
	private Players getStartPlayer(Players[] playersList){
		return gameStartPlayer;
		
	}
	
	/** 
	 * This method a random number according to number of players.
	 * @param playerNum: int type
	 * @return random: int type 
	 */
	private int getRandomNumber(int playerNum){
		
		Random randomNumbers=new Random(0);
				
		int random = randomNumbers.nextInt(playerNum);  //number between 0 to 3
		
		return random;
	}
	
	/**
	 * This method shuffles the lake tiles and returns it.
	 * @param tiles The vector containing complete lake tiles: Vector<LakeTiles> type.
	 * @return Return the vector containing shuffled lake tiles: Vector<LakeTiles> type.
	 */
	private Vector<LakeTiles> shuffleTiles(Vector<LakeTiles> tiles){
		
		Collections.shuffle(tiles);
		return tiles;
	}

	/**
	 * This method initializes the draw pile and returns it according to number of players.
	 * @param shuffledTiles, the vector containing shuffled lake tiles: 
	 * Vector<LakeTiles> type.
	 * @return tilesDrawPile, the vector containing draw pile lake tiles: 
	 * Vector<LakeTiles> type.
	 */
	private Vector<LakeTiles> initializeDrawPileTiles(Vector<LakeTiles> shuffledTiles) {
		List<LakeTiles> tileDrawList= shuffledTiles.subList(0, config.NUM_OF_TILES_IN_DRAW_STACK);
		
		//create draw pile based on number of players
		Vector<LakeTiles> tilesDrawPile = new Vector<LakeTiles>(tileDrawList);
		
		return tilesDrawPile;
	}
	
	/**
	 * This method gets the number of players.
	 * @return the noOfPlayers: int type
	 */
	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	/**
	 * This method sets the number of players.
	 * @param noOfPlayers, the number of players to set: int type
	 */
	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	/**
	 * This method gets the current player turn.
	 * @return playerCurrentTurn: Players type
	 */
	public Players getPlayerCurrentTurn() {
		return playerCurrentTurn;
	}

	/**
	 * This method sets the current player turn.
	 * @param playerCurrentTurn, the player current turn to set: Players type
	 */
	public void setPlayerCurrentTurn(Players playerCurrentTurn) {
		this.playerCurrentTurn = playerCurrentTurn;
	}

	/**
	 * This method gets the dedication tokens.
	 * @return the dedicationTokens: DedicationTokens type
	 */
	public DedicationTokens getDedicationTokens() {
		return dedicationTokens;
	}

	/**
	 * This method sets the dedication tokens.
	 * @param dedicationTokens the dedicationTokens to set: DedicationTokens type
	 */
	public void setDedicationTokens(DedicationTokens dedicationTokens) {
		this.dedicationTokens = dedicationTokens;
	}

	/**
	 * This method gets the favor tokens.
	 * @return gameFavorToken: int type
	 */
	public int getGameFavorToken() {
		return gameFavorToken;
	}

	/**
	 * This method sets the favor tokens.
	 * @param gameFavorToken the gameFavorToken to set: int type
	 */
	public void setGameFavorToken(int gameFavorToken) {
		this.gameFavorToken = gameFavorToken;
	}
	
	/**
	 * This method gets the dedication tokens type four.
	 * @return the nextDedicationTokenFour: int type.
	 */
	public int getNextDedicationTokenFour() {
		return nextDedicationTokenFour;
	}

	/**
	 * This method sets the dedication tokens type four.
	 * @param nextDedicationTokenFour the nextDedicationTokenFour to set: int type.
	 */
	public void setNextDedicationTokenFour(int nextDedicationTokenFour) {
		this.nextDedicationTokenFour = nextDedicationTokenFour;
	}

	/**
	 * This method gets the dedication tokens type six.
	 * @return the nextDedicationTokenSix: int type.
	 */
	public int getNextDedicationTokenSix() {
		return nextDedicationTokenSix;
	}

	/**
	 * This method sets the dedication tokens type six.
	 * @param nextDedicationTokenSix the nextDedicationTokenSix to set: int type.
	 */
	public void setNextDedicationTokenSix(int nextDedicationTokenSix) {
		this.nextDedicationTokenSix = nextDedicationTokenSix;
	}

	/**
	 * This method gets the dedication tokens type seven.
	 * @return the nextDedicationTokenSeven: int type.
	 */
	public int getNextDedicationTokenSeven() {
		return nextDedicationTokenSeven;
	}

	/**
	 * This method sets the dedication tokens type seven.
	 * @param nextDedicationTokenSeven the nextDedicationTokenSeven to set: int type.
	 */
	public void setNextDedicationTokenSeven(int nextDedicationTokenSeven) {
		this.nextDedicationTokenSeven = nextDedicationTokenSeven;
	}

	/**
	 * This method gets the dedication tokens type generic.
	 * @return the nextGenericDedicationToken: int type.
	 */
	public int getNextGenericDedicationToken() {
		return nextGenericDedicationToken;
	}

	/**
	 * This method sets the dedication tokens type generic.
	 * @param nextGenericDedicationToken the nextGenericDedicationToken to set: int type.
	 */
	public void setNextGenericDedicationToken(int nextGenericDedicationToken) {
		this.nextGenericDedicationToken = nextGenericDedicationToken;
	}

	/**
	 * This method gets the game lantern card count for the red color.
	 * @return the gameRedLanternCardCount: int type.
	 */
	public  int getGameRedLanternCardCount() {
		return gameRedLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the red color.
	 * @param gameRedLanternCardCount the gameRedLanternCardCount to set: int type.
	 */
	public  void setGameRedLanternCardCount(int gameRedLanternCardCount) {
		this.gameRedLanternCardCount = gameRedLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the blue color.
	 * @return the gameBlueLanternCardCount: int type.
	 */
	public  int getGameBlueLanternCardCount() {
		return gameBlueLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the blue color.
	 * @param gameBlueLanternCardCount the gameBlueLanternCardCount to set: int type.
	 */
	public  void setGameBlueLanternCardCount(int gameBlueLanternCardCount) {
		this.gameBlueLanternCardCount = gameBlueLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the orange color.
	 * @return the gameOrangeLanternCardCount: int type.
	 */
	public  int getGameOrangeLanternCardCount() {
		return gameOrangeLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the orange color.
	 * @param gameOrangeLanternCardCount the gameOrangeLanternCardCount to set: int type.
	 */
	public  void setGameOrangeLanternCardCount(int gameOrangeLanternCardCount) {
		this.gameOrangeLanternCardCount = gameOrangeLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the black color.
	 * @return the gameBlackLanternCardCount: int type.
	 */
	public  int getGameBlackLanternCardCount() {
		return gameBlackLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the black color.
	 * @param gameBlackLanternCardCount the gameBlackLanternCardCount to set: int type.
	 */
	public  void setGameBlackLanternCardCount(int gameBlackLanternCardCount) {
		this.gameBlackLanternCardCount = gameBlackLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the purple color.
	 * @return the gamePurpleLanternCardCount: int type.
	 */
	public  int getGamePurpleLanternCardCount() {
		return gamePurpleLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the purple color.
	 * @param gamePurpleLanternCardCount the gamePurpleLanternCardCount to set: int type.
	 */
	public  void setGamePurpleLanternCardCount(int gamePurpleLanternCardCount) {
		this.gamePurpleLanternCardCount = gamePurpleLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the white color.
	 * @return the gameWhiteLanternCardCount: int type.
	 */
	public  int getGameWhiteLanternCardCount() {
		return gameWhiteLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the white color.
	 * @param gameWhiteLanternCardCount the gameWhiteLanternCardCount to set: int type.
	 */
	public  void setGameWhiteLanternCardCount(int gameWhiteLanternCardCount) {
		this.gameWhiteLanternCardCount = gameWhiteLanternCardCount;
	}

	/**
	 * This method gets the game lantern card count for the green color.
	 * @return the gameGreenLanternCardCount: int type.
	 */
	public  int getGameGreenLanternCardCount() {
		return gameGreenLanternCardCount;
	}

	/**
	 * This method sets the game lantern card count for the green color.
	 * @param gameGreenLanternCardCount the gameGreenLanternCardCount to set: int type.
	 */
	public  void setGameGreenLanternCardCount(int gameGreenLanternCardCount) {
		this.gameGreenLanternCardCount = gameGreenLanternCardCount;
	}

	/**
	 * This method gets the current lake tiles arrangement.
	 * @return the currentLakeTilesArrangement: Vector<LakeTiles> type
	 */
	public Vector<LakeTiles> getCurrentLakeTilesArrangement() {
		return currentLakeTilesArrangement;
	}

	/**
	 * This method sets the current lake tiles arrangement.
	 * @param currentLakeTilesArrangement the currentLakeTilesArrangement to set: 
	 * Vector<LakeTiles> type
	 */
	public void setCurrentLakeTilesArrangement(
			Vector<LakeTiles> currentLakeTilesArrangement) {
		this.currentLakeTilesArrangement = currentLakeTilesArrangement;
	}
	
	/**
	 * This method gets the draw pile of the board game.
	 * @return the gameTilesDrawPile: Vector<LakeTiles> type
	 */
	public Vector<LakeTiles> getGameTilesDrawPile() {
		return gameTilesDrawPile;
	}

	/**
	 * This method sets the draw pile of the board game.
	 * @param gameTilesDrawPile the game tiles draw pile to set: Vector<LakeTiles> type
	 */
	public void setGameTilesDrawPile(Vector<LakeTiles> gameTilesDrawPile) {
		this.gameTilesDrawPile = gameTilesDrawPile;
	}

	/**
	 * This method gets the configuration of the current game.
	 * @return the config: GameConfiguration type
	 */
	public GameConfiguration getConfig() {
		return config;
	}

	/**
	 * This method sets the configuration of the current game.
	 * @param config the config to set: GameConfiguration type
	 */
	public void setConfig(GameConfiguration config) {
		this.config = config;
	}

	/**
	 * This method gets the start player of the current game.
	 * @return the gameStartPlayer: Players type.
	 */
	public Players getGameStartPlayer() {
		return gameStartPlayer;
	}

	/**
	 * This method sets the start player of the current game.
	 * @param gameStartPlayer the gameStartPlayer to set: Players type.
	 */
	public void setGameStartPlayer(Players gameStartPlayer) {
		this.gameStartPlayer = gameStartPlayer;
	}

	/**
	 * This method gets the start tile.
	 * @return the gameStartTile: LakeTiles type
	 */
	public LakeTiles getGameStartTile() {
		return gameStartTile;
	}

	/**
	 * This method sets the start tile.
	 * @param gameStartTile the gameStartTile to set: LakeTiles type
	 */
	public void setGameStartTile(LakeTiles gameStartTile) {
		this.gameStartTile = gameStartTile;
	}

	/**
	 * This method gets the game tile suite.
	 * @return the gameTileSuite: Vector<LakeTiles> type.
	 */
	public Vector<LakeTiles> getGameTileSuite() {
		return gameTileSuite;
	}

	/**
	 * This method sets the game tile suite.
	 * @param gameTileSuite the gameTileSuite to set: Vector<LakeTiles> type.
	 */
	public void setGameTileSuite(Vector<LakeTiles> gameTileSuite) {
		this.gameTileSuite = gameTileSuite;
	}

	/**
	 * This method gets the array of players positions.
	 * @return the playerPositions: String[] type.
	 */
	public String[] getPlayerPositions() {
		return playerPositions;
	}

	/**
	 * This method sets the array of players positions.
	 * @param playerPositions the playerPositions to set: String[] type.
	 */
	public void setPlayerPositions(String[] playerPositions) {
		this.playerPositions = playerPositions;
	}

	/**
	 * This method gets the default lantern card size.
	 * @return the defaultLanternCardSize: int type.
	 */
	public int getDefaultLanternCardSize() {
		return defaultLanternCardSize;
	}

	/**
	 * This method sets the default lantern card size.
	 * @param defaultLanternCardSize the defaultLanternCardSize to set: int type.
	 */
	public void setDefaultLanternCardSize(int defaultLanternCardSize) {
		this.defaultLanternCardSize = defaultLanternCardSize;
	}

	/**
	 * This method gets the winner player.
	 * @return the winnerPlayer: Players type.
	 */
	public Players getWinnerPlayer() {
		return winnerPlayer;
	}

	/**
	 * This method sets the winner player.
	 * @param winnerPlayer the winnerPlayer to set: Players type.
	 */
	public void setWinnerPlayer(Players winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}

	/**
	 * This method gets the game board layout of lake tiles.
	 * @return the gameBoard: int[][] type.
	 */
	public int[][] getGameBoard() {
		return GameBoard;
	}

	/**
	 * This method sets the game board layout of lake tiles.
	 * @param gameBoard the gameBoard to set: int[][] type.
	 */
	public void setGameBoard(int[][] gameBoard) {
		GameBoard = gameBoard;
	}

	/**
	 * This method sets the all lake tiles.
	 * @param allLakeTiles the allLakeTiles to set: Vector<LakeTiles> type
	 */
	public void setAllLakeTiles(Vector<LakeTiles> allLakeTiles) {
		this.allLakeTiles = allLakeTiles;
	}
	
}