package dunemask.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads And Writes Files
 * <p>Removes any Blank Lines: {@link dunemask.util.RW#cleanFile(File)}</p>
 * <p>Write One Line of Text: {@link dunemask.util.RW#write(File, String, int)}</p>
 * <p>Write Multiple Lines of Text: {@link dunemask.util.RW#write(File, String[], int)}</p>
 * <p>Read One Line of Text From File: {@link dunemask.util.RW#read(File, int)}</p>
 * <p>Read Lines from File: {@link dunemask.util.RW#read(File, int, int)}</p>
 * <p>Reads all lines from File: {@link dunemask.util.RW#readAll(File)}</p>
 * @author Elijah Dunemask
 **/
public class RW {
	/*@RW.java: RW Is the fast version with a clean file function that copies the file 
    before reading them off, Clean takes fairly long*/
	/***Version*/
    final static double version = 4.35;
	/**
	 * Write Line To Specified file
	 * 
	 * @param file
	 *            File Where line is written
	 * @param text
	 *            Text to be Written
	 * @param line
	 *            Line where text will be written
	 *
	 */
	public static void write(File file, String text, int line) {

		if (!file.exists()) {
			try {
				//Make Parent File
				file.getParentFile().mkdirs();
				file.createNewFile();
				//initializeFile(file, line);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/************* File Exists Now *****************/

		/*************** Must Write File Now *******************/
		String tString[] = {text};
		try {
			writeLine(file, tString, line);
		} catch (IOException e) {
			e.printStackTrace();
		}


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
	 * @param startLine
	 *            Line where the writer will start writing
	 *
	 */
	public static void write(File file, String[] text, int startLine) {
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				//initializeFile(file, startLine);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} /*
			 * else { //The Way that this has been set up I need to clean the file try {
			 * cleanFile(file); } catch (IOException e) { e.printStackTrace(); } }
			 */
		/************* File Exists Now *****************/


		try {
			writeLine(file, text, startLine);
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		String text;
		Scanner fileReader = null;

		// create fileReader
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} // Close Catch Clause
		/***************************************/

		// Get Up to What Line we want to read
		for (int i = 1; i < line; i++) {
			try {
				fileReader.nextLine();
			} catch (NoSuchElementException e) {

			}
		}
		// Grab the line we want
		try {
			text = fileReader.nextLine();
		} catch (NoSuchElementException e) {
			text = null;
		}
		// Close The Reader
		fileReader.close();

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
		int linesToBeRead = Math.abs(end - start) + 1;
		String[] text = new String[linesToBeRead];
		Scanner fileReader = null;

		// create fileReader
		try {
			fileReader = new Scanner(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} // Close Catch Clause
		/***************************************/
		// Get Up to What Line we want to read
		for (int i = 1; i < start; i++) {

			fileReader.nextLine();

		}
		// Read the Lines
		for (int i = 0; i < text.length; i++) {
			// System.out.println("NOne at"+i);
			try {
				text[i] = fileReader.nextLine();
			} catch (NoSuchElementException e) {
				text[i] = null;
			}
		}
		fileReader.close(); // Close the Reader
		// Return the string
		return text;

	}

	/**
	 * Cleans the file by removing any and all whitespace
	 * 
	 * @param file
	 *            File to clean
	 * @deprecated
	 */
	public static void cleanFile(File file) {
		int lines = FileUtil.linesInFile(file);
		File copy = new File(file.getParent() + "\\Copy.txt");
		try {
			copyFile(copy, file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 1; i <= lines; i++) {

			String text = null;
			try {
				text = read(copy, i); //**********************************
			} catch (NoSuchElementException e) {

				text = "";
			}
			if (!text.equals("")) {
				try {
					bw.write(text);
					bw.newLine();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		}
		copy.delete(); //******************************
		try {
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	private static void copyFile(File copyOfFile, File file) throws IOException {
    	//Needs File for the number of rows it holds, and also for it to copy that file
    	String existingFile;
    	int linesInFile=FileUtil.linesInFile(file);
    	BufferedWriter bw = new BufferedWriter(new FileWriter(copyOfFile));					//Creates a copy of the document
    	for(int i =1;i<=linesInFile;i++) {
    		existingFile=read(file,i).toString();
    	
    		bw.write(existingFile);
    		bw.newLine();
    		
    		
    	}bw.close();
		
	}

	
	

	// Will Write from starting line to start + String array length
	private static void writeLine(File file,  String[] text, int startLine) throws IOException {
		int i,c,linesInFile;
		linesInFile = FileUtil.linesInFile(file);
		String documentText[] = RW.read(file, 1, Math.max(linesInFile,(text.length-1)+startLine));
	
		String tmp;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(i=1; i<startLine;i++) {
			tmp = documentText[i-1];
			if(tmp!=null) {
				bw.write(tmp);
			}
			bw.newLine();
		}

		for(c=0;c<text.length;c++) {
			tmp = text[c];
			if(tmp!=null) {
				bw.write(tmp);
			}
			bw.newLine();
		}
		int h = i+c;
		for(i=h;i<=linesInFile;i++) {
			tmp = documentText[i-1];
			if(tmp!=null) {
				bw.write(tmp);
			}
			bw.newLine();
		}
		// Close Writer
		bw.close();
	}

}
