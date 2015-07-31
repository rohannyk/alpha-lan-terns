/**
 * 
 */
package edu.concordia.app.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;

/**
 * Class to execute Test Cases for Players
 * @author Team E
 *
 */
public class TestPlayers {

	GameInstance gi;
	GameConfiguration  gc;
		
	

	@Before
	public void setUp() throws Exception {
		gi = new GameInstance(new GameConfiguration());
		gc = new GameConfiguration();
	}

	@After
	public void tearDown() throws Exception {
		gi = null;
		gc = null;
	}

/**
 * This method checks two players  playing the game	
 */
	@Test
	public void testTwoPlayers() {

		// if you enter two players
		//GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration(2));

		assertEquals( gi.getNoOfPlayers(),2);
		assertTrue(gi.getNoOfPlayers() == 2);

	}
	
	/**
	 * This method checks three players  playing the game	
	 */
	@Test
	public void testThreePlayers() {
		// if you enter three players
		//GameConfiguration gc2 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration(3));
		
		assertEquals(gi.getNoOfPlayers(),3);
		assertTrue(gi.getNoOfPlayers() == 3);
		
	}

	/**
	 * This method checks four players  playing the game	
	 */
	@Test
	public void testFourPlayers() {
		// if you enter four players
		//GameConfiguration gc3 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration(4));

		
			assertEquals(gi.getNoOfPlayers(), 4);
			assertTrue(gi.getNoOfPlayers() == 4);
		
	}
	
	/**
	 * The method to test unique position for two player configuration.
	 */
	@Test
	public void testPlayerPositionsforTwoPlayers() {

		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());

		Players player[] = gi.getPlayersList();

		String pos1 = "NORTH";
		String pos2 = "SOUTH";

		if (player[0].equals("NORTH")) {
			assertEquals(player[0].getPlayerPosition(), pos1);
			assertEquals(player[1].getPlayerPosition(), pos2);

		} else if (player[0].equals("SOUTH")) {
			assertEquals(player[0].getPlayerPosition(), pos2);
			assertEquals(player[1].getPlayerPosition(), pos1);

		}

	}
	
	
	/**
	 * The method to test unique position for three player configuration.
	 */
	@Test
	public void testPlayerPositionsforThreePlayers() {
		GameConfiguration gc1 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration());

		Players player[] = gi.getPlayersList();
		String plrPositions[] = gi.getPlayerPositions();

		for (int i = 0; i < player.length; i++) {
			Players players = player[i];

			assertEquals(player[i].getPlayerPosition(), plrPositions[i]);

		}

	}
	
	/**
	 * The method to test unique position for Four player configuration.
	 */
	@Test
	public void testPlayerPositionsforFourPlayers() {
		GameConfiguration gc1 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration());

		Players player[] = gi.getPlayersList();
		String plrPositions[] = gi.getPlayerPositions();

		for (int i = 0; i < player.length; i++) {
			Players players = player[i];

			assertEquals(player[i].getPlayerPosition(), plrPositions[i]);

		}

	}
	
	/**
	 * The method remove one lake tile card from player
	 *  after placing lake tile on game board.
	 */
	@Test
	public void testPlayerRemoveLakeTileFromHand() {
		
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		
		//Call remove lake tile method
		Players plrVal = play.removePlacedLakeTile(tileInHand, player[0]);
		
		assertEquals(plrVal.getCurrentLakeTilesHold().size(), 2);
		
		
	}
	
	/**
	 * The method tes if one lake tile card is added 
	 * to player's hand from draw stack pile 
	 * after placing lake  tile on game board.
	 */
	@Test
	public void testPlayerReplenishLakeTileToPlayer() {
		
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		player[0].getCurrentLakeTilesHold().remove(0);
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		
		//Call remove lake tile method
		play.replenishLakeTilesInHand(gi, player[0]);
		
		assertEquals(player[0].getCurrentLakeTilesHold().size(), 3);
		
		
	}
	
	/**
	 * The method test if lake tile card is rotated 0 degree.
	 */
	@Test
	public void testLakeTileRotationZeroDegree() {

		GameConfiguration gc1 = new GameConfiguration();

		LakeTiles checkTile = gc1.GAME_TOTAL_TILE_SUITE.get(0);

		// create PlayGame class object
		PlayGame play = new PlayGame();

		// Call remove lake tile method
		LakeTiles returnTile = play.rotateLakeTileByDegree(checkTile,
				new String("0"));

		assertEquals(checkTile.getTopColor(), returnTile.getTopColor());
		assertEquals(checkTile.getLeftColor(), returnTile.getLeftColor());
		assertEquals(checkTile.getRightColor(), returnTile.getRightColor());
		assertEquals(checkTile.getBottomColor(), returnTile.getBottomColor());

	}
	
	/**
	 * The method test if lake tile card is rotated 90 degree.
	 */
	/*@Test
	public void testLakeTileRotation90Degree() {
GameConfiguration gc1 = new GameConfiguration();
		
		
		LakeTiles checkTile = gc1.GAME_TOTAL_TILE_SUITE.get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame();
		
		//Call remove lake tile method
		LakeTiles returnTile = play.rotateLakeTileByDegree(checkTile, new String("90"));
		
		System.out.println();
		System.out.println(checkTile.getTopColor()+""+ returnTile.getRightColor());
		System.out.println(checkTile.getLeftColor()+""+ returnTile.getTopColor());
		System.out.println(checkTile.getRightColor()+""+ returnTile.getBottomColor());
		System.out.println(checkTile.getBottomColor()+""+ returnTile.getLeftColor());
		
		assertSame(checkTile.getTopColor(), returnTile.getRightColor());
		assertSame(checkTile.getLeftColor(), returnTile.getTopColor());
		assertSame(checkTile.getRightColor(), returnTile.getBottomColor());
		assertSame(checkTile.getBottomColor(), returnTile.getLeftColor());
	}*/
	
	/**
	 * The method test if lake tile card is rotated 180 degree.
	 */
	/*@Test
	public void testLakeTileRotation180Degree() {
		GameConfiguration gc1 = new GameConfiguration();

		LakeTiles checkTile = gc1.GAME_TOTAL_TILE_SUITE.get(0);

		// create PlayGame class object
		PlayGame play = new PlayGame();

		// Call remove lake tile method
		LakeTiles returnTile = play.rotateLakeTileByDegree(checkTile,
				new String("0"));
		
		

		assertEquals(checkTile.getTopColor(), returnTile.getBottomColor());
		assertEquals(checkTile.getLeftColor(), returnTile.getRightColor());
		assertEquals(checkTile.getRightColor(), returnTile.getLeftColor());
		assertEquals(checkTile.getBottomColor(), returnTile.getTopColor());
	}*/
	
	/**
	 * The method test if lake tile card is rotated 270 degree.
	 */
	/*@Test
	public void testLakeTileRotation270Degree() {
		GameConfiguration gc1 = new GameConfiguration();

		LakeTiles checkTile = gc1.GAME_TOTAL_TILE_SUITE.get(0);

		// create PlayGame class object
		PlayGame play = new PlayGame();

		// Call remove lake tile method
		LakeTiles returnTile = play.rotateLakeTileByDegree(checkTile,
				new String("270"));
		
		
		
		assertSame(checkTile.getTopColor(), returnTile.getLeftColor());
		assertSame(checkTile.getLeftColor(), returnTile.getBottomColor());
		assertSame(checkTile.getRightColor(), returnTile.getTopColor());
		assertSame(checkTile.getBottomColor(), returnTile.getRightColor());
	}*/
	
	/**
	 * The method test the winner player in two player configuration.
	 */
	@Test
	public void testWinnerInTwoPlayerDifferentScore(){
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//
		player[0].setTotalPoints(20); //winner player
		player[1].setTotalPoints(18);
		
		
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		System.out.println(winnerPlayer.get(0));
		
		assertEquals(player[0], winnerPlayer.firstElement());
		
	}
	
	/**
	 * The method test the winner player in three player configuration.
	 */
	@Test
	public void testWinnerInThreePlayerDifferentScore(){
		GameConfiguration gc1 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//
		player[0].setTotalPoints(20); 
		player[1].setTotalPoints(25);//winner player
		player[2].setTotalPoints(10);
		
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[1], winnerPlayer.get(1));
		
	}
	
	/**
	 * The method test the winner player in four player configuration.
	 */
	@Test
	public void testWinnerInFourPlayerDifferentScore(){
		GameConfiguration gc1 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//
		player[0].setTotalPoints(18); //winner player
		player[1].setTotalPoints(8);
		player[2].setTotalPoints(10);
		player[3].setTotalPoints(15);
		
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		System.out.println(winnerPlayer.get(0));
		
		assertEquals(player[0], winnerPlayer.firstElement());
		
	}
	
	/**
	 * The method test the winner player in two player configuration
	 * with same score and different number of lantern cards.
	 */
	@Test
	public void testWinnerTwoPlayerSameScoreDiffCard(){
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(15);
		player[1].setLanternCardCount(20);//winner player
		
		LakeTiles tileInHand = player[0].getCurrentLakeTilesHold().get(0);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[1], winnerPlayer.get(1));
	}
	
	/**
	 * The method test the winner player in two player configuration
	 * with same score , same number of lantern cards and different favor.
	 */
	@Test
	public void testWinnerTwoPlayerSameScoreSameCardDiffFavor(){
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(8);
		player[1].setPlayerFavorToken(12);//winner player
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[1], winnerPlayer.get(0));
	}
	
	/**
	 * The method test the winner player in two player configuration
	 * with same score , same number of lantern cards and same favor tokens.
	 */
	@Test
	public void testWinnerTwoPlayerSameScoreSameCardSameFavor(){
		GameConfiguration gc1 = new GameConfiguration(2);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(8);
		player[1].setPlayerFavorToken(8);//winner player
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(2, winnerPlayer.size());
	}
	
	/**
	 * The method test the winner player in three player configuration
	 * with same score and different number of lantern cards.
	 */
	@Test
	public void testWinnerThreePlayerSameScoreDiffCard(){
		GameConfiguration gc1 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[2].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(20);//winner player
		player[2].setLanternCardCount(18);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[1], winnerPlayer.get(1));
	}
	
	/**
	 * The method test the winner player in three player configuration
	 * with same score , same number of lantern cards and different favor tokens.
	 */
	@Test
	public void testWinnerThreePlayerSameScoreSameCardDiffFavor(){
		GameConfiguration gc1 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[1].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(8);
		player[1].setPlayerFavorToken(10);
		player[2].setPlayerFavorToken(12);//winner player
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[2], winnerPlayer.get(0));
	}
	
	/**
	 * The method test the winner player in three player configuration
	 * with same score , same number of lantern cards and same favor tokens.
	 */
	@Test
	public void testWinnerThreePlayerSameScoreSameCardSameFavor(){
		GameConfiguration gc1 = new GameConfiguration(3);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[2].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		player[2].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(6);
		player[1].setPlayerFavorToken(6);
		player[2].setPlayerFavorToken(6);
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(3, winnerPlayer.size());
	}
	
	/**
	 * The method test the winner player in four player configuration
	 * with same score and different number of lantern cards.
	 */
	@Test
	public void testWinnerFourPlayerSameScoreDiffCard(){
		GameConfiguration gc1 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[2].setTotalPoints(10);
		player[3].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(20);//winner player
		player[2].setLanternCardCount(8);
		player[3].setLanternCardCount(18);
		
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		System.out.println("*****"+winnerPlayer.size());
		
		assertEquals(player[1], winnerPlayer.get(1));
	}
	
	/**
	 * The method test the winner player in four player configuration
	 * with same score , same number of lantern cards and different favor tokens.
	 */
	@Test
	public void testWinnerFourPlayerSameScoreSameCardDiffFavor(){
		GameConfiguration gc1 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[2].setTotalPoints(10);
		player[3].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		player[2].setLanternCardCount(10);
		player[3].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(8);
		player[1].setPlayerFavorToken(10);
		player[2].setPlayerFavorToken(12);
		player[3].setPlayerFavorToken(16);//winner player
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(player[3], winnerPlayer.get(0));
	}
	
	/**
	 * The method test the winner player in four player configuration
	 * with same score , same number of lantern cards and same favor tokens.
	 */
	@Test
	public void testWinnerFourPlayerSameScoreSameCardSameFavor(){
		GameConfiguration gc1 = new GameConfiguration(4);
		gi = new GameInstance(new GameConfiguration());
		
		Players player[] = gi.getPlayersList();
		
		//set total score
		player[0].setTotalPoints(10); 
		player[1].setTotalPoints(10);
		player[2].setTotalPoints(10);
		player[3].setTotalPoints(10);
		
		//set lantern card count
		player[0].setLanternCardCount(10);
		player[1].setLanternCardCount(10);
		player[2].setLanternCardCount(10);
		player[3].setLanternCardCount(10);
		
		//set differnt favor tokens
		player[0].setPlayerFavorToken(5);
		player[1].setPlayerFavorToken(5);
		player[2].setPlayerFavorToken(5);
		player[3].setPlayerFavorToken(5);
		
		//create PlayGame class object
		PlayGame play = new PlayGame(gi,new GameController(gc1, gi));
		Vector<Players> winnerPlayer = play.validateWinner(player);
		
		
		assertEquals(4, winnerPlayer.size());
	}
	

}
