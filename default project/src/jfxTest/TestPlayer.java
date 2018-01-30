/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: jfxTest.TestPlayer.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package jfxTest;




import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import dunemask.objects.movieplayer.MovieLauncher;
import dunemask.util.FileUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * @author karib
 *
 */
public class TestPlayer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = new File("C:/Users/Elijah/Desktop/Nightcore - Sword Art Online - Courage.mp4");
		MovieLauncher.startPlayer(file ,true);
		try {
			MovieLauncher.current.getPlayerReady().await();
		//	MovieLauncher.current.Pause();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		MovieLauncher.current.getMediaPlayer().setRate(1.00);
		//MovieLauncher.current.getMediaPlayer()
		//changeFrames();
		
		/*String address = "https://dunemask.github.io/resources/media/mp3/If%20You%20Just%20Believe.mp3";
		Media m = (new Media(FileUtil.getWebFile(address).toURI().toString()));
		MovieLauncher.current.changeMedia(m);
		System.out.println("Done");*/
	
	
	}


}
