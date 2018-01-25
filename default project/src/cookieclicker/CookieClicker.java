/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: cookieclicker.CookieClicker.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package cookieclicker;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

/**
 * @author karib
 *
 */
public class CookieClicker {

	
	public static JFrame cframe = new JFrame("Cookie Clicker");
	public static int cookies =0;
	protected static JLabel cookiCounter = new JLabel("Cookies:"+cookies);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setFrame();

	}


	/**
	 * 
	 */
	private static void setFrame() {
		
		
		
		cframe.setSize(528, 526);
		cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cframe.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 160, 49);
		cframe.getContentPane().add(panel_1);
		
		
		panel_1.add(cookiCounter);
		
		
		JButton btnCookie = new JButton("Cookie!");
		btnCookie.setBounds(10, 197, 160, 98);
		cframe.getContentPane().add(btnCookie);
		btnCookie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cookies+=1;
				cookiCounter.setText("Cookies:"+cookies);
				cframe.repaint();
				cframe.revalidate();
				
				btnCookie.setForeground(Color.WHITE);
				btnCookie.setBackground(new Color(102, 51, 0));
			}
			
		}
		);

		
		btnCookie.setForeground(Color.WHITE);
		btnCookie.setBackground(new Color(102, 51, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(294, 0, 10, 501);
		cframe.getContentPane().add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(294, 0, 218, 16);
		cframe.getContentPane().add(panel_2);
		
		JButton btnClicker = new JButton("Clicker - 10 ");
		btnClicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cookies>=10) {
					cookies-=10;
				new AutoClicker().start();
				}
			}
		});
		btnClicker.setBounds(304, 27, 198, 23);
		cframe.getContentPane().add(btnClicker);
		
		JButton addGrandma = new JButton("Grandma - 50");
		addGrandma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cookies>=50) {
					cookies-=50;
				new Grandma().start();
				}
			}
		});
		addGrandma.setBounds(304, 61, 198, 23);
		cframe.getContentPane().add(addGrandma);
		cframe.setVisible(true);
		
	}
}
