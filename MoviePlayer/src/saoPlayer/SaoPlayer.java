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
import dunemask.objects.movieplayer.MoviePlayer;

/**
 * @author Elijah
 *
 */
public class SaoPlayer {

	static ArrayList<File> files = new ArrayList<File>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = GitHub.gitFile("Saomp4","episode/1.mp4");
		MoviePlayer.startPlayer(file,true);
		
		
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
				}
				for(int i=1;i<=3;i++) {
					File file = GitHub.gitFile("Saomp4","episode/11-"+i+".mp4");
					files.add(file);
				}
				files.add(GitHub.gitFile("Saomp4","episode/12.mp4"));
				
			}
			
			
		});
		getFiles.start();
		
		try {
			MoviePlayer.mediaFinished.await();
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		System.out.println("MOvin on");
		File second = files.get(0);
		MoviePlayer.changeMedia(second);
	
		for(int i=2;i<files.size();i++) {
			try {
				MoviePlayer.mediaFinished.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MoviePlayer.changeMedia(files.get(i));
		}
		
		
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
