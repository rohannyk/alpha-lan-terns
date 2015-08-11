/**
 * 
 */
package edu.concordia.app.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.PlayGame;
import edu.concordia.app.model.Players;
import edu.concordia.app.view.LanternGameView;

/**
 * @author lovepreet
 *
 */
public class GreedyPlayerStrategy extends PlayerStrategy {

	private GameInstance gameObj;
	
	private Players playing;
	
	/**
	 * @param gameObj
	 */
	public GreedyPlayerStrategy(GameInstance gameObj) {
		super();
		this.gameObj = gameObj;
	}
	

	
	private static Scanner scan = LanternMain.getValue();
	
	
	
	public void playerAvailableColorCards(Players playing) 
	{
		if (playing.getPlayerBlackLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Black");
		}

		if (playing.getPlayerWhiteLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("White");
		}

		if (playing.getPlayerBlueLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Blue");
		}

		if (playing.getPlayerGreenLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Green");
		}

		if (playing.getPlayerOrangeLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Orange");
		}

		if (playing.getPlayerRedLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Red");
		}

		if (playing.getPlayerPurpleLanternCardCount() > 0) {
			playing.possiblePlayerColorExchangeMoves.add("Purple");
		}
	}
	
	/*public void gameAvailableColorCards(GameInstance gameObjs){
		if (gameObjs.getGameBlackLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Black");
		}

		if (gameObjs.getGameWhiteLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("White");
		}

		if (gameObjs.getGameBlueLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Blue");
		}

		if (gameObjs.getGameGreenLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Green");
		}

		if (gameObjs.getGameOrangeLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Orange");
		}

		if (gameObjs.getGameRedLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Red");
		}

		if (gameObjs.getGamePurpleLanternCardCount() > 0) {
			possibleGameColorExchangeMoves.add("Purple");
		}
	}*/
	
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

				} else if ((colorPairs.size() > 2)
						|| (sixValue > sevenValue && sixValue > fourValue)) {

					for (int i = 0; i < 3; i++) {

						String color = colorPairs.get(i);

						doDedicationSix(gameObj, color, playing);
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
					if (playing.possibleDedicationSevenUniqueColor.size() > 6) {
						if (checkPossibleUniqueColor(playing)) {

							doDedicationSeven(gameObj, playing);
						}
					} else if (playing.possibleDedicationThreePairColor.size() > 3) {

						if ((colorPairs.size() > 2)) {

							for (int i = 0; i < 3; i++) {

								String color = colorPairs.get(i);

								doDedicationSix(gameObj, color, playing);
							}
						}

					} else if (playing.possibleDedicationFourUniqueColor.size() > 0) {

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
	
	public String makeAMove(Scanner scan, GameInstance gameObjs, Players playing, String option)
	{
		
			this.playing = playing;
				
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
			if(sevenValue > sixValue && sevenValue > fourValue && playing.possibleDedicationSevenUniqueColor.size() > 6)
			{				
				doWhatIsAsked( playing,  gameObjs,  "SEVEN");
			}else if(sixValue > sevenValue && sixValue > fourValue && playing.possibleDedicationThreePairColor.size() > 3)
			{
				doWhatIsAsked( playing,  gameObjs,  "SIX");
			}else if(fourValue > sixValue && fourValue > sevenValue && playing.possibleDedicationFourUniqueColor.size() > 0)
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
				
				GreedyPlayerStrategy.filterExcessLanternCards(gameObjs, playing);

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
				
				for (int i = startHorizontal; i < endHorizontal + 1; i++) {
					for (int j = startVertial; j < endVertial + 1; j++) {
						if ((gameObjs.GameBoard[i][j]) != 99){
							
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
			
//			Map<String,Integer> playerMatchingCardColors = new HashMap<String,Integer>();
			Vector<String> tilePlacementCominationResults = new Vector<String>();
			
			
			
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
				
				for (int j = 0; j < possibleAdjacentBoardLakeTiles.size(); j++) {
					
					String tilesAdjacentCombination[] = possibleAdjacentBoardLakeTiles.get(j).split(":");
					
					LakeTiles tileOnBoard = totalLakeTilesStack.get(Integer.valueOf(tilesAdjacentCombination[0]));
					
					//add adjacent tile id
					//placementCombination += new String(":"+tilesAdjacentCombination[0]);
					
					for (int k = 1; k < tilesAdjacentCombination.length; k++) {
						String tilePosition = tilesAdjacentCombination[k];
						
						if(tilePosition.equals("L")){
							
							playerRedMatchingCardColors = 0;
							playerWhiteMatchingCardColors = 0;
							playerOrangeMatchingCardColors = 0;
							playerGreenMatchingCardColors = 0;
							playerPurpleMatchingCardColors = 0;
							playerBlackMatchingCardColors = 0;
							playerBlueMatchingCardColors = 0;
							
							//add adjacent position
							placementCombination += new String(":"+tilesAdjacentCombination[0]+":"+"L");
							
							//if two tiles color matches
							if (tileOnBoard.getLeftColor().equalsIgnoreCase(
									lakeTileChoice.getRightColor())) {
								
								String matchingColor = tileOnBoard.getLeftColor();
								
								if(matchingColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
									playerRedMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
									playerBlueMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
									playerBlackMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
									playerPurpleMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
									playerWhiteMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
									playerOrangeMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
									playerGreenMatchingCardColors += 1;
								}
								
							}
							
							//increment player face color
							
							if(playerCardFaceColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
								playerRedMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
								playerBlueMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
								playerBlackMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
								playerPurpleMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
								playerWhiteMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
								playerOrangeMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
								playerGreenMatchingCardColors += 1;
							}
							
							//add card colors and count
							placementCombination += new String(":"+"Red"+":"+playerRedMatchingCardColors);
							placementCombination += new String(":"+"Blue"+":"+playerBlueMatchingCardColors);
							placementCombination += new String(":"+"Black"+":"+playerBlackMatchingCardColors);
							placementCombination += new String(":"+"Purple"+":"+playerPurpleMatchingCardColors);
							placementCombination += new String(":"+"White"+":"+playerWhiteMatchingCardColors);
							placementCombination += new String(":"+"Orange"+":"+playerOrangeMatchingCardColors);
							placementCombination += new String(":"+"Green"+":"+playerGreenMatchingCardColors);
							
							//add result string to vector
							tilePlacementCominationResults.add(placementCombination);
							
						}else if(tilePosition.equals("R")){
							
							playerRedMatchingCardColors = 0;
							playerWhiteMatchingCardColors = 0;
							playerOrangeMatchingCardColors = 0;
							playerGreenMatchingCardColors = 0;
							playerPurpleMatchingCardColors = 0;
							playerBlackMatchingCardColors = 0;
							playerBlueMatchingCardColors = 0;
							
							placementCombination = placedTileCombinations.get(i);
							
							//add adjacent position
							placementCombination += new String(":"+tilesAdjacentCombination[0]+":"+"R");
							
							//if two tiles color matches
							if (tileOnBoard.getRightColor().equalsIgnoreCase(
									lakeTileChoice.getLeftColor())) {
								
								String matchingColor = tileOnBoard.getRightColor();
								
								if(matchingColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
									playerRedMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
									playerBlueMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
									playerBlackMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
									playerPurpleMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
									playerWhiteMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
									playerOrangeMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
									playerGreenMatchingCardColors += 1;
								}
							}
							
							//increment player face color
							
							if(playerCardFaceColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
								playerRedMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
								playerBlueMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
								playerBlackMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
								playerPurpleMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
								playerWhiteMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
								playerOrangeMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
								playerGreenMatchingCardColors += 1;
							}
							
							//add card colors and count
							placementCombination += new String(":"+"Red"+":"+playerRedMatchingCardColors);
							placementCombination += new String(":"+"Blue"+":"+playerBlueMatchingCardColors);
							placementCombination += new String(":"+"Black"+":"+playerBlackMatchingCardColors);
							placementCombination += new String(":"+"Purple"+":"+playerPurpleMatchingCardColors);
							placementCombination += new String(":"+"White"+":"+playerWhiteMatchingCardColors);
							placementCombination += new String(":"+"Orange"+":"+playerOrangeMatchingCardColors);
							placementCombination += new String(":"+"Green"+":"+playerGreenMatchingCardColors);
							
							//add result string to vector
							tilePlacementCominationResults.add(placementCombination);
							
						}else if(tilePosition.equals("T")){
							
							playerRedMatchingCardColors = 0;
							playerWhiteMatchingCardColors = 0;
							playerOrangeMatchingCardColors = 0;
							playerGreenMatchingCardColors = 0;
							playerPurpleMatchingCardColors = 0;
							playerBlackMatchingCardColors = 0;
							playerBlueMatchingCardColors = 0;
							
							placementCombination = placedTileCombinations.get(i);
							
							//add adjacent position
							placementCombination += new String(":"+tilesAdjacentCombination[0]+":"+"T");
							
							//if two tiles color matches
							if (tileOnBoard.getTopColor().equalsIgnoreCase(
									lakeTileChoice.getBottomColor())) {
								String matchingColor = tileOnBoard.getTopColor();
								
								if(matchingColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
									playerRedMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
									playerBlueMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
									playerBlackMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
									playerPurpleMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
									playerWhiteMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
									playerOrangeMatchingCardColors += 1;
								}
								
								if(matchingColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
									playerGreenMatchingCardColors += 1;
								}
							}
							
							//increment player face color
							
							if(playerCardFaceColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
								playerRedMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
								playerBlueMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
								playerBlackMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
								playerPurpleMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
								playerWhiteMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
								playerOrangeMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
								playerGreenMatchingCardColors += 1;
							}
							
							//add card colors and count
							placementCombination += new String(":"+"Red"+":"+playerRedMatchingCardColors);
							placementCombination += new String(":"+"Blue"+":"+playerBlueMatchingCardColors);
							placementCombination += new String(":"+"Black"+":"+playerBlackMatchingCardColors);
							placementCombination += new String(":"+"Purple"+":"+playerPurpleMatchingCardColors);
							placementCombination += new String(":"+"White"+":"+playerWhiteMatchingCardColors);
							placementCombination += new String(":"+"Orange"+":"+playerOrangeMatchingCardColors);
							placementCombination += new String(":"+"Green"+":"+playerGreenMatchingCardColors);
							
							//add result string to vector
							tilePlacementCominationResults.add(placementCombination);
							
						}else if(tilePosition.equals("B")){
							
							playerRedMatchingCardColors = 0;
							playerWhiteMatchingCardColors = 0;
							playerOrangeMatchingCardColors = 0;
							playerGreenMatchingCardColors = 0;
							playerPurpleMatchingCardColors = 0;
							playerBlackMatchingCardColors = 0;
							playerBlueMatchingCardColors = 0;
							
							placementCombination = placedTileCombinations.get(i);
							
							//add adjacent position
							placementCombination += new String(":"+tilesAdjacentCombination[0]+":"+"B");
							
							//if two tiles color matches
							if (tileOnBoard.getBottomColor().equalsIgnoreCase(
									lakeTileChoice.getTopColor())) {
								String matchingColor = tileOnBoard.getBottomColor();
								
								if(playerCardFaceColor.equalsIgnoreCase("Red") && gameObjs.getGameRedLanternCardCount() > 0){
									playerRedMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("Blue") && gameObjs.getGameBlueLanternCardCount() > 0){
									playerBlueMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("Black") && gameObjs.getGameBlackLanternCardCount() > 0){
									playerBlackMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("Purple") && gameObjs.getGamePurpleLanternCardCount() > 0){
									playerPurpleMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("White") && gameObjs.getGameWhiteLanternCardCount() > 0){
									playerWhiteMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("Orange") && gameObjs.getGameOrangeLanternCardCount() > 0){
									playerOrangeMatchingCardColors += 1;
								}
								
								if(playerCardFaceColor.equalsIgnoreCase("Green") && gameObjs.getGameGreenLanternCardCount() > 0){
									playerGreenMatchingCardColors += 1;
								}
							}
							
							//increment player face color
							
							if(playerCardFaceColor.equalsIgnoreCase("Red")){
								playerRedMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Blue")){
								playerBlueMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Black")){
								playerBlackMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Purple")){
								playerPurpleMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("White")){
								playerWhiteMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Orange")){
								playerOrangeMatchingCardColors += 1;
							}
							
							if(playerCardFaceColor.equalsIgnoreCase("Green")){
								playerGreenMatchingCardColors += 1;
							}
							
							//add card colors and count
							placementCombination += new String(":"+"Red"+":"+playerRedMatchingCardColors);
							placementCombination += new String(":"+"Blue"+":"+playerBlueMatchingCardColors);
							placementCombination += new String(":"+"Black"+":"+playerBlackMatchingCardColors);
							placementCombination += new String(":"+"Purple"+":"+playerPurpleMatchingCardColors);
							placementCombination += new String(":"+"White"+":"+playerWhiteMatchingCardColors);
							placementCombination += new String(":"+"Orange"+":"+playerOrangeMatchingCardColors);
							placementCombination += new String(":"+"Green"+":"+playerGreenMatchingCardColors);
							
							//add result string to vector
							tilePlacementCominationResults.add(placementCombination);
						}
						
					}
					
				}
			}
				
			String tileMove="";
			for (int i = 0; i < tilePlacementCominationResults.size(); i++) {
				String combinationResult = tilePlacementCominationResults.get(i);
				
				String result[] = combinationResult.split(":");
				
				System.out.println(combinationResult);
				String res = genValidationGreedy(gameObjs,playing,combinationResult);
				
				if(res.contains("type"))
				{
					tileMove = combinationResult;
					break;					
				}			
				
			}
			
			if(tileMove.equals("")){
				int selectedMove = getRandomNumber(tilePlacementCominationResults.size());
				tileMove = tilePlacementCominationResults.get(selectedMove);
			}
			
			//Check dedication possible.
		
			LakeTiles tileInHand = placeALakeTileAndDistribute(gameObjs, playing, tileMove);
				
				

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
	
	public static LakeTiles placeALakeTileAndDistribute(GameInstance gameObjs, Players playing, String comRes)
	{
		String [] placingInformation = comRes.split(":");
		System.out.println(placingInformation[0]);
		
		LakeTiles tileInHand = null;
		
		//LakeTiles tileInHand = playing.getCurrentLakeTilesHold().get(Integer.parseInt(placingInformation[0]));
		
		for (Iterator<LakeTiles> iterator = playing.getCurrentLakeTilesHold().iterator(); iterator.hasNext();) {
			LakeTiles tiles = (LakeTiles) iterator.next();	
			
			if(tiles.getTilesId() == Integer.parseInt(placingInformation[0])){
				tileInHand = tiles;
			}
		}
		
		tileInHand = PlayGame.rotateLakeTileByDegree(tileInHand, placingInformation[1]);
		int x = 0;
		int y = 0;
		for(int i=0;i<73;i++)
		{
			for(int j=0;j<73;j++)
			{
				if(gameObjs.GameBoard[i][j]==Integer.parseInt(placingInformation[2]))
				{
					y = i;
					x = j;
					break;
				}
			}
		}
		if (placingInformation[3].equals("L"))
		{
			x = x-1;
		}else if (placingInformation[3].equals("R"))
		{
			x = x+1;
		}else if (placingInformation[3].equals("T"))
		{
			y = y-1;
		}else if (placingInformation[3].equals("B"))
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
			if(playing.possibleDedicationSevenUniqueColor.size() > 6)
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
			}else if(playing.possibleDedicationThreePairColor.size() > 3)
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
				
			}else if(playing.possibleDedicationFourUniqueColor.size() > 0){
				
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
		PlayGame.getDedicationType1ColorValidationAndRemoval(
				playing, giveColor, gameObj);
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
						playing.possibleExchangeMoves
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
		for(int i = 0; i<playing.possibleExchangeMoves.size();i++)
		{
			String playerExCol = playing.possibleExchangeMoves.get(i).split(":")[0];
			String gameExCol = playing.possibleExchangeMoves.get(i).split(":")[1];
			if((((playing.getPlayerBlackLanternCardCount() > 2 && gameExCol.equals("Black"))||(playing.getPlayerBlackLanternCardCount()>3))
				|| ((playing.getPlayerWhiteLanternCardCount() > 2 && gameExCol.equals("White"))||(playing.getPlayerWhiteLanternCardCount() > 3))
				|| ((playing.getPlayerBlueLanternCardCount() > 2 && gameExCol.equals("Blue"))||(playing.getPlayerBlueLanternCardCount() > 3))
				|| ((playing.getPlayerGreenLanternCardCount() > 2 && gameExCol.equals("Green"))||(playing.getPlayerGreenLanternCardCount() > 3))
				|| ((playing.getPlayerOrangeLanternCardCount() > 2 && gameExCol.equals("Orange"))||(playing.getPlayerOrangeLanternCardCount() > 3))
				|| ((playing.getPlayerPurpleLanternCardCount() > 2 && gameExCol.equals("Purple"))||(playing.getPlayerPurpleLanternCardCount() > 3))
				|| ((playing.getPlayerRedLanternCardCount() > 2) && gameExCol.equals("Red") )||(playing.getPlayerRedLanternCardCount() > 3)))
			{
				playing.possibleDedicationFourUniqueColor.add(gameExCol);
			}
		}
		
	}
	
	 static void dedicationType2(Players playing, GameInstance gameObjs)
	{
		for(int i = 0; i<playing.possibleExchangeMoves.size();i++)
		{
			String playerExCol = playing.possibleExchangeMoves.get(i).split(":")[0];
			String gameExCol = playing.possibleExchangeMoves.get(i).split(":")[1];
			
			if ((playing.getPlayerBlackLanternCardCount() > 0 && gameExCol
					.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() > 0 && gameExCol
					.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() > 0 && gameExCol
					.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount() > 0 && gameExCol
					.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() > 0 && gameExCol
					.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() > 0 && gameExCol
					.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() > 0 && gameExCol
					.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 1)) {
				playing.possibleDedicationThreePairColor.add(gameExCol);
			}
		}
		
	}
	
	private static void dedicationType3(Players playing, GameInstance gameObjs)
	{
		for(int i = 0; i<playing.possibleExchangeMoves.size();i++)
		{
			String playerExCol = playing.possibleExchangeMoves.get(i).split(":")[0];
			String gameExCol = playing.possibleExchangeMoves.get(i).split(":")[1];
			
			if ((playing.getPlayerBlackLanternCardCount() == 0 && gameExCol
					.equals("Black"))
					|| (playing.getPlayerBlackLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerWhiteLanternCardCount() == 0 && gameExCol
					.equals("White"))
					|| (playing.getPlayerWhiteLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerBlueLanternCardCount() == 0 && gameExCol
					.equals("Blue"))
					|| (playing.getPlayerBlueLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerGreenLanternCardCount()== 0 && gameExCol
					.equals("Green"))
					|| (playing.getPlayerGreenLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerOrangeLanternCardCount() == 0 && gameExCol
					.equals("Orange"))
					|| (playing.getPlayerOrangeLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerPurpleLanternCardCount() == 0 && gameExCol
					.equals("Purple"))
					|| (playing.getPlayerPurpleLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
			}
			if ((playing.getPlayerRedLanternCardCount() == 0 && gameExCol
					.equals("Red"))
					|| (playing.getPlayerRedLanternCardCount() > 0)) {
				playing.possibleDedicationSevenUniqueColor.add(gameExCol);
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
				
			} else if ((colorPairs.size() > 2) || (sixValue > sevenValue && sixValue > fourValue)) {
								
					for (int i = 0; i < 3; i++) {
						
						String color = colorPairs.get(i);
						
						doDedicationSix(gameObj, color, playing);
					}					
				
			} else if ((fourValue > sixValue && fourValue > sevenValue) || fourUniqueColorPairs.size() > 0) {
												
				int randomNumber = getRandomNumber(fourUniqueColorPairs.size());
				
				String colorFour = fourUniqueColorPairs.get(randomNumber);
					
				doDedicationFour(gameObj, colorFour, playing);
				
			} else if (genericValue > sixValue && genericValue > fourValue
					&& genericValue > sevenValue) {
				
				//doWhatIsAsked(playing, gameObjs, "GEN");
				
				// If anyone of the dedication is possible, do it.
				if(playing.possibleDedicationSevenUniqueColor.size() > 6)
				{				
					if (checkPossibleUniqueColor(playing)) {

						doDedicationSeven(gameObj, playing);
					}
				}else if(playing.possibleDedicationThreePairColor.size() > 3)
				{
					
					if ((colorPairs.size() > 2)) {
						
						for (int i = 0; i < 3; i++) {
							
							String color = colorPairs.get(i);
							
							doDedicationSix(gameObj, color, playing);
						}	
					}
										
					
				}else if(playing.possibleDedicationFourUniqueColor.size() > 0){
					
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





	/* (non-Javadoc)
	 * @see edu.concordia.app.strategy.PlayerStrategy#makeAMove()
	 
	@Override
	public String makeAMove(Scanner scan, Players playing, String opt) {
		System.out.println("Greedy Strategy");
		return opt;

	}*/

}