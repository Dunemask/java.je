/** 
 *  Created This File on the Main Repository
 * @contact  at 
 * commit = false
 * File: test.Capture.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package dunemask.dunemasking;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dunemask.util.FileUtil;

/**
 * TO Parse an output as a string value do outContent.parse();
 * Perform setup to capture and cleanup to close
 * @author Elijah
 *
 */
public class Capture {
	/**Version*/
    final static double version = 4.1;
	public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	public static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	public final static PrintStream sysDefaultOut = System.out;
	public final static PrintStream sysDefaultErr = System.err;
	
	/**Turn On Capture*/
	public static void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	/**Turn Off Capture
	 * */
	public static void cleanUpStreams() {
	    System.setOut(sysDefaultOut);
	    System.setErr(sysDefaultErr);
	  
	}
	
	/**@param msg Message
	 * @return InputStream from a String Displayed Via JoptionPane
	 */
	public static InputStream getConsoleIn(String msg) {
		InputStream is = null;
		is = new ByteArrayInputStream(Charset.forName("UTF-8").encode(JOptionPane.showInputDialog(msg)).array());
		return is;
	}
	
	/**@return Output Gathered Thus Far, Clears Stream
	 * */
	public static ByteArrayOutputStream getOut() {
		ByteArrayOutputStream old = outContent;
		outContent = new ByteArrayOutputStream();
		return old;
		
	}
	
	/**@return Error Gathered Thus Far, Clears Stream
	 * */
	public static ByteArrayOutputStream getErr() {
		ByteArrayOutputStream old = errContent;
		errContent = new ByteArrayOutputStream();
		return old;
		
	}
	
	/**Get Input From User
	 * @param msg Message
	 * @return Return String input
	 * */
	public static String getInput(String msg) {
		String input =null;		
		input =  JOptionPane.showInputDialog(msg);
		return input;
	}
	
	

	public static JFrame cons = new JFrame("DM-Console");
	public static JPanel jip = new JPanel();
	public static 	boolean consOpen = true;
	/** Start Console
	 * <p> Will Route all Outs and errors to a JFrame console</p>
	 * 
	 */
	public static void startConsole() {
		Capture.setUpStreams();
		System.out.println("[DM Console]");
		cons.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		cons.setSize(800, 500);
		//jta.append(Capture.outContent.toString());
		File icon = FileUtil.getWebFile("https://github.com/Dunemask/dunemask.github.io/raw/master/resources/media/images/DM-Dice.jpg");
		
		try {
			cons.setIconImage(Toolkit.getDefaultToolkit().getImage(icon.toURI().toURL()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		jip.setLayout( new BoxLayout(jip, BoxLayout.PAGE_AXIS));

        JTextArea jta = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(jta);
        jip.add(scrollPane);
		cons.add(jip);
		cons.setVisible(true);
		jta.setEditable(true);
		cons.repaint();
		cons.revalidate();
		cons.setLocationRelativeTo(null);
	
		Thread update =    new Thread( () -> {
			   while(consOpen) {
				
				   if(!jta.getText().equals(Capture.outContent.toString()+Capture.errContent.toString())) {

					 jta.setText(Capture.outContent.toString()+Capture.errContent.toString());
					 
					
					 JScrollBar vertical = scrollPane.getVerticalScrollBar();
					 vertical.setValue( vertical.getMaximum() );
					  
					 cons.repaint();
					 cons.revalidate();
					 
				 }
				
				   
			   }
			   
			   cons.dispose();
			   cons.setVisible(false);
			   
		   });
		update.start();

	}
	
	/**Closes the institued Console
	 * */
	public static void closeConsole() {
		consOpen = false;
		Capture.cleanUpStreams();
		
		
	}

}
