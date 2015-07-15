/**
 * 
 */
package edu.concordia.app.components;

/**
 * The start marker is given to the player who has the start lake tile as red pointing towards him
 * @author Team E
 *
 */
public class StartPlayerMarker {
	
//	public int startMarker;
	
	/**
	 * The start marker tile given to player.
	 * True - Player has the tile.
	 * False - Player don't have the tile.
	 */
	public boolean startMarker;

	/**
	 * @return the startMarker
	 */
	public boolean isStartMarker() {
		return startMarker;
	}

	/**
	 * @param startMarker the startMarker to set
	 */
	public void setStartMarker(boolean startMarker) {
		this.startMarker = startMarker;
	}
	
	

}
