/**
 * 
 */
package fetch;

import java.io.File;

import javax.swing.JOptionPane;

import dunemask.dunemasking.GitHub;
import dunemask.util.FileUtil;
import dunemask.util.JarUtil;

/**
 * @author dunemask
 *
 */
public class FetchJar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Program Fetchs Jar From Web and Downloads it to File right next to this one
		JOptionPane.showMessageDialog(null, "Don't touch the file being downloaded");
		File f=GitHub.gitFile("dunemask.github.io", "dunemasking/Run.jar");
		FileUtil.writeFile(f, new File(JarUtil.getProgramPath()+"Run.jar"));
		JOptionPane.showMessageDialog(null, "Done!");

	}

}
