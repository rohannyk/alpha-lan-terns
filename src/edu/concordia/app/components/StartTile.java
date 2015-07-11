package edu.concordia.app.components;


import java.util.Vector;
/**
 * @author Team E
 * This program is used to create the Start Tile 
 * This Tile is the first tile of the game
 * The Red color in the tile chooses the first player to start the game
 *
 */
public class StartTile {
	
  public Vector<String> edgecolour = new Vector<String>(4);
  
  {
        edgecolour.addElement("RED");
	    edgecolour.addElement("WHITE");
	    edgecolour.addElement("BLUE");
	    edgecolour.addElement("BLACK");
	  
  }
  
	
	
}
	