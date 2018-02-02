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
		SnakeControl.currentDirection = RIGHT;
		while(true) {
		pushSnake();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		frame.repaint();
		frame.revalidate();
		
		}
		

	}

	/**
	 * 
	 */
	public static void pushSnake() {
		String operation = "";
		switch(SnakeControl.currentDirection) {
		case UP: operation = "Y-"; break;
		case DOWN: operation = "Y+"; break;
		case RIGHT:operation = "X+"; break;
		case LEFT: operation = "X-"; break;
		default: throw new RuntimeException("NO CURRENT DIRECTION FOUND!"); 
		}
		
		changeLocation(operation);
		
		
	}

	/**
	 * @param operation
	 */
	private static void changeLocation(String operation) {
		SnakeHeadSquare head;
		switch(operation) {
		case "Y+":
			for(int i=0;i<s.getSquares().size();i++) {
				SnakeSquare cs = s.getSquares().get(i);
				s.getSquares().get(i).setY(cs.getY()+1);
				cs = s.getSquares().get(i);
				if(cs.getY()*squareSize>frame.getHeight()) {
					s.getSquares().get(i).setY(0);
				}
			}
			head = s.getHead();
			s.getHead().setY(head.getY()+1);
			head = s.getHead();
			if(head.getY()*squareSize>frame.getHeight()) {
				s.getHead().setY(0);
			}

			break;
		case "Y-":
			
			
			
			
			
			break;
		case "X+":
			
			for(int i=0;i<s.getSquares().size();i++) {
				SnakeSquare cs = s.getSquares().get(i);
				s.getSquares().get(i).setX(cs.getX()+1);
				cs = s.getSquares().get(i);
				if(cs.getX()*squareSize>frame.getWidth()) {
					s.getSquares().get(i).setX(0);
				}
			}
			head = s.getHead();
			s.getHead().setX(head.getX()+1);
			head = s.getHead();
			if(head.getX()*squareSize>frame.getWidth()) {
				s.getHead().setX(0);
			}
			
			
			
			break;
		case "X-":
			
			
			
			
			
			break;
		
		
		
		
		
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
