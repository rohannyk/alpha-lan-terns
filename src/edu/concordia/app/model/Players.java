/**
 * 
 */
package edu.concordia.app.model;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.FavorTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.components.LanternCards;
import edu.concordia.app.components.LanternCards.Color;
import edu.concordia.app.components.Tokens;

/**
 * This class contains information about the lantern game players.
 * @author Team E
 *
 */
public class Players {

	private GameConfiguration config;
	
	//@XmlAttribute
	/**
	 * The number of the player.
	 */
	private int playerNumber;
	
	/**
	 * The total honor points of the player.
	 */
	private int totalHonorPoints = 0;


	@XmlAttribute
	/**
	 * The score of the player.
	 */
	public int playerScore; 
	
	
	/**
	 * The color of lake tile the 
	 */
	private String faceColor;// player facing current lake tile color
	
	@XmlAttribute
	/**
	 * The player position during the game.
	 */
	private String playerPosition;
	
	//@XmlElement(name="currentLakeTile")
	
	
	/**
	 * The lake tiles holded by the player.
	 */
	private Vector<LakeTiles> currentLakeTilesHold = new Vector<LakeTiles>();
	
	//@XmlElement(name="playerDedicationTokens")
	
	
	/**
	 * The dedication tokens of player of the game.
	 */
	private DedicationTokens dedicationTokens;
	
	//count oflantern cards;
	
	/**
	 * The count of Red Lantern cards of player
	 */
	private int playerRedLanternCardCount = 0;
	
	/**
	 * The count of Blue Lantern cards of player
	 */
	private int playerBlueLanternCardCount = 0;
	
	/**
	 * The count of Orange Lantern cards of player
	 */
	private int playerOrangeLanternCardCount = 0;
	
	/**
	 * The count of Black Lantern cards of player
	 */
	private int playerBlackLanternCardCount = 0;
	
	/**
	 * The count of Purple Lantern cards of player
	 */
	private int playerPurpleLanternCardCount = 0;
	
	/**
	 * The count of White Lantern cards of player
	 */
	private int playerWhiteLanternCardCount = 0;
	
	/**
	 * The count of Green Lantern cards of player
	 */
	private int playerGreenLanternCardCount = 0;
	
	
	
	/**
	 * The vector to store General dedication tokens with four dedications  for player.
	 */
	private Vector<Tokens> dedicationTokenFour;
	
	/**
	 * The vector to store General dedication tokens with six dedications for player.
	 */
	private Vector<Tokens> dedicationTokenSix;
	
	/**
	 * The vector to store General dedication tokens with seven dedications for player.
	 */
	private Vector<Tokens> dedicationTokenSeven;
	
	/**
	 * The vector to store generic dedication tokens for player.
	 */
	private Vector<Tokens> genericDedicationToken;
	
	//-----
	
	@XmlElement(name="playerDealCards")
	public Vector<LakeTiles> playerDealTiles;
	
	/**
	 * The vector of lanterns cards that player holds during the game.
	 */
	Vector<LanternCards> playerLanternSuite;
	
	//private FavorTokens favorToken;
	
	/**
	 * The number of favor tokens holded by player.
	 */
	private int playerFavorToken;
	
	//private boolean starPlayerMarker;
	
	// each variable for different card added
	
	/**
	 * Default constructor of Players class
	 * Initialize all variables and values required during start of the game.
	 */
	public Players() {
		setDedicationTokens(new DedicationTokens());
		//lanternCards = new LanternCards();
		playerLanternSuite = new Vector<LanternCards>();
		
		// initialize the vector suite of lantern cards to zero.
		intializeLanternCards();
	}

	

	/**
	 * Players constructor initialize player with player number,
	 * player position and GameConfiguration object.
	 * @param config GameConfiguration class object
	 * @param playerNumber The player number in the game
	 * @param playerPosition The player position in the game.
	 */
	public Players(GameConfiguration config, int playerNumber, String playerPosition) {
		this.config = config;
		this.playerNumber = playerNumber;
		this.playerPosition = playerPosition;
		
		playerDealTiles = new Vector<LakeTiles>(config.PLAYER_LAKE_TILE_DEAL_SIZE);
//		setPlayerDealTiles(new Vector<LakeTiles>(config.PLAYER_LAKE_TILE_DEAL_SIZE));
		
		//playerDealTiles = config.PLAYER_LAKE_TILE_DEAL_SIZE;
		
		setDedicationTokens(new DedicationTokens());
		//lanternCards = new LanternCards();
		playerLanternSuite = new Vector<LanternCards>();
		
		// initialize the vector suite of lantern cards to zero.
		intializeLanternCards();
	}
	
	/**
	 * This method will initialize the lantern cards in the LanterCards class.
	 */
	private void intializeLanternCards() {
		playerLanternSuite = new Vector<LanternCards>();
		Color lanterCardColors[] = Color.values();
		
		// initialize all color cards to zero
		for (int i = 0; i < lanterCardColors.length; i++) {
			playerLanternSuite.add(new LanternCards(lanterCardColors[i], 0));
		}
	}

	/**
	 * @return the playerRedLanternCardCount
	 */
	public int getPlayerRedLanternCardCount() {
		return playerRedLanternCardCount;
	}


	/**
	 * @param playerRedLanternCardCount the playerRedLanternCardCount to set
	 */
	public void setPlayerRedLanternCardCount(int playerRedLanternCardCount) {
		this.playerRedLanternCardCount = playerRedLanternCardCount;
	}


	/**
	 * @return the playerBlueLanternCardCount
	 */
	public int getPlayerBlueLanternCardCount() {
		return playerBlueLanternCardCount;
	}



	/**
	 * @param playerBlueLanternCardCount the playerBlueLanternCardCount to set
	 */
	public void setPlayerBlueLanternCardCount(int playerBlueLanternCardCount) {
		this.playerBlueLanternCardCount = playerBlueLanternCardCount;
	}



	/**
	 * @return the playerOrangeLanternCardCount
	 */
	public int getPlayerOrangeLanternCardCount() {
		return playerOrangeLanternCardCount;
	}



	/**
	 * @param playerOrangeLanternCardCount the playerOrangeLanternCardCount to set
	 */
	public void setPlayerOrangeLanternCardCount(int playerOrangeLanternCardCount) {
		this.playerOrangeLanternCardCount = playerOrangeLanternCardCount;
	}



	/**
	 * @return the playerBlackLanternCardCount
	 */
	public int getPlayerBlackLanternCardCount() {
		return playerBlackLanternCardCount;
	}



	/**
	 * @param playerBlackLanternCardCount the playerBlackLanternCardCount to set
	 */
	public void setPlayerBlackLanternCardCount(int playerBlackLanternCardCount) {
		this.playerBlackLanternCardCount = playerBlackLanternCardCount;
	}



	/**
	 * @return the playerPurpleLanternCardCount
	 */
	public int getPlayerPurpleLanternCardCount() {
		return playerPurpleLanternCardCount;
	}



	/**
	 * @param playerPurpleLanternCardCount the playerPurpleLanternCardCount to set
	 */
	public void setPlayerPurpleLanternCardCount(int playerPurpleLanternCardCount) {
		this.playerPurpleLanternCardCount = playerPurpleLanternCardCount;
	}



	/**
	 * @return the playerWhiteLanternCardCount
	 */
	public int getPlayerWhiteLanternCardCount() {
		return playerWhiteLanternCardCount;
	}



	/**
	 * @param playerWhiteLanternCardCount the playerWhiteLanternCardCount to set
	 */
	public void setPlayerWhiteLanternCardCount(int playerWhiteLanternCardCount) {
		this.playerWhiteLanternCardCount = playerWhiteLanternCardCount;
	}



	/**
	 * @return the playerGreenLanternCardCount
	 */
	public int getPlayerGreenLanternCardCount() {
		return playerGreenLanternCardCount;
	}



	/**
	 * @param playerGreenLanternCardCount the playerGreenLanternCardCount to set
	 */
	public void setPlayerGreenLanternCardCount(int playerGreenLanternCardCount) {
		this.playerGreenLanternCardCount = playerGreenLanternCardCount;
	}



	



	/**
	 * @return the dedicationTokenFour
	 */
	public Vector<Tokens> getDedicationTokenFour() {
		return dedicationTokenFour;
	}



	/**
	 * @param dedicationTokenFour the dedicationTokenFour to set
	 */
	public void setDedicationTokenFour(Vector<Tokens> dedicationTokenFour) {
		this.dedicationTokenFour = dedicationTokenFour;
	}



	/**
	 * @return the dedicationTokenSix
	 */
	public Vector<Tokens> getDedicationTokenSix() {
		return dedicationTokenSix;
	}



	/**
	 * @param dedicationTokenSix the dedicationTokenSix to set
	 */
	public void setDedicationTokenSix(Vector<Tokens> dedicationTokenSix) {
		this.dedicationTokenSix = dedicationTokenSix;
	}



	/**
	 * @return the dedicationTokenSeven
	 */
	public Vector<Tokens> getDedicationTokenSeven() {
		return dedicationTokenSeven;
	}



	/**
	 * @param dedicationTokenSeven the dedicationTokenSeven to set
	 */
	public void setDedicationTokenSeven(Vector<Tokens> dedicationTokenSeven) {
		this.dedicationTokenSeven = dedicationTokenSeven;
	}



	/**
	 * @return the genericDedicationToken
	 */
	public Vector<Tokens> getGenericDedicationToken() {
		return genericDedicationToken;
	}



	/**
	 * @param genericDedicationToken the genericDedicationToken to set
	 */
	public void setGenericDedicationToken(Vector<Tokens> genericDedicationToken) {
		this.genericDedicationToken = genericDedicationToken;
	}



	/**
	 * @return the playerFavorToken
	 */
	public int getPlayerFavorToken() {
		return playerFavorToken;
	}


	/**
	 * @param playerFavorToken the playerFavorToken to set
	 */
	public void setPlayerFavorToken(int playerFavorToken) {
		this.playerFavorToken = playerFavorToken;
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
	 * @return the currentLakeTilesHold
	 */
	public Vector<LakeTiles> getCurrentLakeTilesHold() {
		return currentLakeTilesHold;
	}



	/**
	 * @param currentLakeTilesHold the currentLakeTilesHold to set
	 */
	public void setCurrentLakeTilesHold(Vector<LakeTiles> currentLakeTilesHold) {
		this.currentLakeTilesHold = currentLakeTilesHold;
	}



	/**
	 * @return the playerNumber
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}



	/**
	 * @param playerNumber the playerNumber to set
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}



	/**
	 * @return the faceColor
	 */
	public String getFaceColor() {
		return faceColor;
	}



	/**
	 * @param faceColor the faceColor to set
	 */
	public void setFaceColor(String faceColor) {
		this.faceColor = faceColor;
	}



	/**
	 * @return the totalHonorPoints
	 */
	public int getTotalHonorPoints() {
		return totalHonorPoints;
	}



	/**
	 * @param totalHonorPoints the totalHonorPoints to set
	 */
	public void setTotalHonorPoints(int totalHonorPoints) {
		this.totalHonorPoints = totalHonorPoints;
	}



//	/**
//	 * @return the playerDealTiles
//	 */
//	public Vector<LakeTiles> getPlayerDealTiles() {
//		return playerDealTiles;
//	}
//
//
//
//	/**
//	 * @param playerDealTiles the playerDealTiles to set
//	 */
//	public void setPlayerDealTiles(Vector<LakeTiles> playerDealTiles) {
//		this.playerDealTiles = playerDealTiles;
//	}
	
	

}
