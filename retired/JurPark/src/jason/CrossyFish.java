package jason;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CrossyFish extends JFrame {

	public CrossyFish() throws HeadlessException {
		ImageIcon image = new ImageIcon("src/resources/crossyfish/diver.gif");
		ImageIcon bakground = new ImageIcon("src/resources/crossyfish/bg.png");
		ImageIcon fish = new ImageIcon("src/resources/crossyfish/fish.gif");
		ImageIcon lfish = new ImageIcon("src/resources/crossyfish/fishl.gif");
		double time =0;
		//set up main frame
		this.setTitle("Cr0ssY Fi5H");
		this.setSize(400,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		int x = 200;
		int y = 120;
		KeyList key = new KeyList();
		this.addKeyListener(key);
		this.setFocusable(true);
		this.requestFocus();
		
		//Creates Diver Icon
		JLabel lab =  new JLabel(image);
		lab.setSize(64,64);
		lab.setLocation(x-32, y-32);
		lab.setOpaque(false);
		this.add(lab);
		
		//Creates time label
		JLabel tim = new JLabel();
		tim.setSize(64,32);
		tim.setLocation(0, 0);
		tim.setText("TIME");
		this.add(tim);
		
		//Creates Fishes
				JLabel[] fishes =  new JLabel[5];
				for (int i = 0 ; i < fishes.length; i++) {
					fishes[i] = new JLabel(fish);
					fishes[i].setSize(64, 64);
					this.add(fishes[i]);
					fishes[i].setLocation(i*50-32, 400-i*32-80-16);
				}
				JLabel[] lfishes =  new JLabel[5];
				for (int i = 0 ; i < lfishes.length; i++) {
					lfishes[i] = new JLabel(lfish);
					lfishes[i].setSize(64, 64);
					this.add(lfishes[i]);
					lfishes[i].setLocation(400-i*50-32, 400-i*32-32-80);
				}
		
		//background
		JLabel l2 = new JLabel(bakground);
		l2.setSize(400,400);
		l2.setLocation(0, 0);
		this.add(l2);
		
		
		//THE MAIN LOOP
		while (y<400) {
			//MOVEMENTS
			time+=30.0/1000.0;
			tim.setText("Time:"+time);
			if(key.Output()[40]==1) {
				y+=3;
			}
			if(key.Output()[39]==1) {
				x-=3;
			}
			if(key.Output()[37]==1) {
				x+=3;
			}
			y-=1;
			if(y<120) {
				y=120;
			}
			if(x>300) {
				x=300;
			}
			if(x<100) {
				x=100;
			}
			
			//Update pos
			lab.setLocation(x-32, y-32);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Move Fishies
			for (int i = 0 ; i < lfishes.length; i++) {
				lfishes[i].setLocation((lfishes[i].getLocation().x-(5-i*1)-5-400)%((int) (Math.random()*400)+800)+400, lfishes[i].getLocation().y);
				if ((lfishes[i].getLocation().x+32-x)*(lfishes[i].getLocation().x+32-x)+(lfishes[i].getLocation().y+32-y)*(lfishes[i].getLocation().y+32-y)<32*32) {
					y-=29;
					x-=(5-i*1)+5;
				}
				
			}
			for (int i = 0 ; i < fishes.length; i++) {
				fishes[i].setLocation((fishes[i].getLocation().x+5+(5-i*1)+400)%((int) (Math.random()*400)+800)-400, fishes[i].getLocation().y);
				if ((fishes[i].getLocation().x+32-x)*(fishes[i].getLocation().x+32-x)+(fishes[i].getLocation().y+32-y)*(fishes[i].getLocation().y+32-y)<32*32) {
					y-=29;
					x+=(5-i*1)+5;
				}
			}
			
			
		}
		this.dispose();
		System.out.println("You finally swam to the bottom. \nYou held your breath for " +Math.round(time)+" seconds!");
	}
}
