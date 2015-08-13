/**
 * 
 */
package edu.concordia.app.model;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.view.LanternGameView;

/**
 * This class creates an instance for the strategy where the game will end as
 * soon as each player has placed N Lake Tiles on the board (where the value of
 * N is chosen by the user, and must be at least 2 and at most M, where M is the
 * number of Lake Tiles in the original draw stack divided by the number of
 * players)
 * 
 * @author Team E
 *
 */
public class NCardGamePlay extends PlayGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2119275282227903209L;
	private GameInstance gameObj;
	private GameController gameController;
	private int maxCardInput;

	private transient Scanner scan = new Scanner(System.in);

	/**
	 * Constructor to initialize the properties
	 * 
	 * @param gameObj
	 * @param gameController
	 * @param maxInput
	 */
	public NCardGamePlay(GameInstance gameObj, GameController gameController, int maxCardInput) {
		super(gameObj, gameController);
		this.gameObj = gameObj;
		this.gameController = gameController;
		this.maxCardInput = maxCardInput;

		//gameObj.setGameEndMode(this);
		// System.out.println(gameObj);
	}

	/**
	 * The main method to play game.
	 * 
	 * @param scan
	 *            The Scanner object for getting input through console.
	 */
	public void gameStart() {

		this.scan = scan;

		Players playing;
		boolean playFlag = true;
		int playOption = 99;

		int turnCount = 0; // track if one turn is complete or not

		boolean exchangeRun = false; // exchange option didn't run yet
		boolean dedicationRun = false; // dedication option didn't run yet

		int totalPlayers = gameObj.getPlayersList().length;

		// total lake tiles hold by all players and one start tile.
		int loopEndValue = (totalPlayers * maxCardInput) + 1;

		while (playFlag) {

			// if N tiles were played by all players,
			// break the loop.
			if (loopEndValue == gameObj.getCurrentLakeTilesArrangement().size()) {
				break;
			}

			if (turnCount == gameObj.getPlayersList().length) {

				// ask to save and exit the game.
				super.saveAndExitGame(gameController);

				turnCount = 0;

			}

			// get current player of the game.
			playing = gameObj.getPlayerCurrentTurn();

			System.out.println("--------- Player " + playing.getPlayerNumber() + " turn ---------");

			// display player status before placing lake tile.
			new LanternGameView().displayPlayerStatus(playing);

			String opt = this.genValidation(gameObj, playing);

			while (!opt.contains("lakeTile")) {
				opt = playing.getStrategy().makeAMove(gameObj, playing, opt);
			}

			// display player status
			new LanternGameView().displayPlayerStatus(playing);
			// displayPlayerStatus(playing);

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
	protected String filterExcessLanternCards(GameInstance gameObj, Players playing, String opt) {

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

			int checkChoice = scan.nextInt();

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

		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];

			System.out.println("--------- Player " + gamePlayer.getPlayerNumber() + " turn ---------");

			String opt1 = genValidation(gameObj, gamePlayer);

			gamePlayer = gamePlayer.getStrategy().playerLastTurnChoice(gameObj, gamePlayer, opt1);

		}
		return gamePlayers;
	}

}
