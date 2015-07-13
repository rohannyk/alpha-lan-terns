/**
 * 
 */
package edu.concordia.app.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.concordia.app.components.DedicationTokens;
import edu.concordia.app.components.LanternCards;
import edu.concordia.app.components.Tokens;
import edu.concordia.app.controller.GameController;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;
/**
 * @author Team E
 */

/**
 * This is the main GUI class.
 */
public class LanternMain extends JFrame implements ActionListener {
	
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

	/**
	 * Constructor for an LanternMain.
	 */
	public LanternMain() {
		
		super("LANTERN:The Harvest Festival");
		
		createAndShowGui();
		
//		//Exit frame on close
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		 JPanel mainPanel = new JPanel();
//	     mainPanel.setLayout(new FlowLayout());
//
//        JLabel defaultLabel = new JLabel();
//        //defaultLabel.setPreferredSize(new Dimension(630, 500));
//        //getContentPane().add(mainPanel, BorderLayout.CENTER);
//        
//        newGame  = new JButton("New Game");
//        loadGame  = new JButton("Load Game"); 
//        exitGame = new JButton("Exit Game");
//
//        icon = createImageIcon(gameImageFile, imageCaption);
//        
//        defaultLabel.setIcon(icon);
//        
//        
//        add(defaultLabel, BorderLayout.CENTER);
//        add(mainPanel, BorderLayout.SOUTH);
//        
//        // Add buttons to panel
//        mainPanel.add(newGame);
//        mainPanel.add(loadGame);
//        mainPanel.add(exitGame);
//        
//        //Add event listener to buttons
//        newGame.addActionListener(this);
//        loadGame.addActionListener(this);
//        exitGame.addActionListener(this);
//        
//       
//        
//        //position the window
//        setBounds(350,100,2000,1000);
//     // this centers the frame on the screen
//        //setLocationRelativeTo(null);
//
//        //Display the window.
//        pack();
//        setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanternMain mainObj = new LanternMain();
		
		System.out.println("test main "+playerNumber);
		
		//mainObj.createAndShowGui(mainObj);
		
//		//Exit frame on close
//		mainObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JLabel emptyLabel = new JLabel("");
//        emptyLabel.setPreferredSize(new Dimension(375, 200));
//        mainObj.getContentPane().add(emptyLabel, BorderLayout.CENTER);
//
//        //Display the window.
//        mainObj.pack();
//        mainObj.setVisible(true);
	}
	
	/**
	 * Create starting GUI of Lantern Game with three option
	 * New game, Load game and exit game.
	 */
	private  void createAndShowGui(){
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
			setVisible(false);
			
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
			
			Vector<LanternCards> lanternCardsVector = gameInstance.gameLanternSuite;
			System.out.println(lanternCardsVector);
			//Vector<Tokens> tokensFour = dTokens.getDedicationTokenFour();
			//System.out.println(tokensFour);
						
		}
		
		if(selectedAction.equals("Load Game")){
			System.out.println(selectedAction);
			setVisible(false);
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
			System.out.print("Please enter the no of players (between 2 and " + maxPlayers + ") :");
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input! Please try again:");
				scanner.next();
				System.out.print("Please enter the no of players (between 2 and " + maxPlayers + ") :");
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
