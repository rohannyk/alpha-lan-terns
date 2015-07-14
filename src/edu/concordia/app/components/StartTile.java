package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E This program is used to create the Start Tile This Tile is the
 *         first tile of the game The Red color in the tile chooses the first
 *         player to start the game
 */
public class StartTile {

	// vector for the colors for the start tile
	private Vector<String> startTileColour;

	/**
	 * Constructor to set the values for the start tile
	 */
	public StartTile() {
		startTileColour = new Vector<String>(4);
		startTileColour.addElement("RED");
		startTileColour.addElement("WHITE");
		startTileColour.addElement("BLUE");
		startTileColour.addElement("BLACK");

	}

	/**
	 * getter for the vector for the start tile
	 * @return the startTileColour
	 */
	public Vector<String> getStartTileColour() {
		return startTileColour;
	}

	/**
	 * setter for the vector for the start tile
	 * @param startTileColour the startTileColour to set
	 */
	public void setStartTileColour(Vector<String> startTileColour) {
		this.startTileColour = startTileColour;
	}
	
}
