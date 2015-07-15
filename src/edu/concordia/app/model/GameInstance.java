/**
 * 
 */
package edu.concordia.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;


@XmlRootElement
/**
 * @author TEAM E
 * Class to create the game object for the lantern game
 *
 */
public class GameInstance {

	// variable for the game configuration
	private GameConfiguration config;
	
	// variable for default lantern card size
	private int defaultLanternCardSize;
	
	
	/**
	 * the variable to hold current player
	 * 0 for game not started
	 * 1 - 4 for players
	 */
	private Players playerCurrentTurn;
	//	private int playerCurrentTurn;
	
	private Players gameStartPlayer;
	
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
	 * @return the playersList
	 */
	public Players[] getPlayersList() {
		return playersList;
	}

	/**
	 * @param playersList the playersList to set
	 */
	public void setPlayersList(Players[] playersList) {
		this.playersList = playersList;
	}

	//@XmlElementWrapper(name="drawPileTiles")
	//@XmlElement(name="tile")
	/**
	 * The vector to store the lake tiles for doing the draw.
	 */
	private Vector<LakeTiles> gameTilesDrawPile;
	
	

	//@XmlElementWrapper(name="tileSuite")
	//@XmlElement(name="tile")
	/**
	 * The vector to store the complete 36 lake tile and start tile.
	 */
	private Vector<LakeTiles> gameTileSuite;
	
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
	//@XmlElement(name="gameDedicationToken")
	/**
	 * object variable for DedicationTokens
	 */
	private DedicationTokens dedicationTokens;
	
	/**
	 * The vector contains arrangement of lake tiles that are already played.
	 */
	private Vector<LakeTiles> currentLakeTilesArrangement = new Vector<LakeTiles>();
	
	//@XmlElement(name="GameRedLanterCard")
	/**
	 * count for the red lantern cards for the board
	 */
	private  int gameRedLanternCardCount = 0;
	
	//@XmlElement(name="GameBlueLanternCard")
	/**
	 * count for the blue lantern cards for the board
	 */
	private  int gameBlueLanternCardCount = 0;
	
	//@XmlElement(name="GameOrangeLanternCard")
	/**
	 * count for the orange lantern cards for the board
	 */
	private  int gameOrangeLanternCardCount = 0;
	
	//@XmlElement(name="GameBlackLanternCard")
	/**
	 * count for the black lantern cards for the board
	 */
	private  int gameBlackLanternCardCount = 0;
	
	//@XmlElement(name="GamePurpleLanternCard")
	/**
	 * count for the purple lantern cards for the board
	 */
	private  int gamePurpleLanternCardCount = 0;
	
	//@XmlElement(name="GameWhiteLanternCard")
	/**
	 * count for the white lantern cards for the board
	 */
	private  int gameWhiteLanternCardCount = 0;
	
	//@XmlElement(name="GameGreenLanternCard")
	/**
	 * count for the green lantern cards for the board
	 */
	private  int gameGreenLanternCardCount = 0;
	
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
	 * Constructor
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
	 * initializing the game data
	 */
	private void initializeGameData(){
		
		//initialize players of the game
		initializePlayers();
		
		//initialize Lantern Cards
		initializeLanternCards();
		
		//initialize favor tokens
		//initializeFavorTokens();
		
		initializeGameTiles();
		
		//initialize dedicated tokens
		initializeDedicationTokens();
		
		//shuffle lake tiles
		Vector<LakeTiles> shuffledTiles= shuffleTiles(gameTileSuite);
		
		//deal lake tiles to each player
		Vector<LakeTiles> shuffledTilesAfterDeal = dealLakeTileCards(shuffledTiles);
		
		//create draw pile of Lake tiles according to no of players
		gameTilesDrawPile = initializeDrawPileTiles(shuffledTilesAfterDeal);
		
		System.out.println("deal size"+gameTilesDrawPile.size());
		
		//reveal all color of start tile
		//set face color to each player
		playStartTile(gameStartTile);
		
		//determine the start player and also current player
		gameStartPlayer = determineStartPlayer(playersList);
		
		if(gameStartPlayer == null){
			System.out.println("start player is null.");
		}
		
		//assign lantern cards to players and remove from game stack
		assignLanternCards();
		
		//chooseStartTileOrientation();
		
	}
	
	/**
	 * this method assign lantern cards to the players according to the lake tile colors
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
	 * this method determines the player who will start the game
	 * @param playersList2
	 * @return
	 */
	private Players determineStartPlayer(Players[] playersList2) {
		Players startPlayer = null;
		
		for (int i = 0; i < playersList2.length; i++) {
			Players playerCheck = playersList2[i];
			//System.out.println(playerCheck.getFaceColor()+" "+playerCheck.getPlayerNumber());
			if(playerCheck.getFaceColor().equals("RED")){
			startPlayer = playerCheck;
			//playerCurrentTurn = playerCheck;
			setPlayerCurrentTurn(playerCheck);
			}
		}
		//System.out.println("check "+getPlayerCurrentTurn().getPlayerNumber());
		return startPlayer;
	}

	/**
	 * 
	 */
	/*private void chooseStartTileOrientation() {

		// int Random = (int)(Math.random()*3);
		// System.out.println(Random);
		
		int startPlr = noOfPlayers;

		Random randomGenerator = new Random();

		//choose random numbers between 0 - 3
		int randomInt = randomGenerator.nextInt(startPlr - 1);
		
		//increase random numbers by 1 to choose range 1 - 4
		int startPlayer = randomInt + 1;
		
		gameStartPlayer = new Players();
		

	}*/

	/**
	 * this method deals the lake tiles to players, 3 cards to each player
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
		
//		Vector<LakeTiles> shuffledTilesAfterDeal = shuffledTiles;
//		int shuffledTilesSize = shuffledTiles.size();
		
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
	
	/**
	 * Initialize the lantern cards of the game.
	 */
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
	
	/**
	 * Initialize the dedication tokens of the game.
	 */
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
	
	/**
	 * Initialize the lake tiles of the game.
	 */
	private void initializeGameTiles(){
		this.gameTileSuite = config.GAME_TOTAL_TILE_SUITE;
		
		//remove start tile from tile suite
		LakeTiles startTile =  gameTileSuite.remove(0);
		
		//System.out.println("start tile "+startTile.getTopColor());
		
		// set start tile of game
		gameStartTile = startTile;
	}
	
	/** This method get colors of start tile and assign face colors to each player
	 * @param gameStartTile
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
	
	/** This method retunrs the player who will start the game
	 * @param gameStartTile
	 */
	private Players getStartPlayer(Players[] playersList){
		return gameStartPlayer;
		
	}
	
	// get a random player number for the start
	private int getRandomNumber(int playerNum){
		
		Random randomNumbers=new Random(0);
				
		//int random = 1 + randomNumbers.nextInt(4); // number between 1 to 4
		int random = randomNumbers.nextInt(playerNum);  //number between 0 to 3
		System.out.println("RANDOM NUMBER "+random);
		
		return random;
	}
	
	/**
	 * @param tiles The vector containing complete lake tiles.
	 * @return Return the vector containing shuffled lake tiles.
	 */
	private Vector<LakeTiles> shuffleTiles(Vector<LakeTiles> tiles){
		
		Collections.shuffle(tiles);
		return tiles;
	}
	
	/**
	 * method for the draw pile lake tiles
	 * @param tiles The vector containing shuffled lake tiles.
	 * @return Return the vector containing draw pile lake tiles.
	 */
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

	/**
	 * @return the gameRedLanternCardCount
	 */
	public  int getGameRedLanternCardCount() {
		return gameRedLanternCardCount;
	}

	/**
	 * @param gameRedLanternCardCount the gameRedLanternCardCount to set
	 */
	public  void setGameRedLanternCardCount(int gameRedLanternCardCount) {
		this.gameRedLanternCardCount = gameRedLanternCardCount;
	}

	/**
	 * @return the gameBlueLanternCardCount
	 */
	public  int getGameBlueLanternCardCount() {
		return gameBlueLanternCardCount;
	}

	/**
	 * @param gameBlueLanternCardCount the gameBlueLanternCardCount to set
	 */
	public  void setGameBlueLanternCardCount(int gameBlueLanternCardCount) {
		this.gameBlueLanternCardCount = gameBlueLanternCardCount;
	}

	/**
	 * @return the gameOrangeLanternCardCount
	 */
	public  int getGameOrangeLanternCardCount() {
		return gameOrangeLanternCardCount;
	}

	/**
	 * @param gameOrangeLanternCardCount the gameOrangeLanternCardCount to set
	 */
	public  void setGameOrangeLanternCardCount(int gameOrangeLanternCardCount) {
		this.gameOrangeLanternCardCount = gameOrangeLanternCardCount;
	}

	/**
	 * @return the gameBlackLanternCardCount
	 */
	public  int getGameBlackLanternCardCount() {
		return gameBlackLanternCardCount;
	}

	/**
	 * @param gameBlackLanternCardCount the gameBlackLanternCardCount to set
	 */
	public  void setGameBlackLanternCardCount(int gameBlackLanternCardCount) {
		this.gameBlackLanternCardCount = gameBlackLanternCardCount;
	}

	/**
	 * @return the gamePurpleLanternCardCount
	 */
	public  int getGamePurpleLanternCardCount() {
		return gamePurpleLanternCardCount;
	}

	/**
	 * @param gamePurpleLanternCardCount the gamePurpleLanternCardCount to set
	 */
	public  void setGamePurpleLanternCardCount(int gamePurpleLanternCardCount) {
		this.gamePurpleLanternCardCount = gamePurpleLanternCardCount;
	}

	/**
	 * @return the gameWhiteLanternCardCount
	 */
	public  int getGameWhiteLanternCardCount() {
		return gameWhiteLanternCardCount;
	}

	/**
	 * @param gameWhiteLanternCardCount the gameWhiteLanternCardCount to set
	 */
	public  void setGameWhiteLanternCardCount(int gameWhiteLanternCardCount) {
		this.gameWhiteLanternCardCount = gameWhiteLanternCardCount;
	}

	/**
	 * @return the gameGreenLanternCardCount
	 */
	public  int getGameGreenLanternCardCount() {
		return gameGreenLanternCardCount;
	}

	/**
	 * @param gameGreenLanternCardCount the gameGreenLanternCardCount to set
	 */
	public  void setGameGreenLanternCardCount(int gameGreenLanternCardCount) {
		this.gameGreenLanternCardCount = gameGreenLanternCardCount;
	}

	/**
	 * @return the currentLakeTilesArrangement
	 */
	public Vector<LakeTiles> getCurrentLakeTilesArrangement() {
		return currentLakeTilesArrangement;
	}

	/**
	 * @param currentLakeTilesArrangement the currentLakeTilesArrangement to set
	 */
	public void setCurrentLakeTilesArrangement(
			Vector<LakeTiles> currentLakeTilesArrangement) {
		this.currentLakeTilesArrangement = currentLakeTilesArrangement;
	}
	
	/**
	 * @return the gameTilesDrawPile
	 */
	public Vector<LakeTiles> getGameTilesDrawPile() {
		return gameTilesDrawPile;
	}

	/**
	 * @param gameTilesDrawPile the gameTilesDrawPile to set
	 */
	public void setGameTilesDrawPile(Vector<LakeTiles> gameTilesDrawPile) {
		this.gameTilesDrawPile = gameTilesDrawPile;
	}

}
