package frames;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class MainFrame extends JFrame {
	private JTextField txtSearch;
	static ImageIcon notegIcon = new ImageIcon("src/resources/note.gif");
	boolean isplay;
	
	public static ArrayList<JSong> aList;
	DList dlist;
	
	public MainFrame() {
		isplay = false;
		
		this.setTitle("MainFrameTitle");
		this.setSize(512,512);
		this.setLocation(256, 256);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblStuff = new JLabel("JTunes");
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
		
		
		dlist = new DList(tst);
		JScrollPane scrollList = new JScrollPane(dlist);
		scrollList.setPreferredSize(new Dimension(200,300));
		verticalBox.add(scrollList);
		
		
		this.setVisible(true);
		
		//this.pack();
		JLabel lab = new JLabel(notegIcon);
		getContentPane().add(lab);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("<<");
		panel_1.add(button_1);
		
		JButton btnPlay = new JButton("Play");
		panel_1.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isplay){
				btnPlay.setText("Stop");
				isplay=false;
				}else {
				btnPlay.setText("Play");
				isplay=true;
				}
				
			}
		});
		
		JButton button = new JButton(">>");
		panel_1.add(button);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mnFile.add(mntmAdd);
	}
	public void SetSongs(ArrayList<JSong> newaList) {
		aList= newaList;
	}
}
