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
	public FlappyPterodactyl() {
	//gets images and sets variables
	ImageIcon image = new ImageIcon("src/resources/flappyptero/pterodactyl.gif");
	ImageIcon bakground = new ImageIcon("src/resources/flappyptero/bg.png");
	ImageIcon tree = new ImageIcon("src/resources/flappyptero/tree.png");
	ImageIcon bad = new ImageIcon("src/resources/flappyptero/bad.gif");
	int y = 80;
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
	this.setEnabled(true);
	this.setAlwaysOnTop(true);
	
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
	JLabel[] pteros = new JLabel[5];
	int dpy = 0;
	for (int i = 0 ; i < pteros.length; i++) {
		pteros[i] = new JLabel(bad);
		pteros[i].setSize(64, 64);
		this.add(pteros[i]);
		pteros[i].setLocation(i*150, 0);
		dpy+=(int)(Math.random()*100-50);
		if(dpy<-100) {
			dpy=-100;
		}
		if(dpy>100) {
			dpy=100;
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
	int ending = 0;
	//Main loop
	while(y<1000) {
		ptero.requestFocus();
		//System.out.println(y-trees[1].getLocation().y);
		//System.out.println("Y:" +y);
		y-=yvel;
		yvel-=1;
		ptero.setLocation(ptero.getX(), y);
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
		//System.out.println();
		this.repaint();
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Everytime it goes to a switch
		if ((4*x)%160<4) {
			//System.out.println("FFFFFFFLLLLLLLLLIIIIIIIIIPPPPPPPPPPPP DA SWITCH");
			elijah.Story.score += 1;
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
		if ((5*x)%170<5) {
			//System.out.println("FFFFFFFLLLLLLLLLIIIIIIIIIPPPPPPPPPPPP DA SWITCH");
			elijah.Story.score += 1;
			for (int i = 0 ; i < pteros.length-1; i++) {
				pteros[i].setLocation(0, pteros[i+1].getLocation().y);
			}
			dy=pteros[pteros.length-1].getLocation().y-50;
			dy+=(int)(Math.random()*100-50);
			if(dy<-100) {
				dy=-100;
			}
			
			if(dy>100) {
				dy=100;
			}
			pteros[pteros.length-1].setLocation(0,50+dy);
		}
		for (int i = 0 ; i < trees.length; i++) {
			trees[i].setLocation(i*160 -(4*x)%160, trees[i].getLocation().y);
		}
		for (int i = 0 ; i < pteros.length; i++) {
			pteros[i].setLocation(i*170 -(5*x)%170, pteros[i].getLocation().y);
		}
		if(alive ==1){
		//When you hit a tree
		if(y+50>trees[1].getLocation().y) {
			ending = 1;
			alive=0;
		}
		//When you hit a pterodactyl
				if(y+10>pteros[1].getLocation().y&&y-10<pteros[1].getLocation().y) {
					ending = 3;
					alive=0;
				}
		//When you go past 200
		if(x>2000) {
			alive = 0;
			elijah.Story.score += 20;
			ending = 2;
		}
		
		}
	}
	this.dispose();
	if (ending ==1) {
	System.out.println("The Pterodactyl flung your helpless body into a tree.");
	}
	if (ending ==3) {
		System.out.println("The Pterodactyls fought over your corpse.");
		}
	if (ending ==2) {
	System.out.println("The Pterodactyl got too tired and feel on your body.");
	}
	}
}
