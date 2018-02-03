/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: game.Board.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


/**
 * @author karib
 *
 */
public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2558736634517421540L;

	/**
	 * 
	 */
	public Board() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int sq = SnakeControl.squareSize;
		ArrayList<SnakeSquare> snake = SnakeControl.s.getSquares();
		ArrayList<SnakeTurningPoint> turningPoints = SnakeControl.turningPoints;
		SnakeHeadSquare head = SnakeControl.s.getHead();
		//Food
		for(int i=0;i<SnakeControl.food.size();i++) {
			g.setColor(SnakeControl.food.get(i).getColor());
			g.fillRect(sq*SnakeControl.food.get(i).getX(), sq*SnakeControl.food.get(i).getY(), sq, sq);
		}
		//body
		for(int i=0;i<snake.size();i++) {
			g.setColor(SnakeControl.s.getColor());
			g.fillRect(sq*snake.get(i).getX(), sq*snake.get(i).getY(),sq, sq);
			
		}
		//TURNING POINTS
		for(int i=0;i<turningPoints.size();i++) {
			SnakeTurningPoint p = turningPoints.get(i);
			if(p.getColor()!=null) {
				g.setColor(p.getColor());
				g.fillRect(p.getX()*sq, p.getY()*sq, sq, sq);
			}
			
		}
		//Head
		g.setColor(head.getColor());
		g.fillRect(head.getX()*sq, head.getY()*sq, sq, sq);
		
		

		//g.fillRect(sq*SnakeControl.f.getX(), sq*SnakeControl.f.getY(), sq, sq);
		
		//Scores
		g.setColor(Color.YELLOW);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
		g.drawString("Score:"+SnakeControl.score+"      Snake Size:"+(SnakeControl.s.getSquares().size()), 10, 15);
		
		
	}

}
