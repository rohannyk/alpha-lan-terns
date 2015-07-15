package edu.concordia.app.components;

import java.util.Vector;

/**
 * this class is to create the lake tile object, the lake tile have four colors
 * and some of the lake tiles have a platform marker,again lake tile can have adjacent
 * top/bottom/left/right lake tile 
 * @author Team E
 */
public class LakeTiles {

	// default constructor
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
	 * Constructor
	 * @param topColor
	 * @param leftColor
	 * @param rightColor
	 * @param bottomColor
	 * @param platform
	 * @param topTileId
	 * @param bottomTileId
	 * @param leftTileId
	 * @param rightTileId
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


	

	// initializing colors and platform marker for the lake tiles
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initializeTiles() {

		// set the values for vector for tile 1
		Vector tile1 = new Vector(5);
		tile1.addElement(new String("GREEN"));
		tile1.addElement(new String("BLUE"));
		tile1.addElement(new String("BLACK"));
		tile1.addElement(new String("ORANGE"));
		tile1.addElement(new Boolean(false));
		
		// set the values for vector for tile 2
		Vector tile2 = new Vector(5);
		tile2.addElement(new String("GREEN"));
		tile2.addElement(new String("GREEN"));
		tile2.addElement(new String("GREEN"));
		tile2.addElement(new String("RED"));
		tile2.addElement(new Boolean(false));
		
		// set the values for vector for tile 3
		Vector tile3 = new Vector(5);
		tile3.addElement(new String("RED"));
		tile3.addElement(new String("BLACK"));
		tile3.addElement(new String("GREEN"));
		tile3.addElement(new String("Purple"));
		tile3.addElement(new Boolean(false));
		
		// set the values for vector for tile 4
		Vector tile4 = new Vector(5);
		tile4.addElement(new String("WHITE"));
		tile4.addElement(new String("BLACK"));
		tile4.addElement(new String("ORANGE"));
		tile4.addElement(new String("BLACK"));
		tile4.addElement(new Boolean(false));
		
		// set the values for vector for tile 5
		Vector tile5 = new Vector(5);
		tile5.addElement(new String("ORANGE"));
		tile5.addElement(new String("PURPLE"));
		tile5.addElement(new String("ORANGE"));
		tile5.addElement(new String("ORANGE"));
		tile5.addElement(new Boolean(false));
		
		// set the values for vector for tile 6
		Vector tile6 = new Vector(5);
		tile6.addElement(new String("RED"));
		tile6.addElement(new String("GREEN"));
		tile6.addElement(new String("BLUE"));
		tile6.addElement(new String("PURPLE"));
		tile6.addElement(new Boolean(false));
		
		// set the values for vector for tile 7
		Vector tile7 = new Vector(5);
		tile7.addElement(new String("WHITE"));
		tile7.addElement(new String("GREEN"));
		tile7.addElement(new String("ORANGE"));
		tile7.addElement(new String("GREEN"));
		tile7.addElement(new Boolean(true));

		// set the values for vector for tile 8
		Vector tile8 = new Vector(5);
		tile8.addElement(new String("WHITE"));
		tile8.addElement(new String("BLUE"));
		tile8.addElement(new String("BLACK"));
		tile8.addElement(new String("BLUE"));
		tile8.addElement(new Boolean(true));
		
		// set the values for vector for tile 9
		Vector tile9 = new Vector(5);
		tile9.addElement(new String("BLUE"));
		tile9.addElement(new String("PURPLE"));
		tile9.addElement(new String("WHITE"));
		tile9.addElement(new String("RED"));
		tile9.addElement(new Boolean(false));
		
		// set the values for vector for tile 10
		Vector tile10 = new Vector(5);
		tile10.addElement(new String("RED"));
		tile10.addElement(new String("GREEN"));
		tile10.addElement(new String("BLACK"));
		tile10.addElement(new String("RED"));
		tile10.addElement(new Boolean(false));
		
		// set the values for vector for tile 11
		Vector tile11 = new Vector(5);
		tile11.addElement(new String("GREEN"));
		tile11.addElement(new String("RED"));
		tile11.addElement(new String("ORANGE"));
		tile11.addElement(new String("WHITE"));
		tile11.addElement(new Boolean(false));

		// set the values for vector for tile 12
		Vector tile12 = new Vector(5);
		tile12.addElement(new String("RED"));
		tile12.addElement(new String("GREEN"));
		tile12.addElement(new String("GREEN"));
		tile12.addElement(new String("RED"));
		tile12.addElement(new Boolean(true));

		// set the values for vector for tile 13
		Vector tile13 = new Vector(5);
		tile13.addElement(new String("BLACK"));
		tile13.addElement(new String("PURPLE"));
		tile13.addElement(new String("BLUE"));
		tile13.addElement(new String("BLUE"));
		tile13.addElement(new Boolean(false));

		// set the values for vector for tile 14
		Vector tile14 = new Vector(5);
		tile14.addElement(new String("ORANGE"));
		tile14.addElement(new String("PURPLE"));
		tile14.addElement(new String("WHITE"));
		tile14.addElement(new String("WHITE"));
		tile14.addElement(new Boolean(true));

		// set the values for vector for tile 15
		Vector tile15 = new Vector(5);
		tile15.addElement(new String("GREEN"));
		tile15.addElement(new String("BLUE"));
		tile15.addElement(new String("ORANGE"));
		tile15.addElement(new String("BLUE"));
		tile15.addElement(new Boolean(false));

		// set the values for vector for tile 16
		Vector tile16 = new Vector(5);
		tile16.addElement(new String("WHITE"));
		tile16.addElement(new String("WHITE"));
		tile16.addElement(new String("RED"));
		tile16.addElement(new String("GREEN"));
		tile16.addElement(new Boolean(false));

		// set the values for vector for tile 17
		Vector tile17 = new Vector(5);
		tile17.addElement(new String("PURPLE"));
		tile17.addElement(new String("PURPLE"));
		tile17.addElement(new String("RED"));
		tile17.addElement(new String("GREEN"));
		tile17.addElement(new Boolean(true));
		
		// set the values for vector for tile 18
		Vector tile18 = new Vector(5);
		tile18.addElement(new String("BLUE"));
		tile18.addElement(new String("RED"));
		tile18.addElement(new String("BLACK"));
		tile18.addElement(new String("GREEN"));
		tile18.addElement(new Boolean(false));

		// set the values for vector for tile 19
		Vector tile19 = new Vector(4);
		tile19.addElement(new String("BLUE"));
		tile19.addElement(new String("BLACK"));
		tile19.addElement(new String("WHITE"));
		tile19.addElement(new String("GREEN"));
		tile19.addElement(new Boolean(false));

		// set the values for vector for tile 20
		Vector tile20 = new Vector(4);
		tile20.addElement(new String("RED"));
		tile20.addElement(new String("GREY"));
		tile20.addElement(new String("RED"));
		tile20.addElement(new String("ORANGE"));
		tile20.addElement(new Boolean(true));

		// set the values for vector for tile 21
		Vector tile21 = new Vector(4);
		tile21.addElement(new String("ORANGE"));
		tile21.addElement(new String("BLUE"));
		tile21.addElement(new String("WHITE"));
		tile21.addElement(new String("ORANGE"));
		tile21.addElement(new Boolean(true));

		// set the values for vector for tile 22
		Vector tile22 = new Vector(4);
		tile22.addElement(new String("RED"));
		tile22.addElement(new String("GREY"));
		tile22.addElement(new String("WHITE"));
		tile22.addElement(new String("PURPLE"));
		tile22.addElement(new Boolean(true));

		// set the values for vector for tile 23
		Vector tile23 = new Vector(4);
		tile23.addElement(new String("PURPLE"));
		tile23.addElement(new String("BLUE"));
		tile23.addElement(new String("PURPLE"));
		tile23.addElement(new String("GREEN"));
		tile23.addElement(new Boolean(true));

		// set the values for vector for tile 24
		Vector tile24 = new Vector(4);
		tile24.addElement(new String("BLUE"));
		tile24.addElement(new String("RED"));
		tile24.addElement(new String("WHITE"));
		tile24.addElement(new String("GREY"));
		tile24.addElement(new Boolean(false));

		// set the values for vector for tile 25
		Vector tile25 = new Vector(4);
		tile25.addElement(new String("BLUE"));
		tile25.addElement(new String("WHITE"));
		tile25.addElement(new String("GREEN"));
		tile25.addElement(new String("PURPLE"));
		tile25.addElement(new Boolean(false));

		// set the values for vector for tile 26
		Vector tile26 = new Vector(4);
		tile26.addElement(new String("GREY"));
		tile26.addElement(new String("WHITE"));
		tile26.addElement(new String("GREY"));
		tile26.addElement(new String("WHITE"));
		tile26.addElement(new Boolean(true));

		// set the values for vector for tile 27
		Vector tile27 = new Vector(4);
		tile27.addElement(new String("PURPLE"));
		tile27.addElement(new String("GREY"));
		tile27.addElement(new String("WHITE"));
		tile27.addElement(new String("ORANGE"));
		tile27.addElement(new Boolean(false));

		// set the values for vector for tile 28
		Vector tile28 = new Vector(4);
		tile28.addElement(new String("ORANGE"));
		tile28.addElement(new String("BLUE"));
		tile28.addElement(new String("ORANGE"));
		tile28.addElement(new String("BLUE"));
		tile28.addElement(new Boolean(true));

		// set the values for vector for tile 29
		Vector tile29 = new Vector(4);
		tile29.addElement(new String("BLUE"));
		tile29.addElement(new String("RED"));
		tile29.addElement(new String("WHITE"));
		tile29.addElement(new String("GREY"));
		tile29.addElement(new Boolean(true));

		// set the values for vector for tile 30
		Vector tile30 = new Vector(4);
		tile30.addElement(new String("RED"));
		tile30.addElement(new String("ORANGE"));
		tile30.addElement(new String("PURPLE"));
		tile30.addElement(new String("WHITE"));
		tile30.addElement(new Boolean(false));

		// set the values for vector for tile 31
		Vector tile31 = new Vector(4);
		tile31.addElement(new String("PURPLE"));
		tile31.addElement(new String("PURPLE"));
		tile31.addElement(new String("GREY"));
		tile31.addElement(new String("PURPLE"));
		tile31.addElement(new Boolean(false));

		// set the values for vector for tile 32
		Vector tile32 = new Vector(4);
		tile32.addElement(new String("BLUE"));
		tile32.addElement(new String("WHITE"));
		tile32.addElement(new String("ORANGE"));
		tile32.addElement(new String("RED"));
		tile32.addElement(new Boolean(false));

		// set the values for vector for tile 33
		Vector tile33 = new Vector(4);
		tile33.addElement(new String("RED"));
		tile33.addElement(new String("WHITE"));
		tile33.addElement(new String("GREY"));
		tile33.addElement(new String("ORANGE"));
		tile33.addElement(new Boolean(false));

		// set the values for vector for tile 34
		Vector tile34 = new Vector(4);
		tile34.addElement(new String("ORANGE"));
		tile34.addElement(new String("GREY"));
		tile34.addElement(new String("PURPLE"));
		tile34.addElement(new String("RED"));
		tile34.addElement(new Boolean(false));

		// set the values for vector for tile 35
		Vector tile35 = new Vector(4);
		tile35.addElement(new String("BLUE"));
		tile35.addElement(new String("PURPLE"));
		tile35.addElement(new String("GREY"));
		tile35.addElement(new String("ORANGE"));
		tile35.addElement(new Boolean(false));

		// set the values for vector for tile 36
		Vector tile36 = new Vector(4);
		tile36.addElement(new String("WHITE"));
		tile36.addElement(new String("GREEN"));
		tile36.addElement(new String("PURPLE"));
		tile36.addElement(new String("BLUE"));
		tile36.addElement(new Boolean(false));

	}

	/**
	 * getter for tiles id
	 * @return the tilesId
	 */
	public int getTilesId() {
		return tilesId;
	}

	/**
	 * setter for tiles id
	 * @param tilesId the tilesId to set
	 */
	public void setTilesId(int tilesId) {
		this.tilesId = tilesId;
	}

	/**
	 * getter for tiles id for the top tile
	 * @return the topTileId
	 */
	public int getTopTileId() {
		return topTileId;
	}




	/**
	 * setter for tiles id for the top tile
	 * @param topTileId the topTileId to set
	 */
	public void setTopTileId(int topTileId) {
		this.topTileId = topTileId;
	}


	/**
	 * getter for tiles id for the bottom tile
	 * @return the bottomTileId
	 */
	public int getBottomTileId() {
		return bottomTileId;
	}


	/**
	 * setter for tiles id for the bottom tile
	 * @param bottomTileId the bottomTileId to set
	 */
	public void setBottomTileId(int bottomTileId) {
		this.bottomTileId = bottomTileId;
	}


	/**
	 * getter for tiles id for the left tile
	 * @return the leftTileId
	 */
	public int getLeftTileId() {
		return leftTileId;
	}


	/**
	 * setter for tiles id for the left tile
	 * @param leftTileId the leftTileId to set
	 */
	public void setLeftTileId(int leftTileId) {
		this.leftTileId = leftTileId;
	}


	/**
	 * getter for tiles id for the right tile
	 * @return the rightTileId
	 */
	public int getRightTileId() {
		return rightTileId;
	}


	/**
	 * setter for tiles id for the right tile
	 * @param rightTileId the rightTileId to set
	 */
	public void setRightTileId(int rightTileId) {
		this.rightTileId = rightTileId;
	}


	/**
	 * getter for the top color for the tile
	 * @return the topColor, string type
	 */
	public String getTopColor() {
		return topColor;
	}


	/**
	 * setter for the top color for the tile
	 * @param topColor the topColor to set
	 */
	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}



	/**
	 * getter for the left color for the tile
	 * @return the leftColor, string type
	 */
	public String getLeftColor() {
		return leftColor;
	}



	/**
	 * setter for the left color for the tile
	 * @param leftColor the leftColor to set
	 */
	public void setLeftColor(String leftColor) {
		this.leftColor = leftColor;
	}



	/**
	 * getter for the right color for the tile
	 * @return the rightColor, string type
	 */
	public String getRightColor() {
		return rightColor;
	}



	/**
	 * setter for the right color for the tile
	 * @param rightColor the rightColor to set
	 */
	public void setRightColor(String rightColor) {
		this.rightColor = rightColor;
	}



	/**
	 * getter for the bottom color for the tile
	 * @return the bottomColor
	 */
	public String getBottomColor() {
		return bottomColor;
	}



	/**
	 * setter for the bottom color for the tile
	 * @param bottomColor the bottomColor to set
	 */
	public void setBottomColor(String bottomColor) {
		this.bottomColor = bottomColor;
	}


	/**
	 * getter method for the platform for the tile
	 * @return the platform
	 */
	public boolean isPlatform() {
		return platform;
	}


	/**
	 * setter method for the platform for the tile
	 * @param platform the platform to set
	 */
	public void setPlatform(boolean platform) {
		this.platform = platform;
	}
	
}
