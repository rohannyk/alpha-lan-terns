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
 * GameConfiguration Class.
 * Methods saveGameToFile, saveGameToXml, loadGameFromFile, checkFileExist
 * Methods loadGameState, showTextMode
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

	/**
	 * The default constructor of GameController class.
	 */
	public GameController() {
		
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
	}
	
	
	
	/**
	 * This method will check if the file name is empty or not.
	 * Creates File object.
	 * If file name is empty, Default file name will be assigned.
	 * @param gameFile The name of the file to save.
	 */
	public void saveGameToFile(String fileName){
		File file;
		
		
		if(fileName == null || fileName == ""){
			file = new File("/Users/lovepreet/default_game_save.xml");
		}
		else{
			file = new File(fileName);
		}
		
		saveGameToXml(file);
	}
	
	/**
	 * This method will save GameInstance class object to an 'XML' file.
	 * Creates objects of JAXBContext, Marshaller.
	 * @param gameFile The file name of the save file (exception handled).
	 */
	private void saveGameToXml(File gameFile){
		try {
			JAXBContext context = JAXBContext.newInstance(GameInstance.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(this.gameInstance, gameFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will check if the file name is empty or not.
	 * Creates object of File.
	 * @param fileName The name of the file to save.
	 * @return The GameInstance class object is returned.
	 */
	public GameInstance loadGameFromFile(String fileName) {
		File file = null;
		
		if(fileName == null || fileName == ""){
			
			System.out.println("File Name is empty. Please enter the file name.");
			
			return null;
		}
		else{
			file = new File(fileName);
			
			boolean fileExists = checkFileExist(file);
			
			if(fileExists){
				return loadGameState(file);
			}else{
				return null;
			}
		}

	}
	
	/**
	 * This method checks if the given file exists or not.
	 * @param file of File type.
	 * @return The boolean value to check if file exists or not.
	 */
	private boolean checkFileExist(File file){
		
		//System.out.println(file.exists());
		return file.exists();

	}
	
	/**
	 * This method will load 'XML' file and get the GameInstance class object.
	 * Creates objects of GameInstance, JAXBContext, Unmarshaller.
	 * @param file The file name of the file to be loaded.
	 * @return The GameInstance class object will be returned (exception handled).
	 */
	private GameInstance loadGameState(File file) {
		
		System.out.println("Loading the game.......!");
		
		GameInstance gameInstance = null;
		try {
			JAXBContext context = JAXBContext.newInstance(GameInstance.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			gameInstance = (GameInstance) unmarshaller.unmarshal(file);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		this.gameInstance = gameInstance;
				
		System.out.println("Game Loaded successfully.");
		
		return gameInstance;
	}
	
	/**
	 * This method will print the game state and player state on console.
	 * Prints Number of Players, Start Player, Current Player
	 * Prints Game Lantern Cards Status, Game Dedication Tokens.
	 * Prints Available Lake Tiles in shuffled deck, Shuffled Lake Tiles.
	 * Prints Current Lake Tile Arrangement, Game available Favor Tokens.
	 * Prints Player Number, Number of favor tokens player has.
	 * Prints Lantern Cards Player Holds, Player Lake Tiles.
	 * Prints No of Lake Tiles held, Lake Tiles held, Player Dedication Tokens.
	 * Creates objects of DedicationTokens, Vector<LakeTiles>, LakeTiles, Players[]
	 * @param gameObj of GameInstance type
	 */
	public void showTextMode(GameInstance gameObj) {
		gameObj.showTextMode();
		
	}

}