/**
 * 
 */
package edu.concordia.app.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.strategy.FriendlyPlayerStrategy;
import edu.concordia.app.strategy.GreedyPlayerStrategy;
import edu.concordia.app.strategy.NormalPlayerStrategy;
import edu.concordia.app.strategy.RandomPlayerStrategy;
import edu.concordia.app.strategy.UnfriendlyPlayerStrategy;

@XmlRootElement
/**
 * GameInstance class for the Board Game object
 * 
 * @author TEAM E
 *
 */
public class GameInstance implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2962849411013972966L;
	
	private GameConfiguration config;
	public int GameBoard [][] =  new int [73][73];
	private int defaultLanternCardSize;
	//tiles vector to get all tiles details by id
	private Vector<LakeTiles> allLakeTiles = new Vector<LakeTiles>();
	
	private Vector<String> playerTypes;
	
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
	
	private PlayGame gameEndMode = new PlayGame();
	
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
	public GameInstance(GameConfiguration config, Vector<String> playerTypes) {
		
		this.config = config;
		this.noOfPlayers = config.NUM_OF_PLAYERS;
		this.defaultLanternCardSize = config.NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;
		
		this.playerTypes = playerTypes;
		
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
		Vector<LakeTiles> shuffledTiles = shuffleTiles(gameTileSuite);
		
		//System.out.println("shuffled tiles "+shuffledTiles.size());
		
		//create draw pile of Lake tiles according to no of players
		Vector<LakeTiles> filteredExcessShuffledTiles = initializeDrawPileTiles(shuffledTiles);
		
		//System.out.println("filtered shuffled tiles "+filteredExcessShuffledTiles.size());
		
		//deal lake tiles to each player
		gameTilesDrawPile = dealLakeTileCards(filteredExcessShuffledTiles);
		
		//System.out.println("draw pile shuffled tiles "+gameTilesDrawPile.size());
		
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
	private void initializePlayers() {

		playersList = new Players[config.NUM_OF_PLAYERS];

		if (playersList.length == 2) {
			playersList[0] = new Players(config, 1, playerPositions[0]);
			playersList[1] = new Players(config, 2, playerPositions[2]);
		}

		else {
			for (int i = 0; i < playersList.length; i++) {
				playersList[i] = new Players(config, i + 1, playerPositions[i]);
			}
		}

		// set player strategy.
		for (int i = 0; i < playersList.length; i++) {
			String pStrategy = playerTypes.get(i);

			if (pStrategy.equals("Greedy")) {
				playersList[i].setStrategy(new GreedyPlayerStrategy(this));
			} else if (pStrategy.equals("Unfriendly")) {
				playersList[i].setStrategy(new UnfriendlyPlayerStrategy(this));
			} else if (pStrategy.equals("Random")) {
				playersList[i].setStrategy(new RandomPlayerStrategy(this));
			} else if (pStrategy.equals("Friendly")) {
				playersList[i].setStrategy(new FriendlyPlayerStrategy(this));
			} else if (pStrategy.equals("Human")) {
				playersList[i].setStrategy(new NormalPlayerStrategy(this));
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
	 * This method gets a random number according to number of players.
	 * @param playerNum: int type
	 * @return random: int type 
	 */
	private int getRandomNumber(int playerNum){
		
		Random randomNumbers = new Random(0);
				
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

	/**
	 * This method will print the game state and player state on console. Prints Number of Players, Start Player, Current Player Prints Game Lantern Cards Status, Game Dedication Tokens. Prints Available Lake Tiles in shuffled deck, Shuffled Lake Tiles. Prints Current Lake Tile Arrangement, Game available Favor Tokens. Prints Player Number, Number of favor tokens player has. Prints Lantern Cards Player Holds, Player Lake Tiles. Prints No of Lake Tiles held, Lake Tiles held, Player Dedication Tokens. Creates objects of DedicationTokens, Vector<LakeTiles>, LakeTiles, Players[]
	 */
	public void showTextMode() {
		System.out.println(" ********* Game Status in Text Mode ********* ");
		System.out.println();
		System.out.println("Number of Players: " + getNoOfPlayers());
		System.out.println();
		System.out.println("Start Player: "
				+ getGameStartPlayer().getPlayerNumber());
		System.out.println();
		System.out.println("Current Player: "
				+ getPlayerCurrentTurn().getPlayerNumber());
		System.out.println();
		System.out.println("-----Game Lantern Cards Status-----");
		System.out.println();
		System.out.println("Total Lantern cards: " + getLanternCardCount());
		System.out.println("Red Lantern Cards Left: "
				+ getGameRedLanternCardCount());
		System.out.println("Blue Lantern Cards Left: "
				+ getGameBlueLanternCardCount());
		System.out.println("Black Lantern Cards Left: "
				+ getGameBlackLanternCardCount());
		System.out.println("Green Lantern Cards Left: "
				+ getGameGreenLanternCardCount());
		System.out.println("Orange Lantern Cards Left: "
				+ getGameOrangeLanternCardCount());
		System.out.println("Purple Lantern Cards Left: "
				+ getGamePurpleLanternCardCount());
		System.out.println("White Lantern Cards Left: "
				+ getGameWhiteLanternCardCount());
		System.out.println();
		System.out.println("-----Game Dedication Tokens-----");
		System.out.println();
		System.out.println("Next Dedication Token Four: "
				+ getNextDedicationTokenFour());
		System.out.println("Next Dedication Token Six: "
				+ getNextDedicationTokenSix());
		System.out.println("Next Dedication Token Seven: "
				+ getNextDedicationTokenSeven());
		System.out.println("Next Genric Dedication Token: "
				+ getNextGenericDedicationToken());
		System.out.println();
		System.out.println("-----Game Dedication Tokens Count-----");
		System.out.println();
		DedicationTokens deTokens = getDedicationTokens();
		System.out.println("Dedication Token Four: "
				+ deTokens.getDedicationTokenFourSize());
		System.out.println("Dedication Token Six: "
				+ deTokens.getDedicationTokenSixSize());
		System.out.println("Dedication Token Seven: "
				+ deTokens.getDedicationTokenSevenSize());
		System.out.println("Genric Dedication Token: "
				+ deTokens.getGenericDedicationTokensSize());
		System.out.println();
		System.out.println("Available Lake Tiles in shuffled deck  : "
				+ getGameTilesDrawPile().size());
		System.out.println();
		System.out.println("-----Shuffled Lake Tiles-----");
		Vector<LakeTiles> drawTile = getGameTilesDrawPile();
		for (int i = 0; i < drawTile.size(); i++) {
			LakeTiles currTile = drawTile.get(i);
			System.out.println("Lake Tile Number: " + currTile.getTilesId());
		}
		System.out.println();
		System.out.println("-----Current Lake Tile Arrangement-----");
		System.out.println();
		Vector<LakeTiles> tileArrangement = getCurrentLakeTilesArrangement();
		System.out.println("No of Lake Tile: " + tileArrangement.size());
		for (Iterator<LakeTiles> iterator = tileArrangement.iterator(); iterator
				.hasNext();) {
			LakeTiles lakeTiles = (LakeTiles) iterator.next();
			System.out.println("Lake Tile Id: " + lakeTiles.getTilesId()
					+ " TC: " + lakeTiles.getTopColor() + " BC: "
					+ lakeTiles.getBottomColor() + " LC: "
					+ lakeTiles.getLeftColor() + " RC: "
					+ lakeTiles.getRightColor());
		}
		System.out.println();
		System.out.println("Game available Favor Tokens: "
				+ getGameFavorToken());
		System.out.println();
		System.out.println("-----Players Status-----");
		Players[] playersArray = getPlayersList();
		for (int i = 0; i < playersArray.length; i++) {
			Players player = playersArray[i];
			System.out.println();
			System.out.println("********* Player Number: "
					+ player.getPlayerNumber() + " **********");
			System.out.println();
			System.out.println("Number of favor tokens player has: "
					+ player.getPlayerFavorToken());
			System.out.println();
			System.out.println("-------Lantern Cards Player Holds-----");
			System.out.println();
			System.out.println("Total Lantern cards: "
					+ player.getLanternCardCount());
			System.out.println("Red Lantern Cards Left: "
					+ player.getPlayerRedLanternCardCount());
			System.out.println("Blue Lantern Cards Left: "
					+ player.getPlayerBlueLanternCardCount());
			System.out.println("Black Lantern Cards Left: "
					+ player.getPlayerBlackLanternCardCount());
			System.out.println("Green Lantern Cards Left: "
					+ player.getPlayerGreenLanternCardCount());
			System.out.println("Orange Lantern Cards Left: "
					+ player.getPlayerOrangeLanternCardCount());
			System.out.println("Purple Lantern Cards Left: "
					+ player.getPlayerPurpleLanternCardCount());
			System.out.println("White Lantern Cards Left: "
					+ player.getPlayerWhiteLanternCardCount());
			System.out.println();
			System.out.println("-----Player Lake Tiles -----");
			System.out.println();
			Vector<LakeTiles> lakeTiles = player.getCurrentLakeTilesHold();
			System.out.println("No of Lake Tiles held: " + lakeTiles.size());
			for (int j = 0; j < lakeTiles.size(); j++) {
				LakeTiles tileHolded = lakeTiles.get(j);
				System.out
						.println("Lake Tiles held " + tileHolded.getTilesId());
			}
			System.out.println();
			System.out.println("-----Player Dedication Tokens -----");
			DedicationTokens playerDedicationTokens = player
					.getDedicationTokens();
			Vector<Integer> tokenFour = playerDedicationTokens
					.getDedicationTokenFour();
			if (tokenFour.size() == 0) {
				System.out.println("Primary Dedication Tokens Four: "
						+ tokenFour.size());
			} else {
				for (int j = 0; j < tokenFour.size(); j++) {
					System.out.println("Primary Dedication Tokens Four Size: "
							+ tokenFour.size());
					System.out.println("Primary Dedication Tokens Four: "
							+ tokenFour.get(j));
				}
			}
			Vector<Integer> tokenSix = playerDedicationTokens
					.getDedicationTokenSix();
			if (tokenSix.size() == 0) {
				System.out.println("Primary Dedication Tokens Six: "
						+ tokenSix.size());
			} else {
				for (int j = 0; j < tokenSix.size(); j++) {
					System.out.println("Primary Dedication Tokens Six Size: "
							+ tokenSix.size());
					System.out.println("Primary Dedication Tokens Six: "
							+ tokenSix.get(j));
				}
			}
			Vector<Integer> tokenSeven = playerDedicationTokens
					.getDedicationTokenSeven();
			if (tokenSeven.size() == 0) {
				System.out.println("Primary Dedication Tokens Seven: "
						+ tokenSeven.size());
			} else {
				for (int j = 0; j < tokenSeven.size(); j++) {
					System.out.println("Primary Dedication Tokens Seven Size: "
							+ tokenSeven.size());
					System.out.println("Primary Dedication Tokens Seven: "
							+ tokenSeven.get(j));
				}
			}
			Vector<Integer> genericToken = playerDedicationTokens
					.getGenericDedicationTokens();
			if (genericToken.size() == 0) {
				System.out.println("Generic Dedication Tokens: "
						+ genericToken.size());
			} else {
				for (int j = 0; j < genericToken.size(); j++) {
					System.out.println("Generic Dedication Tokens size: "
							+ genericToken.size());
					System.out.println("Generic Dedication Tokens: "
							+ genericToken.get(j));
				}
			}
			System.out.println();
			System.out.println("********* Player Number: "
					+ player.getPlayerNumber() + " **********");
		}
		System.out.println();
	}

	/**
	 * The method to update the lantern cards of given color of GameInstance for exchange.
	 * @param gameColor The color of lantern card removed from GameInstance.
	 * @param yourColor The color of lantern card added to GameInstance.
	 */
	public void gameColorAugment(String gameColor, String yourColor) {
		if (yourColor.equals("Black"))
			setGameBlackLanternCardCount(getGameBlackLanternCardCount() + 1);
		if (yourColor.equals("White"))
			setGameWhiteLanternCardCount(getGameWhiteLanternCardCount() + 1);
		if (yourColor.equals("Blue"))
			setGameBlueLanternCardCount(getGameBlueLanternCardCount() + 1);
		if (yourColor.equals("Green"))
			setGameGreenLanternCardCount(getGameGreenLanternCardCount() + 1);
		if (yourColor.equals("Orange"))
			setGameOrangeLanternCardCount(getGameOrangeLanternCardCount() + 1);
		if (yourColor.equals("Red"))
			setGameRedLanternCardCount(getGameRedLanternCardCount() + 1);
		if (yourColor.equals("Purple"))
			setGamePurpleLanternCardCount(getGamePurpleLanternCardCount() + 1);
		if (gameColor.equals("Black"))
			setGameBlackLanternCardCount(getGameBlackLanternCardCount() - 1);
		if (gameColor.equals("White"))
			setGameWhiteLanternCardCount(getGameWhiteLanternCardCount() - 1);
		if (gameColor.equals("Blue"))
			setGameBlueLanternCardCount(getGameBlueLanternCardCount() - 1);
		if (gameColor.equals("Green"))
			setGameGreenLanternCardCount(getGameGreenLanternCardCount() - 1);
		if (gameColor.equals("Orange"))
			setGameOrangeLanternCardCount(getGameOrangeLanternCardCount() - 1);
		if (gameColor.equals("Red"))
			setGameRedLanternCardCount(getGameRedLanternCardCount() - 1);
		if (gameColor.equals("Purple"))
			setGamePurpleLanternCardCount(getGamePurpleLanternCardCount() - 1);
	}

	/**
	 * The method to validate if the game has at least one lantern card of specified color to perform the exchange operation.
	 * @param gameColor The color exchanged by the GameInstance.
	 * @return  true if the GameInstance can exchange the lantern card.
	 */
	public boolean lanternColorGameValidation(String gameColor) {
		if (gameColor.equals("Black"))
			if (getGameBlackLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("White"))
			if (getGameWhiteLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Blue"))
			if (getGameBlueLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Green"))
			if (getGameGreenLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Orange"))
			if (getGameOrangeLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Red"))
			if (getGameRedLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Purple"))
			if (getGamePurpleLanternCardCount() > 0) {
				return true;
			}
		return false;
	}

	/**
	 * @return the gameEndMode
	 */
	public PlayGame getGameEndMode() {
		return gameEndMode;
	}

	/**
	 * @param gameEndMode the gameEndMode to set
	 */
	public void setGameEndMode(PlayGame gameEndMode) {
		this.gameEndMode = gameEndMode;
	}
	
}