/**
 * 
 */
package elijah;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dunemask.objects.ScaledImageLabel;
import dunemask.util.FileUtil;

/**
 * @author Karib
 *
 */
public class Interactive {
	private boolean berry;
	
	public static boolean eatBurry() throws MalformedURLException {
		
		new Thread( () -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		boolean berryEaten = false;
		JFrame frame = new JFrame("Jurrasic Island");
		frame.setSize(400,400);
		JPanel jip = new JPanel();
		jip.setLayout(new BorderLayout());
		File berryFile = FileUtil.getResource("resources/berry.png");
		ImageIcon img = new ImageIcon(berryFile.toURI().toURL());
		JButton berryButton = new JButton();
		berryButton.setIcon(img);
		berryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You Eat The berry, It's poisonous");
				Story.score+=5;
				Story.youDead();
				
			}
			
			
		});
		jip.add(berryButton);
		frame.add(jip);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		return berryEaten;
		
		
		
	}
	
	
	
}
