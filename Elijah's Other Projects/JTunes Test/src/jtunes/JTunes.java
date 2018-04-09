package jtunes;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import dunemask.util.FileUtil;
import dunemask.util.xml.XMLMap;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;

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
		String ext = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
		new File(JTunes.JTunesSongPath+artist+"/"+album+"/").mkdirs();
		File fo = new File(JTunes.JTunesSongPath+artist+"/"+album+"/"+title+ext);
		FileUtil.writeFile(file, fo);
		library.addElement("File", library.lastParent(), fo.toURI().toString());
		
	}
	public static ArrayList<JSong>


	

}
