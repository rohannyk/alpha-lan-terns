/**
 * 
 */
package edu.concordia.app.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;
import edu.concordia.app.view.LanternGameView;

/**
 * @author lovepreet
 *
 */
public class UnfriendlyPlayerStrategy extends PlayerStrategy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8707943598805857983L;
	private GameInstance gameObj;
	
	/**
	 * @param gameObj
	 */
	public UnfriendlyPlayerStrategy(GameInstance gameObj) {
		this.gameObj = gameObj;
	}

	public Players playerLastTurnChoice(GameInstance gameObj, Players playing,
			String opt1) {

		// when player don't have enough lantern cards or favor tokens
		// for exchange and make a dedication.
		if (opt1.isEmpty()) {
			if (!opt1.contains("exchange")) {
				System.out.println("You don't have enough"
						+ " card for exchange.");
			}
			if (!(opt1.contains("type1") || opt1.contains("type2") || opt1
					.contains("type3"))) {
				System.out.println("You don't have enough "
						+ "cards to make a dedication.");
			}
		} else {

			if ((opt1.contains("type1") || opt1.contains("type2") || opt1
					.contains("type3"))) {
				
				System.out.println();

				int fourValue = gameObj.getNextDedicationTokenFour();

				int sixValue = gameObj.getNextDedicationTokenSix();

				int sevenValue = gameObj.getNextDedicationTokenSeven();

				int genericValue = gameObj.getNextGenericDedicationToken();

				ArrayList<String> colorPairs = getLanternCardColorPairs(playing);

				ArrayList<String> fourUniqueColorPairs = checkPossibleFourUnique(playing);

				// do exchange and dedication if possible
				if (checkPossibleUniqueColor(playing)
						|| (sevenValue > sixValue && sevenValue > fourValue)
						|| (genericValue > sixValue && genericValue > fourValue && genericValue > sevenValue)) {
					doDedicationSeven(gameObj, playing);
					
					System.out.println();
					
					System.out.println("Player " + playing.getPlayerNumber()
							+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
					
					DedicationTokens dedicationObj = gameObj
							.getDedicationTokens();
					Vector<Integer> dedicationSevenVal = dedicationObj
							.getDedicationTokenSeven();
					if (dedicationSevenVal.size() > 0) {
						int dedicationSevenValue = dedicationSevenVal
								.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getDedicationTokenSeven()
								.add(dedicationSevenValue);
						if (dedicationSevenVal.isEmpty()) {
							gameObj.setNextDedicationTokenSeven(-1);
						} else {
							gameObj.setNextDedicationTokenSeven(dedicationSevenVal
									.firstElement());
						}
					} else {
						Vector<Integer> genGenericVal = dedicationObj
								.getGenericDedicationTokens();
						if (genGenericVal.size() > 0) {
							int genericValue1 = genGenericVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj
									.getGenericDedicationTokens().add(
											genericValue1);
						}
					}

				} else if ((colorPairs.size() > 2)
						|| (sixValue > sevenValue && sixValue > fourValue)) {

					int pairCount = 0;
					
					for (int i = 0; i < 3; i++) {

						String color = colorPairs.get(i);

						doDedicationSix(gameObj, color, playing);
						
						pairCount += 1;
					}
					
					if(pairCount == 3){
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj
								.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(
									dedicationSixValue);
							if (dedicationSixVal.isEmpty()) {
								gameObj.setNextDedicationTokenSix(-1);
							} else {
								gameObj.setNextDedicationTokenSix(dedicationSixVal
										.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue1 = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue1);
							}
						}
					}

				} else if ((fourValue > sixValue && fourValue > sevenValue)
						|| fourUniqueColorPairs.size() > 0) {

					int randomNumber = getRandomNumber(fourUniqueColorPairs
							.size());

					String colorFour = fourUniqueColorPairs.get(randomNumber);

					doDedicationFour(gameObj, colorFour, playing);

				} else if (genericValue > sixValue && genericValue > fourValue
						&& genericValue > sevenValue) {

					// doWhatIsAsked(playing, gameObjs, "GEN");

					// If anyone of the dedication is possible, do it.
					if (playing.getPossibleDedicationSevenUniqueColor().size() > 6) {
						if (checkPossibleUniqueColor(playing)) {

							doDedicationSeven(gameObj, playing);
							
							System.out.println();
							
							System.out.println("Player " + playing.getPlayerNumber()
									+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
							
							DedicationTokens dedicationObj = gameObj
									.getDedicationTokens();
							Vector<Integer> dedicationSevenVal = dedicationObj
									.getDedicationTokenSeven();
							if (dedicationSevenVal.size() > 0) {
								int dedicationSevenValue = dedicationSevenVal
										.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj.getDedicationTokenSeven()
										.add(dedicationSevenValue);
								if (dedicationSevenVal.isEmpty()) {
									gameObj.setNextDedicationTokenSeven(-1);
								} else {
									gameObj.setNextDedicationTokenSeven(dedicationSevenVal
											.firstElement());
								}
							} else {
								Vector<Integer> genGenericVal = dedicationObj
										.getGenericDedicationTokens();
								if (genGenericVal.size() > 0) {
									int genericValue1 = genGenericVal.remove(0);
									DedicationTokens playerDedicationObj = playing.getDedicationTokens();
									playerDedicationObj
											.getGenericDedicationTokens().add(
													genericValue1);
								}
							}
						}
					} else if (playing.getPossibleDedicationThreePairColor().size() > 3) {

						if ((colorPairs.size() > 2)) {
							
							int pairCount = 0;

							for (int i = 0; i < 3; i++) {

								String color = colorPairs.get(i);

								doDedicationSix(gameObj, color, playing);
								
								pairCount += 1;
							}
							
							if(pairCount == 3){
								DedicationTokens dedicationObj = gameObj
										.getDedicationTokens();
								Vector<Integer> dedicationSixVal = dedicationObj
										.getDedicationTokenSix();
								if (dedicationSixVal.size() > 0) {
									int dedicationSixValue = dedicationSixVal.remove(0);
									DedicationTokens playerDedicationObj = playing.getDedicationTokens();
									playerDedicationObj.getDedicationTokenSix().add(
											dedicationSixValue);
									if (dedicationSixVal.isEmpty()) {
										gameObj.setNextDedicationTokenSix(-1);
									} else {
										gameObj.setNextDedicationTokenSix(dedicationSixVal
												.firstElement());
									}
								} else {
									Vector<Integer> genDedicationVal = dedicationObj
											.getGenericDedicationTokens();
									if (genDedicationVal.size() > 0) {
										int genericValue1 = genDedicationVal.remove(0);
										DedicationTokens playerDedicationObj = playing.getDedicationTokens();
										playerDedicationObj
												.getGenericDedicationTokens().add(
														genericValue1);
									}
								}
							}
						}

					} else if (playing.getPossibleDedicationFourUniqueColor().size() > 0) {

						if (fourUniqueColorPairs.size() > 0) {

							int randomNumber = getRandomNumber(fourUniqueColorPairs
									.size());

							String colorFour = fourUniqueColorPairs
									.get(randomNumber);

							doDedicationFour(gameObj, colorFour, playing);
						}

					}
				}
			}
		}
		return playing;

	}
	
	public String makeAMove(GameInstance gameObjs, Players playing, String option)
	{
		
			//this.playing = playing;
				
			int fourValue = gameObjs.getNextDedicationTokenFour();
		
			int sixValue = gameObjs.getNextDedicationTokenSix();
		
			int sevenValue = gameObjs.getNextDedicationTokenSeven();
		
			int genericValue = gameObjs.getNextGenericDedicationToken();
			
			// all possible exchange combinations
			playerPossibleExchangeMoves( playing);
		
			// all possible combinations of make a dedication.		
			dedicationType1(playing, gameObjs);
			dedicationType2(playing, gameObjs);
			dedicationType3(playing, gameObjs);
			
			//do exchange and dedication if possible
			if(sevenValue > sixValue && sevenValue > fourValue && playing.getPossibleDedicationSevenUniqueColor().size() > 6)
			{				
				doWhatIsAsked( playing,  gameObjs,  "SEVEN");
			}else if(sixValue > sevenValue && sixValue > fourValue && playing.getPossibleDedicationThreePairColor().size() > 3)
			{
				doWhatIsAsked( playing,  gameObjs,  "SIX");
			}else if(fourValue > sixValue && fourValue > sevenValue && playing.getPossibleDedicationFourUniqueColor().size() > 0)
			{
				doWhatIsAsked( playing,  gameObjs,  "FOUR");
			}else if(genericValue > sixValue && genericValue > fourValue && genericValue> sevenValue)
			{
				doWhatIsAsked( playing,  gameObjs,  "GEN");
			}
			
			//while placing a lake tile if lantern count is greater than 12 
			if (playing.getLanternCardCount() > 12) {

				//abstract method in base class
				// If lantern cards are more than 12
				// filter them i.e. discard or make a dedication.
				//greedy player always do dedication and never discard cards.
				
				filterExcessLanternCards(gameObjs, playing);

			} else {

				//remove playing object
				
				// lake tiles that are already placed on the board.
				new LanternGameView().displayLakeTileBoard(gameObjs);
				//displayLakeTileBoard(gameObj);
				
				int startHorizontal = LanternGameView.getStartHorizontal(gameObjs);
				int endHorizontal = LanternGameView.getEndHorizontal(gameObjs);
				int startVertial = LanternGameView.getStartVertial(gameObjs);
				int endVertial = LanternGameView.getEndVertial(gameObjs);
				
				//araylist contain edge lake tiles
				ArrayList<String> placedGameBoardLakeTiles = new ArrayList<String>();
				
				//arraylist contain lake tiles where adjacent lake tiles can be placed.
				ArrayList<String> possibleAdjacentBoardLakeTiles = new ArrayList<String>();
				
				System.out.print("adjacent tiles: ");
				
				for (int i = startHorizontal; i < endHorizontal + 1; i++) {
					for (int j = startVertial; j < endVertial + 1; j++) {
					if ((gameObjs.GameBoard[i][j]) != 99
							&& (gameObjs.GameBoard[i][j - 1] == 99
									|| gameObjs.GameBoard[i][j + 1] == 99
									|| gameObjs.GameBoard[i - 1][j] == 99 || gameObjs.GameBoard[i + 1][j] == 99)) {
							
//							System.out.print(
//									Integer.toString(gameObjs.GameBoard[i][j]));
							
						placedGameBoardLakeTiles.add(Integer.toString(gameObjs.GameBoard[i][j]));
						
						String possibleLakeTilesOpt = Integer.toString(gameObjs.GameBoard[i][j]);
						
						if (gameObjs.GameBoard[i][j - 1] == 99) {
							//left = true;
							
							possibleLakeTilesOpt += ":L";
						}
						if (gameObjs.GameBoard[i][j + 1] == 99) {
							//right = true;
							
							possibleLakeTilesOpt += ":R";
						}
						if (gameObjs.GameBoard[i - 1][j] == 99) {
							
							//top = true;
							possibleLakeTilesOpt += ":T";
						}
						if (gameObjs.GameBoard[i + 1][j] == 99) {
							
							//bottom = true;
							possibleLakeTilesOpt += ":B";
						}
						
						
						possibleAdjacentBoardLakeTiles.add(possibleLakeTilesOpt);
						
						}
					}
					System.out.println("");
				}
				
				//rotation degree array
				String rotationDegree[] = {"0","90","180","270"};
				
				//tiles in hand
				Vector<LakeTiles> tilesInHandVector = playing.getCurrentLakeTilesHold();
				
				//vector for possible lake tiles in hand combination with degree
				Vector<String> placedTileCombinations = new Vector<String>();
				
			for (int i = 0; i < tilesInHandVector.size(); i++) {

				LakeTiles tile = tilesInHandVector.get(i);
				
				for (int j = 0; j < rotationDegree.length; j++) {
					String degree = rotationDegree[j];
					
					String combination = Integer.toString(tile.getTilesId()) +":" + degree;
					
					placedTileCombinations.add(combination);

				}
			}
			
			String playerCardFaceColor = "";
			
			int playerFavorToken = 0;
			
			int playerRedMatchingCardColors = 0;
			int playerWhiteMatchingCardColors = 0;
			int playerOrangeMatchingCardColors = 0;
			int playerGreenMatchingCardColors = 0;
			int playerPurpleMatchingCardColors = 0;
			int playerBlackMatchingCardColors = 0;
			int playerBlueMatchingCardColors = 0;
			
			int gameRedMatchingCardColors = gameObjs.getGameRedLanternCardCount();
			int gameWhiteMatchingCardColors = gameObjs.getGameWhiteLanternCardCount();
			int gameOrangeMatchingCardColors =gameObjs.getGameOrangeLanternCardCount();
			int gameGreenMatchingCardColors =gameObjs.getGameGreenLanternCardCount();
			int gamePurpleMatchingCardColors = gameObjs.getGamePurpleLanternCardCount();
			int gameBlackMatchingCardColors = gameObjs.getGameBlackLanternCardCount();
			int gameBlueMatchingCardColors = gameObjs.getGameBlueLanternCardCount();
			
//			Map<String,Integer> playerMatchingCardColors = new HashMap<String,Integer>();
			Vector<String> tilePlacementCominationResults = new Vector<String>();
			
			String otherPlayerTilesPlacement = "";
			
			String playerPosition = playing.getPlayerPosition();
			
			//complete game lake tiles
			Vector<LakeTiles> totalLakeTilesStack = gameObjs.getAllLakeTiles();
			
			for (int i = 0; i < placedTileCombinations.size(); i++) {
				
				//contains lake tile placement result
				String placementCombination ="";
				
				String degreeCombination[] = placedTileCombinations.get(i).split(":");
				
				//add tile in hand and degree.
				placementCombination += placedTileCombinations.get(i);
				
				//get lake tile in hand and rotate by given degree.
				LakeTiles lakeTileChoice = totalLakeTilesStack.get(Integer.valueOf(degreeCombination[0]));
				
				// if lake tile has platform
				/*if(lakeTileChoice.isPlatform()){
					playerFavorToken = playerFavorToken+1;
				}*/
				
				String degree = degreeCombination[1];
				
				//rotate lake tile
				lakeTileChoice = PlayGame.rotateLakeTileByDegree(lakeTileChoice, degree);
				
				
				playerCardFaceColor = getPlayerFaceColor(playerPosition, lakeTileChoice);
				
				otherPlayerTilesPlacement  = new String(lakeTileChoice.getTilesId() +":"+ degree);
				
				System.out.println(degreeCombination[0]+" "+degreeCombination[1]);
				
				for (int plyrCount=0; plyrCount < gameObjs.getPlayersList().length;plyrCount++)
				{			
					
					if(gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("North") && colorCount(lakeTileChoice.getTopColor(), gameObjs)>0 )
					{
								otherPlayerTilesPlacement +=":N:1";
					}
					else
					{
						otherPlayerTilesPlacement +=":N:0";
					}
					if(gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("South") && colorCount(lakeTileChoice.getBottomColor(), gameObjs)>0 )
					{
								otherPlayerTilesPlacement +=":S:1";
					}
					else
					{
						otherPlayerTilesPlacement +=":S:0";
					}
					if(gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("East") && colorCount(lakeTileChoice.getLeftColor(), gameObjs)>0 )
					{
								otherPlayerTilesPlacement +=":E:1";
					}
					else
					{
						otherPlayerTilesPlacement +=":E:0";
					}
					if(gameObjs.getPlayersList()[plyrCount].getPlayerPosition().equalsIgnoreCase("West") && colorCount(lakeTileChoice.getRightColor(), gameObjs)>0 )
					{
								otherPlayerTilesPlacement +=":W:1";
					}
					else
					{
						otherPlayerTilesPlacement +=":W:0";
					}
					
//					tilePlacementCominationResults.add(otherPlayerTilesPlacement);
//					otherPlayerTilesPlacement  += lakeTileChoice.getTilesId() +":"+ degree;
					
					
				}
				
				tilePlacementCominationResults.add(otherPlayerTilesPlacement);
				//otherPlayerTilesPlacement  ="";
			}
			
			System.out.println("size "+tilePlacementCominationResults.size());
			
			
			// select possible combination result	
			String tileMove="";
			for (int i = 0; i < tilePlacementCominationResults.size(); i++) {
				String combinationResult = tilePlacementCominationResults.get(i);
				
				String result[] = combinationResult.split(":");
				
				System.out.println(combinationResult);
				
				if(Integer.parseInt(result[3])+Integer.parseInt(result[5])+Integer.parseInt(result[7])+Integer.parseInt(result[9])<1)
				{
					tileMove = combinationResult;
					break;
				}else if (Integer.parseInt(result[3])+Integer.parseInt(result[5])+Integer.parseInt(result[7])+Integer.parseInt(result[9])<2)
				{
					tileMove = combinationResult;
					break;
				}

				
			}
			
			//select random adjacent placement tile
			int adjTileChoice = getRandomNumber(possibleAdjacentBoardLakeTiles.size());
			
			String adjTilePosition = possibleAdjacentBoardLakeTiles.get(adjTileChoice);
			
			/*if(tileMove.equalsIgnoreCase(""))
			{
				//random placing any tile
			}
			else
			{
				//place lakeTile
			}*/
			
			LakeTiles tileInHand;
			if(tileMove.equals("")){
				int selectedMove = getRandomNumber(tilePlacementCominationResults.size());
				tileMove = tilePlacementCominationResults.get(selectedMove);
				
				 tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove, adjTilePosition);
			}else{
				 tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove, adjTilePosition);
			}
			
			//Check dedication possible.
		
			//LakeTiles tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove);
				
				

				//should be abstract method in base class
				// select lake tile from tiles in hand
				//LakeTiles tileInHand = revealLakeTile(playing, gameObjs);

				// ask user to rotate card to what degree (0/90/180/270)
				// and return the rotated lake tile
				//tileInHand = rotateLakeTileOnUserChoice(tileInHand);

				System.out.println(); // for spacing				

				// add lake tile to current lake tile arrangement vector
				gameObjs.getCurrentLakeTilesArrangement().addElement(
						tileInHand);

				// remove laketile in hand and already placed
				playing.removePlacedLakeTile(tileInHand);
//					playing = removePlacedLakeTile(tileInHand, playing);

				System.out.println(""); // for space

				// take one card from draw stack to have three cards in hand
				// and remove top element from draw stack.
				Players.replenishLakeTilesInHand(gameObjs, playing);
				//boolean containTiles = replenishLakeTilesInHand(gameObj, playing);				

				//display player status
				new LanternGameView().displayPlayerStatus(playing);
				//displayPlayerStatus(playing);

				System.out.println("Lake Tile placed on the game board.");

				//remove playing object
				
				// lake tiles that are already placed on the board.
				new LanternGameView().displayLakeTileBoard(gameObjs);
				//displayLakeTileBoard(gameObj);
				
				option = new String("lakeTile");

			}
			
		return option;
	}
	public static int colorCount(String color, GameInstance gameobj)
	{
		int count =0;
		if(color.equalsIgnoreCase("Red") && gameobj.getGameRedLanternCardCount()>0)
				{
					count++;
				}
		if(color.equalsIgnoreCase("Blue") && gameobj.getGameBlueLanternCardCount()>0)
		{
			count++;
		}
		if(color.equalsIgnoreCase("Black") && gameobj.getGameBlackLanternCardCount()>0)
		{
			count++;
		}
		if(color.equalsIgnoreCase("Green") && gameobj.getGameGreenLanternCardCount()>0)
		{
			count++;
		}
		if(color.equalsIgnoreCase("Orange") && gameobj.getGameOrangeLanternCardCount()>0)
		{
			count++;
		}
		if(color.equalsIgnoreCase("Purple") && gameobj.getGamePurpleLanternCardCount()>0)
		{
			count++;
		}
		if(color.equalsIgnoreCase("White") && gameobj.getGameWhiteLanternCardCount()>0)
		{
			count++;
		}
		
				
		return count;
	}
	
	public static LakeTiles placeALakeTileAndDistribute(GameInstance gameObjs, Players playing, String comRes, String adjTilePosition)
	{
		String [] placingInformation = comRes.split(":");
		
		System.out.println(placingInformation[0]);
		
		String [] adjTileInformation = adjTilePosition.split(":");
		
		System.out.println(adjTilePosition);
		
		int randomPlacePosValue = getRandomNumber(adjTileInformation.length - 1) +1;
		String randomPlacePos = adjTileInformation[randomPlacePosValue];
		
		LakeTiles tileInHand = null;
		
		//LakeTiles tileInHand = playing.getCurrentLakeTilesHold().get(Integer.parseInt(placingInformation[0]));
		
		for (Iterator<LakeTiles> iterator = playing.getCurrentLakeTilesHold().iterator(); iterator.hasNext();) {
			LakeTiles tiles = (LakeTiles) iterator.next();	
			
			if(tiles.getTilesId() == Integer.parseInt(placingInformation[0])){
				tileInHand = tiles;
				break;
			}
		}
		
		tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, placingInformation[1]);
		int x = 0;
		int y = 0;
		for(int i=0;i<73;i++)
		{
			for(int j=0;j<73;j++)
			{
				if(gameObjs.GameBoard[i][j]==Integer.parseInt(adjTileInformation[0]))
				{
					y = i;
					x = j;
					break;
				}
			}
		}
		if (randomPlacePos.equals("L"))
		{
			x = x-1;
		}else if (randomPlacePos.equals("R"))
		{
			x = x+1;
		}else if (randomPlacePos.equals("T"))
		{
			y = y-1;
		}else if (randomPlacePos.equals("B"))
		{
			y = y +1;
		}
		gameObjs.GameBoard[y][x] = tileInHand.getTilesId();
		
		//distribute lantern cards
		PlayGame.distributeLakeTilesPlaying(gameObjs, playing, y, x);
		PlayGame.distributingLakeTilesToRestPlayers(gameObjs, playing, y, x);
		
		return tileInHand;
		
	}
	
	public static String genValidationGreedy(GameInstance gameObj,Players playing, String combinationResult) {
		boolean exahange = false;
		String retStr = "";

		boolean type1 = false;
		if ((playing.getPlayerBlackLanternCardCount()+ Integer.parseInt(combinationResult.split(":")[9])> 3)
				|| (playing.getPlayerWhiteLanternCardCount()+ Integer.parseInt(combinationResult.split(":")[13]) > 3)
				|| (playing.getPlayerBlueLanternCardCount() + Integer.parseInt(combinationResult.split(":")[7]) > 3)
				|| (playing.getPlayerGreenLanternCardCount()+ Integer.parseInt(combinationResult.split(":")[17])  > 3)
				|| (playing.getPlayerOrangeLanternCardCount() + Integer.parseInt(combinationResult.split(":")[15]) > 3)
				|| (playing.getPlayerPurpleLanternCardCount()+ Integer.parseInt(combinationResult.split(":")[11])  > 3)
				|| (playing.getPlayerRedLanternCardCount() > 3+ Integer.parseInt(combinationResult.split(":")[5]) )) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenFourSize() > 0) {
				retStr += "type1";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type1";
			}
		}
		boolean type2 = false;
		int countPairs2 = 0;
		if (playing.getPlayerBlackLanternCardCount()+ Integer.parseInt(combinationResult.split(":")[9])  > 1) {
			countPairs2++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[13]) ) {
			countPairs2++;
		}
		if (playing.getPlayerBlueLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[7]) ) {
			countPairs2++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[17]) ) {
			countPairs2++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[15]) ) {
			countPairs2++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[11]) ) {
			countPairs2++;
		}
		if (playing.getPlayerRedLanternCardCount() > 1+ Integer.parseInt(combinationResult.split(":")[5]) ) {
			countPairs2++;
		}
		if (countPairs2 > 2) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSixSize() > 0) {
				retStr += "type2";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type2";
			}
		}
		boolean type3 = false;
		int countPairs7 = 0;
		if (playing.getPlayerBlackLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[9]) ) {
			countPairs7++;
		}
		if (playing.getPlayerWhiteLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[13]) ) {
			countPairs7++;
		}
		if (playing.getPlayerBlueLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[7]) ) {
			countPairs7++;
		}
		if (playing.getPlayerGreenLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[17]) ) {
			countPairs7++;
		}
		if (playing.getPlayerOrangeLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[15]) ) {
			countPairs7++;
		}
		if (playing.getPlayerPurpleLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[11]) ) {
			countPairs7++;
		}
		if (playing.getPlayerRedLanternCardCount() > 0+ Integer.parseInt(combinationResult.split(":")[5]) ) {
			countPairs7++;
		}
		if (countPairs7 > 6) {
			DedicationTokens dedicationToken = gameObj.getDedicationTokens();
			if (dedicationToken.getDedicationTokenSevenSize() > 0) {
				retStr += "type3";
			} else if (dedicationToken.getGenericDedicationTokensSize() > 0) {
				retStr += "type3";
			}
		}
		return retStr;
	}

	/**
	 * @param playerPosition
	 * @param lakeTileChoice
	 * @return 
	 */
	private static String getPlayerFaceColor(String playerPosition,
			LakeTiles lakeTileChoice) {
		String playerCardFaceColor = null;
		
		if (playerPosition.equalsIgnoreCase("NORTH")) {
			playerCardFaceColor = lakeTileChoice.getTopColor();
		} else if (playerPosition.equalsIgnoreCase("SOUTH")) 
		{
			playerCardFaceColor = lakeTileChoice.getBottomColor();
		} else if (playerPosition.equalsIgnoreCase("EAST")) 
		{
			playerCardFaceColor = lakeTileChoice.getLeftColor();
		} else if (playerPosition.equalsIgnoreCase("WEST")) 
		{
			playerCardFaceColor = lakeTileChoice.getRightColor();
		}

		return playerCardFaceColor;
	}
	
	/**
	 * @param playing
	 * @param gameObjs 
	 * @return
	 */
	private static LakeTiles revealLakeTile(Players playing, GameInstance gameObjs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void doWhatIsAsked(Players playing, GameInstance gameObj,
			String type) {
		if (type.equals("SEVEN")) {
			if (playing.getPlayerBlackLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Black");
				// do dedication after exchange
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerWhiteLanternCardCount() == 0) {
				doExchange(playing, gameObj, "White");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerBlueLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Blue");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerGreenLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Green");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerOrangeLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Orange");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerPurpleLanternCardCount() == 0) {
				doExchange(playing, gameObj, "Purple");
				doDedicationSeven(gameObj, playing);
			} else if (playing.getPlayerRedLanternCardCount() == 0) {

				doExchange(playing, gameObj, "Red");
				doDedicationSeven(gameObj, playing);
			} else {
				doDedicationSeven(gameObj, playing);
			}
			
			System.out.println();
			
			System.out.println("Player " + playing.getPlayerNumber()
					+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
			
			DedicationTokens dedicationObj = gameObj
					.getDedicationTokens();
			Vector<Integer> dedicationSevenVal = dedicationObj
					.getDedicationTokenSeven();
			if (dedicationSevenVal.size() > 0) {
				int dedicationSevenValue = dedicationSevenVal
						.remove(0);
				DedicationTokens playerDedicationObj = playing.getDedicationTokens();
				playerDedicationObj.getDedicationTokenSeven()
						.add(dedicationSevenValue);
				if (dedicationSevenVal.isEmpty()) {
					gameObj.setNextDedicationTokenSeven(-1);
				} else {
					gameObj.setNextDedicationTokenSeven(dedicationSevenVal
							.firstElement());
				}
			} else {
				Vector<Integer> genGenericVal = dedicationObj
						.getGenericDedicationTokens();
				if (genGenericVal.size() > 0) {
					int genericValue1 = genGenericVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj
							.getGenericDedicationTokens().add(
									genericValue1);
				}
			}

		} else if (type.equals("SIX")) {
			
			ArrayList<String> possibleThreePair = playing
					.getPossibleDedicationThreePairColor();

			int pairCount = 0;

			for (int i = 0; i < possibleThreePair.size(); i++) {
				if (pairCount == 3) {
					break;
				}
				String color = possibleThreePair.get(i);

				if (color.equalsIgnoreCase("Black")) {
					if (playing.getPlayerBlackLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Black");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					} else if (playing.getPlayerBlackLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					}
				} else if (color.equalsIgnoreCase("White")) {
					if (playing.getPlayerWhiteLanternCardCount() == 1) {
						doExchange(playing, gameObj, "White");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					} else if (playing.getPlayerWhiteLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					}
				} else if (color.equalsIgnoreCase("Blue")) {
					if (playing.getPlayerBlueLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Blue");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					} else if (playing.getPlayerBlueLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					}
				} else if (color.equalsIgnoreCase("Green")) {
					if (playing.getPlayerGreenLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Green");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					} else if (playing.getPlayerGreenLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					}
				} else if (color.equalsIgnoreCase("Purple")) {
					if (playing.getPlayerPurpleLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Purple");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					} else if (playing.getPlayerPurpleLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
						
					}
				} else if (color.equalsIgnoreCase("Red")) {
					if (playing.getPlayerRedLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Red");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
					} else if (playing.getPlayerRedLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
					}
				} else if (color.equalsIgnoreCase("Orange")) {
					if (playing.getPlayerOrangeLanternCardCount() == 1) {
						doExchange(playing, gameObj, "Orange");
						// do dedication after exchange
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
					} else if (playing.getPlayerOrangeLanternCardCount() > 1) {
						doDedicationSix(gameObj, color, playing);
						
						pairCount = pairCount+1;
					}
				}
			}
			
			if(pairCount == 3){
				DedicationTokens dedicationObj = gameObj
						.getDedicationTokens();
				Vector<Integer> dedicationSixVal = dedicationObj
						.getDedicationTokenSix();
				if (dedicationSixVal.size() > 0) {
					int dedicationSixValue = dedicationSixVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getDedicationTokenSix().add(
							dedicationSixValue);
					if (dedicationSixVal.isEmpty()) {
						gameObj.setNextDedicationTokenSix(-1);
					} else {
						gameObj.setNextDedicationTokenSix(dedicationSixVal
								.firstElement());
					}
				} else {
					Vector<Integer> genDedicationVal = dedicationObj
							.getGenericDedicationTokens();
					if (genDedicationVal.size() > 0) {
						int genericValue1 = genDedicationVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj
								.getGenericDedicationTokens().add(
										genericValue1);
					}
				}
			}

		} else if (type.equals("FOUR")) {
			ArrayList<String> possibleFourUniqueColor = playing
					.getPossibleDedicationFourUniqueColor();

			String color = possibleFourUniqueColor.get(0);

			if (color.equalsIgnoreCase("Black")) {
				if (playing.getPlayerBlackLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Black");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerBlackLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("White")) {
				if (playing.getPlayerWhiteLanternCardCount() == 3) {
					doExchange(playing, gameObj, "White");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerWhiteLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("Blue")) {
				if (playing.getPlayerBlueLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Blue");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerBlueLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("Green")) {
				if (playing.getPlayerGreenLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Green");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerGreenLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("Purple")) {
				if (playing.getPlayerPurpleLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Purple");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerPurpleLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("Red")) {
				if (playing.getPlayerRedLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Red");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerRedLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			} else if (color.equalsIgnoreCase("Orange")) {
				if (playing.getPlayerOrangeLanternCardCount() == 3) {
					doExchange(playing, gameObj, "Orange");
					// do dedication after exchange
					doDedicationFour(gameObj, color, playing);
				} else if (playing.getPlayerOrangeLanternCardCount() > 3) {
					doDedicationFour(gameObj, color, playing);
				}
			}

		}//check this 
		else if (type.equals("GEN")) {
			
			// If anyone of the dedication is possible, do it.
			if(playing.getPossibleDedicationSevenUniqueColor().size() > 6)
			{				
				if (playing.getPlayerBlackLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Black");
					// do dedication after exchange
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerWhiteLanternCardCount() == 0) {
					doExchange(playing, gameObj, "White");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerBlueLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Blue");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerGreenLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Green");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerOrangeLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Orange");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerPurpleLanternCardCount() == 0) {
					doExchange(playing, gameObj, "Purple");
					doDedicationSeven(gameObj, playing);
				} else if (playing.getPlayerRedLanternCardCount() == 0) {

					doExchange(playing, gameObj, "Red");
					doDedicationSeven(gameObj, playing);
				} else {
					doDedicationSeven(gameObj, playing);
				}
				
				System.out.println();
				
				System.out.println("Player " + playing.getPlayerNumber()
						+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
				
				DedicationTokens dedicationObj = gameObj
						.getDedicationTokens();
				Vector<Integer> dedicationSevenVal = dedicationObj
						.getDedicationTokenSeven();
				if (dedicationSevenVal.size() > 0) {
					int dedicationSevenValue = dedicationSevenVal
							.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj.getDedicationTokenSeven()
							.add(dedicationSevenValue);
					if (dedicationSevenVal.isEmpty()) {
						gameObj.setNextDedicationTokenSeven(-1);
					} else {
						gameObj.setNextDedicationTokenSeven(dedicationSevenVal
								.firstElement());
					}
				} else {
					Vector<Integer> genGenericVal = dedicationObj
							.getGenericDedicationTokens();
					if (genGenericVal.size() > 0) {
						int genericValue1 = genGenericVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj
								.getGenericDedicationTokens().add(
										genericValue1);
					}
				}
			}else if(playing.getPossibleDedicationThreePairColor().size() > 3)
			{
				ArrayList<String> possibleThreePair = playing
						.getPossibleDedicationThreePairColor();
				
				int pairCount = 0;
				
				//do dedication with first three available colors.
				for (int i = 0; i < 3; i++) {
					
					if (pairCount == 3) {
						break;
					}
					
					String color = possibleThreePair.get(i);

					if (color.equalsIgnoreCase("Black")) {
						if (playing.getPlayerBlackLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Black");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerBlackLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("White")) {
						if (playing.getPlayerWhiteLanternCardCount() == 1) {
							doExchange(playing, gameObj, "White");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerWhiteLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("Blue")) {
						if (playing.getPlayerBlueLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Blue");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerBlueLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("Green")) {
						if (playing.getPlayerGreenLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Green");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerGreenLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("Purple")) {
						if (playing.getPlayerPurpleLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Purple");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerPurpleLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("Red")) {
						if (playing.getPlayerRedLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Red");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerRedLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					} else if (color.equalsIgnoreCase("Orange")) {
						if (playing.getPlayerOrangeLanternCardCount() == 1) {
							doExchange(playing, gameObj, "Orange");
							// do dedication after exchange
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						} else if (playing.getPlayerOrangeLanternCardCount() > 1) {
							doDedicationSix(gameObj, color, playing);
							
							pairCount = pairCount+1;
						}
					}
				}
				
				if(pairCount == 3){
					DedicationTokens dedicationObj = gameObj
							.getDedicationTokens();
					Vector<Integer> dedicationSixVal = dedicationObj
							.getDedicationTokenSix();
					if (dedicationSixVal.size() > 0) {
						int dedicationSixValue = dedicationSixVal.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getDedicationTokenSix().add(
								dedicationSixValue);
						if (dedicationSixVal.isEmpty()) {
							gameObj.setNextDedicationTokenSix(-1);
						} else {
							gameObj.setNextDedicationTokenSix(dedicationSixVal
									.firstElement());
						}
					} else {
						Vector<Integer> genDedicationVal = dedicationObj
								.getGenericDedicationTokens();
						if (genDedicationVal.size() > 0) {
							int genericValue1 = genDedicationVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj
									.getGenericDedicationTokens().add(
											genericValue1);
						}
					}
				}
				
			}else if(playing.getPossibleDedicationFourUniqueColor().size() > 0){
				
				ArrayList<String> possibleFourUniqueColor = playing
						.getPossibleDedicationFourUniqueColor();
				
				// do dedication with first color available.
				String color = possibleFourUniqueColor.get(0);

				if (color.equalsIgnoreCase("Black")) {
					if (playing.getPlayerBlackLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Black");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerBlackLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("White")) {
					if (playing.getPlayerWhiteLanternCardCount() == 3) {
						doExchange(playing, gameObj, "White");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerWhiteLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Blue")) {
					if (playing.getPlayerBlueLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Blue");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerBlueLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Green")) {
					if (playing.getPlayerGreenLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Green");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerGreenLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Purple")) {
					if (playing.getPlayerPurpleLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Purple");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerPurpleLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Red")) {
					if (playing.getPlayerRedLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Red");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerRedLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				} else if (color.equalsIgnoreCase("Orange")) {
					if (playing.getPlayerOrangeLanternCardCount() == 3) {
						doExchange(playing, gameObj, "Orange");
						// do dedication after exchange
						doDedicationFour(gameObj, color, playing);
					} else if (playing.getPlayerOrangeLanternCardCount() > 3) {
						doDedicationFour(gameObj, color, playing);
					}
				}
			}
		}
	}
	
	/**
	 * @param gameObj
	 * @param playing
	 */
	private static void doDedicationSeven(GameInstance gameObj, Players playing) {
		
		PlayGame.getDedicationType3ColorValidationAndRemoval(
				playing, gameObj);
		
	}
	
	/**
	 * @param gameObj
	 * @param playing
	 */
	private static void doDedicationSix(GameInstance gameObj, String giveColor, Players playing){
		PlayGame.getDedicationType2ColorValidationAndRemoval(
				playing, giveColor, gameObj);
	}
	
	/**
	 * @param gameObj
	 * @param playing
	 */
	private static void doDedicationFour(GameInstance gameObj, String giveColor, Players playing){
		boolean type1Val = PlayGame.getDedicationType1ColorValidationAndRemoval(
				playing, giveColor, gameObj);
		
		if (type1Val) {
			DedicationTokens dedicationObj = gameObj
					.getDedicationTokens();
			Vector<Integer> dedicationVal = dedicationObj
					.getDedicationTokenFour();
			if (dedicationVal.size() > 0) {
				int dedicationValue = dedicationVal.remove(0);
				DedicationTokens playerDedicationObj = playing.getDedicationTokens();
				playerDedicationObj.getDedicationTokenFour().add(
						dedicationValue);
				if (dedicationVal.isEmpty()) {
					gameObj.setNextDedicationTokenFour(-1);
				} else {
					gameObj.setNextDedicationTokenFour(dedicationVal
							.firstElement());
				}
			} else {
				Vector<Integer> genDedicationVal = dedicationObj
						.getGenericDedicationTokens();
				if (genDedicationVal.size() > 0) {
					int genericValue = genDedicationVal.remove(0);
					DedicationTokens playerDedicationObj = playing.getDedicationTokens();
					playerDedicationObj
							.getGenericDedicationTokens().add(
									genericValue);
				}
			}
		}
	}

	public static void doExchange(Players playing, GameInstance gameObj, String getColors)
	{
		//sendColor.. any color whose count is > 1
		
		if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Black"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Black", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Black");
			
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Blue"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Blue", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Blue");
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Red"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Red", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Red");
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Green"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Green", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Green");
			
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Orange"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Orange", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Orange");
			
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("White"))
		{
			//get specified color for player from game
			playing.playerColorAugment("White", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "White");
			
		}else if(playing.getPlayerBlackLanternCardCount() > 1 && !getColors.equalsIgnoreCase("Purple"))
		{
			//get specified color for player from game
			playing.playerColorAugment("Purple", getColors);
			
			//method to remove lantern card color from game and add to player
			gameObj.gameColorAugment(getColors, "Purple");
			
		}
		
		//remove two favor tokens from player
		playing.setPlayerFavorToken(playing.getPlayerFavorToken() - 2);
		
		//add two favor tokens to game.
		gameObj.setGameFavorToken(gameObj.getGameFavorToken() + 2);
		
		
	}

	/**
	 * @param playing
	 */
	private static void playerPossibleExchangeMoves(Players playing) {
		
		if (playing.getPlayerFavorToken() >= 2) {
			for (int i = 0; i < playing.possiblePlayerColorExchangeMoves.size(); i++) {
				for (int j = 0; j < playing.possibleGameColorExchangeMoves
						.size(); j++) {
					if (playing.possiblePlayerColorExchangeMoves.get(i)
							.equalsIgnoreCase(
									playing.possibleGameColorExchangeMoves
											.get(j))) {
						playing.getPossibleExchangeMoves()
								.add(playing.possiblePlayerColorExchangeMoves
										.get(i)
										+ ":"
										+ playing.possibleGameColorExchangeMoves
												.get(j));
					}
				}

			}
		}
	}
	
	private static void dedicationType1(Players playing, GameInstance gameObjs)
	{
		for(int i = 0; i<playing.getPossibleExchangeMoves().size();i++)
		{
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];
			if((((playing.getPlayerBlackLanternCardCount() > 2 && gameExCol.equals("Black"))||(playing.getPlayerBlackLanternCardCount()>3))
				|| ((playing.getPlayerWhiteLanternCardCount() > 2 && gameExCol.equals("White"))||(playing.getPlayerWhiteLanternCardCount() > 3))
				|| ((playing.getPlayerBlueLanternCardCount() > 2 && gameExCol.equals("Blue"))||(playing.getPlayerBlueLanternCardCount() > 3))
				|| ((playing.getPlayerGreenLanternCardCount() > 2 && gameExCol.equals("Green"))||(playing.getPlayerGreenLanternCardCount() > 3))
				|| ((playing.getPlayerOrangeLanternCardCount() > 2 && gameExCol.equals("Orange"))||(playing.getPlayerOrangeLanternCardCount() > 3))
				|| ((playing.getPlayerPurpleLanternCardCount() > 2 && gameExCol.equals("Purple"))||(playing.getPlayerPurpleLanternCardCount() > 3))
				|| ((playing.getPlayerRedLanternCardCount() > 2) && gameExCol.equals("Red") )||(playing.getPlayerRedLanternCardCount() > 3)))
			{
				playing.getPossibleDedicationFourUniqueColor().add(gameExCol);
			}
		}
		
	}
	
	 static void dedicationType2(Players playing, GameInstance gameObjs)
	{
		for(int i = 0; i<playing.getPossibleExchangeMoves().size();i++)
		{
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];
			
			if ((playing.getPlayerBlackLanternCardCount() > 0 && gameExCol
					.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() > 0 && gameExCol
					.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() > 0 && gameExCol
					.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount() > 0 && gameExCol
					.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() > 0 && gameExCol
					.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() > 0 && gameExCol
					.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() > 0 && gameExCol
					.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 1)) {
				playing.getPossibleDedicationThreePairColor().add(gameExCol);
			}
		}
		
	}
	
	private static void dedicationType3(Players playing, GameInstance gameObjs)
	{
		for(int i = 0; i<playing.getPossibleExchangeMoves().size();i++)
		{
			String playerExCol = playing.getPossibleExchangeMoves().get(i).split(":")[0];
			String gameExCol = playing.getPossibleExchangeMoves().get(i).split(":")[1];
			
			if ((playing.getPlayerBlackLanternCardCount() == 0 && gameExCol
					.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() == 0 && gameExCol
					.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() == 0 && gameExCol
					.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount()== 0 && gameExCol
					.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() == 0 && gameExCol
					.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() == 0 && gameExCol
					.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() == 0 && gameExCol
					.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 0)) {
				playing.getPossibleDedicationSevenUniqueColor().add(gameExCol);
			}
		}
		
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
	public static void filterExcessLanternCards(GameInstance gameObj,
			Players playing) 
	{
		
		while (playing.getLanternCardCount() > 12) 
		{
			System.out.println("---------- Lantern Cards"
					+ " Count Check -----------");

			System.out.println("Player" + playing.getPlayerNumber()
					+ " has more than 12 lantern cards.");

			System.out.println();
			
			System.out.println("Player" + playing.getPlayerNumber()
					+ " will Make a dedication.");

			System.out.println();
			
			int fourValue = gameObj.getNextDedicationTokenFour();
			
			int sixValue = gameObj.getNextDedicationTokenSix();
		
			int sevenValue = gameObj.getNextDedicationTokenSeven();
		
			int genericValue = gameObj.getNextGenericDedicationToken();
			
			ArrayList<String> colorPairs = getLanternCardColorPairs(playing);	
			
			ArrayList<String> fourUniqueColorPairs = checkPossibleFourUnique(playing);
			
			//do exchange and dedication if possible
			if (checkPossibleUniqueColor(playing) || (sevenValue > sixValue && sevenValue > fourValue)
					|| (genericValue > sixValue && genericValue > fourValue
					&& genericValue > sevenValue))
			{
					doDedicationSeven(gameObj, playing);
					
					System.out.println();
					
					System.out.println("Player " + playing.getPlayerNumber()
							+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
					
					DedicationTokens dedicationObj = gameObj
							.getDedicationTokens();
					Vector<Integer> dedicationSevenVal = dedicationObj
							.getDedicationTokenSeven();
					if (dedicationSevenVal.size() > 0) {
						int dedicationSevenValue = dedicationSevenVal
								.remove(0);
						DedicationTokens playerDedicationObj = playing.getDedicationTokens();
						playerDedicationObj.getDedicationTokenSeven()
								.add(dedicationSevenValue);
						if (dedicationSevenVal.isEmpty()) {
							gameObj.setNextDedicationTokenSeven(-1);
						} else {
							gameObj.setNextDedicationTokenSeven(dedicationSevenVal
									.firstElement());
						}
					} else {
						Vector<Integer> genGenericVal = dedicationObj
								.getGenericDedicationTokens();
						if (genGenericVal.size() > 0) {
							int genericValue1 = genGenericVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj
									.getGenericDedicationTokens().add(
											genericValue1);
						}
					}
				
			} else if ((colorPairs.size() > 2) || (sixValue > sevenValue && sixValue > fourValue)) {
								
				int pairCount = 0;	
				
				for (int i = 0; i < 3; i++) {
						
						String color = colorPairs.get(i);
						
						doDedicationSix(gameObj, color, playing);
						
						pairCount +=1;
					}		
					
					if(pairCount == 3){
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj
								.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(
									dedicationSixValue);
							if (dedicationSixVal.isEmpty()) {
								gameObj.setNextDedicationTokenSix(-1);
							} else {
								gameObj.setNextDedicationTokenSix(dedicationSixVal
										.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue1 = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue1);
							}
						}
					}
				
			} else if ((fourValue > sixValue && fourValue > sevenValue) || fourUniqueColorPairs.size() > 0) {
												
				int randomNumber = getRandomNumber(fourUniqueColorPairs.size());
				
				String colorFour = fourUniqueColorPairs.get(randomNumber);
					
				doDedicationFour(gameObj, colorFour, playing);
				
			} else if (genericValue > sixValue && genericValue > fourValue
					&& genericValue > sevenValue) {
				
				//doWhatIsAsked(playing, gameObjs, "GEN");
				
				// If anyone of the dedication is possible, do it.
				if(playing.getPossibleDedicationSevenUniqueColor().size() > 6)
				{				
					if (checkPossibleUniqueColor(playing)) {

						doDedicationSeven(gameObj, playing);
						
						System.out.println();
						
						System.out.println("Player " + playing.getPlayerNumber()
								+ " has choosen Type 3 Dedication"+"(Seven unique Lantern Cards).");
						
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSevenVal = dedicationObj
								.getDedicationTokenSeven();
						if (dedicationSevenVal.size() > 0) {
							int dedicationSevenValue = dedicationSevenVal
									.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSeven()
									.add(dedicationSevenValue);
							if (dedicationSevenVal.isEmpty()) {
								gameObj.setNextDedicationTokenSeven(-1);
							} else {
								gameObj.setNextDedicationTokenSeven(dedicationSevenVal
										.firstElement());
							}
						} else {
							Vector<Integer> genGenericVal = dedicationObj
									.getGenericDedicationTokens();
							if (genGenericVal.size() > 0) {
								int genericValue1 = genGenericVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue1);
							}
						}
					}
				}else if(playing.getPossibleDedicationThreePairColor().size() > 3)
				{
					
					int pairCount = 0;
					
					if ((colorPairs.size() > 2)) {
						
						for (int i = 0; i < 3; i++) {
							
							String color = colorPairs.get(i);
							
							doDedicationSix(gameObj, color, playing);
							
							pairCount +=1;
						}	
					}
					
					if(pairCount == 3){
						DedicationTokens dedicationObj = gameObj
								.getDedicationTokens();
						Vector<Integer> dedicationSixVal = dedicationObj
								.getDedicationTokenSix();
						if (dedicationSixVal.size() > 0) {
							int dedicationSixValue = dedicationSixVal.remove(0);
							DedicationTokens playerDedicationObj = playing.getDedicationTokens();
							playerDedicationObj.getDedicationTokenSix().add(
									dedicationSixValue);
							if (dedicationSixVal.isEmpty()) {
								gameObj.setNextDedicationTokenSix(-1);
							} else {
								gameObj.setNextDedicationTokenSix(dedicationSixVal
										.firstElement());
							}
						} else {
							Vector<Integer> genDedicationVal = dedicationObj
									.getGenericDedicationTokens();
							if (genDedicationVal.size() > 0) {
								int genericValue1 = genDedicationVal.remove(0);
								DedicationTokens playerDedicationObj = playing.getDedicationTokens();
								playerDedicationObj
										.getGenericDedicationTokens().add(
												genericValue1);
							}
						}
					}
										
					
				}else if(playing.getPossibleDedicationFourUniqueColor().size() > 0){
					
					if(fourUniqueColorPairs.size() > 0) {
												
						int randomNumber = getRandomNumber(fourUniqueColorPairs.size());
						
						String colorFour = fourUniqueColorPairs.get(randomNumber);
													
						doDedicationFour(gameObj, colorFour, playing);
					}
					
				}
			}
				
		}
		//return opt;
	}
	
	/**
	 * @param playing
	 * @param fourUniqueColorPairs
	 */
	private static ArrayList<String> checkPossibleFourUnique(Players playing) {
		
		ArrayList<String> fourUniqueColorPairs = new ArrayList<String>();
		
		if (playing.getPlayerBlackLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Black");
		}
		else if (playing.getPlayerWhiteLanternCardCount() > 3) {
			fourUniqueColorPairs.add("White");
		}
		else if (playing.getPlayerBlueLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Blue");
		}
		else if (playing.getPlayerGreenLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Green");
		}
		else if (playing.getPlayerPurpleLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Purple");
		}
		else if (playing.getPlayerRedLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Red");
		}
		else if (playing.getPlayerOrangeLanternCardCount() > 3) {
			fourUniqueColorPairs.add("Orange");
		}
		
		return fourUniqueColorPairs;
	}

	/**
	 * @param playing
	 * @return
	 */
	private static boolean checkPossibleUniqueColor(Players playing) {
		return playing.getPlayerBlackLanternCardCount() > 0
					&& playing.getPlayerWhiteLanternCardCount() > 0
					&& playing.getPlayerBlueLanternCardCount() > 0
					&& playing.getPlayerGreenLanternCardCount() > 0
					&& playing.getPlayerOrangeLanternCardCount() > 0
					&& playing.getPlayerPurpleLanternCardCount() > 0
					&& playing.getPlayerRedLanternCardCount() > 0;
	}
	
	/**
	 * @param playing
	 * @param colorPairs
	 * @return 
	 */
	private static ArrayList<String> getLanternCardColorPairs(Players playing) {
		
		ArrayList<String> colorPairs = new ArrayList<String>();
		if (playing.getPlayerBlackLanternCardCount() > 1) {
				
			 colorPairs.add("Black");

		}
		if (playing.getPlayerWhiteLanternCardCount() > 1) {
											
			colorPairs.add("White");							
			
		}
		
		if (playing.getPlayerBlueLanternCardCount() > 1) {
											
			colorPairs.add("Blue");
										
		}if (playing.getPlayerGreenLanternCardCount() > 1) {							
				
			colorPairs.add("Green");
										
		}
		
		if (playing.getPlayerPurpleLanternCardCount() > 1) {
											
			colorPairs.add("Purple");						
									
		} if (playing.getPlayerRedLanternCardCount() > 1) {							
				
			colorPairs.add("Red");
										
		} if (playing.getPlayerOrangeLanternCardCount() > 1) {
											
			colorPairs.add("Orange");
									
		}
		
		return colorPairs;
	}
	
	/** 
	 * This method gets a random number.
	 * @param number: int type
	 * @return random: int type 
	 */
	private static int getRandomNumber(int number){
		
		Random randomNumbers = new Random(0);
				
		int random = randomNumbers.nextInt(number);  //number between 0 to 3
		
		return random;
	}
	
}
