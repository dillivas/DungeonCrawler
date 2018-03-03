
package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The class keeps track of player HUD 
 * 
 * @author William
 * date 1/28/2018
 * class hub
 */
public class HUD {
	
	//Move Health to player and displaye health and mana in game function. Should stop null pointer
	 //private GameObject gameObject;
	 private static int health = 100;
	 
	 /**
	 * @return the health
	 */
	public static int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public static void setHealth(int health) {
		HUD.health = health;
	}

	/**
	  * Update player health bar on tick
	  */
	 public void tick() {
		 System.out.println();
		  health = Game.clamp(health,0,100);
		  //System.out.println(HEALTH);
	 }
	 
	 /**
	  * Render the object image
	  * @param g object graphic
	  */
	 public void render(Graphics g){
		  g.setColor(Color.gray);
		  g.fillRect(15 , 15, 200, 32);
		  g.setColor(Color.red);
		  g.fillRect(15 , 15, health * 2, 32);
		  g.setColor(Color.white);
		  g.drawRect(15 , 15, 200, 32);
	 }
}