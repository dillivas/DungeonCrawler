
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.ID;
import game.Render;
import game.SpriteSheet;

/**
 * The class controls a wall or block object.
 * to act as a obstacle to the player.	
 * 
 * date 1/28/2018
 * class wall
 * @author William
 */
public class Fireball extends GameObject {
	
	/**
	 * Variable holds referance to handler for later use
	 */
	 private Handler handler;
	
	 /**
	  * Fireball Constructor
	  * 
	  * @param x axis location of enemy
	  * @param y axis location of enemy
	  * @param speedX controls x direction movement
	  * @param speedY controls y direction movement
	  * @param id of object
	  * @param ss sprite image of object
	  * @param handler for game objects for later this objects use
	  */
	 public Fireball(int x, int y, int speedX,  int speedY, ID id, SpriteSheet ss, Handler handler) {
		super(x,y,speedX,speedY,id, ss);
		this.handler = handler;
		this.setSs(ss);
	 }
	 
	 /**
	  * get object hitbox
	  */
	 @Override
	 public Rectangle getBounds() {
		 return new Rectangle(getX(),getY(),16,16);
	 }

	/**
	 * Update the object on tick
	 */
	 @Override
	 public void tick() {
			setX(getX() + getSpeedX());
			setY(getY() + getSpeedY());

			collision();
	 }
	 
	 /**
	  * This function controls the behavior of this object when it collides with another object.
	  * Collisions use the objects rectangle as a its hitbox.
	  */
	 private void collision() {
			for(int i = 0;i < handler.getObject().size(); i++) {
				GameObject tempObject = handler.getObject().get(i);
				if(tempObject.getID() == ID.Block) {
					if(getBounds().intersects(tempObject.getBounds())) {
						handler.removeObject(this);
					}
				}
			}
		}
	 
	/**
	 * render object image
	 */
	 @Override
	 public void render(Graphics g) {
		 g.drawImage(Render.getFireball(),getX(),getY(),16,16, null);
	}
}

