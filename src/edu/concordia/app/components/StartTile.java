package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E 
 * This program is used to create the Start Tile This Tile is the
 * first tile of the game The Red color in the tile chooses the first
 * player to start the game
 *
 */
public class StartTile {

//	public Vector<String> edgecolour = new Vector<String>(4);
	//private Vector<String> startTileColour;
	
	//private int startTileId;
	private String startTileTopColor = "BLACK";
	private String startTileLeftColor = "WHITE";
	private String startTileRightColor = "BLUE";
	private String startTileBottomColor = "RED";
	private int startTopTileId;
	private int startBottomTileId;
	private int startLeftTileId;
	private int startRightTileId;

	/**
	 * 
	 */
	public StartTile() {
//		startTileColour = new Vector<String>(4);
//		startTileColour.addElement("RED");
//		startTileColour.addElement("WHITE");
//		startTileColour.addElement("BLUE");
//		startTileColour.addElement("BLACK");

	}

	/**
	 * @return the startTileTopColor
	 */
	public String getStartTileTopColor() {
		return startTileTopColor;
	}

	/**
	 * @param startTileTopColor the startTileTopColor to set
	 */
	public void setStartTileTopColor(String startTileTopColor) {
		this.startTileTopColor = startTileTopColor;
	}

	/**
	 * @return the startTileLeftColor
	 */
	public String getStartTileLeftColor() {
		return startTileLeftColor;
	}

	/**
	 * @param startTileLeftColor the startTileLeftColor to set
	 */
	public void setStartTileLeftColor(String startTileLeftColor) {
		this.startTileLeftColor = startTileLeftColor;
	}

	/**
	 * @return the startTileRightColor
	 */
	public String getStartTileRightColor() {
		return startTileRightColor;
	}

	/**
	 * @param startTileRightColor the startTileRightColor to set
	 */
	public void setStartTileRightColor(String startTileRightColor) {
		this.startTileRightColor = startTileRightColor;
	}

	/**
	 * @return the startTileBottomColor
	 */
	public String getStartTileBottomColor() {
		return startTileBottomColor;
	}

	/**
	 * @param startTileBottomColor the startTileBottomColor to set
	 */
	public void setStartTileBottomColor(String startTileBottomColor) {
		this.startTileBottomColor = startTileBottomColor;
	}

	/**
	 * @return the startTopTileId
	 */
	public int getStartTopTileId() {
		return startTopTileId;
	}

	/**
	 * @param startTopTileId the startTopTileId to set
	 */
	public void setStartTopTileId(int startTopTileId) {
		this.startTopTileId = startTopTileId;
	}

	/**
	 * @return the startBottomTileId
	 */
	public int getStartBottomTileId() {
		return startBottomTileId;
	}

	/**
	 * @param startBottomTileId the startBottomTileId to set
	 */
	public void setStartBottomTileId(int startBottomTileId) {
		this.startBottomTileId = startBottomTileId;
	}

	/**
	 * @return the startLeftTileId
	 */
	public int getStartLeftTileId() {
		return startLeftTileId;
	}

	/**
	 * @param startLeftTileId the startLeftTileId to set
	 */
	public void setStartLeftTileId(int startLeftTileId) {
		this.startLeftTileId = startLeftTileId;
	}

	/**
	 * @return the startRightTileId
	 */
	public int getStartRightTileId() {
		return startRightTileId;
	}

	/**
	 * @param startRightTileId the startRightTileId to set
	 */
	public void setStartRightTileId(int startRightTileId) {
		this.startRightTileId = startRightTileId;
	}

//	/**
//	 * @return the startTileColour
//	 */
//	public Vector<String> getStartTileColour() {
//		return startTileColour;
//	}
//
//	/**
//	 * @param startTileColour the startTileColour to set
//	 */
//	public void setStartTileColour(Vector<String> startTileColour) {
//		this.startTileColour = startTileColour;
//	}
	
	

}
