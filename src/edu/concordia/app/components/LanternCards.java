package edu.concordia.app.components;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 * This class is used to create the Lantern Cards.
 * The Lantern Cards are made up of 7 different colors.
 * There are 8 cards for each color.
 * Lantern Cards can be exchanged with favor tokens.
 * Lantern Cards can be exchanged with dedication tokens to score.
 * @author Team E
 */
public class LanternCards implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4871186973694012184L;


	/**
	 * The color of the Lantern Card
	 */
	private Color color;
	
	
	/**
	 * The number of color lantern cards
	 */
	private int numberOfCards;
	
	/**
	 * Default constructor
	 */
	
	public LanternCards(){
		
	}
	
	/**
	 * Constructor specifying the color of the Lantern Card. 
	 * A tile of with a valid color can be created with this constructor.
	 * @param c An enum type of type 'Color'. 
	 * It can have the values RED, BLUE, etc as per the definition of the enum TileType.
	 */
	public LanternCards(Color c){
		this.color = c;
	}
	
	/**
	 * Constructor specifying the color of the Lantern Card. 
	 * A tile of with a valid color can be created with this constructor.
	 * @param c An enum type of type 'Color'. 
	 * It can have the values RED, BLUE, etc as per the definition of the enum TileType.
	 * @param noOfColoredCards The number of cards for particular color.
	 */
	public LanternCards(Color c, int noOfColoredCards){
		this.color = c;
		this.numberOfCards = noOfColoredCards;
	}
	
	@XmlAttribute(name="cardColor")
	/**
	 * Gets the color of the Lantern Cards
	 * @return The type of the tile (as a enum 'Color' object).
	 */
	public Color getCardColor() {
		return this.color;
	}

	/**
	 * An enum type is used for restricting the number of colors 
	 */
	public enum Color{
		RED, ORANGE, PURPLE, WHITE , BLUE, BLACK, GREEN;
	}

	@XmlAttribute
	/**
	 * This method gets the number of cards.
	 * @return the numberOfCards. int type
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * This method sets the number of cards.
	 * @param numberOfCards the numberOfCards to set: int type
	 */
	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}
	
}
