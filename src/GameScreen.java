
import java.awt.Graphics;

/**
 * This Function Controls the pause and start screen of the game.
 * @author Sierra
 */
public class GameScreen{

	/**
	 * Variables to hold the last key pressed and the game handler.
	 */
	private Handler handler;
	public char lastKey = 'd';
	public int count = 0;

	/**
	 * Controls the start screen
	 * @param g graphics image to pass to function
	 */
	public void startScreen(Graphics g) {
		/**
		 * Controls the start screen
		 */
		if (Game.start == false) {
			//Image of start selected
			g.drawImage(Render.gameStart,0,0,637,485, null);
			if(KeyInput.space == true) {
				Game.start = true;
			}

			if(KeyInput.up == true){
				g.drawImage(Render.gameStart,0,0,637,485, null);

				if(KeyInput.space == true) {
					Game.start = true;
				}
			}
			//Image of quite selected
			if (KeyInput.down == true){
			    g.drawImage(Render.startQuit,0,0,637,485, null);
				if(KeyInput.space == true) {
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
		if (KeyInput.pause == true) {
			g.drawImage(Render.pauseQuit,0,0,637,485, null);
			if(KeyInput.space == true && count < 2) {
				System.exit(1);
			}

			if(KeyInput.up == true) {
				count++;
				g.drawImage(Render.pauseQuit,0,0,637,485, null);

				if(KeyInput.space == true) {
					System.exit(1);
				}
			}

			if (KeyInput.down == true){
				count++;
				g.drawImage(Render.pauseRestart,0,0,637,485, null);
				if(KeyInput.space == true) {
					Game.restart = true;
				}
			}
		}

		/**
		 * Controls the death screen
		 */
		if(HUD.HEALTH == 0) {
			//Quite selected
			if(KeyInput.up == true) {
				g.drawImage(Render.gameQuit,0,0,637,485, null);

				if(KeyInput.space == true) {
					System.exit(1);
				}
				//g.fillRect(x, y, 32, 32);
			}
			//Restart selected
			if (KeyInput.down == true){
				g.drawImage(Render.gameRestart,0,0,637,485, null);
				//newGame
				if(KeyInput.space == true) {
					Game.restart = true;
				}
			}
		}
	}
}