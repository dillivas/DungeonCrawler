
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The class control's the player object 
 * @author William
 * date 2/27/2018
 * class Player
 */
public class Player extends GameObject {

	//Store handler for later use
	protected Handler handler;
	
	//Store last key entered by user
	//Used to show character direction to face
	public char lastKey = 'd';
	
	//Store pressLength
	public int pressLength = 4;

	/**
	 * Player constructor
	 * @param x coordinate of player
	 * @param y coordinate of player
	 * @param id of player object
	 */
	public Player(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;
	}

	/**
	 * Player location
	 */
	public void tick() {
		x += speedX;
		y += speedY;
		
		//Call collision function
		collision();
		
		//Player Movement
		if(handler.getUp()) speedY = -5;
		else if(!handler.getDown()) speedY = 0;
		
		if(handler.getDown()) speedY = 5;
		else if(!handler.getUp()) speedY = 0;
		
		if(handler.getRight()) speedX = 5;
		else if(!handler.getLeft()) speedX = 0;
		
		if(handler.getLeft()) speedX = -5;
		else if(!handler.getRight()) speedX = 0;
	}
	
   /**
	* This function controls the behavior of this object when it collides with another object.
	* Collisions use the objects rectangle as a its hitbox.
	*/
	private void collision() {
		for(int i = 0;i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += speedX * -1;
					y += speedY * -1;
				}
			}
			
			if(tempObject.getID() == ID.Enemy) {

				if(getBounds().intersects(tempObject.getBounds()) && (tempObject.getID() == ID.Enemy)) {
					HUD.HEALTH -= 1;
				}
			}
			if(tempObject.getID() == ID.Lava) {

				if(getBounds().intersects(tempObject.getBounds()) && (tempObject.getID() == ID.Lava)) {
					HUD.HEALTH -= 1;
				}
			}
		}
	}
	
	/**
	 * This is the Players hit box.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}

	/**
	 * Player Graphic
	 * @param g is player graphic
	 */
	public void render(Graphics g) {	
		if(handler.getStop() == true && lastKey=='w') {
			g.drawImage(Render.playerUpStanding,x,y,32,50, null);
		}
		if(handler.getStop() == true && lastKey=='s') {
			g.drawImage(Render.playerDownStanding,x,y,32,50, null);
		}
		if(handler.getStop() && lastKey=='a') {
			g.drawImage(Render.playerLeftStanding,x,y,32,50, null);
		}
		if(handler.getStop() && lastKey=='d') {
			g.drawImage(Render.playerRightStanding,x,y,32,50, null);
		}
		
		
		if(handler.getUp() == true) {
			lastKey='w';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.playerForwardWalk1,x,y,32,50, null);
			}
			else {
				g.drawImage(Render.playerForwardWalk2,x,y,32,50, null);
			}
				
		}
		if(handler.getDown() == true) {
			lastKey='s';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.playerDownWalk1,x,y,32,50, null);
			}
			else {
				g.drawImage(Render.playerDownWalk2,x,y,32,50, null);
			}
		}
		
		if(handler.getLeft() == true) {
			lastKey='a';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.playerLeftWalk1,x,y,32,50, null);
			}
			else {
				g.drawImage(Render.playerLeftWalk2,x,y,32,50, null);
			}
		}
		
		if(handler.getRight() == true) {
			lastKey='d';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.playerRightWalk1,x,y,32,50, null);
			}
			else {
				g.drawImage(Render.playerRightWalk2,x,y,32,50, null);
			}
		}	
	}
}

