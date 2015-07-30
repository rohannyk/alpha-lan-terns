package edu.concordia.app.components;

import java.util.Vector;

/**
 * @author Team E 
 * This program is used to create the Start Tile This Tile is the
 * first tile of the game The Red color in the tile chooses the first
 * player to start the game.
 *
 */
public class StartTile {

	private String startTileTopColor = "BLACK";
	private String startTileLeftColor = "WHITE";
	private String startTileRightColor = "BLUE";
	private String startTileBottomColor = "RED";
	private int startTopTileId;
	private int startBottomTileId;
	private int startLeftTileId;
	private int startRightTileId;

	/**
	 * Default Constructor
	 */
	public StartTile() {
	
	}

	/**
	 * This method gets the top color of the start tile.
	 * @return the startTileTopColor: String type
	 */
	public String getStartTileTopColor() {
		return startTileTopColor;
	}

	/**
	 * This method sets the top color of the start tile.
	 * @param startTileTopColor: String type
	 *            the startTileTopColor to set
	 */
	public void setStartTileTopColor(String startTileTopColor) {
		this.startTileTopColor = startTileTopColor;
	}

	/**
	 * This method gets the left color of the start tile.
	 * @return the startTileLeftColor: String type
	 */
	public String getStartTileLeftColor() {
		return startTileLeftColor;
	}

	/**
	 * This method sets the left color of the start tile.
	 * @param startTileLeftColor: String type
	 *            the startTileLeftColor to set
	 */
	public void setStartTileLeftColor(String startTileLeftColor) {
		this.startTileLeftColor = startTileLeftColor;
	}

	/**
	 * This method gets the right color of the start tile.
	 * @return the startTileRightColor: String type
	 */
	public String getStartTileRightColor() {
		return startTileRightColor;
	}

	/**
	 * This method sets the right color of the start tile.
	 * @param startTileRightColor: String type
	 *            the startTileRightColor to set
	 */
	public void setStartTileRightColor(String startTileRightColor) {
		this.startTileRightColor = startTileRightColor;
	}

	/**
	 * This method gets the bottom color of the start tile.
	 * @return the startTileBottomColor: String type
	 */
	public String getStartTileBottomColor() {
		return startTileBottomColor;
	}

	/**
	 * This method sets the bottom color of the start tile.
	 * @param startTileBottomColor: String type
	 *            the startTileBottomColor to set
	 */
	public void setStartTileBottomColor(String startTileBottomColor) {
		this.startTileBottomColor = startTileBottomColor;
	}

	/**
	 * This method gets the top tile id for the start tile.
	 * @return the startTopTileId: int type
	 */
	public int getStartTopTileId() {
		return startTopTileId;
	}

	/**
	 * This method sets the top tile id for the start tile.
	 * @param startTopTileId: int type
	 *            the startTopTileId to set
	 */
	public void setStartTopTileId(int startTopTileId) {
		this.startTopTileId = startTopTileId;
	}

	/**
	 * This method gets the bottom tile id for the start tile.
	 * @return the startBottomTileId: int type
	 */
	public int getStartBottomTileId() {
		return startBottomTileId;
	}

	/**
	 * This method sets the bottom tile id for the start tile.
	 * @param startBottomTileId: int type
	 *            the startBottomTileId to set
	 */
	public void setStartBottomTileId(int startBottomTileId) {
		this.startBottomTileId = startBottomTileId;
	}

	/**
	 * This method gets the left tile id for the start tile.
	 * @return the startLeftTileId: int type
	 */
	public int getStartLeftTileId() {
		return startLeftTileId;
	}

	/**
	 * This method sets the left tile id for the start tile.
	 * @param startLeftTileId: int type
	 *            the startLeftTileId to set
	 */
	public void setStartLeftTileId(int startLeftTileId) {
		this.startLeftTileId = startLeftTileId;
	}

	/**
	 * This method gets the right tile id for the start tile.
	 * @return the startRightTileId: int type
	 */
	public int getStartRightTileId() {
		return startRightTileId;
	}

	/**
	 * This method sets the right tile id for the start tile.
	 * @param startRightTileId: int type
	 *            the startRightTileId to set
	 */
	public void setStartRightTileId(int startRightTileId) {
		this.startRightTileId = startRightTileId;
	}

}
