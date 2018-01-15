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
import java.util.ArrayList;
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
	public static String relPath;
	/**Version*/
    final static double version = 4.1;
	
	/**
	 * Main Entity that opens a station
	 */
	public static void Start() {
		Capture.startConsole();
		setup();
		boolean run = true;
	while(run) {

		switch(Integer.parseInt(Capture.getInput("1 - Add File, 2 - Rmv File, 3 - Quit Git"))) {
		case 1: addFile();
			break;
		case 2: removeFile();
			break;
		default: run = false;
			break;
		
		}
		
	}
	
	System.out.println("GitHubStation has been Closed - Thanks For using!");
	System.out.println();
	Capture.closeConsole();
	}
	
	
	/** Get file From Temp repository on github
	 * @param genericPath Relative path to file from github tmp repository
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFile(String genericPath) {
		String url = "https://github.com/Dunemask/tmp/raw/master/"+genericPath;
		
		File webFile = null;
		try {
		webFile = FileUtil.getWebFile(url);
		}catch(Exception e) {
			System.err.println("Resource not found at: "+url);
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
		fd.setDirectory(new File(repPath+"tmp/").getAbsolutePath());
		fd.setVisible(true);
		
		File[] chosen = fd.getFiles();
		frame.dispose();
		for(File f:chosen) {
			f.delete();
		}
		
		try {
			/*Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Push.bat");
			p.waitFor();*/
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add(repPath+"Push.bat");
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
		//	pb.redirectError(Redirect.INHERIT);
		//	pb.redirectOutput(Redirect.INHERIT);

			Process p = pb.start();		
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
			errorGobbler.start();
			outputGobbler.start();
			p.waitFor();
			p.destroy();
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
		RW.write(addbat, "cd "+repPath+"tmp", 2);
		System.out.println(RW.read(addbat, 1));
	///	RW.write(addbat, "pause", FileUtil.linesInFile(addbat));
		FileUtil.writeFile(addbat, new File(repPath+"Add.bat"));
		try {
			System.out.println("Adding...");
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add(repPath+"Add.bat");
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
		//	pb.redirectError(Redirect.INHERIT);
		//	pb.redirectOutput(Redirect.INHERIT);
			Process p = pb.start();
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
			errorGobbler.start();
			outputGobbler.start();
			
			p.waitFor();
			p.destroy();
			/*
			Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Add.bat");
			p.waitFor();*/
			
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	private static void setup() {
		engage();
	repPath = getDocumentsFileLocation()+"\\reps\\";
		File repFolder = new File(repPath);
		repFolder.delete();
		repFolder.mkdirs();
		System.out.println("Createing Fake Rep In "+repFolder.getAbsolutePath());
		RW.write(FileUtil.getResource("dunemask/resources/github/Create.bat"), "cd "+repPath, 2);
		RW.write(FileUtil.getResource("dunemask/resources/github/Push.bat"), "cd "+repPath+"tmp/", 2);
		
		FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Create.bat"), new File(repPath+"Create.bat"));
		FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Push.bat"), new File(repPath+"Push.bat"));
		try {
		/*	Process p = Runtime.getRuntime().exec("cmd /c start "+repPath+"Create.bat");
			p.waitFor();*/
			System.out.println("Setting Up");
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add(repPath+"Create.bat");
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
			//pb.redirectError(Redirect.INHERIT);
		//	pb.redirectOutput(Redirect.INHERIT);
	//		pb.redirectOutput(new File(repPath+"Log.txt")); 
	//		pb.redirectError(new File(repPath+"Log.txt"));
			Process p = pb.start();
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
			errorGobbler.start();
			outputGobbler.start();
			
			p.waitFor();
			p.destroy();
			

		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	private static void engage() {
	  
		
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
			fd.setDirectory(new File(System.getProperty("user.home") + "/Desktop/").getAbsolutePath());
		//	System.out.println(fd.getDirectory() +"- Get Dir");
		//	System.out.println(repPath + "- When it was ");
			fd.setVisible(true);
			File chosen = fd.getFiles()[0];
			frame.dispose();
			//System.out.println("Custom Path: (Leave Blank For default) ");
			relPath = Capture.getInput("Custom Path: (Leave Blank For default) ");
			if(relPath.equals("")) {
				relPath = chosen.getName();
			}
			File outFile = new File(repPath+"tmp\\"+relPath);
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
