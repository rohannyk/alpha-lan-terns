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
/**
 * @author Team E
 */

/**
 * This is the main GUI class.
 */
//public class LanternMain extends JFrame implements ActionListener {
public class LanternMain implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2550694388930175952L;

	
	/**
	 * 
	 */
	private String imageDir = "images/";
	
	/**
	 * Name of the Background image in main frame.
	 */
	private String gameImageFile = "Lanterns-cover.jpg";
	
	private String imageCaption = "Harvest Game";
	
	/**
	 *  Image icon used in background.
	 */
	private ImageIcon icon;
	
	private JButton newGame, loadGame, exitGame;
	
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
		// TODO Auto-generated method stub
		LanternMain mainObj = new LanternMain();
		
		System.out.println("test main "+playerNumber);
		
		System.out.println("Welcome! to the");
		System.out.println("Lantern: The Harvest Festival Game");
		System.out.println("-----------------------------");
		System.out.println("Press 0 for New Game.");
		System.out.println("Press 1 to Load saved Game.");
		System.out.println("Press 2 to Exit Game.");
		
		int input;
		
		Scanner scanner = new Scanner(System.in);
	   int response = scanner.nextInt();
		//do {
			
			if(response == 0){
				
				int playerNumber = mainObj.getInputNoOfPlayers();
				
				//String fileName = mainObj.getSaveFileName();
				
				GameConfiguration config = new GameConfiguration(playerNumber);
				
				GameInstance gameObj = new GameInstance(config);
								
				
				GameController gameControl = new GameController(config, gameObj);
				
				gameControl.showTextMode();
				
				
				
				//do{
				
				System.out.println("Please enter your choice:");
				
				System.out.println("3:Do you want to save the game?(yes/no)");
				
				System.out.println("4:Show game state in text mode.");
				
				//get choice for save or show text mode
				//scanner = new Scanner(System.in);
				//int stateDecision = scanner.nextInt();
				
//				System.out.print("Please enter your choice:");
//				while (!scanner.hasNextInt()) {
//					System.out.println("Invalid input! Please try again:");
//					scanner.next();
//					System.out.print("Please enter your choice:");
//				}

//				if(stateDecision == 3){
//					System.out.println("save");
//				}
				
				
				   
				//}while(gameLoop);
				
				//gameControl.saveGameToFile("/Users/lovepreet/save.xml");
				
				//GameInstance gameInstance =  new GameController(config, gameObj).loadGameFromFile("/Users/lovepreet/save.xml");
				
			}else if(response == 1){
				
			}else if(response == 2){
				
				
				   System.exit(0);
				//gameLoop = false;
				//break;
			}
			
//			scanner = new Scanner(System.in);
//			System.out.print("Please enter your choice:");
//			while (!scanner.hasNextInt()) {
//				System.out.println("Invalid input! Please try again:");
//				scanner.next();
//				System.out.print("Please enter your choice:");
//			}
			
			//input = scanner.nextInt();
			
//			if(input < 0 || input > maxPlayers){
//				System.out.println("Input must be within the specified range! Please try again:");
//			}
			
		//} while (gameLoop);
		
		scanner.close();
	}
	
	/**
	 * Create starting GUI of Lantern Game with three option
	 * New game, Load game and exit game.
	 */
	/*private  void createAndShowGui(){
		//Exit frame on close
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				 JPanel mainPanel = new JPanel();
			     mainPanel.setLayout(new FlowLayout());

		        JLabel defaultLabel = new JLabel();
		        //defaultLabel.setPreferredSize(new Dimension(630, 500));
		        //getContentPane().add(mainPanel, BorderLayout.CENTER);
		        
		        newGame  = new JButton("New Game");
		        loadGame  = new JButton("Load Game"); 
		        exitGame = new JButton("Exit Game");

		        icon = createImageIcon(gameImageFile, imageCaption);
		        
		        defaultLabel.setIcon(icon);
		        
		        
		        add(defaultLabel, BorderLayout.CENTER);
		        add(mainPanel, BorderLayout.SOUTH);
		        
		        // Add buttons to panel
		        mainPanel.add(newGame);
		        mainPanel.add(loadGame);
		        mainPanel.add(exitGame);
		        
		        //Add event listener to buttons
		        newGame.addActionListener(this);
		        loadGame.addActionListener(this);
		        exitGame.addActionListener(this);
		        
		       
		        
		        //position the window
		        setBounds(350,100,2000,1000);
		     // this centers the frame on the screen
		        //setLocationRelativeTo(null);

		        //Display the window.
		        pack();
		        setVisible(true);
	}*/
	
	/**
	 * @return
	 */
	private String getSaveFileName() {
		int maxPlayers = 4;
		String input ="";
						
	    Scanner scanner = new Scanner(System.in);
	    
		do {
			System.out.print("Please enter the file name :");
//			while (!scanner.hasNextInt()) {
//				System.out.println("Invalid input! Please try again:");
//				scanner.next();
//				System.out.print("Please enter the no of players (between 2 and 4) :");
//			}
			
			input = scanner.nextLine();
			
			/*if(input < 0 || input > maxPlayers){
				System.out.println("Input must be within the specified range! Please try again:");
			}*/
			
		} while (!input.isEmpty());
		
		scanner.close();
		
		return input;
	}

	/**
     * Creates an ImageIcon if the path is valid.
     * @param String - resource path
     * @param String - description of the file
     */
    protected ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String selectedAction = e.getActionCommand();
		
		if(selectedAction.equals("New Game")){
			System.out.println(selectedAction);
			//setVisible(false);
			
			int playerNumber = getInputNoOfPlayers();
			
			//FetchPlayerNumber playerNumberObj = new FetchPlayerNumber();
			//playerNumber = playerNumberObj.getNumberOfPlayers();
			
			// ask user for number of players
			//set number of players value
			//int playerNumber = FetchNumOfPlayers();
			
			//System.out.println("test main "+playerNumber);
			
			GameConfiguration config = new GameConfiguration(playerNumber);
			
			GameInstance gameObj = new GameInstance(config);
			
			new GameController(config, gameObj).saveGameToFile("/Users/lovepreet/save.xml");
			
			GameInstance gameInstance =  new GameController(config, gameObj).loadGameFromFile("/Users/lovepreet/save.xml");
			
			//Vector<LanternCards> lanternCardsVector = gameInstance.gameLanternSuite;
			//System.out.println(lanternCardsVector);
			//Vector<Tokens> tokensFour = dTokens.getDedicationTokenFour();
			//System.out.println(tokensFour);
						
		}
		
		if(selectedAction.equals("Load Game")){
			System.out.println(selectedAction);
			//setVisible(false);
		}
		
		if(selectedAction.equals("Exit Game")){
			System.exit(0);
		}
		
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
						
	    Scanner scanner = new Scanner(System.in);
	    
		do {
			System.out.print("Please enter the no of players (between 2 and 4) :");
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input! Please try again:");
				scanner.next();
				System.out.print("Please enter the no of players (between 2 and 4) :");
			}
			
			input = scanner.nextInt();
			
			if(input < 0 || input > maxPlayers){
				System.out.println("Input must be within the specified range! Please try again:");
			}
			
		} while (input < 2 || input > maxPlayers);
		
		scanner.close();
		
		return input;
	}

}
