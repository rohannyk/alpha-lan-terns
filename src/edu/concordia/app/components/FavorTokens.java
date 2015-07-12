/**
 * 
 */
package edu.concordia.app.components;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class creates Favor Tokens for the players to use in the game
 * Favor Tokens are used by the player to exchange lantern cards 
 * @author Team E
 */
public class FavorTokens {
	
	public int favtoken = 0;
	//public int favtoken = 20;
	//private static int gameFavorToken = 20;
	//private int playerFavorToken = 0;
	/**
	 * Default Constructor
	 */
	public FavorTokens(){
		
	}
	/**
	 * Default Constructor
	 */
	public FavorTokens(int favtoken){
		this.favtoken = favtoken;
	}
	
	
	//getter and setter for favor tokens
	//variable playerFavorTokens initialized to 0
	//private static gameFavorToken initialized to 20
	/**
	 * @return the favtoken
	 */
	public int getFavtoken() {
		return favtoken;
	}


	/**
	 * @param favtoken the favtoken to set
	 */
	public void setFavtoken(int favtoken) {
		this.favtoken = favtoken;
	}
	
	/**
	 * @param tokens The favor tokens added for players 
	 */
	public void addFavorToken(int tokens){
		favtoken = favtoken + tokens; 
	}
	
	/**
	 * @param tokens The favor tokens added for players 
	 */
	public void removeFavorToken(int tokens){
		favtoken = favtoken - tokens; 
	}

}
