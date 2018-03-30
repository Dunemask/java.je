/**
 * 
 */
package dunemask.dm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dunemask.dunemasking.StreamGobbler;
import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * @author dunemask
 *
 */
public class CMD {
    final static double version = 4.8;
	
	
	
	
	/** @param in File in(must be real)
	 * 	@param out File out(must be real)
	 * @return Returns String to run in command
	 * 
	 * */
	public static String copyFileViaCmd(File in, File out) {
		return "copy \""+in.getAbsolutePath().replace("/", "\\")+"\" \""+out.getAbsolutePath().replace("/", "\\")+"\"";
		//copy a.txt tmp/a.boom      
	}
	
	/** @param String path (Abs)
	 * 
	 */
	public static String createDirCmd(String absPath) {
		return "mkdir \""+absPath+"\"";
		
	}

	/** @param in String in(must be real)
	 * 	@param out String out(must be real)
	 * @return Returns String to run in command
	 * 
	 * */
	public static String copyFolderViaCmd(String in, String out) {
		return "copy \""+in.replace("/", "\\")+"\" \""+out.replace("/", "\\")+"\"";
		//copy a.txt tmp/a.boom      
	}
	
	
	/**@param file Name of file (including .ending)
	 * */
	public static String createFileCmdCommand(String file) {
		return "echo AutoGene >" + file;
	}
	
	
	/**
	 * @param command
	 */
	public static void openCmd(String command) {
		openCmd(new String[]{command});
		
	}
	
	/**
	 * @param commands
	 */
	public static void openCmd(ArrayList<String> commands) {
		openCmd(commands.toArray(new String[commands.size()]));
		
	}

	public static void openCmd(String[] args) {
		try {
			
			File bat = File.createTempFile("Cookie", ".bat");
			RW.write(bat, args, 1);
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add(bat.getAbsolutePath());
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
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
	 * @param command
	 */
	public static void openElevatedCmd(String command) {
		openElevatedCmd(new String[]{command});
		
	}
	
	/**
	 * @param command
	 */
	public static void openElevatedCmd(ArrayList<String> commands) {
		openElevatedCmd(commands.toArray(new String[commands.size()]));
		
	}


	public static void openElevatedCmd(String[] args) {
		try {
			
			File bat = File.createTempFile("Cookie", ".bat");
			RW.write(bat,RW.readAll(FileUtil.getResource("resources/elvate.bat")),0);
			RW.write(bat, args, FileUtil.lastLine(bat));
			ArrayList<String> commands = new  ArrayList<String>();
			commands.add(bat.getAbsolutePath());
			commands.add("pause");
			commands.add("exit");
			ProcessBuilder pb = new ProcessBuilder(commands);
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
}
