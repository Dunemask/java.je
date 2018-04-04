package jtunes;
import javax.swing.JFrame;

import dunemask.util.xml.XMLMap;
import panels.MainFrame;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class JTunes {

	public static String JTunesFolder = System.getProperty("user.home")+"/Music/JTunes/";
	public static XMLMap library;
	public static JFrame cf;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		cf = new MainFrame();
		cf.setVisible(true);
		

	}

}
