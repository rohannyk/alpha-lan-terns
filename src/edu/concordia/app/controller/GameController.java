/**
 * 
 */
package edu.concordia.app.controller;

import java.util.Iterator;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.FileManagement;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * @author Team E
 *
 */
public class GameController {
	
	/**
	 * The instance variable of GameConfiguration class.
	 */
	private GameConfiguration gameConfig;
	
	/**
	 * The instance variable of GameInstance class.
	 */
	private GameInstance gameInstance;
	
	private FileManagement fileManagement;

	/**
	 * The default constructor of GameController class.
	 */
	public GameController() {
		fileManagement = new FileManagement();
	}

	/**
	 * This GameController class constructor will set 
	 * GameConfiguration and GameInstance objects.
	 * @param gameConfig The GameConfiguration class object.
	 * @param gameInstance The GameInstance class object.
	 */
	public GameController(GameConfiguration gameConfig,
			GameInstance gameInstance) {
		
		this.gameConfig = gameConfig;
		this.gameInstance = gameInstance;
		
		fileManagement = new FileManagement(gameConfig, gameInstance);
	}
	
	
	
	/**
	 * This method will check if the file name is empty or not.
	 * If file name is empty, Default file name will be assigned.
	 * @param gameFile The name of the file to save.
	 */
	public void saveGameToFile(String fileName) {

		fileManagement.saveGameToFile(fileName);

	}
	
	/**
	 * This method will check if the file name is empty or not.
	 * @param fileName The name of the file to save.
	 * @return The GameInstance class object will be returned.
	 */
	public GameInstance loadGameFromFile(String fileName) {
		
		return fileManagement.loadGameFromFile(fileName);
				
	}
	

	/**
	 * This method will print the Game state and player state on console.
	 * @param gameObj 
	 */
	public void showTextMode(GameInstance gameObj) {

		System.out.println(" ********* Game Status in Text Mode ********* ");

		System.out.println();

		System.out.println("Number of Players: " + gameObj.getNoOfPlayers());

		System.out.println();

		System.out.println("Start Player: "
				+ gameObj.getGameStartPlayer().getPlayerNumber());

		System.out.println();

		System.out.println("Current Player: "
				+ gameObj.getPlayerCurrentTurn().getPlayerNumber());

		System.out.println();

		System.out.println("-----Game Lantern Cards Status-----");

		System.out.println();

		System.out.println("Total Lantern cards: "
				+ gameObj.getLanternCardCount());

		System.out.println("Red Lantern Cards Left: "
				+ gameObj.getGameRedLanternCardCount());

		System.out.println("Blue Lantern Cards Left: "
				+ gameObj.getGameBlueLanternCardCount());

		System.out.println("Black Lantern Cards Left: "
				+ gameObj.getGameBlackLanternCardCount());

		System.out.println("Green Lantern Cards Left: "
				+ gameObj.getGameGreenLanternCardCount());

		System.out.println("Orange Lantern Cards Left: "
				+ gameObj.getGameOrangeLanternCardCount());

		System.out.println("Purple Lantern Cards Left: "
				+ gameObj.getGamePurpleLanternCardCount());

		System.out.println("White Lantern Cards Left: "
				+ gameObj.getGameWhiteLanternCardCount());

		System.out.println();

		System.out.println("-----Game Dedication Tokens-----");

		System.out.println();

		System.out.println("Next Dedication Token Four: "
				+ gameObj.getNextDedicationTokenFour());

		System.out.println("Next Dedication Token Six: "
				+ gameObj.getNextDedicationTokenSix());

		System.out.println("Next Dedication Token Seven: "
				+ gameObj.getNextDedicationTokenSeven());

		System.out.println("Next Genric Dedication Token: "
				+ gameObj.getNextGenericDedicationToken());

		System.out.println();

		System.out.println("-----Game Dedication Tokens Count-----");

		System.out.println();

		DedicationTokens deTokens = gameObj.getDedicationTokens();

		System.out.println("Dedication Token Four: "
				+ deTokens.getDedicationTokenFourSize());

		System.out.println("Dedication Token Six: "
				+ deTokens.getDedicationTokenSixSize());

		System.out.println("Dedication Token Seven: "
				+ deTokens.getDedicationTokenSevenSize());

		System.out.println("Genric Dedication Token: "
				+ deTokens.getGenericDedicationTokensSize());

		System.out.println();

		System.out.println("Available Lake Tiles in shuffled deck  : "
				+ gameObj.getGameTilesDrawPile().size());

		System.out.println();

		System.out.println("-----Shuffled Lake Tiles-----");

		Vector<LakeTiles> drawTile = gameObj.getGameTilesDrawPile();

		for (int i = 0; i < drawTile.size(); i++) {
			LakeTiles currTile = drawTile.get(i);
			System.out.println("Lake Tile Number: " + currTile.getTilesId());
		}

		System.out.println();

		System.out.println("-----Current Lake Tile Arrangement-----");

		System.out.println();

		Vector<LakeTiles> tileArrangement = gameObj
				.getCurrentLakeTilesArrangement();

		System.out.println("No of Lake Tile: " + tileArrangement.size());

		for (Iterator<LakeTiles> iterator = tileArrangement.iterator(); 
				iterator.hasNext();) {
			
			LakeTiles lakeTiles = (LakeTiles) iterator.next();
			
			System.out.println("Lake Tile Id: " + lakeTiles.getTilesId()
					+ " TC: " + lakeTiles.getTopColor() + " BC: "
					+ lakeTiles.getBottomColor() + " LC: "
					+ lakeTiles.getLeftColor() + " RC: "
					+ lakeTiles.getRightColor());
		}

		System.out.println();

		System.out.println("Game available Favor Tokens: "
				+ gameObj.getGameFavorToken());

		System.out.println();

		System.out.println("-----Players Status-----");

		Players[] playersArray = gameObj.getPlayersList();

		for (int i = 0; i < playersArray.length; i++) {

			Players player = playersArray[i];

			System.out.println();

			System.out.println("********* Player Number: "
					+ player.getPlayerNumber() + " **********");

			System.out.println();

			System.out.println("Number of favor tokens player has: "
					+ player.getPlayerFavorToken());

			System.out.println();

			System.out.println("-------Lantern Cards Player Holds-----");

			System.out.println();

			System.out.println("Total Lantern cards: "
					+ player.getLanternCardCount());

			System.out.println("Red Lantern Cards Left: "
					+ player.getPlayerRedLanternCardCount());

			System.out.println("Blue Lantern Cards Left: "
					+ player.getPlayerBlueLanternCardCount());

			System.out.println("Black Lantern Cards Left: "
					+ player.getPlayerBlackLanternCardCount());

			System.out.println("Green Lantern Cards Left: "
					+ player.getPlayerGreenLanternCardCount());

			System.out.println("Orange Lantern Cards Left: "
					+ player.getPlayerOrangeLanternCardCount());

			System.out.println("Purple Lantern Cards Left: "
					+ player.getPlayerPurpleLanternCardCount());

			System.out.println("White Lantern Cards Left: "
					+ player.getPlayerWhiteLanternCardCount());

			System.out.println();

			System.out.println("-----Player Lake Tiles -----");

			System.out.println();

			Vector<LakeTiles> lakeTiles = player.getCurrentLakeTilesHold();

			System.out.println("No of Lake Tiles holded: " + lakeTiles.size());

			for (int j = 0; j < lakeTiles.size(); j++) {

				LakeTiles tileHolded = lakeTiles.get(j);

				System.out.println("Lake Tiles holded "
						+ tileHolded.getTilesId());
			}

			System.out.println();

			System.out.println("-----Player Dedication Tokens -----");

			DedicationTokens playerDedicationTokens = player
					.getDedicationTokens();

			Vector<Integer> tokenFour = playerDedicationTokens
					.getDedicationTokenFour();

			if (tokenFour.size() == 0) {
				System.out.println("Primary Dedication Tokens Four: "
						+ tokenFour.size());
			} else {

				for (int j = 0; j < tokenFour.size(); j++) {
					System.out.println("Primary Dedication Tokens Four Size: "
							+ tokenFour.size());
					System.out.println("Primary Dedication Tokens Four: "
							+ tokenFour.get(j));
				}
			}

			Vector<Integer> tokenSix = playerDedicationTokens
					.getDedicationTokenSix();

			if (tokenSix.size() == 0) {
				System.out.println("Primary Dedication Tokens Six: "
						+ tokenSix.size());
			} else {

				for (int j = 0; j < tokenSix.size(); j++) {
					System.out.println("Primary Dedication Tokens Six Size: "
							+ tokenSix.size());
					System.out.println("Primary Dedication Tokens Six: "
							+ tokenSix.get(j));
				}
			}

			Vector<Integer> tokenSeven = playerDedicationTokens
					.getDedicationTokenSeven();

			if (tokenSeven.size() == 0) {
				System.out.println("Primary Dedication Tokens Seven: "
						+ tokenSeven.size());
			} else {

				for (int j = 0; j < tokenSeven.size(); j++) {
					System.out.println("Primary Dedication Tokens Seven Size: "
							+ tokenSeven.size());
					System.out.println("Primary Dedication Tokens Seven: "
							+ tokenSeven.get(j));
				}
			}

			Vector<Integer> genericToken = playerDedicationTokens
					.getGenericDedicationTokens();

			if (genericToken.size() == 0) {
				System.out.println("Generic Dedication Tokens: "
						+ genericToken.size());
			} else {

				for (int j = 0; j < genericToken.size(); j++) {
					System.out.println("Generic Dedication Tokens size: "
							+ genericToken.size());
					System.out.println("Generic Dedication Tokens: "
							+ genericToken.get(j));
				}
			}
			System.out.println();
			System.out.println("********* Player Number: "
					+ player.getPlayerNumber() + " **********");

			// System.out.println();
		}
		System.out.println();

	}

}
