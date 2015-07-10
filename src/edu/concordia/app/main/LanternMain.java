/**
 * 
 */
package edu.concordia.app.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	/**
	 * Constructor for an LanternMain.
	 */
	public LanternMain() {
		// TODO Auto-generated constructor stub
		super("LANTERN:The Harvest Festival");
		
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanternMain mainObj = new LanternMain();
		
		//createAndShowGui(mainObj);
		
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
	
	//private static JFrame createAndShowGui(LanternMain mainObj){
//		//Exit frame on close
//				mainObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				
//				
//
//		        JLabel defaultLabel = new JLabel();
//		        defaultLabel.setPreferredSize(new Dimension(375, 200));
//		        mainObj.getContentPane().add(defaultLabel, BorderLayout.CENTER);
//
//		        JPanel mainPanel = new JPanel();
//		        mainPanel.setLayout(new FlowLayout());
//		        
//		        
//                icon = createImageIcon(imagedir + imageFileNames[i], imageCaptions[i]);
//		        
//		        mainObj.add(defaultLabel, BorderLayout.CENTER);
//		        mainObj.add(mainPanel, BorderLayout.SOUTH);
//		        
//		       
//		        
//		        //position the window
//		        //mainObj.setBounds(500,100,2000,1000);
//		     // this centers the frame on the screen
//		        mainObj.setLocationRelativeTo(null);
//
//		        //Display the window.
//		        mainObj.pack();
//		        mainObj.setVisible(true);
//		return mainObj;
	//}
	
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
		}
		
		if(selectedAction.equals("Load Game")){
			System.out.println(selectedAction);
			setVisible(false);
		}
		
		if(selectedAction.equals("Exit Game")){
			System.exit(0);
		}
		
	}

}
