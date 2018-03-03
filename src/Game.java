
/**
 * Info about this package doing something for package-info.java file.
 * package-info.java;
 */

package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import game.GameScreen;
import game.HUD;
import game.Handler;
import game.ID;
import game.KeyInput;
import game.Lava;
import game.Player;
import game.Render;
import game.SpriteSheet;
import game.Window;

/**
 * Function controls the game setup and running.
 * 
 * date 2/27/2018
 * class Game
 * @author William
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 665215786302647934L;

	//Window Size settings
	public static final int WIDTH = 640, HEIGHT = 512;

	/**
	 * Variables to hold all of the important game functions
	 * Such as the handler, thread, player hub, running status,
	 * and images and sprite sheets to load in at start of game.
	 */
	private boolean running = false;
	private static Game game;
	private Handler handler;
	private Thread thread;
	private HUD hud;
	private GameScreen gameScreen;
	private SpriteSheet ss;
	private BufferedImage spriteSheet = null;
	private BufferedImage floor = null;
	private BufferedImage level = null;
	private static boolean start = false;
	private static boolean restart = true;



	/**
	 * Game object constructed
	 */
	public Game() {

		//The order that these load in is very important
		Render.load();
		handler = new Handler();
		new Window(WIDTH, HEIGHT, "Dungeon Crawler", this);
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/Test2.png");
		spriteSheet = loader.loadImage("/BlocksNew1.png");
		ss = new SpriteSheet(spriteSheet);
		floor = ss.grabImage(1, 1, 32, 32);
		loadLevel(level);
		hud = new HUD();
		gameScreen = new GameScreen();
		this.addKeyListener(new KeyInput(handler, ss));

		//Start Game
		start();
	}

	/**
	 * Start Game and thread setup
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * This class restarts the game
	 */
	public void restart() {
		Render.load();
		handler = new Handler();
		//new Window(WIDTH, HEIGHT, "Dungeon Crawler", this);
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/Test2.png");
		spriteSheet = loader.loadImage("/BlocksNew1.png");
		ss = new SpriteSheet(spriteSheet);
		floor = ss.grabImage(1, 1, 32, 32);
		loadLevel(level);
		hud = new HUD();
		HUD.setHealth(100);
		gameScreen = new GameScreen();
		this.addKeyListener(new KeyInput(handler, ss));

	}
	
	/**
	 * This function checks for the game to restart and restarts the game.	
	 */
	public static void checkForRestart(){
		//Always run loop checking for restart
		//System.out.println(restart);
		if (restart == true) {
			
			Game.start = false;
			restart = false;
			KeyInput.setPause(false);
			game.restart();
		}
	}
	/**
	 * Stop Game
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Game Loop.
	 * Also controls the frames.
	 */
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1){
				delta--;
				updates++;
				//Used for pause and death screen
				if (!(HUD.getHealth() == 0) && (KeyInput.getPause() == false) && (Game.getStart() == true)) {
					tick();
				}
				checkForRestart();

			}
			if(running) {
				render();
			}
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames + " Updates: " + updates);
				frames = 0;
				updates =0;

			}
		}
		//Stop the Game
		stop();
	}

	/**
	 * Run next update of game objects based on the game loop
	 */
	private void tick() {
		handler.tick();
		hud.tick();
	}


	/**
	 * Controls what renders on the screen
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		//Code between these comment lines controls what we load into the window
		///////////////////////////////////////

		for(int xx = 0; xx < (30*72); xx += 32) {
			for(int yy = 0; yy < (30*72); yy += 32) {
				g.drawImage(floor, xx, yy, null);
			}
		}

		handler.render(g);
		hud.render(g);
		gameScreen.startScreen(g);
		gameScreen.render(g);
		///////////////////////////////////////
		g.dispose();
		bs.show();
	}

	/**
	 * Method to make sure player stays within room
	 * @param var player movement
	 * @param min room size
	 * @param max room size
	 * @return player position
	 */
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			var = max;
			return var;
		}else if(var <= min) {
			var = min;
			return var;

		}else {
			return var;
		}
	}


	/**
	 * Load in the game level objects
	 * @param image used for mapping the game level
	 */
	private void loadLevel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		for(int xx = 0; xx < width; xx++) {
			for(int yy = 0; yy < height; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				//Code between lines controls what objects are rendered into the game.
				/////////////////////////////////////////////////////////
				//Load Level First then enemy and player
				if(red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(xx*32, yy*32, ID.Player,ss, handler));
				}
				if(red == 255 && green == 0 && blue == 0) {
					handler.addObject(new Block(xx*32, yy*32, ID.Block,ss));
				}
				if(red == 255 && green == 100 && blue == 0) {
					handler.addObject(new Lava(xx*32, yy*32, ID.Lava,ss));
				}
				if(red == 0 && green == 255 && blue == 0) {
					handler.addObject(new BasicEnemy(xx*32, yy*32, ID.Enemy,ss,handler));
				}
				
				//////////////////////////////////////////////////////////
				
			}
		}
	}
	
	/**
	 * Getter method for start
	 * @return start state
	 */
	public static boolean getStart() {
		return start;
	}	
	/**
	 * get restart
	 * @return restart restart
	 */
	public static boolean getRestart() {
		return restart;
	}
	
	/**
	 * set start
	 * @param x restart
	 */
	public static void setStart(boolean x) {
		start  = x;
	}
	
	/**
	 * set testart
	 * @param x restart
	 */
	public static void setRestart(boolean x) {
		restart  = x;
	}
	
	/**
	 * Main method to launch game
	 * @param args default argument
	 */
	public static void main(String args[])
	{
		//Start new launch and game
		game = new Game();
	}
}