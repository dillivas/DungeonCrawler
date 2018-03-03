
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Class controls Lava object
 * @author William
 * date 2/27/2018
 * class Lava
 */
public class Lava extends GameObject{
	
	//Variable holds image of lava object to render
	private BufferedImage lavaImage;

	/**
	 * Lava constructor
	 * @param x axis location of enemy
	 * @param y axis location of enemy
	 * @param id of object
	 * @param ss sprite image of object
	 */
	public Lava(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		lavaImage = ss.grabImage(5,1,32,32);
	}

	/**
	 * Update lava object.
	 * Nothing to update
	 */
	@Override
	public void tick() {
		
	}

	/**
	 * render object image
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(lavaImage, x, y, null);
	}

	/**
	 * get object hitbox
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
}
