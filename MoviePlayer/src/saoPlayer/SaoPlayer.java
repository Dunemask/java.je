/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: saoPlayer.SaoPlayer.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package saoPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import dunemask.dunemasking.GitHub;
import dunemask.objects.movieplayer.MovieLauncher;
import dunemask.objects.movieplayer.MoviePlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.media.Media;

/**
 * @author Elijah
 *
 */
public class SaoPlayer {

	static ArrayList<File> files = new ArrayList<File>();
	static int playing = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = GitHub.gitFile("Saomp4","episode/1.mp4");
		files.add(file);
		
		MovieLauncher.startPlayer(file,true);
		
		
		Thread getFiles = new Thread( new Runnable() {

			@Override
			public void run() {
				for(int i=2;i<10;i++) {

				File file = GitHub.gitFile("Saomp4","episode/"+i+".mp4");
				files.add(file);
				System.out.println("Added Episode"+i);
				}
				
				for(int i=1;i<3;i++) {
					File file = GitHub.gitFile("Saomp4","episode/10-"+i+".mp4");
					files.add(file);
					System.out.println("Added Episode 10-"+i);
				}
				for(int i=1;i<=3;i++) {
					File file = GitHub.gitFile("Saomp4","episode/11-"+i+".mp4");
					files.add(file);
					System.out.println("Added Episode 11-"+i);
				}
				files.add(GitHub.gitFile("Saomp4","episode/12.mp4"));
				System.out.println("Added Episode 12");
			}
			
			
		});
		getFiles.start();

		
		
		try {
			MovieLauncher.current.getPlayerReady().await();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		MovieLauncher.current.getRepeatButton().setDisable(true);
		
		MovieLauncher.frame.setTitle(MovieLauncher.frame.getTitle()+" - "+files.get(playing).getName());
		
		MovieLauncher.current.getBackButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evt) {
				if(playing-1>=0) {
					MovieLauncher.current.changeMedia(new Media((files.get(playing-1).toURI().toString())));
					playing--;
					MovieLauncher.frame.setTitle(MovieLauncher.frame.getTitle()+" - "+files.get(playing).getName());
				}
				
			}
			
		}
		);
		
		
		
		
		MovieLauncher.current.getForwardButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evt) {
				if(playing+1<files.size()) {
					MovieLauncher.current.changeMedia(new Media((files.get(playing+1).toURI().toString())));
					playing++;
					MovieLauncher.frame.setTitle(MovieLauncher.frame.getTitle()+" - "+files.get(playing).getName());
				}
				
			}
			
		}
		);
		
		MovieLauncher.current.getMediaPlayer().setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				MovieLauncher.current.getForwardButton().getOnAction().handle(null);
				
			}
			
		});
		
		
		
		/*for(int i=0;i<files.size();i++) {
			
			MovieLauncher.current.changeMedia(new Media((files.get(i).toURI().toString())));
		}*/
		
		
	/*	for(int i=2;i<10;i++) {
			try {
				MoviePlayer.mediaFinished.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Changing");
			MoviePlayer.changeMedia(GitHub.gitFile("Saomp4","episode/"+i+".mp4"));
		}
		
		for(int i=0;i<=2;i++) {
			try {
				MoviePlayer.mediaFinished.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Changing");
			MoviePlayer.changeMedia(GitHub.gitFile("Saomp4","episode/10-"+i+".mp4"));
		}
		
		for(int i=0;i<=3;i++) {
			try {
				MoviePlayer.mediaFinished.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Changing");
			MoviePlayer.changeMedia(GitHub.gitFile("Saomp4","episode/11-"+i+".mp4"));
		}
		MoviePlayer.changeMedia(GitHub.gitFile("Saomp4","episode/12.mp4"));*/
		
		
		
		
		

	}

}
