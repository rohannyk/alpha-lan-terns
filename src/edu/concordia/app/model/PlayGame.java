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
	 * @param gameObj The GameInstance object of the game.
	 * @param gameController The GameController object 
	 * for controlling game view.
	 */
	public PlayGame(GameInstance gameObj, GameController gameController) {
		this.gameObj = gameObj;
		this.gameController = gameController;
	}

	/**
	 * The main method to play game.  
	 * @param scan The Scanner object for getting 
	 * input through console.  
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

			if(exchangeRun){
				// remove exchange substring from opt string
				opt = removeSubstring(new String("exchange"), opt);
			}
			if(dedicationRun){
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

					
					//if no location is available
					if(placementOpt.isEmpty()){
						
					}

					int x = 0, y = 0;
					boolean locationLoop = true;
					
					String location = null;
					
					while (locationLoop) {
						location = scan.next();
						
						if(placementOpt.contains(location)){
							locationLoop = false;
						}
						else{
							System.out.println("Please enter the correct available location.");
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
	 * @param tileInHand The tile to be rotated before placing on the board.
	 * @return The rotated lake tile.
	 */
	private LakeTiles rotateLakeTileOnUserChoice(LakeTiles tileInHand) {
		boolean choiceRotate = true;

		//loop until correct input is given
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

	

}
