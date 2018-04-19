/**
 * 
 */
package mplayer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import dunemask.objects.DMediaPlayer;
import dunemask.util.FileUtil;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mc.ResourceHandler;

/**
 * @author Dunemask
 *
 */
public class PlaySound {
	private static boolean init = false;
	public static boolean overlapSongs = false;
	/**0.0-1.0
	 * */
	public static double vol=1;
	/** Plays a sound and doesn't blow up playing sounds
	 * @param sound Sound
	 * 
	 * */
	public static MediaPlayer playOverSound(Sound sound) {
		init();
		try {
			String rp = sound.getSoundsRelPath();
			if(String.valueOf(rp.charAt(0)).equals("/")) {
				rp = rp.replaceFirst("/", "");
			}
			if(rp.endsWith("/")) {
				rp = rp.substring(0, rp.length()-1);
		
			}
			String sp = ResourceHandler.soundmap.pullValue("Sounds/relpath");
			URL url = null;
			if(sp!=null) {
				url = FileUtil.getResourceURL(sp+rp);
			}else {
				url = FileUtil.getResourceURL("resources/sounds/"+rp);
			}
			if(!new File(url.getPath()).exists()) {
				url = FileUtil.getResourceURL("resources/sounds/"+rp);
			}
			
				

			URI ur = new URI(url.toString().replace(" ", "%20"));
			Media m = new Media(ur.toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.setVolume(vol);
			new Thread(new Runnable(){

				@Override
				public void run() {
					mp.play();
					
				}
				
			}).start(); 
			return mp;
			
		} catch (URISyntaxException e) {
			System.err.println("MEDIA DON'T EXIST -DM");
			e.printStackTrace();
			return null;

		}

	}
	
	
	/** Plays a sound and doesn't blow up playing sounds
	 * @param sound Sound
	 * 
	 * */
	public static MediaPlayer playOverSound(Sound sound,double volume) {
		init();
		try {
			String rp = sound.getSoundsRelPath();
			if(String.valueOf(rp.charAt(0)).equals("/")) {
				rp = rp.replaceFirst("/", "");
			}
			if(rp.endsWith("/")) {
				rp = rp.substring(0, rp.length()-1);
		
			}

			String sp = ResourceHandler.soundmap.pullValue("Sounds/relpath");
			URL url = null;
			if(sp!=null) {
				url = FileUtil.getResourceURL(sp+rp);
			}else {
				url = FileUtil.getResourceURL("resources/sounds"+rp);
			}
			if(!new File(url.getPath()).exists()) {
				url = FileUtil.getResourceURL("resources/sounds/"+rp);
			}
			URI ur = new URI(url.toString().replace(" ", "%20"));
			Media m = new Media(ur.toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.setVolume(volume);
			new Thread(new Runnable(){

				@Override
				public void run() {
					mp.play();
					
				}
				
			}).start(); 
			return mp;
			
		} catch (URISyntaxException e) {
			System.err.println("MEDIA DON'T EXIST -DM");
			e.printStackTrace();
			return null;

		}

	}
	
	
	
	/**  
	 * @param s Sound
	 * 
	 * */
	public static Media playSound(Sound s) {
		init();
		if(DMediaPlayer.getMediaPlayer()!=null&&overlapSongs==false) {//If MediaPlayer null we need to init
			DMediaPlayer.getMediaPlayer().stop();
		}
		try {
			String rp = s.getSoundsRelPath();
			if(String.valueOf(rp.charAt(0)).equals("/")) {
				rp = rp.replaceFirst("/", "");
			}
			if(rp.endsWith("/")) {
				rp = rp.substring(0, rp.length()-1);
		
			}
			String sp = ResourceHandler.soundmap.pullValue("Sounds/relpath");
			URL url = null;
			if(sp!=null) {
				url = FileUtil.getResourceURL(sp+rp);
			}else {
				System.out.println("Couldn't find:"+rp);
				url = FileUtil.getResourceURL("resources/sounds/"+rp);
			}
			if(!new File(url.getPath()).exists()) {
				url = FileUtil.getResourceURL("resources/sounds/"+rp);
			}
			
			
			URI ur = new URI(url.toString().replace(" ", "%20"));
			DMediaPlayer.setMedia(ur.toString());
		} catch (URISyntaxException e) {
			System.err.println("MEDIA DON'T EXIST -DM");
			e.printStackTrace();
		}
		new Thread( new Runnable() {

			@Override
			public void run() {
				DMediaPlayer.getMediaPlayer().setVolume(vol);
				DMediaPlayer.getMediaPlayer().play();
				
			}

		}).start();
		return DMediaPlayer.getPlayingMedia();
		
	}






	/**
	 * 
	 */
	public static void init() {
		if(!init) {
			vol=1.0;
			DMediaPlayer.init();
			init=true;
			//if(ResourceHandler.soundmap==null) {
			//	ResourceHandler.soundmap=RuneMap.ParseDXMLMap(FileUtil.getResource("resources/sounds/Index.xml"));
			//}
		}
		
	}
	
	
	
	
	
	

}
