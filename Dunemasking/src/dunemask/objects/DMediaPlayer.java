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
		Media sound = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
	}
	
	public static void setMedia(String string) {
		Media sound = new Media(string);
		mediaPlayer = new MediaPlayer(sound);
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


}
