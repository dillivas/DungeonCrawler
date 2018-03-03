
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * The class controls a wall or block object
 * to act as a obstacle to the player.	
 * 
 * @author William
 * date 2/27/2018
 * class block
 */
public class Block extends GameObject{
	
	//Hold object sprite image
	private BufferedImage blockImage;
	
	/**
	 * Block Constructor
	 * @param x axis location of enemy
	 * @param y axis location of enemy
	 * @param id of object
	 * @param ss sprite image of object
	 */
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x,y,id, ss);
		blockImage = ss.grabImage(2,1,32,32);
	}
	/**
	 * Update the object on tick
	 */
	@Override
	public void tick() {
		
	}
	
	/**
	 * render object image
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(blockImage, getX(), getY(), null);
	}
	
	/**
	 * Add object hitbox
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),32,32);
	}
}
