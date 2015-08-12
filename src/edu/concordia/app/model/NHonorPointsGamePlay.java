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
 * This class creates an instance for the strategy where the game will end as soon as any one 
 * player has made enough dedications to earn N Honor points (where the value of N is chosen 
 * by the user, and must be at least 4 and at most M, where M is the sum of the values of all 
 * Dedication Tokens in the game divided by the number of players)
 * @author Team E
 *
 */
public class NHonorPointsGamePlay extends PlayGame {

	private GameInstance gameObj;
	private GameController gameController;
	private int gamePoint;
	
	private Scanner scan;
	
	/**
	 * Constructor
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
			
			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();
			
			System.out.println("--------- Player " + playing.getPlayerNumber()
					+ " turn ---------");
			
			//display player status before placing lake tile.
			new LanternGameView().displayPlayerStatus(playing);
			
			String opt = this.genValidation(gameObj, playing);
			
			while (!opt.contains("lakeTile")) {
				opt = playing.getStrategy().makeAMove(scan, gameObj, playing, opt);
				
				if(playing.getTotalPoints() >= gamePoint){
					playFlag = false;
					break;
				}
				
				System.out.println("playing score : "+playing.getTotalPoints());
				
			}
			
			if(playFlag == false){
				break;
			}

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
		}else{
			return false;
		}	
	}
}
