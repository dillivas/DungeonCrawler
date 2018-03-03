
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * date: 1/28/2018       					*
 * class: BasicEnemy      					*
 *            								*
 * The class control's a BasicEnemy object 	*
 * 											*
 * @author William							*
 */
public class BasicEnemy extends GameObject{

	/**
	 * Variable of the Basic Enemy for its handler,
	 * random set up for its movement, and its hp.
	 * 
	 */
	 protected Handler handler;
	 private Random rand = new Random();
	 private int choose = 0;
	 protected int hp = 100;
	
	 /**
	  * BasicEnemy Constructor
	  * @param x axis location of enemy
	  * @param y axis location of enemy
	  * @param id of object
	  * @param ss sprite image of object
	  * @param handler connection for object use.
	  */
	public BasicEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler=handler;
	}

	/**
	 * Update the object on tick
	 */
	@Override
	public void tick() {
		x += speedX;
		y += speedY;
		
		choose = rand.nextInt(10);
		//AI behavior found on:
		//https://www.youtube.com/watch?v=JBGCCAv76YI&t=1s
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
					
			if(tempObject.getID() == ID.Block) {
				if(getBoundsWall().intersects(tempObject.getBounds())) {
					x += (speedX*2) * -1;
					y += (speedY*2) * -1;
					speedX = 0;
					speedY = 0;
				}else if(choose == 0) {
					speedX = (rand.nextInt(4 - -4) + -4);
					speedY = (rand.nextInt(4 - -4) + -4);
				}
			}
			//Collision effects on enemy
			if(tempObject.getID() == ID.Attack) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 25;
					//Removes fireball
					handler.removeObject(tempObject);
				}
			}
			if(tempObject.getID() == ID.Lava) {
				if(getBounds().intersects(tempObject.getBounds()) && (tempObject.getID() == ID.Lava)) {
					hp -= 1;
				
				}
			}
		}
		if(hp <= 0) handler.removeObject(this);
	}
	
	/**
	 * render enemy image
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Render.enemyImage,x,y,32,32, null);
	}

	/**
	 * Add enemy hitbox
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x ,y ,40,40);
	}
	
	/**
	 * Add second hit box for enemy AI pathing
	 * @return rectangle for hitbox
	 */
	public Rectangle getBoundsWall() {
		return new Rectangle(x - 16,y - 16,64,64);
	}


}
