/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.SnakeSquare.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

/**
 * @author karib
 *
 */
public class SnakeSquare {

	private Object x;
	private int y;

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
	public Object getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Object x) {
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
