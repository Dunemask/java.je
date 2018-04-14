/**
 * 
 */
package mplayer;

import dunemask.util.FileUtil;

/**
 * @author Dunemask
 *
 */
public class AddSong {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String rel = write(true);
		load(rel);
	}

	/**
	 * @param rel 
	 * 
	 */
	private static void load(String rel) {
		Sound s = SoundHandler.loadSong(rel);
		PlaySound.playSound(s);
	}

	/**
	 * @return 
	 * 
	 */
	private static String write(boolean write) {
		if(write) {
		String uid="126442";
		String path = "resources/sounds/etc/block_place.mp3";
		Sound s = new Sound(uid, FileUtil.getResource(path), uid, "block_place","Minecraft", "/etc/block_place.mp3");
		SoundHandler.editXML(s, "/etc/block_place/");
		}
		return "/etc/block_place/";
		
	}

}
