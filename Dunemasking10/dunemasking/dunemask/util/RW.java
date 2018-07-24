package dunemask.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/** RW Is a simple Read and Write Program
 * @author dunemask 
 * @since 3.0
 * @version 10.1
 * 
 * */
public class RW {

	/**Read a line from a file 
	 * @param url
	 * @param line
	 * @return text on line
	 * 
	 * */
	public static String read(URL url,int line) {
		return read(url,line,line).get(0);
	}
	
	/** Read a file
	 * @param url
	 * @param start
	 * @param end
	 * @return list of lines
	 * 
	 * */
	public static ArrayList<String> read(URL url,int start,int end){
		if(end<start) {
			throw new RuntimeException("Start IS HIGHER THAN THE END!");
		}
		
		int linecount = 0;
		ArrayList<String> lines = new ArrayList<String>();
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String cline=null;
		while (((cline=reader.readLine()) != null)&&linecount!=end) {
			++linecount;
			if(linecount>=start) {
				lines.add(cline);
			}
		}
		reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lines;
		
		
		
	}
	
	
	
	/** Read Lines in a file until and including a specific line
	 * @param url URL
	 * @param line Line until stop
	 * @return ArrayList<String> of the lines 
	 * 
	 *
	 */
	public static ArrayList<String> readUntil(URL url,int line) {
		int linecount = 0;
		ArrayList<String> lines = new ArrayList<String>();
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String cline=null;
		while (((cline=reader.readLine()) != null)&&linecount!=line) {
			lines.add(cline);
			++linecount;
		}
		reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	
	/** Read All Lines In A File 
	 * @param URL
	 *  @return All Lines From File
	 * */
	public static ArrayList<String> readAll(URL url) {

		ArrayList<String> lines = new ArrayList<String>();
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String cline=null;
		while ((cline=reader.readLine()) != null) {
			lines.add(cline);
		}
		reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lines;

	}
	
	
	
	

	
	
	/** Write list of text to file
	 * @param file
	 * @param list Text to be written
	 * @param line Start Line
	 * 
	 * 
	 * 
	 * */
	public static void write(File file,ArrayList<String> list,int line) {
		String[] text = list.toArray(new String[list.size()]);
		try {
			int i,c,linesInFile;
			ArrayList<String> lines =null; 
			if(file.exists()) {
				lines = readAll(file.toURI().toURL()); //= read(file, 1, Math.max(linesInFile,(text.length-1)+line));
			}else {
				lines = new ArrayList<String>(line+list.size()-1);
			}
			linesInFile = lines.size()-1;
			if(linesInFile<line) {
				lines.removeAll(Collections.singleton(null));
				for(i=0;i<line;i++) {
					lines.add(null);
				}
			}
			
			String documentText[]  = lines.toArray(new String[lines.size()]);
			
			
			String tmp;
			
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos,8192);
			OutputStreamWriter osw = new OutputStreamWriter(bos);
			BufferedWriter bw = new BufferedWriter(osw);
			for(i=1; i<line;i++) {
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
				//if(i+c<=documentText.length) {//TODO Prob delete to be safe
				bw.newLine();
				//}
			}
			int h = i+c;
			for(i=h;i<=linesInFile+1;i++) {
				tmp = documentText[i-1];
				if(tmp!=null) {
					bw.write(tmp);
				}
				if(i<=linesInFile) {//TODO Prob delete to be safe
				bw.newLine();
				}
			}
			// Close Writer
			bw.flush();
			bw.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/** Writes text to a file on a particular line
	 * @param file
	 * @param text
	 * @param line
	 * 
	 * 
	 * */
	public static void write(File file, String text, int line) {
		ArrayList<String> txt = new ArrayList<String>(Arrays.asList(new String[] {text}));
		write(file,txt,line);
		
	}
	/** Writes list of text to a file
	 * @param file
	 * @param text
	 * */
	public static void writeAll(File out,ArrayList<String> text) {
		write(out,text,1);
		
		
		
	}
	
	
	/** Returns the number of lines in the file
	 * @param url URL
	 * @return lines in file or -1 if an error occurred
	 * 
	 * */
	public static int countLines(URL url) {
        int count = 0;
		try {
		    InputStream is = new BufferedInputStream(url.openStream());
		    try {
		        byte[] c = new byte[1024];
		        int readChars = 0;
		        boolean endsWithoutNewLine = false;
		        while ((readChars = is.read(c)) != -1) {
		            for (int i = 0; i < readChars; ++i) {
		                if (c[i] == '\n')
		                    ++count;
		            }
		            endsWithoutNewLine = (c[readChars - 1] != '\n');
		        }
		        if(endsWithoutNewLine) {
		            ++count;
		        } 
		        return count;
		    } finally {
		        is.close();
		    }
		    
		}catch(Exception e) {
			e.printStackTrace();
			count = -1;
		}
		return count;
	}
	
	
	
}
