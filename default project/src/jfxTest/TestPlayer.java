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
		
		MovieLauncher.startPlayer(null ,true);
		try {
			MovieLauncher.current.getPlayerReady().await();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		String address = "https://dunemask.github.io/resources/media/mp3/If%20You%20Just%20Believe.mp3";
		Media m = (new Media(FileUtil.getWebFile(address).toURI().toString()));
		MovieLauncher.current.setMediaPlayer(new MediaPlayer(m));
		MovieLauncher.current.getMediaPlayer().seek(new Duration(0.0));
		MovieLauncher.current.getMediaView().setMediaPlayer(MovieLauncher.current.getMediaPlayer());
		System.out.println("Done");
	
	
	}

}
