/**
 * 
 */
package edu.concordia.app.components;

import java.util.Vector;

/**
 * This class is for creating the stacks for dedication tokens.
 * There are 30 dedication tokens and divided into four groups. First
 * group of tokens are for four same color cards Second group of tokens
 * are for three pair of same color cards Third group of tokens are for
 * seven different color of cards Fourth group of tokens are extra
 * 
 * @author Team E
 */
public class DedicationTokens {

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
	 * The integer variable to store size of General dedication tokens with four
	 * dedications.
	 */
	private int dedicationTokenFourSize;

	/**
	 * The integer variable to store size of General dedication tokens with six
	 * dedications.
	 */
	private int dedicationTokenSixSize;

	/**
	 * The integer variable to store size of General dedication tokens with
	 * seven dedications.
	 */
	private int dedicationTokenSevenSize;

	/**
	 * The integer variable to store size of generic dedication tokens.
	 */
	private int genericDedicationTokensSize;

	/**
	 * Default Constructor
	 */
	public DedicationTokens() {

		dedicationTokenFourSize = dedicationTokenFour.size();
		dedicationTokenSixSize = dedicationTokenSix.size();
		dedicationTokenSevenSize = dedicationTokenSeven.size();
		genericDedicationTokensSize = genericDedicationTokens.size();
	}

	/**
	 * This constructor will initialize all the dedication token vectors and set
	 * their sizes in the variables.
	 * 
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
	public DedicationTokens(Vector<Integer> dedicationTokenFour, Vector<Integer> dedicationTokenSix,
			Vector<Integer> dedicationTokenSeven, Vector<Integer> genericDedicationTokens) {

		// Initialize vectors
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
	 * This method gets the Dedication Token Four
	 * @return the dedicationTokenFour: Vector<Integer> type
	 */
	public Vector<Integer> getDedicationTokenFour() {
		return dedicationTokenFour;
	}

	/**
	 * This method sets the Dedication Token Four
	 * @param dedicationTokenFour: Vector<Integer> type
	 *            the dedicationTokenFour to set
	 */
	public void setDedicationTokenFour(Vector<Integer> dedicationTokenFour) {
		this.dedicationTokenFour = dedicationTokenFour;
	}

	/**
	 * This method gets the Dedication Token Six
	 * @return the dedicationTokenSix: Vector<Integer> type
	 */
	public Vector<Integer> getDedicationTokenSix() {
		return dedicationTokenSix;
	}

	/**
	 * This method sets the Dedication Token Six
	 * @param dedicationTokenSix: Vector<Integer> type
	 *            The dedicationTokenSix to set
	 */
	public void setDedicationTokenSix(Vector<Integer> dedicationTokenSix) {
		this.dedicationTokenSix = dedicationTokenSix;
	}

	/**
	 * This method gets the Dedication Token Seven
	 * @return the dedicationTokenSeven: Vector<Integer> type
	 */
	public Vector<Integer> getDedicationTokenSeven() {
		return dedicationTokenSeven;
	}

	/**
	 * This method sets the Dedication Token Seven
	 * @param dedicationTokenSeven: Vector<Integer> type
	 *            The dedicationTokenSeven to set
	 */
	public void setDedicationTokenSeven(Vector<Integer> dedicationTokenSeven) {
		this.dedicationTokenSeven = dedicationTokenSeven;
	}

	/**
	 * This method gets the Dedication Token Generic
	 * @return the genericDedicationTokens: Vector<Integer> type
	 */
	public Vector<Integer> getGenericDedicationTokens() {
		return genericDedicationTokens;
	}

	/**
	 * This method sets the Dedication Token Generic
	 * @param genericDedicationTokens: Vector<Integer> type
	 *            The genericDedicationTokens to set
	 */
	public void setGenericDedicationTokens(Vector<Integer> genericDedicationTokens) {
		this.genericDedicationTokens = genericDedicationTokens;
	}

	/**
	 * This method gets the Dedication Token Four size.
	 * @return the dedicationTokenFourSize: int type
	 */
	public int getDedicationTokenFourSize() {
		return dedicationTokenFourSize;
	}

	/**
	 * This method sets the Dedication Token Four size.
	 * @param dedicationTokenFourSize: int type
	 *            The dedicationTokenFourSize to set
	 */
	public void setDedicationTokenFourSize(int dedicationTokenFourSize) {
		this.dedicationTokenFourSize = dedicationTokenFourSize;
	}

	/**
	 * This method gets the Dedication Token Six size.
	 * @return the dedicationTokenSixSize: int type
	 */
	public int getDedicationTokenSixSize() {
		return dedicationTokenSixSize;
	}

	/**
	 * This method sets the Dedication Token Six size.
	 * @param dedicationTokenSixSize: int type
	 *            The dedicationTokenSixSize to set
	 */
	public void setDedicationTokenSixSize(int dedicationTokenSixSize) {
		this.dedicationTokenSixSize = dedicationTokenSixSize;
	}

	/**
	 * This method gets the Dedication Token Seven size.
	 * @return the dedicationTokenSevenSize: int type
	 */
	public int getDedicationTokenSevenSize() {
		return dedicationTokenSevenSize;
	}

	/**
	 * This method sets the Dedication Token Seven size.
	 * @param dedicationTokenSevenSize: int type
	 *            The dedicationTokenSevenSize to set
	 */
	public void setDedicationTokenSevenSize(int dedicationTokenSevenSize) {
		this.dedicationTokenSevenSize = dedicationTokenSevenSize;
	}

	/**
	 * This method gets the Dedication Token Generic size.
	 * @return the genericDedicationTokensSize: int type
	 */
	public int getGenericDedicationTokensSize() {
		return genericDedicationTokensSize;
	}

	/**
	 * This method sets the Dedication Token Generic size.
	 * @param genericDedicationTokensSize: int type
	 *            The genericDedicationTokensSize to set
	 */
	public void setGenericDedicationTokensSize(int genericDedicationTokensSize) {
		this.genericDedicationTokensSize = genericDedicationTokensSize;
	}

}