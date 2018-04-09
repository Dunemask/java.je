/**
 * 
 */
package dunemask.util;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**Media Player for mp3 and wav files, Uses deprectaed API
 * <p>Play Song Once {@link dunemask.util.MediaUtil#play(File)}</p>
 * <p>Play a song on Repeat {@link dunemask.util.MediaUtil#playRepeat(File)}</p>
 * <p>Stop Music{@link dunemask.util.MediaUtil#stop()}</p>
 * <p>Pause Music {@link dunemask.util.MediaUtil#pause()}</p>
 * <p>Resume Music {@link dunemask.util.MediaUtil#resume()}</p>
 * <p>Sets song to start repeating {@link dunemask.util.MediaUtil#setOnRepeat()}</p>
 * <p>Sets song to end when done Running {@link dunemask.util.MediaUtil#removeRepeat()}</p>
 * @author Elijah
 *
 */
public class MediaUtil{
	/***Version*/
    final static double version = 4.5;
	public static  MediaPlayer mediaPlayer;
	public static CountDownLatch latch = new CountDownLatch(1);
	/**Play a song Once
	 * @param song The Song File
	 * */
	public static void play(File song) {
		latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		        
		    }
		});
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Media sound = new Media(song.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();	
		
		
	}
	/**Stops the media and puts the cursors to 0
	 * And Removes Loop Modifier
	 * */
	public static void stop() {
		if(mediaPlayer!=null) {
		mediaPlayer.stop();
		removeRepeat();
		}
	}
	/**Pauses Media
	 * **/
	public static void pause() {
		if(mediaPlayer!=null) {	
			mediaPlayer.pause();

		}
	}
	/**Resumes song if not null
	 * **/
	public static void resume() {
		if(mediaPlayer!=null) {
			mediaPlayer.play();
			
		}
		
	}
	/**Set Song on Repeat
	 * */
	public static void setOnRepeat() {
		if(mediaPlayer!=null) {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		           mediaPlayer.seek(Duration.ZERO);
		         }
		     });
		}
	}
	/**Set Song on Repeat
	 * */
	public static void seek(double millis) {
		if(mediaPlayer!=null) {
		mediaPlayer.seek(new Duration(millis));
		}
	}
	/**If Player was On Repeat this will remove it
	 **/
	public static void removeRepeat() {
		if(mediaPlayer!=null) {
			mediaPlayer.setOnEndOfMedia(new Runnable() {
			       public void run() {
			       
			         }
			     });
			}
	}
	
	/***
	 * Play A Song On Repeat
	 * @param song The Song File
	 * */
	public static void playRepeat(File song) {
		latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		        
		    }
		});
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Media sound = new Media(song.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		           mediaPlayer.seek(Duration.ZERO);
		         }
		     });
		mediaPlayer.play();
		
	}
	
	
	
}
