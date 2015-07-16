/**
 * 
 */
package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E
 * This class is for creating the stacks for dedication tokens. 
 * There are 30 dedication tokens and divided into four groups.
 * First group of tokens are for four same color cards
 * Second group of tokens are for three pair of same color cards
 * Third group of tokens are for seven different color of cards
 * Fourth group of tokens are extra
 */
public class DedicationTokens {

	
	
//	private Vector<Tokens> dedicationTokenFour = new Vector<Tokens>();
//	
//	private Vector<Tokens> dedicationTokenSix = new Vector<Tokens>();
//	
//	private Vector<Tokens> dedicationTokenSeven = new Vector<Tokens>();
//	
//	private Vector<Tokens> genericDedicationTokens = new Vector<Tokens>();
	
	/**
	 * The vector to store General dedication tokens with four dedications.
	 */
	private Vector<Integer> dedicationTokenFour = new Vector<Integer>();
	
	/**
	 * The vector to store General dedication tokens with six dedications.
	 */
	private Vector<Integer> dedicationTokenSix = new Vector<Integer>();
	
	/**
	 * The vector to store General dedication tokens with seven dedications.
	 */
	private Vector<Integer> dedicationTokenSeven = new Vector<Integer>();
	
	/**
	 * The vector to store generic dedication tokens.
	 */
	private Vector<Integer> genericDedicationTokens = new Vector<Integer>();
	
	
	/**
	 * The integer variable to store size of General dedication
	 * tokens with four dedications.
	 */
	private int dedicationTokenFourSize;
	
	
	/**
	 * The integer variable to store size of General dedication
	 * tokens with six dedications.
	 */
	private int dedicationTokenSixSize;
	
	
	/**
	 * The integer variable to store size of General dedication
	 * tokens with seven dedications.
	 */
	private int dedicationTokenSevenSize;
	
	
	/**
	 * The integer variable to store size of generic dedication
	 * tokens.
	 */
	private int genericDedicationTokensSize;
	
	
	
	
		/**
		 * Default Constructor
		 */
		public DedicationTokens(){
			
			dedicationTokenFourSize = dedicationTokenFour.size();
			dedicationTokenSixSize = dedicationTokenSix.size();
			dedicationTokenSevenSize = dedicationTokenSeven.size();
			genericDedicationTokensSize = genericDedicationTokens.size();
		}
	
	/**
	 * This constructor will initialize all the dedication token vectors
	 * and set their sizes in the variables.
	 * @param dedicationTokenFour
	 *            The vector of General dedication tokens with seven
	 *            dedications.
	 * @param dedicationTokenSix
	 *            The vector of General dedication tokens with seven
	 *            dedications.
	 * @param dedicationTokenSeven
	 *            The vector of General dedication tokens with seven
	 *            dedications.
	 * @param genericDedicationTokens
	 *            The vector of Generic dedication tokens.
	 */
		public DedicationTokens(Vector<Integer> dedicationTokenFour,
				Vector<Integer> dedicationTokenSix,
				Vector<Integer> dedicationTokenSeven,
				Vector<Integer> genericDedicationTokens) {
			
			//Initialize vectors
			this.dedicationTokenFour = dedicationTokenFour;
			this.dedicationTokenSix = dedicationTokenSix;
			this.dedicationTokenSeven = dedicationTokenSeven;
			this.genericDedicationTokens = genericDedicationTokens;
			
			// set no of cards according to vector size.
			dedicationTokenFourSize = dedicationTokenFour.size();
			dedicationTokenSixSize = dedicationTokenSix.size();
			dedicationTokenSevenSize = dedicationTokenSeven.size();
			genericDedicationTokensSize = genericDedicationTokens.size();
		}

	/**
	 * @return the dedicationTokenFour
	 */
	public Vector<Integer> getDedicationTokenFour() {
		return dedicationTokenFour;
	}

	/**
	 * @param dedicationTokenFour the dedicationTokenFour to set
	 */
	public void setDedicationTokenFour(Vector<Integer> dedicationTokenFour) {
		this.dedicationTokenFour = dedicationTokenFour;
	}

	/**
	 * @return the dedicationTokenSix
	 */
	public Vector<Integer> getDedicationTokenSix() {
		return dedicationTokenSix;
	}

	/**
	 * @param dedicationTokenSix The dedicationTokenSix to set
	 */
	public void setDedicationTokenSix(Vector<Integer> dedicationTokenSix) {
		this.dedicationTokenSix = dedicationTokenSix;
	}

	/**
	 * @return the dedicationTokenSeven
	 */
	public Vector<Integer> getDedicationTokenSeven() {
		return dedicationTokenSeven;
	}

	/**
	 * @param dedicationTokenSeven The dedicationTokenSeven to set
	 */
	public void setDedicationTokenSeven(Vector<Integer> dedicationTokenSeven) {
		this.dedicationTokenSeven = dedicationTokenSeven;
	}

	/**
	 * @return the genericDedicationTokens
	 */
	public Vector<Integer> getGenericDedicationTokens() {
		return genericDedicationTokens;
	}

	/**
	 * @param genericDedicationTokens The genericDedicationTokens to set
	 */
	public void setGenericDedicationTokens(Vector<Integer> genericDedicationTokens) {
		this.genericDedicationTokens = genericDedicationTokens;
	}

	/**
	 * @return the dedicationTokenFourSize
	 */
	public int getDedicationTokenFourSize() {
		return dedicationTokenFourSize;
	}

	/**
	 * @param dedicationTokenFourSize The dedicationTokenFourSize to set
	 */
	public void setDedicationTokenFourSize(int dedicationTokenFourSize) {
		this.dedicationTokenFourSize = dedicationTokenFourSize;
	}

	/**
	 * @return the dedicationTokenSixSize
	 */
	public int getDedicationTokenSixSize() {
		return dedicationTokenSixSize;
	}

	/**
	 * @param dedicationTokenSixSize The dedicationTokenSixSize to set
	 */
	public void setDedicationTokenSixSize(int dedicationTokenSixSize) {
		this.dedicationTokenSixSize = dedicationTokenSixSize;
	}

	/**
	 * @return the dedicationTokenSevenSize
	 */
	public int getDedicationTokenSevenSize() {
		return dedicationTokenSevenSize;
	}

	/**
	 * @param dedicationTokenSevenSize The dedicationTokenSevenSize to set
	 */
	public void setDedicationTokenSevenSize(int dedicationTokenSevenSize) {
		this.dedicationTokenSevenSize = dedicationTokenSevenSize;
	}

	/**
	 * @return the genericDedicationTokensSize
	 */
	public int getGenericDedicationTokensSize() {
		return genericDedicationTokensSize;
	}

	/**
	 * @param genericDedicationTokensSize The genericDedicationTokensSize to set
	 */
	public void setGenericDedicationTokensSize(int genericDedicationTokensSize) {
		this.genericDedicationTokensSize = genericDedicationTokensSize;
	}


	
	

}