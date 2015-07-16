/**
 * 
 */
package edu.concordia.app.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Team E
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestDedicationTokens.class, TestFavorTokens.class, TestLakeTiles.class, 
				TestPlayers.class,TestComponents.class,TestStartTile.class})
public class AllTests {

}
