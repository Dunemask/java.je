/**
 * 
 */
package jason;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;
/**
 * @author Jason Roberts
 *
 */
public class BunkerMaze extends JFrame{

	Random rand = new Random();

	public BunkerMaze() {
		MazeGenerator mazgen = new MazeGenerator();
		int[][] maz = mazgen.mazeGen(24, 10000);
		ImageIcon grass = new ImageIcon("src/resources/bunkermaze/grass.png");
		ImageIcon square = new ImageIcon("src/resources/bunkermaze/square.png");
		ImageIcon bnker = new ImageIcon("src/resources/bunkermaze/bunker.png");
		ImageIcon dinogif = new ImageIcon("src/resources/bunkermaze/dino.gif");
		ImageIcon playimg = new ImageIcon("src/resources/bunkermaze/play.gif");

		int dinonum = 6;
		//Set Up da main frame
		this.setTitle("Mazey Bunker");
		this.setSize(408,420);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		int x = 1;
		int y = 1;
		KeyList key = new KeyList();
		this.addKeyListener(key);
		this.setFocusable(true);
		this.requestFocus();
		
		JLabel[][] sqar = new JLabel[maz.length][maz[0].length];
		//Create Bunker
		JLabel bunker =  new JLabel(bnker);
		bunker.setSize(16,16);
		bunker.setLocation(200-8,200-8);
		this.add(bunker);
		
		//Create Player
		JLabel player = new JLabel(playimg);
		player.setSize(16,16);
		player.setLocation(16,16);
		this.add(player);
		
		//Create Dino
		JLabel[] dino = new JLabel[dinonum];
		for(int i = 0; i < dinonum; i++) {
			
			dino[i] = new JLabel(dinogif);
			
			dino[i].setSize(16, 16);
			int xa=0; 
			int ya=0;
			boolean loop = true;
			while(loop) {
				xa=(rand.nextInt(25));
				ya=(rand.nextInt(25));
				if(maz[xa][ya]==1) {
					loop=false;
				}
			}
			dino[i].setLocation(xa*16,ya*16);
			this.add(dino[i]);
		}
		
		
		//Create Maze
		for(int i = 0; i < maz.length; i++) {
			for(int j = 0; j < maz[i].length; j++) {
				
				sqar[i][j] = new JLabel();
				if(maz[i][j]==0) {
					System.out.print("0");
					sqar[i][j].setIcon(square);
					}else {
					System.out.print("1");
					sqar[i][j].setIcon(grass);
					}
				sqar[i][j].setSize(16, 16);
				sqar[i][j].setLocation(i*16,j*16);
				this.add(sqar[i][j]);
			}
			System.out.println("");
		}
		this.repaint();
		this.revalidate();
		sqar[12][12].setIcon(grass);
		maz[12][12]=1;
		
		
		while(true) {
			//MOVE DINOS
			for(int i = 0; i < dinonum; i++) {
				
				int dx=0;
				int dy=0;
				
				if(dino[i].getLocation().x-x*16<0) {
					dx=16;
				}
				if(dino[i].getLocation().x-x*16>0) {
					dx=-16;
				}
				if(dino[i].getLocation().y-y*16<0) {
					dy=16;
				}
				if(dino[i].getLocation().y-y*16>0) {
					dy=-16;
				}
				dino[i].setLocation(dx+dino[i].getLocation().x,dy+dino[i].getLocation().y);
			}
			
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while(key.Output()[40]==0) {
				this.requestFocus();
				
			}
			
			
			
			
		}
	
	}

}
