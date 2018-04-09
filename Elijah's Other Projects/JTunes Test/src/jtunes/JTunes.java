package jtunes;

import java.io.File;

import dunemask.util.xml.XMLMap;

public class JTunes {
	public static XMLMap library;
	public static String JTunesPath = System.getProperty("user.home")+"/Music/JTunes/";
	
	public static void main(String[] args) {
		new File(JTunesPath).mkdirs();
		

	}

}
