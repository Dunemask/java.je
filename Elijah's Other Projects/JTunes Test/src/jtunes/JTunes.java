package jtunes;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.FileUtil;
import dunemask.util.StringUtil;
import dunemask.util.xml.XMLMap;

public class JTunes {
	public static XMLMap library;
	public static String JTunesPath = System.getProperty("user.home")+"/Music/JTunes/";
	public static String JTunesSongPath = System.getProperty("user.home")+"/Music/JTunes/Music/";
	
	public static void main(String[] args) {
		library = new XMLMap(new File(JTunes.JTunesPath+"Library.xml"));
		String path = "C:\\Users\\Dunemask\\Documents\\GitHub\\dunemask.github.io\\resources\\media\\mp3\\Two Steps From Hell - Never Give Up On Your Dreams.mp3";
		addFile(new File(FileUtil.filePathFix(path)),"Two Steps","Unleashed","Never Give Up On Your Dreams");
		

	}
	public static void addFile(File file,String artist,String album,String title) {
		//fxInit();
		//Media media = new Media(file.toURI().toString());
		//String album=(String) media.getMetadata().get("album");
		//String artist=(String) media.getMetadata().get("artist");
		//String title = (String) media.getMetadata().get("title");
		library.addContainer(artist, library.getParentByState("Library"));
		library.addContainer(album, library.getParentByState(artist));
		library.addContainer(title, library.lastParent());
		library.addElement("Title", library.lastParent(), title);
		/*String ext = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
		new File(JTunes.JTunesSongPath+artist+"/"+album+"/").mkdirs();
		File fo = new File(JTunes.JTunesSongPath+artist+"/"+album+"/"+title+ext);
		FileUtil.writeFile(file, fo);
		library.addElement("File", library.lastParent(), fo.toURI().toString());*/
		searchSongs("Nev");
		
		
	}
	
	public static ArrayList<JSong> searchSongs(String search){
		ArrayList<JSong> matches = new ArrayList<JSong>();
			HashMap<String, ArrayList<String>> map = library.getSubComponents(library.getParentByState("Library"));
			ArrayList<String> key = new ArrayList<String>(map.keySet());
			//3 Layers one for artist, album, Song
			for(int a=0;a<key.size();a++) {
				ArrayList<String> subParent = map.get(key.get(a));
				HashMap<String, ArrayList<String>> subMap = library.getSubComponents(subParent);
				ArrayList<String> subKey = new ArrayList<String>(subMap.keySet());
				for(int b=0;b<subKey.size();b++) {
					ArrayList<String> subSubParent = subMap.get(subKey.get(b));
					HashMap<String, ArrayList<String>> subSubMap = library.getSubComponents(subSubParent);
					ArrayList<String> subSubKey = new ArrayList<String>(subSubMap.keySet());
					for(int c=0;c<subSubKey.size();c++) {
						ArrayList<String> subSubSubParent = subSubMap.get(subSubKey.get(c));
						HashMap<String, ArrayList<String>> subSubSubMap = library.getSubComponents(subSubSubParent);
						ArrayList<String> subSubSubKey = new ArrayList<String>(subSubSubMap.keySet());
						for(int d=0;d<subSubSubKey.size();d++) {
							//System.out.println(subSubSubKey);
							ArrayList<String> subSubSubSubParent = subSubSubMap.get(subSubSubKey.get(d));	
							HashMap<String, ArrayList<String>> subSubSubSubMap = library.getSubComponents(subSubSubSubParent);
							ArrayList<String> subSubSubSubKey = new ArrayList<String>(subSubSubSubMap.keySet());
							if(subSubSubSubParent.get(subSubSubSubParent.size()-1).equalsIgnoreCase("Title")) {
								String val = library.getElementFromDoc(subSubSubSubParent);
								if(StringUtil.containsIgnoreCase(val,search)) {
									//System.out.println(subSubSubParent);
									ArrayList<String> tmp = new ArrayList<String>(subSubSubParent);
									tmp.add("File");
									String fval = library.getElementFromDoc(tmp);
									URI ur = null;
									try {
										ur = new URI(fval);
									} catch (URISyntaxException e) {
										e.printStackTrace();
									}
									JSong s = new JSong(new File(ur),val);
									tmp.removeAll(tmp);
									matches.add(s);
								}
								
							}
							//if(subSubSubSubKey) {
							//}
						}
					}
				}
			}
		
		
		
		
		
		return matches;
	}


	

}
