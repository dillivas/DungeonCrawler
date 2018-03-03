

package game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This Class controls the game window
 * 
 * date 1/28/2018
 * class Window 
 * @author William
 */
public class Window extends Canvas{
 
	//Store JFrame for rendering the window
	private JFrame frame;
	
	 //Add generated serial version ID
	 //unsure what this does
	 private static final long serialVersionUID = 354447516600833510L;
	 
	 /**
	  * Window constructor
	  *
	  * @param width of window
	  * @param height of window
	  * @param title of window
	  * @param game launch be displayed
	  */
	 public Window (int width, int height, String title, Game game) {
		  System.out.println("Working");
		  frame = new JFrame(title);
		  
		  frame.setPreferredSize(new Dimension(width, height));
		  frame.setMaximumSize(new Dimension(width, height));
		  frame.setMinimumSize(new Dimension(width, height));
		  
		  //Very important to closing the game correctly
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.setResizable(false);
		  frame.setLocationRelativeTo(null);
		  frame.add(game);
		  frame.setVisible(true);
		  game.start();
	 }
	 
	 /**
	  * Add the game to the frame.
	  * Not currently used.
	  * @param game to add
	  */
	 public void addGame(Game game) {
		 frame.add(game);
	 }
	 
	 /**
	  * Remove the game from the frame.
	  * Not currently used.
	  * @param game to remove
	  */
	 public void removeGame(Game game) {
		 frame.remove(game);
	 }
}