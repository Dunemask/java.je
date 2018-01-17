package game;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import dunemask.util.FileUtil;

public class GameMain {
	//Use dunemask.fileutil.getresource it will ignore the bin and will also work in jar form
	//static ImageIcon image1 = new ImageIcon("bin/game/imgs/blok1.png");
	
	static ImageIcon image1 = new ImageIcon(FileUtil.getResource("game/imgs/blok1.png").getPath());
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("GAME MAIN");
		f.setSize(1024,1024);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(20,240,250));
		p.setSize(512,512);
		p.setLocation(256, 256);
		f.add(p);
		f.repaint();
		JPanel[][] AllSquares = new JPanel[32][32];
		for(int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				AllSquares[i][j] = Addsquare(i,j,p);
			}
		}
		f.repaint();
		JLabel sel = new JLabel(image1);
		sel.setSize(16,16);
		p.add(sel);
		p.addMouseListener(new MouseListener() {
	        public void mousePressed(MouseEvent me) { }
	        public void mouseReleased(MouseEvent me) { }
	        public void mouseEntered(MouseEvent me) { }
	        public void mouseExited(MouseEvent me) { }
	        public void mouseClicked(MouseEvent me) { 
	        	AllSquares[me.getX()/16][me.getY()/16] = Addsquare(me.getX()/16,me.getY()/16,p);
	        }
	    });
		p.addMouseMotionListener(new MouseInputAdapter() {
			public void mouseMoved(MouseEvent me){
				//System.out.println(me.getX());
				sel.setLocation((me.getX()/16)*16, (me.getY()/16)*16);
				
			}
		});
		
		
		
	}
	public static JPanel Addsquare(int x, int y,JPanel j){
		JPanel s  = new JPanel();
		s.setSize(16, 16);
		s.setLocation(x*16, y*16);
		s.setBackground(new Color(10,10,100));
		s.setLayout(null);
		JLabel lab =  new JLabel(image1);
		lab.setSize(16,16);
		lab.setLocation(00, 0);
		s.add(lab);
		j.add(s);
		return s;
	}

}
