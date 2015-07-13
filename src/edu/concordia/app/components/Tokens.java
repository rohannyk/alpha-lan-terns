package edu.concordia.app.components;

/**
 * @author Team E
 * This class is used to create objects of Tokens class which is used by DedicationTokens class.It 
 * would hold value and number of dots for each dedication token card.
 *
 */
public class Tokens {
	
	
	
	/**
	 * 
	 */
	public Tokens() {
		
	}
	public Tokens(int value, int dots) {
		super();
		this.value = value;
		this.dots = dots;
	}
	private int value;
	private int dots;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getDots() {
		return dots;
	}
	public void setDots(int dots) {
		this.dots = dots;
	}

}
