/**
 * 
 */
package edu.concordia.app.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class creates Favor Tokens for the players to use in the game Favor
 * Tokens are used by the player to exchange lantern cards
 * 
 * @author Team E
 */
public class FavorTokens {

	// variable to count favor tokens initialized to 0
	public int favtokenCount = 0;

	/**
	 * Default Constructor
	 */
	public FavorTokens() {

	}

	/**
	 * Constructor to set favor token count
	 */
	public FavorTokens(int favtokenCount) {
		this.favtokenCount = favtokenCount;
	}

	@XmlAttribute(name = "totalFavorTokens")
	/**
	 * This method gets the favor token count.
	 * @return the favtokenCount: int type
	 */
	public int getFavtokenCount() {
		return favtokenCount;
	}

	/**
	 * This method sets the favor token count.
	 * @param favtokenCount: int type
	 *            the favtokenCount to set
	 */
	public void setFavtokenCount(int favtokenCount) {
		this.favtokenCount = favtokenCount;
	}

	/**
	 * This method adds/increases the favor token.
	 * @param tokens,
	 *            the favor tokens added for players to favtokenCount
	 */
	public void addFavorToken(int tokens) {
		favtokenCount = favtokenCount + tokens;
	}

	/**
	 * This method removes/decreases the favor token.
	 * @param tokens,
	 *            the favor tokens removed for players from favtokenCount
	 */
	public void removeFavorToken(int tokens) {
		favtokenCount = favtokenCount - tokens;
	}

}
