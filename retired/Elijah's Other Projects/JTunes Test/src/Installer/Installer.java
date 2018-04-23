package Installer;

import java.io.File;

import dunemask.util.xml.XMLMap;
import jtunes.JTunes;

public class Installer {

	public static void main(String[] args) {
		new File(JTunes.JTunesSongPath).mkdirs();
		File lib = new File(JTunes.JTunesPath+"Library.xml");
		XMLMap map = new XMLMap(lib,"Library");
		map.addTopElement("Info", "JTunes - A java.je program");
		map.addTopContainer("Playlist");
		JTunes.main(args);

	}

}
