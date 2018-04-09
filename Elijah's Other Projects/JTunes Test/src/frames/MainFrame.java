package frames;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import jtunes.JSong;

import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JList;

public class MainFrame extends JFrame {
	private JTextField txtSearch;
	public static ArrayList<JSong> aList;
	JList<String> list = new JList<String>();
	
	public MainFrame() {
		this.setTitle("MainFrameTitle");
		this.setSize(512,512);
		this.setLocation(256, 256);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblStuff = new JLabel("Stuff");
		lblStuff.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblStuff, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("SEARCH TOOL");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setText("search");
		verticalBox.add(txtSearch);
		txtSearch.setColumns(10);
		
		verticalBox.add(list);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		verticalBox.add(lblNewLabel_1);
		this.setVisible(true);
		
	}
	public void SetSongs(ArrayList<JSong> newaList) {
		aList= newaList;
	}
	void ResetList() {
		list.add(comp)
	}
}
