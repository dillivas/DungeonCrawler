
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
	private GameObject gameObject;
	 public static int HEALTH = 100;
	 
	 /**
	  * Update player health bar on tick
	  */
	 public void tick() {
		 System.out.println();
		  HEALTH = Game.clamp(HEALTH,0,100);
		  //System.out.println(HEALTH);
	 }
	 
	 /**
	  * Render the object image
	  * @param object graphic
	  */
	 public void render(Graphics g){
		  g.setColor(Color.gray);
		  g.fillRect(15 , 15, 200, 32);
		  g.setColor(Color.red);
		  g.fillRect(15 , 15, HEALTH * 2, 32);
		  g.setColor(Color.white);
		  g.drawRect(15 , 15, 200, 32);
	 }
}