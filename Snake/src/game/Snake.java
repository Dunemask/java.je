/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.Snake.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author karib
 *
 */
public class Snake {

	private Color color;
	private ArrayList<SnakeSquare> squares = new ArrayList<SnakeSquare>();

	/**
	 * 
	 */
	public Snake(Color color,int startX,int startY) {
		this.setColor(color);
		for(int i=0;i<=SnakeControl.snakeSize;i++) {
			squares.add(new SnakeSquare(startX+i,startY));
		}
		
		
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
	 * @return the squares
	 */
	public ArrayList<SnakeSquare> getSquares() {
		return squares;
	}


	/**
	 * @param squares the squares to set
	 */
	public void setSquares(ArrayList<SnakeSquare> squares) {
		this.squares = squares;
	}

}
