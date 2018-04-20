/**
 * 
 */
package dunemask.objects;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * @author dunemask
 *
 */
public class DMediaPlayer {

	private static MediaPlayer mediaPlayer;
	private static CountDownLatch envLatch;
	private static Media playingMedia;
	
	
	
	
	
	public static void init() {
		envLatch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        envLatch.countDown();
		        
		    }
		});
		
		try {
			envLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void setMedia(File file) {
		DMediaPlayer.setPlayingMedia(new Media(file.toURI().toString()));
		mediaPlayer = new MediaPlayer(DMediaPlayer.playingMedia);
	}
	
	public static void setMedia(String string) {
		DMediaPlayer.setPlayingMedia(new Media(string));
		mediaPlayer = new MediaPlayer(DMediaPlayer.playingMedia);
	}
	
	
	/**
	 * @return the mediaPlayer
	 */
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	/**
	 * @param mediaPlayer the mediaPlayer to set
	 */
	public static void setMediaPlayer(MediaPlayer mediaPlayer) {
		DMediaPlayer.mediaPlayer = mediaPlayer;
	}







	/**
	 * @return the envLatch
	 */
	public static CountDownLatch getEnvLatch() {
		return envLatch;
	}






	/**
	 * @param envLatch the envLatch to set
	 */
	public static void setEnvLatch(CountDownLatch envLatch) {
		DMediaPlayer.envLatch = envLatch;
	}
	/**
	 * @return the playingMedia
	 */
	public static Media getPlayingMedia() {
		return playingMedia;
	}
	/**
	 * @param playingMedia the playingMedia to set
	 */
	public static void setPlayingMedia(Media playingMedia) {
		DMediaPlayer.playingMedia = playingMedia;
	}


}
