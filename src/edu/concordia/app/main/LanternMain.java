/**
 * 
 */
package edu.concordia.app.main;

import java.util.Scanner;

import edu.concordia.app.controller.GameController;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;

/**
 * @author Team E
 */

/**
 * The LanternMain class is the driver class. It will show game options for new
 * game, save game and load game.
 */
public class LanternMain {

	/**
	 * Scanner object to get user input from console.
	 */
	private static Scanner scan = new Scanner(System.in);

	private static final long serialVersionUID = 2550694388930175952L;

	private static int playerNumber;

	/**
	 * Default constructor for an LanternMain.
	 */
	public LanternMain() {

	}

	/**
	 * Main Method.
	 * Asks the user to choose option for New Game/Load Saved Game/Exit.
	 * Creates objects for LanternMain, GameConfiguration and GameInstance gameObj.
	 * Initializes the 2D array for the lake tile layout.
	 * Creates the PlayGame object.
	 * Does input validation as well.
	 * @param args
	 */
	public static void main(String[] args) {

		// object of LanternMain class.
		LanternMain mainObj = new LanternMain();

		System.out.println("Welcome! to the");
		System.out.println("Lantern: The Harvest Festival Game");
		System.out.println("-----------------------------");

		boolean choiceInitial = true;

		while (choiceInitial) {

			System.out.println("Press 0 for New Game.");
			System.out.println("Press 1 to Load saved Game.");
			System.out.println("Press 2 to Exit Game.");

			GameController gameController = null;
			String response = scan.next();
			scan.nextLine();

			if (response.equals("0")) {

				int playerNumber = mainObj.getInputNoOfPlayers();

				System.out.println();

				GameConfiguration config = new GameConfiguration(playerNumber);

				GameInstance gameObj = new GameInstance(config);

				gameController = new GameController(config, gameObj);

				gameController.showTextMode(gameObj);

				// initialize the array
				for (int i = 0; i < 73; i++) {
					for (int j = 0; j < 73; j++)
						gameObj.GameBoard[i][j] = 99;
				}

				// place start tile at center.
				gameObj.GameBoard[36][36] = 0;

				// method to play game

				new PlayGame(gameObj, gameController).gameStart(scan);

				choiceInitial = false;

			} else if (response.equals("1")) {

				gameController = new GameController();
				String fileName = mainObj.getLoadFileName();

				GameInstance instance = gameController
						.loadGameFromFile(fileName);

				if (instance == null) {
					System.out.println("File not found.");

				} else {
					gameController.showTextMode(instance);

					new PlayGame(instance, gameController).gameStart(scan);

				}
				choiceInitial = false;

			} else if (response.equals("2")) {
				choiceInitial = false;
				System.exit(0);
			} else {
				System.out.println("Please enter correct input (0/1/2)");
				choiceInitial = true;
			}

		}

	}

	/**
	 * @return The scanner object to be used in other class. The static scanner
	 *         object to get user input.
	 */
	static String getValue() {

		return scan.next();
	}

	/**
	 * Asks the user to input a valid no of players (no of players between 2 and 4)
	 * Does input validation as well.
	 * @return The no of players as input by the user.
	 */
	public int getInputNoOfPlayers() {
		int maxPlayers = 4;
		int input;

		do {
			System.out.println(); // for providing space

			System.out
					.print("Please enter the no of players (between 2 and 4) :");
			while (!scan.hasNextInt()) {
				System.out.println("Invalid input! Please try again:");
				scan.next();
				System.out
						.print("Please enter the no of players (between 2 and 4) :");
			}

			input = scan.nextInt();
			scan.nextLine();
			if (input < 0 || input > maxPlayers) {
				System.out
						.println("Input must be within the specified range! Please try again:");
			}

		} while (input < 2 || input > maxPlayers);

		return input;
	}

	/**
	 * The method to get the user input of file name for loading.
	 * The file name to be loaded by user is validated.
	 * @return The file name to be loaded by user.
	 */
	public String getLoadFileName() {
		// int maxPlayers = 4;
		String input;
		int inputSize;

		do {
			System.out.print("Please enter the file to load :");
			while (!scan.hasNextLine()) {
				System.out.println("Invalid input! Please try again:");
				scan.next();
				System.out.print("Please enter the file to load :");
			}

			input = scan.nextLine();
			inputSize = input.length();

			if (inputSize == 0) {
				System.out.println("Please enter the file to load :");
			}

		} while (inputSize == 0);

		return input;
	}

	/**
	 * Gets the user to input file name for loading.
	 * The file name to be loaded by user is validated.
	 * @return The file name to be loaded by user.
	 */
	public String getSaveInput() {

		String stringValue;
		do {
			System.out.println("Please enter your choice:");

			System.out.println("3:Do you want to save the game?(yes/no)");

			stringValue = scan.next();

			System.out.println(stringValue);
		} while (stringValue.length() == 0);

		return stringValue;
	}

}
