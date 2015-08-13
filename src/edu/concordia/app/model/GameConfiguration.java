/**
 * 
 */
package edu.concordia.app.model;

import java.io.Serializable;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;

/**
 * This class contains the configuration parameters of Lantern Game.
 * 
 * @author Team E
 *
 */
public class GameConfiguration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -433418405543959852L;

	/**
	 * The number of players of the game.
	 */
	public final int NUM_OF_PLAYERS;

	/**
	 * The no of tiles in drawstack.
	 */
	public final int NUM_OF_TILES_IN_DRAW_STACK;

	/**
	 * Total number of tiles cards except start lake tile. 
	 */
	public final int TOTAL_TILE_CARDS = 36;

	/**
	 * The start tile to indicate which players starts
	 */
	public final int START_TILE = 1;

	/**
	 * The total no of lantern cards for every color.
	 */
	public final int NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;

	/**
	 * The default total number of lantern cards in the game.
	 */
	public final int DEFAULT_TOTAL_LANTERN_CARDS = 56;

	/**
	 * The black lantern cards of game.
	 */
	protected  int GAME_BLACK_LANTERN_CARDS = 8;

	/**
	 * The blue lantern cards of game.
	 */
	protected  int GAME_BLUE_LANTERN_CARDS = 8;

	/**
	 * The green lantern cards  of game.
	 */
	protected  int GAME_GREEN_LANTERN_CARDS = 8;

	/**
	 * The orange lantern cards of game.
	 */
	protected  int GAME_ORANGE_LANTERN_CARDS = 8;

	/**
	 * The purple lantern cards of game.
	 */
	protected  int GAME_PURPLE_LANTERN_CARDS = 8;

	/**
	 * The red lantern cards of game.
	 */
	protected  int GAME_RED_LANTERN_CARDS = 8;

	/**
	 * The white lantern cards of game.
	 */
	protected  int GAME_WHITE_LANTERN_CARDS = 8;

	protected static int GAME_GENERIC_DEDICATED_TOKEN = 3;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_FOUR = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_SIX = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN_SEVEN = 9;

	protected static int GAME_TOTAL_NO_DEDICATED_TOKEN = 27;

	/**
	 * The total favor tokens of the game
	 */
	public final int TOTAL_FAVOR_TOKEN = 20;

	/**
	 * The total lake tiles of the game.
	 */
	public final Vector<LakeTiles> GAME_TOTAL_TILE_SUITE;

	/**
	 * The dedication tokens.
	 */
	protected final DedicationTokens DEDICATION_TOKENS;
	
	/**
	 * The deal size of cards given to each player.
	 */
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
	 * @param numberOfPlayers: The number of players of the game.
	 * This constructor will set the game configuration according to number of players.
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
	 * The enum type having the seven colors for Lantern Cards.
	 */
	public enum TileColor {
		RED, ORANGE, PURPLE, WHITE, BLUE, BLACK, GREEN;
	}

	/**
	 * @return The vector containing 36 lake tiles and one start lake tile.
	 * This method initializes the lake tile suit containing 36 lake tiles and 
	 * one start lake tile; with their respective colors, platform value
	 * and adjacent lake tile id information.
	 * Creates Vector<LakeTiles> for the Lake Tiles
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

	/**
	 * @param numberOfPlayers The number of players in the game.
	 * @return The dedication tokens according to game rules.
	 * This method initializes and returns the DedicationTokens object 
	 * according to the game rules, depending on the number of players.
	 * Separates Dedication Tokens by type into 3 groups.
	 * Arranges each group in descending order of value.
	 * 4 Players: uses all tokens.
	 * 3 Players: removes tokens with 4 dots.
	 * 2 Players: removes tokens with 3 or 4 dots.
	 */
	public DedicationTokens initializeDedicationTokens(int numberOfPlayers) {

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
			
			// generic dedication token
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			
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
			
			// dedication token six
			dedicationTokenSix.add(9);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(5);
			dedicationTokenSix.add(5);
			
			// dedication token seven
			dedicationTokenSeven.add(10);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(6);
			dedicationTokenSeven.add(5);
			
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
		
		return dedicationTokens;
	}

	/**
	 * This method calculates the no of tiles in the draw stack based on the number
	 * of players. This calculation is done by the rules of the game.
	 * Creates a draw pile of Lake Tiles. The number of tiles in the stack depends 
	 * on player count:
	 * 4 Players: 20 tiles
	 * 3 Players: 18 tiles
	 * 2 Players: 16 tiles
	 * @param noOfPlayers: The no of players in the game.
	 * @return Returns the no of tiles in the draw pile.
	 */

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
	 * This method calculates the no of lantern cards for every color, based on
	 * the number of players. This calculation is done by the rules of of the game.
	 * Separates the Lantern Cards by color into 7 groups.
	 * These groups are collectively called the �supply.�
	 * The number of cards in each stack depends on player count:
	 * 4 Players: 8 cards
	 * 3 Players: 7 cards
	 * 2 Players: 5 cards
	 * @param noOfPlayers: The number of players in the game.
	 * @return Returns the number of lantern cards for every color.
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