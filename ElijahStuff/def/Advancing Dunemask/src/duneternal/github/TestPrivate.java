/**
 * 
 */
package duneternal.github;

import java.io.File;
import java.net.MalformedURLException;

import dunemask.util.FileUtil;
import dunemask.util.RW;
import duneternal.Github;

/**
 * @author Dunemask
 *
 */
public class TestPrivate {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		File f = Github.getSealedFile("Media/Docs/cb.txt");
		System.out.println(RW.readAll(f.toURI().toURL()));
		//File out = new File(System.getProperty("user.home")+"/Desktop/tmp.jpg");
		//FileUtil.writeFile(f, out);
		

	}

}
