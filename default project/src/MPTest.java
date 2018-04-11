import java.io.File;

import dunemask.objects.DMediaPlayer;
import dunemask.util.FileUtil;

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
		File f = FileUtil.getResource("This Is War.mp3");
		DMediaPlayer p = new DMediaPlayer();
		p.init();
		p.SetSong(f);
		p.getMediaPlayer().play();

	}

}
