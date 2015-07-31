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
 * This class governs the game play of the lantern game according to the rules.
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

	/**
	 * This method will discard the excess Lantern cards,
	 * if the current player has more than 12 lantern cards.
	 * The player has two options, either to discard the lantern
	 * cards of his choice or to perform either of the three possible dedications.
	 * @param gameObj The GameInstance object for updating game 
	 * elements according to user choice.
	 * @param playing The current player of the game.
	 * @param opt The string contain options that player can choose (Dedication/discard).
	 * @return The string contain those choices that are not used by the user.
	 */
	private String filterExcessLanternCards(GameInstance gameObj,
			Players playing, String opt) {
		
		while (playing.getLanternCardCount() > 12) 
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
				makeADedication(gameObj, playing, opt);

				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);

				break;

			case 2:
				
				//discard the lantern cards.
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
	 * @param gameController The GameController object to store
	 * the game state to an XML file.
	 */
	private  void saveAndExitGame(GameController gameController) {
		
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
	private  Players[] gameLastTurnWithoutLakeTiles(GameInstance gameObj) {
		
		// last turn after all lake tiles were exposed
		//int playerCont = gameObj.getPlayersList().length;
		Players[] gamePlayers = gameObj.getPlayersList();
		
		System.out.println("Last turn of each player "
				+ "after all LakeTiles are used.");
		
		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];
			
			System.out.println("--------- Player "
					+ gamePlayer.getPlayerNumber() + " turn ---------");
			
			
			String opt1 = genValidation(gameObj, gamePlayer);
			
			//when player don't have enough lantern cards or favor tokens
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
				
				//for loop until exchange and dedication is done until completed.
				int optionTurnCheck = 0;
				int lastExchange = 0;
				int lastDedication = 0;
				
				boolean optionLoop = true;
				
				while(optionLoop){
					
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

						lastExchange = 1; //exchange option used.

						break;

					case 1:
						makeADedication(gameObj, gamePlayer, opt1);
						System.out.println(opt1);
						opt1 = removeSubstring(new String("type1"), opt1);
						opt1 = removeSubstring(new String("type2"), opt1);
						opt1 = removeSubstring(new String("type3"), opt1);
						System.out.println(opt1);

						lastDedication = 1; //dedication option used.

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
	 * @param string The main string that contain the value to be removed.
	 * @param opt1 The substring to be removed from the string.
	 * @return The updated string with given substring removed from it.
	 */
	private  String removeSubstring(String string, String opt1) {
	
		int removeSize = string.length();
		
		int strtPoint = opt1.indexOf(string);
		//int endPoint = opt1.lastIndexOf(string);
		
		String firstSubString = "";
		String lastSubString = "";
		
		if(strtPoint == 0){
			 firstSubString = "";
			 lastSubString = opt1.substring(((strtPoint)+removeSize), opt1.length());
		}else{
			 firstSubString = opt1.substring(0, strtPoint-1);
			 lastSubString = opt1.substring(((strtPoint)+removeSize), opt1.length());
		}
		
		//opt1 = new String(firstSubString+lastSubString);
		return new String(firstSubString+lastSubString);
	}

	
	/**
	 * The method to determine the winners of the game and
	 *  to store the winner players in a vector. If the score of two 
	 *  players is same, then their corresponding lantern cards count
	 *  will be checked to determine the winner.But if the lantern 
	 *  cards are also same, then favor tokens will be counted. If 
	 *  favor tokens are also same then both players will be winner
	 * @param gamePlayers The array of players of the game.
	 */
	private static Vector<Players> validateWinner(Players[] gamePlayers) {
		int winnerScore = 0;
		
		//int winnerLanternCount = 0;
		//int winnerFavorTokenCount = 0;
		
		//Players[] winnerPlayer = new Players[gamePlayers.length];
		
		Vector<Players> winner = new Vector<Players>();
		
		for (int i = 0; i < gamePlayers.length; i++) {
			Players checkPlayer = gamePlayers[i];
			
			if (checkPlayer.getTotalPoints() > winnerScore) {
				winnerScore = checkPlayer.getTotalPoints();
				winner.removeAllElements();
				winner.add(checkPlayer);
			}
			else if (checkPlayer.getTotalPoints() < winnerScore) {
				// do nothing
			}
			// if two players has same score.
			else if(checkPlayer.getTotalPoints() == winnerScore){
				
				//if no players in winner array.
				if(winner.isEmpty())
				{
					winner.addElement(checkPlayer);
				}
				
				else if(winner.size() == 1)
				{
					// lantern card count is same
					//then check no. of favor tokens
					if (winner.firstElement().getLanternCardCount() == checkPlayer
							.getLanternCardCount()) {

						// if no of favor tokens are same
						// then add check player to winner array
						if (winner.firstElement().getPlayerFavorToken() == checkPlayer
								.getPlayerFavorToken()) {
							
							winner.addElement(checkPlayer);
						}
						
						//if winner player has less favor tokens
						//then remove winner player and add 
						//check player to winner array.
						if (winner.firstElement().getPlayerFavorToken() < checkPlayer
								.getPlayerFavorToken()) {
							
							winner.remove(0);
							winner.addElement(checkPlayer);
						}
						
						//if winner player has more favor tokens
						//then do nothing.
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
							.getLanternCardCount()) 
					{

						// remove all elements since all
						// elements have same lantern cards count.
						winner.removeAllElements();
						
						winner.addElement(checkPlayer);
						
					} else if (winner.firstElement().getLanternCardCount() > checkPlayer
							.getLanternCardCount()) 
					{}
				}
				
				
			}
			
		}
				
		return winner;
		
	}
	
	/**
	 * The method to display the winners of the game. 
	 * @param winnerPlayers The array containing the winner players of the game.
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
	 * @param gameObj The GameInstance object for getting the draw stack of lake tiles.
	 * @return The boolean value to determine id lake tile draw stack is empty or not.
	 * <BR>
	 * True : If the size is zero.
	 * False: If the draw stack is not empty.
	 */
	private static boolean checkDrawStackSize(GameInstance gameObj) {
		if(gameObj.getGameTilesDrawPile().isEmpty()){
			return true;
//			return 0;
		}else{
			return false;
			//return gameObj.getGameTilesDrawPile().size();
		}
		
	}

	/**
	 * The method to discard the specified Lantern cards of the given player of the game.
	 * It will ask for the lantern cards to be discarded and discard the selected cards
	 * @see getLanterCardColorRemoval
	 * @param gameObj The GameInstance object for updating 
	 * the lantern cards of the game board.
	 * @param playing The current player whose lantern cards will be discarded.
	 */
	private void discardLanternCards(GameInstance gameObj,
			Players playing) {
		
		System.out.println("-------Lantern Cards Player Holds-----");
		
		System.out.println();
		
		System.out.println("Red Lantern Cards Left: "+playing.getPlayerRedLanternCardCount());
		
		System.out.println("Blue Lantern Cards Left: "+playing.getPlayerBlueLanternCardCount());
		
		System.out.println("Black Lantern Cards Left: "+playing.getPlayerBlackLanternCardCount());
		
		System.out.println("Green Lantern Cards Left: "+playing.getPlayerGreenLanternCardCount());
		
		System.out.println("Orange Lantern Cards Left: "+playing.getPlayerOrangeLanternCardCount());
		
		System.out.println("Purple Lantern Cards Left: "+playing.getPlayerPurpleLanternCardCount());
		
		System.out.println("White Lantern Cards Left: "+playing.getPlayerWhiteLanternCardCount());
		
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
	 * The method to remove the lantern cards of specified color from 
	 * the given player.The number of lantern cards will be discarded
	 * according to the given cardCount.
	 * @param playing The current player whose lantern cards will be discarded.
	 * @param deleteColor The color of the lantern card to be discarded
	 * @param gameObj The GameInstance object for updating 
	 * the lantern cards of the game board.
	 * @param cardCount The number of cards to be discarded
	 * @return true if lantern cards of selected color are removed from
	 * players lantern count and add to the game lantern count.
	 */
	private static boolean getLanterCardColorRemoval(Players playing,
			String deleteColor, GameInstance gameObj, int cardCount) {

		if (deleteColor.equals("Black")) 
		{
			if (playing.getPlayerBlackLanternCardCount() >= cardCount) 
			{
				playing.setPlayerBlackLanternCardCount(playing
						.getPlayerBlackLanternCardCount() - cardCount);
				gameObj.setGameBlackLanternCardCount(gameObj
						.getGameBlackLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("White")) 
		{
			if (playing.getPlayerWhiteLanternCardCount() >= cardCount) 
			{
				playing.setPlayerWhiteLanternCardCount(playing
						.getPlayerWhiteLanternCardCount() - cardCount);
				gameObj.setGameWhiteLanternCardCount(gameObj
						.getGameWhiteLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Blue")) 
		{
			if (playing.getPlayerBlueLanternCardCount() >= cardCount) 
			{
				playing.setPlayerBlueLanternCardCount(playing
						.getPlayerBlueLanternCardCount() - cardCount);
				gameObj.setGameBlueLanternCardCount(gameObj
						.getGameBlueLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Green")) 
		{
			if (playing.getPlayerGreenLanternCardCount() >= cardCount) 
			{
				playing.setPlayerGreenLanternCardCount(playing
						.getPlayerGreenLanternCardCount() - cardCount);
				gameObj.setGameGreenLanternCardCount(gameObj
						.getGameGreenLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Orange")) 
		{
			if (playing.getPlayerOrangeLanternCardCount() >= cardCount) 
			{
				playing.setPlayerOrangeLanternCardCount(playing
						.getPlayerOrangeLanternCardCount() - cardCount);
				gameObj.setGameOrangeLanternCardCount(gameObj
						.getGameOrangeLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Red")) 
		{
			if (playing.getPlayerRedLanternCardCount() >= cardCount) 
			{
				playing.setPlayerRedLanternCardCount(playing
						.getPlayerRedLanternCardCount() - cardCount);
				gameObj.setGameRedLanternCardCount(gameObj
						.getGameRedLanternCardCount() + cardCount);
				return true;
			}
		}

		if (deleteColor.equals("Purple")) 
		{
			if (playing.getPlayerPurpleLanternCardCount() >= cardCount) 
			{
				playing.setPlayerPurpleLanternCardCount(playing
						.getPlayerPurpleLanternCardCount() - cardCount);
				gameObj.setGamePurpleLanternCardCount(gameObj
						.getGamePurpleLanternCardCount() + cardCount);
				return true;
			}
		}
		return false;
	}

	/**
	 * The method to display the status of the specified player.
	 * @param playing The player of the game.
	 */
	private static void displayPlayerStatus(Players playing) {

		System.out.println();

		System.out.println("******** Player number "
				+ playing.getPlayerNumber() + " status ********");
		System.out.println();  //space

		System.out.println("Player position: " + playing.getPlayerPosition());
		
		System.out.println();  //space

		System.out.println("Total Lantern cards: "
				+ playing.getLanternCardCount());
		
		System.out.println();  //space

		System.out.println("Red Lantern cards: "
				+ playing.getPlayerRedLanternCardCount());

		System.out.println("Black Lantern cards: "
				+ playing.getPlayerBlackLanternCardCount());

		System.out.println("Blue Lantern cards: "
				+ playing.getPlayerBlueLanternCardCount());

		System.out.println("Green Lantern cards: "
				+ playing.getPlayerGreenLanternCardCount());

		System.out.println("Purple Lantern cards: "
				+ playing.getPlayerPurpleLanternCardCount());

		System.out.println("White Lantern cards: "
				+ playing.getPlayerWhiteLanternCardCount());

		System.out.println("Orange Lantern cards: "
				+ playing.getPlayerOrangeLanternCardCount());

		System.out.println("Total Favor tokens: "
				+ playing.getPlayerFavorToken());

		System.out.println("************************************");

		System.out.println();
	}

	/**
	 * The method to distribute the lantern cards to all the players
	 * of the game except the current player of the game, whose turn is 
	 * going on. The players will get the lantern card according to the
	 * color of the lake tile card they are facing.
	 * @param gameObj The GameInstance object for updating 
	 * the lantern cards of the game board.
	 * @param playing The current player of the game.
	 * @param y The row of the game board.
	 * @param x The column of the game board.
	 */
	private static void distributingLakeTilesToRestPlayers(GameInstance gameObj, Players playing, int y, int x) {
		
		Players[] players = gameObj.getPlayersList();
		
		Vector<LakeTiles> VLT = gameObj.getAllLakeTiles();
		
		for (int plyrCount = 0; plyrCount < players.length; plyrCount++) {
			
			Players tempPlyr = players[plyrCount];
			
			if (tempPlyr.getPlayerNumber() != playing.getPlayerNumber()) {
				
				if (gameObj.GameBoard[y][x] != 99) 
				{
					if (tempPlyr.getPlayerPosition().equals("NORTH")) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x]).getTopColor(),gameObj);
					}
				
					if (tempPlyr.getPlayerPosition().equals("SOUTH")) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x])
										.getBottomColor(), gameObj);
					}
				
					if (tempPlyr.getPlayerPosition().equals("EAST")) {
						addingLanternCardsToPlayer(
								tempPlyr,
								VLT.get(gameObj.GameBoard[y][x]).getLeftColor(),
								gameObj);
					}
				
					if (tempPlyr.getPlayerPosition().equals("WEST")) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x])
										.getRightColor(), gameObj);
					}
				}
				
				/*if (gameObj.GameBoard[y - 1][x] != 99) {
					if (tempPlyr.getPlayerPosition().equals("NORTH")
							&& VLT.get(gameObj.GameBoard[y - 1][x])
									.getBottomColor()
									.equals(VLT.get(gameObj.GameBoard[y][x]).getTopColor())) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x]).getTopColor(),gameObj);
					}
				}
				if (gameObj.GameBoard[y + 1][x] != 99) {
					if (tempPlyr.getPlayerPosition().equals("SOUTH")
							&& VLT.get(gameObj.GameBoard[y + 1][x])
									.getTopColor()
									.equals(VLT.get(gameObj.GameBoard[y][x])
											.getBottomColor())) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x])
										.getBottomColor(), gameObj);
					}
				}
				if (gameObj.GameBoard[y][x - 1] != 99) {
					if (tempPlyr.getPlayerPosition().equals("EAST")
							&& VLT.get(gameObj.GameBoard[y][x - 1])
									.getRightColor()
									.equals(VLT.get(gameObj.GameBoard[y][x])
											.getLeftColor())) {
						addingLanternCardsToPlayer(
								tempPlyr,
								VLT.get(gameObj.GameBoard[y][x]).getLeftColor(),
								gameObj);
					}
				}
				
				if (gameObj.GameBoard[y][x + 1] != 99) {
					if (tempPlyr.getPlayerPosition().equals("WEST")
							&& VLT.get(gameObj.GameBoard[y][x + 1])
									.getLeftColor()
									.equals(VLT.get(gameObj.GameBoard[y][x])
											.getRightColor())) {
						addingLanternCardsToPlayer(tempPlyr,
								VLT.get(gameObj.GameBoard[y][x])
										.getRightColor(), gameObj);
					}
				}*/
			}
		}
	}

	/**
	 * The method to distribute the lantern card and favor tokens to the current
	 * player of the game and according to the rules of the game.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating the lantern cards of the
	 *            game board.
	 * @param playing
	 *            The current player of the game.
	 */
	private static void distributeLakeTilesPlaying(GameInstance gameObj, Players playing, int y, int x) {
	
		//current player gets everything
		Vector<LakeTiles> VLT = gameObj.getAllLakeTiles();
		
		boolean right = false, top = false, left =false, bottom = false;
		
		String position = playing.getPlayerPosition();
		
		if (gameObj.GameBoard[y - 1][x] != 99) {

			left = true;
			if (VLT.get(gameObj.GameBoard[y - 1][x]).getBottomColor()
					.equals(VLT.get(gameObj.GameBoard[y][x]).getTopColor())) {
				addingLanternCardsToPlayer(playing,
						VLT.get(gameObj.GameBoard[y][x]).getTopColor(), gameObj);
				/*
				 * if(position.equals("NORTH")) {
				 * addingLanternCardsToPlayer(playing
				 * ,VLT.get(gameObj.GameBoard[y][x]).getTopColor(),gameObj); }
				 */
			}
		}
		if (position.equals("NORTH")) {
			addingLanternCardsToPlayer(playing, VLT
					.get(gameObj.GameBoard[y][x]).getTopColor(), gameObj);
		}
		if (gameObj.GameBoard[y + 1][x] != 99) {

			right = true;
			if (VLT.get(gameObj.GameBoard[y + 1][x]).getTopColor()
					.equals(VLT.get(gameObj.GameBoard[y][x]).getBottomColor())) {
				addingLanternCardsToPlayer(playing,
						VLT.get(gameObj.GameBoard[y][x]).getBottomColor(),
						gameObj);
				/*
				 * if(position.equals("SOUTH")) {
				 * addingLanternCardsToPlayer(playing
				 * ,VLT.get(gameObj.GameBoard[y][x]).getBottomColor(),gameObj);
				 * }
				 */
			}
		}
		if (position.equals("SOUTH")) {
			addingLanternCardsToPlayer(playing, VLT
					.get(gameObj.GameBoard[y][x]).getBottomColor(), gameObj);
		}
		if (gameObj.GameBoard[y][x - 1] != 99) {

			System.out.println(gameObj.GameBoard[y][x - 1]);
			
			System.out.println(gameObj.GameBoard[y][x]);
			
			top = true;
			
			if (VLT.get(gameObj.GameBoard[y][x - 1]).getRightColor()
					.equals(VLT.get(gameObj.GameBoard[y][x]).getLeftColor())) {
				addingLanternCardsToPlayer(playing,
						VLT.get(gameObj.GameBoard[y][x]).getLeftColor(),
						gameObj);
				/*
				 * if(position.equals("EAST")) {
				 * addingLanternCardsToPlayer(playing
				 * ,VLT.get(gameObj.GameBoard[y][x]).getLeftColor(),gameObj); }
				 */
			}
		}
		if (position.equals("EAST")) {
			addingLanternCardsToPlayer(playing, VLT
					.get(gameObj.GameBoard[y][x]).getLeftColor(), gameObj);
		}
		if (gameObj.GameBoard[y][x + 1] != 99) {

			bottom = true;
			
			if (VLT.get(gameObj.GameBoard[y][x + 1]).getLeftColor()
					.equals(VLT.get(gameObj.GameBoard[y][x]).getRightColor())) {
				addingLanternCardsToPlayer(playing,
						VLT.get(gameObj.GameBoard[y][x]).getRightColor(),
						gameObj);
				/*
				 * if(position.equals("WEST")) {
				 * addingLanternCardsToPlayer(playing
				 * ,VLT.get(gameObj.GameBoard[y][x]).getRightColor(),gameObj); }
				 */
			}
		}
		if (position.equals("WEST")) {
			addingLanternCardsToPlayer(playing, VLT
					.get(gameObj.GameBoard[y][x]).getRightColor(), gameObj);
		}
		
		//Platform Card To do
		if (VLT.get(gameObj.GameBoard[y][x]).isPlatform()) {
			if (gameObj.getGameFavorToken() > 0) {
				playing.setPlayerFavorToken(playing.getPlayerFavorToken() + 1);
				gameObj.setGameFavorToken(gameObj.getGameFavorToken() - 1);
			}

			if (gameObj.GameBoard[y][x + 1] != 99) {
				if (VLT.get(gameObj.GameBoard[y][x + 1]).isPlatform()) {
					if (gameObj.getGameFavorToken() > 0) {
						playing.setPlayerFavorToken(playing
								.getPlayerFavorToken() + 1);
						gameObj.setGameFavorToken(gameObj.getGameFavorToken() - 1);
					}
				}
			}
			if (gameObj.GameBoard[y][x - 1] != 99) {
				if (VLT.get(gameObj.GameBoard[y][x - 1]).isPlatform()) {
					if (gameObj.getGameFavorToken() > 0) {
						playing.setPlayerFavorToken(playing
								.getPlayerFavorToken() + 1);
						gameObj.setGameFavorToken(gameObj.getGameFavorToken() - 1);
					}
				}
			}
			if (gameObj.GameBoard[y + 1][x] != 99) {
				if (VLT.get(gameObj.GameBoard[y + 1][x]).isPlatform()) {
					if (gameObj.getGameFavorToken() > 0) {
						playing.setPlayerFavorToken(playing
								.getPlayerFavorToken() + 1);
						gameObj.setGameFavorToken(gameObj.getGameFavorToken() - 1);
					}
				}
			}
			if (gameObj.GameBoard[y - 1][x] != 99) {
				if (VLT.get(gameObj.GameBoard[y - 1][x]).isPlatform()) {
					if (gameObj.getGameFavorToken() > 0) {
						playing.setPlayerFavorToken(playing
								.getPlayerFavorToken() + 1);
						gameObj.setGameFavorToken(gameObj.getGameFavorToken() - 1);
					}
				}
			}

		}
		
	}
		
	
	private static boolean addingLanternCardsToPlayer(Players playing,
			String yourColor, GameInstance gameObj) {

		if (yourColor.equals("BLACK"))
			if (gameObj.getGameBlackLanternCardCount() > 0) {
				playing.setPlayerBlackLanternCardCount(playing
						.getPlayerBlackLanternCardCount() + 1);
				gameObj.setGameBlackLanternCardCount(gameObj
						.getGameBlackLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("WHITE"))
			if (gameObj.getGameWhiteLanternCardCount() > 0) {
				playing.setPlayerWhiteLanternCardCount(playing
						.getPlayerWhiteLanternCardCount() + 1);
				gameObj.setGameWhiteLanternCardCount(gameObj
						.getGameWhiteLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("BLUE"))
			if (gameObj.getGameBlueLanternCardCount() > 0) {
				playing.setPlayerBlueLanternCardCount(playing
						.getPlayerBlueLanternCardCount() + 1);
				gameObj.setGameBlueLanternCardCount(gameObj
						.getGameBlueLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("GREEN"))
			if (gameObj.getGameGreenLanternCardCount() > 0) {
				playing.setPlayerGreenLanternCardCount(playing
						.getPlayerGreenLanternCardCount() + 1);
				gameObj.setGameGreenLanternCardCount(gameObj
						.getGameGreenLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("ORANGE"))
			if (gameObj.getGameOrangeLanternCardCount() > 0) {
				playing.setPlayerOrangeLanternCardCount(playing
						.getPlayerOrangeLanternCardCount() + 1);
				gameObj.setGameOrangeLanternCardCount(gameObj
						.getGameOrangeLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("RED"))
			if (gameObj.getGameRedLanternCardCount() > 0) {
				playing.setPlayerRedLanternCardCount(playing
						.getPlayerRedLanternCardCount() + 1);
				gameObj.setGameRedLanternCardCount(gameObj
						.getGameRedLanternCardCount() - 1);
				return true;
			}
		if (yourColor.equals("PURPLE"))
			if (gameObj.getGamePurpleLanternCardCount() > 0) {
				playing.setPlayerPurpleLanternCardCount(playing
						.getPlayerPurpleLanternCardCount() + 1);
				gameObj.setGamePurpleLanternCardCount(gameObj
						.getGamePurpleLanternCardCount() - 1);
				return true;
			}
		return false;
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
	private static boolean replenishLakeTilesInHand(GameInstance gameObj,
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
			System.out.println("Tile iD: " + replenishedTile.getTilesId()
					+ " TC: " + replenishedTile.getTopColor() + " RC: "
					+ replenishedTile.getRightColor() + " BC: "
					+ replenishedTile.getBottomColor() + " LC: "
					+ replenishedTile.getLeftColor() + " Platform: "
					+ replenishedTile.isPlatform());

			System.out.println("---------------------------------------------");

			return true;
		} else {
			return false;
		}

	}

	/**
	 * The method to remove the lake tile from player's hand.
	 * 
	 * @param tileInHand
	 *            The lake tile to be removed.
	 * @param playing
	 *            The player of the game from whose hand the given lake tiles
	 *            will be removed.
	 * @return The player of the game with update lake tiles in hand.
	 */
	private Players removePlacedLakeTile(LakeTiles tileInHand, Players playing) {
		Vector<LakeTiles> handLakeTiles = playing.getCurrentLakeTilesHold();
		
		boolean removed = handLakeTiles.remove(tileInHand);
//		System.out.println("check removed "+ removed);
//		
//		System.out.println(" tiles in hand "+handLakeTiles.size());
//		System.out.println(" tiles in hand "+playing.getCurrentLakeTilesHold().size());
		return playing;
	}

	/**
	 * The method to rotate the lake tile 90 Degree clockwise
	 * @param tileInHand The lake tile which will be rotated
	 */
	private LakeTiles rotateLakeTile(LakeTiles tileInHand) {

		String TC = tileInHand.getTopColor();
		String BC = tileInHand.getBottomColor();
		String RC = tileInHand.getRightColor();
		String LC = tileInHand.getLeftColor();

//		tileInHand.setBottomColor(LC);
//		tileInHand.setLeftColor(TC);
//		tileInHand.setTopColor(RC);
//		tileInHand.setRightColor(BC);
		
		tileInHand.setBottomColor(RC);
		tileInHand.setLeftColor(BC);
		tileInHand.setTopColor(LC);
		tileInHand.setRightColor(TC);

		return tileInHand;
	}

	/**
	 * The method to ask player to select one lake tile from three lake tiles in
	 * hand and return the selected lake tile.
	 * 
	 * @param playing
	 *            The player of the game.
	 * @return The lake tile selected by the player.
	 */
	private LakeTiles revealLakeTile(Players playing) {

		boolean inputLoop = true;
		LakeTiles tileHolded;
		while (inputLoop) {
			System.out.println(); // for spacing
			
			System.out.println("******** Player " + playing.getPlayerNumber()
					+ " Lake Tiles details ********");

			Vector<LakeTiles> lakeTiles = playing.getCurrentLakeTilesHold();
			
			System.out.println(); // for spacing

			System.out.println("No of Lake Tiles in hand: " + lakeTiles.size());

			for (int j = 0; j < lakeTiles.size(); j++) {
				LakeTiles tileHolded1 = lakeTiles.get(j);
				System.out.println("Lake Tiles holded : "
						+ tileHolded1.getTilesId() + " TC: "
						+ tileHolded1.getTopColor() + " RC: "
						+ tileHolded1.getRightColor() + " BC: "
						+ tileHolded1.getBottomColor() + " LC: "
						+ tileHolded1.getLeftColor() + " Platform: "
						+ tileHolded1.isPlatform());
			}

			System.out.println(); // for spacing

			System.out.println("Please enter your Lake Tile number: ");

			int tileChoice = scan.nextInt();

			for (int j = 0; j < lakeTiles.size(); j++) {
				tileHolded = lakeTiles.get(j);
				if (tileHolded.getTilesId() == tileChoice) {
					inputLoop = false;
					return tileHolded;
				}
			}
			if (inputLoop) {
				System.out.println("Please enter the valid lake tile id!");
			}
		}

		return null;
	}

	
	/**
	 * The method to return the top vertical element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return The top vertical array element which is not empty.
	 */
	public static int getStartVertial(GameInstance gameObj) {
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					return i - 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method to return the left horizontal element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @returnThe left horizontal array element which is not empty.
	 */
	public static int getStartHorizontal(GameInstance gameObj) {
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if ((gameObj.GameBoard[j][i]) != 99) {
					return i - 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method to return the bottom vertical element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return bottom vertical array element which is not empty.
	 */
	public static int getEndVertial(GameInstance gameObj) {
		for (int i = 72; i > 0; i--) {
			for (int j = 72; j > 0; j--) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/**
	 *The method to return the right horizontal element of the game board array
	 * which is not empty.
	 * @param gameObj The GameInstance object for updating game board array.
	 * @return right horizontal array element which is not empty.
	 */
	public static int getEndHorizontal(GameInstance gameObj) {

		for (int i = 72; i > 0; i--) {
			for (int j = 72; j > 0; j--) {
				if ((gameObj.GameBoard[j][i]) != 99) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method for left padding.
	 * @param s The string to be padded.
	 * @param n The number of time string is padded.
	 * @return The padded string.
	 */
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	/**
	 * The method for right padding.
	 * @param s The string to be padded.
	 * @param n The number of time string is padded.
	 * @return The padded string.
	 */
	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
	
	/**
	 * The method to display the game board and the lake tiles placed on the game board.
	 * @param gameObj The GameInstance object for updating game board array.
	 * @param playing The current player of the game.
	 */
	private void displayLakeTileBoard(GameInstance gameObj, Players playing) {

		int startHorizontal = getStartHorizontal(gameObj);
		int endHorizontal = getEndHorizontal(gameObj);
		int startVertial = getStartVertial(gameObj);
		int endVertial = getEndVertial(gameObj);
		// //////////////////////////

		System.out.println("----------- Game Board -------------");

		for (int i = startHorizontal; i < endHorizontal + 1; i++) {
			for (int j = startVertial; j < endVertial + 1; j++) {
				if ((gameObj.GameBoard[i][j]) == 99)
					System.out.print(padLeft(" ", 3));
				else
					System.out.print(padLeft(
							Integer.toString(gameObj.GameBoard[i][j]), 3));

			}
			System.out.println("");
		}

		System.out.println("-----------------------------------");
		System.out.println();

		System.out.println("-------Details of Lake Tiles on Board:-------");

		System.out.println(); // for spacing

		// Displaying tiles details of on board tiles.
		for (int i = 0; i < 72 + 1; i++) {
			for (int j = 0; j < 72 + 1; j++) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					int k = gameObj.GameBoard[i][j];
					System.out.println("Tile iD " + gameObj.GameBoard[i][j]
							+ " TC: "
							+ gameObj.getAllLakeTiles().get(k).getTopColor()
							+ " RC: "
							+ gameObj.getAllLakeTiles().get(k).getRightColor()
							+ " BC: "
							+ gameObj.getAllLakeTiles().get(k).getBottomColor()
							+ " LC: "
							+ gameObj.getAllLakeTiles().get(k).getLeftColor()
							+ " Platform: "
							+ gameObj.getAllLakeTiles().get(k).isPlatform());

				}
			}
		}

		System.out.println("----------------------------------");

	}

	/**
	 * The method to make a dedication
	 * @param gameObj The GameInstance object to update its elements
	 *  according to the dedications..
	 * @param playing The current player who is doing the dedication.
	 * @param opt The string to choose the type of dedication.
	 */
	private void makeADedication(GameInstance gameObj, Players playing, String opt) {
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
					
					if (playing.getPlayerBlackLanternCardCount() > 3)
						System.out.println("Number of Black Lantern Card :"
								+ playing.getPlayerBlackLanternCardCount());
					if (playing.getPlayerWhiteLanternCardCount() > 3)
						System.out.println("Number of White Lantern Card :"
								+ playing.getPlayerWhiteLanternCardCount());
					if (playing.getPlayerBlueLanternCardCount() > 3)
						System.out.println("Number of Blue Lantern Card :"
								+ playing.getPlayerBlueLanternCardCount());
					if (playing.getPlayerGreenLanternCardCount() > 3)
						System.out.println("Number of Green Lantern Card :"
								+ playing.getPlayerGreenLanternCardCount());
					if (playing.getPlayerOrangeLanternCardCount() > 3)
						System.out.println("Number of Orange Lantern Card :"
								+ playing.getPlayerOrangeLanternCardCount());
					if (playing.getPlayerPurpleLanternCardCount() > 3)
						System.out.println("Number of Purple Lantern Card :"
								+ playing.getPlayerPurpleLanternCardCount());
					if (playing.getPlayerRedLanternCardCount() > 3)
						System.out.println("Number of Red Lantern Card :"
								+ playing.getPlayerRedLanternCardCount());

					System.out.println("Input color of lantern card ");
					
					String color1 = scan.next();
					
					boolean type1Val = getDedicationType1ColorValidationAndRemoval(
							playing, color1, gameObj);
					
					if (type1Val) {
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationVal = dedicationObj
								.getDedicationTokenFour();
						if (dedicationVal.size() > 0) {
							int dedicationValue = dedicationVal.remove(0);

							DedicationTokens playerDedicationObj = playing
									.getDedicationTokens();
							playerDedicationObj.getDedicationTokenFour().add(
									dedicationValue);
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();

							// check if generic tokens vector is empty or not.
							if (genDedicationVal.size() > 0) {

								int genericValue = genDedicationVal.remove(0);

								DedicationTokens playerDedicationObj = playing
										.getDedicationTokens();
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

					// count to check if there are more than three pairs or not
					int stepTwoColorCount = 0;

					System.out.println("Your Lantern Cards Details ");

					if (playing.getPlayerBlackLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Black Lantern Card :"
								+ playing.getPlayerBlackLanternCardCount());
					}
					if (playing.getPlayerWhiteLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of White Lantern Card :"
								+ playing.getPlayerWhiteLanternCardCount());
					}
					if (playing.getPlayerBlueLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Blue Lantern Card :"
								+ playing.getPlayerBlueLanternCardCount());
					}
					if (playing.getPlayerGreenLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Green Lantern Card :"
								+ playing.getPlayerGreenLanternCardCount());
					}
					if (playing.getPlayerOrangeLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Orange Lantern Card :"
								+ playing.getPlayerOrangeLanternCardCount());
					}
					if (playing.getPlayerPurpleLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Purple Lantern Card :"
								+ playing.getPlayerPurpleLanternCardCount());
					}
					if (playing.getPlayerRedLanternCardCount() > 1) 
					{
						stepTwoColorCount += 1;
						System.out.println("Number of Red Lantern Card :"
								+ playing.getPlayerRedLanternCardCount());
					}

					if (stepTwoColorCount > 2) 
					{

						boolean colorLoop1 = true;
						while (colorLoop1) {
							System.out
									.println("Input first color of lantern card: ");
							String colorOne = scan.next();

							boolean type2ValOne = getDedicationType2ColorValidationAndRemoval(
									playing, colorOne, gameObj);
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

							boolean type2ValTwo = getDedicationType2ColorValidationAndRemoval(
									playing, colorTwo, gameObj);
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

							boolean type2ValThree = getDedicationType2ColorValidationAndRemoval(
									playing, colorThree, gameObj);
							if (type2ValThree) {
								colorLoop3 = false;
							} else {
								colorLoop3 = true;
							}
						}

						// Remove dedication tokens from game and to player
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj
								.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);

							DedicationTokens playerDedicationObj = playing
									.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(
									dedicationSixValue);
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();

							// check if generic tokens vector is empty or not.
							if (genDedicationVal.size() > 0) {
								int genericValue = genDedicationVal.remove(0);

								DedicationTokens playerDedicationObj = playing
										.getDedicationTokens();
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

					// count to check if there are more than three pairs or not
					int stepThreeColorCount = 0;

					System.out.println("Your Lantern Cards Details ");

					if (playing.getPlayerBlackLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Black Lantern Card :"
								+ playing.getPlayerBlackLanternCardCount());
					}
					if (playing.getPlayerWhiteLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of White Lantern Card :"
								+ playing.getPlayerWhiteLanternCardCount());
					}
					if (playing.getPlayerBlueLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Blue Lantern Card :"
								+ playing.getPlayerBlueLanternCardCount());
					}
					if (playing.getPlayerGreenLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Green Lantern Card :"
								+ playing.getPlayerGreenLanternCardCount());
					}
					if (playing.getPlayerOrangeLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Orange Lantern Card :"
								+ playing.getPlayerOrangeLanternCardCount());
					}
					if (playing.getPlayerPurpleLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Purple Lantern Card :"
								+ playing.getPlayerPurpleLanternCardCount());
					}
					if (playing.getPlayerRedLanternCardCount() > 0) {
						stepThreeColorCount += 1;
						System.out.println("Number of Red Lantern Card :"
								+ playing.getPlayerRedLanternCardCount());
					}

					if (stepThreeColorCount == 7) {
						// Remove color from player and add to the game.
						boolean type3Val = getDedicationType3ColorValidationAndRemoval(
								playing, gameObj);

						// if lantern cards are removed then update
						// dedication or generic tokens.
						if (type3Val) {
							// Remove dedication tokens from game and to player
							DedicationTokens dedicationObj = gameObj
									.getDedicationTokens();
							Vector<Integer> dedicationSevenVal = dedicationObj
									.getDedicationTokenSeven();
							if (dedicationSevenVal.size() > 0) {
								int dedicationSevenValue = dedicationSevenVal
										.remove(0);

								DedicationTokens playerDedicationObj = playing
										.getDedicationTokens();
								playerDedicationObj.getDedicationTokenSeven()
										.add(dedicationSevenValue);
							} else {
								Vector<Integer> genGenericVal = dedicationObj
										.getGenericDedicationTokens();

								// check if generic tokens vector is empty or
								// not.
								if (genGenericVal.size() > 0) {
									int genericValue = genGenericVal.remove(0);

									DedicationTokens playerDedicationObj = playing
											.getDedicationTokens();
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

	/**
	 * @param playing
	 * @param gameObj
	 * @return
	 */
	private static boolean getDedicationType3ColorValidationAndRemoval(
			Players playing, GameInstance gameObj) {

		if (playing.getPlayerBlackLanternCardCount() > 0) {
			playing.setPlayerBlackLanternCardCount(playing
					.getPlayerBlackLanternCardCount() - 1);
			gameObj.setGameBlackLanternCardCount(gameObj
					.getGameBlackLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			playing.setPlayerWhiteLanternCardCount(playing
					.getPlayerWhiteLanternCardCount() - 1);
			gameObj.setGameWhiteLanternCardCount(gameObj
					.getGameWhiteLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerBlueLanternCardCount() > 0) {
			playing.setPlayerBlueLanternCardCount(playing
					.getPlayerBlueLanternCardCount() - 1);
			gameObj.setGameBlueLanternCardCount(gameObj
					.getGameBlueLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerGreenLanternCardCount() > 0) {
			playing.setPlayerGreenLanternCardCount(playing
					.getPlayerGreenLanternCardCount() - 1);
			gameObj.setGameGreenLanternCardCount(gameObj
					.getGameGreenLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			playing.setPlayerOrangeLanternCardCount(playing
					.getPlayerOrangeLanternCardCount() - 1);
			gameObj.setGameOrangeLanternCardCount(gameObj
					.getGameOrangeLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerRedLanternCardCount() > 0) {
			playing.setPlayerRedLanternCardCount(playing
					.getPlayerRedLanternCardCount() - 1);
			gameObj.setGameRedLanternCardCount(gameObj
					.getGameRedLanternCardCount() + 1);
			return true;
		}

		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			playing.setPlayerPurpleLanternCardCount(playing
					.getPlayerPurpleLanternCardCount() - 1);
			gameObj.setGamePurpleLanternCardCount(gameObj
					.getGamePurpleLanternCardCount() + 1);
			return true;
		}
		return false;
	}

	/**
	 * @param playing
	 * @param colorOne
	 * @param gameObj
	 * @return
	 */
	private static boolean getDedicationType2ColorValidationAndRemoval(Players playing, String yourColor,
 GameInstance gameObj) {

		if (yourColor.equals("Black"))
			if (playing.getPlayerBlackLanternCardCount() > 1) {
				playing.setPlayerBlackLanternCardCount(playing
						.getPlayerBlackLanternCardCount() - 2);
				gameObj.setGameBlackLanternCardCount(gameObj
						.getGameBlackLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("White"))
			if (playing.getPlayerWhiteLanternCardCount() > 1) {
				playing.setPlayerWhiteLanternCardCount(playing
						.getPlayerWhiteLanternCardCount() - 2);
				gameObj.setGameWhiteLanternCardCount(gameObj
						.getGameWhiteLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("Blue"))
			if (playing.getPlayerBlueLanternCardCount() > 1) {
				playing.setPlayerBlueLanternCardCount(playing
						.getPlayerBlueLanternCardCount() - 2);
				gameObj.setGameBlueLanternCardCount(gameObj
						.getGameBlueLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("Green"))
			if (playing.getPlayerGreenLanternCardCount() > 1) {
				playing.setPlayerGreenLanternCardCount(playing
						.getPlayerGreenLanternCardCount() - 2);
				gameObj.setGameGreenLanternCardCount(gameObj
						.getGameGreenLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("Orange"))
			if (playing.getPlayerOrangeLanternCardCount() > 1) {
				playing.setPlayerOrangeLanternCardCount(playing
						.getPlayerOrangeLanternCardCount() - 2);
				gameObj.setGameOrangeLanternCardCount(gameObj
						.getGameOrangeLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("Red"))
			if (playing.getPlayerRedLanternCardCount() > 1) {
				playing.setPlayerRedLanternCardCount(playing
						.getPlayerRedLanternCardCount() - 2);
				gameObj.setGameRedLanternCardCount(gameObj
						.getGameRedLanternCardCount() + 2);
				return true;
			}
		if (yourColor.equals("Purple"))
			if (playing.getPlayerPurpleLanternCardCount() > 1) {
				playing.setPlayerPurpleLanternCardCount(playing
						.getPlayerPurpleLanternCardCount() - 2);
				gameObj.setGamePurpleLanternCardCount(gameObj
						.getGamePurpleLanternCardCount() + 2);
				return true;
			}
		return false;
	}

	/**
	 * @param playing
	 * @param gameObj
	 * @param color1
	 */
	private static boolean getDedicationType1ColorValidationAndRemoval(Players playing, String yourColor,
 GameInstance gameObj) {
		if (yourColor.equals("Black"))
			if (playing.getPlayerBlackLanternCardCount() > 3) {
				playing.setPlayerBlackLanternCardCount(playing
						.getPlayerBlackLanternCardCount() - 4);
				gameObj.setGameBlackLanternCardCount(gameObj
						.getGameBlackLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("White"))
			if (playing.getPlayerWhiteLanternCardCount() > 3) {
				playing.setPlayerWhiteLanternCardCount(playing
						.getPlayerWhiteLanternCardCount() - 4);
				gameObj.setGameWhiteLanternCardCount(gameObj
						.getGameWhiteLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("Blue"))
			if (playing.getPlayerBlueLanternCardCount() > 3) {
				playing.setPlayerBlueLanternCardCount(playing
						.getPlayerBlueLanternCardCount() - 4);
				gameObj.setGameBlueLanternCardCount(gameObj
						.getGameBlueLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("Green"))
			if (playing.getPlayerGreenLanternCardCount() > 3) {
				playing.setPlayerGreenLanternCardCount(playing
						.getPlayerGreenLanternCardCount() - 4);
				gameObj.setGameGreenLanternCardCount(gameObj
						.getGameGreenLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("Orange"))
			if (playing.getPlayerOrangeLanternCardCount() > 3) {
				playing.setPlayerOrangeLanternCardCount(playing
						.getPlayerOrangeLanternCardCount() - 4);
				gameObj.setGameOrangeLanternCardCount(gameObj
						.getGameOrangeLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("Red"))
			if (playing.getPlayerRedLanternCardCount() > 3) {
				playing.setPlayerRedLanternCardCount(playing
						.getPlayerRedLanternCardCount() - 4);
				gameObj.setGameRedLanternCardCount(gameObj
						.getGameRedLanternCardCount() + 4);
				return true;
			}
		if (yourColor.equals("Purple"))
			if (playing.getPlayerPurpleLanternCardCount() > 3) {
				playing.setPlayerPurpleLanternCardCount(playing
						.getPlayerPurpleLanternCardCount() - 4);
				gameObj.setGamePurpleLanternCardCount(gameObj
						.getGamePurpleLanternCardCount() + 4);
				return true;
			}
		return false;

	}

	/**
	 * @param gameObj
	 * @param playing
	 */
	private void exchageLaternCard(GameInstance gameObj, Players playing) {
		boolean loop = true;
		String yourColor = "";
		String gameColor = "";

		while (loop) {
			
			System.out.println();  //for space
			
			System.out.println("Number of your Favor "
					+ "tokens present currently = "
					+ playing.getPlayerFavorToken());
			
			System.out.println();  //for space
			
			System.out.println("Your Lantern Cards Details ");
			
			System.out.println("Number of Black Lantern Card :"
					+ playing.getPlayerBlackLanternCardCount());
			
			System.out.println("Number of White Lantern Card :"
					+ playing.getPlayerWhiteLanternCardCount());
			
			System.out.println("Number of Blue Lantern Card :"
					+ playing.getPlayerBlueLanternCardCount());
			
			System.out.println("Number of Green Lantern Card :"
					+ playing.getPlayerGreenLanternCardCount());
			
			System.out.println("Number of Orange Lantern Card :"
					+ playing.getPlayerOrangeLanternCardCount());
			
			System.out.println("Number of Purple Lantern Card :"
					+ playing.getPlayerPurpleLanternCardCount());
			
			System.out.println("Number of Red Lantern Card :"
					+ playing.getPlayerRedLanternCardCount());

			System.out.println("Number of favor tokens in game = "
					+ gameObj.getGameFavorToken());
			
			System.out.println();  //for space
			
			System.out.println("Game Lantern Cards Details ");
			
			System.out.println();  //for space
			
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
			
			loop = lanternColorPlayerValidation(playing, yourColor);
			
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
			loop = lanternColorGameValidation(gameObj, gameColor);
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

			playing.setPlayerFavorToken(playing.getPlayerFavorToken() - 2);
			
			gameObj.setGameFavorToken(gameObj.getGameFavorToken() + 2);
			
			playerColorAugment(yourColor, playing, gameColor);
			
			gameColorAugment(gameColor, gameObj, yourColor);

		}

	}

	/**
	 * @param gameColor
	 * @param gameObj
	 */
	private void gameColorAugment(String gameColor, GameInstance gameObj, String yourColor) {
		// Game lantern color Addition
		if (yourColor.equals("Black"))
			gameObj.setGameBlackLanternCardCount(gameObj.getGameBlackLanternCardCount() + 1);
		if (yourColor.equals("White"))
			gameObj.setGameWhiteLanternCardCount(gameObj.getGameWhiteLanternCardCount() + 1);
		if (yourColor.equals("Blue"))
			gameObj.setGameBlueLanternCardCount(gameObj.getGameBlueLanternCardCount() + 1);
		if (yourColor.equals("Green"))
			gameObj.setGameGreenLanternCardCount(gameObj.getGameGreenLanternCardCount() + 1);
		if (yourColor.equals("Orange"))
			gameObj.setGameOrangeLanternCardCount(gameObj.getGameOrangeLanternCardCount() + 1);
		if (yourColor.equals("Red"))
			gameObj.setGameRedLanternCardCount(gameObj.getGameRedLanternCardCount() + 1);
		if (yourColor.equals("Purple"))
			gameObj.setGamePurpleLanternCardCount(gameObj.getGamePurpleLanternCardCount() + 1);
		// Game lantern color removal
		if (gameColor.equals("Black"))
			gameObj.setGameBlackLanternCardCount(gameObj.getGameBlackLanternCardCount() - 1);
		if (gameColor.equals("White"))
			gameObj.setGameWhiteLanternCardCount(gameObj.getGameWhiteLanternCardCount() - 1);
		if (gameColor.equals("Blue"))
			gameObj.setGameBlueLanternCardCount(gameObj.getGameBlueLanternCardCount() - 1);
		if (gameColor.equals("Green"))
			gameObj.setGameGreenLanternCardCount(gameObj.getGameGreenLanternCardCount() - 1);
		if (gameColor.equals("Orange"))
			gameObj.setGameOrangeLanternCardCount(gameObj.getGameOrangeLanternCardCount() - 1);
		if (gameColor.equals("Red"))
			gameObj.setGameRedLanternCardCount(gameObj.getGameRedLanternCardCount() - 1);
		if (gameColor.equals("Purple"))
			gameObj.setGamePurpleLanternCardCount(gameObj.getGamePurpleLanternCardCount() - 1);

	}

	/**
	 * @param yourColor
	 * @param playing
	 */
	private void playerColorAugment(String yourColor, Players playing,
			String gameColor) {
		// Player lantern color removal
		if (yourColor.equals("Black")) {
			playing.setPlayerBlackLanternCardCount(playing
					.getPlayerBlackLanternCardCount() - 1);
		}
		if (yourColor.equals("White")) {
			playing.setPlayerWhiteLanternCardCount(playing
					.getPlayerWhiteLanternCardCount() - 1);
		}
		if (yourColor.equals("Blue")) {
			playing.setPlayerBlueLanternCardCount(playing
					.getPlayerBlueLanternCardCount() - 1);
		}
		if (yourColor.equals("Green")) {
			playing.setPlayerGreenLanternCardCount(playing
					.getPlayerGreenLanternCardCount() - 1);
		}
		if (yourColor.equals("Orange")) {
			playing.setPlayerOrangeLanternCardCount(playing
					.getPlayerOrangeLanternCardCount() - 1);
		}
		if (yourColor.equals("Red")) {
			playing.setPlayerRedLanternCardCount(playing
					.getPlayerRedLanternCardCount() - 1);
		}
		if (yourColor.equals("Purple")) {
			playing.setPlayerPurpleLanternCardCount(playing
					.getPlayerPurpleLanternCardCount() - 1);
		}
		
		// Player lantern color addition
		if (gameColor.equals("Black")) {
			playing.setPlayerBlackLanternCardCount(playing
					.getPlayerBlackLanternCardCount() + 1);
		}
		if (gameColor.equals("White")) {
			playing.setPlayerWhiteLanternCardCount(playing
					.getPlayerWhiteLanternCardCount() + 1);
		}
		if (gameColor.equals("Blue")) {
			playing.setPlayerBlueLanternCardCount(playing
					.getPlayerBlueLanternCardCount() + 1);
		}
		if (gameColor.equals("Green")) {
			playing.setPlayerGreenLanternCardCount(playing
					.getPlayerGreenLanternCardCount() + 1);
		}
		if (gameColor.equals("Orange")) {
			playing.setPlayerOrangeLanternCardCount(playing
					.getPlayerOrangeLanternCardCount() + 1);
		}
		if (gameColor.equals("Red")) {
			playing.setPlayerRedLanternCardCount(playing
					.getPlayerRedLanternCardCount() + 1);
		}
		if (gameColor.equals("Purple")) {
			playing.setPlayerPurpleLanternCardCount(playing
					.getPlayerPurpleLanternCardCount() + 1);
		}

	}

	/**
	 * @param gameObj
	 * @param gameColor
	 * @return
	 */
	private boolean lanternColorGameValidation(GameInstance gameObj, String gameColor) {

		if (gameColor.equals("Black"))
			if (gameObj.getGameBlackLanternCardCount() > 0) 
			{
				return true;
			}
		if (gameColor.equals("White"))
			if (gameObj.getGameWhiteLanternCardCount() > 0) 
			{
				return true;
			}
		if (gameColor.equals("Blue"))
			if (gameObj.getGameBlueLanternCardCount() > 0) 
			{
				return true;
			}
		if (gameColor.equals("Green"))
			if (gameObj.getGameGreenLanternCardCount() > 0) 
			{
				return true;
			}
		if (gameColor.equals("Orange"))
			if (gameObj.getGameOrangeLanternCardCount() > 0) 
			{
				return true;
			}
		if (gameColor.equals("Red"))
			if (gameObj.getGameRedLanternCardCount() > 0) {
				return true;
			}
		if (gameColor.equals("Purple"))
			if (gameObj.getGamePurpleLanternCardCount() > 0) 
			{
				return true;
			}
		return false;
	}

	/**
	 * @param playing
	 * @param yourColor
	 * @return
	 */
	private boolean lanternColorPlayerValidation(Players playing, String yourColor) {
		if (yourColor.equals("Black"))
			if (playing.getPlayerBlackLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("White"))
			if (playing.getPlayerWhiteLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("Blue"))
			if (playing.getPlayerBlueLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("Green"))
			if (playing.getPlayerGreenLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("Orange"))
			if (playing.getPlayerOrangeLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("Red"))
			if (playing.getPlayerRedLanternCardCount() > 0) 
			{
				return true;
			}
		if (yourColor.equals("Purple"))
			if (playing.getPlayerPurpleLanternCardCount() > 0) 
			{
				return true;
			}
		return false;
	}

	/**
	 * @param playing
	 * @param gameObj
	 * 
	 */
	private Players updateCurrentPlayer(GameInstance gameObj, Players playing) {
		int playerCont = gameObj.getPlayersList().length;
		// System.out.println("count "+ playerCont);
		// System.out.println("current number player "+
		// playing.getPlayerNumber());
		if (playing.getPlayerNumber() == playerCont) 
		{
			playing = gameObj.getPlayersList()[0];
		} else 
		{
			playing = gameObj.getPlayersList()[playing.getPlayerNumber()];
		}
		return playing;

	}

	/**
	 * @param gameObj
	 * @param playing
	 * @return
	 */
	private String genValidation(GameInstance gameObj, Players playing) {
		// Validation for Exchange a lantern
		boolean exahange = false;
		String retStr = "";
		if (playing.getPlayerFavorToken() >= 2) {
			if (playing.getPlayerBlackLanternCardCount() > 0
					|| playing.getPlayerWhiteLanternCardCount() > 0
					|| playing.getPlayerBlueLanternCardCount() > 0
					|| playing.getPlayerGreenLanternCardCount() > 0
					|| playing.getPlayerOrangeLanternCardCount() > 0
					|| playing.getPlayerPurpleLanternCardCount() > 0
					|| playing.getPlayerRedLanternCardCount() > 0) 
			{
				if (gameObj.getGameBlackLanternCardCount() > 0
						|| gameObj.getGameWhiteLanternCardCount() > 0
						|| gameObj.getGameBlueLanternCardCount() > 0
						|| gameObj.getGameGreenLanternCardCount() > 0
						|| gameObj.getGameOrangeLanternCardCount() > 0
						|| gameObj.getGamePurpleLanternCardCount() > 0
						|| gameObj.getGameRedLanternCardCount() > 0) 
				{
					retStr += "exchange";
				}
			}
		}
		// Make a dedication
		// Validation for Dedication Type 1
		boolean type1 = false;
		if (playing.getPlayerBlackLanternCardCount() > 3
				|| playing.getPlayerWhiteLanternCardCount() > 3
				|| playing.getPlayerBlueLanternCardCount() > 3
				|| playing.getPlayerGreenLanternCardCount() > 3
				|| playing.getPlayerOrangeLanternCardCount() > 3
				|| playing.getPlayerPurpleLanternCardCount() > 3
				|| playing.getPlayerRedLanternCardCount() > 3) 
		{
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenFourSize() > 0) 
			{
				retStr += "type1";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) 
			{
				retStr += "type1";
			}
		}
		
		// Validation for Dedication Type 2
		boolean type2 = false;
		int countPairs2 = 0;
		if (playing.getPlayerBlackLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 1) 
		{
			countPairs2++;
		}

		if (playing.getPlayerBlueLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (playing.getPlayerRedLanternCardCount() > 1) 
		{
			countPairs2++;
		}
		if (countPairs2 > 2) 
		{
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSixSize() > 0) {
				retStr += "type2";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type2";
			}
		}
		// type 3 validation
		boolean type3 = false;
		int countPairs7 = 0;
		if (playing.getPlayerBlackLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0) 
		{
			countPairs7++;
		}

		if (playing.getPlayerBlueLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (playing.getPlayerRedLanternCardCount() > 0) 
		{
			countPairs7++;
		}
		if (countPairs7 > 6) 
		{
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSevenSize() > 0) 
			{
				retStr += "type3";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) 
			{
				retStr += "type3";
			}
		}

		return retStr;
	}

}
