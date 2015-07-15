/**
 * 
 */
package edu.concordia.app.test;
import java.util.Vector;

import edu.concordia.app.components.DedicationTokens;




import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.concordia.app.main.LanternMain;
import edu.concordia.app.model.GameConfiguration;
import edu.concordia.app.model.GameInstance;

/**
 * @author Team E
 *
 */
public class TestDedicationTokens {

	GameInstance gi;
	GameConfiguration gc;
	LanternMain lm;
	
	@Before
	public void setUp(){
		gc = new GameConfiguration();
		gi = new GameInstance();
		lm = new LanternMain();
	}
	
	@After
	public void tearDown(){
		gc = null;
		gi = null;
		lm = null;
	}
	
	@Test
	public void testInitialzeDedicationTokens() {
		GameConfiguration gc1= new GameConfiguration();
		DedicationTokens dedicationTokensActual= new DedicationTokens();
		dedicationTokensActual= gc1.initializeDedicationTokens(2);
		
		/*Vector<Integer> dedicationTokenFour = new Vector<Integer>();

		Vector<Integer> dedicationTokenSix = new Vector<Integer>();

		Vector<Integer> dedicationTokenSeven = new Vector<Integer>();

		Vector<Integer> genericDedicationTokens = new Vector<Integer>();

			// dedication token four
			dedicationTokenFour.add(8);
			dedicationTokenFour.add(7);
			dedicationTokenFour.add(6);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(5);
			dedicationTokenFour.add(4);
			
			// dedication token six
			dedicationTokenSix.add(9);
			dedicationTokenSix.add(8);
			dedicationTokenSix.add(7);
			dedicationTokenSix.add(6);
			dedicationTokenSix.add(5);
			dedicationTokenSix.add(5);

			// dedication token seven
			dedicationTokenSeven.add(10);
			dedicationTokenSeven.add(9);
			dedicationTokenSeven.add(8);
			dedicationTokenSeven.add(7);
			dedicationTokenSeven.add(6);
			dedicationTokenSeven.add(5);
			
			// generic dedication token
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			genericDedicationTokens.add(4);
			
			DedicationTokens dedicationTokensExpected = new DedicationTokens(dedicationTokenFour, dedicationTokenSix, 
					dedicationTokenSeven, genericDedicationTokens);
			
			assertEquals(dedicationTokensExpected,dedicationTokensActual);*/
		
			
			Vector<Integer> dedicationTokenFour = new Vector<Integer>(dedicationTokensActual.getDedicationTokenFour());
			int sizeX=dedicationTokenFour.size();
			assertEquals(sizeX,6);
			
						
		}
}
