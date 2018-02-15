/**
 * 
 */
package jason;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author Jason Roberts
 *
 */
public class FlappyPterodactyl extends JFrame {
	public FlappyPterodactyl() throws InterruptedException {
	//gets images and sets variables
	ImageIcon image = new ImageIcon("src/jason/flappyptero/pterodactyl.gif");
	ImageIcon bakground = new ImageIcon("src/jason/flappyptero/bg.png");
	ImageIcon tree = new ImageIcon("src/jason/flappyptero/tree.png");
	ImageIcon bad = new ImageIcon("src/jason/flappyptero/bad.png");
	int y = 20;
	int yvel = 5;
	y-=yvel;
	int x = 0;
	
	//Sets up frame
	this.setTitle("FLappy PteroDacTyl");
	this.setSize(400,400);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setLayout(null);
	
	//Creates PTerodactyl Panel
	JPanel ptero = new JPanel();
	ptero.setSize(64, 64);
	ptero.setLocation(16, 16);
	ptero.setLayout(null);
	ptero.setBackground(null);
	
	//Creates PTERODACTYL Icon
	JLabel lab =  new JLabel(image);
	lab.setSize(64,64);
	lab.setLocation(0, 0);
	lab.setOpaque(false);
	ptero.add(lab);
	ptero.setOpaque(false);
	//Adds the keylistener
	KeyList key = new KeyList();
	ptero.addKeyListener(key);
	ptero.setFocusable(true);
	ptero.requestFocus();
	//Creates Trees
	JLabel[] trees = new JLabel[5];
	int dy = 0;
	for (int i = 0 ; i < trees.length; i++) {
		trees[i] = new JLabel(tree);
		trees[i].setSize(64, 300);
		this.add(trees[i]);
		trees[i].setLocation(i*150, 200+dy);
		dy+=(int)(Math.random()*100-50);
		if(dy<-100) {
			dy=-100;
		}
		if(dy>100) {
			dy=100;
		}
	}
	
	this.add(ptero);
	//Background
	JLabel l2 = new JLabel(bakground);
	l2.setSize(400,400);
	l2.setLocation(0, 0);
	this.add(l2);
	
	//click is to detect button presses
	int click = 0;
	int alive = 1;
	//Main loop
	while(true) {
		//System.out.println(y-trees[1].getLocation().y);
		//System.out.println("Y:" +y);
		y-=yvel;
		yvel-=1;
		ptero.setLocation(16, y);
		if(alive ==1) {
			x+=1;
		if ((key.Output()[38]==0&&key.Output()[32]==0)&&(click==1)) {
			click=0;
		}
		if ((key.Output()[38]==1||key.Output()[32]==1)&&(click==0)) {
			yvel=10;
			click=1;
		}
		}
		this.repaint();
		Thread.sleep(30);
		//Everytime it goes to a switch
		if ((4*x)%160<4) {
			System.out.print("FFFFFFFLLLLLLLLLIIIIIIIIIPPPPPPPPPPPP DA SWITCH");
			for (int i = 0 ; i < trees.length-1; i++) {
				trees[i].setLocation(0, trees[i+1].getLocation().y);
			}
			dy=trees[trees.length-1].getLocation().y-200;
			dy+=(int)(Math.random()*100-50);
			if(dy<-100) {
				dy=-100;
			}
			if(dy>100) {
				dy=100;
			}
			trees[trees.length-1].setLocation(0,200+dy);
		}
		for (int i = 0 ; i < trees.length; i++) {
			trees[i].setLocation(i*160 -(4*x)%160, trees[i].getLocation().y);
		}
		if(y+50>trees[1].getLocation().y) {
			alive=0;
			
		}
	}
	
	
	
	}
}
