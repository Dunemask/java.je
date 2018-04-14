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
	final public static int title=0;
	final static int titleWaitTime = 12000;
	public static boolean run = false;
	
	
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
	
	public static void start(int engine) {
		
		switch(engine) {
		case SoundEngine.title:
			run=true;
			titleEngine();
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
				while(run) {
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
	
	
}
