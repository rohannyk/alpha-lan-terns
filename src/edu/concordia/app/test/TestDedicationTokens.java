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
	
	
	/**
	 * This test is used to check if the dedication tokens are being initialized
	 */
	@Test
	public void testInitialzeDedicationTokens() {
		GameConfiguration gc1= new GameConfiguration();
		DedicationTokens dedicationTokensActual= new DedicationTokens();
		dedicationTokensActual= gc1.initializeDedicationTokens(2);
		
				
			Vector<Integer> dedicationTokenFour = new Vector<Integer>(dedicationTokensActual.getDedicationTokenFour());
			int sizeX=dedicationTokenFour.size();
			assertEquals(sizeX,6);
			
						
		}
}
