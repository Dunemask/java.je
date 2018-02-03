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
	private SnakeHeadSquare head;

	/**
	 * @param
	 * 
	 */
	public Snake(Color color,Color headColor, int startX,int startY, int startingDirection) {
		this.setColor(color);
		//Odd
		/**/for(int i=SnakeControl.snakeSize;i>0;i--) {
			squares.add(new SnakeSquare(SnakeControl.snakeSize-i,startY));
		}/**/
		
		for(int i=0;i<SnakeControl.snakeSize;i++) {
			//squares.add(new SnakeSquare(startX+i,startY));
			squares.get(i).setLastDirection(startingDirection);
		}
		this.setHead(new SnakeHeadSquare(headColor,SnakeControl.snakeSize,startY));
		
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


	/**
	 * @return the head
	 */
	public SnakeHeadSquare getHead() {
		return head;
	}


	/**
	 * @param head the head to set
	 */
	public void setHead(SnakeHeadSquare head) {
		this.head = head;
	}

	
	
}

class SnakeHeadSquare {

	private Color color;
	private int x;
	private int y;
	private int lastDirection;

	/**
	 * 
	 */
	public SnakeHeadSquare(Color color,int x, int y) {
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

	/**
	 * @return the lastDirection
	 */
	public int getLastDirection() {
		return lastDirection;
	}

	/**
	 * @param lastDirection the lastDirection to set
	 */
	public void setLastDirection(int lastDirection) {
		this.lastDirection = lastDirection;
	}

}

class SnakeSquare {

	private int x;
	private int y;
	private int lastDirection;

	/**
	 * 
	 */
	public SnakeSquare(int x, int y) {
		this.x = x;
		this.y = y;
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


	/**
	 * @return the lastDirection
	 */
	public int getLastDirection() {
		return lastDirection;
	}


	/**
	 * @param lastDirection the lastDirection to set
	 */
	public void setLastDirection(int lastDirection) {
		this.lastDirection = lastDirection;
	}

}

class SnakeTurningPoint {

	
	
	
	private int direction;
	private int y;
	private int x;
	private Color color;

	/**
	 * 
	 */
	public SnakeTurningPoint(int x, int y, int direction) {
		this.color = null;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	/**
	 * 
	 */
	public SnakeTurningPoint(Color color, int x, int y, int direction) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
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

}

