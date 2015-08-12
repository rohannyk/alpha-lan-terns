/**
 * 
 */
package edu.concordia.app.main;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.NCardGamePlay;
import edu.concordia.app.model.NHonorPointsGamePlay;
import edu.concordia.app.model.NormalGamePlay;
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
		
		PlayGame pGame;

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
				
				System.out.println();  //for space
				
				//set player type in vector
				Vector<String> playerTypes = mainObj.getPlayerTypeArray(playerNumber);

				System.out.println();

				GameConfiguration config = new GameConfiguration(playerNumber);

				GameInstance gameObj = new GameInstance(config, playerTypes);
				
				gameController = new GameController(config, gameObj);
				
				System.out.println("How you want to end the game?");
				
				System.out.println("1: Normal game end.");
				
				System.out.println("2: N Lake tile game end.");
				
				System.out.println("3: N Honor points game end.");
				
				System.out.print("Please enter your choice: ");
				
				int endChoice = scan.nextInt();
				
				switch (endChoice) {
				case 1:

//					gameObj.setGameEndMode(new NormalGamePlay(gameObj, gameController));
//					

					
					pGame = new NormalGamePlay(gameObj, gameController);
					
					pGame = gameObj.getGameEndMode();
					
					break;
				
				case 2:
					
					int totalTiles = gameObj.getGameTilesDrawPile().size();
					
					int playerTiles = 3*playerNumber; //tiles deal to all player
					
					int maxInput = (totalTiles+playerTiles)/playerNumber;
					
					//System.out.println(maxInput);
					
					boolean cardLoop = true;
					
					int tileChoice = 0;

					while (cardLoop) {
						System.out.println("Please enter No. of Laketiles "
								+ "each player will place(between 2 and "
								+ maxInput + ").");
						
						tileChoice = scan.nextInt();
						
						if(tileChoice <= 4 || tileChoice >= 2){
							cardLoop = false;
						}
					}
					
					//gameObj.setGameEndMode(new NCardGamePlay(gameObj, gameController, tileChoice));
					
					//pGame = gameObj.getGameEndMode();

					pGame = new NCardGamePlay(gameObj, gameController, tileChoice);
					
					//gameObj.setGameEndMode(pGame);
					
					break;
				
				case 3:
					
					DedicationTokens dedicationTokens = gameObj.getDedicationTokens();
					
					Vector<Integer> fourValues = dedicationTokens.getDedicationTokenFour();
					Vector<Integer> sixValues = dedicationTokens.getDedicationTokenSix();
					Vector<Integer> sevenValues = dedicationTokens.getDedicationTokenSeven();
					Vector<Integer> genericValues = dedicationTokens.getGenericDedicationTokens();
					
					int totalPoints = 0;
					
					for (Iterator<Integer> iterator = fourValues.iterator(); iterator
							.hasNext();) {
						Integer integer = (Integer) iterator.next();
						totalPoints = totalPoints + integer;
						
					}
					
					for (Iterator<Integer> iterator = sixValues.iterator(); iterator
							.hasNext();) {
						Integer integer = (Integer) iterator.next();
						totalPoints = totalPoints + integer;
						
					}
					
					for (Iterator<Integer> iterator = sevenValues.iterator(); iterator
							.hasNext();) {
						Integer integer = (Integer) iterator.next();
						totalPoints = totalPoints + integer;
						
					}
					
					for (Iterator<Integer> iterator = genericValues.iterator(); iterator
							.hasNext();) {
						Integer integer = (Integer) iterator.next();
						totalPoints = totalPoints + integer;
						
					}
					
					System.out.println("total points: "+totalPoints);
					
					int maxPointInput = totalPoints/playerNumber;
					//System.out.println(maxPointInput);					
					
					boolean pointLoop = true;
					
					int pointChoice = 0;

					while (pointLoop) {
						System.out.println("Please enter No. of Honor points "
								+ "winner should have(between 4 and "
								+ maxPointInput + ").");
						
						pointChoice = scan.nextInt();
						
						if(pointChoice <= maxPointInput || pointChoice >= 4){
							pointLoop = false;
						}
					}
					
					//gameObj.setGameEndMode(new NHonorPointsGamePlay(gameObj, gameController, pointChoice));
					
					//pGame = gameObj.getGameEndMode();
					
					pGame = new NHonorPointsGamePlay(gameObj, gameController, pointChoice);
					
					//gameObj.setGameEndMode(pGame);
					
					break;

				default:
					
					gameObj.setGameEndMode(new NormalGamePlay(gameObj, gameController));
					
					pGame = gameObj.getGameEndMode();
					
//					pGame = new NormalGamePlay(gameObj, gameController);
//					gameObj.setGameEndMode(pGame);
					break;
				}

				

				gameController.showTextMode(gameObj);

				// initialize the array
				for (int i = 0; i < 73; i++) {
					for (int j = 0; j < 73; j++)
						gameObj.GameBoard[i][j] = 99;
				}

				// place start tile at center.
				gameObj.GameBoard[36][36] = 0;

				// method to play game
				pGame.gameStart(scan);
				//new PlayGame(gameObj, gameController).gameStart(scan);

				choiceInitial = false;

			} else if (response.equals("1")) {

				gameController = new GameController();
				String fileName = mainObj.getLoadFileName();

				GameInstance instance = gameController
						.loadGameFromFile(fileName);

				if (instance == null) {
					System.out.println("File not found.");

				} else {
					//gameController.showTextMode(instance);
					
					if(instance.getGameEndMode() instanceof NormalGamePlay){
						System.out.println("normal");
						instance.getGameEndMode().gameStart(scan);
					}else if(instance.getGameEndMode() instanceof NCardGamePlay){
						System.out.println("card");
					}else if(instance.getGameEndMode() instanceof NHonorPointsGamePlay){
						System.out.println("point");
					}else if(instance.getGameEndMode() instanceof PlayGame){
						System.out.println("default");
					}
					
					instance.getGameEndMode().gameStart(scan);

					//pGame.gameStart(scan);
					//new PlayGame(instance, gameController).gameStart(scan);

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
	 * @param playerNumber2 
	 * @return
	 */
	private Vector<String> getPlayerTypeArray(int playerNumber) {
		
		Vector<String> playerTypes = new Vector<String>();
		
		for (int i = 0; i < playerNumber; i++) {
			
			System.out.println("Please enter the player type (0-Greedy/"
					+ "1-Unfriendly/2-Random/3-Friendly/4-Human).");
			
			System.out.println("Enter type for player "+(i+1));
			System.out.print(" your choice: ");
			
			int typeChoice = scan.nextInt();
			
			switch (typeChoice) {
			
			case 0:
				
				playerTypes.add("Greedy");
				break;

			case 1:
				
				playerTypes.add("Unfriendly");
				break;
				
			case 2:
	
				playerTypes.add("Random");
				break;
			
			case 3:
				
				playerTypes.add("Friendly");
				break;
				
			case 4:
	
				playerTypes.add("Human");
				break;
				
			default:
				playerTypes.add("Human");
				break;
			}
			System.out.println();  //for space
		}		
		
		return playerTypes;
	}

	/**
	 * @return The scanner object to be used in other class. The static scanner
	 *         object to get user input.
	 */
	public static Scanner getValue() {

		return scan;
		//return scan.next();
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
