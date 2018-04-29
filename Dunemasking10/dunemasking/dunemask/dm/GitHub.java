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
package dunemask.dm;

import java.io.File;

import dunemask.util.FileUtil;

/**
 * @author karib
 *
 */
public class GitHub {
	public static String repPath;
	public static String lastAddedFilePath;
	/**Version*/
    final static double version = 4.5;
    

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
    
    
    
    	
   





	

}
