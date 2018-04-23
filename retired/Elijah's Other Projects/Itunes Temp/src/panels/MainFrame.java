/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dunemask.util.FileUtil;
import dunemask.util.MediaUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import jtunes.JSong;
import jtunes.JTunes;

/**
 * @author dunemask
 *
 */
public class MainFrame extends JFrame {
	
	Thread track;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852115628604464359L;
	private static  JButton btnRepeat;
	public static boolean onRep=false;
	private JList<String> songList;
	public static JLabel icon ;
	public JTextField txtSearchbox;
	public static boolean pauseMode;
	public static DefaultListModel<String> list = new DefaultListModel<String>();
	public static ArrayList<JSong> songs = new ArrayList<JSong>();
	public static JPanel main;
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
	    track = new Thread();
	    main = new JPanel();
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
	    lblSongTitle.setPreferredSize(lblSongTitle.getPreferredSize());
	    panel.add(lblSongTitle);
	    
	    String normText = "Search...";
	    txtSearchbox = new JTextField();
	    txtSearchbox.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
	    	
	    });
	    txtSearchbox.setText(normText);
	    txtSearchbox.setBounds(450, 55, 86, 24);
	    panel.add(txtSearchbox);
	    txtSearchbox.setColumns(10);
	    
	    JButton btnPlay = new JButton("Play");
	    btnPlay.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		//TODO
	    		int ind  =  songList.getSelectedIndex();
	    		//System.out.println(ind);
	    		File item = songs.get(ind).getFile();
	    		//System.out.println(item.getAbsolutePath());
	    		MediaUtil.stop();
	    		
	    	    panel.remove(lblSongTitle);
	    		lblSongTitle.setText(FileUtil.removeExtension(item.getName()));
	    	    lblSongTitle.setLocation(236, 11);
	    	    lblSongTitle.setFont(new Font("Verdana", Font.PLAIN, 18));
	    	    lblSongTitle.setSize(900,(int) lblSongTitle.getPreferredSize().getHeight());
	    	    panel.add(lblSongTitle);
	    	    panel.repaint();
	    	    panel.revalidate();
	    	   
	    		new Thread( ()->{
	    		MediaUtil.play(item);
	    		}).start();

	    	}
	    });
	    btnPlay.setBounds(535, 55, 59, 29);
	    panel.add(btnPlay);
	    
	    JButton btnPauseplay = new JButton("(Resume)");
	    btnPauseplay.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		MainFrame.pauseMode=!MainFrame.pauseMode;
	    		if(MainFrame.pauseMode) {
	    			MediaUtil.pause();
	    			btnPauseplay.setText("Paused");
	    		}else {
	    			btnPauseplay.setText("(Resume)");
	    			MediaUtil.resume();
	    		}
	    	}
	    });
	    btnPauseplay.setBounds(10, 11, 99, 23);
	    panel.add(btnPauseplay);
	    
	    btnRepeat = new JButton("Repeat");
	    btnRepeat.setBackground(UIManager.getColor("Button.light"));
	    btnRepeat.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		repButton();
	    	}
	    });
	    btnRepeat.setBounds(10, 45, 99, 23);
	    panel.add(btnRepeat);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(Color.DARK_GRAY);
	    panel_1.setBounds(0, 80, 125, 491);
	    main.add(panel_1);
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setBackground(Color.DARK_GRAY);
	    panel_2.setBounds(459, 80, 135, 491);
	    main.add(panel_2);
	    panel_2.setLayout(null);
	    songList = new JList<String>(list);
	    songList.setBounds(10, 11, 115, 70);
	    panel_2.add(songList);
	    
	    JPanel panel_3 = new JPanel();
	    panel_3.setBackground(Color.LIGHT_GRAY);
	    panel_3.setBounds(123, 174, 335, 244);
	    main.add(panel_3);
	    panel_3.setLayout(null);
	    
	    icon = new JLabel("");
	    icon.setBounds(0, 0, 335, 244);
	    panel_3.add(icon);
	    
	    //list = new JList<String>(stuff.toArray(new String[stuff.size()]));
	    //list.setBounds(484, 80, 110, 88);
	    //main.add(list);
	    
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
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		search();
	}
	/**
	 * 
	 */
	protected void repButton() {
		if(onRep) {
			MediaUtil.removeRepeat();
			btnRepeat.setBackground(UIManager.getColor("Button.light"));
			btnRepeat.setText("Repeat-off");
		}else {
			MediaUtil.setOnRepeat();
			btnRepeat.setBackground(UIManager.getColor("Button.focus"));
			btnRepeat.setText("Repeat-on");
		}
		onRep = !onRep;

		
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
			File nf = new File((mdir+artist+"/"+album+"/")+f.getName());
			JTunes.addSong(artist, album, FileUtil.removeExtension(f.getName()), nf);
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
	Thread test =	new Thread( () ->{
			while(true) {
	    		if(!txtSearchbox.getText().equals(ctext)) {
	    			list.removeAllElements();
	    			ctext = this.txtSearchbox.getText();
	    			//System.out.println("Update");
	    			ArrayList<JSong> song=	JTunes.findSongs(this.txtSearchbox.getText());
	    			songs.removeAll(songs);
	    			songs.addAll(song);
	    			//System.out.println(songs.addAll(song));
	    			for(int i=0;i<song.size();i++) {
	    				list.addElement(song.get(i).getName());
	    			}
				    this.setVisible(true);
				    this.setResizable(false);
	    			
	    			
	    		}
	    		
			
			}	   
		});
	test.start();
		
	}
}
