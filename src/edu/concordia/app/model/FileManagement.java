/**
 * 
 */
package edu.concordia.app.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.concordia.app.model.GameInstance;

/**
 * @author Team E
 *
 */
public class FileManagement {
	
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
	public FileManagement() {
		
	}

	/**
	 * This GameController class constructor will set 
	 * GameConfiguration and GameInstance objects.
	 * @param gameConfig The GameConfiguration class object.
	 * @param gameInstance The GameInstance class object.
	 */
	public FileManagement(GameConfiguration gameConfig,
			GameInstance gameInstance) {
		
		this.gameConfig = gameConfig;
		this.gameInstance = gameInstance;
	}
	
	/**
	 * This method will check if the file name is empty or not.
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
	 * This method will save GameInstance class object to an xml file.
	 * @param gameFile The file name of the save file.
	 */
	private void saveGameToXml(File gameFile){
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
	
	/**
	 * This method will check if the file name is empty or not.
	 * @param fileName The name of the file to save.
	 * @return The GameInstance class object will be returned.
	 */
	public GameInstance loadGameFromFile(String fileName) {
		File file = null;
		
		if(fileName == null || fileName == ""){
			
			System.out.println("File Name is empty. Please enter the file name.");
			
			return null;
			//file = new File("/Users/lovepreet/default_game_load.xml");
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
		
		//return loadGameState(file);
	}
	
	/**
	 * @param file 
	 * @return The boolean value to check if file exists or not.
	 */
	private boolean checkFileExist(File file){
		
		//System.out.println(file.exists());
		
		return file.exists();
		//return true;
	}
	
	/**
	 * This method will load xml file and get the GameInstance class object.
	 * @param file The file name of the file to be loaded.
	 * @return The GameInstance class object will be returned.
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
		
		//System.out.println("Verifying game configuration....");
		
		//verifyLoadedGameState();
		
		System.out.println("Game Loaded successfully.");
		
		return gameInstance;
	}

}
