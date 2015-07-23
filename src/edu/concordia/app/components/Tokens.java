package edu.concordia.app.components;

/**
 *
 * This class is used to create objects of Tokens class which is used by DedicationTokens class.It 
 * would hold value and number of dots for each dedication token card.
 * @author Team E
 * @since 23-07-2015
 */
public class Tokens {
	
		
	/**
	 * 
	 */
	public Tokens() {
		
	}
	
	/*
	 * @param value value is the honor score on the card
	 * @param dots is the number of dots on the dedication tokens which is used to accept or discard
	 *        the card based on number of players playing the game
	 */
	
	public Tokens(int value, int dots) {
		super();
		this.value = value;
		this.dots = dots;
	}
	private int value;
	private int dots;
	
	/*
	 * @return value on the dedication token is returned
	 */
	public int getValue() {
		return value;
	}
	
	/*
	 * @param value is set
	 * 
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/*
	 * @return dots is returned
	 */
	public int getDots() {
		return dots;
	}
	/*
	 * @param dots is returned
	 */
	public void setDots(int dots) {
		this.dots = dots;
	}

}
