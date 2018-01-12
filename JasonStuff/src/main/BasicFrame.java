package main;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicFrame extends JFrame{
	Popup pop;
	int xpos;
	int ypos;
	int num = 0;
	JPanel jil;
	BasicFrame dra;
	boolean dead = true;
	public BasicFrame(int x,int y,Popup z) throws HeadlessException {
		xpos = x;
		ypos = y;
		pop = z;
		this.setTitle("YOU WON");
		this.setSize(300,300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLocation(x, y);
		jil = new JPanel();
		jil.setLayout(null);		
		this.add(jil);
		repaint();
		revalidate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dead = false;
				dra = pop.Createnewframe(xpos+30, ypos-30);
			}
		});
	}
	public void Increm() {
		num+=1;
		jil.setBackground(new Color(num % 256, 5*num % 256, 100));
		
		//dra.Increm();
	}
}
