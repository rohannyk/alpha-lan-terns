package edu.concordia.app.components;


/**
 * This class is used to create the Lantern Cards
 * The Lantern Cards are made up of 7 different colors
 * There are 8 cards for each color
 * Lantern Cards can be exchanged with favor tokens
 * Lantern Cards can be exchanged with dedication tokens to score
 * @author Team E
 */
public class LanternCards {
	
	private Color color;
	public int numberOfCards;
	
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

	/**
	 * @return the numberOfCards
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * @param numberOfCards the numberOfCards to set
	 */
	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}
	
	
}
