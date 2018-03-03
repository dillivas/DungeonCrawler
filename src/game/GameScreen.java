
package game;

import java.awt.Graphics;

/**
 * This Function Controls the pause and start screen of the game.
 * @author Sierra
 */
public class GameScreen{

	/**
	 * Variables to hold the last key pressed and the game handler.
	 */
	//private Handler handler;
	private char lastKey = 'd';
	private int count = 0;
	
	/**
	 * get lastKey
	 * @return lastKey pressed
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * set lastKey
	 * @param count store
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * get lastKey
	 * @return lastKey pressed
	 */
	public char getLastKey() {
		return lastKey;
	}
	
	/**
	 * set lastKey
	 * @param lastKey pressed
	 */
	public void setLastKey(char lastKey) {
		this.lastKey = lastKey;
	}

	/**
	 * Controls the start screen
	 * @param g graphics image to pass to function
	 */
	public void startScreen(Graphics g) {
		/**
		 * Controls the start screen
		 */
		if (Game.getStart()  == false) {
			//Image of start selected
			g.drawImage(Render.getGameStart(),0,0,637,485, null);
			if(KeyInput.getSpace() == true) {
				Game.setStart(true);
			}

			if(KeyInput.getUp() == true){
				g.drawImage(Render.getGameStart(),0,0,637,485, null);

				if(KeyInput.getSpace() == true) {
					Game.setStart(true);
				}
			}
			//Image of quite selected
			if (KeyInput.getDown() == true){
			    g.drawImage(Render.getStartQuit(),0,0,637,485, null);
				if(KeyInput.getSpace() == true) {
					System.exit(1);		
				}
			}
		}
	}

	/**
	 * Controls the pause and death screen rendering
	 * @param g graphics image to pass to function
	 */
	public void render(Graphics g) {
		/**
		 * Controls the pause screen controls
		 */
		if (KeyInput.getPause() == true) {
			g.drawImage(Render.getPauseQuit(),0,0,637,485, null);
			if(KeyInput.getSpace() == true && count < 2) {
				System.exit(1);
			}

			if(KeyInput.getUp() == true) {
				count++;
				g.drawImage(Render.getPauseQuit(),0,0,637,485, null);

				if(KeyInput.getSpace() == true) {
					System.exit(1);
				}
			}

			if (KeyInput.getDown() == true){
				count++;
				g.drawImage(Render.getPauseRestart(),0,0,637,485, null);
				if(KeyInput.getSpace() == true) {
					Game.setRestart(true);
				}
			}
		}

		/**
		 * Controls the death screen
		 */
		if(HUD.getHealth() == 0) {
			//Quite selected
			if(KeyInput.getUp() == true) {
				g.drawImage(Render.getGameQuit(),0,0,637,485, null);

				if(KeyInput.getSpace() == true) {
					System.exit(1);
				}
				//g.fillRect(x, y, 32, 32);
			}
			//Restart selected
			if (KeyInput.getDown() == true){
				g.drawImage(Render.getGameRestart(),0,0,637,485, null);
				//newGame
				if(KeyInput.getSpace() == true) {
					Game.setRestart(true);
				}
			}
		}
	}
}