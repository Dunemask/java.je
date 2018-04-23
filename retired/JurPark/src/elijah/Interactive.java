/**
 * 
 */
package elijah;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.CountDownLatch;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dunemask.util.FileUtil;

/**
 * @author Karib
 *
 */
public class Interactive {
	private boolean berry;
	
	public static boolean eatBurry() throws MalformedURLException {
		CountDownLatch latch = new CountDownLatch(1);
		
		
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
		frame.setAlwaysOnTop(true);
		new Thread( () -> {
			try {
				Thread.sleep(5000);
				//System.out.println("Waitteetd");
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}).start();;
		//System.out.println("Taco");
		
		
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.dispose();
		
		return false;
		
		
		
	}
	
	
	
}
