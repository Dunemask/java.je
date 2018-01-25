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

import java.io.File;

import dunemask.dunemasking.GitHub;
import dunemask.objects.movieplayer.MovieLauncher;

/**
 * @author karib
 *
 */
public class MoviePlayerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//File file = FileUtil.getResource("C:\\Users\\Elijah\\Downloads\\Bye Bye Miss American Pie.mp3");
		//File file = GitHub.gitFile("tmp","resources/media/mp4/Coming Home.mp4");
		String name = "Two Steps From Hell - Never Give Up On Your Dreams.mp3";
		File file = GitHub.gitFile("dunemask.github.io","resources/media/mp3/"+name);
		
		MovieLauncher.startPlayer(file, true);
		try {
			MovieLauncher.current.getPlayerReady().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		/*
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
		MovieLauncher.frame.setContentPane(p);*/
		
	}

}
