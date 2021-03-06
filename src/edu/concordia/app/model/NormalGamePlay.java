/**
 * 
 */
package edu.concordia.app.model;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.view.LanternGameView;

/**
 * This class creates an instance for the strategy where the game will end as it
 * did in Build 2 (i.e., exactly as described in the rule book).
 * 
 * @author Team E
 *
 */
public class NormalGamePlay extends PlayGame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8289485124522917097L;

	private GameInstance gameObj;

	private GameController gameController;

	private transient Scanner scan = new Scanner(System.in);
	
	protected String gameMode;

	/**
	 * Constructor
	 * 
	 * @param gameObj
	 * @param gameController
	 */
	public NormalGamePlay(GameInstance gameObj, GameController gameController) {
		super(gameObj, gameController);

		this.gameObj = gameObj;
		this.gameController = gameController;
		
		super.gameMode = "NormalGame";

		//gameObj.setGameEndMode(this);
	}
	
	

	/**
	 * 
	 */
	public NormalGamePlay() {
	}



	/**
	 * The main method to play game.
	 * 
	 * @param scan
	 *            The Scanner object for getting input through console.
	 */
	public void gameStart() {

		

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
				if (NormalGamePlay.checkDrawStackSize(gameObj)) {

					// check if tiles in hand of each player is empty or not

					for (int i = 0; i < gameObj.getPlayersList().length; i++) {
						Players player = gameObj.getPlayersList()[i];

						//System.out.println("tiles " + player.getCurrentLakeTilesHold().size());

						if (player.getCurrentLakeTilesHold().isEmpty()) {
							tileHoldCount = tileHoldCount + 1;
						}
					}

				}

				if (tileHoldCount == gameObj.getPlayersList().length) {
					playFlag = false;
					tileHoldCount = 0;
					break;

				}

				// ask to save and exit the game.
				saveAndExitGame(gameController);

				turnCount = 0;

			}

			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();

			System.out.println("--------- Player " + playing.getPlayerNumber() + " turn ---------");

			// display player status before placing lake tile.
			new LanternGameView().displayPlayerStatus(playing);

			String opt = genValidation(gameObj, playing);

			while (!opt.contains("lakeTile")) {
				opt = playing.getStrategy().makeAMove(gameObj, playing, opt);
			}

			// display player status
			new LanternGameView().displayPlayerStatus(playing);

			System.out.println("Lake Tile placed on the game board.");

			// remove playing object

			// lake tiles that are already placed on the board.
			new LanternGameView().displayLakeTileBoard(gameObj);

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
		// displayPlayersScore(gamePlayers);

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
	public String filterExcessLanternCards(GameInstance gameObj, Players playing, String opt) {

		while (playing.getLanternCardCount() > 12) {
			System.out.println("---------- Lantern Cards" + " Count Check -----------");

			System.out.println("Player" + playing.getPlayerNumber() + " has more than 12 lantern cards.");

			System.out.println();

			System.out.println("You have following choices to move forward.");

			if (opt.contains("type1") || opt.contains("type2") || opt.contains("type3")) {
				System.out.println("1: Make a dedication.");
			}

			System.out.println();

			System.out.println("2: Discard Lantern Cards.");

			System.out.println();

			System.out.print("Please choose one option:");

			int checkChoice = new NormalGamePlay().scan.nextInt();

			switch (checkChoice) {

			case 1:
				// make dedication
				playing.makeADedication(gameObj, opt);

				// remove dedication substring from opt string
				opt = removeSubstring(new String("type1"), opt);
				opt = removeSubstring(new String("type2"), opt);
				opt = removeSubstring(new String("type3"), opt);

				break;

			case 2:

				// discard the lantern cards.
				playing.discardLanternCards(gameObj);
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
	public Players[] gameLastTurnWithoutLakeTiles(GameInstance gameObj) {

		// last turn after all lake tiles were exposed
		Players[] gamePlayers = gameObj.getPlayersList();

		System.out.println("Last turn of each player " + "after all LakeTiles are used.");
		
		System.out.println();	//for space

		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];

			System.out.println("--------- Player " + gamePlayer.getPlayerNumber() + " turn ---------");
			
			System.out.println();	//for space

			String opt1 = genValidation(gameObj, gamePlayer);

			gamePlayer = gamePlayer.getStrategy().playerLastTurnChoice(gameObj, gamePlayer, opt1);

		}
		return gamePlayers;
	}

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
			if (playing.getPlayerBlackLanternCardCount() > 0 || playing.getPlayerWhiteLanternCardCount() > 0
					|| playing.getPlayerBlueLanternCardCount() > 0 || playing.getPlayerGreenLanternCardCount() > 0
					|| playing.getPlayerOrangeLanternCardCount() > 0 || playing.getPlayerPurpleLanternCardCount() > 0
					|| playing.getPlayerRedLanternCardCount() > 0) {
				if (gameObj.getGameBlackLanternCardCount() > 0 || gameObj.getGameWhiteLanternCardCount() > 0
						|| gameObj.getGameBlueLanternCardCount() > 0 || gameObj.getGameGreenLanternCardCount() > 0
						|| gameObj.getGameOrangeLanternCardCount() > 0 || gameObj.getGamePurpleLanternCardCount() > 0
						|| gameObj.getGameRedLanternCardCount() > 0) {
					retStr += "exchange";
				}
			}
		}
		// Make a dedication
		// Validation for Dedication Type 1
		boolean type1 = false;
		if (playing.getPlayerBlackLanternCardCount() > 3 || playing.getPlayerWhiteLanternCardCount() > 3
				|| playing.getPlayerBlueLanternCardCount() > 3 || playing.getPlayerGreenLanternCardCount() > 3
				|| playing.getPlayerOrangeLanternCardCount() > 3 || playing.getPlayerPurpleLanternCardCount() > 3
				|| playing.getPlayerRedLanternCardCount() > 3) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenFourSize() > 0) {
				retStr += "type1";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type1";
			}
		}

		// Validation for Dedication Type 2
		boolean type2 = false;
		int countPairs2 = 0;
		if (playing.getPlayerBlackLanternCardCount() > 1) {
			countPairs2++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 1) {
			countPairs2++;
		}

		if (playing.getPlayerBlueLanternCardCount() > 1) {
			countPairs2++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 1) {
			countPairs2++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 1) {
			countPairs2++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 1) {
			countPairs2++;
		}
		if (playing.getPlayerRedLanternCardCount() > 1) {
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
		// type 3 validation
		boolean type3 = false;
		int countPairs7 = 0;
		if (playing.getPlayerBlackLanternCardCount() > 0) {
			countPairs7++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			countPairs7++;
		}

		if (playing.getPlayerBlueLanternCardCount() > 0) {
			countPairs7++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 0) {
			countPairs7++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			countPairs7++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			countPairs7++;
		}
		if (playing.getPlayerRedLanternCardCount() > 0) {
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
		} else {
			return false;
		}

	}

	/**
	 * The method to save and exit the game according to the user choice.
	 * 
	 * @param gameController
	 *            The GameController object to store the game state to an XML
	 *            file.
	 */
	public void saveAndExitGame(GameController gameController) {

		// Ask user to exit the game
		System.out.println("Do you want to exit the game?(yes/no)");

		System.out.println("Please enter your choice: ");

		String exitChoice = new NormalGamePlay().scan.next();

		if (exitChoice.equals("yes")) {

			// Ask user to save file
			System.out.println("Do you want to save the game?(yes/no)");

			System.out.println("Please enter your choice: ");

			String userChoice = new NormalGamePlay().scan.next();

			if (userChoice.equals("yes")) {

				System.out.println("Please enter the file name?");

				String fileName = new NormalGamePlay().scan.next();

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

		// loop until correct input is given
		while (choiceRotate) {

			// Rotate lake tile
			System.out.print("Do you want to rotate tile by" + " 90/180/270 clockwise or 0 degree: ");

			// int degreeRotation = scan.nextInt();

			String degreeRotation = new NormalGamePlay().scan.next();

			//scan.nextLine();

			if (degreeRotation.equals("90")) {

				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();

				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				choiceRotate = false;

			} else if (degreeRotation.equals("180")) {

				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();

				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				choiceRotate = false;

			} else if (degreeRotation.equals("270")) {

				rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();

				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				choiceRotate = false;

			} else if (degreeRotation.equals("0")) {
				// do nothing
				choiceRotate = false;
			} else {
				System.out.println("Please enter the correct" + " rotation choice!");
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
		if (playing.getPlayerNumber() == playerCont) {
			playing = gameObj.getPlayersList()[0];
		} else {
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

			System.out.println("******** Player " + playing.getPlayerNumber() + " Lake Tiles details ********");

			Vector<LakeTiles> lakeTiles = playing.getCurrentLakeTilesHold();

			System.out.println(); // for spacing

			System.out.println("No of Lake Tiles in hand: " + lakeTiles.size());

			for (int j = 0; j < lakeTiles.size(); j++) {
				LakeTiles tileHolded1 = lakeTiles.get(j);

				new LanternGameView().displayLakeTiles(gameObj, tileHolded1.getTilesId());

			}

			System.out.println(); // for spacing

			System.out.println("Please enter your Lake Tile number: ");

			int tileChoice = new NormalGamePlay().scan.nextInt();

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
