/**
 * 
 */
package edu.concordia.app.strategy;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;
import edu.concordia.app.view.LanternGameView;

/**
 * This class creates an instance for the Human player.
 * 
 * @author Team E
 *
 */
public class NormalPlayerStrategy extends PlayerStrategy implements Serializable {

	private static final long serialVersionUID = -4659220962800092534L;

	private GameInstance gameObj;

	private transient Scanner scan = new Scanner(System.in);

	/**
	 * Constructor
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 */
	public NormalPlayerStrategy(GameInstance gameObj) {
		this.gameObj = gameObj;
	}

	/**
	 * Default Constructor
	 */
	public NormalPlayerStrategy() {

	}

	/**
	 * Method to implement cases for current player's last turn
	 * @param gameObj
	 * 		The GameInstance object
	 * @param gamePlayer
	 * 		The current player of the game.
	 * @param opt1
	 * 		The string to save exchange or type1/type2/type3 for dedication
	 */
	public Players playerLastTurnChoice(GameInstance gameObj, Players gamePlayer, String opt1) {

		// when player don't have enough lantern cards or favor tokens
		// for exchange and make a dedication.
		if (opt1.isEmpty()) {
			if (!opt1.contains("exchange")) {
				System.out.println("You don't have enough" + " card for exchange.");
			}
			if (!(opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3"))) {
				System.out.println("You don't have enough " + "cards to make a dedication.");
			}
		} else {

			// for loop until exchange and dedication is done until completed.
			// int optionTurnCheck = 0;
			int lastExchange = 0;
			int lastDedication = 0;

			boolean optionLoop = true;

			while (optionLoop) {

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
					if (opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3")) {
						System.out.println("1 - Make a dedication");

						// optionTurnCheck += 1;
					}
					if (!(opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3"))) {
						lastDedication = 1;
					}
				}

				System.out.println();

				int optTurnChoice = 0;

				if (lastExchange == 0 || lastDedication == 0) {

					// if don't want to use any option.
					System.out.println("2 - No Choice.");

				}

				optTurnChoice = scan.nextInt();

				switch (optTurnChoice) {
				case 0:

					gamePlayer.exchageLaternCard(gameObj);

					opt1 = PlayGame.removeSubstring(new String("exchange"), opt1);

					lastExchange = 1; // exchange option used.

					break;

				case 1:
					gamePlayer.makeADedication(gameObj, opt1);

					opt1 = PlayGame.removeSubstring(new String("type1"), opt1);
					opt1 = PlayGame.removeSubstring(new String("type2"), opt1);
					opt1 = PlayGame.removeSubstring(new String("type3"), opt1);

					lastDedication = 1; // dedication option used.

					break;

				case 2:

					System.out.println("No choice used by player " + gamePlayer.getPlayerNumber());

					optionLoop = false;

					lastExchange = 1;
					lastDedication = 1;

					break;

				default:
					break;
				}
			}
		}

		return gamePlayer;
	}

	/**
	 * Method to make a move
	 * @param gameObjs
	 * 		The GameInstance object
	 * @param playing
	 * 		The current player of the game.
	 * @param option
	 * 		The string for SEVEN/SIX/FOUR/GEN for dedication
	 */
	@Override
	public String makeAMove(GameInstance gameObj, Players playing, String opt) {

		System.out.println("--------------------------");
		System.out.println("Please enter you choice:");
		System.out.println("--------------------------");

		if (opt.contains("exchange")) {
			System.out.println("0 - Exchange a Lantern Card");

		}
		if (opt.contains("type1") || opt.contains("type2") || opt.contains("type3")) {
			System.out.println("1 - Make a dedication");
		}

		System.out.println("2 - Place a lake tile");
		System.out.println("--------------------------");

		System.out.println(); // for spacing

		System.out.print("Player " + playing.getPlayerNumber() + " choice: ");

		int optPlay = new NormalPlayerStrategy().scan.nextInt();

		System.out.println(); // for spacing

		switch (optPlay) {

		case 0:
			if (!opt.contains("exchange")) {
				System.out.println("exchange option is not available rignt now.");
				break;
			} else {

				playing.exchageLaternCard(gameObj);
				
				// to mark exchange has been done
				opt = PlayGame.removeSubstring(new String("exchange"), opt);

			}
			break;

		case 1:
			if (!(opt.contains("type1") || opt.contains("type2") || opt.contains("type3"))) {
				System.out.println("Make a dedication option is not available rignt now.");
				break;
			} else {

				playing.makeADedication(gameObj, opt);

				// to mark dedication has been done.
				opt = PlayGame.removeSubstring(new String("type1"), opt);
				opt = PlayGame.removeSubstring(new String("type2"), opt);
				opt = PlayGame.removeSubstring(new String("type3"), opt);

			}

			break;

		case 2:
			if (playing.getLanternCardCount() > 12) {

				// If lantern cards are more than 12
				// filter them i.e. discard or make a dedication.
				opt = NormalPlayerStrategy.filterExcessLanternCards(gameObj, playing, opt);

			} else {

				// remove playing object

				// lake tiles that are already placed on the board.
				new LanternGameView().displayLakeTileBoard(gameObj);

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

					adjacentTileId = new NormalPlayerStrategy().scan.nextInt();

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
						System.out.println("Please enter the correct tile id!");
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
					location = new NormalPlayerStrategy().scan.next();

					if (placementOpt.contains(location)) {
						locationLoop = false;
					} else {
						System.out.println("Please enter the correct available location.");
						locationLoop = true;
					}
				}

				if (location.equals("right") && right == true) {
					gameObj.GameBoard[adjLocY][adjLocX + 1] = tileInHand.getTilesId();

					y = adjLocY;
					x = adjLocX + 1;
				}
				if (location.equals("left") && left == true) {
					gameObj.GameBoard[adjLocY][adjLocX - 1] = tileInHand.getTilesId();

					y = adjLocY;
					x = adjLocX - 1;
				}
				if (location.equals("top") && top == true) {
					gameObj.GameBoard[adjLocY - 1][adjLocX] = tileInHand.getTilesId();

					y = adjLocY - 1;
					x = adjLocX;
				}
				if (location.equals("bottom") && bottom == true) {
					y = adjLocY + 1;
					x = adjLocX;

					gameObj.GameBoard[adjLocY + 1][adjLocX] = tileInHand.getTilesId();
				}

				// distribute lantern cards and favor tokens to
				// currently playing player
				PlayGame.distributeLakeTilesPlaying(gameObj, playing, y, x);

				// distribute lantern cards to
				// all players except currently playing player
				PlayGame.distributingLakeTilesToRestPlayers(gameObj, playing, y, x);

				// add lake tile to current lake tile arrangement vector
				gameObj.getCurrentLakeTilesArrangement().addElement(tileInHand);

				// remove laketile in hand and already placed
				playing.removePlacedLakeTile(tileInHand);

				System.out.println(""); // for space

				// take one card from draw stack to have three cards in hand
				// and remove top element from draw stack.
				Players.replenishLakeTilesInHand(gameObj, playing);

				// to mark that place a lake tile option is used.
				opt = new String("lakeTile");

			}

			break;

		default:
			break;
		}

		return opt;
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

			int tileChoice = new NormalPlayerStrategy().scan.nextInt();

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

			String degreeRotation = new NormalPlayerStrategy().scan.next();

			if (degreeRotation.equals("90")) {

				PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();

				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				choiceRotate = false;

			} else if (degreeRotation.equals("180")) {

				PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

				System.out.println();

				new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

				choiceRotate = false;

			} else if (degreeRotation.equals("270")) {

				PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

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
	public static String filterExcessLanternCards(GameInstance gameObj, Players playing, String opt) {

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

			int checkChoice = new NormalPlayerStrategy().scan.nextInt();

			switch (checkChoice) {

			case 1:
				// make dedication
				playing.makeADedication(gameObj, opt);

				// remove dedication substring from opt string
				opt = PlayGame.removeSubstring(new String("type1"), opt);
				opt = PlayGame.removeSubstring(new String("type2"), opt);
				opt = PlayGame.removeSubstring(new String("type3"), opt);

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

}
