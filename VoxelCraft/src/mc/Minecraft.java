/**
 * 
 */
package mc;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frames.MainBack;
import frames.Settings;
import minemain.VoxelCt;
import minerender.FilePanel;
import mplayer.SoundEngine;

/**
 * @author Dunemask
 *
 */
public class Minecraft {
	/**
	 * CF- Current Frame
	 * */
	public static JFrame cf;
	public static JPanel cp;
	public static Settings set;
	public static MainBack mb;
	public static FilePanel fp;
	public static VoxelCt vx;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		set = new Settings();
		mb = new MainBack();
		fp = new FilePanel();
		SoundEngine.handle("click");
		SoundEngine.start(SoundEngine.title);
		cf = new JFrame();
		cp= new JPanel(null);
		cf.setSize(600, 600);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		cp=mb;
		cf.setLocationRelativeTo(null);
		cf.add(cp);
		cp.repaint();
		cp.revalidate();
		cf.repaint();
		cf.revalidate();
		//goToSettings();

	}
	public static void loadWorld(VoxelCt vix)
	{
		vx = vix;
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		cp=vx;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
		
		
	}	
	public static void goToSelect() {
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		cp=fp;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
		
	}
	
	/** Settings panel call
	 * 
	 */
	public static void goToSettings() {
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		cp=set;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
	}
	/** Only Sets Visible size etc
	 * 
	 */
	private static void frameinit() {
		JFrame f = new JFrame();
		cf.setLocationRelativeTo(null);
		cf.setSize(600, 600);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		//cf.add(new JLabel("Tmp"));
		
		
	}
	
	/**
	 *  Main Menu Panel Call
	 */
	public static void goToMain() {
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cp=mb;
		cf.setContentPane(f.getContentPane());
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
	}

}
