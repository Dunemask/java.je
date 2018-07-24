/**
 * 
 */
package dunemask.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author dunemask
 *@deprecated
 */
public class ResourceUtil {
	
	
	/** @param url URL Usually a resource if blowing up try checking yer resourcepath first
	 * @return return "File" Form of the url by writing it out
	 * */
	public static File writeUrl(URL url) {
		File f = null;
		try {
			f = File.createTempFile("tempfile", ".tmp");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			return writeFile(url,f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/** @param url URL Usually a resource if blowing up try checking yer resourcepath first
	 * @param f File where the url will be written
	 * @return return "File" Form of the url by writing it out
	 * */
	public static File writeUrl(URL url,File f) {
		try {
			return writeFile(url,f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static int bytesize = 1024^3;
	
	private static File writeFile(URL url,File file) throws Exception {
		InputStream is = url.openStream();
		OutputStream out = new FileOutputStream(file);
		int read;
		byte[] bytes = new byte[bytesize];

		while ((read = is.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		//file.deleteOnExit();
		out.close();
		
		
		return file;
	}
	
	
	public static String sysPathFix(String path) {
		path=path.replace("\\", "/");
		String fs = File.separator;
		path = path.replace("\\", fs);
		return path;
		
	}
	
	
}
