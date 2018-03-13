/**
 * 
 */
package temp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * @author Karib
 *
 */
public class JFrameFun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame c = new JFrame();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setSize(500, 500);
		c.setVisible(true);
		c.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println(arg0.getKeyChar());
				
			}
			
		});
		
		c.repaint();
		c.revalidate();

	}

}
