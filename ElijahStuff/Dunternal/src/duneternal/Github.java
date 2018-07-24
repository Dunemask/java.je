/**
 * 
 */
package duneternal;

import java.io.File;

import dunemask.crypto.CryptoUtil;
import dunemask.util.FileUtil;
import dunemask.util.IOUtil;

/**
 * @author Dunemask
 *
 */
public class Github {

	
	/** Get file From Dunemask Repository
	@param rep Repository Shortcut
	@param path Relative path From Root of rep
	 * */
	public static File getFile(String rep,String path) {
		String reps ="Duneternal";
		if(rep.equals("jv")) {
			reps = "java.je";
		}else if(rep.equals("dt")) {
			reps = "Duneternal";
		}
		File f =null;
		String url = "https://github.com/Dunemask/"+reps+"/raw/master/"+path;
		f = FileUtil.retrieveFile(url);
		return f;
		
		
	}
	
	/** Get sealed file from Duneternal repo
	 * @param path
	 * @return sealed file
	 * */
	public static File getSealedFile(String path) {
		File f = getFile(Default.Duneternal,path);
		//FileUtil.writeFile(f,new File(System.getProperty("user.home")+"/Desktop/tmp.file") );
		CryptoUtil.decryptFile(CryptoUtil.readPrivKey(IOUtil.FTU(new File(Default.PrivateKey))), f);
		return f;
	}
	
	
	
}
