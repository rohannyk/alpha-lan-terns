/**
 * 
 */
package edu.concordia.app.model;

import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.view.LanternGameView;

/**
 * @author lovepreet
 *
 */
public class NormalGamePlay extends PlayGame {

	private GameInstance gameObj;
	
	private GameController gameController;
	
	private Scanner scan;
	
	/**
	 * @param gameObj
	 * @param gameController
	 */
	public NormalGamePlay(GameInstance gameObj, GameController gameController) {
		super(gameObj, gameController);
		
		this.gameObj = gameObj;
		this.gameController = gameController;
		
		gameObj.setGameEndMode(this);
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
				
				//gameObj.getGameTilesDrawPile().removeAllElements();
				
				int tileHoldCount = 0;
				
				// if draw stack is empty
				if (NormalGamePlay.checkDrawStackSize(gameObj)) {
					
					//check if tiles in hand of each player is empty or not
					
					
					for (int i = 0; i < gameObj.getPlayersList().length; i++) {
						Players player = gameObj.getPlayersList()[i];
						
						System.out.println("tiles "+player.getCurrentLakeTilesHold().size());
						
						if(player.getCurrentLakeTilesHold().isEmpty()){
							tileHoldCount = tileHoldCount+1;
						}
					}
										
				}
				
				if(tileHoldCount == gameObj.getPlayersList().length){
					playFlag = false;
					tileHoldCount = 0;
					break;
					
				}

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

			//gameObj.getGameTilesDrawPile().removeAllElements();
			
			// if stack is empty, method returns true.
			// break the loop.
			/*if (checkDrawStackSize(gameObj)) {
				break;
			}*/

			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();

			System.out.println("--------- Player " + playing.getPlayerNumber()
					+ " turn ---------");
			
			//display player status before placing lake tile.
			new LanternGameView().displayPlayerStatus(playing);			

			String opt = genValidation(gameObj, playing);
			
			
			while (!opt.contains("lakeTile")) {
				opt = playing.getStrategy().makeAMove(scan, gameObj, playing, opt);
			}
			
			

			/*if(exchangeRun){
				// remove exchange substring from opt string
				opt = removeSubstring(new String("exchange"), opt);
			}
			if(dedicationRun){
				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);
			}*/

			/*if (opt.contains("exchange")) {
				System.out.println("0 - Exchange a Lantern Card");

			}
			if (opt.contains("type1") || opt.contains("type2")
					|| opt.contains("type3")) {
				System.out.println("1 - Make a dedication");
			}
			
			System.out.println("--------------------------");
			System.out.println("Please enter you choice:");
			System.out.println("--------------------------");

			System.out.println("2 - Place a lake tile");
			System.out.println("--------------------------");

			System.out.println(); // for spacing

			System.out.print("Player " + playing.getPlayerNumber()
					+ " choice: ");*/
			
			//playing.getStrategy().makeAMove(scan, playing, opt);
			
			//playing.getStrategy().placeALakeTile(scan, playing, gameObj);

			/*int optPlay = scan.nextInt();

			System.out.println(); // for spacing

			switch (optPlay) {
			
			case 0:
				if (!opt.contains("exchange")) {
					break;
				} else {

					playing.exchageLaternCard(gameObj, scan);
					exchangeRun = true; // to mark exchange has been done.
					
				}
				break;
			
			case 1:
				if (!(opt.contains("type1") || opt.contains("type2") || opt
						.contains("type3"))) {
					break;
				} else {

					playing.makeADedication(gameObj, opt, scan);
					dedicationRun = true; // to mark dedication has been done.
					
				}

				break;
			
			case 2:
				if (playing.getLanternCardCount() > 12) {

					// If lantern cards are more than 12
					// filter them i.e. discard or make a dedication.
					opt = filterExcessLanternCards(gameObj, playing, opt);

				} else {

					//remove playing object
					
					// lake tiles that are already placed on the board.
					new LanternGameView().displayLakeTileBoard(gameObj);
					//displayLakeTileBoard(gameObj);
					

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

					// distribute lantern cards and favor tokens to
					// currently playing player
					//super.distributeLakeTilesPlaying(gameObj, playing, y, x);

					// distribute lantern cards to
					// all players except currently playing player
					//super.distributingLakeTilesToRestPlayers(gameObj, playing, y, x);

					// add lake tile to current lake tile arrangement vector
					gameObj.getCurrentLakeTilesArrangement().addElement(
							tileInHand);

					// remove laketile in hand and already placed
					playing = playing.removePlacedLakeTile(tileInHand);

					System.out.println(""); // for space

					// take one card from draw stack to have three cards in hand
					// and remove top element from draw stack.
					boolean containTiles = Players.replenishLakeTilesInHand(gameObj,
							playing);

					if (containTiles) {
						playFlag = true;

					} else {
						playFlag = false;
						break;
					}

					//display player status
					new LanternGameView().displayPlayerStatus(playing);
					//displayPlayerStatus(playing);

					System.out.println("Lake Tile placed on the game board.");

					//remove playing object
					
					// lake tiles that are already placed on the board.
					new LanternGameView().displayLakeTileBoard(gameObj);
					//displayLakeTileBoard(gameObj);

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
			}*/
			
			//display player status
			new LanternGameView().displayPlayerStatus(playing);
			//displayPlayerStatus(playing);

			System.out.println("Lake Tile placed on the game board.");

			//remove playing object
			
			// lake tiles that are already placed on the board.
			new LanternGameView().displayLakeTileBoard(gameObj);
			//displayLakeTileBoard(gameObj);
			
			// update current player of game to next player
			playing = updateCurrentPlayer(gameObj, playing);

			gameObj.setPlayerCurrentTurn(playing);
			
			// exchange option will run after player change.
			exchangeRun = false; 
			dedicationRun = false;

			turnCount += 1;

			System.out.println(); // for space

		}

		// last turn of game when all lake tile cards were used.
		Players[] gamePlayers = gameLastTurnWithoutLakeTiles(gameObj);

		// Display players score
		new LanternGameView().displayPlayersScore(gamePlayers);
		//displayPlayersScore(gamePlayers);

		// validate who are the winners
		Vector<Players> winnerPlayers = validateWinner(gamePlayers);

		// Display winner
		new LanternGameView().displayWinner(winnerPlayers);

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
	public String filterExcessLanternCards(GameInstance gameObj,
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
		}
		return opt;
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
	public  Players[] gameLastTurnWithoutLakeTiles(GameInstance gameObj) {
		
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
			
			gamePlayer = gamePlayer.getStrategy().playerLastTurnChoice(gameObj, gamePlayer, opt1);
			
			//when player don't have enough lantern cards or favor tokens
			// for exchange and make a dedication.
			/*if (opt1.isEmpty()) {
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
				//int optionTurnCheck = 0;
				int lastExchange = 0;
				int lastDedication = 0;
				
				boolean optionLoop = true;
				
				while(optionLoop){
					
					if (lastExchange == 1 && lastDedication == 1) {
						optionLoop = false;
						break;
					}
					
					System.out.println("--------------------------");
					
					System.out.println("Please enter you choice:");
					
					System.out.println("--------------------------");
									

					if (lastExchange == 0) {
						if (opt1.contains("exchange")) {
							System.out.println("0 - Exchange a Lantern Card");

							// optionTurnCheck += 1;

						}
						if (!opt1.contains("exchange")) {
							lastExchange = 1;
						}
					}

					if (lastDedication == 0) {
						if (opt1.contains("type1") || opt1.contains("type2")
								|| opt1.contains("type3")) {
							System.out.println("1 - Make a dedication");

							// optionTurnCheck += 1;
						}
						if (!(opt1.contains("type1") || opt1.contains("type2")
								|| opt1.contains("type3"))) {
							lastDedication = 1;
						}
					}

					System.out.println();
					
					int optTurnChoice = 0;
					
					if (lastExchange == 0 || lastDedication == 0) {
						
						//if don't want to use any option.
						System.out.println("2 - No Choice.");
						
					}
					
					optTurnChoice = scan.nextInt();

					switch (optTurnChoice) {
					case 0:
						gamePlayer.exchageLaternCard(gameObj, scan);
						//System.out.println(opt1);
						//opt1 = removeSubstring(new String("exchange"), opt1);
						//System.out.println(opt1);

						lastExchange = 1; //exchange option used.

						break;

					case 1:
						gamePlayer.makeADedication(gameObj, opt1, scan);
						//System.out.println(opt1);
						//opt1 = removeSubstring(new String("type1"), opt1);
						//opt1 = removeSubstring(new String("type2"), opt1);
						//opt1 = removeSubstring(new String("type3"), opt1);
						//System.out.println(opt1);

						lastDedication = 1; //dedication option used.

						break;
						
					case 2:
						
						System.out.println("No choice used by player "+gamePlayer.getPlayerNumber());
						
						optionLoop = false;
						
						lastExchange = 1;
						lastDedication = 1;
						
						break;

					default:
						break;
					}
					
					// if all options are used
					// exit loop
					//if (optionTurnCheck == 2) {
						
					//}
					if (optionTurnCheck == 1) {
						if (lastExchange == 1 || lastDedication == 1) {
							optionLoop = false;
						}
					}
					//optionTurnCheck = 0;					
				}				
			}*/
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
	/*public  String removeSubstring(String string, String opt1) {
	
		int removeSize = string.length();
		
		int strtPoint = opt1.indexOf(string);
		//int endPoint = opt1.lastIndexOf(string);
		
		String firstSubString = "";
		String lastSubString = "";
		
		if(strtPoint == -1){
			
		}else if(strtPoint == 0){
			 firstSubString = "";
			 lastSubString = opt1.substring(((strtPoint)+removeSize), opt1.length());
		}else{
			 firstSubString = opt1.substring(0, strtPoint-1);
			 lastSubString = opt1.substring(((strtPoint)+removeSize), opt1.length());
		}
		
		//opt1 = new String(firstSubString+lastSubString);
		return new String(firstSubString+lastSubString);
	}*/
	
	/**
	 * The method to validate the options(exchange/make a dedication) user can
	 * choose during the game.
	 * 
	 * @param gameObj
	 *            The GameInstance to verify if exchange or is possible or not.
	 * @param playing
	 *            The player to verify if exchange or is possible or not.
	 * @return The string containing the possible options.
	 */
	public String genValidation(GameInstance gameObj, Players playing) {
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
		if(gameObj.getGameTilesDrawPile().isEmpty()){
			return true;
//			return 0;
		}else{
			return false;
			//return gameObj.getGameTilesDrawPile().size();
		}
		
	}
	
	/**
	 * The method to save and exit the game according to the user choice.
	 * 
	 * @param gameController
	 *            The GameController object to store the game state to an XML
	 *            file.
	 */
	public  void saveAndExitGame(GameController gameController) {
		
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
	 * This method asks takes input from user and rotate the selected tile to
	 * 0/90/180/270 degree.
	 * 
	 * @param tileInHand
	 *            The tile to be rotated before placing on the board.
	 * @return The rotated lake tile.
	 */
	public LakeTiles rotateLakeTileOnUserChoice(LakeTiles tileInHand) {
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
				
				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();
				
				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				/*System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());*/

				choiceRotate = false;

			} else if (degreeRotation.equals("180")) {
				
				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();
				
				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				/*System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());*/
				
				choiceRotate = false;

			} else if (degreeRotation.equals("270")) {

				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();
				
				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				/*System.out.println("Lake Tile : " + tileInHand.getTilesId()
						+ " TC: " + tileInHand.getTopColor() + " RC: "
						+ tileInHand.getRightColor() + " BC: "
						+ tileInHand.getBottomColor() + " LC: "
						+ tileInHand.getLeftColor() + " Platform: "
						+ tileInHand.isPlatform());*/

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
	 * 
	 * The method to switch turn to the next player of the game in clockwise
	 * direction.
	 * 
	 * @param playing
	 *            The current player of the game.
	 * @param gameObj
	 *            The GameInstance to retrieve players of the game.
	 */
	public Players updateCurrentPlayer(GameInstance gameObj, Players playing) {
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
	 * The method to ask player to select one lake tile from three lake tiles in
	 * hand and return the selected lake tile.
	 * 
	 * @param playing
	 *            The player of the game.
	 * @return The lake tile selected by the player.
	 */
	public LakeTiles revealLakeTile(Players playing) {

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
				
				new LanternGameView().displayLakeTiles(gameObj, tileHolded1.getTilesId());
				
				/*System.out.println("Lake Tile Id : "
						+ tileHolded1.getTilesId() + " TC: "
						+ tileHolded1.getTopColor() + " RC: "
						+ tileHolded1.getRightColor() + " BC: "
						+ tileHolded1.getBottomColor() + " LC: "
						+ tileHolded1.getLeftColor() + " Platform: "
						+ tileHolded1.isPlatform());*/
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

}
