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

	private MediaPlayer mediaPlayer;
	private CountDownLatch envLatch;
	/**
	 * 
	 */
	public DMediaPlayer() {
	}
	
	
	
	
	
	public void init() {
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
	public void setMedia(File file) {
		Media sound = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
	}
	
	public void setMedia(String string) {
		Media sound = new Media(string);
		mediaPlayer = new MediaPlayer(sound);
	}
	
	
	/**
	 * @return the mediaPlayer
	 */
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	/**
	 * @param mediaPlayer the mediaPlayer to set
	 */
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}







	/**
	 * @return the envLatch
	 */
	public CountDownLatch getEnvLatch() {
		return envLatch;
	}






	/**
	 * @param envLatch the envLatch to set
	 */
	public void setEnvLatch(CountDownLatch envLatch) {
		this.envLatch = envLatch;
	}


}
