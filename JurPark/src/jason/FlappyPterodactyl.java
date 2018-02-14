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
	ImageIcon image = new ImageIcon("src/jason/pterodactyl.gif");
	ImageIcon bakground = new ImageIcon("src/jason/bg.png");
	int y = 20;
	int yvel = 5;
	y-=yvel;
	
	
	this.setTitle("FLappy PteroDacTyl");
	this.setSize(400,400);
	this.setResizable(true);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setLayout(null);
	
	
	JPanel ptero = new JPanel();
	ptero.setSize(64, 64);
	ptero.setLocation(16, 16);
	ptero.setLayout(null);
	ptero.setBackground(null);
	JLabel lab =  new JLabel(image);
	lab.setSize(64,64);
	lab.setLocation(0, 0);
	ptero.add(lab);
	
	this.add(ptero);
	
	JLabel l2 = new JLabel(bakground);
	l2.setSize(400,400);
	l2.setLocation(0, 0);
	this.add(l2);
	
	while(true) {
		
	}
	
	
	
	}
}
