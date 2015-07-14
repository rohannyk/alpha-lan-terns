/**
 * 
 */
package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E
 * this class is for creating the stacks for dedication tokens 
 * there are 30 dedication tokens and divided into four groups
 * first group of tokens are for four same color cards
 * second group of tokens are for three pair of same color cards
 * third group of tokens are for seven different color of cards
 * fourth group of tokens are extra
 */
public class DedicationTokens {

	
	
//	private Vector<Tokens> dedicationTokenFour = new Vector<Tokens>();
//	
//	private Vector<Tokens> dedicationTokenSix = new Vector<Tokens>();
//	
//	private Vector<Tokens> dedicationTokenSeven = new Vector<Tokens>();
//	
//	private Vector<Tokens> genericDedicationTokens = new Vector<Tokens>();
	
	private Vector<Integer> dedicationTokenFour = new Vector<Integer>();
	
	private Vector<Integer> dedicationTokenSix = new Vector<Integer>();
	
	private Vector<Integer> dedicationTokenSeven = new Vector<Integer>();
	
	private Vector<Integer> genericDedicationTokens = new Vector<Integer>();
	
	
	/**
	 * 
	 */
	private int dedicationTokenFourSize;
	
	
	/**
	 * 
	 */
	private int dedicationTokenSixSize;
	
	
	/**
	 * 
	 */
	private int dedicationTokenSevenSize;
	
	
	/**
	 * 
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
		 * @param dedicationTokenFour
		 * @param dedicationTokenSix
		 * @param dedicationTokenSeven
		 * @param genericDedicationTokens
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
	 * @param dedicationTokenSix the dedicationTokenSix to set
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
	 * @param dedicationTokenSeven the dedicationTokenSeven to set
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
	 * @param genericDedicationTokens the genericDedicationTokens to set
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
	 * @param dedicationTokenFourSize the dedicationTokenFourSize to set
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
	 * @param dedicationTokenSixSize the dedicationTokenSixSize to set
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
	 * @param dedicationTokenSevenSize the dedicationTokenSevenSize to set
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
	 * @param genericDedicationTokensSize the genericDedicationTokensSize to set
	 */
	public void setGenericDedicationTokensSize(int genericDedicationTokensSize) {
		this.genericDedicationTokensSize = genericDedicationTokensSize;
	}

	
	
	
	
	
	//create getter and setter to get dedicated components
	//problem to remove particular token object from array
	
	

}