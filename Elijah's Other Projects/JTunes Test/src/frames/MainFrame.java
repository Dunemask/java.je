package frames;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import jtunes.JSong;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;

public class MainFrame extends JFrame {
	private JTextField txtSearch;
	static ImageIcon notegIcon = new ImageIcon("src/resources/note.gif");

	
	public static ArrayList<JSong> aList;
	DList dlist;
	
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
		
		
		
		//TEXT FEILD
		txtSearch = new JTextField();
		txtSearch.setText("search");
		verticalBox.add(txtSearch);
		//txtSearch.setColumns(10);
		txtSearch.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtSearch.getText().contains("search")){
					txtSearch.setText("");
				}
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				dlist.SearchString(txtSearch.getText());
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				//dlist.SearchString(txtSearch.getText());
				
			}});
		
		
		//TESTING ARRAYLISTS
		ArrayList<JSong> tst = new ArrayList<JSong>();
		tst.add(new JSong(null, "Daylight Moon"));
		tst.add(new JSong(null, "Midnight Moon"));
		tst.add(new JSong(null, "HarshLess"));
		tst.add(new JSong(null, "SoulLess"));
		
		
		
		
		dlist = new DList(tst);
		JScrollPane scrollList = new JScrollPane(dlist);
		scrollList.setPreferredSize(new Dimension(150,300));
		verticalBox.add(scrollList);
		
		
		this.setVisible(true);
		
		//this.pack();
		JLabel lab = new JLabel(notegIcon);
		this.add(lab);
	}
	public void SetSongs(ArrayList<JSong> newaList) {
		aList= newaList;
	}
}
