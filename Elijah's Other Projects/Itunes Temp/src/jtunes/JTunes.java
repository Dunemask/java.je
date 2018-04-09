package jtunes;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;

import dunemask.objects.ArrayListState;
import dunemask.util.MediaUtil;
import dunemask.util.StringUtil;
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
		
		HashMap<String,ArrayList<String>> map =library.getSubComponents(library.getParentByState("Library"));
													//Direct
		ArrayList<String> key = new ArrayList<String>(map.keySet());
		for(int i=0;i<key.size();i++) {
			HashMap<String,ArrayList<String>> m =library.getAllSubComponents(map.get(key.get(i)));
			ArrayList<String> mkey = new ArrayList<String>(m.keySet());
			for(int c=0;c<mkey.size();c++) {
				ArrayList<String> paf = m.get(mkey.get(c));
			}
			
			
		}
		/*File file = new File(System.getProperty("user.home")+"/Desktop/tmp.xml");
		XMLMap xml = new XMLMap(file,"File");
		xml.addContainerWithUID("Cookie", xml.lastParent(), "Alpha");
		xml.addElementWithUID("Chocolate Chip", xml.getParentByState("Alpha"), 7, "ChocoChip");
		xml.addElement("White Macadamia Nut", xml.getParentByState("Alpha"), 8);
		System.out.println(xml.getParentByState("White Macadamia Nut"));
		System.out.println(xml.getUid(xml.getParentByState("White Macadamia Nut")));*/

	}
	

	
	
	public static void addSong(String art,String alb,String name,File file) {
		JTunes.library.addContainer(art, JTunes.library.getParentByState("Library"));
		JTunes.library.addContainer(alb, JTunes.library.getParentByState(art));
		JTunes.library.addContainerWithUID("Song Info", JTunes.library.getParentByState(alb),art+" - "+name);
		JTunes.library.addElement("Name", JTunes.library.lastParent(), name);
		JTunes.library.addElement("File", JTunes.library.lastParent(), file.toURI().toString());
	}

	/**
	 * @param text
	 * @return
	 */
	public static ArrayList<String> findArtists(String need) {
		ArrayList<String> match = new ArrayList<String>();
		HashMap<String,ArrayList<String>> map =library.getSubComponents(library.getParentByState("Library"));
		ArrayList<String> key = new ArrayList<String>(map.keySet());
		for(int i=0;i<key.size();i++) {
			if(StringUtil.containsIgnoreCase(key.get(i), need)) {
				match.add(key.get(i));
			}
		}
		return match;
	}


	
	/**
	 * @param text
	 * @return
	 */
	public static ArrayList<JSong> findSongs(String need) {
		ArrayList<JSong> match = new ArrayList<JSong>();
		HashMap<String,ArrayList<String>> map =library.getSubComponents(library.getParentByState("Library"));
		ArrayList<String> key = new ArrayList<String>(map.keySet());
		for(int i=0;i<key.size();i++) {
			HashMap<String,ArrayList<String>>  subMap = library.getSubComponents(map.get(key.get(i)));
			ArrayList<String> subKey = new ArrayList<String>(subMap.keySet());
			for(int c=0;c<subKey.size();c++) {
				HashMap<String,ArrayList<String>>  subSubMap = library.getSubComponents(subMap.get(subKey.get(c)));
				ArrayList<String> subSubKey = new ArrayList<String>(subSubMap.keySet());
				for(int b=0;b<subSubKey.size();b++) {
					ArrayList<String> p = subSubMap.get(subSubKey.get(b));
					ArrayList<String> fp = new ArrayList<String>(p);
					ArrayList<String> fname = new ArrayList<String>(p);
					fp.add("File");
					fname.add("Name");
					String furi = library.getElementFromDoc(fp);
					String name = library.getElementFromDoc(fname);
					if(StringUtil.containsIgnoreCase(name, need)) {
						try {
							match.add(new JSong(new File(new URI(furi)),name));
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			
		}
		
		
		return match;
	}

}
