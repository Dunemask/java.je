/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.SnakeControl.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * @author karib
 *
 */
public class SnakeControl {
	
	public static int currentDirection;
	public static final int UP=2;
	public static final int DOWN =4;
	public static final int LEFT =1;
	public static final int RIGHT =3;
	public static int squareSize=20;
	public static int snakeSize =5;
	public static JFrame frame;
	public static Board b;
	public static Snake s;
	//public static Food f;
	public static int boundsY = 45;
	public static int boundsX = 45;
	public static ArrayList<SnakeTurningPoint> turningPoints = new ArrayList<SnakeTurningPoint>() ;
	public static int lastHeadDirection;
	public static int score=0;
	public static boolean run;
	public static double speed=1;
	public static ArrayList<Food> food = new ArrayList<Food>();
	
}
