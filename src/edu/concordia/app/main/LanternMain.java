/**
 * 
 */
package edu.concordia.app.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.concordia.app.controller.GameController;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;
/**
 * @author Team E
 */

/**
 * This is the main GUI class.
 */
//public class LanternMain extends JFrame implements ActionListener {
public class LanternMain{
	private static Scanner scan = new Scanner(System.in);
	/**
	 * 
	 */
	private static final long serialVersionUID = 2550694388930175952L;
	
	private static int playerNumber;
	
	private static boolean gameLoop = true;

	/**
	 * Constructor for an LanternMain.
	 */
	public LanternMain() {
		
		//super("LANTERN:The Harvest Festival");
		
		//createAndShowGui();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		LanternMain mainObj = new LanternMain();
		
		System.out.println("test main "+playerNumber);
		
		System.out.println("Welcome! to the");
		System.out.println("Lantern: The Harvest Festival Game");
		System.out.println("-----------------------------");
		System.out.println("Press 0 for New Game.");
		System.out.println("Press 1 to Load saved Game.");
		System.out.println("Press 2 to Exit Game.");
		
		GameController gameController = null;
	    String response = scan.next();
	    scan.nextLine();
			
			if(response.equals("0")){
				
				int playerNumber = mainObj.getInputNoOfPlayers();
				
				//String fileName = mainObj.getSaveFileName();
				
				GameConfiguration config = new GameConfiguration(playerNumber);
				
				GameInstance gameObj = new GameInstance(config);
								
				
				 gameController = new GameController(config, gameObj);
				
				 gameController.showTextMode();
				
				//GameInstance gameInstance =  new GameController(config, gameObj).loadGameFromFile("/Users/lovepreet/save.xml");
				
			}else if(response.equals("1")){
				
				gameController =  new GameController();
				String fileName = mainObj.getLoadFileName();
				
				GameInstance instance = gameController.loadGameFromFile(fileName);
				
				gameController.showTextMode();
				
//				GameInstance instance = gameController.loadGameFromFile("/Users/lovepreet/save.xml");
				
				/*Players[] play = instance.getPlayersList();
				for (int j = 0; j < play.length; j++) {
					Players p = play[j];
					System.out.println(p.getPlayerNumber());
					System.out.println(p.getFaceColor());
				}*/
				//System.out.println(i);
				
				gameController.showTextMode();
				
		} else if (response.equals("2")) {

			System.exit(0);
		}
			
			
			System.out.println("3:Do you want to save the game?(yes/no)");
			//System.out.println("4:Show game state in text mode.");	
			System.out.println("Please enter your choice: ");
			
			String userChoice = scan.next();
			
			if(userChoice.equals("yes")){
				
				System.out.println("Please enter the file name?");
				
				String fileName = scan.next();
				
				gameController.saveGameToFile(fileName);
			}else if(userChoice.equals("no")){
				System.exit(0);
			}
			
//			if(getValue().equals("yes")){			
//			System.out.println(stringsValue);}
		   
			//scanner.close();
	}
	
	static String getValue()
	{

		return scan.next();
	}
	
	/**
	 * @return
	 */
	private String getSaveFileName() {
		int maxPlayers = 4;
		String input ="";
						
	   
	    
		do {
			System.out.print("Please enter the file name :");
//			while (!scanner.hasNextInt()) {
//				System.out.println("Invalid input! Please try again:");
//				scanner.next();
//				System.out.print("Please enter the no of players (between 2 and 4) :");
//			}
			
			input = scan.nextLine();
			
			/*if(input < 0 || input > maxPlayers){
				System.out.println("Input must be within the specified range! Please try again:");
			}*/
			
		} while (!input.isEmpty());
		

		
		return input;
	}		
	
//	public int FetchNumOfPlayers(){
//		FetchPlayerNumber playerNumberObj = new FetchPlayerNumber();
//		playerNumber = playerNumberObj.getNumberOfPlayers();
//		System.out.println("test main "+playerNumber);
//		
//		if(playerNumber >= 2){
//			//playerNumberObj.dispose();
//		}
//		
//		return playerNumber;
//	}
	
	/**
	 * Gets the user to input a valid no of players (no of players between 2 and 4)
	 * @return The no of players as input by the user (validated).
	 */
	public int getInputNoOfPlayers(){
		int maxPlayers = 4;
		int input;
						
	  
	    
		do {
			System.out.print("Please enter the no of players (between 2 and 4) :");
			while (!scan.hasNextInt()) {
				System.out.println("Invalid input! Please try again:");
				scan.next();
				System.out.print("Please enter the no of players (between 2 and 4) :");
			}
			
			input = scan.nextInt();
			scan.nextLine();
			if(input < 0 || input > maxPlayers){
				System.out.println("Input must be within the specified range! Please try again:");
			}
			
		} while (input < 2 || input > maxPlayers);
		
		
		
		return input;
	}
	
	/**
	 * Gets the user to input file name for loading.
	 * @return The file name to be loaded by user.
	 */
	public String getLoadFileName(){
		int maxPlayers = 4;
		String input;
		int inputSize;
						
	   
	    
		do {
			System.out.print("Please enter the file to load :");
			while (!scan.hasNextLine()) {
				System.out.println("Invalid input! Please try again:");
				scan.next();
				System.out.print("Please enter the file to load :");
			}
			
			input = scan.nextLine();
			inputSize = input.length();
			
			if(inputSize == 0){
				System.out.println("Please enter the file to load :");
			}
			
		} while (inputSize == 0);
		
	
		
		return input;
	}
	
	/**
	 * Gets the user to input file name for loading.
	 * @return The file name to be loaded by user.
	 */
	public String getSaveInput(){
		
		String stringValue;
		int intInputValue;
		//do{
		do{
		System.out.println("Please enter your choice:");
		
		System.out.println("3:Do you want to save the game?(yes/no)");
		
		
		stringValue = scan.next();
		
		System.out.println(stringValue);
		}while(stringValue.length() == 0);
		
//		Console c = System.console();
//		
//		stringValue = c.readLine();
//		intInputValue = Integer.valueOf(stringValue);
//		}while(stringValue.length() == 0);
		
		/*int maxPlayers = 4;
		String input;
		int inputSize;
						
	    Scanner scanner = new Scanner(System.in);
	    
		do {
			System.out.print("Please enter the file to load :");
			while (!scanner.hasNextLine()) {
				System.out.println("Invalid input! Please try again:");
				scanner.next();
				System.out.print("Please enter the file to load :");
			}
			
			input = scanner.nextLine();
			inputSize = input.length();
			
			if(inputSize == 0){
				System.out.println("Please enter the file to load :");
			}
			
		} while (inputSize == 0);
		
		scanner.close();
		
		return input;*/
		
		return stringValue;
	}

}
