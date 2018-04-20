/**
 * 
 */
package dunemask;

/**
 * @author dunemask
 *
 */
public class Dunemasking {
	/***Version*/
    final static double version = 4.35;
    /** Program Files Path in windows with / at end
     * */
	public static String Dunemasking_Program_Windows = "C:/Program Files/Dunemasking/";
	
    /** Dunemasking Apps Path in windows with / at end
     * */
	public static String Dunemasking_Program_Windows_Apps = "C:/Program Files/Dunemasking/apps/";
	
	/** Returns Specific Version of Apps Folder
	 * */
	public static String appFolder(String version) {
		return Dunemasking.Dunemasking_Program_Windows_Apps+version+"Apps/";
	}
	
}
