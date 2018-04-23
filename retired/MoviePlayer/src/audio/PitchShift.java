/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: audio.PitchShift.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link audio }</p>
 */
package audio;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import dunemask.objects.movieplayer.MovieLauncher;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.MediaPlayer;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link audio }</p>
 */
public class PitchShift {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MovieLauncher.startPlayer(null,true);
		try {
			MovieLauncher.current.getPlayerReady().await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AudioSpectrumListener asl  = MovieLauncher.current.getMediaPlayer().getAudioSpectrumListener();
		//asl.
		
		//asl.spectrumDataUpdate(timestamp, duration, magnitudes, phases);	
		// MovieLauncher.current.getMediaPlayer().audioSpectrumIntervalProperty().
		
		
	
		
	}
}
