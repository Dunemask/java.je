/**
 * 
 */
package mplayer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Dunemask
 *
 */
public class SoundEngine {
	final public static int game=1;
	final static int gameWaitTime = 16000;
	final public static int title=0;
	final static int titleWaitTime = 12000;
	public static boolean titleRun = false;
	public static boolean gameRun = false;
	
	
	public static void handle(String type) {
		switch(type) {
		case "click":
			Sound s  =SoundHandler.loadSong("/etc/click");
			PlaySound.playOverSound(s);
			break;
	
		default:
			
			System.err.println("Invalid Sound Engine");
			System.exit(1);
			break;
		
		}
	}
	public static void stop(int engine) {
		
		switch(engine) {
		case SoundEngine.title:
			titleRun=false;
			break;
		case SoundEngine.game:
			gameRun=false;
			break;
		
			
		default:
			System.err.println("Invalid Sound Engine");
			System.exit(1);
			break;
		}
	}
	
	public static void start(int engine) {
		
		switch(engine) {
		case SoundEngine.title:
			titleRun=true;
			titleEngine();
			break;
		case SoundEngine.game:
			gameRun=true;
			gameEngine();
			break;
		
			
		default:
			System.err.println("Invalid Sound Engine");
			System.exit(1);
			break;
		}
	}


	/**
	 * 
	 */
	private static void titleEngine() {
		new Thread ( new Runnable() {

			@Override
			public void run() {
				Random r = new Random();
				while(titleRun) {
				String song = Sound.menuSong[r.nextInt(4)];
				Sound s = SoundHandler.loadSong(song);
				Media m = PlaySound.playSound(s);	
				MediaPlayer tmp = new MediaPlayer(m);
				CountDownLatch pReady = new CountDownLatch(1);
				tmp.setOnReady(()->{
					pReady.countDown();
				});
				//Wait until ready
				try {
					pReady.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//Play
				long waitTime =Math.round(m.getDuration().toMillis());
				CountDownLatch latch = new CountDownLatch(1);
				try {
					latch.await(waitTime, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				

				try {
					Thread.sleep(r.nextInt(SoundEngine.titleWaitTime));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				}
				
			}
			
		}).start();;
		
		
		
	}
	/**
	 * 
	 */
	private static void gameEngine() {
		new Thread ( new Runnable() {

			@Override
			public void run() {
				Random r = new Random();
				while(gameRun) {
				String song = Sound.gameSong()[r.nextInt(Sound.gameSong().length)];
				Sound s = SoundHandler.loadSong(song);
				Media m = PlaySound.playSound(s);	
				MediaPlayer tmp = new MediaPlayer(m);
				CountDownLatch pReady = new CountDownLatch(1);
				tmp.setOnReady(()->{
					pReady.countDown();
				});
				//Wait until ready
				try {
					pReady.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//Play
				long waitTime =Math.round(m.getDuration().toMillis());
				CountDownLatch latch = new CountDownLatch(1);
				try {
					latch.await(waitTime, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				

				try {
					Thread.sleep(r.nextInt(SoundEngine.titleWaitTime));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				}
				
			}
			
		}).start();;
		
		
		
	}
	
	
}