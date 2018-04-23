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
	ImageIcon grass = new ImageIcon("src/resources/bunkermaze/grass.png");
	ImageIcon square = new ImageIcon("src/resources/bunkermaze/square.png");
	Random rand = new Random();
	MazeGenerator mazgen = new MazeGenerator();
	int[][] maz = mazgen.mazeGen(24, 10000);
	JLabel[][] sqar = new JLabel[maz.length][maz[0].length];
	public BunkerMaze() {
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
		this.CreateMaze();
		
		int time=0;
		int win = 0;
		int alive =1;
		int deaths = 0;
		
		/////MAIN LOOOP////////////////////////
		while(win==0) {
			time++;
			//MOVE DINOS
			if(time % 5==0) {
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
				if(maz[((dino[i].getLocation().x+dx)/16)][(dino[i].getLocation().y/16)]==1) {
					dino[i].setLocation(dx+dino[i].getLocation().x,dino[i].getLocation().y);
					}
				if(maz[((dino[i].getLocation().x)/16)][((dino[i].getLocation().y+dy)/16)]==1) {
					dino[i].setLocation(dino[i].getLocation().x,dy+dino[i].getLocation().y);
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.requestFocus();			
			//Player Movement
			int pdx=0;
			int pdy=0;
			if(key.Output()[40]==1) {
				pdy=1;
			}
			if(key.Output()[38]==1) {
				pdy=-1;
			}
			if(key.Output()[37]==1) {
				pdx=-1;
			}
			if(key.Output()[39]==1) {
				pdx=+1;
			}
			if(maz[(x)][(y+pdy)]==1) {
				y+=pdy;
			}
			if(maz[(x+pdx)][(y)]==1) {
				x+=pdx;
			}
			player.setLocation(x*16,y*16);
			
			//WHEN YOU DIE
			if(alive ==0) {
				deaths++;
				x=1;
				y=1;
				SetMaze();
				for(int i = 0; i < dinonum; i++) {
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
				}
				alive=1;
			}
			
			//TEST IF  YOU WIN
			if(x==12&&y==12) {
				win=1;
			}
			
			
			
			
			//TEST IF YOU DIE
				for(int i = 0; i < dinonum; i++) {
					if(dino[i].getLocation().x-x*16==0&&dino[i].getLocation().y-y*16==0) {
						alive=0;
					}
				}
		}
		time=0;
		////////////END ANIMJIOTGRIOGHIOSJE////
		while(time<60) {
			time++;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < maz.length; i++) {
				for(int j = 0; j < maz[i].length; j++) {
					double mag = Math.sqrt(((12.5-i)*(12.5-i)+(12.5-j)*(12.5-j)));
					sqar[i][j].setLocation(i*16,j*16-(int) (time*mag));
				}
			}
			for(int i = 0; i < dinonum; i++) {
					dino[i].setLocation(dino[i].getLocation().x+3, dino[i].getLocation().y+3);
			}
			
		}
		this.dispose();
		System.out.println("You got to the bunker.");
		if (deaths==0) {
			System.out.println("You didn't die!!!");
		} else if(deaths==1){
			System.out.println("You only died once!");
		} else{
			System.out.println("You died "+deaths+" times." );
		}
		
	
	}
		private void SetMaze() {
			maz = mazgen.mazeGen(24, 10000);
			for(int i = 0; i < maz.length; i++) {
				for(int j = 0; j < maz[i].length; j++) {
					if(maz[i][j]==0) {
						//System.out.print("0");
						sqar[i][j].setIcon(square);
						}else {
						//System.out.print("1");
						sqar[i][j].setIcon(grass);
						}
					sqar[i][j].setSize(16, 16);
					sqar[i][j].setLocation(i*16,j*16);
				}
				//System.out.println("");
			}
			this.repaint();
			this.revalidate();
			sqar[12][12].setIcon(grass);
			maz[12][12]=1;
		}
		private void CreateMaze() {
			maz = mazgen.mazeGen(24, 10000);
			for(int i = 0; i < maz.length; i++) {
				for(int j = 0; j < maz[i].length; j++) {
					
					sqar[i][j] = new JLabel();
					if(maz[i][j]==0) {
						//System.out.print("0");
						sqar[i][j].setIcon(square);
						}else {
						//System.out.print("1");
						sqar[i][j].setIcon(grass);
						}
					sqar[i][j].setSize(16, 16);
					sqar[i][j].setLocation(i*16,j*16);
					this.add(sqar[i][j]);
				}
				//System.out.println("");
			}
			this.repaint();
			this.revalidate();
			sqar[12][12].setIcon(grass);
			maz[12][12]=1;
		}
}
