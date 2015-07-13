/**
 * 
 */
package edu.concordia.app.controller;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;



import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;

import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

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
	
	/**
	 * @param gameFile
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
	 * @param gameFile
	 */
	public void saveGameToXml(File gameFile){
		try {
			JAXBContext context = JAXBContext.newInstance(GameInstance.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			marshaller.marshal(this.gameInstance, System.out);
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
		
		return gameInstance;
	}

}
