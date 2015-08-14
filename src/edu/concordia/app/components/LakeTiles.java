package edu.concordia.app.components;

import java.io.Serializable;
import java.util.Vector;

/**
 * This class is to create the lake tile object, the lake tile have four colors
 * and some of the lake tiles have a platform marker,again lake tile can have adjacent
 * top/bottom/left/right lake tile 
 * @author Team E
 */
public class LakeTiles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2009565864150267740L;

	/**
	 * Default Constructor
	 */
	public LakeTiles() {

	}
	
	private int tilesId; 			// variable for tile's id
	private String topColor;		// variable for tile's top color
	private String leftColor;		// variable for tile's left color
	private String rightColor;		// variable for tile's right color
	private String bottomColor;		// variable for tile's bottom color
	private boolean platform;		// variable for tile's platform marker
	private int topTileId;			// variable for tile's top adjacent tile's id
	private int bottomTileId;		// variable for tile's bottom adjacent tile's id
	private int leftTileId;			// variable for tile's left adjacent tile's id
	private int rightTileId;		// variable for tile's right adjacent tile's id

	/**
	 * This constructor will set the fields of LakeTiles class.
	 * @param topColor The top color of lake tile.
	 * @param leftColor The left color of lake tile.
	 * @param rightColor The right color of lake tile.
	 * @param bottomColor The bottom color of lake tile.
	 * @param platform The platform of lake tile.
	 * @param topTileId The top tile id of lake tile.
	 * @param bottomTileId The top bottom id of lake tile.
	 * @param leftTileId The top left id of lake tile.
	 * @param rightTileId The top right id of lake tile.
	 */
	public LakeTiles(int tilesId, String topColor, String leftColor, String rightColor,
			String bottomColor, boolean platform, int topTileId, int bottomTileId, int leftTileId, int rightTileId) {
		
		this.tilesId =tilesId;
		this.topColor = topColor;
		this.leftColor = leftColor;
		this.rightColor = rightColor;
		this.bottomColor = bottomColor;
		this.platform = platform;
		this.topTileId = topTileId;
		this.bottomTileId = topTileId;
		this.leftTileId = topTileId;
		this.rightTileId = topTileId;
	}

	/**
	 * The getter method for tiles id
	 * @return the tilesId: int type
	 */
	public int getTilesId() {
		return tilesId;
	}

	/**
	 * The setter method for tiles id
	 * @param tilesId the tilesId to set: int type
	 */
	public void setTilesId(int tilesId) {
		this.tilesId = tilesId;
	}

	/**
	 * The getter method for tiles id of the top tile
	 * @return the topTileId: int type
	 */
	public int getTopTileId() {
		return topTileId;
	}

	/**
	 * The setter method for tiles id of the top tile
	 * @param topTileId the topTileId to set: int type
	 */
	public void setTopTileId(int topTileId) {
		this.topTileId = topTileId;
	}

	/**
	 * The getter method for tiles id of the bottom tile
	 * @return the bottomTileId: int type
	 */
	public int getBottomTileId() {
		return bottomTileId;
	}

	/**
	 * The setter method for tiles id of the bottom tile
	 * @param bottomTileId The bottomTileId to set: int type
	 */
	public void setBottomTileId(int bottomTileId) {
		this.bottomTileId = bottomTileId;
	}

	/**
	 * The getter method for tiles id of the left tile
	 * @return the leftTileId: int type
	 */
	public int getLeftTileId() {
		return leftTileId;
	}

	/**
	 * The setter method for tiles id of the left tile
	 * @param leftTileId The leftTileId to set: int type
	 */
	public void setLeftTileId(int leftTileId) {
		this.leftTileId = leftTileId;
	}

	/**
	 * The getter method for tiles id of the right tile
	 * @return The rightTileId: int type
	 */
	public int getRightTileId() {
		return rightTileId;
	}

	/**
	 * The setter method for tiles id of the right tile
	 * @param rightTileId The rightTileId to set: int type
	 */
	public void setRightTileId(int rightTileId) {
		this.rightTileId = rightTileId;
	}

	/**
	 * The getter method for the top color for the tile
	 * @return the topColor: String type
	 */
	public String getTopColor() {
		return topColor;
	}

	/**
	 * The setter method for the top color of the tile
	 * @param topColor The topColor to set: String type
	 */
	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}

	/**
	 * The getter method for the left color of the tile
	 * @return The leftColor: String type
	 */
	public String getLeftColor() {
		return leftColor;
	}

	/**
	 * The setter method for the left color of the tile
	 * @param leftColor The leftColor to set: String type
	 */
	public void setLeftColor(String leftColor) {
		this.leftColor = leftColor;
	}

	/**
	 * The getter method for the right color of the tile
	 * @return The rightColor: String type
	 */
	public String getRightColor() {
		return rightColor;
	}

	/**
	 * The setter method for the right color of the tile
	 * @param rightColor The rightColor to set: String type
	 */
	public void setRightColor(String rightColor) {
		this.rightColor = rightColor;
	}

	/**
	 * The getter method for the bottom color of the tile
	 * @return The bottomColor: String type
	 */
	public String getBottomColor() {
		return bottomColor;
	}

	/**
	 * The setter method for the bottom color of the tile
	 * @param bottomColor The bottomColor to set: String type
	 */
	public void setBottomColor(String bottomColor) {
		this.bottomColor = bottomColor;
	}

	/**
	 *The getter method for the platform of the tile
	 * @return The platform: String type
	 */
	public boolean isPlatform() {
		return platform;
	}

	/**
	 * The setter method for the platform of the tile
	 * @param platform The platform to set: String type
	 */
	public void setPlatform(boolean platform) {
		this.platform = platform;
	}

	/**
	 * The method to rotate the lake tile 90 Degree clockwise
	 */
	public LakeTiles rotateLakeTile() {
		String TC = getTopColor();
		String BC = getBottomColor();
		String RC = getRightColor();
		String LC = getLeftColor();
		setBottomColor(RC);
		setLeftColor(BC);
		setTopColor(LC);
		setRightColor(TC);
		return this;
	}
	
}
