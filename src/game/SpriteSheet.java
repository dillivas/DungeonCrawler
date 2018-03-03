
package game;

import java.awt.image.BufferedImage;

/**
 * Class to handle sprite sheet for game
 * @author William
 * date 2/27/2018
 * class SpriteSheet
 */
public class SpriteSheet {

	//Store image found
	private BufferedImage image;
	
	/**
	 * Stores the image
	 * @param image to store and use as sprite sheet
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Find the sprite in the sheet and cut it out and return it for rendering
	 * @param col of image location
	 * @param row of image location
	 * @param width of image location
	 * @param height of image location
	 * @return the cut out image or sprite.
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage((col * 32) - 32, (row*32) - 32, width, height);
	}
}