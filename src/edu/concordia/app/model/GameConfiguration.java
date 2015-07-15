/**
 * 
 */
package edu.concordia.app.model;

import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;

/**
 * This class contain the configuration parameters of Lantern Game.
 * 
 * @author Team E
 *
 */
public class GameConfiguration {

	/**
	 * The number of players of the game.
	 */
	public final int NUM_OF_PLAYERS;// no of players playing game

	public final int NUM_OF_TILES_IN_DRAW_STACK;// no of tiles in drawstack

	public final int TOTAL_TILE_CARDS = 36;// total tiles

	public final int START_TILE = 1;// start tile to indicate which players starts

	public final int NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;// total no of lantern cards

	public final int DEFAULT_TOTAL_LANTERN_CARDS = 56;

	protected  int GAME_BLACK_LANTERN_CARDS = 8;// black lantern cards

	protected  int GAME_BLUE_LANTERN_CARDS = 8;// blue lantern cards

	protected  int GAME_GREEN_LANTERN_CARDS = 8;// green lantern cards

	protected  int GAME_ORANGE_LANTERN_CARDS = 8;// ornage lantern cards

	protected  int GAME_PURPLE_LANTERN_CARDS = 8;//purple lantern cards

	protected  int GAME_RED_LANTERN_CARDS = 8;// red lantern cards

	protected  int GAME_WHITE_LANTERN_CARDS = 8;// white lantern cards

	protected static int GAME_GENERIC_DEDICATED_TOKEN = 3;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_FOUR = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_SIX = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_SEVEN = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN = 27;

	// protected int[] gameDedicatedTokenFour = {8,7,6,6,5,4,3,2,1};
	//
	// protected int[] gameDedicatedTokenSix = {9,8,7,6,5,4,3,2,1};
	//
	// protected int[] gameDedicatedTokenSeven = {10,9,8,8,7,6,6,5,4,3};
	//
	// protected int[] gameGenericDedicatedToken = {4,3,2};

	public final int TOTAL_FAVOR_TOKEN = 20;// toal favor tokens

	public final Vector<LakeTiles> GAME_TOTAL_TILE_SUITE;

	protected final DedicationTokens DEDICATION_TOKENS;
	
	protected final int PLAYER_LAKE_TILE_DEAL_SIZE = 3;

	/**
	 * Default constructor for GameConfiguration class.
	 */
	public GameConfiguration() {
		NUM_OF_PLAYERS = 4;
		NUM_OF_TILES_IN_DRAW_STACK = calculateNoOfDrawTile(NUM_OF_PLAYERS);
		NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR = calculateNoOfLanternCardsEveryColor(NUM_OF_PLAYERS);
		GAME_TOTAL_TILE_SUITE = initializeDefaultTileSuite();
		DEDICATION_TOKENS = initializeDedicationTokens(NUM_OF_PLAYERS);
	}

	/**
	 * @param numberOfPlayers
	 *            The number of players of the game.
	 */
	public GameConfiguration(int numberOfPlayers) {

		if (!(numberOfPlayers >= 2 && numberOfPlayers <= 4)) {
			System.out.println("Invalid no of players. Reset value to 4.");
			numberOfPlayers = 4;
		}

		NUM_OF_PLAYERS = numberOfPlayers;
		NUM_OF_TILES_IN_DRAW_STACK = calculateNoOfDrawTile(numberOfPlayers);
		NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR = calculateNoOfLanternCardsEveryColor(numberOfPlayers);
		GAME_TOTAL_TILE_SUITE = initializeDefaultTileSuite();
		DEDICATION_TOKENS = initializeDedicationTokens(numberOfPlayers);
	}

	/**
	 * An enum type having the seven colors
	 */
	public enum TileColor {
		RED, ORANGE, PURPLE, WHITE, BLUE, BLACK, GREEN;
	}

	/**
	 * 
	 */
	private Vector<LakeTiles> initializeDefaultTileSuite() {
		Vector<LakeTiles> tileVector = new Vector<LakeTiles>();

		tileVector.add(new LakeTiles(0, TileColor.RED.toString(),
				TileColor.BLUE.toString(), TileColor.WHITE.toString(),
				TileColor.BLACK.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(1, TileColor.GREEN.toString(),
				TileColor.ORANGE.toString(), TileColor.BLUE.toString(),
				TileColor.BLACK.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(2, TileColor.GREEN.toString(), TileColor.RED
				.toString(), TileColor.GREEN.toString(), TileColor.GREEN
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(3, TileColor.RED.toString(), TileColor.PURPLE
				.toString(), TileColor.BLACK.toString(), TileColor.GREEN
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(4, TileColor.WHITE.toString(),
				TileColor.BLACK.toString(), TileColor.BLACK.toString(),
				TileColor.ORANGE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(5, TileColor.ORANGE.toString(),
				TileColor.ORANGE.toString(), TileColor.PURPLE.toString(),
				TileColor.ORANGE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(6, TileColor.RED.toString(), TileColor.PURPLE
				.toString(), TileColor.GREEN.toString(), TileColor.BLUE
				.toString(), false, 0, 0, 0, 0));

		tileVector.add(new LakeTiles(7, TileColor.WHITE.toString(),
				TileColor.GREEN.toString(), TileColor.GREEN.toString(),
				TileColor.ORANGE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(8, TileColor.WHITE.toString(), TileColor.BLUE
				.toString(), TileColor.BLUE.toString(), TileColor.BLACK
				.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(9, TileColor.BLUE.toString(), TileColor.RED
				.toString(), TileColor.PURPLE.toString(), TileColor.WHITE
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(10, TileColor.RED.toString(), TileColor.RED
				.toString(), TileColor.GREEN.toString(), TileColor.BLACK
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(11, TileColor.GREEN.toString(),
				TileColor.WHITE.toString(), TileColor.RED.toString(),
				TileColor.ORANGE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(12, TileColor.RED.toString(), TileColor.RED
				.toString(), TileColor.GREEN.toString(), TileColor.GREEN
				.toString(), true, 0, 0, 0, 0));

		tileVector.add(new LakeTiles(13, TileColor.BLACK.toString(), TileColor.BLUE
				.toString(), TileColor.PURPLE.toString(), TileColor.BLUE
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(14, TileColor.ORANGE.toString(),
				TileColor.WHITE.toString(), TileColor.PURPLE.toString(),
				TileColor.WHITE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(15, TileColor.GREEN.toString(), TileColor.BLUE
				.toString(), TileColor.BLUE.toString(), TileColor.ORANGE
				.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(16, TileColor.WHITE.toString(),
				TileColor.GREEN.toString(), TileColor.WHITE.toString(),
				TileColor.RED.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(17, TileColor.PURPLE.toString(),
				TileColor.GREEN.toString(), TileColor.PURPLE.toString(),
				TileColor.RED.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(18, TileColor.BLUE.toString(), TileColor.GREEN
				.toString(), TileColor.RED.toString(), TileColor.BLACK
				.toString(), false, 0, 0, 0, 0));
		
		tileVector.add(new LakeTiles(19, TileColor.BLUE.toString(), TileColor.GREEN.toString(), TileColor.BLACK
				.toString(), TileColor.WHITE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(20, TileColor.RED.toString(), TileColor.ORANGE.toString(), TileColor.BLACK
				.toString(), TileColor.RED.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(21, TileColor.ORANGE.toString(), TileColor.ORANGE.toString(), TileColor.BLUE
				.toString(), TileColor.WHITE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(22, TileColor.RED.toString(), TileColor.PURPLE.toString(), TileColor.BLACK
				.toString(), TileColor.BLACK.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(23, TileColor.PURPLE.toString(), TileColor.GREEN.toString(), TileColor.BLUE
				.toString(), TileColor.PURPLE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(24, TileColor.BLUE.toString(), TileColor.BLACK.toString(), TileColor.RED
				.toString(), TileColor.WHITE.toString(), false, 0, 0, 0, 0));
		
		tileVector.add(new LakeTiles(25, TileColor.BLUE.toString(), TileColor.PURPLE.toString(), TileColor.WHITE
				.toString(), TileColor.GREEN.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(26, TileColor.BLACK.toString(), TileColor.WHITE.toString(), TileColor.WHITE
				.toString(), TileColor.BLACK.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(27, TileColor.PURPLE.toString(), TileColor.ORANGE.toString(), TileColor.BLACK
				.toString(), TileColor.WHITE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(28, TileColor.ORANGE.toString(), TileColor.BLUE.toString(), TileColor.BLUE
				.toString(), TileColor.ORANGE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(29, TileColor.BLUE.toString(), TileColor.BLACK.toString(), TileColor.RED
				.toString(), TileColor.WHITE.toString(), true, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(30, TileColor.RED.toString(), TileColor.WHITE.toString(), TileColor.ORANGE
				.toString(), TileColor.PURPLE.toString(), false, 0, 0, 0, 0));
		
		tileVector.add(new LakeTiles(31, TileColor.PURPLE.toString(), TileColor.PURPLE.toString(), TileColor.PURPLE
				.toString(), TileColor.BLACK.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(32, TileColor.BLUE.toString(), TileColor.RED.toString(), TileColor.WHITE
				.toString(), TileColor.ORANGE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(33, TileColor.RED.toString(), TileColor.ORANGE.toString(), TileColor.WHITE
				.toString(), TileColor.BLACK.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(34, TileColor.ORANGE.toString(), TileColor.RED.toString(), TileColor.BLACK
				.toString(), TileColor.PURPLE.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(35, TileColor.BLUE.toString(), TileColor.ORANGE.toString(), TileColor.PURPLE
				.toString(), TileColor.BLACK.toString(), false, 0, 0, 0, 0));
		tileVector.add(new LakeTiles(36, TileColor.WHITE.toString(), TileColor.BLUE.toString(), TileColor.GREEN
				.toString(), TileColor.PURPLE.toString(), false, 0, 0, 0, 0));
		
		return tileVector;
	}

	private DedicationTokens initializeDedicationTokens(int numberOfPlayers) {

//		DedicationTokens dedicationTokens = new DedicationTokens();

		Vector<Integer> dedicationTokenFour = new Vector<Integer>();

		Vector<Integer> dedicationTokenSix = new Vector<Integer>();

		Vector<Integer> dedicationTokenSeven = new Vector<Integer>();

		Vector<Integer> genericDedicationTokens = new Vector<Integer>();

		switch (numberOfPlayers) {

		case 2:

			// dedication token four
			dedicationTokenFour.add(8);
			dedicationTokenFour.add(7);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(4);
			
//			dedicationTokenFour.add(new Tokens(8, 0));
//			dedicationTokenFour.add(new Tokens(7, 0));
//			dedicationTokenFour.add(new Tokens(6, 0));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(4, 0));

			// dedication token six
			dedicationTokenSix.add(9);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(5);
			dedicationTokenSix.add(5);

			// dedication token seven
			dedicationTokenSeven.add(10);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(6);
			dedicationTokenSeven.add(5);
			
//			dedicationTokenSeven.add(new Tokens(10, 0));
//			dedicationTokenSeven.add(new Tokens(9, 0));
//			dedicationTokenSeven.add(new Tokens(8, 0));
//			dedicationTokenSeven.add(new Tokens(7, 0));
//			dedicationTokenSeven.add(new Tokens(6, 0));
//			dedicationTokenSeven.add(new Tokens(5, 0));

			// generic dedication token
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			
//			genericDedicationTokens.add(new Tokens(4, 0));
//			genericDedicationTokens.add(new Tokens(4, 0));
//			genericDedicationTokens.add(new Tokens(4, 0));

			break;

		case 3:

			// dedication token four
			dedicationTokenFour.add(8);
			dedicationTokenFour.add(7);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(4);
			
//			dedicationTokenFour.add(new Tokens(8, 0));
//			dedicationTokenFour.add(new Tokens(7, 0));
//			dedicationTokenFour.add(new Tokens(6, 3));
//			dedicationTokenFour.add(new Tokens(6, 0));
//			dedicationTokenFour.add(new Tokens(5, 3));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(4, 0));

			// dedication token six
			dedicationTokenSix.add(9);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(5);
			dedicationTokenSix.add(5);
			
//			dedicationTokenSix.add(new Tokens(9, 0));
//			dedicationTokenSix.add(new Tokens(8, 0));
//			dedicationTokenSix.add(new Tokens(7, 3));
//			dedicationTokenSix.add(new Tokens(7, 0));
//			dedicationTokenSix.add(new Tokens(6, 3));
//			dedicationTokenSix.add(new Tokens(6, 0));
//			dedicationTokenSix.add(new Tokens(5, 0));
//			dedicationTokenSix.add(new Tokens(5, 0));

			// dedication token seven
			dedicationTokenSeven.add(10);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(6);
			dedicationTokenSeven.add(5);
			
//			dedicationTokenSeven.add(new Tokens(10, 0));
//			dedicationTokenSeven.add(new Tokens(9, 0));
//			dedicationTokenSeven.add(new Tokens(8, 3));
//			dedicationTokenSeven.add(new Tokens(8, 0));
//			dedicationTokenSeven.add(new Tokens(7, 3));
//			dedicationTokenSeven.add(new Tokens(7, 0));
//			dedicationTokenSeven.add(new Tokens(6, 0));
//			dedicationTokenSeven.add(new Tokens(5, 0));

			// generic dedication token
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);

			break;

		case 4:

			// dedication token four
			
			dedicationTokenFour.add(8);
			dedicationTokenFour.add(7);
			dedicationTokenFour.add(7);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(4);
			
//			dedicationTokenFour.add(new Tokens(8, 0));
//			dedicationTokenFour.add(new Tokens(7, 4));
//			dedicationTokenFour.add(new Tokens(7, 0));
//			dedicationTokenFour.add(new Tokens(6, 3));
//			dedicationTokenFour.add(new Tokens(6, 0));
//			dedicationTokenFour.add(new Tokens(5, 3));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(5, 0));
//			dedicationTokenFour.add(new Tokens(4, 0));

			// dedication token six
			dedicationTokenSix.add(9);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(5);
			dedicationTokenSix.add(5);
			
//			dedicationTokenSix.add(new Tokens(9, 0));
//			dedicationTokenSix.add(new Tokens(8, 4));
//			dedicationTokenSix.add(new Tokens(8, 0));
//			dedicationTokenSix.add(new Tokens(7, 3));
//			dedicationTokenSix.add(new Tokens(7, 0));
//			dedicationTokenSix.add(new Tokens(6, 3));
//			dedicationTokenSix.add(new Tokens(6, 0));
//			dedicationTokenSix.add(new Tokens(5, 0));
//			dedicationTokenSix.add(new Tokens(5, 0));

			// dedication token seven
			dedicationTokenSeven.add(10);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(6);
			dedicationTokenSeven.add(5);
			
//			dedicationTokenSeven.add(new Tokens(10, 0));
//			dedicationTokenSeven.add(new Tokens(9, 4));
//			dedicationTokenSeven.add(new Tokens(9, 0));
//			dedicationTokenSeven.add(new Tokens(8, 3));
//			dedicationTokenSeven.add(new Tokens(8, 0));
//			dedicationTokenSeven.add(new Tokens(7, 3));
//			dedicationTokenSeven.add(new Tokens(7, 0));
//			dedicationTokenSeven.add(new Tokens(6, 0));
//			dedicationTokenSeven.add(new Tokens(5, 0));

			// generic dedication token
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);

			break;

		default:
			break;
		}
		
		DedicationTokens dedicationTokens = new DedicationTokens(dedicationTokenFour, dedicationTokenSix, 
				dedicationTokenSeven, genericDedicationTokens);
		
		

//		dedicationTokens.setDedicationTokenFour(dedicationTokenFour);
//		dedicationTokens.setDedicationTokenSix(dedicationTokenSix);
//		dedicationTokens.setDedicationTokenSeven(dedicationTokenSeven);
//		dedicationTokens.setGenericDedicationTokens(genericDedicationTokens);

		return dedicationTokens;
	}

	/**
	 * This method calculates the no of tiles in the draw stack based on the no
	 * of players. This calculation is done by the rules of of the game.
	 * 
	 * @param noOfPlayers
	 *            The no of players in the game.
	 * @return Returns the no of tiles in the draw stack.
	 */
	public int noOfDrawTileInStack = 0;
	private int calculateNoOfDrawTile(int numberOfPlayers) {
		int noOfDrawTileInStack = 0;
		switch (numberOfPlayers) {
		case 2:
			noOfDrawTileInStack = 16;
			break;

		case 3:
			noOfDrawTileInStack = 18;
			break;

		case 4:
			noOfDrawTileInStack = 20;
			break;

		default:
			noOfDrawTileInStack = 20;
			break;
		}

		return noOfDrawTileInStack;
	}

	/**
	 * This method calculates the no of lantern cards for every color. based on
	 * the no of players. This calculation is done by the rules of of the game.
	 * 
	 * @param noOfPlayers
	 *            The no of players in the game.
	 * @return Returns the no of lantern cards for every color.
	 */
	private int calculateNoOfLanternCardsEveryColor(int numberOfPlayers) {
		int noOfLanternCards = 0;
		switch (numberOfPlayers) {
		case 2:
			noOfLanternCards = 5;
			break;

		case 3:
			noOfLanternCards = 7;
			break;

		case 4:
			noOfLanternCards = 8;
			break;

		default:
			noOfLanternCards = 8;
			break;
		}

		return noOfLanternCards;
	}

}