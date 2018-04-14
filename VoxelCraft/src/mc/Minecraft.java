/**
 * 
 */
package mc;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
	public static int renderVal=130;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		set = new Settings();
		mb = new MainBack();
		fp = new FilePanel();
		SoundEngine.handle("block_place");
		SoundEngine.start(SoundEngine.title);
		cf = new JFrame();
		cp= new JPanel(null);
		cf.setSize(600, 600);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Minecraft - Java.JE");
		cf.setVisible(true);
		listenHandle();
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
		SoundEngine.stop(SoundEngine.title);
		SoundEngine.start(SoundEngine.game);
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
		listenHandle();
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
		listenHandle();
		cp= new FilePanel();
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
		listenHandle();
		cp=set;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
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
		listenHandle();
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
	}
	/**
	 * 
	 */
	private static void listenHandle() {
		cf.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
	}

}
