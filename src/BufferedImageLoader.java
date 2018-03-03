

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Load image for sprite sheet.
 * In the future this class will be combined with render class.
 * 
 * @author William
 * date 2/27/2018
 * class BufferedImageLoader
 */
public class BufferedImageLoader {

	/**
	 * Image to load.
	 */
	private BufferedImage image;
	
	/**
	 * Function to find and load the sprite sheet image
	 * @param path string to find the image
	 * @return image sprite sheet
	 */
	public BufferedImage loadImage(String path){
		try {
			//Change Path Later for more levels
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
