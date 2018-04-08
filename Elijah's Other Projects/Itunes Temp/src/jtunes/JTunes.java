package jtunes;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;

import dunemask.util.xml.XMLMap;
import panels.MainFrame;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class JTunes {

	public static String JTunesFolder = System.getProperty("user.home")+"/Music/JTunes/";
	public static XMLMap library;
	public static JFrame cf;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File xml = new File(JTunes.JTunesFolder+"Library.xml");
		JTunes.library = new XMLMap(xml);
		cf = new MainFrame();
		cf.setVisible(true);
		ArrayList<String> path = new ArrayList<String>(Arrays.asList(new String[] {"Library","30 Seconds To mars","Unknown Album","Song Info","Name"}));
		//System.out.println(library.getValueByPath(library.getParentByState("Name")));
		//System.out.println(library.getValueByPath(path));
		HashMap<String,ArrayList<String>> map =library.getAllSubComponents(library.getParentByState("Library"));
														//Direct
		
		/*File file = new File(System.getProperty("user.home")+"/Desktop/tmp.xml");
		XMLMap xml = new XMLMap(file,"File");
		xml.addContainerWithUID("Cookie", xml.lastParent(), "Alpha");
		xml.addElementWithUID("Chocolate Chip", xml.getParentByState("Alpha"), 7, "ChocoChip");
		xml.addElement("White Macadamia Nut", xml.getParentByState("Alpha"), 8);
		System.out.println(xml.getParentByState("White Macadamia Nut"));
		System.out.println(xml.getUid(xml.getParentByState("White Macadamia Nut")));*/

	}
	public static ArrayList<String> findArtists(String search){
		ArrayList<String> art = new ArrayList<String>();
		HashMap<String,String> mp = library.getElementsAndKeys(library.getParentByState("Library"));
		ArrayList<String> keys = new ArrayList<String>(mp.keySet());
		for(int i=0;i<mp.keySet().size();i++) {
			//if(keys.get(i).equalsIgnoreCase("Name")) {
				//System.out.println(mp.get(keys.get(i)));
				//System.out.println(keys.get(i));
			//}
			
		}
		
		
		/*ArrayList<String> full = library.getSubComponents(library.getParentByState("Library"));
		for(int i=0;i<full.size();i++) {
			if(StringUtil.containsIgnoreCase(full.get(i), search)) {
				art.add(full.get(i));
			}
		}*/
		return art;
	}
	public static ArrayList<String> findSongs(String search){
		ArrayList<String> art = new ArrayList<String>();
		HashMap<String, ArrayList<String>> full = library.getSubComponents(library.getParentByState("Library"));
		for(int i=0;i<full.size();i++) {
			
		}
		return art;
	}

	
	
	public static void addSong(String art,String alb,String name,File file) {
		JTunes.library.addContainer(art, JTunes.library.getParentByState("Library"));
		JTunes.library.addContainer(alb, JTunes.library.getParentByState(art));
		JTunes.library.addContainerWithUID("Song Info", JTunes.library.getParentByState(alb),art+" - "+name);
		JTunes.library.addElement("Name", JTunes.library.lastParent(), name);
		JTunes.library.addElement("File", JTunes.library.lastParent(), file.toURI().toString());
	}

}
