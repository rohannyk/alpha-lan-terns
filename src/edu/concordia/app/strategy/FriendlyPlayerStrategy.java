/**
 * 
 */
package edu.concordia.app.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;
import edu.concordia.app.view.LanternGameView;

/**
 * This class creates an instance for the Friendly player. Friendly player will
 * make a move in such a way which will help other players to get maximum number
 * of Lantern cards. This player will balance out the moves of the game when
 * players like Greedy and Unfriendly are playing.
 * 
 * @author Team E
 *
 */
public class FriendlyPlayerStrategy extends PlayerStrategy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3305084357466452652L;
	private GameInstance gameObj;
	
	public String name;

	/**
	 * Constructor
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 */
	public FriendlyPlayerStrategy(GameInstance gameObj) {
		this.gameObj = gameObj;
		super.name = "Friendly";
	}

	/**
	 * Method to implement cases for current player's last turn
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param gamePlayer
	 *            The current player of the game.
	 * @param opt1
	 *            The string to save exchange or type1/type2/type3 for
	 *            dedication
	 */
	public Players playerLastTurnChoice(GameInstance gameObj, Players gamePlayer, String opt1) {

		System.out.println(); // for space

		// when player don't have enough lantern cards or favor tokens
		// for exchange and make a dedication.
		if (opt1.isEmpty()) {
			if (!opt1.contains("exchange")) {
				System.out.println("You don't have enough" + " card for exchange.");
			}
			if (!(opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3"))) {
				System.out.println("You don't have enough " + "cards to make a dedication.");
			}
		} else {

			// for loop until exchange and dedication is done until completed.
			int lastExchange = 0;
			int lastDedication = 0;

			boolean optionLoop = true;

			System.out.println("--------------------------");

			System.out.println("Please enter you choice:");

			System.out.println("--------------------------");

			while (optionLoop) {

				if (lastExchange == 1 && lastDedication == 1) {
					optionLoop = false;
					break;
				}

				if (lastExchange == 0) {
					if (opt1.contains("exchange")) {

						System.out.println(); // for space

						System.out.println("0 - Exchange a Lantern Card");

					}
					if (!opt1.contains("exchange")) {
						lastExchange = 1;
					}
				}

				if (lastDedication == 0) {
					if (opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3")) {

						System.out.println(); // for space

						System.out.println("1 - Make a dedication");

					}
					if (!(opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3"))) {
						lastDedication = 1;
					}
				}

				int optTurnChoice = 0;

				if (lastExchange == 0 || lastDedication == 0) {

					System.out.println(); // for space

					// if don't want to use any option.
					System.out.println("2 - No Choice.");

				}

				optTurnChoice = getRandomNumber(2);

				if (lastExchange == 0 && lastDedication == 1) {

					optTurnChoice = 0;

				}

				if (lastExchange == 1 && lastDedication == 0) {

					optTurnChoice = 1;

				}

				switch (optTurnChoice) {
				case 0:

					if (gamePlayer.getPlayerFavorToken() > 1) {

						System.out.println(); // for space

						System.out.println("Player " + gamePlayer.getPlayerNumber() + " will do an exchange.");

						// possible lantern card colors player can give
						Vector<String> possibleExchangColorsToGive = availableDiscardCardColors(gamePlayer);

						Vector<String> possibleExchangeColorToTake = emptyLanternCardColors(gamePlayer);

						// empty color index in vector
						int emptyColorNumber = getRandomNumber(possibleExchangeColorToTake.size());

						// empty lantern card color
						String getEmptyColor = possibleExchangeColorToTake.get(emptyColorNumber);

						System.out.println("Player " + gamePlayer.getPlayerNumber() + " exchanged " + getEmptyColor);

						// get the empty color
						String returnedColor = doExchange(gamePlayer, gameObj, getEmptyColor);

						System.out.println(
								"Player " + gamePlayer.getPlayerNumber() + " get " + returnedColor + " color.");

						opt1 = PlayGame.removeSubstring(new String("exchange"), opt1);

						lastExchange = 1; // exchange option used.

					}

					break;

				case 1:

					// make a dedication

					System.out.println();

					System.out.println("Player " + gamePlayer.getPlayerNumber() + " will do a dedication.");

					System.out.println();

					int fourValue = gameObj.getNextDedicationTokenFour();

					int sixValue = gameObj.getNextDedicationTokenSix();

					int sevenValue = gameObj.getNextDedicationTokenSeven();

					int genericValue = gameObj.getNextGenericDedicationToken();

					ArrayList<String> colorPairs = getLanternCardColorPairs(gamePlayer);

					ArrayList<String> fourUniqueColorPairs = checkPossibleFourUnique(gamePlayer);

					// do exchange and dedication if possible
					if (checkPossibleUniqueColor(gamePlayer)) { 
						doDedicationSeven(gameObj, gamePlayer);

						System.out.println();

						System.out.println("Player " + gamePlayer.getPlayerNumber() + " has choosen Type 3 Dedication"
								+ "(Seven unique Lantern Cards).");

						DedicationTokens dedicationObj = gameObj.getDedicationTokens();
						Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();
						if (dedicationSevenVal.size() > 0) {
							int dedicationSevenValue = dedicationSevenVal.remove(0);
							DedicationTokens playerDedicationObj = gamePlayer.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);
							if (dedicationSevenVal.isEmpty()) {
								gameObj.setNextDedicationTokenSeven(-1);
							} else {
								gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
							}
						} else {
							Vector<Integer> genGenericVal = dedicationObj.getGenericDedicationTokens();
							if (genGenericVal.size() > 0) {
								int genericValue1 = genGenericVal.remove(0);
								DedicationTokens playerDedicationObj = gamePlayer.getDedicationTokens();
								playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
							}
						}

					} else if ((colorPairs.size() > 2)) {
						System.out.println("Player " + gamePlayer.getPlayerNumber() + " has choosen Type 2 Dedication"
								+ "(Three color pairs of Lantern Cards).");

						int pairCount = 0;

						for (int i = 0; i < 3; i++) {

							String color = colorPairs.get(i);

							doDedicationSix(gameObj, color, gamePlayer);

							System.out.println();

							System.out.println(
									"Player " + gamePlayer.getPlayerNumber() + "  dedicated " + color + " color.");

							pairCount += 1;

						}

						if (pairCount == 3) {
							DedicationTokens dedicationObj = gameObj.getDedicationTokens();
							Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
							if (dedicationSixVal.size() > 0) {
								int dedicationSixValue = dedicationSixVal.remove(0);
								DedicationTokens playerDedicationObj = gamePlayer.getDedicationTokens();
								playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
								if (dedicationSixVal.isEmpty()) {
									gameObj.setNextDedicationTokenSix(-1);
								} else {
									gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
								}
							} else {
								Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
								if (genDedicationVal.size() > 0) {
									int genericValue1 = genDedicationVal.remove(0);
									DedicationTokens playerDedicationObj = gamePlayer.getDedicationTokens();
									playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
								}
							}
						}

					} else if (fourUniqueColorPairs.size() > 0) { 

						System.out.println("Player " + gamePlayer.getPlayerNumber() + " has choosen Type 1 Dedication"
								+ "(Four same color pair of Lantern Cards).");

						int randomNumber = getRandomNumber(fourUniqueColorPairs.size());

						String colorFour = fourUniqueColorPairs.get(randomNumber);

						doDedicationFour(gameObj, colorFour, gamePlayer);

						System.out.println();

						System.out.println(
								"Player " + gamePlayer.getPlayerNumber() + "  dedicated " + colorFour + " color.");

					} 
					
					System.out.println();

					System.out.println(
							"Player " + gamePlayer.getPlayerNumber() + " score: " + gamePlayer.getTotalPoints());

					System.out.println();

					opt1 = PlayGame.removeSubstring(new String("type1"), opt1);
					opt1 = PlayGame.removeSubstring(new String("type2"), opt1);
					opt1 = PlayGame.removeSubstring(new String("type3"), opt1);

					lastDedication = 1; // dedication option used.

					break;

				case 2:

					System.out.println();

					System.out.println("No choice used by player " + gamePlayer.getPlayerNumber());

					System.out.println();

					optionLoop = false;

					lastExchange = 1;
					lastDedication = 1;

					break;

				default:
					break;
				}

			}
		}

		return gamePlayer;

	}

	/**
	 * Method to get the the lantern cards which are empty
	 * 
	 * @param gamePlayer
	 *            The current player of the game.
	 * @return Vector containing the lantern cards which are empty
	 */
	private Vector<String> emptyLanternCardColors(Players gamePlayer) {

		Vector<String> exchangeColorChoices = new Vector<String>();

		if (gamePlayer.getPlayerRedLanternCardCount() == 0) {
			exchangeColorChoices.add("Red");
		}
		if (gamePlayer.getPlayerBlueLanternCardCount() == 0) {
			exchangeColorChoices.add("Blue");
		}
		if (gamePlayer.getPlayerBlackLanternCardCount() == 0) {
			exchangeColorChoices.add("Black");
		}
		if (gamePlayer.getPlayerGreenLanternCardCount() == 0) {
			exchangeColorChoices.add("Green");
		}
		if (gamePlayer.getPlayerOrangeLanternCardCount() == 0) {
			exchangeColorChoices.add("Orange");
		}
		if (gamePlayer.getPlayerPurpleLanternCardCount() == 0) {
			exchangeColorChoices.add("Purple");
		}
		if (gamePlayer.getPlayerWhiteLanternCardCount() == 0) {
			exchangeColorChoices.add("White");
		}
		return exchangeColorChoices;

	}

	/**
	 * Method to make a move
	 * 
	 * @param gameObjs
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param option
	 *            The string for SEVEN/SIX/FOUR/GEN for dedication
	 */
	public String makeAMove(GameInstance gameObjs, Players playing, String option) {

		// this.playing = playing;

		int fourValue = gameObjs.getNextDedicationTokenFour();

		int sixValue = gameObjs.getNextDedicationTokenSix();

		int sevenValue = gameObjs.getNextDedicationTokenSeven();

		int genericValue = gameObjs.getNextGenericDedicationToken();

		// all possible exchange combinations
		playerPossibleExchangeMoves(playing);

		// all possible combinations of make a dedication.
		dedicationType1(playing, gameObjs);
		dedicationType2(playing, gameObjs);
		dedicationType3(playing, gameObjs);

		// do exchange and dedication if possible
		if (sevenValue > sixValue && sevenValue > fourValue
				&& playing.getPossibleDedicationSevenUniqueColor().size() > 6) {
			doWhatIsAsked(playing, gameObjs, "SEVEN");
		} else if (sixValue > sevenValue && sixValue > fourValue
				&& playing.getPossibleDedicationThreePairColor().size() > 3) {
			doWhatIsAsked(playing, gameObjs, "SIX");
		} else if (fourValue > sixValue && fourValue > sevenValue
				&& playing.getPossibleDedicationFourUniqueColor().size() > 0) {
			doWhatIsAsked(playing, gameObjs, "FOUR");
		} else if (genericValue > sixValue && genericValue > fourValue && genericValue > sevenValue) {
			doWhatIsAsked(playing, gameObjs, "GEN");
		}

		// while placing a lake tile if lantern count is greater than 12
		if (playing.getLanternCardCount() > 12) {

			System.out.println("Player " + playing.getPlayerNumber() + " has more than 12 Lantern Cards.");

			// abstract method in base class
			// If lantern cards are more than 12
			// filter them i.e. discard or make a dedication.
			// greedy player always do dedication and never discard cards.

			this.filterExcessLanternCards(gameObjs, playing, option);

		} else {

			// remove playing object

			// lake tiles that are already placed on the board.
			new LanternGameView().displayLakeTileBoard(gameObjs);

			int startHorizontal = LanternGameView.getStartHorizontal(gameObjs);
			int endHorizontal = LanternGameView.getEndHorizontal(gameObjs);
			int startVertial = LanternGameView.getStartVertial(gameObjs);
			int endVertial = LanternGameView.getEndVertial(gameObjs);

			// araylist contain edge lake tiles
			ArrayList<String> placedGameBoardLakeTiles = new ArrayList<String>();

			// arraylist contain lake tiles where adjacent lake tiles can be
			// placed.
			ArrayList<String> possibleAdjacentBoardLakeTiles = new ArrayList<String>();

			for (int i = startHorizontal; i < endHorizontal + 1; i++) {
				for (int j = startVertial; j < endVertial + 1; j++) {
					if ((gameObjs.GameBoard[i][j]) != 99
							&& (gameObjs.GameBoard[i][j - 1] == 99 || gameObjs.GameBoard[i][j + 1] == 99
									|| gameObjs.GameBoard[i - 1][j] == 99 || gameObjs.GameBoard[i + 1][j] == 99)) {

						placedGameBoardLakeTiles.add(Integer.toString(gameObjs.GameBoard[i][j]));

						String possibleLakeTilesOpt = Integer.toString(gameObjs.GameBoard[i][j]);

						if (gameObjs.GameBoard[i][j - 1] == 99) {

							possibleLakeTilesOpt += ":L";
						}
						if (gameObjs.GameBoard[i][j + 1] == 99) {

							possibleLakeTilesOpt += ":R";
						}
						if (gameObjs.GameBoard[i - 1][j] == 99) {
							possibleLakeTilesOpt += ":T";
						}
						if (gameObjs.GameBoard[i + 1][j] == 99) {
							possibleLakeTilesOpt += ":B";
						}

						possibleAdjacentBoardLakeTiles.add(possibleLakeTilesOpt);

					}
				}
				System.out.println("");
			}

			// rotation degree array
			String rotationDegree[] = { "0", "90", "180", "270" };

			// tiles in hand
			Vector<LakeTiles> tilesInHandVector = playing.getCurrentLakeTilesHold();

			// vector for possible lake tiles in hand combination with degree
			Vector<String> placedTileCombinations = new Vector<String>();

			for (int i = 0; i < tilesInHandVector.size(); i++) {

				LakeTiles tile = tilesInHandVector.get(i);

				for (int j = 0; j < rotationDegree.length; j++) {
					String degree = rotationDegree[j];

					String combination = Integer.toString(tile.getTilesId()) + ":" + degree;

					placedTileCombinations.add(combination);

				}
			}

			String playerCardFaceColor = "";

			int playerFavorToken = 0;

			int playerRedMatchingCardColors = 0;
			int playerWhiteMatchingCardColors = 0;
			int playerOrangeMatchingCardColors = 0;
			int playerGreenMatchingCardColors = 0;
			int playerPurpleMatchingCardColors = 0;
			int playerBlackMatchingCardColors = 0;
			int playerBlueMatchingCardColors = 0;

			int gameRedMatchingCardColors = gameObjs.getGameRedLanternCardCount();
			int gameWhiteMatchingCardColors = gameObjs.getGameWhiteLanternCardCount();
			int gameOrangeMatchingCardColors = gameObjs.getGameOrangeLanternCardCount();
			int gameGreenMatchingCardColors = gameObjs.getGameGreenLanternCardCount();
			int gamePurpleMatchingCardColors = gameObjs.getGamePurpleLanternCardCount();
			int gameBlackMatchingCardColors = gameObjs.getGameBlackLanternCardCount();
			int gameBlueMatchingCardColors = gameObjs.getGameBlueLanternCardCount();

			Vector<String> tilePlacementCominationResults = new Vector<String>();

			String otherPlayerTilesPlacement = "";

			String playerPosition = playing.getPlayerPosition();

			// complete game lake tiles
			Vector<LakeTiles> totalLakeTilesStack = gameObjs.getAllLakeTiles();

			for (int i = 0; i < placedTileCombinations.size(); i++) {

				// contains lake tile placement result
				String placementCombination = "";

				String degreeCombination[] = placedTileCombinations.get(i).split(":");

				// add tile in hand and degree.
				placementCombination += placedTileCombinations.get(i);

				// get lake tile in hand and rotate by given degree.
				LakeTiles lakeTileChoice = totalLakeTilesStack.get(Integer.valueOf(degreeCombination[0]));

				String degree = degreeCombination[1];

				// rotate lake tile
				lakeTileChoice = PlayGame.rotateLakeTileByDegree(lakeTileChoice, degree);

				playerCardFaceColor = getPlayerFaceColor(playerPosition, lakeTileChoice);

				otherPlayerTilesPlacement = new String(lakeTileChoice.getTilesId() + ":" + degree);

				int north = 0;
				int south = 0;
				int east = 0;
				int west = 0;

				for (int plyrCount = 0; plyrCount < gameObjs.getPlayersList().length; plyrCount++) {

					if (gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("North")
							&& colorCount(lakeTileChoice.getTopColor(), gameObjs) > 0) {
						north += 1;
					} else {
					}
					if (gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("South")
							&& colorCount(lakeTileChoice.getBottomColor(), gameObjs) > 0) {
						south += 1;
					} else {
					}
					if (gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("East")
							&& colorCount(lakeTileChoice.getLeftColor(), gameObjs) > 0) {
						east += 1;
					} else {
					}
					if (gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("West")
							&& colorCount(lakeTileChoice.getRightColor(), gameObjs) > 0) {
						west += 1;
					} else {
					}

				}

				otherPlayerTilesPlacement += ":N:" + north + ":S:" + south + ":E:" + east + ":W:" + west;

				tilePlacementCominationResults.add(otherPlayerTilesPlacement);
			}

			// select possible combination result
			String tileMove = "";
			for (int i = 0; i < tilePlacementCominationResults.size(); i++) {
				String combinationResult = tilePlacementCominationResults.get(i);

				String result[] = combinationResult.split(":");

				if (Integer.parseInt(result[3]) + Integer.parseInt(result[5]) + Integer.parseInt(result[7])
						+ Integer.parseInt(result[9]) > 3) {
					tileMove = combinationResult;
					break;
				} else if (Integer.parseInt(result[3]) + Integer.parseInt(result[5]) + Integer.parseInt(result[7])
						+ Integer.parseInt(result[9]) > 2) {
					tileMove = combinationResult;
					break;
				}

			}

			// select random adjacent placement tile
			int adjTileChoice = getRandomNumber(possibleAdjacentBoardLakeTiles.size());

			String adjTilePosition = possibleAdjacentBoardLakeTiles.get(adjTileChoice);

			LakeTiles tileInHand;
			if (tileMove.equals("")) {
				int selectedMove = getRandomNumber(tilePlacementCominationResults.size());
				tileMove = tilePlacementCominationResults.get(selectedMove);

				tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove, adjTilePosition);
			} else {
				tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove, adjTilePosition);
			}

			System.out.println(); // for spacing

			// add lake tile to current lake tile arrangement vector
			gameObjs.getCurrentLakeTilesArrangement().addElement(tileInHand);

			// remove laketile in hand and already placed
			playing.removePlacedLakeTile(tileInHand);

			System.out.println(""); // for space

			// take one card from draw stack to have three cards in hand
			// and remove top element from draw stack.
			Players.replenishLakeTilesInHand(gameObjs, playing);

			// display player status
			new LanternGameView().displayPlayerStatus(playing);

			System.out.println("Lake Tile placed on the game board.");

			// remove playing object

			// lake tiles that are already placed on the board.
			new LanternGameView().displayLakeTileBoard(gameObjs);

			option = new String("lakeTile");

		}

		return option;
	}

	/**
	 * Method to count the number of lantern cards for a specific color
	 * 
	 * @param gameobj
	 *            The GameInstance object
	 * @param color
	 *            The color of the lantern card
	 */
	public static int colorCount(String color, GameInstance gameobj) {
		int count = 0;
		if (color.equalsIgnoreCase("Red") && gameobj.getGameRedLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("Blue") && gameobj.getGameBlueLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("Black") && gameobj.getGameBlackLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("Green") && gameobj.getGameGreenLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("Orange") && gameobj.getGameOrangeLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("Purple") && gameobj.getGamePurpleLanternCardCount() > 0) {
			count++;
		}
		if (color.equalsIgnoreCase("White") && gameobj.getGameWhiteLanternCardCount() > 0) {
			count++;
		}

		return count;
	}

	/**
	 * Method to place a lake tile and distribute the lantern cards
	 * 
	 * @param gameObjs
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param comRes
	 *            The combination result string
	 * @param adjTilePosition
	 *            The adjacent tile position string
	 */
	public static LakeTiles placeALakeTileAndDistribute(GameInstance gameObjs, Players playing, String comRes,
			String adjTilePosition) {
		String[] placingInformation = comRes.split(":");

		// System.out.println(placingInformation[0]);

		System.out.println("******** Player " + playing.getPlayerNumber() + " Lake Tiles details ********");
		System.out.println();

		System.out.println("No of Lake Tiles in hand: " + playing.getCurrentLakeTilesHold().size());

		System.out.println();

		for (int j = 0; j < playing.getCurrentLakeTilesHold().size(); j++) {
			LakeTiles tileHolded1 = playing.getCurrentLakeTilesHold().get(j);

			new LanternGameView().displayLakeTiles(gameObjs, tileHolded1.getTilesId());
		}

		System.out.println(); // for spacing

		System.out.println("Please enter your Lake Tile number: " + placingInformation[0]);

		String[] adjTileInformation = adjTilePosition.split(":");

		System.out.println();

		System.out.println(
				"Do you want to rotate tile by" + " 90/180/270 clockwise or 0 degree: " + placingInformation[1]);
		System.out.println();

		new LanternGameView().displayLakeTiles(gameObjs, Integer.parseInt(placingInformation[0]));

		// System.out.println(adjTilePosition);
		System.out.println();

		System.out.println("What tile adjacent to put it to: " + adjTileInformation[0]);

		int randomPlacePosValue = getRandomNumber(adjTileInformation.length - 1) + 1;
		String randomPlacePos = adjTileInformation[randomPlacePosValue];

		System.out.println();

		System.out.print("Please enter the available location: ");

		if (randomPlacePos.equalsIgnoreCase("L")) {
			System.out.println("Left");
		} else if (randomPlacePos.equalsIgnoreCase("R")) {
			System.out.println("Right");
		} else if (randomPlacePos.equalsIgnoreCase("T")) {
			System.out.println("Top");
		} else if (randomPlacePos.equalsIgnoreCase("B")) {
			System.out.println("Bottom");
		}

		LakeTiles tileInHand = null;

		for (Iterator<LakeTiles> iterator = playing.getCurrentLakeTilesHold().iterator(); iterator.hasNext();) {
			LakeTiles tiles = (LakeTiles) iterator.next();

			if (tiles.getTilesId() == Integer.parseInt(placingInformation[0])) {
				tileInHand = tiles;
				break;
			}
		}

		tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, placingInformation[1]);

		int x = 0;
		int y = 0;
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if (gameObjs.GameBoard[i][j] == Integer.parseInt(adjTileInformation[0])) {
					y = i;
					x = j;
					break;
				}
			}
		}
		if (randomPlacePos.equals("L")) {
			x = x - 1;
		} else if (randomPlacePos.equals("R")) {
			x = x + 1;
		} else if (randomPlacePos.equals("T")) {
			y = y - 1;
		} else if (randomPlacePos.equals("B")) {
			y = y + 1;
		}
		gameObjs.GameBoard[y][x] = tileInHand.getTilesId();

		// distribute lantern cards
		PlayGame.distributeLakeTilesPlaying(gameObjs, playing, y, x);
		PlayGame.distributingLakeTilesToRestPlayers(gameObjs, playing, y, x);

		return tileInHand;

	}

	/**
	 * Method to return the possible dedication types
	 * 
	 * @param gameObjs
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param combinationResult
	 *            The combination result string
	 * @return retStr The possible dedication types string
	 */
	public static String genValidationGreedy(GameInstance gameObj, Players playing, String combinationResult) {
		boolean exahange = false;
		String retStr = "";

		boolean type1 = false;
		if ((playing.getPlayerBlackLanternCardCount() + Integer.parseInt(combinationResult.split(":")[9]) > 3)
				|| (playing.getPlayerWhiteLanternCardCount() + Integer.parseInt(combinationResult.split(":")[13]) > 3)
				|| (playing.getPlayerBlueLanternCardCount() + Integer.parseInt(combinationResult.split(":")[7]) > 3)
				|| (playing.getPlayerGreenLanternCardCount() + Integer.parseInt(combinationResult.split(":")[17]) > 3)
				|| (playing.getPlayerOrangeLanternCardCount() + Integer.parseInt(combinationResult.split(":")[15]) > 3)
				|| (playing.getPlayerPurpleLanternCardCount() + Integer.parseInt(combinationResult.split(":")[11]) > 3)
				|| (playing.getPlayerRedLanternCardCount() > 3 + Integer.parseInt(combinationResult.split(":")[5]))) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenFourSize() > 0) {
				retStr += "type1";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type1";
			}
		}
		boolean type2 = false;
		int countPairs2 = 0;
		if (playing.getPlayerBlackLanternCardCount() + Integer.parseInt(combinationResult.split(":")[9]) > 1) {
			countPairs2++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[13])) {
			countPairs2++;
		}
		if (playing.getPlayerBlueLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[7])) {
			countPairs2++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[17])) {
			countPairs2++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[15])) {
			countPairs2++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[11])) {
			countPairs2++;
		}
		if (playing.getPlayerRedLanternCardCount() > 1 + Integer.parseInt(combinationResult.split(":")[5])) {
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
		if (playing.getPlayerBlackLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[9])) {
			countPairs7++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[13])) {
			countPairs7++;
		}
		if (playing.getPlayerBlueLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[7])) {
			countPairs7++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[17])) {
			countPairs7++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[15])) {
			countPairs7++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[11])) {
			countPairs7++;
		}
		if (playing.getPlayerRedLanternCardCount() > 0 + Integer.parseInt(combinationResult.split(":")[5])) {
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
	 * This method returns the Player Face color
	 * 
	 * @param playerPosition
	 *            position of the player
	 * @param lakeTileChoice
	 *            The lake tile placed
	 * @return playerCardFaceColor Face color the player is supposed to receive
	 */
	private static String getPlayerFaceColor(String playerPosition, LakeTiles lakeTileChoice) {
		String playerCardFaceColor = null;

		if (playerPosition.equalsIgnoreCase("NORTH")) {
			playerCardFaceColor = lakeTileChoice.getTopColor();
		} else if (playerPosition.equalsIgnoreCase("SOUTH")) {
			playerCardFaceColor = lakeTileChoice.getBottomColor();
		} else if (playerPosition.equalsIgnoreCase("EAST")) {
			playerCardFaceColor = lakeTileChoice.getLeftColor();
		} else if (playerPosition.equalsIgnoreCase("WEST")) {
			playerCardFaceColor = lakeTileChoice.getRightColor();
		}

		return playerCardFaceColor;
	}

	/**
	 * Method to reveal the lake tile
	 * 
	 * @param playing
	 *            The current player
	 * @param gameObjs
	 *            The GameInstance object
	 * @return The lake tile in consideration
	 */
	private static LakeTiles revealLakeTile(Players playing, GameInstance gameObjs) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to exchange or dedication as per as requirement
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param type
	 *            The possible dedication types string
	 */
	public static void doWhatIsAsked(Players playing, GameInstance gameObj, String type) {
		if (type.equals("SEVEN")) {
			if (playing.getPlayerBlackLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Black");
				// do dedication after exchange
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerWhiteLanternCardCount() == 0) {
				doExchange(playing, gameObj, "White");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerBlueLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Blue");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerGreenLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Green");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerOrangeLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Orange");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerPurpleLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Purple");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerRedLanternCardCount() == 0) {

				doExchange(playing, gameObj, "Red");
				doDedicationSeven(gameObj, playing);
			} else {
				doDedicationSeven(gameObj, playing);
			}

			System.out.println();

			System.out
					.println("Player " + playing.getPlayerNumber() + " perform type 3 dedication(Seven unique color).");

			DedicationTokens dedicationObj = gameObj.getDedicationTokens();
			Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();
			if (dedicationSevenVal.size() > 0) {
				int dedicationSevenValue = dedicationSevenVal.remove(0);
				DedicationTokens playerDedicationObj = playing.getDedicationTokens();
				playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);
				if (dedicationSevenVal.isEmpty()) {
					gameObj.setNextDedicationTokenSeven(-1);
				} else {
					gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
				}
			} else {
				Vector<Integer> genGenericVal = dedicationObj.getGenericDedicationTokens();
				if (genGenericVal.size() > 0) {
					int genericValue = genGenericVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getGenericDedicationTokens().add(genericValue);
				}
			}

			new LanternGameView().displayPlayerStatus(playing);

		} else if (type.equals("SIX")) {

			ArrayList<String> possibleThreePair = playing.getPossibleDedicationThreePairColor();

			System.out
					.println("Player " + playing.getPlayerNumber() + " perform type 2 dedication(Three color pairs).");

			int pairCount = 0;

			for (int i = 0; i < possibleThreePair.size(); i++) {
				if (pairCount == 3) {
					break;
				}
				String color = possibleThreePair.get(i);

				if (color.equalsIgnoreCase("Black")) {
					if (playing.getPlayerBlackLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Black");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					} else if (playing.getPlayerBlackLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					}
				} else if (color.equalsIgnoreCase("White")) {
					if (playing.getPlayerWhiteLanternCardCount() == 1) {
						doExchange(playing, gameObj, "White");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					} else if (playing.getPlayerWhiteLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					}
				} else if (color.equalsIgnoreCase("Blue")) {
					if (playing.getPlayerBlueLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Blue");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					} else if (playing.getPlayerBlueLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					}
				} else if (color.equalsIgnoreCase("Green")) {
					if (playing.getPlayerGreenLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Green");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					} else if (playing.getPlayerGreenLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					}
				} else if (color.equalsIgnoreCase("Purple")) {
					if (playing.getPlayerPurpleLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Purple");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					} else if (playing.getPlayerPurpleLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;

					}
				} else if (color.equalsIgnoreCase("Red")) {
					if (playing.getPlayerRedLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Red");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;
					} else if (playing.getPlayerRedLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;
					}
				} else if (color.equalsIgnoreCase("Orange")) {
					if (playing.getPlayerOrangeLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Orange");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;
					} else if (playing.getPlayerOrangeLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);

						pairCount = pairCount + 1;
					}
				}
			}

			if (pairCount == 3) {
				DedicationTokens dedicationObj = gameObj.getDedicationTokens();
				Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
				if (dedicationSixVal.size() > 0) {
					int dedicationSixValue = dedicationSixVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
					if (dedicationSixVal.isEmpty()) {
						gameObj.setNextDedicationTokenSix(-1);
					} else {
						gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
					}
				} else {
					Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
					if (genDedicationVal.size() > 0) {
						int genericValue = genDedicationVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getGenericDedicationTokens().add(genericValue);
					}
				}
			}

		} else if (type.equals("FOUR")) {
			ArrayList<String> possibleFourUniqueColor = playing.getPossibleDedicationFourUniqueColor();

			String color = possibleFourUniqueColor.get(0);

			System.out.println("Player " + playing.getPlayerNumber()
					+ " perform type 1 dedication(Four same color Lantern cards).");

			if (color.equalsIgnoreCase("Black")) {
				if (playing.getPlayerBlackLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Black");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerBlackLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("White")) {
				if (playing.getPlayerWhiteLanternCardCount() == 3) {
					doExchange(playing, gameObj, "White");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerWhiteLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("Blue")) {
				if (playing.getPlayerBlueLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Blue");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerBlueLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("Green")) {
				if (playing.getPlayerGreenLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Green");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerGreenLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("Purple")) {
				if (playing.getPlayerPurpleLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Purple");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerPurpleLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("Red")) {
				if (playing.getPlayerRedLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Red");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerRedLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			} else if (color.equalsIgnoreCase("Orange")) {
				if (playing.getPlayerOrangeLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Orange");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				} else if (playing.getPlayerOrangeLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " dedicated " + color);
				}
			}

		} // check this
		else if (type.equals("GEN")) {

			// If anyone of the dedication is possible, do it.
			if (playing.getPossibleDedicationSevenUniqueColor().size() > 6) {
				if (playing.getPlayerBlackLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Black");
					// do dedication after exchange
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerWhiteLanternCardCount() == 0) {
					doExchange(playing, gameObj, "White");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerBlueLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Blue");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerGreenLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Green");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerOrangeLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Orange");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerPurpleLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Purple");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerRedLanternCardCount() == 0) {

					doExchange(playing, gameObj, "Red");
					doDedicationSeven(gameObj, playing);
				} else {
					doDedicationSeven(gameObj, playing);
				}

				DedicationTokens dedicationObj = gameObj.getDedicationTokens();
				Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();
				if (dedicationSevenVal.size() > 0) {
					int dedicationSevenValue = dedicationSevenVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);
					if (dedicationSevenVal.isEmpty()) {
						gameObj.setNextDedicationTokenSeven(-1);
					} else {
						gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
					}
				} else {
					Vector<Integer> genGenericVal = dedicationObj.getGenericDedicationTokens();
					if (genGenericVal.size() > 0) {
						int genericValue = genGenericVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getGenericDedicationTokens().add(genericValue);
					}
				}

			} else if (playing.getPossibleDedicationThreePairColor().size() > 3) {
				ArrayList<String> possibleThreePair = playing.getPossibleDedicationThreePairColor();

				int pairCount = 0;

				// do dedication with first three available colors.
				for (int i = 0; i < 3; i++) {

					if (pairCount == 3) {
						break;
					}

					String color = possibleThreePair.get(i);

					if (color.equalsIgnoreCase("Black")) {
						if (playing.getPlayerBlackLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Black");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerBlackLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("White")) {
						if (playing.getPlayerWhiteLanternCardCount() == 1) {
							doExchange(playing, gameObj, "White");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerWhiteLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("Blue")) {
						if (playing.getPlayerBlueLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Blue");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerBlueLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("Green")) {
						if (playing.getPlayerGreenLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Green");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerGreenLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("Purple")) {
						if (playing.getPlayerPurpleLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Purple");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerPurpleLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("Red")) {
						if (playing.getPlayerRedLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Red");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerRedLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					} else if (color.equalsIgnoreCase("Orange")) {
						if (playing.getPlayerOrangeLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Orange");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						} else if (playing.getPlayerOrangeLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);

							pairCount = pairCount + 1;
						}
					}
				}

				if (pairCount == 3) {
					DedicationTokens dedicationObj = gameObj.getDedicationTokens();
					Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
					if (dedicationSixVal.size() > 0) {
						int dedicationSixValue = dedicationSixVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
						if (dedicationSixVal.isEmpty()) {
							gameObj.setNextDedicationTokenSix(-1);
						} else {
							gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
						}
					} else {
						Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
						if (genDedicationVal.size() > 0) {
							int genericValue = genDedicationVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getGenericDedicationTokens().add(genericValue);
						}
					}
				}

			} else if (playing.getPossibleDedicationFourUniqueColor().size() > 0) {

				ArrayList<String> possibleFourUniqueColor = playing.getPossibleDedicationFourUniqueColor();

				// do dedication with first color available.
				String color = possibleFourUniqueColor.get(0);

				if (color.equalsIgnoreCase("Black")) {
					if (playing.getPlayerBlackLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Black");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerBlackLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("White")) {
					if (playing.getPlayerWhiteLanternCardCount() == 3) {
						doExchange(playing, gameObj, "White");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerWhiteLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Blue")) {
					if (playing.getPlayerBlueLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Blue");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerBlueLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Green")) {
					if (playing.getPlayerGreenLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Green");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerGreenLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Purple")) {
					if (playing.getPlayerPurpleLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Purple");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerPurpleLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Red")) {
					if (playing.getPlayerRedLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Red");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerRedLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Orange")) {
					if (playing.getPlayerOrangeLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Orange");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerOrangeLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				}
			}
		}
	}

	/**
	 * Method to execute dedication for type SEVEN
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 */
	private static void doDedicationSeven(GameInstance gameObj, Players playing) {

		PlayGame.getDedicationType3ColorValidationAndRemoval(playing, gameObj);

	}

	/**
	 * Method to execute dedication for type SIX
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 */
	private static void doDedicationSix(GameInstance gameObj, String giveColor, Players playing) {
		PlayGame.getDedicationType2ColorValidationAndRemoval(playing, giveColor, gameObj);
	}

	/**
	 * Method to execute dedication for type FOUR
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 */
	private static void doDedicationFour(GameInstance gameObj, String giveColor, Players playing) {
		boolean type1Val = PlayGame.getDedicationType1ColorValidationAndRemoval(playing, giveColor, gameObj);

		if (type1Val) {
			DedicationTokens dedicationObj = gameObj.getDedicationTokens();
			Vector<Integer> dedicationVal = dedicationObj.getDedicationTokenFour();
			if (dedicationVal.size() > 0) {
				int dedicationValue = dedicationVal.remove(0);
				DedicationTokens playerDedicationObj = playing.getDedicationTokens();
				playerDedicationObj.getDedicationTokenFour().add(dedicationValue);
				if (dedicationVal.isEmpty()) {
					gameObj.setNextDedicationTokenFour(-1);
				} else {
					gameObj.setNextDedicationTokenFour(dedicationVal.firstElement());
				}
			} else {
				Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
				if (genDedicationVal.size() > 0) {
					int genericValue = genDedicationVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getGenericDedicationTokens().add(genericValue);
				}
			}
		}

	}

	/**
	 * Method to exchange lantern card
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param getColors
	 *            The specified color.
	 */
	public static String doExchange(Players playing, GameInstance gameObj, String getColors) {
		// sendColor.. any color whose count is > 1

		String returnColor = "";

		if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Black")) {
			// get specified color for player from game
			playing.playerColorAugment("Black", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Black");

			returnColor = "Black";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Blue")) {
			// get specified color for player from game
			playing.playerColorAugment("Blue", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Blue");

			returnColor = "Blue";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Red")) {
			// get specified color for player from game
			playing.playerColorAugment("Red", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Red");

			returnColor = "Red";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Green")) {
			// get specified color for player from game
			playing.playerColorAugment("Green", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Green");

			returnColor = "Green";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Orange")) {
			// get specified color for player from game
			playing.playerColorAugment("Orange", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Orange");

			returnColor = "Orange";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("White")) {
			// get specified color for player from game
			playing.playerColorAugment("White", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "White");

			returnColor = "White";

		} else if (playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Purple")) {
			// get specified color for player from game
			playing.playerColorAugment("Purple", getColors);

			// method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Purple");

			returnColor = "Purple";

		}

		// remove two favor tokens from player
		playing.setPlayerFavorToken(playing.getPlayerFavorToken() - 2);

		// add two favor tokens to game.
		gameObj.setGameFavorToken(gameObj.getGameFavorToken() + 2);

		return returnColor;

	}

	/**
	 * Method to find the possible exchange move for the current player
	 * 
	 * @param playing
	 *            The current player
	 */
	private static void playerPossibleExchangeMoves(Players playing) {

		if (playing.getPlayerFavorToken() >= 2) {
			for (int i = 0; i < playing.possiblePlayerColorExchangeMoves.size(); i++) {
				for (int j = 0; j < playing.possibleGameColorExchangeMoves.size(); j++) {
					if (playing.possiblePlayerColorExchangeMoves.get(i)
							.equalsIgnoreCase(playing.possibleGameColorExchangeMoves.get(j))) {
						playing.getPossibleExchangeMoves().add(playing.possiblePlayerColorExchangeMoves.get(i) + ":"
								+ playing.possibleGameColorExchangeMoves.get(j));
					}
				}

			}
		}
	}

	/**
	 * Method to find the possibilities for dedication type 1
	 * 
	 * @param playing
	 *            The current player
	 * @param gameobjs
	 *            The GameInstance object
	 */
	private static void dedicationType1(Players playing, GameInstance gameObjs) {
		for (int i = 0; i < playing.getPossibleExchangeMoves().size(); i++) {
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];
			if ((((playing.getPlayerBlackLanternCardCount() > 2 && gameExCol.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 3))
					|| ((playing.getPlayerWhiteLanternCardCount() > 2 && gameExCol.equals("White"))
							|| (playing.getPlayerWhiteLanternCardCount() > 3))
					|| ((playing.getPlayerBlueLanternCardCount() > 2 && gameExCol.equals("Blue"))
							|| (playing.getPlayerBlueLanternCardCount() > 3))
					|| ((playing.getPlayerGreenLanternCardCount() > 2 && gameExCol.equals("Green"))
							|| (playing.getPlayerGreenLanternCardCount() > 3))
					|| ((playing.getPlayerOrangeLanternCardCount() > 2 && gameExCol.equals("Orange"))
							|| (playing.getPlayerOrangeLanternCardCount() > 3))
					|| ((playing.getPlayerPurpleLanternCardCount() > 2 && gameExCol.equals("Purple"))
							|| (playing.getPlayerPurpleLanternCardCount() > 3))
					|| ((playing.getPlayerRedLanternCardCount() > 2) && gameExCol.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 3))) {
				playing.getPossibleDedicationFourUniqueColor().add(gameExCol);
			}
		}

	}

	/**
	 * Method to find the possibilities for dedication type 2
	 * 
	 * @param playing
	 *            The current player
	 * @param gameobjs
	 *            The GameInstance object
	 */
	static void dedicationType2(Players playing, GameInstance gameObjs) {
		for (int i = 0; i < playing.getPossibleExchangeMoves().size(); i++) {
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];

			if ((playing.getPlayerBlackLanternCardCount() > 0 && gameExCol.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() > 0 && gameExCol.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() > 0 && gameExCol.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount() > 0 && gameExCol.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() > 0 && gameExCol.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() > 0 && gameExCol.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() > 0 && gameExCol.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
		}

	}

	/**
	 * Method to find the possibilities for dedication type 3
	 * 
	 * @param playing
	 *            The current player
	 * @param gameobjs
	 *            The GameInstance object
	 */
	private static void dedicationType3(Players playing, GameInstance gameObjs) {
		for (int i = 0; i < playing.getPossibleExchangeMoves().size(); i++) {
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];

			if ((playing.getPlayerBlackLanternCardCount() == 0 && gameExCol.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() == 0 && gameExCol.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() == 0 && gameExCol.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount() == 0 && gameExCol.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() == 0 && gameExCol.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() == 0 && gameExCol.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() == 0 && gameExCol.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
		}

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
	public String filterExcessLanternCards(GameInstance gameObj, Players playing, String option) {

		while (playing.getLanternCardCount() > 12) {
			System.out.println("---------- Lantern Cards" + " Count Check -----------");

			System.out.println();

			System.out.println(
					"Player" + playing.getPlayerNumber() + " lantern card count: " + playing.getLanternCardCount());

			System.out.println();

			System.out.println("You have following choices to move forward.");

			if (option.contains("type1") || option.contains("type2") || option.contains("type3")) {
				System.out.println("1: Make a dedication.");
			}

			System.out.println();

			System.out.println("2: Discard Lantern Cards.");

			System.out.println();

			System.out.print("Please choose one option:");

			// int checkChoice = scan.nextInt();

			// set random choice
			int checkChoice = getRandomNumber(2);

			checkChoice = checkChoice + 1;

			switch (checkChoice) {
			case 1:

				System.out.println("Player " + playing.getPlayerNumber() + " chooses: Make a dedication option.");

				int fourValue = gameObj.getNextDedicationTokenFour();

				int sixValue = gameObj.getNextDedicationTokenSix();

				int sevenValue = gameObj.getNextDedicationTokenSeven();

				int genericValue = gameObj.getNextGenericDedicationToken();

				ArrayList<String> colorPairs = getLanternCardColorPairs(playing);

				ArrayList<String> fourUniqueColorPairs = checkPossibleFourUnique(playing);

				// do exchange and dedication if possible
				if (checkPossibleUniqueColor(playing) || (sevenValue > sixValue && sevenValue > fourValue)
						|| (genericValue > sixValue && genericValue > fourValue && genericValue > sevenValue)) {
					doDedicationSeven(gameObj, playing);

					// update dedication value
					DedicationTokens dedicationObj = gameObj.getDedicationTokens();
					Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();
					if (dedicationSevenVal.size() > 0) {
						int dedicationSevenValue = dedicationSevenVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);
						if (dedicationSevenVal.isEmpty()) {
							gameObj.setNextDedicationTokenSeven(-1);
						} else {
							gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
						}
					} else {
						Vector<Integer> genGenericVal = dedicationObj.getGenericDedicationTokens();
						if (genGenericVal.size() > 0) {
							int genericValue1 = genGenericVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
						}
					}

					System.out.println("Type 3 Dedication (Seven cards of different color.)");

				} else if ((colorPairs.size() > 2) || (sixValue > sevenValue && sixValue > fourValue)) {

					int pairCount = 0;

					System.out.println("Type 2 Dedication (Three pairs of unique color)");

					for (int i = 0; i < 3; i++) {

						String color = colorPairs.get(i);

						doDedicationSix(gameObj, color, playing);

						System.out.println("Player " + playing.getPlayerNumber() + " chooses " + color + " color.");

						System.out.println();

						pairCount += 1;
					}

					if (pairCount == 3) {
						DedicationTokens dedicationObj = gameObj.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
							if (dedicationSixVal.isEmpty()) {
								gameObj.setNextDedicationTokenSix(-1);
							} else {
								gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue1 = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
							}
						}
					}

				} else if (fourUniqueColorPairs.size() > 0 || (fourValue > sixValue && fourValue > sevenValue)) {

					System.out.println("Player " + playing.getPlayerNumber()
							+ " chooses: Type 1 Dedication (Four cards of unique color.)");
					System.out.println();

					int randomNumber = getRandomNumber(fourUniqueColorPairs.size());

					String colorFour = fourUniqueColorPairs.get(randomNumber);

					doDedicationFour(gameObj, colorFour, playing);

					System.out.println("Player " + playing.getPlayerNumber() + " chooses " + colorFour + " color.");
					System.out.println();

				} else if (genericValue > sixValue && genericValue > fourValue && genericValue > sevenValue) {

					// doWhatIsAsked(playing, gameObjs, "GEN");

					// If anyone of the dedication is possible, do it.
					if (playing.getPossibleDedicationSevenUniqueColor().size() > 6) {
						if (checkPossibleUniqueColor(playing)) {

							doDedicationSeven(gameObj, playing);

							DedicationTokens dedicationObj = gameObj.getDedicationTokens();
							Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();
							if (dedicationSevenVal.size() > 0) {
								int dedicationSevenValue = dedicationSevenVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);
								if (dedicationSevenVal.isEmpty()) {
									gameObj.setNextDedicationTokenSeven(-1);
								} else {
									gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
								}
							} else {
								Vector<Integer> genGenericVal = dedicationObj.getGenericDedicationTokens();
								if (genGenericVal.size() > 0) {
									int genericValue1 = genGenericVal.remove(0);
									DedicationTokens playerDedicationObj = playing.getDedicationTokens();
									playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
								}
							}

							System.out.println("Type 3 Dedication (Seven cards of different color.)");
						}

					} else if (playing.getPossibleDedicationThreePairColor().size() > 3) {

						if ((colorPairs.size() > 2)) {

							int pairCount = 0;

							System.out.println("Type 2 Dedication (Three pairs of unique color)");

							System.out.println();

							for (int i = 0; i < 3; i++) {

								String color = colorPairs.get(i);

								doDedicationSix(gameObj, color, playing);

								System.out.println(
										"Player " + playing.getPlayerNumber() + " chooses " + color + " color.");

								System.out.println();

								pairCount += 1;
							}

							if (pairCount == 3) {
								DedicationTokens dedicationObj = gameObj.getDedicationTokens();
								Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
								if (dedicationSixVal.size() > 0) {
									int dedicationSixValue = dedicationSixVal.remove(0);
									DedicationTokens playerDedicationObj = playing.getDedicationTokens();
									playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
									if (dedicationSixVal.isEmpty()) {
										gameObj.setNextDedicationTokenSix(-1);
									} else {
										gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
									}
								} else {
									Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
									if (genDedicationVal.size() > 0) {
										int genericValue1 = genDedicationVal.remove(0);
										DedicationTokens playerDedicationObj = playing.getDedicationTokens();
										playerDedicationObj.getGenericDedicationTokens().add(genericValue1);
									}
								}
							}
						}

					} else if (playing.getPossibleDedicationFourUniqueColor().size() > 0) {

						if (fourUniqueColorPairs.size() > 0) {

							System.out.println("Player " + playing.getPlayerNumber()
									+ " chooses: Type 1 Dedication (Four cards of unique color.)");
							System.out.println();

							int randomNumber = getRandomNumber(fourUniqueColorPairs.size());

							String colorFour = fourUniqueColorPairs.get(randomNumber);

							doDedicationFour(gameObj, colorFour, playing);

							System.out.println(
									"Player " + playing.getPlayerNumber() + " chooses " + colorFour + " color.");
							System.out.println();
						}

					}
				}

				break;

			case 2:

				System.out.println("Player " + playing.getPlayerNumber() + " chooses: Discard Lantern cards.");

				this.discardLanternCards(gameObj, playing);
				break;

			default:
				break;
			}

		}
		return option;
	}

	/**
	 * The method to discard the specified Lantern cards of the given player of
	 * the game. It will ask for the lantern cards to be discarded and discard
	 * the selected cards
	 * 
	 * @see getLanterCardColorRemoval
	 * @param gameObj
	 *            The GameInstance object for updating the lantern cards of the
	 *            game board.
	 * @param playing
	 */
	public void discardLanternCards(GameInstance gameObj, Players playing) {

		System.out.println("-------Lantern Cards Player Holds-----");

		System.out.println();

		System.out.println("Red Lantern Cards Left: " + playing.getPlayerRedLanternCardCount());
		System.out.println("Blue Lantern Cards Left: " + playing.getPlayerBlueLanternCardCount());
		System.out.println("Black Lantern Cards Left: " + playing.getPlayerBlackLanternCardCount());
		System.out.println("Green Lantern Cards Left: " + playing.getPlayerGreenLanternCardCount());
		System.out.println("Orange Lantern Cards Left: " + playing.getPlayerOrangeLanternCardCount());
		System.out.println("Purple Lantern Cards Left: " + playing.getPlayerPurpleLanternCardCount());
		System.out.println("White Lantern Cards Left: " + playing.getPlayerWhiteLanternCardCount());
		System.out.println();

		// get colors that can be discarded
		Vector<String> discardColorChoices = availableDiscardCardColors(playing);

		boolean loopCheck = true;

		while (loopCheck) {

			System.out.println("Lantern cards count: " + playing.getLanternCardCount());

			System.out.println("Which color you want to discard?");

			System.out.println("Black/Blue/White/Green/Orange/Red/Purple");

			System.out.println();

			int colorId = getRandomNumber(discardColorChoices.size());

			String deleteColor = discardColorChoices.get(colorId);

			System.out.println("Please enter your choice:");

			System.out.println(deleteColor);

			// get card count of lantern card color to be deleted
			int colorCount = getLanternCardColorCount(playing, deleteColor);

			System.out.print("Please enter the number of" + " cards you want to discard: ");

			System.out.println(colorCount);
			// method should return player whose lantern cards have been
			// updated.
			playing = this.getLanterCardColorRemoval(playing, deleteColor, gameObj, colorCount);

			if (playing.getLanternCardCount() > 12) {
				loopCheck = true;
			} else {
				loopCheck = false;
			}
		}

	}

	/**
	 * The method to remove the lantern cards of specified color from the given
	 * player.The number of lantern cards will be discarded according to the
	 * given cardCount.
	 * 
	 * @param playing
	 *            The current player whose lantern cards will be discarded.
	 * @param deleteColor
	 *            The color of the lantern card to be discarded
	 * @param gameObj
	 *            The GameInstance object for updating the lantern cards of the
	 *            game board.
	 * @param cardCount
	 *            The number of cards to be discarded
	 * @return true if lantern cards of selected color are removed from players
	 *         lantern count and add to the game lantern count.
	 */
	public Players getLanterCardColorRemoval(Players playing, String deleteColor, GameInstance gameObj, int cardCount) {

		if (deleteColor.equals("Black")) {
			if (playing.getPlayerBlackLanternCardCount() >= cardCount) {
				playing.setPlayerBlackLanternCardCount(playing.getPlayerBlackLanternCardCount() - cardCount);
				gameObj.setGameBlackLanternCardCount(gameObj.getGameBlackLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("White")) {
			if (playing.getPlayerWhiteLanternCardCount() >= cardCount) {
				playing.setPlayerWhiteLanternCardCount(playing.getPlayerWhiteLanternCardCount() - cardCount);
				gameObj.setGameWhiteLanternCardCount(gameObj.getGameWhiteLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("Blue")) {
			if (playing.getPlayerBlueLanternCardCount() >= cardCount) {
				playing.setPlayerBlueLanternCardCount(playing.getPlayerBlueLanternCardCount() - cardCount);
				gameObj.setGameBlueLanternCardCount(gameObj.getGameBlueLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("Green")) {
			if (playing.getPlayerGreenLanternCardCount() >= cardCount) {
				playing.setPlayerGreenLanternCardCount(playing.getPlayerGreenLanternCardCount() - cardCount);
				gameObj.setGameGreenLanternCardCount(gameObj.getGameGreenLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("Orange")) {
			if (playing.getPlayerOrangeLanternCardCount() >= cardCount) {
				playing.setPlayerOrangeLanternCardCount(playing.getPlayerOrangeLanternCardCount() - cardCount);
				gameObj.setGameOrangeLanternCardCount(gameObj.getGameOrangeLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("Red")) {
			if (playing.getPlayerRedLanternCardCount() >= cardCount) {
				playing.setPlayerRedLanternCardCount(playing.getPlayerRedLanternCardCount() - cardCount);
				gameObj.setGameRedLanternCardCount(gameObj.getGameRedLanternCardCount() + cardCount);
				return playing;
			}
		}

		if (deleteColor.equals("Purple")) {
			if (playing.getPlayerPurpleLanternCardCount() >= cardCount) {
				playing.setPlayerPurpleLanternCardCount(playing.getPlayerPurpleLanternCardCount() - cardCount);
				gameObj.setGamePurpleLanternCardCount(gameObj.getGamePurpleLanternCardCount() + cardCount);
				return playing;
			}
		}
		return playing;
	}

	/**
	 * Method to count the lantern card for the deletion
	 * 
	 * @param playing
	 *            The current player of the game
	 * @param deleteColor
	 *            The color string for deletion
	 */
	private int getLanternCardColorCount(Players playing, String deleteColor) {
		int colorCount = 0;

		if (deleteColor.equalsIgnoreCase("Red")) {
			colorCount = playing.getPlayerRedLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Black")) {
			colorCount = playing.getPlayerBlackLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Blue")) {
			colorCount = playing.getPlayerBlueLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Orange")) {
			colorCount = playing.getPlayerOrangeLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Green")) {
			colorCount = playing.getPlayerGreenLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("White")) {
			colorCount = playing.getPlayerWhiteLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Purple")) {
			colorCount = playing.getPlayerPurpleLanternCardCount();
		}

		return colorCount;
	}

	/**
	 * Method to get the list of available lantern cards to be discarded
	 * 
	 * @param playing
	 *            The current player of the game
	 */
	private Vector<String> availableDiscardCardColors(Players playing) {
		Vector<String> discardColorChoices = new Vector<String>();

		if (playing.getPlayerRedLanternCardCount() > 0) {
			discardColorChoices.add("Red");
		}
		if (playing.getPlayerBlueLanternCardCount() > 0) {
			discardColorChoices.add("Blue");
		}
		if (playing.getPlayerBlackLanternCardCount() > 0) {
			discardColorChoices.add("Black");
		}
		if (playing.getPlayerGreenLanternCardCount() > 0) {
			discardColorChoices.add("Green");
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			discardColorChoices.add("Orange");
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			discardColorChoices.add("Purple");
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			discardColorChoices.add("White");
		}
		return discardColorChoices;
	}

	/**
	 * Method to get the lantern card color pairs
	 * 
	 * @param playing
	 *            The current player
	 * @return colorPairs The array list containing possible lantern card color
	 *         pairs
	 */
	static ArrayList<String> checkPossibleFourUnique(Players playing) {

		ArrayList<String> fourUniqueColorPairs = new ArrayList<String>();

		if (playing.getPlayerBlackLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Black");
		} else if (playing.getPlayerWhiteLanternCardCount() > 3) {
			fourUniqueColorPairs.add("White");
		} else if (playing.getPlayerBlueLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Blue");
		} else if (playing.getPlayerGreenLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Green");
		} else if (playing.getPlayerPurpleLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Purple");
		} else if (playing.getPlayerRedLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Red");
		} else if (playing.getPlayerOrangeLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Orange");
		}

		return fourUniqueColorPairs;
	}

	/**
	 * Method to check possible unique color
	 * @param playing
	 * 			The current player
	 * @return
	 * 			The boolean value
	 */
	private static boolean checkPossibleUniqueColor(Players playing) {
		return playing.getPlayerBlackLanternCardCount() > 0 && playing.getPlayerWhiteLanternCardCount() > 0
				&& playing.getPlayerBlueLanternCardCount() > 0 && playing.getPlayerGreenLanternCardCount() > 0
				&& playing.getPlayerOrangeLanternCardCount() > 0 && playing.getPlayerPurpleLanternCardCount() > 0
				&& playing.getPlayerRedLanternCardCount() > 0;
	}

	/**
	 * Method to get the lantern card color pairs
	 * @param playing
	 * 			The current player
	 * @return colorPairs
	 * 			The array list containing possible lantern card color pairs
	 */
	private static ArrayList<String> getLanternCardColorPairs(Players playing) {

		ArrayList<String> colorPairs = new ArrayList<String>();
		if (playing.getPlayerBlackLanternCardCount() > 1) {

			colorPairs.add("Black");

		}
		if (playing.getPlayerWhiteLanternCardCount() > 1) {

			colorPairs.add("White");

		}

		if (playing.getPlayerBlueLanternCardCount() > 1) {

			colorPairs.add("Blue");

		}
		if (playing.getPlayerGreenLanternCardCount() > 1) {

			colorPairs.add("Green");

		}

		if (playing.getPlayerPurpleLanternCardCount() > 1) {

			colorPairs.add("Purple");

		}
		if (playing.getPlayerRedLanternCardCount() > 1) {

			colorPairs.add("Red");

		}
		if (playing.getPlayerOrangeLanternCardCount() > 1) {

			colorPairs.add("Orange");

		}

		return colorPairs;
	}

	/**
	 * This method gets a random number.
	 * 
	 * @param number:
	 *            int type
	 * @return random: int type
	 */
	private static int getRandomNumber(int number) {

		Random randomNumbers = new Random(0);

		int random = randomNumbers.nextInt(number); // number between 0 to 3

		return random;
	}
}