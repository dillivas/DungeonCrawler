
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
	protected int steps = 0;
	
	//Ghost health
	private static int HEALTH = 100;

	/**
	 * BasicEnemy constructor
	 * @param x coordinates
	 * @param y coordinates
	 * @param id enemy type
	 */
	public Ghost(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		speedX = 3;
		speedY = 3;
	}

	/**
	 * This is the basic enemies hit box.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

	/**
	 * Controls ghost movements
	 */
	@Override
	public void tick() {
		HEALTH = Game.clamp(HEALTH,0,12);
        // makes the bird fly clockwise
        if(steps%140 < 30){
            x += speedX;
        }
        else if(steps%140 < 70){
            y += speedY;
        }
        else if(steps%140 < 100){
            x -= speedX;
        }
        else if(steps%140 < 140){
            y -= speedY;
        }
        steps++;
        
	}

	/**
	 * Controls BasicEnemy rendering
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Render.ghostImage,x,y,50,50, null);
	}
	public void health(Graphics g) {
		  g.setColor(Color.gray);
		  g.fillRect(200 , 15, 120, 32);
		  g.setColor(Color.red);
		  g.fillRect(200 , 15, HEALTH * 10, 32);
		  g.setColor(Color.white);
		  g.drawRect(200 , 15, 120, 32);
	 }
}
