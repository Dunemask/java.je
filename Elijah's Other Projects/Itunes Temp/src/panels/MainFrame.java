/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dunemask.util.FileUtil;
import jtunes.JTunes;
import javax.swing.JButton;

/**
 * @author dunemask
 *
 */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852115628604464359L;
	public JTextField txtSearchbox;
	public static JSlider slider;
	public static List list;
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileUtil.getResourceURL("resources/lightjtn.png")));
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTunes");
	    try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    } catch (InstantiationException e) {
	      e.printStackTrace();
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e) {
	      e.printStackTrace();
	    }
	    SwingUtilities.updateComponentTreeUI(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel main = new JPanel();
	    getContentPane().add(main, BorderLayout.CENTER);
	    main.setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.LIGHT_GRAY);
	    panel.setBounds(0, 0, 594, 79);
	    main.add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblSongTitle = new JLabel("Song Title");
	    lblSongTitle.setFont(new Font("Verdana", Font.PLAIN, 18));
	    lblSongTitle.setBounds(236, 11, 99, 29);
	    panel.add(lblSongTitle);
	    
	    slider = new JSlider();
	    slider.setBounds(188, 42, 200, 26);
	    slider.setValue(0);
	    panel.add(slider);
	    
	    String normText = "Search...";
	    txtSearchbox = new JTextField();
	    txtSearchbox.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				search();
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    txtSearchbox.setText(normText);
	    txtSearchbox.setBounds(450, 55, 86, 24);
	    panel.add(txtSearchbox);
	    txtSearchbox.setColumns(10);
	    
	    JButton btnPlay = new JButton("Play");
	    btnPlay.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int index = list.getSelectedIndex();
	    		String item = list.getItem(index);
	    	}
	    });
	    btnPlay.setBounds(535, 55, 59, 29);
	    panel.add(btnPlay);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(Color.DARK_GRAY);
	    panel_1.setBounds(0, 80, 125, 491);
	    main.add(panel_1);
	    
	    list = new List();
	    list.setBounds(484, 80, 110, 88);
	    main.add(list);
	    this.setVisible(true);
	    this.setResizable(false);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu mnFile = new JMenu("File");
	    menuBar.add(mnFile);
	    
	    JMenuItem mntmAddFile = new JMenuItem("Add File");
	    mntmAddFile.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		addFile();
	    	}
	    });
	    mnFile.add(mntmAddFile);
	    this.setLocationRelativeTo(null);
	}
	/**
	 * 
	 */
	protected void addFile() {
		Frame frame = new Frame();
		frame.setVisible(true);
		FileDialog fd = new FileDialog(frame);
		fd.setAlwaysOnTop(true);
		fd.setMultipleMode(true);
		fd.setDirectory(System.getProperty("user.home"+"/Desktop/"));
		fd.setVisible(true);
		fd.setVisible(false);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(fd.getFiles()));
		fd.dispose();
		frame.dispose();
		String mdir = JTunes.JTunesFolder+"Music/";
		for(File f:files) {
			String artist = JOptionPane.showInputDialog("Artist (Leave Blank For Unknown)");
			String album = JOptionPane.showInputDialog("Album (Leave Blank For Unknown)");
			if(artist.equals("")) {
				artist = "Unknown Artist";
			}
			if(album.equals("")) {
				album = "Unknown Album";
			}
			new File(mdir+artist+"/"+album+"/").mkdirs();
			FileUtil.writeFile(f, new File((mdir+artist+"/"+album+"/")+f.getName()));
			JTunes.addSong(artist, album, FileUtil.removeExtension(f.getName()), f);
		}
		JOptionPane.showMessageDialog(null, "All Files Chosen Added SuccessFully", "JTunes File Add", JOptionPane.PLAIN_MESSAGE);
		
		
	}
	/**
	 * 
	 */
	String ctext;
	protected void search() {
	    String normText = "Search...";
	   ctext = normText;
		new Thread( () ->{
			while(true) {
	    		if(!txtSearchbox.getText().equals(ctext)) {
	    			ctext = this.txtSearchbox.getText();
	    			list.removeAll();
	    			System.out.println("REmove");
	    			ArrayList<String> art=	JTunes.findArtists(this.txtSearchbox.getText());
	    			for(int i=0;i<art.size();i++) {
	    				list.add(art.get(i));
	    			}
	    			ArrayList<String> song=	JTunes.findSongs(this.txtSearchbox.getText());
	    			for(int i=0;i<art.size();i++) {
	    				list.add(art.get(i));
	    			}
	    			
	    			
	    		}
			
			
			}
		}).start();
		
	}
}
