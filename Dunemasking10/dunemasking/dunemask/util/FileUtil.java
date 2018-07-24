package dunemask.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
/**Dunemasking FileUtil for easy editing and changing of filesv
 * <p>Test In List: {@link dunemask.util.FileUtil#alreadyInFile(File, String)}</p>
 * <p>Remove Extension From File: {@link dunemask.util.FileUtil#removeExtension(String)}</p>
 * <p>Change File Extension: {@link dunemask.util.FileUtil#changeExtension(String, String)}</p>
 * <p>Get Number of Lines In File: {@link dunemask.util.FileUtil#linesInFile(File)}</p>
 * <p>Change FilePath Folder Handling: {@link dunemask.util.FileUtil#filePathFix(String)}</p>
 * <p>Find Certain Text In Document: {@link dunemask.util.FileUtil#findInDocument(File, String)}</p>
 * <p>Get SubFiles: {@link dunemask.util.FileUtil#getAllSubFiles(File)}</p>
 * <p>Return Next Free Line In File: {@link dunemask.util.FileUtil#nextFreeLine(File)}</p>
 * <p>Get File From URL: {@link dunemask.util.FileUtil#getWebFile(String)}</p>
 * <p>Write File from File: {@link dunemask.util.FileUtil#writeFile(File, File)}</p>
 * 
 * @author Elijah
 * */
public class FileUtil{
    /** Write the url to the File Not to be mistaken with {@link ResourceUtil#writeUrl(URL, File)}
     * <p>Primarily Used for Retrieving Files From the Web</p>
     * @param path URL path
     * @return 
     * */
    public static File retrieveFile(String path) {
    	File tmp = null;
    	try {
			tmp = File.createTempFile("Temp"+new SecureRandom(), ".dtmp");
			FileUtil.retrieveFile(path, tmp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return tmp;
    }
    

    /** Write the url to the File Not to be mistaken with {@link ResourceUtil#writeUrl(URL, File)}
     * <p>Primarily Used for Retrieving Files From the Web</p>
     * @param path URL path
     * @param file file where the url will be written
     * */
    public static void retrieveFile(String path,File file) {
    	path = path.replace(" ", "%20");
    	URLConnection connection;
		try {
			URL url = new URL(path);
			connection = url.openConnection();
    	InputStream in = connection.getInputStream();
    	FileOutputStream fos = new FileOutputStream(file);
    	byte[] buf = new byte[512];
    	while (true) {
    	    int len = in.read(buf);
    	    if (len == -1) {
    	        break;
    	    }
    	    fos.write(buf, 0, len);
    	}
    	in.close();
    	fos.flush();
    	fos.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    
    /**@deprecated use {@link FileUtil#retrieveFile(String)}
	 * <p>Get File From Specified URL (Web File)</p>
	 * @param address  address
	 * @return Return file from url
	 * */
    public static File getWebFile(String address) {
    	File webFile=null;

    	try {
			webFile = getWebFile(address,File.createTempFile("tmp", null));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return webFile;
    	
    }
    
    /**@deprecated Use: {@link FileUtil#retrieveFile(String, File)}
     * 
   	 * <p>Get File From Specified URL and download to Wanted 'File' (Web File)</p>
   	 * @param address  address
   	 * @return Return file from url
   	 * */
       public static File getWebFile(String address,File file) {
       	URL url = null;
   		try {
   			url = new URL(FileUtil.filePathFix(address).replace(" ", "%20"));
   		} catch (MalformedURLException e1) {
   			e1.printStackTrace();
   		}
       	
       	File webFile=null;
       	//String name = new File(url.getFile()).getName();
       //	String tDir = System.getProperty("java.io.tmpdir");
       	try {
       		InputStream in = url.openStream();
       		webFile = file;
   	
       		OutputStream out = new FileOutputStream(webFile);
       		int read;
   			byte[] bytes = new byte[1024^4];

   			while ((read = in.read(bytes)) != -1) {
   				out.write(bytes, 0, read);
   			}
       		
       		out.flush();
       		out.close();
       		in.close();	
       		//webFile.deleteOnExit();
       		
       		
       	}catch(IOException e) {
       		e.printStackTrace();
       	}
       	return webFile;
       	
       }
    
    
    /**Write File From "File"
     * @param fileIn Input File
     * @param fileOut Place where file will be written
     * **/
    public static void writeFile(File fileIn,File fileOut) {
    	try {
    		fileOut.getParentFile().mkdirs();
    		fileOut.createNewFile();
    		InputStream in = new FileInputStream(fileIn);
    		OutputStream out = new FileOutputStream(fileOut);
    		int read;
			byte[] bytes = new byte[1024*1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
    		out.close();
    		in.close();		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }
    
    
    





	/**
	 * Reads the number of lines that are written in the file
	 * 
	 * @param file
	 *            File whose lines shall be counted
	 * @return Returns the number of lines in the file or -1 if it blew up
	 */
	public static int linesInFile(File file) {
		return RW.countLines(IOUtil.FTU(file));

	}

	/**Get a list of files and folders from a package
	 * @param dir Starts at src level and works relative from that
	 * @return Returnrs array of files
	 * 
	 * **/
	public static File[] getAllSubFiles(File dir) {
		thefiles = new ArrayList<>();
		File[] subPackage = dir.listFiles();
		ArrayList<File> arfiles = new ArrayList<>(Arrays.asList(subPackage));
		setf(dir,arfiles);
		File[] files = getFiles();
		return files;
		
	}
	
	
	
	private static ArrayList<File> thefiles = new ArrayList<>();
	private static File[] getFiles() {
		//thefiles = new ArrayList<>();
		File[] files = thefiles.toArray(new File[thefiles.size()]);
		return files;
		
	}
	
	private static void setf(File directory, ArrayList<File> files) {
	  

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	            thefiles.add(file);
	        } else if (file.isDirectory()) {
	        	thefiles.add(file);
	            setf(file, files);
	        }
	    }
	    
	}
	
	/**
	 * Replace all back/forward slashes with the system file sepearator
	 * 
	 * @param filePath
	 *            Path To File
	 * @return String WIth forward slahes as opposed to backslahes
	 * @deprecated 	since 10.1
	 * @since 3.0
	 **/
	public static String filePathFix(String filePath) {
		return filePath.replace("/", "\\").replace("\\", File.separator);
		
	}






	
	
}
