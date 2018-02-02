/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.RunSnakeGame.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * @author karib
 *
 */
public class RunSnakeGame extends SnakeControl {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Snake Game");
		frame.setResizable(false);
		b = new Board();
		b.setBackground(Color.LIGHT_GRAY);
		frame.add(b);
		
		SnakeControl.s = new Snake(Color.GREEN,Color.pink,0,0);
		SnakeControl.f = new Food(Color.RED,9,9);
		while(true) {
		pushSnake();
			
		frame.repaint();
		frame.revalidate();
		
		}
		

	}

	/**
	 * 
	 */
	public static void pushSnake() {
		
		
	}

}
