/**
 * 
 */
package dunemask.dunemasking;

import java.io.File;
import java.io.IOException;

import dunemask.util.FileUtil;
import dunemask.util.JarUtil;
import dunemask.util.RW;

/**
 * SetsUp Program call the Setup.init function
 * <p>Init: {@link dunemask.dunemasking.Setup#init(int, String, String)}</p>
 *  <p>Print Main Setup: {@link dunemask.dunemasking.Setup#mainSetup()}</p>
 *  <p>Print Dunemasking Credits: {@link dunemask.dunemasking.Setup#DMCredits()}</p>
 * @author Elijah
 *
 */
public class Setup {
	/***Version*/
    final static double version = 4.5;
	/**
	 * Automatically extracts jar if it is a jar and returns the path for the
	 * resource folder
	 * {@link dunemask.dunemasking.Setup#autoHandleSetup(String, String)}}
	 * <p> jarName
	 *            Name of jar
	 * <p> defaultFolderName
	 *            name of folder the jar (if exists) will be unpackaged too
	 * <p> returns the path
	 **/
	public static final int autoHandleSetup = 0;
	/**
	 * Returns the location of where the resource folder is. If non existant; it
	 * creates a new one being jarName_lib (Yes it has \\ At the end)
	 * {@link dunemask.dunemasking.Src#defaultFolder(String, double)}}
	 * 
	 * <p> wantedJarName
	 *            The name I want for my Jar Folder
	 * <p> version
	 *            Version of Dunemasking required
	 * <p> Returns the place where my main Resource folder is
	 **/
	public static final int Src = 1;
	/**
	 * Set Up with a default jar handle
	 * {@link dunemask.dunemasking.Setup#JarSetup()}
	 * <p> Returns the resource folder, not the top level
	 **/
	public static final int JarSetup = 2;

	/**
	 * Sets up program based on how you want
	 * 
	 * @param setUpStyle
	 *            Style of setup
	 * @param jarName
	 *            Name of the jar
	 * @param defaultFolderName
	 *            the name for the defaultFolder
	 * @return Path To Dfault Folder
	 */
	@SuppressWarnings("deprecation")
	public static String init(int setUpStyle, String jarName, String defaultFolderName) {
		String path = null;
		switch (setUpStyle) {
		// Default Setup
		case autoHandleSetup:
			path = autoHandleSetup(jarName, defaultFolderName);
			break;
		case Src:
			path = dunemask.dunemasking.Src.defaultFolder(jarName, Version.getVersion());
			break;
		case JarSetup:
			path = JarSetup();
			break;

		}

		return path;

	}

	/**
	 * Used For Running Resources from inside the jar
	 * 
	 * @return resources Path
	 */
	private static String JarSetup() {
		return "resources/";
	}

	/**
	 * Automatically extracts jar if it is a jar and returns the path for the
	 * resource folder
	 * 
	 * @param jarName
	 *            Name of jar
	 * @param defaultFolderName
	 *            name of folder the jar (if exists) will be unpackaged too
	 * @return returns the path
	 */
	private static String autoHandleSetup(String jarName, String defaultFolderName) {
		String path;
		if (jarName.contains(".jar")) {
			jarName = FileUtil.removeExtension(jarName);
		}

		if (new File(FileUtil.fixSpaces(JarUtil.getProgramPath()).replace("%20", " ") + jarName + ".jar").exists()) {
			String dir = new File(JarUtil.getProgramPath()).getAbsolutePath() + "\\";

			if (!new File(dir + defaultFolderName + "\\").exists()) {
				try {
					JarUtil.extractAllOpenDialog(dir, jarName, defaultFolderName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// Update path
			path = dir + jarName + "_lib\\";
		} else {
			path = JarUtil.getProgramPath();
		}
		return path;
	}

	/**
	 * Default Main Setup
	 * <p>
	 * Prints Out The Basic Principle Startup thingies
	 * 
	 **/
	public static void mainSetup() {
		File Setup = FileUtil.getResource("dunemask/resources/Setup.txt");
		String[] setupText = RW.read(Setup, 1, FileUtil.linesInFile(Setup));
		for (String s : setupText) {
			System.out.println(s);
		}
	}
	/**Prints out all the Dunemasking Things
	 * @author Elijah Dunemask
	 * */
	public static void DMCredits() {
		Version.ProgramSetUp(Version.getVersion());
		
	}
}
