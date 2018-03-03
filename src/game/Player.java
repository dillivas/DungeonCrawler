
package game;

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
	private Handler handler;
	
	//Store last key entered by user
	//Used to show character direction to face
	private char lastKey = 'd';
	
	//Store pressLength
	private int pressLength = 4;

	/**
	 * Player constructor
	 * @param x coordinate of player
	 * @param y coordinate of player
	 * @param id of player object
	 * @param ss spread sheet
	 * @param handler handler for object use
	 */
	public Player(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;
	}

	/**
	 * Player location
	 */
	public void tick() {
		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());
		
		//Call collision function
		collision();
		
		//Player Movement
		if(handler.getUp()) setSpeedY(-5);
		else if(!handler.getDown()) setSpeedY(0);
		
		if(handler.getDown()) setSpeedY(5);
		else if(!handler.getUp()) setSpeedY(0);
		
		if(handler.getRight()) setSpeedX(5);
		else if(!handler.getLeft()) setSpeedX(0);
		
		if(handler.getLeft()) setSpeedX(-5);
		else if(!handler.getRight()) setSpeedX(0);
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
					setX(getX() + getSpeedX() * -1);
					setY(getY() + getSpeedY() * -1);
				}
			}
			
			if(tempObject.getID() == ID.Enemy) {

				if(getBounds().intersects(tempObject.getBounds()) && (tempObject.getID() == ID.Enemy)) {
					HUD.setHealth(HUD.getHealth() - 1);
				}
			}
			if(tempObject.getID() == ID.Lava) {

				if(getBounds().intersects(tempObject.getBounds()) && (tempObject.getID() == ID.Lava)) {
					HUD.setHealth(HUD.getHealth() - 1);
				}
			}
		}
	}
	
	/**
	 * This is the Players hit box.
	 * @return Rectangle returns hit box
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),32,48);
	}

	/**
	 * Player Graphic
	 * @param g is player graphic
	 */
	public void render(Graphics g) {	
		if(handler.getStop() == true && lastKey=='w') {
			g.drawImage(Render.getPlayerUpStanding(),getX(),getY(),32,50, null);
		}
		if(handler.getStop() == true && lastKey=='s') {
			g.drawImage(Render.getPlayerDownStanding(),getX(),getY(),32,50, null);
		}
		if(handler.getStop() && lastKey=='a') {
			g.drawImage(Render.getPlayerLeftStanding(),getX(),getY(),32,50, null);
		}
		if(handler.getStop() && lastKey=='d') {
			g.drawImage(Render.getPlayerRightStanding(),getX(),getY(),32,50, null);
		}
		
		
		if(handler.getUp() == true) {
			lastKey='w';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.getPlayerForwardWalk1(),getX(),getY(),32,50, null);
			}
			else {
				g.drawImage(Render.getPlayerForwardWalk2(),getX(),getY(),32,50, null);
			}
				
		}
		if(handler.getDown() == true) {
			lastKey='s';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.getPlayerDownWalk1(),getX(),getY(),32,50, null);
			}
			else {
				g.drawImage(Render.getPlayerDownWalk2(),getX(),getY(),32,50, null);
			}
		}
		
		if(handler.getLeft() == true) {
			lastKey='a';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.getPlayerLeftWalk1(),getX(),getY(),32,50, null);
			}
			else {
				g.drawImage(Render.getPlayerLeftWalk2(),getX(),getY(),32,50, null);
			}
		}
		
		if(handler.getRight() == true) {
			lastKey='d';
			pressLength++;
			if(pressLength%2==0) {
				g.drawImage(Render.getPlayerRightWalk1(),getX(),getY(),32,50, null);
			}
			else {
				g.drawImage(Render.getPlayerRightWalk2(),getX(),getY(),32,50, null);
			}
		}	
	}
}