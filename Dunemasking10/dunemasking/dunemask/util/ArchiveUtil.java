package dunemask.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ArchiveUtil {

	
	
	
	
	
	
	
	/** Get File From Jar
	 * @param jar Jar File
	 * @param absoluteInternalPath Absolute Internal Path to file sought
	 * @param out File to Be written out
	 * @return The file out now written with the is, or null if failure
	 * 
	 * */
	public static File getFileFromJar(File jar,String absoluteInternalPath,File out) {
		try {
		if(!absoluteInternalPath.startsWith("/")) {
			absoluteInternalPath = "/"+absoluteInternalPath;
		}
		String baseurl = jar.toURI().toURL().toString();
		URL murl = new URL("jar:"+baseurl+"!"+absoluteInternalPath);
		InputStream is = murl.openStream();
		 byte[] buffer = new byte[is.available()];
		    is.read(buffer);
		    OutputStream outStream = new FileOutputStream(out);
		    outStream.write(buffer);
		    outStream.close();
		    is.close();
		return out;
		}catch(Exception e) {
			System.err.println("Ya don goofed! Check yer paths");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
}
