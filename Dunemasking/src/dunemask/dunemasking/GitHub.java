/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = true
 * File: dunemask.dunemasking.GitHub.java
 * Version: 0.1
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

import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * @author karib
 *
 */
public class GitHub {
	public static String repPath;
	public static String lastAddedFilePath;
	/**Version*/
    final static double version = 4.5;
    
    /** Open GithubStation
     **/
    public static void Station(){
    	Capture.startConsole();
    	setup();
    	File[] dirFiles = FileUtil.getAllSubFiles(new File(repPath+"tmp/"));
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
    	if(changed(dirFiles)){
    	pushHub();
    	}
    	
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	Capture.closeConsole();
    	}
    
	/** Get file From Dunemask Repository
	 * @param genericPath Relative path to file from github tmp repository
	 * @param reps Repository
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFile(String reps,String genericPath) {
		String url = "https://github.com/Dunemask/"+reps+"/raw/master/"+genericPath;
		
		
		File webFile = null;
		try {
		webFile = FileUtil.getWebFile(url);
		}catch(Exception e) {
			System.err.println("Resource not found at: "+url);		
		}	
		File file = webFile;
		return file;
		
	}
	
	/** Get file From Dunemask Repository
	 * @param genericPath Relative path to file from github tmp repository
	 * @param reps Repository
	 * @param out OutFile
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFile(String reps,String genericPath,File out) {
		String url = "https://github.com/Dunemask/"+reps+"/raw/master/"+genericPath;
		try {
		out = FileUtil.getWebFile(url,out);
		}catch(Exception e) {
			System.err.println("Resource not found at: "+url);		
		}	
		return out;
		
	}
	
	/** Get file From Dunemask Repository
	 * @param genericPath Relative path to file from github tmp repository
	 * @param reps Repository
	 * @param user Github User
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFileFromUser(String reps,String genericPath,String user,File out) {
		String url = "https://github.com/"+user+"/"+reps+"/raw/master/"+genericPath;
		try {
		out = FileUtil.getWebFile(url,out);
		}catch(Exception e) {
			System.err.println("Resource not found at: "+url);		
		}	
		return out;
		
	}
	
	/** Get file From Dunemask Repository
	 * @param genericPath Relative path to file from github tmp repository
	 * @param reps Repository
	 * @param user Github User
	 * @return file From dunemask.github.tmp directory by the specified path
	 */
	public static File gitFileFromUser(String reps,String genericPath,String user) {
		String url = "https://github.com/"+user+"/"+reps+"/raw/master/"+genericPath;
		File webFile = null;
		try {
		webFile = FileUtil.getWebFile(url);
		}catch(Exception e) {
			System.err.println("Resource not found at: "+url);		
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
		frame.dispose();
		
		
	}


	/**
	 * 
	 */
	private static void addFile() {
		//Place File
		Frame frame = new Frame();
		frame.setVisible(true);
		FileDialog fd = new FileDialog(frame);
		fd.setAlwaysOnTop(true);
		fd.setDirectory(new File(System.getProperty("user.home") + "/Desktop/").getAbsolutePath());
	
		fd.setVisible(true);
		File[] chosen = fd.getFiles();
		for(File f:chosen) {
		//System.out.println("Custom Path: (Leave Blank For default) ");
		lastAddedFilePath = Capture.getInput("Custom Path For "+f.getName()+ "(Leave Blank For default) ");
		if(lastAddedFilePath.startsWith("/")||lastAddedFilePath.startsWith("\\")) {
			GitHub.lastAddedFilePath = GitHub.lastAddedFilePath.substring(1, GitHub.lastAddedFilePath.length());
		}
		
		if(lastAddedFilePath.equals("")) {
			lastAddedFilePath = f.getName();
		}
		
		if(lastAddedFilePath.endsWith("/")||lastAddedFilePath.endsWith("\\")) {
			lastAddedFilePath = lastAddedFilePath + f.getName();
		}
		
		
		File outFile = new File(repPath+"tmp\\"+lastAddedFilePath);
		FileUtil.writeFile(f, outFile);
		}
		frame.dispose();
		
		//Push It

		
		
	}

	/**
	 * @param files 
	 * @return
	 */
	private static boolean changed(File[] files) {
		boolean allSame = true;
		File[] now = FileUtil.getAllSubFiles(new File(repPath+"tmp/"));	
		if(now.length!=files.length) {
			allSame = false;	
		}
		
		
		if(allSame){
		System.out.println("You Have Not Changed The tmp Repository");
		return false;
		}else{
		System.out.println("You Have Updated The Temp Repository");
		return true;
		}
	}


	/**
	 * 
	 */
	private static void pushHub() {
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
	private static void setup() {
	
		repPath = getDocumentsFileLocation()+"\\reps\\";
		File repFolder = new File(repPath);
		if((repFolder.exists()&&!new File(repPath+"/tmp/").exists())||!repFolder.exists()){
			repFolder.delete();
			repFolder.mkdirs();
			System.out.println("Createing Fake Rep In "+repFolder.getAbsolutePath());
			
			FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Create.bat"), new File(repPath+"Create.bat"));
			FileUtil.writeFile(FileUtil.getResource("dunemask/resources/github/Push.bat"), new File(repPath+"Push.bat"));
			RW.write(new File(repPath+"Create.bat"), "cd "+repPath, 2);
			RW.write(new File(repPath+"Push.bat"), "cd "+repPath+"tmp/", 2);
			
			try{
				System.out.println("Setting Up");
				ArrayList<String> commands = new  ArrayList<String>();
				commands.add(repPath+"Create.bat");
				commands.add("exit");
				ProcessBuilder pb = new ProcessBuilder(commands);
				Process p = pb.start();
				StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
				StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
				errorGobbler.start();
				outputGobbler.start();	
				p.waitFor();
				} catch (IOException | InterruptedException e) {
				
					e.printStackTrace();
				}
			
			
		}else if(new File(repPath+"/tmp/.git/").exists()){
		System.out.println("PUlling");
			try{
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add("cd "+repPath+"/tmp/");
			commands.add("git pull");
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
			Process p = pb.start();
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
			errorGobbler.start();
			outputGobbler.start();	
			p.waitFor();
		}catch(Exception e){
			
		}
			
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
