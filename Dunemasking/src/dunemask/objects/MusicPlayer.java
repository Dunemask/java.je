/**
 * 
 */
package dunemask.objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dunemask.util.MediaUtil;
import dunemask.util.FileUtil;
import dunemask.util.RW;
import java.awt.Dimension;

/**
 * Create a new JInternalFrame Music Player using {@link dunemask.util.MediaUtil}
 * 
 * @author Elijah
 *
 */
public class MusicPlayer extends JInternalFrame{
	/**@MeidaPlayer.java: MediaPlayer from (dunemask.objects.MediaPlayer.java) uses
	   MediaUtil from (dunemask.util.MediaUtil.java) as opposed 
	   to using AudioUtil (dunemask.util.AudioUtil.java) MediaUtil
	   uses Deprecated methods that are not reccomended from java*/
	
	/***Version*/
    final static double version = 4.5;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8673426890237302881L;
	/**
	 * Track List
	 **/
	private File trackList;
	/**
	 * Song Folder
	 **/
	private String songFolder;
	/**
	 * File Being Played
	 **/
	private File playing;



	/**
	 * @return the trackList
	 */
	public File getTrackList() {
		return trackList;
	}

	/**
	 * @param trackList the trackList to set
	 */
	public void setTrackList(File trackList) {
		this.trackList = trackList;
	}

	/**
	 * @return the songFolder
	 */
	public String getSongFolder() {
		return songFolder;
	}

	/**
	 * @param songFolder the songFolder to set
	 */
	public void setSongFolder(String songFolder) {
		this.songFolder = songFolder;
	}

	/**
	 * @param playing the playing to set
	 */
	public void setPlaying(File playing) {
		this.playing = playing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	JLabel lblPlaying = new JLabel("Playing:  ");
	static JButton btnFlop;
	static JPanel Main_Button_Panel = new JPanel();
	JButton Play = new JButton(" Play  ");
	/**
	 * Creates a music Player
	 * 
	 * @param trackList
	 *            File that contains all the track names
	 * @param songsFolder
	 *            Folder where the tracks are being stored
	 */
	public MusicPlayer(File trackList, String songsFolder) {
		super("Music Player");
		this.setSize(300, 153);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTrackList(trackList);
		this.songFolder = songsFolder;
		this.playing = new File("NOTHING");


		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		
		Main_Button_Panel.setBounds(10, 11, 264, 101);
		panel.add(Main_Button_Panel);
		Main_Button_Panel.setLayout(null);

		lblPlaying.setBounds(83, 38, 34, 14);
		lblPlaying.setSize(lblPlaying.getPreferredSize());
		Main_Button_Panel.add(lblPlaying);

		JButton btnList = new JButton("List");
		btnList.setBounds(205, 67, 49, 23);
		btnList.setSize(btnList.getPreferredSize());
		Main_Button_Panel.add(btnList);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listSongs();
			}
		});
		btnList.setToolTipText("List of Songs");
		btnList.setBackground(new Color(0, 0, 0));
		btnList.setForeground(new Color(204, 204, 204));

		JButton btnStop = new JButton(" Stop ");
		btnStop.setBounds(8, 67, 55, 23);
		btnStop.setSize(btnStop.getPreferredSize());
		Main_Button_Panel.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MediaUtil.stop();
				Main_Button_Panel.remove(btnFlop);
				btnFlop = pb();
				Main_Button_Panel.add(btnFlop);
				Main_Button_Panel.repaint();
				Main_Button_Panel.revalidate();
			}
		});
		btnStop.setToolTipText("Stops the Music Playing (Removes loop function if active)");
		btnStop.setBackground(new Color(0, 0, 0));
		btnStop.setForeground(new Color(204, 204, 204));

		
		Play.setBounds(8, 0, 61, 23);
		Play.setSize(Play.getPreferredSize());
		Main_Button_Panel.add(Play);
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play();
				Main_Button_Panel.remove(btnFlop);
				btnFlop = pb();
				Main_Button_Panel.add(btnFlop);
				Main_Button_Panel.repaint();
				Main_Button_Panel.revalidate();
			}
		});
		Play.setToolTipText("Plays a song once");
		Play.setBackground(new Color(0, 0, 0));
		Play.setForeground(new Color(204, 204, 204));
		
		JButton Loop = new JButton("Loop");
		Loop.setLocation(btnList.getX(),Play.getY());
		Loop.setSize(Loop.getPreferredSize());
		Main_Button_Panel.add(Loop);
		Loop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*	if(playing!=null) {
					MediaUtil.stop();
					new Thread(() -> {
						MediaUtil.playRepeat(playing);
					}).start();
					Main_Button_Panel.remove(btnFlop);
					btnFlop = pb();
					Main_Button_Panel.add(btnFlop);
					Main_Button_Panel.repaint();
					Main_Button_Panel.revalidate();
				}*/
				MediaUtil.setOnRepeat();
	
			}
		});
		Loop.setToolTipText("Puts the song playing on a loop");
		Loop.setBackground(new Color(0, 0, 0));
		Loop.setForeground(new Color(204, 204, 204));
		

		btnFlop = pb();
		Main_Button_Panel.add(btnFlop);
		
		JButton buttonOnline = new JButton("Load");
		buttonOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MediaUtil.pause();
				String path = JOptionPane.showInputDialog("URL:");
				load(path);
				
			}
		});
		buttonOnline.setToolTipText("Load Song From Online and start");
		buttonOnline.setSize(new Dimension(61, 23));
		buttonOnline.setForeground(new Color(204, 204, 204));
		buttonOnline.setBackground(Color.BLACK);
		buttonOnline.setBounds(109, 67, Play.getWidth(), Play.getHeight());
		Main_Button_Panel.add(buttonOnline);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(66, 117, 136, -46);
		panel.add(panel_1);
		this.setVisible(true);

	}

	/**
	 * @param path URL
	 */
	protected void load(String path) {
		File tmp = FileUtil.getWebFile(path);
		//System.out.println(tmp);
		File song = tmp;
		//FileUtil.writeFile(tmp, song);
		playing = song;
		lblPlaying.setText("Playing:  " + song.getName().replace("%20", " "));
		lblPlaying.setSize(lblPlaying.getPreferredSize());
		this.repaint();
		this.revalidate();
		MediaUtil.pause();
		new Thread(() -> {
			MediaUtil.play(song);
		}).start();
		
	}

	/**
	 * 
	 */
	protected void listSongs() {
		final int trackNumber = FileUtil.linesInFile(trackList);
		for (int i = 1; i <= trackNumber; i += 5) {
			String[] songs = RW.read(trackList, i, i + 5);
			for (int c = 0; c < songs.length; c++) {
				if (songs[c] == null) {
					songs[c] = "";
				} else {
					songs[c] = String.valueOf(i + c) + ":" + songs[c].replace("%20", " ");
				} // Close ifElse
			} // Close INternal For

			JOptionPane.showMessageDialog(null,
					songs[0] + "\n" + songs[1] + "\n" + songs[2] + "\n" + songs[3] + "\n" + songs[4] + "\n");
		} // Close External for

	}

	/**
	 * Play Function
	 * 
	 */
	protected void play() {
		String tmp = JOptionPane.showInputDialog("Track Number");
		final int trackNumber = Integer.parseInt(tmp);
		String trackName = RW.read(trackList, trackNumber);
		

		final String trackPath = songFolder + trackName;
		//System.out.println("Tpath: "+trackPath);
		final File song = FileUtil.getResource(trackPath);
		playing = song;
		lblPlaying.setText("Playing:  " + trackName);
		lblPlaying.setSize(lblPlaying.getPreferredSize());
		this.repaint();
		this.revalidate();
		MediaUtil.pause();
		new Thread(() -> {
			MediaUtil.play(song);
		}).start();

	}
	/**Puts the song specified on loop Without any jpanel dialog
	 * @param song Song File
	 * */
	public static void prePlayerLoop(File song) {
		new Thread(() -> {
			MediaUtil.play(song);
			MediaUtil.setOnRepeat();
		}).start();
	}
	

	public File getPlaying() {
		return playing;
	}
	
	private static JButton pb(){
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(8, 34, 55, 23);
		btnPause.setSize(btnPause.getPreferredSize());
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MediaUtil.pause();
				Main_Button_Panel.remove(btnFlop);
				btnFlop = rs();
				Main_Button_Panel.add(btnFlop);
				Main_Button_Panel.repaint();
				Main_Button_Panel.revalidate();
			}
		});
		btnPause.setBackground(new Color(0, 0, 0));
		btnPause.setForeground(new Color(204, 204, 204));
		btnPause.setToolTipText("Pauses the song playing");
		return btnPause;
		
	}
	private static JButton rs() {

			JButton btnPause = new JButton("Resume");
			btnPause.setBounds(8, 34, 50, 23);
			btnPause.setSize(btnPause.getPreferredSize());
			
			btnPause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MediaUtil.resume();
					Main_Button_Panel.remove(btnFlop);
					btnFlop = pb();
					Main_Button_Panel.add(btnFlop);
					Main_Button_Panel.repaint();
					Main_Button_Panel.revalidate();
				}
			});
			btnPause.setBackground(new Color(0, 0, 0));
			btnPause.setForeground(new Color(204, 204, 204));
			btnPause.setToolTipText("Resumes the song");
			return btnPause;
			
		
	}
}


	

