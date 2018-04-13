package dunemask.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**Dunemasking FileUtil for easy editing and changing of filesv
 * <p>Test In List: {@link dunemask.util.FileUtil#alreadyInFile(File, String)}</p>
 * <p>Remove Extension From File: {@link dunemask.util.FileUtil#removeExtension(String)}</p>
 * <p>Change File Extension: {@link dunemask.util.FileUtil#changeExtension(String, String)}</p>
 * <p>Get Number of Lines In File: {@link dunemask.util.FileUtil#linesInFile(File)}</p>
 * <p>Change FilePath Folder Handling: {@link dunemask.util.FileUtil#filePathFix(String)}</p>
 * <p>Find Certain Text In Document: {@link dunemask.util.FileUtil#findInDocument(File, String)}</p>
 * <p>Get SubFiles: {@link dunemask.util.FileUtil#getAllSubFiles(File)}</p>
 * <p>Get Resource: {@link dunemask.util.FileUtil#getResource(String)}</p>
 *  <p>Last Line: {@link dunemask.util.FileUtil#lastLine(File)}</p>
 * <p>Return Next Free Line In File: {@link dunemask.util.FileUtil#nextFreeLine(File)}</p>
 * <p>Get File From URL: {@link dunemask.util.FileUtil#getWebFile(String)}</p>
 * <p>Write File from File: {@link dunemask.util.FileUtil#writeFile(File, File)}</p>
 * <p>Last Index of Text: {@link dunemask.util.FileUtil#lastInstanceOfText(File, String)}}</p>
 * <p>Remove Spaces: {@link dunemask.util.FileUtil#fixSpaces(String)}}</p>
 * 
 * @author Elijah
 * */
public class FileUtil{
	/***Version*/
    final static double version = 4.7;
	
    /** Recursively delete Sub Files and Folders
     * @param file Folder in File form to delete
     * 
     * 
     * */
   public static void deleteAllSubFolders(File file) {
		  if (file.isDirectory()) {
		    for (File c : file.listFiles())
		      deleteAllSubFolders(c);
		  }
		  if (!file.delete())
			try {
				throw new FileNotFoundException("Failed to delete file: " + file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
    
    
    /**Get The last line where a specific text was mentioned
     * @param file File Searched
     * @param text Text wanted
     * @return Return line where text was found
     * */
    public static int lastInstanceOfText(File file,String text) {
    	int line = -5;
    	String[] lines = RW.read(file, 0, FileUtil.linesInFile(file));
    	for(int i=0;i<lines.length;i++) {
    		String x = lines[i];
    		if(x.equals(text)) {
    			line = i;
    		}
    		
    	}
    	if(line==-5) {
    		Throwable notFound = new Throwable("Text: "+ text + " Was not found in the file");
    		notFound.printStackTrace();
    	}
    	
    	return line;
    	
    }
    
    
    
    /**Get File From Specified URL
	 * <p>(Web File)</p>
	 * @param address  address
	 * @return Return file from url
	 * */
    public static File getWebFile(String address) {
    	URL url = null;
		try {
			url = new URL(FileUtil.fixSpaces(FileUtil.filePathFix(address)));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
    	
    	File webFile=null;
    	String name = new File(url.getFile()).getName();
    	String tDir = System.getProperty("java.io.tmpdir");
    	try {
    		InputStream in = url.openStream();
    		webFile = new File(tDir+"/"+name.replace("%20", " "));
	
    		OutputStream out = new FileOutputStream(webFile);
    		int read;
			byte[] bytes = new byte[1024^4];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
    		
    		
    		out.close();
    		in.close();	
    		//webFile.deleteOnExit();
    		
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return webFile;
    	
    }
    
    /**Get File From Specified URL and download to Wanted 'File'
   	 * <p>(Web File)</p>
   	 * @param address  address
   	 * @return Return file from url
   	 * */
       public static File getWebFile(String address,File file) {
       	URL url = null;
   		try {
   			url = new URL(FileUtil.fixSpaces(FileUtil.filePathFix(address)));
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
	 * @param fileName
	 *            (String)
	 * @return Return filename without the extension (This also removes the period,)
	 * <p>If No Period is found it will go boom</p>
	 */
	public static String removeExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf(".");
		if (dotPosition == -1) {
			//No Period found
			return fileName;
		} else {
			//Period found
			return fileName.substring(0, dotPosition);
		}
	}

	/**
	 * Requires a period in the FileName, and no period in the extension name
	 * 
	 * @param fileName
	 *            name of file
	 * @param newExtension
	 *            extension name without period
	 * @return Previous extension removed, new extension added.
	 */
	public static String changeExtension(String fileName, String newExtension) {
		newExtension.replace(".", "");
		fileName = removeExtension(fileName);
		return fileName + "." + newExtension;
	}


	/**
	 * Reads the number of lines that are written in the file
	 * 
	 * @param file
	 *            File whose lines shall be counted
	 * @return Returns the number of lines in the file
	 */
	public static int linesInFile(File file) {
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File:"+file+" WAS NOT FOUND!");
			e.printStackTrace();
		} // Close Catch Clause
		/***************************************/

		int i = 0;
		// While I can still read the file
		while (fileReader.hasNextLine()) {
			i = i + 1;
			fileReader.nextLine();
		}
		fileReader.close(); // Close Reader
	
		return i;

	}

	/**
	 * Scans the File to see if the text is already in the list
	 * 
	 * @param file
	 *            to search
	 * @param text
	 *            to look for
	 * @return Whether it's in the list or not.
	 */
	public static boolean alreadyInFile(File file, String text) {
		boolean isInList = false;
		String[] lines = RW.read(file, 0, FileUtil.linesInFile(file));
		isInList = StringUtil.alreadyInList(lines, text);	
		return isInList;
	}
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @return The line where the text is located
	 */
	public static int findInIgnoreCaseDocument(File file, String text) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = 0; i <lines.length; i++) {
			if (lines[i].equalsIgnoreCase(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found in \n File:"+file);
		}
		return location;
	}
	//StringUtil.containsIgnoreCase(lines[i],text)||
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @param low Lower Bounds to be searched
	 * 
	 * @param high Max Bounds to be searched
	 * @return The line where the text is located
	 */
	public static int findInIgnoreCaseDocumentBounds(File file, String text,int low,int high) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		if(low>lines.length) {
			throw new RuntimeException("Lower Bounds "+ low + " Exceed the File's Length of "+lines.length);
		}
		if(high>lines.length) {
			throw new RuntimeException("Higher Bounds "+ high + " Exceed the File's Length of "+lines.length);
		}
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = low; i <high; i++) {
			if (lines[i].equalsIgnoreCase(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found between "+low+" and "+high+" \n File:"+file);
		}
		return location;
	}
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @return The line where the text is located
	 */
	public static int findInDocument(File file, String text) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = 0; i <lines.length; i++) {
			if (lines[i].equals(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found in \n File:"+file);
		}
		return location;
	}
	//StringUtil.containsIgnoreCase(lines[i],text)||
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @param low Lower Bounds to be searched
	 * 
	 * @param high Max Bounds to be searched
	 * @return The line where the text is located
	 */
	public static int findInDocumentBounds(File file, String text,int low,int high) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		if(low>lines.length) {
			throw new RuntimeException("Lower Bounds "+ low + " Exceed the File's Length of "+lines.length);
		}
		if(high>lines.length) {
			throw new RuntimeException("Higher Bounds "+ high + " Exceed the File's Length of "+lines.length);
		}
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = low; i <high; i++) {
			if (lines[i].equals(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found between "+low+" and "+high+" \n File:"+file);
		}
		return location;
	}
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @return The line where the text is located
	 */
	public static int containsInDocument(File file, String text) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = 0; i <lines.length; i++) {
			if (lines[i].contains(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found in \n File:"+file);
		}
		return location;
	}
	
	//StringUtil.containsIgnoreCase(lines[i],text)||
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @param low Lower Bounds to be searched
	 * 
	 * @param high Max Bounds to be searched
	 * @return The line where the text is located
	 */
	public static int containsInDocumentBounds(File file, String text,int low,int high) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		if(low>lines.length) {
			throw new RuntimeException("Lower Bounds "+ low + " Exceed the File's Length of "+lines.length);
		}
		if(high>lines.length) {
			throw new RuntimeException("Higher Bounds "+ high + " Exceed the File's Length of "+lines.length);
		}
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = low; i <high; i++) {
			if (lines[i].contains(text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found between "+low+" and "+high+" \n File:"+file);
		}
		return location;
	}
	
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @return The line where the text is located
	 */
	public static int containsIgnoreCaseInDocument(File file, String text) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = 0; i <lines.length; i++) {
			if (StringUtil.containsIgnoreCase(lines[i],text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found in \n File:"+file);
		}
		return location;
	}
	
	//StringUtil.containsIgnoreCase(lines[i],text)||
	/**Find a line of text in file, Case Not sensitive
	 * <p>Returns First instance found</p>
	 * @param file
	 *            File to search
	 * @param text
	 *            Text to be searched for
	 * @param low Lower Bounds to be searched
	 * 
	 * @param high Max Bounds to be searched
	 * @return The line where the text is located
	 */
	public static int containsIgnoreCaseInDocumentBounds(File file, String text,int low,int high) {
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		
		if(low>lines.length) {
			throw new RuntimeException("Lower Bounds "+ low + " Exceed the File's Length of "+lines.length);
		}
		if(high>lines.length) {
			throw new RuntimeException("Higher Bounds "+ high + " Exceed the File's Length of "+lines.length);
		}
		
		int location = -5;
		// Start at first line (Not 0 lines in file :P)
		// To Account For the counter it has to be 0 revoked previous argument
		for (int i = low; i <high; i++) {
			if (StringUtil.containsIgnoreCase(lines[i],text)) {
				location = i+1;
				i=lines.length;
			}

		}
		if(location==-5) {
			throw new RuntimeException("Text \n"+text+" not found between "+low+" and "+high+" \n File:"+file);
		}
		return location;
	}

	/**
	 * Gets the last Line in the document
	 * 
	 * @param file
	 *            testing;
	 * @return last line;
	 **/
	public static int lastLine(File file) {
		return linesInFile(file) + 1;
	}
	/**
	 * Gets the next Blank Line In the document
	 * 
	 * @param file
	 *            testing;
	 * @return next free line;
	 **/
	public static int nextFreeLine(File file) {
		int fl=-5;
		String[] line = RW.read(file, 1, linesInFile(file));
		for(int i=0;i<line.length;i++) {
			if(line[i].equals("")||line[i].equals(" ")) {
				fl = i;
				i=line.length;
			}
			
		}
		if(fl==-5) {
			fl = lastLine(file);
		}
		return fl;
		
		
	}
	
	/**
	 * Get File From relative Location "starts at src folder" Inside src folder
	 * "resources/README!.txt"; Use that
	 * 
	 * @param ResourceDirectory
	 *            Directory To resource should be relative
	 * @return Returns the wanted Resource as a url
	 **/
	public static  URL getResourceURL(String ResourceDirectory) {
		if (!ResourceDirectory.startsWith("/")) {
			//ResourceDirectory = "/" + ResourceDirectory; Add it
		}else {
			ResourceDirectory=ResourceDirectory.substring(1, ResourceDirectory.length());
		}
		/***(Does Not Need Absolute Referencing now apparently I:)**/

		String resource = FileUtil.filePathFix(ResourceDirectory); // "..\\..\\resources\\"+rm.getPath();
		//resource = "/resources/track-list.txt";
		
		//Create ClassLoader to get resources
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL res = null;
			//Fixes stuffs
		
			//This catches if it's a Direct filepath, it should assume IDE path
			if(resource.substring(1, 2).equals(":")) {
				try {
					resource = resource.replace("%20", " ");
					res = new File(resource).toURI().toURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			//Otherwise have the classloader get the resource
			}else {
				
				res = classLoader.getResource(resource);
			}
				//Catch Any Spaces in the name
				try {
					res = new URL(res.toString().replace("%20", " "));
				} catch (Exception e) {
					System.err.println("Prb don't exist");
					System.err.println("Try checking your path for:"+ResourceDirectory.toString().replace("%20", " "));
					e.printStackTrace();
				}//Close Catch
				
			return res;
	
	}
	
	
	
	
	
	
	/**
	 * Get File From relative Location "starts at src folder" Inside src folder
	 * "resources/README!.txt"; Use that
	 * 
	 * @param ResourceDirectory
	 *            Directory To resource should be relative
	 * @return Returns the wanted Resource as a file
	 **/
	public static  File getResource(String ResourceDirectory) {
		File file = null;
		if (!ResourceDirectory.startsWith("/")) {
			//ResourceDirectory = "/" + ResourceDirectory; Add it
		}else {
			ResourceDirectory=ResourceDirectory.substring(1, ResourceDirectory.length());
		}
		/***(Does Not Need Absolute Referencing now apparently I:)**/

		String resource = FileUtil.filePathFix(ResourceDirectory); // "..\\..\\resources\\"+rm.getPath();
		//resource = "/resources/track-list.txt";
		
		//Create ClassLoader to get resources
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL res = null;
			//Fixes stuffs
		
			//This catches if it's a Direct filepath, it should assume IDE path
			if(resource.substring(1, 2).equals(":")) {
				try {
					resource = resource.replace("%20", " ");
					res = new File(resource).toURI().toURL();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			//Otherwise have the classloader get the resource
			}else {
				
				res = classLoader.getResource(resource);
			}
				//Catch Any Spaces in the name
				try {
					res = new URL(res.toString().replace("%20", " "));
				} catch (Exception e) {
					System.err.println("Prb don't exist");
					System.err.println("Try checking your path for:"+ResourceDirectory.toString().replace("%20", " "));
					e.printStackTrace();
				}//Close Catch
				
		if (res.toString().startsWith("jar:")||res.toString().startsWith("rsrc:")) {
			try {
				InputStream input = classLoader.getResourceAsStream(resource);
				file = File.createTempFile("tempfile", ".tmp");
				OutputStream out = new FileOutputStream(file);
				int read;
				byte[] bytes = new byte[1024^3];

				while ((read = input.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				//file.deleteOnExit();
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			// this will work in IDE, but not from a JAR
			file = new File(res.getFile());
		}

		if (file != null && !file.exists()) {
			throw new RuntimeException("Error: File " + file + " not found!"+"\n"+"From: "+res);
			
		}
		return file;

	}
	/**Replaces all ' ' with '%20'
	 * @param path Path of File
	 * @return String with fixed spacing
	 */
	public static String fixSpaces(String path) {
		return path.replace(" ", "%20");
			

		
	}
	
	/**Replaces all '%20' with ' '
	 * @param path Path of File
	 * @return String with fixed spacing
	 */
	public static String fixToSpaces(String path) {
		return path.replace("%20", " ");
			

		
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
	 * Replace all Back Slashes with forward ones
	 * 
	 * @param filePath
	 *            Path To File
	 * @return String WIth forward slahes as opposed to backslahes
	 **/
	public static String filePathFix(String filePath) {
		return filePath.replace("\\", "/");
		
	}
	/**
	 * Replace all Forward Slashes with back ones
	 * 
	 * @param filePath
	 *            Path To File
	 * @return String WIth back slahes as opposed to forwardslahes
	 **/
	public static String filePathFixReverse(String filePath) {
		return filePath.replace("/", "\\");
		
	}




	/**Tests File exists within a dir
	 * <p>(Tests names)</p>
	 * @param dirFile Package whose subcomponents will be searched
	 * @param file File being Searched for
	 * @return FileExists
	 * */
	public static boolean fileExists(File dirFile,File file) {
		boolean exists=false;
		File[] subFile = getAllSubFiles(dirFile);
		for(int i=0;i<subFile.length;i++) {
			if(subFile[i].getName()==file.getName()) {
				exists = false;
				i=subFile.length;
			}
		}
		
		
		return exists;
	}

	
	
}
