/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: temp.MoviePlayerTest.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package temp;

import java.awt.Color;
import java.awt.Container;
import java.io.File;

import javax.swing.JLabel;

import dunemask.objects.movieplayer.MovieLauncher;
import javafx.embed.swing.JFXPanel;

/**
 * @author karib
 *
 */
public class MoviePlayerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MovieLauncher.startPlayer(new File("C:/Users/karib/AppData/Local/Temp/Two Guys On A Scooter.mp4"), true);
		try {
			MovieLauncher.current.getPlayerReady().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JLabel moo = new JLabel("Moo");
		moo.setSize(moo.getPreferredSize());	
		moo.setLocation(200, 200);
		moo.setForeground(Color.pink);
		
		System.out.println(MovieLauncher.current.getMedia().getSource());
		Container p = MovieLauncher.frame.getContentPane();
		JFXPanel pan =  (JFXPanel) p.getComponent(0);
		
		pan.add(moo);
		p.remove(p.getComponent(0));
		p.add(pan);
		p.setLayout(null);
		MovieLauncher.frame.setContentPane(p);
		
	}

}
