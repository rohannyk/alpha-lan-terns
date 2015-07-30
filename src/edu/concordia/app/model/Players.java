/**
 * 
 */
package edu.concordia.app.model;

import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.components.LanternCards;
import edu.concordia.app.components.LanternCards.Color;
import edu.concordia.app.components.Tokens;

/**
 * This class contains information about the lantern game players.
 * 
 * @author Team E
 *
 */
public class Players {

	private GameConfiguration config;

	/**
	 * The number of the player.
	 */
	private int playerNumber;

	/**
	 * The total honor points of the player.
	 */
	private int totalHonorPoints = 0;
	
	/**
	 * The total points player scored.
	 */
	private int totalPoints = 0;
	
	/**
	 * The total lantern cards player holds.
	 */
	private int lanterCardCount = 0;

	@XmlAttribute
	/**
	 * The score of the player.
	 */
	public int playerScore;

	/**
	 * The color of lake tile the
	 */
	private String faceColor;// player facing current lake tile color

	/**
	 * The player position during the game.
	 */
	private String playerPosition;

	/**
	 * The lake tiles holded by the player.
	 */
	private Vector<LakeTiles> currentLakeTilesHold = new Vector<LakeTiles>();

	/**
	 * The dedication tokens of player of the game.
	 */
	private DedicationTokens dedicationTokens;

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
	 * The vector to store General dedication tokens with four dedications for
	 * player.
	 */
	private Vector<Tokens> dedicationTokenFour;

	/**
	 * The vector to store General dedication tokens with six dedications for
	 * player.
	 */
	private Vector<Tokens> dedicationTokenSix;

	/**
	 * The vector to store General dedication tokens with seven dedications for
	 * player.
	 */
	private Vector<Tokens> dedicationTokenSeven;

	/**
	 * The vector to store generic dedication tokens for player.
	 */
	private Vector<Tokens> genericDedicationToken;

	@XmlElement(name = "playerDealCards")
	public Vector<LakeTiles> playerDealTiles;

	/**
	 * The vector of lanterns cards that player holds during the game.
	 */
	Vector<LanternCards> playerLanternSuite;

	/**
	 * The number of favor tokens holded by player.
	 */
	private int playerFavorToken;

	/**
	 * Default constructor of Players class Initialize all variables and values
	 * required during start of the game.
	 */
	public Players() {
		setDedicationTokens(new DedicationTokens());
		playerLanternSuite = new Vector<LanternCards>();

		intializeLanternCards();
	}

	/**
	 * Players constructor initialize player with player number, player position
	 * and GameConfiguration object.
	 * 
	 * @param config
	 *            GameConfiguration class object
	 * @param playerNumber
	 *            The player number in the game
	 * @param playerPosition
	 *            The player position in the game.
	 */
	public Players(GameConfiguration config, int playerNumber, String playerPosition) {
		this.config = config;
		this.playerNumber = playerNumber;
		this.playerPosition = playerPosition;

		playerDealTiles = new Vector<LakeTiles>(config.PLAYER_LAKE_TILE_DEAL_SIZE);

		setDedicationTokens(new DedicationTokens());
		playerLanternSuite = new Vector<LanternCards>();

		intializeLanternCards();
	}

	/**
	 * This method initializes the lantern cards.
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
	 * This method gets the red lantern card count for player.
	 * @return the playerRedLanternCardCount: int type.
	 */
	public int getPlayerRedLanternCardCount() {
		return playerRedLanternCardCount;
	}

	/**
	 * This sethod gets the red lantern card count for player.
	 * @param playerRedLanternCardCount: int type
	 *            the playerRedLanternCardCount to set
	 */
	public void setPlayerRedLanternCardCount(int playerRedLanternCardCount) {
		this.playerRedLanternCardCount = playerRedLanternCardCount;
	}

	/**
	 * This method gets the blue lantern card count for player.
	 * @return the playerBlueLanternCardCount: int type.
	 */
	public int getPlayerBlueLanternCardCount() {
		return playerBlueLanternCardCount;
	}

	/**
	 * This method sets the blue lantern card count for player.
	 * @param playerBlueLanternCardCount: int type
	 *            the playerBlueLanternCardCount to set
	 */
	public void setPlayerBlueLanternCardCount(int playerBlueLanternCardCount) {
		this.playerBlueLanternCardCount = playerBlueLanternCardCount;
	}

	/**
	 * This method gets the orange lantern card count for player.
	 * @return the playerOrangeLanternCardCount: int type
	 */
	public int getPlayerOrangeLanternCardCount() {
		return playerOrangeLanternCardCount;
	}

	/**
	 * This method sets the orange lantern card count for player.
	 * @param playerOrangeLanternCardCount: int type
	 *            the playerOrangeLanternCardCount to set
	 */
	public void setPlayerOrangeLanternCardCount(int playerOrangeLanternCardCount) {
		this.playerOrangeLanternCardCount = playerOrangeLanternCardCount;
	}

	/**
	 * This method gets the black lantern card count for player.
	 * @return the playerBlackLanternCardCount: int type
	 */
	public int getPlayerBlackLanternCardCount() {
		return playerBlackLanternCardCount;
	}

	/**
	 * This method sets the black lantern card count for player.
	 * @param playerBlackLanternCardCount: int type
	 *            the playerBlackLanternCardCount to set
	 */
	public void setPlayerBlackLanternCardCount(int playerBlackLanternCardCount) {
		this.playerBlackLanternCardCount = playerBlackLanternCardCount;
	}

	/**
	 * This method gets the purple lantern card count for player.
	 * @return the playerPurpleLanternCardCount: int type
	 */
	public int getPlayerPurpleLanternCardCount() {
		return playerPurpleLanternCardCount;
	}

	/**
	 * This method sets the purple lantern card count for player.
	 * @param playerPurpleLanternCardCount: int type
	 *            the playerPurpleLanternCardCount to set
	 */
	public void setPlayerPurpleLanternCardCount(int playerPurpleLanternCardCount) {
		this.playerPurpleLanternCardCount = playerPurpleLanternCardCount;
	}

	/**
	 * This method gets the white lantern card count for player.
	 * @return the playerWhiteLanternCardCount: int type
	 */
	public int getPlayerWhiteLanternCardCount() {
		return playerWhiteLanternCardCount;
	}

	/**
	 * This method sets the white lantern card count for player.
	 * @param playerWhiteLanternCardCount: int type
	 *            the playerWhiteLanternCardCount to set
	 */
	public void setPlayerWhiteLanternCardCount(int playerWhiteLanternCardCount) {
		this.playerWhiteLanternCardCount = playerWhiteLanternCardCount;
	}

	/**
	 * This method gets the green lantern card count for player.
	 * @return the playerGreenLanternCardCount: int type
	 */
	public int getPlayerGreenLanternCardCount() {
		return playerGreenLanternCardCount;
	}

	/**
	 * This method sets the green lantern card count for player.
	 * @param playerGreenLanternCardCount: int type
	 *            the playerGreenLanternCardCount to set
	 */
	public void setPlayerGreenLanternCardCount(int playerGreenLanternCardCount) {
		this.playerGreenLanternCardCount = playerGreenLanternCardCount;
	}

	/**
	 * This method gets the dedication tokens type four.
	 * @return the dedicationTokenFour: Vector<Tokens> type
	 */
	public Vector<Tokens> getDedicationTokenFour() {
		return dedicationTokenFour;
	}

	/**
	 * This method sets the dedication tokens type four.
	 * @param dedicationTokenFour: Vector<Tokens> type
	 *            the dedicationTokenFour to set
	 */
	public void setDedicationTokenFour(Vector<Tokens> dedicationTokenFour) {
		this.dedicationTokenFour = dedicationTokenFour;
	}

	/**
	 * This method gets the dedication tokens type six.
	 * @return the dedicationTokenSix: Vector<Tokens> type
	 */
	public Vector<Tokens> getDedicationTokenSix() {
		return dedicationTokenSix;
	}

	/**
	 * This method sets the dedication tokens type six.
	 * @param dedicationTokenSix: Vector<Tokens> type
	 *            the dedicationTokenSix to set
	 */
	public void setDedicationTokenSix(Vector<Tokens> dedicationTokenSix) {
		this.dedicationTokenSix = dedicationTokenSix;
	}

	/**
	 * This method gets the dedication tokens type seven.
	 * @return the dedicationTokenSeven: Vector<Tokens> type
	 */
	public Vector<Tokens> getDedicationTokenSeven() {
		return dedicationTokenSeven;
	}

	/**
	 * This method sets the dedication tokens type seven.
	 * @param dedicationTokenSeven: Vector<Tokens> type
	 *            the dedicationTokenSeven to set
	 */
	public void setDedicationTokenSeven(Vector<Tokens> dedicationTokenSeven) {
		this.dedicationTokenSeven = dedicationTokenSeven;
	}

	/**
	 * This method gets the dedication tokens type generic.
	 * @return the genericDedicationToken: Vector<Tokens> type
	 */
	public Vector<Tokens> getGenericDedicationToken() {
		return genericDedicationToken;
	}

	/**
	 * This method sets the dedication tokens type generic.
	 * @param genericDedicationToken: Vector<Tokens> type
	 *            the genericDedicationToken to set
	 */
	public void setGenericDedicationToken(Vector<Tokens> genericDedicationToken) {
		this.genericDedicationToken = genericDedicationToken;
	}

	/**
	 * This method gets the favor tokens.
	 * @return the playerFavorToken: int type
	 */
	public int getPlayerFavorToken() {
		return playerFavorToken;
	}

	/**
	 * This method sets the favor tokens.
	 * @param playerFavorToken: int type
	 *            the playerFavorToken to set
	 */
	public void setPlayerFavorToken(int playerFavorToken) {
		this.playerFavorToken = playerFavorToken;
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
	 * @param dedicationTokens: DedicationTokens type
	 *            the dedicationTokens to set
	 */
	public void setDedicationTokens(DedicationTokens dedicationTokens) {
		this.dedicationTokens = dedicationTokens;
	}

	/**
	 * This method gets the current lake tiles held by the player.
	 * @return the currentLakeTilesHold: Vector<LakeTiles> type
	 */
	public Vector<LakeTiles> getCurrentLakeTilesHold() {
		return currentLakeTilesHold;
	}
	
	/**
	 * This method gets the lantern card count for all the colors..
	 * @return playerBlackLanternCardCount +
	 * playerBlueLanternCardCount +
	 * playerGreenLanternCardCount +
	 * playerOrangeLanternCardCount +
	 * playerPurpleLanternCardCount +
	 * playerRedLanternCardCount +
	 * playerWhiteLanternCardCount :int type
	 */
	public int getLanternCardCount() {
		this.lanterCardCount = this.playerBlackLanternCardCount
				+ this.playerBlueLanternCardCount
				+ this.playerGreenLanternCardCount
				+ this.playerOrangeLanternCardCount
				+ this.playerPurpleLanternCardCount
				+ this.playerRedLanternCardCount
				+ this.playerWhiteLanternCardCount;

		return lanterCardCount;
	}

	/**
	 * @param lanterCardCount
	 *            The total count of lantern cards to set.
	 */
	public void setLanternCardCount(int lanterCardCount) {
		this.lanterCardCount = lanterCardCount;
	}

	/**
	 * This method sets the current lake tiles held by the player.
	 * @param currentLakeTilesHold: Vector<LakeTiles> type
	 *            the currentLakeTilesHold to set
	 */
	public void setCurrentLakeTilesHold(Vector<LakeTiles> currentLakeTilesHold) {
		this.currentLakeTilesHold = currentLakeTilesHold;
	}

	/**
	 * This method gets the number of player.
	 * @return the playerNumber: int type
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * This method sets the number of player.
	 * @param playerNumber: int type
	 *            the playerNumber to set
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	/**
	 * This method gets the face color.
	 * @return the faceColor: String type
	 */
	public String getFaceColor() {
		return faceColor;
	}

	/**
	 * This method sets the face color.
	 * @param faceColor: String type
	 *            the faceColor to set
	 */
	public void setFaceColor(String faceColor) {
		this.faceColor = faceColor;
	}

	/**
	 * Not used yet.
	 * This method gets the total honor points.
	 * @return the totalHonorPoints: int type
	 */
	public int getTotalHonorPoints() {
		return totalHonorPoints;
	}

	/**
	 * Not used yet.
	 * This method sets the total honor points.
	 * @param totalHonorPoints: int type
	 *            the totalHonorPoints to set
	 */
	public void setTotalHonorPoints(int totalHonorPoints) {
		this.totalHonorPoints = totalHonorPoints;
	}
	
	/**
	 * This method calculates and returns the total points for the player.
	 * Players add up the points they earned from their dedications.
	 * Calculates points for all dedication types (four, six, seven and generic)
	 * @return the totalPoints: int type
	 */
	public int getTotalPoints() {
		//int totalPoints = 0;

		Vector<Integer> dediFourToken = getDedicationTokens()
				.getDedicationTokenFour();
		Vector<Integer> dediSixToken = getDedicationTokens()
				.getDedicationTokenSix();
		Vector<Integer> dediSevenToken = getDedicationTokens()
				.getDedicationTokenSeven();
		Vector<Integer> genericToken = getDedicationTokens()
				.getGenericDedicationTokens();

		if (!dediFourToken.isEmpty()) {
			for (Iterator<Integer> iterator = dediFourToken.iterator(); 
					iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();

				totalPoints += integer;
			}
		}

		if (!dediSixToken.isEmpty()) {
			for (Iterator<Integer> iterator = dediFourToken.iterator(); 
					iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();

				totalPoints += integer;
			}
		}

		if (!dediSevenToken.isEmpty()) {
			for (Iterator<Integer> iterator = dediFourToken.iterator(); 
					iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();

				totalPoints += integer;
			}
		}

		if (!dediFourToken.isEmpty()) {
			for (Iterator<Integer> iterator = genericToken.iterator(); iterator
					.hasNext();) {
				Integer integer = (Integer) iterator.next();

				totalPoints += integer;
			}
		}

		return totalPoints;
	}
	
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	/**
	 * This method gets player position.
	 * @return the playerPosition: String type.
	 */
	public String getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * This method sets player position.
	 * @param playerPosition the playerPosition to set: String type.
	 */
	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

}