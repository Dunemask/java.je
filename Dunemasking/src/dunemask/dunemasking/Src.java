package dunemask.dunemasking;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import dunemask.util.JarUtil;

/**
 * SRC does some stuff, not reccomended use
 * 
 * @author Elijah Dunemask
 * @deprecated
 * Will Be Removed in a future release
 **/
public class Src {
	/***Version*/
    final static double version = 4.5;
	public static final int IDE = 0;
	public static final int Jar = 1;
	public static final int Close = 2;

	/**
	 * Returns the location of where the resource folder is. If non existant; it
	 * creates a new one being jarName_lib (Yes it has \\ At the end)
	 * 
	 * @param wantedJarName
	 *            The name I want for my Jar Folder
	 * @param version
	 *            Version of Dunemasking required
	 * @return Returns the place where my main Resource folder is
	 */
	public static String defaultFolder(String wantedJarName, double version) {
		String location;
		String[] buttons = { "IDE", "Jar", "Close" };
		int choice = JOptionPane.showOptionDialog(null, "Source", "Source", JOptionPane.PLAIN_MESSAGE, 0, null, buttons,
				buttons[2]);
		location = "G:\\Sources\\";
		switch (choice) {

		case IDE:
			location = ide();
			break;
		case Jar:
			location = jar(wantedJarName);
			Version.ProgramSetUp(version);
			break;
		case Close:
			System.exit(1);
			break;

		}

		return location + "\\";

	}

	private static String jar(String wantedJarName) {
		String location = "";
		String jarName;

		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		File jarFile = new File(jarDir + "\\" + wantedJarName + ".jar");
		// See If I Have used argument
		if (wantedJarName != null && !wantedJarName.equalsIgnoreCase("") && jarFile.exists()) {
			jarName = wantedJarName;
		} else {
			System.out.println(jarFile.getAbsolutePath());
			jarName = JOptionPane.showInputDialog("Jar Name");
		}

		if (JarUtil.alreadyExisits(jarName, jarDir)) {
			location = jarDir.getAbsolutePath() + "\\" + jarName + "_lib\\";

		} else {
			JarUtil.init();
			File file = new File(jarDir.getAbsolutePath() + "\\");
			try {
				JarUtil.extractAllOpenDialog(file.getAbsolutePath() + "\\", jarName, (jarName + "_lib"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			location = jarDir.getAbsolutePath() + "\\" + jarName + "_lib\\";
		}

		return location;
	}

	private static String ide() {
		String location;
		File d = new File("D:\\Sources\\");
		File e = new File("E:\\Sources\\");
		File f = new File("F:\\Sources\\");
		File g = new File("G:\\Sources\\");
		if (d.exists()) {
			location = d.getAbsolutePath();
		} else if (e.exists()) {
			location = e.getAbsolutePath();
		} else if (f.exists()) {
			location = f.getAbsolutePath();
		} else if (g.exists()) {
			location = g.getAbsolutePath();
		} else {
			location = "";
		}
		return location;

	}

	/**
	 * Returns the Name of Jar from the Default Folder Path
	 * 
	 * @param defFolder
	 *            Default Folder Path
	 * @return The name of Jar
	 */
	public static String jarName(String defFolder) {
		String jarName = "";
		int libLocation = defFolder.lastIndexOf("_lib");
		if (libLocation != -1) {
			defFolder = defFolder.substring(0, libLocation);
			int backslashLocation = defFolder.lastIndexOf("\\");

			defFolder = defFolder.substring(backslashLocation + 1);
			// System.out.println("REmoveBacks "+ defFolder);
			jarName = defFolder;

		}
		return jarName;
	}

}
