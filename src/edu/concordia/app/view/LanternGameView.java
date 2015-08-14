/**
 * 
 */
package edu.concordia.app.view;

import java.util.Iterator;
import java.util.Vector;

import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * This class handles the GUI elements of the screen
 * @author Team E
 *
 */
public class LanternGameView {

	/**
	 * 
	 */
	public LanternGameView() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The method to display the game board and the lake tiles placed on the
	 * game board.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 */
	public void displayLakeTileBoard(GameInstance gameObj) {

		int startHorizontal = getStartHorizontal(gameObj);
		int endHorizontal = getEndHorizontal(gameObj);
		int startVertial = getStartVertial(gameObj);
		int endVertial = getEndVertial(gameObj);
		// //////////////////////////

		System.out.println("----------- Game Board ------------");

		for (int i = startHorizontal-5; i < endHorizontal + 5; i++) {
			for (int j = startVertial-5; j < endVertial + 5; j++) {
				if ((gameObj.GameBoard[i][j]) == 99)
					System.out.print(padLeft(" ", 3));
				else
					System.out.print(padLeft(
							Integer.toString(gameObj.GameBoard[i][j]), 3));

			}
			System.out.println("");
		}

		System.out.println("-----------------------------------");
		System.out.println();

		System.out.println("-------Details of Lake Tiles on Board:-------");

		System.out.println(); // for spacing

		// Displaying tiles details of on board tiles.
		for (int i = 0; i < 72 + 1; i++) {
			for (int j = 0; j < 72 + 1; j++) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					//int k = gameObj.GameBoard[i][j];
					
					displayLakeTiles(gameObj, gameObj.GameBoard[i][j]);
					
					/*System.out.println("Tile iD " + gameObj.GameBoard[i][j]
							+ " TC: "
							+ gameObj.getAllLakeTiles().get(k).getTopColor()
							+ " RC: "
							+ gameObj.getAllLakeTiles().get(k).getRightColor()
							+ " BC: "
							+ gameObj.getAllLakeTiles().get(k).getBottomColor()
							+ " LC: "
							+ gameObj.getAllLakeTiles().get(k).getLeftColor()
							+ " Platform: "
							+ gameObj.getAllLakeTiles().get(k).isPlatform());*/
				}
			}
		}
		System.out.println("----------------------------------");
	}
	
	/**
	 * The method to display lake tile details.
	 * 
	 * @param gameObj
	 *            The GameInstance to display lake tile.
	 * @param tileId
	 *            The id of lake tile for display.
	 */
	public void displayLakeTiles(GameInstance gameObj, int tileId) {
		System.out.println("Lake Tile iD: "
				+ gameObj.getAllLakeTiles().get(tileId).getTilesId()// gameObj.GameBoard[i][j]
				+ " TC: " + gameObj.getAllLakeTiles().get(tileId).getTopColor()
				+ " RC: "
				+ gameObj.getAllLakeTiles().get(tileId).getRightColor()
				+ " BC: "
				+ gameObj.getAllLakeTiles().get(tileId).getBottomColor()
				+ " LC: "
				+ gameObj.getAllLakeTiles().get(tileId).getLeftColor()
				+ " Platform: "
				+ gameObj.getAllLakeTiles().get(tileId).isPlatform());
	}
	
	/**
	 * The method to return the top vertical element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return The top vertical array element which is not empty.
	 */
	public static int getStartVertial(GameInstance gameObj) {
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					return i - 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method to return the left horizontal element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return 
	 * 			The left horizontal array element which is not empty.
	 */
	public static int getStartHorizontal(GameInstance gameObj) {
		for (int i = 0; i < 73; i++) {
			for (int j = 0; j < 73; j++) {
				if ((gameObj.GameBoard[j][i]) != 99) {
					return i - 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method to return the bottom vertical element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return bottom vertical array element which is not empty.
	 */
	public static int getEndVertial(GameInstance gameObj) {
		for (int i = 72; i > 0; i--) {
			for (int j = 72; j > 0; j--) {
				if ((gameObj.GameBoard[i][j]) != 99) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * The method to return the right horizontal element of the game board array
	 * which is not empty.
	 * 
	 * @param gameObj
	 *            The GameInstance object for updating game board array.
	 * @return right horizontal array element which is not empty.
	 */
	public static int getEndHorizontal(GameInstance gameObj) {

		for (int i = 72; i > 0; i--) {
			for (int j = 72; j > 0; j--) {
				if ((gameObj.GameBoard[j][i]) != 99) {
					return i + 1;
				}
			}
		}
		return 0;
	}
	
	/**
	 * The method for left padding.
	 * 
	 * @param s
	 *            The string to be padded.
	 * @param n
	 *            The number of time string is padded.
	 * @return The padded string.
	 */
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	/**
	 * The method for right padding.
	 * 
	 * @param s
	 *            The string to be padded.
	 * @param n
	 *            The number of time string is padded.
	 * @return The padded string.
	 */
	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
	
	/**
	 * The method to display the score of each player of the game.
	 * 
	 * @param gamePlayers
	 *            The array of players of the game.
	 * 
	 */
	public void displayPlayersScore(Players[] gamePlayers) {

		System.out.println("-------------- Players Score-------------");

		for (int i = 0; i < gamePlayers.length; i++) {
			Players gamePlayer = gamePlayers[i];

			System.out.println("----- Player " + gamePlayer.getPlayerNumber()+"-------");
			
			System.out.println(); //space
			
			System.out.println(" Score: " + gamePlayer.getTotalPoints());
			
			System.out.println(); //space
			
			System.out.println(" Total Lantern Cards: "+gamePlayer.getLanternCardCount());
			
			System.out.println(); //space
			
			System.out.println(" Total Favor Tokens: "+gamePlayer.getPlayerFavorToken());
			
			System.out.println("------- ------------- -------");
			
		}

		System.out.println("-------------- ---------- -------------");

	}
	
	/**
	 * The method to display the winners of the game.
	 * 
	 * @param winnerPlayers
	 *            The array containing the winner players of the game.
	 */
	public void displayWinner(Vector<Players> winnerPlayers) {

		System.out.println();

		System.out.println("************* Game Winner *************");

		for (Iterator<Players> iterator = winnerPlayers.iterator(); iterator
				.hasNext();) {

			Players player = (Players) iterator.next();

			System.out.println("------------Player " + player.getPlayerNumber()
					+ " --------------");

			System.out.println();

			System.out.println("------------- Details --------------");

			System.out.println(" Score: " + player.getTotalPoints());

			System.out.println(" Lantern Cards: "
					+ player.getLanternCardCount());

			System.out
					.println(" Favor Tokens: " + player.getPlayerFavorToken());

			System.out.println("------------- ------ --------------");

			System.out.println();
		}

	}
	
	/**
	 * The method to display the status of the specified player.
	 * 
	 * @param playing
	 *            The player of the game.
	 */
	public void displayPlayerStatus(Players playing) {

		playing.displayPlayerStatus();
	}

}
