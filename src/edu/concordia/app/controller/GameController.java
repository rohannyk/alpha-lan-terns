/**
 * 
 */
package edu.concordia.app.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LakeTiles;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * @author Team E
 *
 */
public class GameController {
	
	private GameConfiguration gameConfig;
	
	private GameInstance gameInstance;

	/**
	 * 
	 */
	public GameController() {
		
	}

	/**
	 * @param gameConfig
	 * @param gameInstance
	 */
	public GameController(GameConfiguration gameConfig,
			GameInstance gameInstance) {
		
		this.gameConfig = gameConfig;
		this.gameInstance = gameInstance;
	}
	
	public String getFileName(){
		System.out.println("Please enter file name : ");
		String s = null;
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    s = bufferRead.readLine();
	 
		    System.out.println(s);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return s;
	}
	
	/**
	 * @param gameFile
	 */
	public void saveGameToFile(String fileName){
		File file;
		//fileName = getFileName();
		/*System.out.println("Please enter file name : ");
		 
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();
	 
		    System.out.println(s);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}*/
		
		if(fileName == null || fileName == ""){
			file = new File("/Users/lovepreet/default_game_save.xml");
		}
		else{
			file = new File(fileName);
		}
		
		saveGameToXml(file);
	}
	
	/**
	 * @param gameFile
	 */
	public void saveGameToXml(File gameFile){
		try {
			JAXBContext context = JAXBContext.newInstance(GameInstance.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//marshaller.marshal(this.gameInstance, System.out);
			marshaller.marshal(this.gameInstance, gameFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GameInstance loadGameFromFile(String fileName) {
		File file;
		
		if(fileName == null || fileName == ""){
			file = new File("/Users/lovepreet/default_game_load.xml");
		}
		else{
			file = new File(fileName);
		}
		
		return loadGameState(file);
	}
	
	public GameInstance loadGameState(File file) {
		
		GameInstance gameInstance = null;
		try {
			JAXBContext context = JAXBContext.newInstance(GameInstance.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			gameInstance = (GameInstance) unmarshaller.unmarshal(file);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		this.gameInstance = gameInstance;
		
		return gameInstance;
	}

	/**
	 * 
	 */
	public void showTextMode() {
		System.out.println("Game Status in Text Mode:");
		System.out.println();
		System.out.println("Number of Players: " +gameInstance.getNoOfPlayers());
		System.out.println();
		System.out.println("Current Player: " +gameInstance.getPlayerCurrentTurn().getPlayerNumber());
		System.out.println();
		System.out.println("-----Lantern Cards-----");
		System.out.println();
		System.out.println("Red Lantern Cards Left: "+gameInstance.getGameRedLanternCardCount());
		
		System.out.println("Blue Lantern Cards Left: "+gameInstance.getGameBlueLanternCardCount());
		System.out.println("Black Lantern Cards Left: "+gameInstance.getGameBlackLanternCardCount());
		System.out.println("Green Lantern Cards Left: "+gameInstance.getGameGreenLanternCardCount());
		System.out.println("Orange Lantern Cards Left: "+gameInstance.getGameOrangeLanternCardCount());
		System.out.println("Purple Lantern Cards Left: "+gameInstance.getGamePurpleLanternCardCount());
		System.out.println("White Lantern Cards Left: "+gameInstance.getGameWhiteLanternCardCount());
		System.out.println();
		System.out.println("-----Dedication Tokens-----");
		System.out.println();
		System.out.println("Next Dedication Token Four: " +gameInstance.getNextDedicationTokenFour());
		System.out.println("Next Dedication Token Six: "+gameInstance.getNextDedicationTokenSix());
		System.out.println("Next Dedication Token Seven: "+gameInstance.getNextDedicationTokenSeven());
		System.out.println("Next Genric Dedication Token: "+gameInstance.getNextGenericDedicationToken());
		System.out.println();
		System.out.println("-----Dedication Tokens Count-----");
		System.out.println();
		
		DedicationTokens deTokens = gameInstance.getDedicationTokens();
		
		System.out.println("Dedication Token Four: " +deTokens.getDedicationTokenFourSize());
		System.out.println("Dedication Token Six: "+deTokens.getDedicationTokenSixSize());
		System.out.println("Dedication Token Seven: "+deTokens.getDedicationTokenSevenSize());
		System.out.println("Genric Dedication Token: "+deTokens.getGenericDedicationTokensSize());
		System.out.println();
		System.out.println("Available Lake Tiles in shuffled deck  : "+gameInstance.getGameTilesDrawPile().size());
		System.out.println();
		
		System.out.println("-----Shuffled Lake Tiles-----");
		Vector<LakeTiles> drawTile = gameInstance.getGameTilesDrawPile();
		
		for (int i = 0; i < drawTile.size(); i++) {
			LakeTiles currTile= drawTile.get(i);
			System.out.println("Lake Tile Number: "+currTile.getTilesId());
		}
		
		System.out.println();
		System.out.println("-----Current Lake Tile Arrangement-----");
		System.out.println();
		Vector<LakeTiles> tileArrangement = gameInstance.getCurrentLakeTilesArrangement();
		System.out.println("No of Lake Tile: "+tileArrangement.size());
		System.out.println("Lake Tile Number Id: "+tileArrangement.get(0).getTilesId());
		System.out.println();
		System.out.println("Available Favor Tokens: "+gameInstance.getGameFavorToken());
		System.out.println();
		System.out.println("-----Players Status-----");
		Players[] playersArray = gameInstance.getPlayersList();
		
		for (int i = 0; i < playersArray.length; i++) {
			Players player = playersArray[i];
			System.out.println();
			System.out.println("Player Number: "+player.getPlayerNumber());
			System.out.println("Number of favor tokens player has: "+player.getPlayerFavorToken());
			System.out.println();
			System.out.println("-------Lantern Cards Player Holds-----");
			System.out.println();
			System.out.println("Red Lantern Cards Left: "+player.getPlayerRedLanternCardCount());
			System.out.println("Blue Lantern Cards Left: "+player.getPlayerBlueLanternCardCount());
			System.out.println("Black Lantern Cards Left: "+player.getPlayerBlackLanternCardCount());
			System.out.println("Green Lantern Cards Left: "+player.getPlayerGreenLanternCardCount());
			System.out.println("Orange Lantern Cards Left: "+player.getPlayerOrangeLanternCardCount());
			System.out.println("Purple Lantern Cards Left: "+player.getPlayerPurpleLanternCardCount());
			System.out.println("White Lantern Cards Left: "+player.getPlayerWhiteLanternCardCount());
			
			System.out.println();
			System.out.println("-----Player Lake Tiles -----");
			System.out.println();
			Vector<LakeTiles> lakeTiles = player.getCurrentLakeTilesHold();
			System.out.println("No of Lake Tiles holded: "+lakeTiles.size());
			for (int j = 0; j < lakeTiles.size(); j++) {
				LakeTiles tileHolded = lakeTiles.get(j);
				System.out.println("Lake Tiles holded "+tileHolded.getTilesId());
			}
			
			System.out.println("-----Player Dedication Tokens -----");
			DedicationTokens playerDedicationTokens = player.getDedicationTokens();
			
			Vector<Integer> tokenFour = playerDedicationTokens
					.getDedicationTokenFour();
			if (tokenFour.size() == 0) {
				System.out.println("Primary Dedication Tokens Four: "+tokenFour.size());
			} else {

				for (int j = 0; j < tokenFour.size(); j++) {
					System.out.println("Primary Dedication Tokens Four Size: "+tokenFour.size());
					System.out.println("Primary Dedication Tokens Four: "+tokenFour.get(j));
				}
			}
			
			Vector<Integer> tokenSix = playerDedicationTokens.getDedicationTokenSix();
			
			if (tokenSix.size() == 0) {
				System.out.println("Primary Dedication Tokens Six: "+tokenSix.size());
			} else {

				for (int j = 0; j < tokenSix.size(); j++) {
					System.out.println("Primary Dedication Tokens Six Size: "+tokenSix.size());
					System.out.println("Primary Dedication Tokens Six: "+tokenSix.get(j));
				}
			}
			
			Vector<Integer> tokenSeven = playerDedicationTokens.getDedicationTokenSeven();
			
			if (tokenSeven.size() == 0) {
				System.out.println("Primary Dedication Tokens Seven: "+tokenSeven.size());
			} else {

				for (int j = 0; j < tokenSeven.size(); j++) {
					System.out.println("Primary Dedication Tokens Seven Size: "+tokenSeven.size());
					System.out.println("Primary Dedication Tokens Seven: "+tokenSeven.get(j));
				}
			}
			
			Vector<Integer> genericToken = playerDedicationTokens.getGenericDedicationTokens();
			
			if (genericToken.size() == 0) {
				System.out.println("Generic Dedication Tokens: "+genericToken.size());
			} else {

				for (int j = 0; j < genericToken.size(); j++) {
					System.out.println("Generic Dedication Tokens size: "+genericToken.size());
					System.out.println("Generic Dedication Tokens: "+genericToken.get(j));
				}
			}
			
			System.out.println();
		}
		System.out.println();
		
	}

}
