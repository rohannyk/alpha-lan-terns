/**
 * 
 */
package edu.concordia.app.model;

import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.view.LanternGameView;

/**
 * @author lovepreet
 *
 */
public class NHonorPointsGamePlay extends PlayGame {

	private GameInstance gameObj;
	private GameController gameController;
	private int gamePoint;
	
	private Scanner scan;
	
	/**
	 * @param gameObj
	 * @param gameController
	 * @param maxPointInput
	 */
	public NHonorPointsGamePlay(GameInstance gameObj,
			GameController gameController, int gamePoint) {
		super(gameObj, gameController);
		this.gameObj = gameObj;
		this.gameController = gameController;
		this.gamePoint = gamePoint;
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
				
				int tileHoldCount = 0;
				
				// if draw stack is empty
				if (NHonorPointsGamePlay.checkDrawStackSize(gameObj)) {
					
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
				super.saveAndExitGame(gameController);

				turnCount = 0;

			}			
			
			/*for (int i = 0; i < gameObj.getPlayersList().length; i++) {
				Players playingCheck = gameObj.getPlayersList()[i];
				
				if(playingCheck.getTotalPoints() >= gamePoint){
					playFlag = false;
					break;
				}
				
			}*/
			
			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();
			
			/*if(playing.getTotalPoints() >= gamePoint){
				playFlag = false;
				break;
			}*/

			System.out.println("--------- Player " + playing.getPlayerNumber()
					+ " turn ---------");
			
			//display player status before placing lake tile.
			new LanternGameView().displayPlayerStatus(playing);
			
			

			String opt = this.genValidation(gameObj, playing);
			
			/*System.out.println("--------------------------");
			System.out.println("Please enter you choice:");
			System.out.println("--------------------------");

			if(exchangeRun){
				// remove exchange substring from opt string
				opt = super.removeSubstring(new String("exchange"), opt);
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
					+ " choice: ");*/
			
			while (!opt.contains("lakeTile")) {
				opt = playing.getStrategy().makeAMove(scan, gameObj, playing, opt);
				
				if(playing.getTotalPoints() >= gamePoint){
					playFlag = false;
					break;
				}
				
				System.out.println("playing score : "+playing.getTotalPoints());
				
				//if score is not reached and all lake tiles has been placed
				// exit the loop
				if(playing.getTotalPoints() != gamePoint){
					
					break;
				}
				
				
				
				
			}
			
			if(playFlag == false){
				break;
			}

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
					distributeLakeTilesPlaying(gameObj, playing, y, x);

					// distribute lantern cards to
					// all players except currently playing player
					distributingLakeTilesToRestPlayers(gameObj, playing, y, x);

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

		Players[] gamePlayers = gameObj.getPlayersList();
		
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
	protected String filterExcessLanternCards(GameInstance gameObj,
			Players playing, String opt) 
	{
		
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

}
