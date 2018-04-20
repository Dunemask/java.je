package dunemask.dunemasking;

import java.io.File;

import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * Handles All Dunemasking Version kinds of things
 * <p>Get Dunemasking Version: {@link dunemask.dunemasking.Version#getVersion()}</p>
 * <p>Get Dunemasking Library Version: {@link dunemask.dunemasking.Version#getLibraryVersion()}</p>
 * <p>Test compatable Version: {@link dunemask.dunemasking.Version#compatableVersion(double)}</p>
 * <p>Tests Dunemasking compatability: {@link dunemask.dunemasking.Version#ProgramSetUp(double)}</p>
 * <p>Get Dunemasking Library Version: {}</p>
 */
public class Version {
	/***Version*/
    final static double version = 4.5;
	
	/**
	 * Gets the Library version of Dunemasking
	 * 
	 * @return Library Version
	 */
	public static double getLibraryVersion() {
		File versionFile = FileUtil.getResource("/dunemask/resources/Version.txt");
		
	
		String line = RW.read(versionFile, 2);
		line = line.replace("Library Version:", "");
		double libVer= Double.parseDouble(line);
		return libVer;
	}
	
	
	
	/**
	 * Tests to see if it's a compatable Version
	 * 
	 * @param version
	 *            Version Wanted
	 * @return If it's compatable or not
	 */
	public static boolean compatableVersion(double version) {
		boolean compatable = true;
		// If it's not unpackaged we need to unpackage it
		// Unpackage("");
		File versionFile = FileUtil.getResource("/dunemask/resources/Version.txt");
		String thisVersion = RW.read(versionFile, 1);
		double ver = Double.parseDouble(thisVersion.replace("Version", "").replaceAll(" ", ""));
		// Get The Number
		if (ver >= version) {
			compatable = true;
		} else {
			compatable = false;
		}

		return compatable;
	}

	/**
	 * Gets the version of Dunemasking
	 * 
	 * @return Version
	 */
	public static double getVersion() {
		// Unpackage("");
		File versionFile = FileUtil.getResource("/dunemask/resources/Version.txt");
		String thisVersion = RW.read(versionFile, 1);
		double ver = Double.parseDouble(thisVersion.replace("Version", "").replaceAll(" ", ""));
		return ver;
	}

	/**
	 * @param ver
	 *            Required Version
	 */
	public static void ProgramSetUp(double ver) {

		if (!compatableVersion(ver)) {
			System.out.println("PLEASE UPDATE YOUR DUNEMASKING LIBRARY");
			System.exit(1);
		}
		File readme = FileUtil.getResource("/dunemask/resources/README!.txt");
		for (int i = 1; i <= FileUtil.linesInFile(readme); i++) {
			System.out.println(RW.read(readme, i));
		}
	}

}
