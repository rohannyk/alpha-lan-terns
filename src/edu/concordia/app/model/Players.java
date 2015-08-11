/**
 * Players Class
 */
package edu.concordia.app.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.components.LanternCards;
import edu.concordia.app.components.LanternCards.Color;
import edu.concordia.app.components.Tokens;
import edu.concordia.app.strategy.PlayerStrategy;
import edu.concordia.app.view.LanternGameView;

@XmlRootElement
/**
 * This class contains information about the lantern game players.
 * 
 * @author Team E
 *
 */
public class Players {
	
	public ArrayList<String> possiblePlayerColorExchangeMoves = new ArrayList<String>();
	
	public ArrayList<String> possibleGameColorExchangeMoves = new ArrayList<String>();
	
	public ArrayList<String> possibleExchangeMoves = new ArrayList<String>();
	
	public ArrayList<String> possibleDedicationFourUniqueColor = new ArrayList<String>();
	
	public ArrayList<String> possibleDedicationThreePairColor = new ArrayList<String>();
	
	public ArrayList<String> possibleDedicationSevenUniqueColor = new ArrayList<String>();
	
	public ArrayList<String> firstLakeTilePossibleScore = new ArrayList<String>();
	
	public ArrayList<String> secondLakeTilePossibleScore = new ArrayList<String>();
	
	public ArrayList<String> thirdLakeTilePossibleScore = new ArrayList<String>();
	
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
	
	private PlayerStrategy strategy;

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
		int totalPoints = 0;

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

		if (!genericToken.isEmpty()) {
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

	/**
	 * The method to make a dedication by the player passed as a parameter. Any one of the three options will be executed depending upon the user input.
	 * @param gameObj The GameInstance object to update its elements according to the dedications.
	 * @param opt The string to choose the type of dedication.
	 * @param scan
	 */
	public void makeADedication(GameInstance gameObj, String opt, Scanner scan) {
		boolean loop = true;
		while (loop) {
			if (opt.contains("type1")) {
				System.out
						.println("1 - Type 1 Dedication (Four cards of unique color.)");
			}
			if (opt.contains("type2")) {
				System.out
						.println("2 - Type 2 Dedication (Three pairs of unique color)");
			}
			if (opt.contains("type3")) {
				System.out
						.println("3 - Type 3 Dedication (Seven cards of different color.)");
			}
			int selectionType = scan.nextInt();
			switch (selectionType) {
			case 1:
				if (opt.contains("type1")) {
					loop = false;
					System.out.println("Your Lantern Cards Details ");
					if (getPlayerBlackLanternCardCount() > 3)
						System.out.println("Number of Black Lantern Card :"
								+ getPlayerBlackLanternCardCount());
					if (getPlayerWhiteLanternCardCount() > 3)
						System.out.println("Number of White Lantern Card :"
								+ getPlayerWhiteLanternCardCount());
					if (getPlayerBlueLanternCardCount() > 3)
						System.out.println("Number of Blue Lantern Card :"
								+ getPlayerBlueLanternCardCount());
					if (getPlayerGreenLanternCardCount() > 3)
						System.out.println("Number of Green Lantern Card :"
								+ getPlayerGreenLanternCardCount());
					if (getPlayerOrangeLanternCardCount() > 3)
						System.out.println("Number of Orange Lantern Card :"
								+ getPlayerOrangeLanternCardCount());
					if (getPlayerPurpleLanternCardCount() > 3)
						System.out.println("Number of Purple Lantern Card :"
								+ getPlayerPurpleLanternCardCount());
					if (getPlayerRedLanternCardCount() > 3)
						System.out.println("Number of Red Lantern Card :"
								+ getPlayerRedLanternCardCount());
					System.out.println("Input color of lantern card ");
					String color1 = scan.next();
					boolean type1Val = PlayGame
							.getDedicationType1ColorValidationAndRemoval(this,
									color1, gameObj);
					if (type1Val) {
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationVal = dedicationObj
								.getDedicationTokenFour();
						if (dedicationVal.size() > 0) {
							int dedicationValue = dedicationVal.remove(0);
							DedicationTokens playerDedicationObj = this.getDedicationTokens();
							playerDedicationObj.getDedicationTokenFour().add(
									dedicationValue);
							if (dedicationVal.isEmpty()) {
								gameObj.setNextDedicationTokenFour(-1);
							} else {
								gameObj.setNextDedicationTokenFour(dedicationVal
										.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = this.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue);
							}
						}
					} else {
						System.out.println("Wrong color choice");
					}
				}
				break;
			case 2:
				if (opt.contains("type2")) {
					loop = false;
					int stepTwoColorCount = 0;
					System.out.println("Your Lantern Cards Details ");
					if (getPlayerBlackLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Black Lantern Card :"
								+ getPlayerBlackLanternCardCount());
					}
					if (getPlayerWhiteLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of White Lantern Card :"
								+ getPlayerWhiteLanternCardCount());
					}
					if (getPlayerBlueLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Blue Lantern Card :"
								+ getPlayerBlueLanternCardCount());
					}
					if (getPlayerGreenLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Green Lantern Card :"
								+ getPlayerGreenLanternCardCount());
					}
					if (getPlayerOrangeLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Orange Lantern Card :"
								+ getPlayerOrangeLanternCardCount());
					}
					if (getPlayerPurpleLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Purple Lantern Card :"
								+ getPlayerPurpleLanternCardCount());
					}
					if (getPlayerRedLanternCardCount() > 1) {
						stepTwoColorCount += 1;
						System.out.println("Number of Red Lantern Card :"
								+ getPlayerRedLanternCardCount());
					}
					if (stepTwoColorCount > 2) {
						boolean colorLoop1 = true;
						while (colorLoop1) {
							System.out
									.println("Input first color of lantern card: ");
							String colorOne = scan.next();
							boolean type2ValOne = PlayGame
									.getDedicationType2ColorValidationAndRemoval(
											this, colorOne, gameObj);
							if (type2ValOne) {
								colorLoop1 = false;
							} else {
								colorLoop1 = true;
							}
						}
						boolean colorLoop2 = true;
						while (colorLoop2) {
							System.out
									.println("Input second color of lantern card: ");
							String colorTwo = scan.next();
							boolean type2ValTwo = PlayGame
									.getDedicationType2ColorValidationAndRemoval(
											this, colorTwo, gameObj);
							if (type2ValTwo) {
								colorLoop2 = false;
							} else {
								colorLoop2 = true;
							}
						}
						boolean colorLoop3 = true;
						while (colorLoop3) {
							System.out
									.println("Input third color of lantern card ");
							String colorThree = scan.next();
							boolean type2ValThree = PlayGame
									.getDedicationType2ColorValidationAndRemoval(
											this, colorThree, gameObj);
							if (type2ValThree) {
								colorLoop3 = false;
							} else {
								colorLoop3 = true;
							}
						}
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj
								.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);
							DedicationTokens playerDedicationObj = this.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(
									dedicationSixValue);
							if (dedicationSixVal.isEmpty()) {
								gameObj.setNextDedicationTokenSix(-1);
							} else {
								gameObj.setNextDedicationTokenSix(dedicationSixVal
										.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = this.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue);
							}
						}
					} else {
						break;
					}
				}
				break;
			case 3:
				if (opt.contains("type3")) {
					loop = false;
					int stepThreeColorCount = 0;
					System.out.println("Your Lantern Cards Details ");
					if (getPlayerBlackLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Black Lantern Card :"
								+ getPlayerBlackLanternCardCount());
					}
					if (getPlayerWhiteLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of White Lantern Card :"
								+ getPlayerWhiteLanternCardCount());
					}
					if (getPlayerBlueLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Blue Lantern Card :"
								+ getPlayerBlueLanternCardCount());
					}
					if (getPlayerGreenLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Green Lantern Card :"
								+ getPlayerGreenLanternCardCount());
					}
					if (getPlayerOrangeLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Orange Lantern Card :"
								+ getPlayerOrangeLanternCardCount());
					}
					if (getPlayerPurpleLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Purple Lantern Card :"
								+ getPlayerPurpleLanternCardCount());
					}
					if (getPlayerRedLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Red Lantern Card :"
								+ getPlayerRedLanternCardCount());
					}
					if (stepThreeColorCount == 7) {
						boolean type3Val = PlayGame
								.getDedicationType3ColorValidationAndRemoval(
										this, gameObj);
						if (type3Val) {
							DedicationTokens dedicationObj = gameObj
									.getDedicationTokens();
							Vector<Integer> dedicationSevenVal = dedicationObj
									.getDedicationTokenSeven();
							if (dedicationSevenVal.size() > 0) {
								int dedicationSevenValue = dedicationSevenVal
										.remove(0);
								DedicationTokens playerDedicationObj = this.getDedicationTokens();
								playerDedicationObj.getDedicationTokenSeven()
										.add(dedicationSevenValue);
								if (dedicationSevenVal.isEmpty()) {
									gameObj.setNextDedicationTokenSeven(-1);
								} else {
									gameObj.setNextDedicationTokenSeven(dedicationSevenVal
											.firstElement());
								}
							} else {
								Vector<Integer> genGenericVal = dedicationObj
										.getGenericDedicationTokens();
								if (genGenericVal.size() > 0) {
									int genericValue = genGenericVal.remove(0);
									DedicationTokens playerDedicationObj = this.getDedicationTokens();
									playerDedicationObj
											.getGenericDedicationTokens().add(
													genericValue);
								}
							}
						}
					} else {
						break;
					}
				}
				break;
			default:
				break;
			}
		}
	}

	//updated method
	/**
	 * The method prompt player to enter lantern card color player wants to exchange and then perform the exchange operation.
	 * @param gameObj The GameInstance object to update its elements according to lantern card exchange.
	 * @param scan
	 * @param playGame
	 */
	public void exchageLaternCard(GameInstance gameObj, Scanner scan) {
		boolean loop = true;
		String yourColor = "";
		String gameColor = "";
		while (loop) {
			System.out.println();
			System.out.println("Number of your Favor "
					+ "tokens present currently = " + getPlayerFavorToken());
			System.out.println();
			System.out.println("Your Lantern Cards Details ");
			System.out.println("Number of Black Lantern Card :"
					+ getPlayerBlackLanternCardCount());
			System.out.println("Number of White Lantern Card :"
					+ getPlayerWhiteLanternCardCount());
			System.out.println("Number of Blue Lantern Card :"
					+ getPlayerBlueLanternCardCount());
			System.out.println("Number of Green Lantern Card :"
					+ getPlayerGreenLanternCardCount());
			System.out.println("Number of Orange Lantern Card :"
					+ getPlayerOrangeLanternCardCount());
			System.out.println("Number of Purple Lantern Card :"
					+ getPlayerPurpleLanternCardCount());
			System.out.println("Number of Red Lantern Card :"
					+ getPlayerRedLanternCardCount());
			System.out.println("Number of favor tokens in game = "
					+ gameObj.getGameFavorToken());
			System.out.println();
			System.out.println("Game Lantern Cards Details ");
			System.out.println();
			System.out.println("Number of Black Lantern Card :"
					+ gameObj.getGameBlackLanternCardCount());
			System.out.println("Number of White Lantern Card :"
					+ gameObj.getGameWhiteLanternCardCount());
			System.out.println("Number of Blue Lantern Card :"
					+ gameObj.getGameBlueLanternCardCount());
			System.out.println("Number of Green Lantern Card :"
					+ gameObj.getGameGreenLanternCardCount());
			System.out.println("Number of Orange Lantern Card :"
					+ gameObj.getGameOrangeLanternCardCount());
			System.out.println("Number of Purple Lantern Card :"
					+ gameObj.getGamePurpleLanternCardCount());
			System.out.println("Number of Red Lantern Card :"
					+ gameObj.getGameRedLanternCardCount());
			System.out.println("Which your color do you want to give: ");
			yourColor = scan.next();
			
			//check if game has one lantern card with player for exchange
//			loop = playGame.lanternColorPlayerValidation(this, yourColor);
			loop = this.lanternColorPlayerValidation(yourColor);
			
			if (loop == true) {
				loop = false;
			} else {
				System.out.println("Please enter your Lantern "
						+ "card's valid color ");
				loop = true;
				continue;
			}
			System.out.println("Which Game color do you want to get: ");
			
			gameColor = scan.next();
			
			//check if game has one lantern card in game for exchange
//			loop = playGame.lanternColorGameValidation(gameObj, gameColor);
			loop = gameObj.lanternColorGameValidation(gameColor);
			
			if (loop == true) {
				loop = false;
			} else {
				System.out.println("Please enter game Lantern"
						+ " card's valid color ");
				loop = true;
				continue;
			}
		}
		if (!loop) {
			
			setPlayerFavorToken(getPlayerFavorToken() - 2);
			
			gameObj.setGameFavorToken(gameObj.getGameFavorToken() + 2);
			
			//method to remove lantern card color from player and add to game
			//playGame.playerColorAugment(yourColor, this, gameColor);
			this.playerColorAugment(yourColor, gameColor);
			
			//method to remove lantern card color from game and add to player
			//playGame.gameColorAugment(gameColor, gameObj, yourColor);
			gameObj.gameColorAugment(gameColor, yourColor);
		}
	}

	/**
	 * The method to display the status of the specified player.
	 */
	public void displayPlayerStatus() {
		System.out.println();
		System.out.println("******** Player number " + getPlayerNumber()
				+ " status ********");
		System.out.println();
		System.out.println("Player position: " + getPlayerPosition());
		System.out.println();
		System.out.println("Total Lantern cards: " + getLanternCardCount());
		System.out.println();
		System.out.println("Red Lantern cards: "
				+ getPlayerRedLanternCardCount());
		System.out.println("Black Lantern cards: "
				+ getPlayerBlackLanternCardCount());
		System.out.println("Blue Lantern cards: "
				+ getPlayerBlueLanternCardCount());
		System.out.println("Green Lantern cards: "
				+ getPlayerGreenLanternCardCount());
		System.out.println("Purple Lantern cards: "
				+ getPlayerPurpleLanternCardCount());
		System.out.println("White Lantern cards: "
				+ getPlayerWhiteLanternCardCount());
		System.out.println("Orange Lantern cards: "
				+ getPlayerOrangeLanternCardCount());
		System.out.println("Total Favor tokens: " + getPlayerFavorToken());
		System.out.println("************************************");
		System.out.println();
	}

	/**
	 * The method to update the lantern cards of given color of player for exchange.
	 * @param yourColor The color of lantern card removed from player.
	 * @param gameColor The color of lantern card added to player.
	 */
	public void playerColorAugment(String yourColor, String gameColor) {
		if (yourColor.equals("Black")) {
			setPlayerBlackLanternCardCount(getPlayerBlackLanternCardCount() - 1);
		}
		if (yourColor.equals("White")) {
			setPlayerWhiteLanternCardCount(getPlayerWhiteLanternCardCount() - 1);
		}
		if (yourColor.equals("Blue")) {
			setPlayerBlueLanternCardCount(getPlayerBlueLanternCardCount() - 1);
		}
		if (yourColor.equals("Green")) {
			setPlayerGreenLanternCardCount(getPlayerGreenLanternCardCount() - 1);
		}
		if (yourColor.equals("Orange")) {
			setPlayerOrangeLanternCardCount(getPlayerOrangeLanternCardCount() - 1);
		}
		if (yourColor.equals("Red")) {
			setPlayerRedLanternCardCount(getPlayerRedLanternCardCount() - 1);
		}
		if (yourColor.equals("Purple")) {
			setPlayerPurpleLanternCardCount(getPlayerPurpleLanternCardCount() - 1);
		}
		if (gameColor.equals("Black")) {
			setPlayerBlackLanternCardCount(getPlayerBlackLanternCardCount() + 1);
		}
		if (gameColor.equals("White")) {
			setPlayerWhiteLanternCardCount(getPlayerWhiteLanternCardCount() + 1);
		}
		if (gameColor.equals("Blue")) {
			setPlayerBlueLanternCardCount(getPlayerBlueLanternCardCount() + 1);
		}
		if (gameColor.equals("Green")) {
			setPlayerGreenLanternCardCount(getPlayerGreenLanternCardCount() + 1);
		}
		if (gameColor.equals("Orange")) {
			setPlayerOrangeLanternCardCount(getPlayerOrangeLanternCardCount() + 1);
		}
		if (gameColor.equals("Red")) {
			setPlayerRedLanternCardCount(getPlayerRedLanternCardCount() + 1);
		}
		if (gameColor.equals("Purple")) {
			setPlayerPurpleLanternCardCount(getPlayerPurpleLanternCardCount() + 1);
		}
	}

	/**
	 * The method to validate if the player has at least one lantern card of specified color to perform the exchange operation.
	 * @param yourColor The color exchanged by the player.
	 * @return  true if the player can perform the exchange operation.
	 */
	public boolean lanternColorPlayerValidation(String yourColor) {
		if (yourColor.equals("Black"))
			if (getPlayerBlackLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("White"))
			if (getPlayerWhiteLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("Blue"))
			if (getPlayerBlueLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("Green"))
			if (getPlayerGreenLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("Orange"))
			if (getPlayerOrangeLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("Red"))
			if (getPlayerRedLanternCardCount() > 0) {
				return true;
			}
		if (yourColor.equals("Purple"))
			if (getPlayerPurpleLanternCardCount() > 0) {
				return true;
			}
		return false;
	}

	/**
	 * The method to discard the specified Lantern cards of the given player of the game. It will ask for the lantern cards to be discarded and discard the selected cards
	 * @see getLanterCardColorRemoval
	 * @param gameObj The GameInstance object for updating the lantern cards of the game board.
	 * @param scan
	 */
	public void discardLanternCards(GameInstance gameObj, Scanner scan) {
		System.out.println("-------Lantern Cards Player Holds-----");
		System.out.println();
		System.out.println("Red Lantern Cards Left: "
				+ getPlayerRedLanternCardCount());
		System.out.println("Blue Lantern Cards Left: "
				+ getPlayerBlueLanternCardCount());
		System.out.println("Black Lantern Cards Left: "
				+ getPlayerBlackLanternCardCount());
		System.out.println("Green Lantern Cards Left: "
				+ getPlayerGreenLanternCardCount());
		System.out.println("Orange Lantern Cards Left: "
				+ getPlayerOrangeLanternCardCount());
		System.out.println("Purple Lantern Cards Left: "
				+ getPlayerPurpleLanternCardCount());
		System.out.println("White Lantern Cards Left: "
				+ getPlayerWhiteLanternCardCount());
		System.out.println();
		boolean loopCheck = true;
		while (loopCheck) {
			System.out.println("Lantern cards count: " + getLanternCardCount());
			System.out.println("Which color you want to discard?");
			System.out.println("Black/Blue/White/Green/Orange/Red/Purple");
			System.out.println();
			System.out.print("Please enter your choice:");
			String deleteColor = scan.next();
			System.out.print("Please enter the number of"
					+ " cards you want to discard:");
			int deleteCardCount = scan.nextInt();
			boolean removeResult = PlayGame.getLanterCardColorRemoval(this,
					deleteColor, gameObj, deleteCardCount);
			if (removeResult) {
				if (getLanternCardCount() > 12) {
					loopCheck = true;
				} else {
					loopCheck = false;
				}
			}
		}
		
	}

	public void playMove(Players playing, GameInstance gameObjs, String option)
	{
		if(playing instanceof GreedyPlayer)
		{
			playing = GreedyPlayer.makeAMove(playing, gameObjs, option);
		}else if (playing instanceof UnfriendlyPlayer)
		{
			playing = UnfriendlyPlayer.makeAMove(playing, gameObjs, option); 
		}else if (playing instanceof RandomPlayer)
		{
			playing = RandomPlayer.makeAMove(playing, gameObjs, option);
		}else if(playing instanceof FriendlyPlayer)
		{
			playing = FriendlyPlayer.makeAMove(playing, gameObjs, option);
		}else if(playing instanceof HumanPlayer)
		{
			playing = HumanPlayer.makeAMove(playing, gameObjs, option);
		}
		
	}

	/**
	 * The method to validate the options(exchange/make a dedication) user can choose during the game.
	 * @param gameObj The GameInstance to verify if exchange or is possible or not.
	 * @return  The string containing the possible options.
	 */
	public String genValidation(GameInstance gameObj) {
		boolean exahange = false;
		String retStr = "";
		if (getPlayerFavorToken() >= 2) {
			if (getPlayerBlackLanternCardCount() > 0
					|| getPlayerWhiteLanternCardCount() > 0
					|| getPlayerBlueLanternCardCount() > 0
					|| getPlayerGreenLanternCardCount() > 0
					|| getPlayerOrangeLanternCardCount() > 0
					|| getPlayerPurpleLanternCardCount() > 0
					|| getPlayerRedLanternCardCount() > 0) {
				if (gameObj.getGameBlackLanternCardCount() > 0
						|| gameObj.getGameWhiteLanternCardCount() > 0
						|| gameObj.getGameBlueLanternCardCount() > 0
						|| gameObj.getGameGreenLanternCardCount() > 0
						|| gameObj.getGameOrangeLanternCardCount() > 0
						|| gameObj.getGamePurpleLanternCardCount() > 0
						|| gameObj.getGameRedLanternCardCount() > 0) {
					retStr += "exchange";
				}
			}
		}
		boolean type1 = false;
		if (getPlayerBlackLanternCardCount() > 3
				|| getPlayerWhiteLanternCardCount() > 3
				|| getPlayerBlueLanternCardCount() > 3
				|| getPlayerGreenLanternCardCount() > 3
				|| getPlayerOrangeLanternCardCount() > 3
				|| getPlayerPurpleLanternCardCount() > 3
				|| getPlayerRedLanternCardCount() > 3) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenFourSize() > 0) {
				retStr += "type1";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type1";
			}
		}
		boolean type2 = false;
		int countPairs2 = 0;
		if (getPlayerBlackLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerWhiteLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerBlueLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerGreenLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerOrangeLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerPurpleLanternCardCount() > 1) {
			countPairs2++;
		}
		if (getPlayerRedLanternCardCount() > 1) {
			countPairs2++;
		}
		if (countPairs2 > 2) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSixSize() > 0) {
				retStr += "type2";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type2";
			}
		}
		boolean type3 = false;
		int countPairs7 = 0;
		if (getPlayerBlackLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerWhiteLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerBlueLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerGreenLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerOrangeLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerPurpleLanternCardCount() > 0) {
			countPairs7++;
		}
		if (getPlayerRedLanternCardCount() > 0) {
			countPairs7++;
		}
		if (countPairs7 > 6) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSevenSize() > 0) {
				retStr += "type3";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type3";
			}
		}
		return retStr;
	}
	
	/**
	 * This method will discard the excess Lantern cards, if the current player
	 * has more than 12 lantern cards. The player has two options, either to
	 * discard the lantern cards of his choice or to perform either of the three
	 * possible dedications.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game elements according
	 *            to user choice.
	 * @param playing
	 *            The current player of the game.
	 * @param opt
	 *            The string contain options that player can choose
	 *            (Dedication/discard).
	 * @return The string contain those choices that are not used by the user.
	 */
	public static String filterExcessLanternCards(GameInstance gameObj,
			Players playing, String opt) 
	{
		
		/*while (playing.getLanternCardCount() > 12) 
		{
			System.out.println("---------- Lantern Cards"
					+ " Count Check -----------");

			System.out.println("Player" + playing.getPlayerNumber()
					+ " has more than 12 lantern cards.");

			System.out.println();

			System.out.println("You have following choices to move forward.");

			if (opt.contains("type1") || opt.contains("type2")
					|| opt.contains("type3")) {
				System.out.println("1: Make a dedication.");
			}

			System.out.println();

			System.out.println("2: Discard Lantern Cards.");

			System.out.println();

			System.out.print("Please choose one option:");

			int checkChoice = scan.nextInt();

			switch (checkChoice) {
			
			case 1:
				// make dedication
				playing.makeADedication(gameObj, opt, scan);

				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);

				break;

			case 2:
				
				//discard the lantern cards.
				playing.discardLanternCards(gameObj, scan);
				break;

			default:
				break;
			}
		}*/
		return opt;
	}

	/**
	 * The method to remove the lake tile from player's hand.
	 * 
	 * @param tileInHand
	 *            The lake tile to be removed.
	 * @return The player of the game with update lake tiles in hand.
	 */
	public Players removePlacedLakeTile(LakeTiles tileInHand) {
		Vector<LakeTiles> handLakeTiles = getCurrentLakeTilesHold();
		
		boolean removed = handLakeTiles.remove(tileInHand);
	//		System.out.println("check removed "+ removed);
	//		
	//		System.out.println(" tiles in hand "+handLakeTiles.size());
	//		System.out.println(" tiles in hand "+playing.getCurrentLakeTilesHold().size());
		return this;
	}

	/**
	 * The method to replenish lake tile from draw stack to the players hand,
	 * after the player has placed the lake tile on the board.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating the lantern cards of the
	 *            game board.
	 * @param playing
	 *            The current player of the game.
	 */
	public static boolean replenishLakeTilesInHand(GameInstance gameObj,
			Players playing) {
		LakeTiles replenishedTile = null;
	
		// get top tile from draw pile and remove it.
		if (gameObj.getGameTilesDrawPile().size() > 0) {
			replenishedTile = gameObj.getGameTilesDrawPile().remove(0);
	
			// LakeTiles replenishedTile =
			// gameObj.getGameTilesDrawPile().firstElement();
	
			// add tile to player hand
			playing.getCurrentLakeTilesHold().add(replenishedTile);
	
			System.out.println("--------Replenished Lake Tile for Player "
					+ playing.getPlayerNumber() + "--------");
			
			//display tile details
			new LanternGameView().displayLakeTiles(gameObj, replenishedTile.getTilesId());
	
			System.out.println("---------------------------------------------");
	
			return true;
		} else {
			return false;
		}
	
	}

	/**
	 * @return the strategy
	 */
	public PlayerStrategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(PlayerStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the possibleDedicationFourUniqueColor
	 */
	public ArrayList<String> getPossibleDedicationFourUniqueColor() {
		return possibleDedicationFourUniqueColor;
	}

	/**
	 * @param possibleDedicationFourUniqueColor the possibleDedicationFourUniqueColor to set
	 */
	public void setPossibleDedicationFourUniqueColor(
			ArrayList<String> possibleDedicationFourUniqueColor) {
		this.possibleDedicationFourUniqueColor = possibleDedicationFourUniqueColor;
	}

	/**
	 * @return the possibleDedicationThreePairColor
	 */
	public ArrayList<String> getPossibleDedicationThreePairColor() {
		return possibleDedicationThreePairColor;
	}

	/**
	 * @param possibleDedicationThreePairColor the possibleDedicationThreePairColor to set
	 */
	public void setPossibleDedicationThreePairColor(
			ArrayList<String> possibleDedicationThreePairColor) {
		this.possibleDedicationThreePairColor = possibleDedicationThreePairColor;
	}

	/**
	 * @return the possibleDedicationSevenUniqueColor
	 */
	public ArrayList<String> getPossibleDedicationSevenUniqueColor() {
		return possibleDedicationSevenUniqueColor;
	}

	/**
	 * @param possibleDedicationSevenUniqueColor the possibleDedicationSevenUniqueColor to set
	 */
	public void setPossibleDedicationSevenUniqueColor(
			ArrayList<String> possibleDedicationSevenUniqueColor) {
		this.possibleDedicationSevenUniqueColor = possibleDedicationSevenUniqueColor;
	}
	
	
	
}