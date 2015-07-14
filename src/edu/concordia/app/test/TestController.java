/**
 * 
 */
package edu.concordia.app.test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.concordia.app.model.*;
import edu.concordia.app.controller.*;
import edu.concordia.app.components.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class to execute Test Cases for Controller Package
 * @author Team E
 */
public class TestController {

	GameInstance gi;
	GameController gc;
	
	@Before
	public void setUp(){
		gc = new GameController();
	}
	
	@After
	public void tearDown(){
		gc = null;
		gi = null;
	}

	
	
	@Test
	/**
	 * Tests the game loading from file functionality
	 */
	 
	public void testLoadGame() {

		String file1 = "test_file1.xml";
		String file2 = "test_file2.xml";
		String file3 = "test_file3.xml";
		
		//not yet done
	}
		
		
		
}
































































































































































































































































































