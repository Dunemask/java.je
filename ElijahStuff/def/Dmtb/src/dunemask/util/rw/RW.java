 package dunemask.util.rw;

import java.io.File;

import dunemask.util.FileUtil;
import dunemask.util.internal.DMRW;

/**
 * Reads And Writes Files
 * <p>Write One Line of Text: {@link dunemask.util.rw.RW#write(File, String, int, int)}</p>
 * <p>Write Multiple Lines of Text: {@link dunemask.util.rw.RW#write(File, String[], int,int)}</p>
 * <p>Read One Line of Text From File: {@link dunemask.util.rw.RW#read(File, int)}</p>
 * <p>Read Lines from File: {@link dunemask.util.rw.RW#read(File, int, int)}</p>
 * <p>Reads all lines from File: {@link dunemask.util.rw.RW#readAll(File)}</p>
 * @author Elijah Dunemask
 **/
public class RW {
	/***Version*/
    final static double version = 4.5;

	/**
	 * Insert Line To Specified file
	 * 
	 * @param file
	 *            File Where line is inserted
	 * @param text
	 *            Text to be Inserted
	 * @param line
	 *            Line where text will be inserted
	 */
    public static synchronized void insertLine(File file,String text,int line) {
    	DMRW.insertLine(file, text, line, DMRW.Default_Buffer_Size);
    	
    }

    
	
	/**
	 * Insert Lines in Specified file
	 * 
	 * @param file
	 *            File Where lines are written
	 * @param text
	 *            Text to be Inserted
	 * @param startLine
	 *            StartLine where text will be inserted
	 */
    public static synchronized void insertLines(File file,String[] text,int startLine) {
    	DMRW.insertLines(file, text, startLine, DMRW.Default_Buffer_Size);
    }
    
    
    
	
	/**
	 * Write Line To Specified file
	 * 
	 * @param file
	 *            File Where line is written
	 * @param text
	 *            Text to be Written
	 * @param line
	 *            Line where text will be written
	 */
	public static synchronized void write(File file, String text, int line) {
		DMRW.write(file, text, line, DMRW.Default_Buffer_Size);
	}
	
	
	
	/**Reads All Lines In The File
	 * @param file File to be read
	 * @return String Array of all the liens
	 * */
	public static String[] readAll(File file) {
		String[] lines = read(file, 1, FileUtil.linesInFile(file));
		return lines;
	}
	
	/**
	 * Write String array To Specified file
	 * 
	 * @param file
	 *            File Where line is written
	 * @param text
	 *            String Array to be Written
	 *
	 */
	public static synchronized void writeAll(File file, String[] text) {
		DMRW.write(file,text,0,DMRW.Default_Buffer_Size);
		
	}
	
	
	
	
	/**
	 * Write String array To Specified file
	 * 
	 * @param file
	 *            File Where line is written
	 * @param text
	 *            String Array to be Written
	 * @param startLine
	 *            Line where the writer will start writing
	 */
	public static synchronized void write(File file, String[] text, int startLine) {
		DMRW.write(file, text, startLine, DMRW.Default_Buffer_Size);
	}
	
	
	
	
	

	
	
	/**
	 * Reads a line of text from file
	 * 
	 * @param file
	 *            File to be read
	 * @param line
	 *            Line to be read
	 * @return Returns the text at the line
	 */
	public static String read(File file, int line) {
		String text = DMRW.read(file, line);

		return text;

	}

	/**
	 * Reads a lines of text from file
	 * 
	 * @param file
	 *            File to be read
	 * @param start
	 *            Line to start reading from
	 * @param end
	 *            Line to end reading at
	 * @return Returns string array of text read
	 */
	public static String[] read(File file, int start, int end) {
		String[] text = DMRW.read(file, start, end);
		return text;

	}

	


}
