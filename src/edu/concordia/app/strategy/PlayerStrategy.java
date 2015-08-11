/**
 * 
 */
package edu.concordia.app.strategy;

import java.util.Scanner;

import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

/**
 * @author lovepreet
 *
 */
public abstract class PlayerStrategy {

	public abstract String makeAMove(Scanner scan, GameInstance gameObjs, Players playing, String opt);

	public abstract Players playerLastTurnChoice(GameInstance gameObj, Players gamePlayer,
			String opt1) ;
}
