/**
 * 
 */
package testing;

import java.io.File;
import java.net.URISyntaxException;

import dunemask.util.xml.Runemap;

/**
 * @author dunemask
 *
 */
public class WriteTestMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		var testUrl = WriteTestMap.class.getResource("/resources/test.drm");
		File drmFile = null;
		try {
			drmFile = new File(testUrl.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Runemap map = new Runemap(drmFile);
		map.writeContainer("Test/");
		map.writeContainer("Test/Elements");
		map.writeElement("Test/Elements/Element", "Value1");
		map.writeElement("Test/Elements/Element2", "Value1");
		map.write();

	}

}
