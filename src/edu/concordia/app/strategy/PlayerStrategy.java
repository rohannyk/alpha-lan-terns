/**
 * 
 */
package edu.concordia.app.strategy;

import java.util.Scanner;

import javax.xml.bind.annotation.XmlRootElement;

import edu.concordia.app.model.GameInstance;
import edu.concordia.app.model.Players;

@XmlRootElement
/**
 * @author lovepreet
 *
 */
public class PlayerStrategy {

	public String makeAMove(GameInstance gameObjs, Players playing, String opt){
		return opt;
	}

	public Players playerLastTurnChoice(GameInstance gameObj, Players gamePlayer,
			String opt1){
		return gamePlayer;
	}
}
