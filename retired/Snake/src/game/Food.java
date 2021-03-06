/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.Food.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

import java.awt.Color;
import java.util.Random;

/**
 * @author karib
 *
 */
public class Food {

	private Color color;
	private int x;
	private int y;

	
	
	public Food(Color color) {
		this.color = color;
		Random r = new Random();
		this.x = r.nextInt(SnakeControl.boundsX-1)+1;
		this.y = r.nextInt(SnakeControl.boundsY-1)+1;
		
	}
	
	
	/**
	 * 
	 */
	public Food(Color color,int x,int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
