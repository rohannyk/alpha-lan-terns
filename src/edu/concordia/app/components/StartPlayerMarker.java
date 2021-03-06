/**
 * 
 */
package edu.concordia.app.components;

/**
 * The start marker is given to the player who has the start lake tile as red
 * pointing towards him
 * 
 * @author Team E
 *
 */
public class StartPlayerMarker {

	/**
	 * The start marker tile given to player. True - Player has the tile. False
	 * - Player don't have the tile.
	 */
	public boolean startMarker;

	/**
	 * This method gets the boolean for startMarker 
	 * @return the startMarker: boolean type.
	 */
	public boolean isStartMarker() {
		return startMarker;
	}

	/**
	 * This method sets the boolean for startMarker
	 * @param startMarker: boolean type.
	 *            the startMarker to set
	 */
	public void setStartMarker(boolean startMarker) {
		this.startMarker = startMarker;
	}

}
