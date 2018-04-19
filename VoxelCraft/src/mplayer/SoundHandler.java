/**
 * 
 */
package mplayer;

import java.io.File;

import dunemask.util.xml.RuneMap;
import mc.ResourceHandler;

/**
 * @author Dunemask
 *
 */
public class SoundHandler {
//sfasdf
	//TODO
	/** Load Song from the resources
	 * @param relPath Song Path
	 * @return A sound to be played with PlaySound
	 * */
	public static Sound loadSong(String relPath) {
		RuneMap index = ResourceHandler.soundmap;
		String rp = relPath;
		if(String.valueOf(relPath.charAt(0)).equals("/")) {
			rp = rp.replaceFirst("/", "");
		}
		if(relPath.endsWith("/")) {
			rp = rp.substring(0, rp.length()-1);
	
		}
		String id = index.pullValue("Sounds/"+rp+"/ID");
		String title = index.pullValue("Sounds/"+rp+"/Title");
		String artist = index.pullValue("Sounds/"+rp+"/Artist");
		String album =index.pullValue("Sounds/"+rp+"/Album");
		String relp=index.pullValue("Sounds/"+rp+"/RelPath");
		File f = null;
		Sound s = new Sound(id,f,title,album,artist,relp);
	return s;
	}
	/** Edit XML Only For Devlopment Purposes
	 * @param s Sound
	 * @param relPath path
	 * 
	 */
	public static void editXML(Sound s,String relPath) {
		String rp = relPath;
		if(String.valueOf(relPath.charAt(0)).equals("/")) {
			rp = rp.replaceFirst("/", "");
		}
		if(relPath.endsWith("/")) {
			rp = rp.substring(0, rp.length()-1);
	
		}
		RuneMap  index = ResourceHandler.soundmap;

		
		index.writeContainer("Sounds/ambient/");
		index.writeContainer("Sounds/player/");
		index.writeContainer("Sounds/title/");
		index.writeContainer("Sounds/etc/");
		index.writeContainer("Sounds/game/");
		index.writeContainer("Sounds/"+rp+"/");
		index.writeElement("Sounds/"+rp+"/ID", s.getId());
		index.writeElement("Sounds/"+rp+"/Title", s.getTitle());
		index.writeElement("Sounds/"+rp+"/Song", s.getSong().toURI().toString());
		index.writeElement("Sounds/"+rp+"/Artist", s.getArtist());
		index.writeElement("Sounds/"+rp+"/Album", s.getAlbum());
		index.writeElement("Sounds/"+rp+"/RelPath", s.getSoundsRelPath());
		//Write Both
		index = RuneMap.ParseDXMLMap(new File("bin/resources/sounds/Index.xml"));
		index.writeContainer("Sounds/ambient/");
		index.writeContainer("Sounds/player/");
		index.writeContainer("Sounds/title/");
		index.writeContainer("Sounds/game/");
		index.writeContainer("Sounds/etc/");
		index.writeContainer("Sounds/"+rp+"/");
		index.writeElement("Sounds/"+rp+"/ID", s.getId());
		index.writeElement("Sounds/"+rp+"/Title", s.getTitle());
		index.writeElement("Sounds/"+rp+"/Song", s.getSong().toURI().toString());
		index.writeElement("Sounds/"+rp+"/Artist", s.getArtist());
		index.writeElement("Sounds/"+rp+"/Album", s.getAlbum());
		index.writeElement("Sounds/"+rp+"/RelPath", s.getSoundsRelPath());
	}
	
}
