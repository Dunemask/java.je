/**
 * 
 */
package frames;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;

/**
 * @author dunemask
 *
 */
public class TestFrame extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public TestFrame() {
		getContentPane().setLayout(null);
		

		btnResume.setBounds(102, 68, 231, 56);
		getContentPane().add(btnResume);
	}
}
