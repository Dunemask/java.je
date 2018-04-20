package mc;
import java.io.File;

import dunemask.util.FileUtil;
import mplayer.PlaySound;
import mplayer.Sound;
import mplayer.SoundHandler;

/**
 * 
 */

/**
 * @author Dunemask
 *
 */
public class Run {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		def();
	}
	/**
	 * 
	 */
	private static void def() {
		//System.out.println(new File("tmp.txt").getAbsolutePath());
		Sound s = new Sound("000009", FileUtil.getResource("resources/sounds/etc/click.mp3"), 
				"click", "Minecraft", "Dunemask","/etc/click.mp3");
		
		//SoundHandler.editXML(s,"/etc/click/");
		//String son = "/etc/click/";
		//son = Sound.menuSong[r.nextInt(4)];
		//Sound tmp = SoundHandler.loadSong(son);
		PlaySound.playSound(s);
		
	}
	public static void write() {
		File[] f = new File("src/resources/sounds/game/").listFiles();
		int start = 8;
		for(int i=1;i<f.length+1;i++) {
			Sound s = new Sound("00000"+start+i, FileUtil.getResource("resources/sounds/game/Game ("+i+").mp3"), 
					"Game ("+i+")", "Minecraft", "Dunemask","/game/Game ("+i+").mp3");
		
			SoundHandler.editXML(s,"/game/Game ("+i+")");
		}
	}



}
