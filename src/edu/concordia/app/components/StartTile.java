package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E This program is used to create the Start Tile This Tile is the
 *         first tile of the game The Red color in the tile chooses the first
 *         player to start the game
 *
 */
public class StartTile {

//	public Vector<String> edgecolour = new Vector<String>(4);
	private Vector<String> startTileColour;

	/**
	 * 
	 */
	public StartTile() {
		startTileColour = new Vector<String>(4);
		startTileColour.addElement("RED");
		startTileColour.addElement("WHITE");
		startTileColour.addElement("BLUE");
		startTileColour.addElement("BLACK");

	}

	/**
	 * @return the startTileColour
	 */
	public Vector<String> getStartTileColour() {
		return startTileColour;
	}

	/**
	 * @param startTileColour the startTileColour to set
	 */
	public void setStartTileColour(Vector<String> startTileColour) {
		this.startTileColour = startTileColour;
	}
	
	

}
