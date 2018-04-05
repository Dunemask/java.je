/**
 * 
 */
package installer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import dunemask.util.xml.XMLMap;
import jtunes.JTunes;

/**
 * @author dunemask
 *
 */
public class Install {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new File(JTunes.JTunesFolder).mkdirs();
		new File(JTunes.JTunesFolder+"Music/").mkdirs();
		File xml = new File(JTunes.JTunesFolder+"Library.xml");
		JTunes.library = new XMLMap(xml,"Library");
		JTunes.library.addElement("Info", new ArrayList<String>(), "This is a project of Jason Roberts and Eljah Parker");
		JTunes.library.addContainers(new ArrayList<String>(Arrays.asList(new String[] {"Playlists"})),new ArrayList<String>());
		JTunes.main(args);
	}

}
