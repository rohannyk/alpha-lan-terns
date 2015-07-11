/**
 * 
 */
package edu.concordia.app.model;

/**
 * This class contain the configuration parameters of Lantern Game. 
 * @author Team E
 *
 */
public class GameConfiguration {

	/**
	 * The number of players of the game.
	 */
	public final int NUM_OF_PLAYERS;
	public final int NUM_OF_TILES_IN_DRAW_STACK;
	public final int NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR;
	public final int TOTAL_TILE_CARDS = 36;
	public final int DEFAULT_TOTAL_LANTERN_CARDS = 56;
	
	/**
	 * Default constructor for GameConfiguration class.
	 */
	public GameConfiguration() {
		NUM_OF_PLAYERS = 4;
		NUM_OF_TILES_IN_DRAW_STACK = calculateNoOfDrawTile(NUM_OF_PLAYERS);
		NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR = calculateNoOfLanternCardsEveryColor(NUM_OF_PLAYERS);
	}

	/**
	 * @param numberOfPlayers The number of players of the game. 
	 */
	public GameConfiguration(int numberOfPlayers) {
		
		if(!(numberOfPlayers >= 2 && numberOfPlayers <= 4)){
			System.out.println("Invalid no of players. Reset value to 4.");
			numberOfPlayers = 4;
		}
		
		NUM_OF_PLAYERS = numberOfPlayers;
		NUM_OF_TILES_IN_DRAW_STACK = calculateNoOfDrawTile(numberOfPlayers);
		NUM_OF_LANTERN_CARDS_FOR_EVERY_COLOR = calculateNoOfLanternCardsEveryColor(numberOfPlayers);
	}
	
	/**
	 * This method calculates the no of tiles in the draw stack
	 * based on the no of players.
	 * This calculation is done by the rules of of the game.
	 * 
	 * @param noOfPlayers The no of players in the game.
	 * @return Returns the no of tiles in the draw stack.
	 */
	
	private int calculateNoOfDrawTile(int numberOfPlayers){
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
	 * This method calculates the no of lantern cards for every color.
	 * based on the no of players.
	 * This calculation is done by the rules of of the game.
	 * 
	 * @param noOfPlayers The no of players in the game.
	 * @return Returns the no of lantern cards for every color.
	 */
	private int calculateNoOfLanternCardsEveryColor(int numberOfPlayers){
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
