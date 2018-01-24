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

public class GameMain {
	//Use dunemask.fileutil.getresource it will ignore the bin and will also work in jar form
	//static ImageIcon image1 = new ImageIcon("bin/game/imgs/blok1.png");
	static int lengthf = new File("bin/game/imgs").listFiles().length;
	static ImageIcon images[] = new ImageIcon[lengthf];
	//static ImageIcon image1 = new ImageIcon(FileUtil.getResource("game/imgs/blok1.png").getPath());
	public static void main(String[] args) {
		for(int i = 0 ; i<lengthf;i++) {
			images[i] = new ImageIcon("bin/game/imgs/blok"+(i+1)+".png");
		}
		int choose = 0;
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
		
		TButon[] labs = new TButon[lengthf];
		for (int i=0; i<lengthf;i++) {
		labs[i] = new TButon(i,num);
		//labs[i].setText("blo "+ i);
		labs[i].setIcon(images[i]);
		labs[i].setSize(50,50);
		labs[i].setLocation(i*50, 10);
		tab1.add(labs[i]);
		}
		
		
		f.setTitle("GAME MAIN");
		f.setSize(1024,1024);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.add(tab1);
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(20,240,250));
		p.setSize(512,512);
		p.setLocation(256, 256);
		f.add(p);
		f.add(num);
		f.repaint();
		JPanel[][] AllSquares = new JPanel[32][32];
		int c = Integer.parseInt(num.getText());
		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				AllSquares[i][j] = Addsquare(i,j,p,c);
			}
		}
		f.repaint();
		JLabel sel = new JLabel(images[Integer.parseInt(num.getText())]);
		sel.setSize(16,16);
		p.add(sel);
		p.addMouseListener(new MouseListener() {
	        public void mousePressed(MouseEvent me) { }
	        public void mouseReleased(MouseEvent me) { }
	        public void mouseEntered(MouseEvent me) { }
	        public void mouseExited(MouseEvent me) { }
	        public void mouseClicked(MouseEvent me) { 
	        	//AllSquares[me.getX()/16][me.getY()/16] = Addsquare(me.getX()/16,me.getY()/16,p);
	        	//System.out.println(me.getX());
	        }
	    });
		p.addMouseMotionListener(new MouseInputAdapter() {
			public void mouseMoved(MouseEvent me){
				//System.out.println(me.getX());
				sel.setLocation((me.getX()/16)*16, (me.getY()/16)*16);
			}
			public void mouseDragged(MouseEvent me) {
				int c = Integer.parseInt(num.getText());
				AllSquares[me.getX()/16][me.getY()/16] = Addsquare(me.getX()/16,me.getY()/16,p,c);
				System.out.println(me.getX());
				f.repaint();
			}
		});
		/*Timer timer = new Timer(10,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choose = Integer.parseInt(num.getText());
			}
		});
		timer.start();
		*/
		while (1==1) {
			sel.setIcon(images[Integer.parseInt(num.getText())]);
		}
		
	}
	public static JPanel Addsquare(int x, int y,JPanel j,int choose){
		JPanel s  = new JPanel();
		s.setSize(16, 16);
		s.setLocation(x*16, y*16);
		s.setBackground(new Color(10,10,100));
		s.setLayout(null);
		JLabel lab=(null);
		lab =  new JLabel(images[choose]);
		lab.setSize(16,16);
		lab.setLocation(00, 0);
		s.add(lab);
		j.add(s);
		return s;
	}

}