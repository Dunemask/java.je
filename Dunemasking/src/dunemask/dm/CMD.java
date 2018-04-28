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
	
	
	
	public static ArrayList<String> copyFolderAndSubContetntsViaCmd(String folderIn,String folderOut) {
		ArrayList<String> cmd = new ArrayList<String>();
		File homeDir = new File(folderIn);
		File in = null,out = null;
		File[] files = FileUtil.getAllSubFiles(homeDir);
		for(int i=0;i<files.length;i++) {
			String tmp;
			String relPath = FileUtil.filePathFix(files[i].getAbsolutePath()).replace(FileUtil.filePathFix(folderIn), "");
			//System.out.println("RelPath:"+(files[i].getAbsolutePath().replace(folderIn, "")));
			if(files[i].isDirectory()) {
				tmp = mkdirAbs(folderOut+relPath);
			}else {
				in = files[i];
				out = new File(folderOut+relPath);
				tmp = copyFileViaCmd(in,out);
			}
			cmd.add(tmp);
		}
		
		
		
		
		return cmd;
	}
	/** Delete file
	 * @return del file thing
	 * 
	 * */
	public static String delFile(String path) {
		if(!path.startsWith("\"")) {
			path = "\""+path;
		}
		if(!path.endsWith("\"")) {
			path = path+"\"";
		}
		return "del "+path;

	}
	
	
	
	/** Creates Relative Directory
	 * */
	public static String mkdir(String dir) {
		return "mkdir "+dir;		
	}
	/** Creates Absolutee Directory
	 * */
	public static String mkdirAbs(String dir) {
		return "mkdir "+absolutePathFixCmd(dir);		
	}
	
	public static String absolutePathFixCmd(String command) {
		return "\""+command+"\"";
	}
	
    
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
