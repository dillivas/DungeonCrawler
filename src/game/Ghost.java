
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * This class controls the ghost enemy.
 * Not yet used in game and some work on its AI is still needed.
 * 
 * @author William
 * date 2/27/2018
 * class Ghost
 */
public class Ghost extends GameObject{

	//Random variable for ghost movement
	 private Random rand;
	
	//Ghost "steps" variable
	private int steps = 0;
	
	//Ghost health
	private static int health = 100;

	/**
	 * BasicEnemy constructor
	 * @param x coordinates
	 * @param y coordinates
	 * @param id enemy type
	 * @param ss sprite sheet
	 */
	public Ghost(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		setSpeedX(3);
		setSpeedY(3);
	}

	/**
	 * This is the basic enemies hit box.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),32,32);
	}

	/**
	 * Controls ghost movements
	 */
	@Override
	public void tick() {
		health = Game.clamp(health,0,12);
        // makes the bird fly clockwise
        if(steps%140 < 30){
            setX(getX() + getSpeedX());
        }
        else if(steps%140 < 70){
            setY(getY() + getSpeedY());
        }
        else if(steps%140 < 100){
            setX(getX() - getSpeedX());
        }
        else if(steps%140 < 140){
            setY(getY() - getSpeedY());
        }
        steps++;
        
	}

	/**
	 * Controls BasicEnemy rendering
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Render.getGhostImage(),getX(),getY(),50,50, null);
	}
	/**
	 * Display ghost graphic
	 * @param g graphic
	 */
	public void health(Graphics g) {
		  g.setColor(Color.gray);
		  g.fillRect(200 , 15, 120, 32);
		  g.setColor(Color.red);
		  g.fillRect(200 , 15, health * 10, 32);
		  g.setColor(Color.white);
		  g.drawRect(200 , 15, 120, 32);
	 }
}
