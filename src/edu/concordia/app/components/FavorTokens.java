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
 * @since 23-07-2015
 */
public class FavorTokens {
	
	// variable to count favor tokens initialized to 0
	public int favtokenCount = 0;
	
	/**
	 * Default Constructor
	 */
	public FavorTokens(){
		
	}
	
	/**
	 * This Constructor is used to set favortokenCount.
	 * @param favtokenCount favtokenCount is set
	 */
	public FavorTokens(int favtokenCount){
		this.favtokenCount = favtokenCount;
	}
	
    	
	@XmlAttribute(name="totalFavorTokens")
	/**
	 * @return favtokenCount favtokenCount is returned 
	 */
	public int getFavtokenCount() {
		return favtokenCount;
	}


	/**
	 * @param favtokenCount the favtokenCount to set
	 */
	public void setFavtokenCount(int favtokenCount) {
		this.favtokenCount = favtokenCount;
	}
	
	/**
	 * @param tokens, the favor tokens added for players to favtokenCount 
	 */
	public void addFavorToken(int tokens){
		favtokenCount = favtokenCount + tokens; 
	}
	
	/**
	 * @param tokens, the favor tokens removed for players from favtokenCount 
	 */
	public void removeFavorToken(int tokens){
		favtokenCount = favtokenCount - tokens; 
	}

}
