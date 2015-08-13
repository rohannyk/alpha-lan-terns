/**
 * 
 */
package edu.concordia.app.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test Suite Class
 * @author Team E
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestDedicationTokens.class, TestFavorTokens.class, TestLakeTiles.class, 
				TestPlayers.class,TestComponents.class,TestStartTile.class, TestMakeDedicationTest.class,
				TestPlayerStrategyChoiceTest.class })
public class AllTests {

}
