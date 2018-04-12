import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class MPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CountDownLatch envLatch = new CountDownLatch(1);
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
		String reps = "tmp";
		String genericPath = "resources/media/mp3/Bromance.mp3";
		String url = "https://github.com/Dunemask/"+reps+"/raw/master/"+genericPath;
		//System.out.println(f.getAbsolutePath());
		Media m = new Media(url);
		MediaPlayer player = new MediaPlayer(m);
		player.play();
		
		
		
		
		
		
		
		

	}

}
