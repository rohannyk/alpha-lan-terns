/**
 * 
 */
package edu.concordia.app.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;
import edu.concordia.app.view.LanternGameView;

/**
 * This class creates an object of random player strategy which is a sub-class of PlayerStrategy.java
 * This class contains the logic for random player strategy.
 * @author Team E
 */
public class RandomPlayerStrategy extends PlayerStrategy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3917573629181668193L;
	private GameInstance gameObj;
	
	public String name;

	/**
	 * Constructor
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 */
	public RandomPlayerStrategy(GameInstance gameObj) {

		this.gameObj = gameObj;
		super.name = "Random";
	}

	/**
	 * Method to implement cases for current player's last turn
	 * 
	 * @param gameObj
	 *            The GameInstance object
	 * @param gamePlayer
	 *            The current player of the game.
	 * @param opt1
	 *            The string to save exchange or type1/type2/type3 for
	 *            dedication
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

					}
					if (!opt1.contains("exchange")) {
						lastExchange = 1;
					}
				}

				if (lastDedication == 0) {
					if (opt1.contains("type1") || opt1.contains("type2") || opt1.contains("type3")) {
						System.out.println("1 - Make a dedication");

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

				if (lastExchange == 0 && lastDedication == 1) {

					optTurnChoice = 0;

				}

				if (lastExchange == 1 && lastDedication == 0) {

					optTurnChoice = 1;

				}

				optTurnChoice = getRandomNumber(2);

				switch (optTurnChoice) {
				case 0:

					this.exchageLaternCard(gameObj, gamePlayer);

					opt1 = PlayGame.removeSubstring(new String("exchange"), opt1);

					lastExchange = 1; // exchange option used.

					break;

				case 1:
					this.makeADedication(gameObj, opt1, gamePlayer);

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
	 * 
	 * @param gameObjs
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param option
	 *            The string for SEVEN/SIX/FOUR/GEN for dedication
	 */
	@Override
	public String makeAMove(GameInstance gameObjs, Players playing, String opt) {

		// this.scan = scan;

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

		int optPlay = 0;

		// if any one of the options available
		if ((opt.contains("exchange") && !(opt.contains("type1") || opt.contains("type2") || opt.contains("type3")))
				|| (!opt.contains("exchange")
						&& (opt.contains("type1") || opt.contains("type2") || opt.contains("type3")))) {

			int randomNum = getRandomNumber(2);

			if (randomNum == 0) {
				if (opt.contains("exchange")) {
					optPlay = 0;
				} else if ((opt.contains("type1") || opt.contains("type2") || opt.contains("type3"))) {
					optPlay = 1;
				}

			}
			if (randomNum == 1) {
				optPlay = 2;
			}
		}

		// if both options available
		if (opt.contains("exchange") && (opt.contains("type1") || opt.contains("type2") || opt.contains("type3"))) {
			optPlay = getRandomNumber(3);
		}

		// if both options not available
		if (!(opt.contains("exchange") && (opt.contains("type1") || opt.contains("type2") || opt.contains("type3")))) {
			optPlay = 2;
		}

		System.out.print("Player " + playing.getPlayerNumber() + " choice: ");

		System.out.println(); // for spacing

		switch (optPlay) {

		case 0:
			if (!opt.contains("exchange")) {
				System.out.println("exchange option is not available rignt now.");
				break;
			} else {

				this.exchageLaternCard(gameObj, playing);

				opt = PlayGame.removeSubstring(new String("exchange"), opt);// to
																			// mark
																			// exchange
																			// has
																			// been
																			// done.

			}
			break;

		case 1:
			if (!(opt.contains("type1") || opt.contains("type2") || opt.contains("type3"))) {
				System.out.println("Make a dedication option is not available rignt now.");
				break;
			} else {

				this.makeADedication(gameObj, opt, playing);

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
				opt = this.filterExcessLanternCards(gameObj, playing, opt);

			} else {

				// remove playing object

				// lake tiles that are already placed on the board.
				new LanternGameView().displayLakeTileBoard(gameObj);
				// displayLakeTileBoard(gameObj);

				// select lake tile from tiles in hand
				LakeTiles tileInHand = revealLakeTile(playing);

				// ask user to rotate card to what degree (0/90/180/270)
				// and return the rotated lake tile
				tileInHand = this.rotateLakeTileOnUserChoice(tileInHand);

				System.out.println(); // for spacing

				int startHorizontal = LanternGameView.getStartHorizontal(gameObjs);
				int endHorizontal = LanternGameView.getEndHorizontal(gameObjs);
				int startVertial = LanternGameView.getStartVertial(gameObjs);
				int endVertial = LanternGameView.getEndVertial(gameObjs);

				// arraylist contain lake tiles where adjacent lake tiles can be
				// placed.
				ArrayList<String> possibleAdjacentBoardLakeTiles = new ArrayList<String>();

				for (int i = startHorizontal; i < endHorizontal + 1; i++) {
					for (int j = startVertial; j < endVertial + 1; j++) {
						if ((gameObjs.GameBoard[i][j]) != 99) {

							String possibleLakeTilesOpt = Integer.toString(gameObjs.GameBoard[i][j]);

							if (gameObjs.GameBoard[i][j - 1] == 99) {
								// left = true;

								possibleLakeTilesOpt += ":L";
							}
							if (gameObjs.GameBoard[i][j + 1] == 99) {
								// right = true;

								possibleLakeTilesOpt += ":R";
							}
							if (gameObjs.GameBoard[i - 1][j] == 99) {

								// top = true;
								possibleLakeTilesOpt += ":T";
							}
							if (gameObjs.GameBoard[i + 1][j] == 99) {

								// bottom = true;
								possibleLakeTilesOpt += ":B";
							}

							possibleAdjacentBoardLakeTiles.add(possibleLakeTilesOpt);

						}
					}
					System.out.println("");
				}

				// choose random adjacent lake tile
				int adjacentTileToPlace = getRandomNumber(possibleAdjacentBoardLakeTiles.size());

				String placementString = possibleAdjacentBoardLakeTiles.get(adjacentTileToPlace);

				boolean tileStatus = placeALakeTileAndDistribute(gameObjs, playing, placementString, tileInHand);

				while (!tileStatus) {
					possibleAdjacentBoardLakeTiles.remove(adjacentTileToPlace);

					// choose random adjacent lake tile
					adjacentTileToPlace = getRandomNumber(possibleAdjacentBoardLakeTiles.size());
					placementString = possibleAdjacentBoardLakeTiles.get(adjacentTileToPlace);
					tileStatus = placeALakeTileAndDistribute(gameObjs, playing, placementString, tileInHand);
				}

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
	 * Method to place a lake tile and distribute the lantern cards
	 * 
	 * @param gameObjs
	 *            The GameInstance object
	 * @param playing
	 *            The current player of the game.
	 * @param comRes
	 *            The combination result string
	 * @param adjTilePosition
	 *            The adjacent tile position string
	 */
	public boolean placeALakeTileAndDistribute(GameInstance gameObjs, Players playing, String placementString,
			LakeTiles tileInHand) {

		String resultChoice[] = placementString.split(":");

		System.out.println("langth " + resultChoice.length);

		if (resultChoice.length == 1) {
			return false;
		}

		// adjacent tile where to place the tile
		int adjacentTile = Integer.valueOf(resultChoice[0]);

		int positionChoice = getRandomNumber(resultChoice.length - 1) + 1;

		// tile position (L/R/T/B)
		String tilePlace = resultChoice[positionChoice];

		System.out.println(tilePlace);

		int x = 0;
		int y = 0;
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if (gameObjs.GameBoard[i][j] == Integer.parseInt(resultChoice[0])) {
					y = i;
					x = j;
					System.out.println(y + "" + x);
					break;
				}
			}
		}
		if (tilePlace.equals("L")) {
			x = x - 1;
		}
		if (tilePlace.equals("R")) {
			x = x + 1;
		}
		if (tilePlace.equals("T")) {
			y = y - 1;
		}
		if (tilePlace.equals("B")) {
			y = y + 1;
		}

		System.out.println("changed " + y + "" + x);
		gameObjs.GameBoard[y][x] = tileInHand.getTilesId();

		// distribute lantern cards
		PlayGame.distributeLakeTilesPlaying(gameObjs, playing, y, x);
		PlayGame.distributingLakeTilesToRestPlayers(gameObjs, playing, y, x);

		return true;

	}

	/**
	 * The method to exchange lantern card.
	 * 
	 * @param gameObj
	 *            The GameInstance object to update its elements according to
	 *            lantern card exchange.
	 * @param playing
	 *            The current player
	 */
	public void exchageLaternCard(GameInstance gameObj, Players playing) {

		String yourColor = "";
		String gameColor = "";

		System.out.println();
		System.out.println("Number of your Favor " + "tokens present currently: = " + playing.getPlayerFavorToken());
		System.out.println();
		System.out.println("Your Lantern Cards Details ");
		System.out.println();
		System.out.println("Number of Black Lantern Card :" + playing.getPlayerBlackLanternCardCount());

		System.out.println("Number of White Lantern Card :" + playing.getPlayerWhiteLanternCardCount());

		System.out.println("Number of Blue Lantern Card :" + playing.getPlayerBlueLanternCardCount());

		System.out.println("Number of Green Lantern Card :" + playing.getPlayerGreenLanternCardCount());

		System.out.println("Number of Orange Lantern Card :" + playing.getPlayerOrangeLanternCardCount());

		System.out.println("Number of Purple Lantern Card :" + playing.getPlayerPurpleLanternCardCount());

		System.out.println("Number of Red Lantern Card :" + playing.getPlayerRedLanternCardCount());

		System.out.println("Number of favor tokens in game = " + gameObj.getGameFavorToken());

		System.out.println();

		System.out.println("Game Lantern Cards Details ");

		System.out.println();

		System.out.println("Number of Black Lantern Card :" + gameObj.getGameBlackLanternCardCount());
		System.out.println("Number of White Lantern Card :" + gameObj.getGameWhiteLanternCardCount());
		System.out.println("Number of Blue Lantern Card :" + gameObj.getGameBlueLanternCardCount());
		System.out.println("Number of Green Lantern Card :" + gameObj.getGameGreenLanternCardCount());
		System.out.println("Number of Orange Lantern Card :" + gameObj.getGameOrangeLanternCardCount());
		System.out.println("Number of Purple Lantern Card :" + gameObj.getGamePurpleLanternCardCount());
		System.out.println("Number of Red Lantern Card :" + gameObj.getGameRedLanternCardCount());

		System.out.println("Which your color do you want to give: ");

		Vector<String> playerColorToExchange = new Vector<String>();

		if (playing.getPlayerBlackLanternCardCount() > 0) {
			playerColorToExchange.add("Black");
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			playerColorToExchange.add("White");
		}
		if (playing.getPlayerBlueLanternCardCount() > 0) {
			playerColorToExchange.add("Blue");
		}
		if (playing.getPlayerGreenLanternCardCount() > 0) {
			playerColorToExchange.add("Green");
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			playerColorToExchange.add("Orange");
		}
		if (playing.getPlayerRedLanternCardCount() > 0) {
			playerColorToExchange.add("Red");
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			playerColorToExchange.add("Purple");
		}

		int playerColorChoice = getRandomNumber(playerColorToExchange.size());

		yourColor = playerColorToExchange.get(playerColorChoice);

		System.out.println("Which Game color do you want to get: ");
		Vector<String> gameColorToExchange = new Vector<String>();

		if (gameObj.getGameBlackLanternCardCount() > 0) {
			gameColorToExchange.add("Black");
		}
		if (gameObj.getGameWhiteLanternCardCount() > 0) {
			gameColorToExchange.add("White");
		}
		if (gameObj.getGameBlueLanternCardCount() > 0) {
			gameColorToExchange.add("Blue");
		}
		if (gameObj.getGameGreenLanternCardCount() > 0) {
			gameColorToExchange.add("Green");
		}
		if (gameObj.getGameOrangeLanternCardCount() > 0) {
			gameColorToExchange.add("Orange");
		}
		if (gameObj.getGameRedLanternCardCount() > 0) {
			gameColorToExchange.add("Red");
		}
		if (gameObj.getGamePurpleLanternCardCount() > 0) {
			gameColorToExchange.add("Purple");
		}

		int gameColorChoice = getRandomNumber(gameColorToExchange.size());

		gameColor = gameColorToExchange.get(gameColorChoice);

		playing.setPlayerFavorToken(playing.getPlayerFavorToken() - 2);

		gameObj.setGameFavorToken(gameObj.getGameFavorToken() + 2);

		// method to remove lantern card color from player and add to game
		playing.playerColorAugment(yourColor, gameColor);

		// method to remove lantern card color from game and add to player
		gameObj.gameColorAugment(gameColor, yourColor);

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

			// set random choice
			int checkChoice = getRandomNumber(2);

			checkChoice = checkChoice + 1;

			switch (checkChoice) {

			case 1:
				// make dedication
				this.makeADedication(gameObj, opt, playing);

				// remove dedication substring from opt string
				opt = PlayGame.removeSubstring(new String("type1"), opt);
				opt = PlayGame.removeSubstring(new String("type2"), opt);
				opt = PlayGame.removeSubstring(new String("type3"), opt);

				break;

			case 2:
				// discard the lantern cards.
				this.discardLanternCards(gameObj, playing);
				break;

			default:
				break;
			}
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

		LakeTiles tileHolded;
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

		// get random tile from hand
		int tileInHandID = getRandomNumber(lakeTiles.size());

		tileHolded = lakeTiles.get(tileInHandID);

		System.out.println("Random Player Lake Tile choice: " + tileHolded.getTilesId());

		return tileHolded;
	}

	/**
	 * The method to discard the specified Lantern cards of the given player of
	 * the game. It will ask for the lantern cards to be discarded and discard
	 * the selected cards
	 * 
	 * @see getLanterCardColorRemoval
	 * @param gameObj
	 *            The GameInstance object for updating the lantern cards of the
	 *            game board.
	 * @param playing
	 */
	public void discardLanternCards(GameInstance gameObj, Players playing) {

		System.out.println("-------Lantern Cards Player Holds-----");

		System.out.println();

		System.out.println("Red Lantern Cards Left: " + playing.getPlayerRedLanternCardCount());
		System.out.println("Blue Lantern Cards Left: " + playing.getPlayerBlueLanternCardCount());
		System.out.println("Black Lantern Cards Left: " + playing.getPlayerBlackLanternCardCount());
		System.out.println("Green Lantern Cards Left: " + playing.getPlayerGreenLanternCardCount());
		System.out.println("Orange Lantern Cards Left: " + playing.getPlayerOrangeLanternCardCount());
		System.out.println("Purple Lantern Cards Left: " + playing.getPlayerPurpleLanternCardCount());
		System.out.println("White Lantern Cards Left: " + playing.getPlayerWhiteLanternCardCount());
		System.out.println();

		// get colors that can be discarded
		Vector<String> discardColorChoices = availableDiscardCardColors(playing);

		boolean loopCheck = true;

		while (loopCheck) {

			System.out.println("Lantern cards count: " + playing.getLanternCardCount());

			System.out.println("Which color you want to discard?");

			System.out.println("Black/Blue/White/Green/Orange/Red/Purple");

			System.out.println();

			System.out.println("Please enter your choice:");

			System.out.println(discardColorChoices.size());

			int colorId = getRandomNumber(discardColorChoices.size());

			String deleteColor = discardColorChoices.get(colorId);

			discardColorChoices.remove(colorId);

			// get card count of lantern card color to be deleted
			int colorCount = getLanternCardColorCount(playing, deleteColor);

			System.out.println("Please enter the number of" + " cards you want to discard:");

			boolean removeResult = PlayGame.getLanterCardColorRemoval(playing, deleteColor, gameObj, colorCount);

			System.out.println(removeResult);

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
	 * Method to count the lantern card for the deletion
	 * 
	 * @param playing
	 *            The current player of the game
	 * @param deleteColor
	 *            The color string for deletion
	 */
	private int getLanternCardColorCount(Players playing, String deleteColor) {
		int colorCount = 0;

		if (deleteColor.equalsIgnoreCase("Red")) {
			colorCount = playing.getPlayerRedLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Black")) {
			colorCount = playing.getPlayerBlackLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Blue")) {
			colorCount = playing.getPlayerBlueLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Orange")) {
			colorCount = playing.getPlayerOrangeLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Green")) {
			colorCount = playing.getPlayerGreenLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("White")) {
			colorCount = playing.getPlayerWhiteLanternCardCount();
		} else if (deleteColor.equalsIgnoreCase("Purple")) {
			colorCount = playing.getPlayerPurpleLanternCardCount();
		}

		return colorCount;
	}

	/**
	 * Method to get the list of available lantern cards to be discarded
	 * 
	 * @param playing
	 *            The current player of the game
	 */
	private Vector<String> availableDiscardCardColors(Players playing) {
		Vector<String> discardColorChoices = new Vector<String>();

		if (playing.getPlayerRedLanternCardCount() > 0) {
			discardColorChoices.add("Red");
		}
		if (playing.getPlayerBlueLanternCardCount() > 0) {
			discardColorChoices.add("Blue");
		}
		if (playing.getPlayerBlackLanternCardCount() > 0) {
			discardColorChoices.add("Black");
		}
		if (playing.getPlayerGreenLanternCardCount() > 0) {
			discardColorChoices.add("Green");
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			discardColorChoices.add("Orange");
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			discardColorChoices.add("Purple");
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			discardColorChoices.add("White");
		}
		return discardColorChoices;
	}

	/**
	 * The method to make a dedication by the player passed as a parameter.
	 * 
	 * @param gameObj
	 *            The GameInstance object to update its elements according to
	 *            the dedications.
	 * @param opt
	 *            The string to choose the type of dedication.
	 */
	public void makeADedication(GameInstance gameObj, String opt, Players playing) {

		if (opt.contains("type1")) {
			System.out.println("1 - Type 1 Dedication (Four cards of unique color.)");
		}
		if (opt.contains("type2")) {
			System.out.println("2 - Type 2 Dedication (Three pairs of unique color)");
		}
		if (opt.contains("type3")) {
			System.out.println("3 - Type 3 Dedication (Seven cards of different color.)");
		}

		// select one of the three dedication types
		int selectionType = getDedicationTypeChoice(opt);

		switch (selectionType) {

		case 1:
			if (opt.contains("type1")) {

				Vector<String> type1ColorOptions = new Vector<String>();

				System.out.println("Your Lantern Cards Details ");

				if (playing.getPlayerBlackLanternCardCount() > 3) {
					System.out.println("Number of Black Lantern Card :" + playing.getPlayerBlackLanternCardCount());
					type1ColorOptions.add("Black");
				}
				if (playing.getPlayerWhiteLanternCardCount() > 3) {
					System.out.println("Number of White Lantern Card :" + playing.getPlayerWhiteLanternCardCount());
					type1ColorOptions.add("White");
				}
				if (playing.getPlayerBlueLanternCardCount() > 3) {
					System.out.println("Number of Blue Lantern Card :" + playing.getPlayerBlueLanternCardCount());
					type1ColorOptions.add("Blue");
				}
				if (playing.getPlayerGreenLanternCardCount() > 3) {
					System.out.println("Number of Green Lantern Card :" + playing.getPlayerGreenLanternCardCount());
					type1ColorOptions.add("Green");
				}
				if (playing.getPlayerOrangeLanternCardCount() > 3) {
					System.out.println("Number of Orange Lantern Card :" + playing.getPlayerOrangeLanternCardCount());
					type1ColorOptions.add("Orange");
				}
				if (playing.getPlayerPurpleLanternCardCount() > 3) {
					System.out.println("Number of Purple Lantern Card :" + playing.getPlayerPurpleLanternCardCount());
					type1ColorOptions.add("Purple");
				}
				if (playing.getPlayerRedLanternCardCount() > 3) {
					System.out.println("Number of Red Lantern Card :" + playing.getPlayerRedLanternCardCount());
					type1ColorOptions.add("Red");
				}

				int type1Color = getRandomNumber(type1ColorOptions.size());

				System.out.println("Input color of lantern card: " + type1ColorOptions.get(type1Color));

				String color1 = type1ColorOptions.get(type1Color);

				boolean type1Val = PlayGame.getDedicationType1ColorValidationAndRemoval(playing, color1, gameObj);
				if (type1Val) {

					DedicationTokens dedicationObj = gameObj.getDedicationTokens();

					Vector<Integer> dedicationVal = dedicationObj.getDedicationTokenFour();

					if (dedicationVal.size() > 0) {

						int dedicationValue = dedicationVal.remove(0);

						DedicationTokens playerDedicationObj = playing.getDedicationTokens();

						playerDedicationObj.getDedicationTokenFour().add(dedicationValue);

						if (dedicationVal.isEmpty()) {
							gameObj.setNextDedicationTokenFour(-1);
						} else {
							gameObj.setNextDedicationTokenFour(dedicationVal.firstElement());

						}
					} else {

						Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();

						if (genDedicationVal.size() > 0) {

							int genericValue = genDedicationVal.remove(0);

							DedicationTokens playerDedicationObj = playing.getDedicationTokens();

							playerDedicationObj.getGenericDedicationTokens().add(genericValue);
						}
					}
				} else {
					System.out.println("Wrong color choice");
				}
			}
			break;

		case 2:
			if (opt.contains("type2")) {

				Vector<String> type2ColorOptions = new Vector<String>();
				System.out.println("Your Lantern Cards Details ");
				if (playing.getPlayerBlackLanternCardCount() > 1) {

					System.out.println("Number of Black Lantern Card :" + playing.getPlayerBlackLanternCardCount());

					type2ColorOptions.add("Black");
				}
				if (playing.getPlayerWhiteLanternCardCount() > 1) {

					System.out.println("Number of White Lantern Card :" + playing.getPlayerWhiteLanternCardCount());

					type2ColorOptions.add("White");
				}
				if (playing.getPlayerBlueLanternCardCount() > 1) {

					System.out.println("Number of Blue Lantern Card :" + playing.getPlayerBlueLanternCardCount());

					type2ColorOptions.add("Blue");
				}
				if (playing.getPlayerGreenLanternCardCount() > 1) {

					System.out.println("Number of Green Lantern Card :" + playing.getPlayerGreenLanternCardCount());

					type2ColorOptions.add("Green");
				}
				if (playing.getPlayerOrangeLanternCardCount() > 1) {

					System.out.println("Number of Orange Lantern Card :" + playing.getPlayerOrangeLanternCardCount());

					type2ColorOptions.add("Orange");
				}
				if (playing.getPlayerPurpleLanternCardCount() > 1) {

					System.out.println("Number of Purple Lantern Card :" + playing.getPlayerPurpleLanternCardCount());

					type2ColorOptions.add("Purple");
				}
				if (playing.getPlayerRedLanternCardCount() > 1) {

					System.out.println("Number of Red Lantern Card :" + playing.getPlayerRedLanternCardCount());

					type2ColorOptions.add("Red");
				}

				int type2Color = getRandomNumber(type2ColorOptions.size());

				System.out.println("Input first color of lantern card: " + type2ColorOptions.get(type2Color));

				String colorOne = type2ColorOptions.remove(type2Color);

				boolean type2ValOne = PlayGame.getDedicationType2ColorValidationAndRemoval(playing, colorOne, gameObj);

				type2Color = getRandomNumber(type2ColorOptions.size());

				System.out.println("Input second color of lantern card: " + type2ColorOptions.get(type2Color));

				String colorTwo = type2ColorOptions.remove(type2Color);

				boolean type2ValTwo = PlayGame.getDedicationType2ColorValidationAndRemoval(playing, colorTwo, gameObj);

				type2Color = getRandomNumber(type2ColorOptions.size());

				System.out.println("Input third color of lantern card " + type2ColorOptions.get(type2Color));

				String colorThree = type2ColorOptions.remove(type2Color);

				boolean type2ValThree = PlayGame.getDedicationType2ColorValidationAndRemoval(playing, colorThree,
						gameObj);

				DedicationTokens dedicationObj = gameObj.getDedicationTokens();
				Vector<Integer> dedicationSixVal = dedicationObj.getDedicationTokenSix();
				if (dedicationSixVal.size() > 0) {
					int dedicationSixValue = dedicationSixVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getDedicationTokenSix().add(dedicationSixValue);
					if (dedicationSixVal.isEmpty()) {
						gameObj.setNextDedicationTokenSix(-1);
					} else {
						gameObj.setNextDedicationTokenSix(dedicationSixVal.firstElement());
					}
				} else {
					Vector<Integer> genDedicationVal = dedicationObj.getGenericDedicationTokens();
					if (genDedicationVal.size() > 0) {
						int genericValue = genDedicationVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getGenericDedicationTokens().add(genericValue);
					}
				}

			}
			break;
		case 3:
			if (opt.contains("type3")) {

				System.out.println("Your Lantern Cards Details ");
				if (playing.getPlayerBlackLanternCardCount() > 0) {
					System.out.println("Number of Black Lantern Card :" + playing.getPlayerBlackLanternCardCount());
				}
				if (playing.getPlayerWhiteLanternCardCount() > 0) {
					System.out.println("Number of White Lantern Card :" + playing.getPlayerWhiteLanternCardCount());
				}
				if (playing.getPlayerBlueLanternCardCount() > 0) {
					System.out.println("Number of Blue Lantern Card :" + playing.getPlayerBlueLanternCardCount());
				}
				if (playing.getPlayerGreenLanternCardCount() > 0) {
					System.out.println("Number of Green Lantern Card :" + playing.getPlayerGreenLanternCardCount());
				}
				if (playing.getPlayerOrangeLanternCardCount() > 0) {
					System.out.println("Number of Orange Lantern Card :" + playing.getPlayerOrangeLanternCardCount());
				}
				if (playing.getPlayerPurpleLanternCardCount() > 0) {
					System.out.println("Number of Purple Lantern Card :" + playing.getPlayerPurpleLanternCardCount());
				}
				if (playing.getPlayerRedLanternCardCount() > 0) {
					System.out.println("Number of Red Lantern Card :" + playing.getPlayerRedLanternCardCount());
				}

				boolean type3Val = PlayGame.getDedicationType3ColorValidationAndRemoval(playing, gameObj);
				if (type3Val) {

					DedicationTokens dedicationObj = gameObj.getDedicationTokens();

					Vector<Integer> dedicationSevenVal = dedicationObj.getDedicationTokenSeven();

					if (dedicationSevenVal.size() > 0) {

						int dedicationSevenValue = dedicationSevenVal.remove(0);

						DedicationTokens playerDedicationObj = playing.getDedicationTokens();

						playerDedicationObj.getDedicationTokenSeven().add(dedicationSevenValue);

						if (dedicationSevenVal.isEmpty()) {
							gameObj.setNextDedicationTokenSeven(-1);
						} else {
							gameObj.setNextDedicationTokenSeven(dedicationSevenVal.firstElement());
						}
					} else {

						Vector<Integer> genGenericVal = dedicationObj

						.getGenericDedicationTokens();

						if (genGenericVal.size() > 0) {

							int genericValue = genGenericVal.remove(0);

							DedicationTokens playerDedicationObj = playing.getDedicationTokens();

							playerDedicationObj.getGenericDedicationTokens().add(genericValue);
						}
					}
				}

			}
			break;
		default:
			break;
		}

	}

	/**
	 * Method to get the dedication type choice
	 * 
	 * @param opt
	 *            The string option for the type of dedication
	 * @return The selection
	 */
	private int getDedicationTypeChoice(String opt) {

		int selectionType = 0;

		// if only type 1 is available
		if (opt.contains("type1") && !opt.contains("type2") && !opt.contains("type3")) {
			selectionType = 1;
		}

		// if only type 2 is available
		if (!opt.contains("type1") && opt.contains("type2") && !opt.contains("type3")) {
			selectionType = 2;
		}

		// if only type 3 is available
		if (!opt.contains("type1") && !opt.contains("type2") && opt.contains("type3")) {
			selectionType = 3;
		}

		// if only type 1 and type 2 is available
		if (opt.contains("type1") && opt.contains("type2") && !opt.contains("type3")) {
			int choice = getRandomNumber(2);

			if (choice == 0) {
				selectionType = 1;
			} else {
				selectionType = 2;
			}

		}

		// if only type 1 and type 3 is available
		if (opt.contains("type1") && !opt.contains("type2") && opt.contains("type3")) {
			int choice = getRandomNumber(2);

			if (choice == 0) {
				selectionType = 1;
			} else {
				selectionType = 3;
			}

		}

		// if only type 2 and type 3 is available
		if (!opt.contains("type1") && opt.contains("type2") && opt.contains("type3")) {
			int choice = getRandomNumber(2);

			if (choice == 0) {
				selectionType = 2;
			} else {
				selectionType = 3;
			}

		}

		// if all options are available
		if (opt.contains("type1") && opt.contains("type2") && opt.contains("type3")) {
			selectionType = getRandomNumber(3) + 1;
		}
		return selectionType;
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

		// Rotate lake tile
		System.out.print("Do you want to rotate tile by" + " 90/180/270 clockwise or 0 degree: ");

		String rotation[] = { "0", "90", "180", "270" };

		int rotationChoice = getRandomNumber(rotation.length);

		String degreeRotation = rotation[rotationChoice];

		if (degreeRotation.equals("90")) {

			tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

			System.out.println();

			new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

		} else if (degreeRotation.equals("180")) {

			tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

			System.out.println();

			new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

		} else if (degreeRotation.equals("270")) {

			tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, degreeRotation);

			System.out.println();

			new LanternGameView().displayLakeTiles(gameObj, tileInHand.getTilesId());

		} else if (degreeRotation.equals("0")) {
			// do nothing
		}

		return tileInHand;
	}

	/**
	 * This method gets a random number.
	 * 
	 * @param number:
	 *            	int type
	 * @return random: 
	 * 				int type
	 */
	private int getRandomNumber(int number) {

		Random randomNumbers = new Random(0);

		int random = randomNumbers.nextInt(number); // number between 0 to 3

		return random;
	}

}
