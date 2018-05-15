/**
 * 
 */
package xml;

import java.io.File;

import dunemask.util.xml.Runemap;

/**
 * @author dunemask
 *
 */
public class XMLTEst1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runemap rm = new Runemap(new File(System.getProperty("user.home")+"/Desktop/tmp.drm"));
		rm.writeForcedElement("Yo/Potato/Momma", "alpha");
		rm.write();

	}

}
