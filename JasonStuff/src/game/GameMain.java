package game;

import java.awt.Color;
import java.awt.MouseInfo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import java.io.File;
import java.awt.event.*;

import dunemask.util.FileUtil;
import game.Board;

public class GameMain {
	//Use dunemask.fileutil.getresource it will ignore the bin and will also work in jar form
	//static ImageIcon image1 = new ImageIcon("bin/game/imgs/blok1.png");
	static int lengthf = new File("bin/game/imgs").listFiles().length;
	public static ImageIcon images[] = new ImageIcon[lengthf];
	//static ImageIcon image1 = new ImageIcon(FileUtil.getResource("game/imgs/blok"+(i+1)+".png").getPath());
	public static void main(String[] args) {
		//Get images
		for(int i = 0 ; i<lengthf;i++) {
			images[i] = new ImageIcon(FileUtil.getResource("game/imgs/blok"+(i+1)+".png").getPath());
		}
		int choose = 1;
		JFrame f = new JFrame();
		JLabel num = new JLabel("STUFF");
		num.setSize(100,100);
		num.setLocation(10,10);
		JPanel tab1 = new JPanel();
		tab1.setLayout(null);
		tab1.setBackground(new Color(200,200,200));
		tab1.setSize(1024,256);
		tab1.setLocation(0,768);
		num.setText("1");
		
		
		//Buttons
		TButon[] labs = new TButon[lengthf];
		for (int i=0; i<lengthf;i++) {
		labs[i] = new TButon(i+1,num);
		//labs[i].setText("blo "+ i);
		labs[i].setIcon(images[i]);
		labs[i].setSize(50,50);
		labs[i].setLocation(i*50+50, 10);
		tab1.add(labs[i]);
		}
		
		//Eraser
		TButon erase = new TButon(0,num);
		erase.setSize(50,50);
		erase.setLocation(0,10);
		tab1.add(erase);
		
		f.setTitle("GAME MAIN");
		f.setSize(1024,1024);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.add(tab1);
		
		Board p = new Board(f,num,images);
		Player play = new Player(p,32,32);
		
		f.add(p);
		f.add(num);
		f.repaint();
		f.repaint();
		JLabel sel = new JLabel(images[Integer.parseInt(num.getText())]);
		sel.setSize(16,16);
		p.add(sel);
		
		
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timer timer = new Timer(25,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				play.update();
			}
		});
		timer.start();
		
		while (1==1) {
			int ca= Integer.parseInt(num.getText())-1;
			if (ca>=0) {
			sel.setIcon(images[ca]);
			//play.update();
			}else {
			sel.setIcon(null);
			}
			sel.setLocation(p.getMouse());
			p.requestFocus();
		}
	}
}