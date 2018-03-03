/************************************************
 * Written By: William Mckeever     			*
 * Date: 1/28/2018        						*
 * Class: Handler        						*
 *             									*
 * This class handles all of the game objects 	*
 * 												*
 * This function also handles all user inputs 	*
 * and stores them for later use in the game.	*
 ************************************************/


import java.awt.Graphics;
import java.util.LinkedList;

/**
 * This class handles all of the game objects.
 * 
 * This function also handles all user inputs
 * and stores them for later use in the game.
 * 
 * @author William
 * date 2/27/2018
 * class Handler
 */
public class Handler {
	 //List of objects
	 public LinkedList<GameObject> object = new LinkedList<GameObject>();
	 
	 /**
	  * Variables to handle all of the players inputs.
	  * The are Saved in handler as they function better here
	  * then when in the keyInput class.
	  */
	 private boolean up = false, down = false, right = false, left = false, stop = true;
	 private boolean upAim = false, downAim = false, rightAim = false, leftAim = false;
	 private boolean space = false;
	 private boolean pause = false;
	 
	 /**
	  * get pause
	  * @return pause
	  */
	 public boolean getPause() {
		return pause;
	}

	 /**
	  * set pause
	  * @param pause
	  */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * get space
	 * @return space
	 */
	public boolean getSpace() {
		return space;
	}

	/**
	 * set space
	 * @param space
	 */
	public void setSpace(boolean space) {
		this.space = space;
	}

	/**
	 * get up
	 * @return up
	 */
	public boolean getUp() {
		return up;
	}

	/**
	 * set up 
	 * @param up
	 */
	public void setUp(boolean up) {
		this.up = up;
	}
 
	/**
	 * get down
	 * @return dwon
	 */
	public boolean getDown() {
		return down;
	}

	/**
	 * set down
	 * @param down
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * get right
	 * @return
	 */
	public boolean getRight() {
		return right;
	}

	/**
	 * set right
	 * @param right
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * get left
	 * @return left
	 */
	public boolean getLeft() {
		return left;
	}

	/**
	 * set left
	 * @param left
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * get stop
	 * @return stop
	 */
	public boolean getStop() {
		return stop;
	}

	/**
	 * set stop
	 * @param stop
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * get upAim
	 * @return upAim
	 */
	public boolean getUpAim() {
		return upAim;
	}

	/**
	 * set upAim
	 * @param upAim
	 */
	public void setUpAim(boolean upAim) {
		this.upAim = upAim;
	}

	/**
	 * get downAim
	 * @return
	 */
	public boolean getDownAim() {
		return downAim;
	}

	/**
	 * set downAim
	 * @param downAim
	 */
	public void setDownAim(boolean downAim) {
		this.downAim = downAim;
	}

	/**
	 * get rightAim
	 * @return rightAim
	 */
	public boolean getRightAim() {
		return rightAim;
	}

	/**
	 * set rightAim
	 * @param rightAim
	 */
	public void setRightAim(boolean rightAim) {
		this.rightAim = rightAim;
	}

	/**
	 * get leftAim
	 * @return leftAim
	 */
	public boolean getLeftAim() {
		return leftAim;
	}

	/**
	 * set leftAim
	 * @param leftAim
	 */
	public void setLeftAim(boolean leftAim) {
		this.leftAim = leftAim;
	}

	 
	 
	 /**
	  * Controls the game object tick
	  */
	 public void tick() {
		  for(int i = 0; i < object.size(); i++) {
			   GameObject tempObject = object.get(i);
			   tempObject.tick();
		  }
	 }
	 
	 /**
	  * Controls adding object rendering
	  * @param g graphics to render
	  */
	 public void render (Graphics g) {
		  for(int i = 0; i < object.size(); i++) {
			   GameObject tempObject = object.get(i);
			   tempObject.render(g);
		  }
	 }
	 
	 /**
	  * Adds the object to the list
	  * @param object to add to list
	  */
	 public void addObject(GameObject object) {
		 this.object.add(object);
	 }
	 
	 /**
	  * Removes the object from the list
	  * @param object to remove from the list
	  */
	 public void removeObject(GameObject object) {
		 this.object.remove(object);
	 }
}
