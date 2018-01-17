/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: application.Run.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package application;

import java.io.File;

import dunemask.objects.movieplayer.MoviePlayer;
import dunemask.util.FileUtil;

/**
 * @author Elijah
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File lit = FileUtil.getResource("resources/SaoAbridged9.mp4");
		MoviePlayer.startPlayer(null,null);

	}

}
