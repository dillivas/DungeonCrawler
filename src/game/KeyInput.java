
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The class reads player inputs
 * @author William
 * date 2/27/2018
 * class KeyInput
 */
public class KeyInput extends KeyAdapter{

	//Store handler for KeyInput use
	private Handler handler;
	
	//Store sprite sheet.
	//Not used Yet
	private SpriteSheet ss;
	
	//Stores inputs for menu use
	private static boolean pause = false;
	private static boolean stop = false;
	private static boolean up = false;
	private static boolean down = false;
	private static boolean space = false;
	private int count = 0;

	/**
	 * @return the handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * @return the ss
	 */
	public SpriteSheet getSs() {
		return ss;
	}

	/**
	 * @param ss the ss to set
	 */
	public void setSs(SpriteSheet ss) {
		this.ss = ss;
	}

	/**
	 * @return the pause
	 */
	public static boolean getPause() {
		return pause;
	}

	/**
	 * @param pause the pause to set
	 */
	public static void setPause(boolean pause) {
		KeyInput.pause = pause;
	}

	/**
	 * @return the stop
	 */
	public static boolean getStop() {
		return stop;
	}

	/**
	 * @param stop the stop to set
	 */
	public static void setStop(boolean stop) {
		KeyInput.stop = stop;
	}

	/**
	 * @return the up
	 */
	public static boolean getUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public static void setUp(boolean up) {
		KeyInput.up = up;
	}

	/**
	 * @return the down
	 */
	public static boolean getDown() {
		return down;
	}

	/**
	 * @param down the down to set
	 */
	public static void setDown(boolean down) {
		KeyInput.down = down;
	}

	/**
	 * @return the space
	 */
	public static boolean getSpace() {
		return space;
	}

	/**
	 * @param space the space to set
	 */
	public static void setSpace(boolean space) {
		KeyInput.space = space;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * KeyInput constructor
	 * 
	 * @param handler for game objects for later this objects use
	 * @param ss sprite image of object
	 */
	public KeyInput(Handler handler, SpriteSheet ss) {
		this.handler = handler;
		this.ss = ss;
	}
	
	/**
	 * Make action happen based on key being pressed
	 * @param e is a KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//introduce walking by including counter, everyfour switches picture

		for(int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);
			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_Q) {
					count++;
					if (count % 2 ==0){
						handler.setPause(false);
						pause = false;
					}else {
						handler.setPause(true);
						pause = true;
					}
				}
				
				if(key == KeyEvent.VK_W) {
					handler.setUp(true);
					handler.setStop(false);
					up = true;
					down = false;
				}
				if(key == KeyEvent.VK_S) {
					handler.setDown(true);
					handler.setStop(false);
					up = false;
					down = true;
				}
				if(key == KeyEvent.VK_A) {
					handler.setLeft(true);
					handler.setStop(false);
				}
				if(key == KeyEvent.VK_D) {
					handler.setRight(true);
					handler.setStop(false);
				}
				if(key == KeyEvent.VK_UP) {
					handler.setUpAim(true);
					
				}
				if(key == KeyEvent.VK_DOWN) {
					handler.setDownAim(true);
				}
				if(key == KeyEvent.VK_LEFT) {
					handler.setLeftAim(true);	
				}
				if(key == KeyEvent.VK_RIGHT) {
					handler.setRightAim(true);	
				}
				if(key == KeyEvent.VK_SPACE) {
					handler.setSpace(true);
					space = true;
				}
			}
		}

		if(key == KeyEvent.VK_ESCAPE) System.exit(1);

	}

	/**
	 * Make action happen based of key being released
	 * @param e is a KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i = 0; i < handler.getObject().size(); i++) {
			GameObject tempObject = handler.getObject().get(i);

			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
				if(key == KeyEvent.VK_UP) {
					handler.setUpAim(false); 
					handler.addObject(new Fireball(tempObject.getX(),tempObject.getY(),0,-5,ID.Attack,ss, handler));
				}
				if(key == KeyEvent.VK_DOWN) {
					handler.setDownAim(false);
					handler.addObject(new Fireball(tempObject.getX(),tempObject.getY(),0,5,ID.Attack,ss, handler));
				}
				if(key == KeyEvent.VK_LEFT) {
					handler.setLeftAim(false);
					handler.addObject(new Fireball(tempObject.getX(),tempObject.getY(),-5,0,ID.Attack,ss, handler));
				}
				if(key == KeyEvent.VK_RIGHT) {
					handler.setRightAim(false);
					handler.addObject(new Fireball(tempObject.getX(),tempObject.getY(),5,0,ID.Attack,ss, handler));
				}
				if(key == KeyEvent.VK_SPACE) {
					handler.setSpace(false);
					space = false;
				}
				handler.setStop(true);
			}
		}
	}
}