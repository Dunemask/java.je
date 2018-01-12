/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: dunemask.dunemasking.GitHubStation.java
 * Version: 0.3
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package dunemask.dunemasking;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import dunemask.util.FileUtil;
import dunemask.util.RW;


/**<p>Add/Remove files from temp: {@link dunemask.dunemasking.GitHubStation#Start()}</p>
 *<p>Get File From github online temp: {@link dunemask.dunemasking.GitHubStation#gitFile(String)}</p>
 * 
 * @author Elijah
 *
 */
public class GitHubStation {
	public static InputStream stream = System.in;
	public static 	String repPath;
	public static Scanner sysin = new Scanner(System.in);
	/**Version*/
    final static double version = 4.1;
	
	/**
	 * Main Entity that opens a station
	 */
	public static void Start() {
		setup();
		boolean run = true;
	while(run) {
		System.out.println("1- Add File");
		System.out.println("2 - Remove File");
		System.out.println("3 - Quit");
		switch(Integer.parseInt(sysin.nextLine())) {
		case 1: addFile();
			break;
		case 2: removeFile();
			break;
		default: run = false;
			break;
		
		}
		
	}
	}
	
	
	/** Get file From Temp repository on github
	 * @param genericPath Relative path to file from github tmp repository
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFile(String genericPath) {
		URL url = null;
		try {
			url = new URL("https://github.com/Dunemask/tmp/raw/master/"+genericPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		File webFile = null;
		try {
		webFile = FileUtil.getWebFile(url);
		}catch(Exception e) {
			System.err.println("Resource not found at:"+url.toString());
			e.printStackTrace();
		}	
		File file = webFile;
		return file;
		
	}


	/**
	 * 
	 */
	private static void removeFile() {
		Frame frame = new Frame();
		frame.setVisible(true);
		FileDialog fd = new FileDialog(frame);
		fd.setAlwaysOnTop(true);
		fd.setMultipleMode(true);
		fd.setDirectory(repPath+"tmp/");
		fd.setVisible(true);
		
		File[] chosen = fd.getFiles();
		frame.dispose();
		for(File f:chosen) {
			f.delete();
		}
		
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Push.bat");
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	private static void addFile() {
		format(Add);
		File addbat =FileUtil.getResource("dunemask/resources/github/Add.bat");;
		RW.write(addbat, "cd "+repPath+"tmp", 1);
		System.out.println(RW.read(addbat, 1));
		FileUtil.writeFile(addbat, new File(repPath+"Add.bat"));
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Add.bat");
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	private static void setup() {
	repPath = getDocumentsFileLocation()+"\\reps\\";
		File repFolder = new File(repPath);
		repFolder.mkdirs();
		RW.write(FileUtil.getResource("dunemask/resources/github/Create.bat"), "cd "+repPath, 1);
		RW.write(FileUtil.getResource("dunemask/resources/github/Push.bat"), "cd "+repPath+"tmp/", 1);
		
		FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Create.bat"), new File(repPath+"Create.bat"));
		FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Push.bat"), new File(repPath+"Push.bat"));
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Create.bat");
			p.waitFor();

		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	final static int Add = 1;
	/**
	 * @param arg int for choice
	 */
	private static void format(int arg) {
		File format = null;
		switch(arg) {
		case Add: format = FileUtil.getResource("dunemask/resources/github/Format.add.txt");
			Frame frame = new Frame();
			frame.setVisible(true);
			FileDialog fd = new FileDialog(frame);
			fd.setAlwaysOnTop(true);
			fd.setDirectory(repPath);
			fd.setVisible(true);
			File chosen = fd.getFiles()[0];
			frame.dispose();
			System.out.println("Custom Path: (Leave Blank For default and end with a /)");
			File outFile = new File(repPath+"tmp\\"+sysin.nextLine()+chosen.getName());
			FileUtil.writeFile(chosen, outFile);
			//String fileName = outFile.getAbsolutePath();
			//Store lines
			String[] lines = RW.read(format, 1, FileUtil.linesInFile(format));
			File out = FileUtil.getResource("dunemask/resources/github/Add.bat");
			RW.write(out, lines, 0);
			break;
		}
	}

	/**
	 * @return documents without /
	 */
	private static String getDocumentsFileLocation() {
		String myDocuments = null;

		try {
		    Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
		    p.waitFor();

		    InputStream in = p.getInputStream();
		    byte[] b = new byte[in.available()];
		    in.read(b);
		    in.close();

		    myDocuments = new String(b);
		    myDocuments = myDocuments.split("\\s\\s+")[4];

		} catch(Throwable t) {
		    t.printStackTrace();
		}
	
		return myDocuments;
	}

}
