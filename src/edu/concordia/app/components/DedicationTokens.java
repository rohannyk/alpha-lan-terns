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

	// default constructor
	public DedicationTokens(){}
	
	private Vector<Tokens> dedicationTokenFour = new Vector<Tokens>();
	
	private Vector<Tokens> dedicationTokenSix = new Vector<Tokens>();
	
	private Vector<Tokens> dedicationTokenSeven = new Vector<Tokens>();
	
	private Vector<Tokens> genericDedicationTokens = new Vector<Tokens>();
	
	public void initializeDedicationComponents(){
	
		// creating array of Component objects to hold dedication tokens for four of same color
		Tokens[] dedicationComponent_4 = new Tokens[8];
	
		// initializing Component objects for tokens with four of same color cards
		dedicationComponent_4[0] = new Tokens(8,0);
		dedicationComponent_4[1] = new Tokens(7,4);
		dedicationComponent_4[2] = new Tokens(7,0);
		dedicationComponent_4[3] = new Tokens(6,0);
		dedicationComponent_4[4] = new Tokens(6,3);
		dedicationComponent_4[5] = new Tokens(5,0);
		dedicationComponent_4[6] = new Tokens(5,3);
		dedicationComponent_4[7] = new Tokens(5,0);
		dedicationComponent_4[8] = new Tokens(4,0);
	
		// creating array of Component objects to hold dedication tokens for three pair of same color
		Tokens[] dedicationComponent_6 = new Tokens[8];
	
		// initializing Component objects for tokens with three pair of same color
		dedicationComponent_6[0] = new Tokens(9,0);
		dedicationComponent_6[1] = new Tokens(8,4);
		dedicationComponent_6[2] = new Tokens(8,0);
		dedicationComponent_6[3] = new Tokens(7,3);
		dedicationComponent_6[4] = new Tokens(7,0);
		dedicationComponent_6[5] = new Tokens(6,3);
		dedicationComponent_6[6] = new Tokens(6,0);
		dedicationComponent_6[7] = new Tokens(5,0);
		dedicationComponent_6[8] = new Tokens(5,0);

		// creating array of Component objects to hold dedication tokens for cards of seven different color
		Tokens[] dedicationComponent_7 = new Tokens[8];
	
		// initializing Component objects for tokens with cards of seven different color
		dedicationComponent_7[0] = new Tokens(10,0);
		dedicationComponent_7[1] = new Tokens(9,0);
		dedicationComponent_7[2] = new Tokens(9,0);
		dedicationComponent_7[3] = new Tokens(8,4);
		dedicationComponent_7[4] = new Tokens(8,0);
		dedicationComponent_7[5] = new Tokens(7,3);
		dedicationComponent_7[6] = new Tokens(7,0);
		dedicationComponent_7[7] = new Tokens(6,0);
		dedicationComponent_7[8] = new Tokens(5,0);
	
		// creating array of Component objects to hold dedication tokens for cards of extra dedication tokens
		Tokens[] dedicationComponent_extra = new Tokens[2];
	
		// initializing Component objects for tokens with cards of extra dedication tokens
		dedicationComponent_extra[0] = new Tokens(4,0);
		dedicationComponent_extra[1] = new Tokens(4,0);
		dedicationComponent_extra[2] = new Tokens(4,0);
	}

	/**
	 * @return the dedicationTokenFour
	 */
	public Vector<Tokens> getDedicationTokenFour() {
		return dedicationTokenFour;
	}

	/**
	 * @param dedicationTokenFour the dedicationTokenFour to set
	 */
	public void setDedicationTokenFour(Vector<Tokens> dedicationTokenFour) {
		this.dedicationTokenFour = dedicationTokenFour;
	}

	/**
	 * @return the dedicationTokenSix
	 */
	public Vector<Tokens> getDedicationTokenSix() {
		return dedicationTokenSix;
	}

	/**
	 * @param dedicationTokenSix the dedicationTokenSix to set
	 */
	public void setDedicationTokenSix(Vector<Tokens> dedicationTokenSix) {
		this.dedicationTokenSix = dedicationTokenSix;
	}

	/**
	 * @return the dedicationTokenSeven
	 */
	public Vector<Tokens> getDedicationTokenSeven() {
		return dedicationTokenSeven;
	}

	/**
	 * @param dedicationTokenSeven the dedicationTokenSeven to set
	 */
	public void setDedicationTokenSeven(Vector<Tokens> dedicationTokenSeven) {
		this.dedicationTokenSeven = dedicationTokenSeven;
	}

	/**
	 * @return the genericDedicationTokens
	 */
	public Vector<Tokens> getGenericDedicationTokens() {
		return genericDedicationTokens;
	}

	/**
	 * @param genericDedicationTokens the genericDedicationTokens to set
	 */
	public void setGenericDedicationTokens(Vector<Tokens> genericDedicationTokens) {
		this.genericDedicationTokens = genericDedicationTokens;
	}
	
	//create getter and setter to get dedicated components
	//problem to remove particular token object from array
	
	

}