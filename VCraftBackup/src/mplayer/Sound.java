/**
 * 
 */
package mplayer;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Dunemask
 *
 */
public class Sound {
	/**Relpath to be called for loadsound*/
	public static final String[] menuSong = new String[] {"/title/menu4","/title/menu3","/title/menu2","/title/menu1"};
	/**@return arraylist of game songs called in loadsound*/
	public static String[] gameSong() {
		ArrayList<String> songs = new ArrayList<String>();
		for(int i=1;i<13;i++) {
			songs.add("/game/Game ("+i+")");
		}
		return  songs.toArray(new String[songs.size()]);
	}
	
	private String artist;
	private String album;
	private String title;
	private File song;
	private String id;
	private String soundsRelPath;

	/** @param id
	 * @param song
	 * @param title
	 * @param album
	 * @param artist
	 * @param soundsrelpath 
	 * 
	 */
	public Sound(String id,File song,String title,String album,String artist, String soundsrelpath) {
		this.setId(id);
		this.setSong(song);
		this.setTitle(title);
		this.setAlbum(album);
		this.setArtist(artist);
		this.setSoundsRelPath(soundsrelpath);
		
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the song
	 */
	public File getSong() {
		return song;
	}

	/**
	 * @param song the song to set
	 */
	public void setSong(File song) {
		this.song = song;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the soundsRelPath
	 */
	public String getSoundsRelPath() {
		return soundsRelPath;
	}

	/**
	 * @param soundsRelPath the soundsRelPath to set
	 */
	public void setSoundsRelPath(String soundsRelPath) {
		this.soundsRelPath = soundsRelPath;
	}

}
