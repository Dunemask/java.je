/**
 * 
 */
package mc;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frames.MainBack;
import frames.QuickMenu;
import frames.QuickSettings;
import frames.Settings;
import minerender.FilePanel;
import minerender.VoxEn;
import minerender.VoxelCt;
import mplayer.PlaySound;
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
	public static QuickMenu qm;
	public static MainBack mb;
	public static QuickSettings qs;
	public static FilePanel fp;
	public static VoxelCt vx;
	public static int renderVal=130;
	public static String currentWorldName;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceHandler.init();
		set = new Settings();
		mb = new MainBack();
		fp = new FilePanel();
		qs = new QuickSettings();
		SoundEngine.handle("click");
		SoundEngine.start(SoundEngine.title);
		cf = new JFrame();
		cp= new JPanel(null);
		cf.setSize(600, 600);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Voxelcraft - Java.JE");
		cf.setVisible(true);
		listenHandle();
		cp=mb;
		cf.setLocationRelativeTo(null);
		cf.add(cp);
		String urlPath = "/resources/textures/gui/title/Voxelcraft.png";
		cf.setIconImage(new ImageIcon(Minecraft.class.getResource(urlPath)).getImage());
		cp.repaint();
		cp.revalidate();
		cf.repaint();
		cf.revalidate();
		//goToSettings();

	}

	public static void loadWorld(VoxelCt vix, boolean resume)
	{
		
		vx = vix;
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Voxelcraft - Java.JE: "+vix.getVen().getName());
		cf.setVisible(true);
		listenHandle();
		Minecraft.currentWorldName = vx.getName();
		cp=vx;
		cf.getContentPane().setCursor(Minecraft.getBlankCurosr());
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
		cf.setTitle("Voxelcraft - Java.JE");
		cf.setVisible(true);
		listenHandle();
		cp= new FilePanel();
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
		
	}
	/** Set ContentPane with this
	 * */
	public static Cursor getBlankCurosr() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		return blankCursor;
	}
	
	
	public static void goToVox() {
		VoxEn ven = Minecraft.vx.getVen();
		VoxelCt vct = new VoxelCt(ven,Minecraft.vx.mode,true);
		Minecraft.loadWorld(vct,true);
	}
	public static void loadVen(VoxEn ven) {
		VoxelCt vct = new VoxelCt(ven,Minecraft.vx.mode,true);
		Minecraft.loadWorld(vct,true);
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
		cf.setTitle("Voxelcraft - Java.JE");
		cf.setVisible(true);
		listenHandle();
		set.getRenslider().setValue(Minecraft.renderVal);
		set.getVolSlider().setValue((int) (PlaySound.vol*100));
		cp=set;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
	}

	public static void test() {
		
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
		cf.setTitle("Voxelcraft - Java.JE");
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
	public static void quickSettings() {
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Voxelcraft - Java.JE");
		cf.setVisible(true);
		listenHandle();
		qs.getRenslider().setValue(Minecraft.renderVal);
		qs.getVolSlider().setValue((int) (PlaySound.vol*100));
		cp=qs;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.add(cp);
		cf.repaint();
		cf.revalidate();
		
		
		
		
	}
	
	
	
	
	/** Quick Menu Popup
	 * 
	 */
	public static void quickMenu() {
		if(qm==null) {
			qm = new QuickMenu(Minecraft.vx);
			
		}else {
			if(cp==Minecraft.vx) {
				qm.reset();
			}
			qm.redraw(Minecraft.vx);
		}
		Point p = cf.getLocationOnScreen();
		Dimension siz = cf.getSize();
		//cf.dispose();
		JFrame f = new JFrame();
		cf.setContentPane(f.getContentPane());
		cp= new JPanel(null);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setTitle("Voxelcraft - Java.JE: "+vx.getVen().getName());
		cf.setVisible(true);
		listenHandle();
		vx.add(qm,1);
		ComponentListener[] listeners = qm.getComponentListeners();
		for(int i=0;i<listeners.length;i++) {
		vx.addComponentListener(listeners[i]);
		}
		vx.setCursor(Cursor.getDefaultCursor());
		cp=vx;
		cf.setLocation(p);
		cf.setSize(siz);
		cf.setCursor(Cursor.getDefaultCursor());
		cf.add(cp);
		cf.repaint();
		cf.revalidate();

		
		
		
		
	}

}
