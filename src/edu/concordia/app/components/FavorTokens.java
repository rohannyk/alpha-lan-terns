/**
 * 
 */
package edu.concordia.app.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class creates Favor Tokens for the players to use in the game
 * Favor Tokens are used by the player to exchange lantern cards 
 * @author Team E
 */
public class FavorTokens {
	
	
	public int favtokenCount = 0;
	
	//	public int favtoken = 20;
	
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
	public FavorTokens(int favtokenCount){
		this.favtokenCount = favtokenCount;
	}
	
	
	//getter and setter for favor tokens
	//variable playerFavorTokens initialized to 0
	//private static gameFavorToken initialized to 20


	@XmlAttribute(name="totalFavorTokens")
	/**
	 * @return the favtoken
	 */
	public int getFavtokenCount() {
		return favtokenCount;
	}


	/**
	 * @param favtoken the favtoken to set
	 */
	public void setFavtokenCount(int favtokenCount) {
		this.favtokenCount = favtokenCount;
	}
	
	/**
	 * @param tokens The favor tokens added for players 
	 */
	public void addFavorToken(int tokens){
		favtokenCount = favtokenCount + tokens; 
	}
	
	/**
	 * @param tokens The favor tokens added for players 
	 */
	public void removeFavorToken(int tokens){
		favtokenCount = favtokenCount - tokens; 
	}

}
