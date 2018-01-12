package main;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicFrame extends JFrame {

	public BasicFrame() throws HeadlessException {
		this.setTitle("Basic Frame");
		this.setSize(800,800);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		JPanel jil = new JPanel();
		jil.setLayout(null);
		this.add(jil);
		repaint();
		revalidate();
	}

}
