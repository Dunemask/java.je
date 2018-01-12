/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: githubtesting.Test.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package githubtesting;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;

import dunemask.util.FileUtil;
import dunemask.util.RW;


/**
 * @author Elijah
 *
 */
public class Test {
	public static InputStream stream = System.in;
	public static 	String repPath;
	public static Scanner sysin = new Scanner(System.in);
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		setup();
	while(true) {
		System.out.println("1- Add File");
		System.out.println("2 - Remove File");
		System.out.println("3 - Quit");
		switch(Integer.parseInt(sysin.nextLine())) {
		case 1: addFile();
			break;
		case 2: removeFile();
			break;
		default: System.exit(0);
			break;
		
		}
		
	}

	}
	/**
	 * 
	 */
	private static void removeFile() {
		JFileChooser jc = new JFileChooser(repPath+"tmp\\");
		jc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jc.showOpenDialog(null);
		File[] chosen = jc.getSelectedFiles();
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
		File addbat =FileUtil.getResource("githubtesting/resources/Add.bat");;
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
		RW.write(FileUtil.getResource("githubtesting/resources/Create.bat"), "cd "+repPath, 1);
		RW.write(FileUtil.getResource("githubtesting/resources/Push.bat"), "cd "+repPath+"tmp/", 1);
		
		FileUtil.writeFile(FileUtil.getResource("githubtesting/resources/Create.bat"), new File(repPath+"Create.bat"));
		FileUtil.writeFile(FileUtil.getResource("githubtesting/resources/Push.bat"), new File(repPath+"Push.bat"));
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
		case Add: format = FileUtil.getResource("githubtesting/resources/Format.add.txt");
			JFileChooser jc = new JFileChooser();
			jc.showOpenDialog(null);
			jc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			File chosen = jc.getSelectedFile();
			System.out.println("Custom Path: (Leave Blank For default and end with a /)");
			File outFile = new File(repPath+"tmp\\"+sysin.nextLine()+chosen.getName());
			FileUtil.writeFile(chosen, outFile);
			//String fileName = outFile.getAbsolutePath();
			//Store lines
			String[] lines = RW.read(format, 1, FileUtil.linesInFile(format));
			File out = FileUtil.getResource("githubtesting/resources/Add.bat");
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
