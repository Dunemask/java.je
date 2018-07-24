/**
 * 
 */
package dunemask.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dunemask
 *
 */
public class IOUtil {

	/** Convert File to URL
	 * @param file
	 * @return url
	 * 
	 * */
	public static URL FTU(File file) {
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Write A Byte Array to a file
	 * @param bytes
	 * @param out
	 * 
	 * */
	public static void writeBytes(byte[] bytes,File out) {
		try{
			FileOutputStream fos = new FileOutputStream(out);
			   fos.write(bytes);
			   fos.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	/** Read Bytes from a path
	 * @param url URL
	 * @return byte array
	 * 
	 * */
	public static byte[] getBytes(URL url) {
		InputStream is = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
		  is = url.openStream ();
		  byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
		  int n;

		  while ( (n = is.read(byteChunk)) > 0 ) {
		    baos.write(byteChunk, 0, n);
		  }
		}
		catch (IOException e) {
		  System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
		  e.printStackTrace ();
		  // Perform any other exception handling that's appropriate.
		}
		finally {
		  if (is != null) { try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} }
		}
		return baos.toByteArray();
	}
	
	
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
	

}
