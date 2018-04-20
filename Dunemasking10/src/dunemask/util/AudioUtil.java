package dunemask.util;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**Used for playing audio clips (.WAV files)
 * <p>Play Song On Loop: {@link dunemask.util.AudioUtil#songLoop(File)}</p>
 * <p>Stop Music: {@link dunemask.util.AudioUtil#closeClip()}</p>
 *  <p>Pause: {@link dunemask.util.AudioUtil#pause()}</p>
 *  <p>Play Song Once: {@link dunemask.util.AudioUtil#playOnce(File)}</p>
 *  <p>Resume: {@link dunemask.util.AudioUtil#resumeSong(File)}</p>
 * @author Elijah 
 * 
 * 
 */
public class AudioUtil{
	/***Version*/
    final static double version = 4.5;

	/*
	 * public static Runnable Lyoko() throws LineUnavailableException,
	 * javax.sound.sampled.UnsupportedAudioFileException, IOException,
	 * InterruptedException { String Lyko = Colloseum.resc + "music/Lyoko.wav";
	 * 
	 * javax.sound.sampled.AudioInputStream audioInputStream =
	 * AudioSystem.getAudioInputStream(new File(Lyko).getAbsoluteFile()); clip =
	 * AudioSystem.getClip(); clip.open(audioInputStream); clip.start();
	 * Thread.sleep(clip.getMicrosecondLength() / 1000L); return null; }
	 */
	public static Clip clip;
	public static File song;
	public static int pauseLocation;

	/**
	 * Plays song on loop
	 * 
	 * @param song
	 *            The File where song is located (Must be a .wav file)
	 * 
	 */
	public static void songLoop(File song) {

		AudioInputStream audioInputStream = null;

		try {
			audioInputStream = AudioSystem.getAudioInputStream(song.getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e1) {

			e1.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		}

		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e1) {

			e1.printStackTrace();
		}

		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		try {
			Thread.sleep(clip.getMicrosecondLength() / 1000);
			// Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Stops current playing song (If playing)
	 * 
	 */
	public static void closeClip() {
		if (clip != null) {
			clip.setFramePosition(0);
			clip.flush();
			clip.close();
			clip.stop();
		}
	}

	/**
	 * Plays song or short Sound clip once
	 * 
	 * @param song
	 *            The File where song is located (Must be a .wav file)
	 * 
	 */
	public static void playOnce(File song) {
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(song.getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e1) {

			e1.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e1) {

			e1.printStackTrace();
		}
		Clip playedOnceClip = clip;
		clip.start();

		try {
			Thread.sleep(clip.getMicrosecondLength() / 1000);
			// Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		if (playedOnceClip == clip) {
			closeClip();
		}
	}

	/**
	 * Stores current location and closes clip
	 */
	public static void pause() {
		if (clip != null) {
			pauseLocation = clip.getFramePosition();
			closeClip();
		}

	}

	/**
	 * Puts the song where the held location is
	 * 
	 * @param song
	 *            File that will be resumed
	 */
	public static void resumeSong(File song) {
		AudioInputStream audioInputStream = null;
	
			try {
				audioInputStream = AudioSystem.getAudioInputStream(song.getAbsoluteFile());
			} catch (UnsupportedAudioFileException | IOException e2) {
				e2.printStackTrace();
			}
			
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e1) {

			e1.printStackTrace();
		}
		clip.setFramePosition(pauseLocation);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		try {
			Thread.sleep(clip.getMicrosecondLength() / 1000);
			// Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	 

}
