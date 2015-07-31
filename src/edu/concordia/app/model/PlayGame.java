/**
 * 
 */
package edu.concordia.app.model;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;

/**
 * @author Team E
 * 
 */
public class PlayGame {

	/**
	 * The instance variable of type GameInstance.
	 */
	private GameInstance gameObj;

	/**
	 * The instance variable of type GameController.
	 */
	private GameController gameController;

	/**
	 * The instance variable of type Scanner.
	 */
	private Scanner scan;

	/**
	 * The PlayGame constructor with two parameters.
	 * 
	 * @param gameObj
	 *            The GameInstance object of the game.
	 * @param gameController
	 *            The GameController object for controlling game view.
	 */
	public PlayGame(GameInstance gameObj, GameController gameController) {
		this.gameObj = gameObj;
		this.gameController = gameController;
	}

	/**
	 * The main method to play game.
	 * 
	 * @param scan
	 *            The Scanner object for getting input through console.
	 */
	public void gameStart(Scanner scan) {

		this.scan = scan;

		Players playing;
		boolean playFlag = true;
		int playOption = 99;

		int turnCount = 0; // track if one turn is complete or not

		boolean exchangeRun = false; // exchange option didn't run yet
		boolean dedicationRun = false; // dedication option didn't run yet

		while (playFlag) {

			if (turnCount == gameObj.getPlayersList().length) {

				// ask to save and exit the game.
				saveAndExitGame(gameController);

				turnCount = 0;

			}

			// check if draw stack contain tiles or not
			// int stackSize = checkDrawStackSize(gameObj);

			// break loop if no lake tile in draw stack
			/*
			 * if(stackSize == 0){ break; }
			 */

			// if stack is empty, method returns true.
			// break the loop.
			if (checkDrawStackSize(gameObj)) {
				break;
			}

			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();

			System.out.println("--------- Player " + playing.getPlayerNumber()
					+ " turn ---------");
			System.out.println("--------------------------");
			System.out.println("Please enter you choice:");
			System.out.println("--------------------------");

			String opt = genValidation(gameObj, playing);

			if (exchangeRun) {
				// remove exchange substring from opt string
				opt = removeSubstring(new String("exchange"), opt);
			}
			if (dedicationRun) {
				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);
			}

			if (opt.contains("exchange")) {
				System.out.println("0 - Exchange a Lantern Card");

			}
			if (opt.contains("type1") || opt.contains("type2")
					|| opt.contains("type3")) {
				System.out.println("1 - Make a dedication");
			}

			System.out.println("2 - Place a lake tile");
			System.out.println("--------------------------");

			System.out.println(); // for spacing

			System.out.print("Player " + playing.getPlayerNumber()
					+ " choice: ");

			int optPlay = scan.nextInt();

			System.out.println(); // for spacing

			switch (optPlay) {

			case 0:
				if (!opt.contains("exchange")) {
					break;
				} else {

					exchageLaternCard(gameObj, playing);
					exchangeRun = true; // to mark exchange has been done.

				}
				break;

			case 1:
				if (!(opt.contains("type1") || opt.contains("type2") || opt
						.contains("type3"))) {
					break;
				} else {

					makeADedication(gameObj, playing, opt);
					dedicationRun = true; // to mark dedication has been done.

				}

				break;

			case 2:
				if (playing.getLanternCardCount() > 12) {

					// If lantern cards are more than 12
					// filter them i.e. discard or make a dedication.
					opt = filterExcessLanternCards(gameObj, playing, opt);

				} else {

					// lake tiles that are already placed on the board.
					displayLakeTileBoard(gameObj, playing);
					// showCurrentlyPlacedTilesDetails();

					// select lake tile from tiles in hand
					LakeTiles tileInHand = revealLakeTile(playing);

					// ask user to rotate card to what degree (0/90/180/270)
					// and return the rotated lake tile
					tileInHand = rotateLakeTileOnUserChoice(tileInHand);

					System.out.println(); // for spacing

					// place lake tile on board.
					boolean placeLoop = true;

					int adjacentTileId = 99;

					int adjLocX = 99;

					int adjLocY = 99;

					while (placeLoop) {
						System.out.println("What tile adjacent to put it to: ");

						adjacentTileId = scan.nextInt();

						for (int i = 0; i < 72; i++) {
							for (int j = 0; j < 72; j++) {
								if (gameObj.GameBoard[i][j] == adjacentTileId) {
									placeLoop = false;
									adjLocX = j;
									adjLocY = i;
								}
							}
						}
						if (placeLoop) {
							System.out
									.println("Please enter the correct tile id!");
						}

					}

					System.out.println(); // for spacing

					String placementOpt = "";

					System.out.print("Place tile at ");
					boolean right = false, top = false, left = false, bottom = false;
					if (gameObj.GameBoard[adjLocY][adjLocX - 1] == 99) {
						left = true;
						System.out.print("left/ ");
						placementOpt += "left/";
					}
					if (gameObj.GameBoard[adjLocY][adjLocX + 1] == 99) {
						right = true;
						System.out.print("right/ ");
						placementOpt += "right/";
					}
					if (gameObj.GameBoard[adjLocY - 1][adjLocX] == 99) {
						System.out.print("top/ ");
						placementOpt += "top/";
						top = true;
					}
					if (gameObj.GameBoard[adjLocY + 1][adjLocX] == 99) {
						System.out.print("bottom ");
						placementOpt += "bottom";
						bottom = true;
					}

					// if no location is available
					if (placementOpt.isEmpty()) {

					}

					int x = 0, y = 0;
					boolean locationLoop = true;

					String location = null;

					while (locationLoop) {
						location = scan.next();

						if (placementOpt.contains(location)) {
							locationLoop = false;
						} else {
							System.out
									.println("Please enter the correct available location.");
							locationLoop = true;
						}
					}

					if (location.equals("right") && right == true) {
						gameObj.GameBoard[adjLocY][adjLocX + 1] = tileInHand
								.getTilesId();

						y = adjLocY;
						x = adjLocX + 1;
					}
					if (location.equals("left") && left == true) {
						gameObj.GameBoard[adjLocY][adjLocX - 1] = tileInHand
								.getTilesId();

						y = adjLocY;
						x = adjLocX - 1;
					}
					if (location.equals("top") && top == true) {
						gameObj.GameBoard[adjLocY - 1][adjLocX] = tileInHand
								.getTilesId();

						y = adjLocY - 1;
						x = adjLocX;
					}
					if (location.equals("bottom") && bottom == true) {
						y = adjLocY + 1;
						x = adjLocX;

						gameObj.GameBoard[adjLocY + 1][adjLocX] = tileInHand
								.getTilesId();
					}

					/*
					 * System.out.println("Lake Tile : " +
					 * tileInHand.getTilesId() + " TC: " +
					 * tileInHand.getTopColor() + " RC: " +
					 * tileInHand.getRightColor() + " BC: " +
					 * tileInHand.getBottomColor() + " LC: " +
					 * tileInHand.getLeftColor() + " Platform: " +
					 * tileInHand.isPlatform());
					 */

					// distribute lantern cards and favor tokens to
					// currently playing player
					distributeLakeTilesPlaying(gameObj, playing, y, x);

					// distribute lantern cards to
					// all players except currently playing player
					distributingLakeTilesToRestPlayers(gameObj, playing, y, x);

					// add lake tile to current lake tile arrangement vector
					gameObj.getCurrentLakeTilesArrangement().addElement(
							tileInHand);

					// remove laketile in hand and already placed
					playing = removePlacedLakeTile(tileInHand, playing);

					System.out.println(""); // for space

					// take one card from draw stack to have three cards in hand
					// and remove top element from draw stack.
					boolean containTiles = replenishLakeTilesInHand(gameObj,
							playing);

					if (containTiles) {
						playFlag = true;

					} else {
						playFlag = false;
						break;
					}

					displayPlayerStatus(playing);

					System.out.println("Lake Tile placed on the game board.");

					// lake tiles that are already placed on the board.
					displayLakeTileBoard(gameObj, playing);

					// update current player of game to next player
					playing = updateCurrentPlayer(gameObj, playing);

					gameObj.setPlayerCurrentTurn(playing);

					// exchange option will run after player change.
					exchangeRun = false;
					dedicationRun = false;

					turnCount += 1;

					System.out.println(); // for space

				}

				break;

			default:
				break;
			}

		}

		// last turn of game when all lake tile cards were used.
		Players[] gamePlayers = gameLastTurnWithoutLakeTiles(gameObj);

		// Display players score
		displayPlayersScore(gamePlayers);

		// validate who are the winners
		Vector<Players> winnerPlayers = validateWinner(gamePlayers);

		// Display winner
		displayWinner(winnerPlayers);

	}

	/**
	 * This method asks takes input from user and rotate the selected tile to
	 * 0/90/180/270 degree.
	 * 
	 * @param tileInHand
	 *            The tile to be rotated before placing on the board.
	 * @return The rotated lake tile.
	 */
	private LakeTiles rotateLakeTileOnUserChoice(LakeTiles tileInHand) {
		boolean choiceRotate = true;

		// loop until correct input is given
		while (choiceRotate) {

			// Rotate lake tile
			System.out.print("Do you want to rotate tile by"
					+ " 90/180/270 clockwise or 0 degree: ");

			// int degreeRotation = scan.nextInt();

			String degreeRotation = scan.next();

			scan.nextLine();

			if (degreeRotation.equals("90")) {

				tileInHand = rotateLakeTile(tileInHand);

				System.out.println();

				System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());

				choiceRotate = false;

			} else if (degreeRotation.equals("180")) {

				tileInHand = rotateLakeTile(tileInHand);

				tileInHand = rotateLakeTile(tileInHand);

				System.out.println();

				System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());
				choiceRotate = false;

			} else if (degreeRotation.equals("270")) {

				tileInHand = rotateLakeTile(tileInHand);

				tileInHand = rotateLakeTile(tileInHand);

				tileInHand = rotateLakeTile(tileInHand);

				System.out.println();

				System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());

				choiceRotate = false;

			} else if (degreeRotation.equals("0")) {
				// do nothing
				choiceRotate = false;
			} else {
				System.out.println("Please enter the correct"
						+ " rotation choice!");
				choiceRotate = true;
			}
		}
		return tileInHand;
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
	private String filterExcessLanternCards(GameInstance gameObj,
			Players playing, String opt) {

		while (playing.getLanternCardCount() > 12) {
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
				makeADedication(gameObj, playing, opt);

				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);

				break;

			case 2:

				// discard the lantern cards.
				discardLanternCards(gameObj, playing);
				break;

			default:
				break;
			}
		}
		return opt;
	}

	/**
	 * The method to save and exit the game according to the user choice.
	 * 
	 * @param gameController
	 *            The GameController object to store the game state to an XML
	 *            file.
	 */
	private void saveAndExitGame(GameController gameController) {

		// Ask user to exit the game
		System.out.println("Do you want to exit the game?(yes/no)");

		System.out.println("Please enter your choice: ");

		String exitChoice = scan.next();

		if (exitChoice.equals("yes")) {

			// Ask user to save file
			System.out.println("Do you want to save the game?(yes/no)");

			System.out.println("Please enter your choice: ");

			String userChoice = scan.next();

			if (userChoice.equals("yes")) {

				System.out.println("Please enter the file name?");

				String fileName = scan.next();

				gameController.saveGameToFile(fileName);

				System.exit(0);

			} else if (userChoice.equals("no")) {
				System.exit(0);
			}
		} else if (exitChoice.equals("no")) {

		}
	}

	/**
	 * The method to take last turn after all lake tiles has been placed on the
	 * game board. The players can exchange lantern cards and do the dedication,
	 * if they have enough lantern cards and dedication tokens.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game elements according
	 *            to user choice.
	 * @return The array of players with updated values according to their last
	 *         turn.
	 */
	private Players[] gameLastTurnWithoutLakeTiles(GameInstance gameObj) {

		// last turn after all lake tiles were exposed
		// int playerCont = gameObj.getPlayersList().length;
		Players[] gamePlayers = gameObj.getPlayersList();

		System.out.println("Last turn of each player "
				+ "after all LakeTiles are used.");

		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];

			System.out.println("--------- Player "
					+ gamePlayer.getPlayerNumber() + " turn ---------");

			String opt1 = genValidation(gameObj, gamePlayer);

			// when player don't have enough lantern cards or favor tokens
			// for exchange and make a dedication.
			if (opt1.isEmpty()) {
				if (!opt1.contains("exchange")) {
					System.out.println("You don't have enough"
							+ " card for exchange.");
				}
				if (!(opt1.contains("type1") || opt1.contains("type2") || opt1
						.contains("type3"))) {
					System.out.println("You don't have enough "
							+ "cards to make a dedication.");
				}
			} else {

				// for loop until exchange and dedication is done until
				// completed.
				int optionTurnCheck = 0;
				int lastExchange = 0;
				int lastDedication = 0;

				boolean optionLoop = true;

				while (optionLoop) {

					System.out.println("--------------------------");

					System.out.println("Please enter you choice:");

					System.out.println("--------------------------");

					if (opt1.contains("exchange")) {
						System.out.println("0 - Exchange a Lantern Card");

						optionTurnCheck += 1;

					}

					if (opt1.contains("type1") || opt1.contains("type2")
							|| opt1.contains("type3")) {
						System.out.println("1 - Make a dedication");

						optionTurnCheck += 1;
					}

					System.out.println();
					int optTurnChoice = scan.nextInt();

					switch (optTurnChoice) {
					case 0:
						exchageLaternCard(gameObj, gamePlayer);
						System.out.println(opt1);
						opt1 = removeSubstring(new String("exchange"), opt1);
						System.out.println(opt1);

						lastExchange = 1; // exchange option used.

						break;

					case 1:
						makeADedication(gameObj, gamePlayer, opt1);
						System.out.println(opt1);
						opt1 = removeSubstring(new String("type1"), opt1);
						opt1 = removeSubstring(new String("type2"), opt1);
						opt1 = removeSubstring(new String("type3"), opt1);
						System.out.println(opt1);

						lastDedication = 1; // dedication option used.

						break;

					default:
						break;
					}

					// if all options are used
					// exit loop
					if (optionTurnCheck == 2) {
						if (lastExchange == 1 && lastDedication == 1) {
							optionLoop = false;
						}
					}
					if (optionTurnCheck == 1) {
						if (lastExchange == 1 || lastDedication == 1) {
							optionLoop = false;
						}
					}
					optionTurnCheck = 0;

				}

			}
		}
		return gamePlayers;
	}

	/**
	 * The method to remove the substring, i.e. game option, from given string
	 * 
	 * @param string
	 *            The main string that contain the value to be removed.
	 * @param opt1
	 *            The substring to be removed from the string.
	 * @return The updated string with given substring removed from it.
	 */
	private String removeSubstring(String string, String opt1) {

		int removeSize = string.length();

		int strtPoint = opt1.indexOf(string);
		// int endPoint = opt1.lastIndexOf(string);

		String firstSubString = "";
		String lastSubString = "";

		if (strtPoint == 0) {
			firstSubString = "";
			lastSubString = opt1.substring(((strtPoint) + removeSize),
					opt1.length());
		} else {
			firstSubString = opt1.substring(0, strtPoint - 1);
			lastSubString = opt1.substring(((strtPoint) + removeSize),
					opt1.length());
		}

		// opt1 = new String(firstSubString+lastSubString);
		return new String(firstSubString + lastSubString);
	}

	/**
	 * The method to determine the winners of the game and to store the winner
	 * players in a vector. If the score of two players is same, then their
	 * corresponding lantern cards count will be checked to determine the
	 * winner.But if the lantern cards are also same, then favor tokens will be
	 * counted. If favor tokens are also same then both players will be winner
	 * 
	 * @param gamePlayers
	 *            The array of players of the game.
	 */
	private static Vector<Players> validateWinner(Players[] gamePlayers) {
		int winnerScore = 0;

		// int winnerLanternCount = 0;
		// int winnerFavorTokenCount = 0;

		// Players[] winnerPlayer = new Players[gamePlayers.length];

		Vector<Players> winner = new Vector<Players>();

		for (int i = 0; i < gamePlayers.length; i++) {
			Players checkPlayer = gamePlayers[i];

			if (checkPlayer.getTotalPoints() > winnerScore) {
				winnerScore = checkPlayer.getTotalPoints();
				winner.removeAllElements();
				winner.add(checkPlayer);
			} else if (checkPlayer.getTotalPoints() < winnerScore) {
				// do nothing
			}
			// if two players has same score.
			else if (checkPlayer.getTotalPoints() == winnerScore) {

				// if no players in winner array.
				if (winner.isEmpty()) {
					winner.addElement(checkPlayer);
				}

				else if (winner.size() == 1) {
					// lantern card count is same
					// then check no. of favor tokens
					if (winner.firstElement().getLanternCardCount() == checkPlayer
							.getLanternCardCount()) {

						// if no of favor tokens are same
						// then add check player to winner array
						if (winner.firstElement().getPlayerFavorToken() == checkPlayer
								.getPlayerFavorToken()) {

							winner.addElement(checkPlayer);
						}

						// if winner player has less favor tokens
						// then remove winner player and add
						// check player to winner array.
						if (winner.firstElement().getPlayerFavorToken() < checkPlayer
								.getPlayerFavorToken()) {

							winner.remove(0);
							winner.addElement(checkPlayer);
						}

						// if winner player has more favor tokens
						// then do nothing.
						if (winner.firstElement().getPlayerFavorToken() > checkPlayer
								.getPlayerFavorToken()) {
						}

					} else if (winner.firstElement().getLanternCardCount() < checkPlayer
							.getLanternCardCount()) {

						winner.remove(0);
						winner.addElement(checkPlayer);
					} else if (winner.firstElement().getLanternCardCount() > checkPlayer
							.getLanternCardCount()) {
					}
				}

				else if (winner.size() > 1) {

					// lantern card count is same
					// then check no. of favor tokens
					if (winner.firstElement().getLanternCardCount() == checkPlayer
							.getLanternCardCount()) {

						// if no of favor tokens are same
						// then add check player to winner array
						if (winner.firstElement().getPlayerFavorToken() == checkPlayer
								.getPlayerFavorToken()) {
							winner.addElement(checkPlayer);
						}

						// if winner player has less favor tokens
						// then remove winner player and add
						// check player to winner array.
						if (winner.firstElement().getPlayerFavorToken() < checkPlayer
								.getPlayerFavorToken()) {

							// remove all elements since all
							// elements have same favor tokens.
							winner.removeAllElements();
							winner.addElement(checkPlayer);
						}

						// if winner player has more favor tokens
						// then do nothing.
						if (winner.firstElement().getPlayerFavorToken() > checkPlayer
								.getPlayerFavorToken()) {
						}

					} else if (winner.firstElement().getLanternCardCount() < checkPlayer
							.getLanternCardCount()) {

						// remove all elements since all
						// elements have same lantern cards count.
						winner.removeAllElements();

						winner.addElement(checkPlayer);

					} else if (winner.firstElement().getLanternCardCount() > checkPlayer
							.getLanternCardCount()) {
					}
				}

			}

		}

		return winner;

	}

	/**
	 * The method to display the winners of the game.
	 * 
	 * @param winnerPlayers
	 *            The array containing the winner players of the game.
	 */
	private static void displayWinner(Vector<Players> winnerPlayers) {

		System.out.println();

		System.out.println("************* Game Winner *************");

		for (Iterator<Players> iterator = winnerPlayers.iterator(); iterator
				.hasNext();) {

			Players player = (Players) iterator.next();

			System.out.println("------------Player " + player.getPlayerNumber()
					+ " --------------");

			System.out.println();

			System.out.println("------------- Details --------------");

			System.out.println(" Score: " + player.getTotalPoints());

			System.out.println(" Lantern Cards: "
					+ player.getLanternCardCount());

			System.out
					.println(" Favor Tokens: " + player.getPlayerFavorToken());

			System.out.println("------------- ------ --------------");

			System.out.println();
		}

	}

	/**
	 * The method to display the score of each player of the game.
	 * 
	 * @param gamePlayers
	 *            The array of players of the game.
	 * 
	 */
	private static void displayPlayersScore(Players[] gamePlayers) {

		System.out.println("-------------- Players Score-------------");

		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];

			System.out.println("Player " + gamePlayer.getPlayerNumber()
					+ " Score: " + gamePlayer.getTotalPoints());
		}

		System.out.println("-------------- ---------- -------------");

	}

	/**
	 * The method to check the size of the draw stack of Lake Tiles.
	 * 
	 * @param gameObj
	 *            The GameInstance object for getting the draw stack of lake
	 *            tiles.
	 * @return The boolean value to determine id lake tile draw stack is empty
	 *         or not. <BR>
	 *         True : If the size is zero. False: If the draw stack is not
	 *         empty.
	 */
	private static boolean checkDrawStackSize(GameInstance gameObj) {
		if (gameObj.getGameTilesDrawPile().isEmpty()) {
			return true;
			// return 0;
		} else {
			return false;
			// return gameObj.getGameTilesDrawPile().size();
		}

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
	 *            The current player whose lantern cards will be discarded.
	 */
	private void discardLanternCards(GameInstance gameObj, Players playing) {

		System.out.println("-------Lantern Cards Player Holds-----");

		System.out.println();

		System.out.println("Red Lantern Cards Left: "
				+ playing.getPlayerRedLanternCardCount());

		System.out.println("Blue Lantern Cards Left: "
				+ playing.getPlayerBlueLanternCardCount());

		System.out.println("Black Lantern Cards Left: "
				+ playing.getPlayerBlackLanternCardCount());

		System.out.println("Green Lantern Cards Left: "
				+ playing.getPlayerGreenLanternCardCount());

		System.out.println("Orange Lantern Cards Left: "
				+ playing.getPlayerOrangeLanternCardCount());

		System.out.println("Purple Lantern Cards Left: "
				+ playing.getPlayerPurpleLanternCardCount());

		System.out.println("White Lantern Cards Left: "
				+ playing.getPlayerWhiteLanternCardCount());

		System.out.println();

		boolean loopCheck = true;

		while (loopCheck) {

			System.out.println("Lantern cards count: "
					+ playing.getLanternCardCount());

			System.out.println("Which color you want to discard?");

			System.out.println("Black/Blue/White/Green/Orange/Red/Purple");

			System.out.println();

			System.out.print("Please enter your choice:");

			String deleteColor = scan.next();

			System.out.print("Please enter the number of"
					+ " cards you want to discard:");

			int deleteCardCount = scan.nextInt();

			boolean removeResult = getLanterCardColorRemoval(playing,
					deleteColor, gameObj, deleteCardCount);

			if (removeResult) {
				if (playing.getLanternCardCount() > 12) {
					loopCheck = true;
				} else {
					loopCheck = false;
				}
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
	private static boolean getLanterCardColorRemoval(Players playing,
			String deleteColor, GameInstance gameObj, int cardCount) {

		if (deleteColor.equals("Black")) {
			if (playing.getPlayerBlackLanternCardCount() >= cardCount) {
				playing.setPlayerBlackLanternCardCount(playing
						.getPlayerBlackLanternCardCount() - cardCount);
				gameObj.setGameBlackLanternCardCount(gameObj
						.getGameBlackLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("White")) {
			if (playing.getPlayerWhiteLanternCardCount() >= cardCount) {
				playing.setPlayerWhiteLanternCardCount(playing
						.getPlayerWhiteLanternCardCount() - cardCount);
				gameObj.setGameWhiteLanternCardCount(gameObj
						.getGameWhiteLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Blue")) {
			if (playing.getPlayerBlueLanternCardCount() >= cardCount) {
				playing.setPlayerBlueLanternCardCount(playing
						.getPlayerBlueLanternCardCount() - cardCount);
				gameObj.setGameBlueLanternCardCount(gameObj
						.getGameBlueLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Green")) {
			if (playing.getPlayerGreenLanternCardCount() >= cardCount) {
				playing.setPlayerGreenLanternCardCount(playing
						.getPlayerGreenLanternCardCount() - cardCount);
				gameObj.setGameGreenLanternCardCount(gameObj
						.getGameGreenLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Orange")) {
			if (playing.getPlayerOrangeLanternCardCount() >= cardCount) {
				playing.setPlayerOrangeLanternCardCount(playing
						.getPlayerOrangeLanternCardCount() - cardCount);
				gameObj.setGameOrangeLanternCardCount(gameObj
						.getGameOrangeLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Red")) {
			if (playing.getPlayerRedLanternCardCount() >= cardCount) {
				playing.setPlayerRedLanternCardCount(playing
						.getPlayerRedLanternCardCount() - cardCount);
				gameObj.setGameRedLanternCardCount(gameObj
						.getGameRedLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Purple")) {
			if (playing.getPlayerPurpleLanternCardCount() >= cardCount) {
				playing.setPlayerPurpleLanternCardCount(playing
						.getPlayerPurpleLanternCardCount() - cardCount);
				gameObj.setGamePurpleLanternCardCount(gameObj
						.getGamePurpleLanternCardCount() + cardCount);
				return true;
			}
		}
		return false;
	}

}
