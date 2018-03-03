
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The Abstracr class control's all the game objects
 * @author William
 * date 2/27/2018
 * class GameObject
 */
public abstract class GameObject {
	 
	 //Object x and y coordinates
	 protected int x, y;
	 //Object ID
	 protected ID id;
	 //Objects speed / direction
	 protected int speedX, speedY;
	 //Object Sprite
	 protected SpriteSheet ss;
	 
	 /**
	  * GameObject constructor
	  *
	  * @param x coordinate
	  * @param y coordinate
	  * @param id of object
	  * @param ss sprite image of object
	  */
	 public GameObject(int x, int y, ID id, SpriteSheet ss) {
		  this.x = x;
		  this.y = y;
		  this.id = id;
		  this.ss =ss;
	 }
	 
	 /**
	  * Game Object constructer
	  * @param x axis location of enemy
	  * @param y axis location of enemy
	  * @param speedX controls x direction movement
	  * @param speedY controls y direction movement
	  * @param id of object
	  * @param ss sprite image of object
	  */
	 public GameObject(int x, int y, int speedX, int speedY, ID id, SpriteSheet ss) {
		 this.x = x;
		  this.y = y;
		  this.speedX = speedX;
		  this.speedY = speedY;
		  this.id = id;
		  this.ss =ss;
	}

	 /**
	  * Abstract class to update the object.
	  */
	 public abstract void tick();
	 
	 /**
	  * Abstract class to render the object sprite image.
	  * @param graphic to render
	  */
	 public abstract void render(Graphics g);
	 
	 /**
	  * Abstract class to get the objects hitbox.
	  * @return object hitbox
	  */
	 public abstract Rectangle getBounds();
	 
	 /**
	  * Set x
	  * @param x coordinate to set
	  */
	 public void setX(int x) {
		 this.x = x;
	 }
	 
	 /**
	  * Set y
	  * @param y coordinate to set
	  */
	 public void setY(int y) {
		 this.y = y;
	 }
	 
	 /**
	  * Get x
	  * @return x coordinate
	  */
	 public int getX() {
		 return x;
	 }
	 
	 /**
	  * Get y
	  * @return y coordinate
	  */
	 public int getY() {
		 return y;
	 }
	 
	 /**
	  * Set id
	  * @param id coordinate to set
	  */
	 public void setID(ID id) {
		 this.id = id;
	 }
	 
	 /**
	  * Get id
	  * @return id coordinate
	  */
	 public ID getID() {
		 return id;
	 }
	 
	 /**
	  * Set speedX
	  * @param speedX to set
	  */
	 public void setSpeedX(int speedX) {
		 this.speedX = speedX;
	 }
	 
	 /**
	  * Set speedY
	  * @param speedY to set
	  */
	 public void setSpeedY(int speedY) {
		 this.speedY = speedY;
	 }
	 
	 /**
	  * Get speedX
	  * @return speedX of object
	  */
	 public int getSpeedX() {
		 return speedX;
	 }
	 
	 /**
	  * Get speedY
	  * @return speedY of object
	  */
	 public int getSpeedY() {
		 return speedY;
	 }
}

