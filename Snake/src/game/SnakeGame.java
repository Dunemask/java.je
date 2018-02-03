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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author karib
 *
 */
public class SnakeGame extends SnakeControl {

	public SnakeGame() {
		frame = new JFrame();
		frame.setSize(boundsX*squareSize, boundsY*squareSize+32);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Snake Game");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent evt) {
			    switch( evt.getKeyCode() ) { 
		        case KeyEvent.VK_UP:
		        	currentDirection = UP; break;
		        case KeyEvent.VK_DOWN:
		        	currentDirection = DOWN; break;
		        case KeyEvent.VK_LEFT:
		        	 currentDirection = LEFT; break;      
		        case KeyEvent.VK_RIGHT :
		        	 currentDirection = RIGHT; break;
			    }
				
			}

			@Override
			public void keyReleased(KeyEvent evt) {
				
				
			}

			@Override
			public void keyTyped(KeyEvent evt) {
				switch(evt.getKeyChar()) {
				case 'w': currentDirection = UP; break;
				case 'd': currentDirection = RIGHT; break;
				case 's': currentDirection = DOWN; break;
				case 'a': currentDirection = LEFT; break;
				}	   



			}
			
		});
		b = new Board();
		b.setBackground(Color.LIGHT_GRAY);
		frame.add(b);
		
		food.add(new Food(Color.RED));
		SnakeControl.currentDirection = RIGHT;
		SnakeControl.s = new Snake(Color.GREEN,Color.pink,0,0,currentDirection);
		run = true;
		injectable();
			
		
		//Dimension dim = frame.getSize();
		while(run) {
		/*if(dim!=frame.getSize()) {
			dim = frame.getSize();
			boundsX = (int) (((dim.getWidth() + 99) / 100 ) * 100)/squareSize;
			boundsY = (int) (((dim.getHeight() + 99) / 100 ) * 100)/squareSize;
			dim.setSize(boundsX*squareSize, boundsY*squareSize);
		}*/
			
		pushHead();
		pushSquares();
		//Also Updates all turning points
		//testCollision();
		testFood();
		
		
		try {
			Thread.sleep((long) (100/speed));
			//System.out.println(speed+"||"+(100/speed));
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		frame.repaint();
		frame.revalidate();
		
		}
	}
	
	private static void injectable() {
		if(score<-1) {
		int foodCount= 17;
		for(int i=0;i<foodCount;i++) {
			food.add(new Food(Color.RED));
		}
		}
	}
	
	/**
	 * 
	 */
	private static void testFood() {
		injectable();
		//If head at food
		for(int i=0;i<food.size();i++) {
			Food f = food.get(i);
			if(s.getHead().getX()==f.getX()&&s.getHead().getY()==f.getY()) {
				score++;
				speed+=.025;
				SnakeSquare lastSquare = s.getSquares().get(0);
				//s.getSquares().get(s.getSquares().size()-1).setX(20);
				int x = lastSquare.getX();
			    int y=lastSquare.getY();
			    //System.out.println(x+","+y);
			    //throw new RuntimeException("null");/*
				switch(lastSquare.getLastDirection()) {
				case UP:
					y+=1;
				break;
				case DOWN:
					y-=1;
				break;
				case RIGHT:
					x-=1;
				break;
				case LEFT:
					x+=1;
				break;
				}
				SnakeSquare nsq = new SnakeSquare(x,y);
				nsq.setLastDirection(lastSquare.getLastDirection());
				s.getSquares().add(0,nsq);
				food.remove(i);
				food.add(i, new Food(Color.RED));
				
			}
		}
		
	}

	/**
	 * 
	 */
	private static void testCollision() {
		//For each sqaure
		for(int i=0;i<s.getSquares().size();i++) {
			SnakeSquare snakeSquare = s.getSquares().get(i);
			//if square overlapping
			for(int c=0;c<s.getSquares().size();c++) {
				if(s.getSquares().get(c)!=snakeSquare) {
					SnakeSquare compare = s.getSquares().get(c);
					if(snakeSquare.getX()==compare.getX()&&snakeSquare.getY()==compare.getY()) {
						collision();
					}
			}
			if(snakeSquare.getX()==s.getHead().getX()&&snakeSquare.getY()==s.getHead().getY()) {
				collision();
			}	
		
		}
	}
		
		for(int i=0;i<turningPoints.size();i++) {
			SnakeTurningPoint tp = turningPoints.get(i);
			boolean keep = false;
			for(int c=0;c<s.getSquares().size();c++) {
				SnakeSquare snakeSquare = s.getSquares().get(c);
				//If snakesquare at turningpoint
				if(snakeSquare.getX()==tp.getX()&&snakeSquare.getY()==tp.getY()) {
						keep = true;
				}
				
			}
			if(!keep) {
				turningPoints.remove(i);
			}
		}
		
	}

	/**
	 * 
	 */
	private static void collision() {
		JOptionPane.showMessageDialog(null, "You lose :(");
		run = false;
		frame.dispose();
		score = 0;
		speed=1;
		for(int i=food.size()-1;i>=0;i--) {
			food.remove(i);
		}
		new SnakeGame();
	}

	/**
	 * 
	 */
	private static void pushSquares() {
		int cx,cy;
		for(int i= 0;i<s.getSquares().size();i++) {
			cx = s.getSquares().get(i).getX();
			cy = s.getSquares().get(i).getY();
			for(int c=0;c<turningPoints.size();c++) {
				//If at turning point
				if(cx==turningPoints.get(c).getX()&&cy==turningPoints.get(c).getY()) {
					s.getSquares().get(i).setLastDirection(turningPoints.get(c).getDirection());
				}
			}
			
			
			switch(s.getSquares().get(i).getLastDirection()) {
			case UP: 
				s.getSquares().get(i).setY(s.getSquares().get(i).getY()-1);		
				if(s.getSquares().get(i).getY()<0) {
					s.getSquares().get(i).setY(boundsY-1);
				}
				break;
			case DOWN: 
				s.getSquares().get(i).setY(s.getSquares().get(i).getY()+1);		
				if(s.getSquares().get(i).getY()>boundsY-1) {
					s.getSquares().get(i).setY(0);
				}
				break;
			case LEFT: 
				s.getSquares().get(i).setX(s.getSquares().get(i).getX()-1);		
				if(s.getSquares().get(i).getX()<0) {
				s.getSquares().get(i).setX(boundsX-1);
				}
				
				break;
			case RIGHT: 
				s.getSquares().get(i).setX(s.getSquares().get(i).getX()+1);		
				if(s.getSquares().get(i).getX()>boundsX-1) {
				s.getSquares().get(i).setX(0);
				}
				break;
			
			}
			
			
			
		}
		
	}

	/**
	 * 
	 */
	public static void pushHead() {
	
		//String operation;
		switch(SnakeControl.currentDirection) {
		case UP: //operation = "Y-"; 
			if(s.getHead().getLastDirection()!=DOWN) {
				if(s.getHead().getLastDirection()!=SnakeControl.currentDirection) {
					turningPoints.add(new SnakeTurningPoint(
							s.getHead().getX(),
							s.getHead().getY(),SnakeControl.currentDirection
							));
				}
				//Update Point
				s.getHead().setY(s.getHead().getY()-1);		
				if(s.getHead().getY()<0) {
					s.getHead().setY(boundsY-1);
				}
				s.getHead().setLastDirection(UP);
			}else {
				//Update Point
				s.getHead().setY(s.getHead().getY()+1);		
				if(s.getHead().getY()>SnakeControl.boundsX-1) {
					s.getHead().setY(0);
				}
			}
		break;
		case DOWN: //operation = "Y+"; 
			if(s.getHead().getLastDirection()!=UP) {
				if(s.getHead().getLastDirection()!=SnakeControl.currentDirection) {
					turningPoints.add(new SnakeTurningPoint(
							s.getHead().getX(),
							s.getHead().getY(),SnakeControl.currentDirection
							));
				}
				//Update Point
				s.getHead().setY(s.getHead().getY()+1);		
				if(s.getHead().getY()>SnakeControl.boundsX-1) {
					s.getHead().setY(0);
				}
				s.getHead().setLastDirection(DOWN);
			}else {
				//Update Point
				s.getHead().setY(s.getHead().getY()-1);		
				if(s.getHead().getY()<0) {
					s.getHead().setY(boundsY-1);
				}
			}
		break;
		case RIGHT://operation = "X+"; 
			if(s.getHead().getLastDirection()!=LEFT) {
				
				if(s.getHead().getLastDirection()!=SnakeControl.currentDirection) {
					turningPoints.add(new SnakeTurningPoint(
							s.getHead().getX(),
							s.getHead().getY(),SnakeControl.currentDirection
							));
				}
				//Update Point
				s.getHead().setX(s.getHead().getX()+1);		
				if(s.getHead().getX()>SnakeControl.boundsX-1) {
					s.getHead().setX(0);
				}
				s.getHead().setLastDirection(RIGHT);
			}else {
				s.getHead().setX(s.getHead().getX()-1);		
				if(s.getHead().getX()<0) {
					s.getHead().setX(boundsX-1);
				}
			}
		break;
		case LEFT: //operation = "X-"; 
			if(s.getHead().getLastDirection()!=RIGHT) {
				
				if(s.getHead().getLastDirection()!=SnakeControl.currentDirection) {
					turningPoints.add(new SnakeTurningPoint(
							s.getHead().getX(),
							s.getHead().getY(),SnakeControl.currentDirection
							));
				}
				//Update Point
				s.getHead().setX(s.getHead().getX()-1);		
				if(s.getHead().getX()<0) {
					s.getHead().setX(boundsX-1);
				}
				s.getHead().setLastDirection(LEFT);
			}else {
				//Update Point
				s.getHead().setX(s.getHead().getX()+1);		
				if(s.getHead().getX()>SnakeControl.boundsX-1) {
					s.getHead().setX(0);
				}
			}
			
		break;
		default: throw new RuntimeException("NO CURRENT DIRECTION FOUND!"); 
		}
		

		
		
		
	}


	
	
	
	
	
	
	

}
