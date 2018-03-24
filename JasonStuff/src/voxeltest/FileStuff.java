/**
 * 
 */
package voxeltest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * @author Jason Roberts
 *
 */
public class FileStuff {

	/**
	 * 
	 */
	static final JFileChooser fc = new JFileChooser("Players\\");

	public FileStuff() {
		// TODO Auto-generated constructor stub
	}
	public static void Write(File file, int line, String text) {
		if(!file.exists()) {// If file doesnt exist then create a new one.
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
				initializeFile(file, line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String[] linetxt;
		if(line>LinesInFile(file)) {
		linetxt= new String[line];
		}
		else {
		linetxt= new String[LinesInFile(file)];
		}
		Scanner s = null;//Creates empty Scanner
		try {
			s = new Scanner(file);//tries to find file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i=0; i<LinesInFile(file);i++) {// Goes through lines and puts it in an array
			linetxt[i] = s.nextLine();
			//System.out.println(linetxt[i]);
		}
		linetxt[line-1] = text;//Replaces the line
		
		
		BufferedWriter bw;//Writes it back in
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for(int i = 1;i<= linetxt.length; i++) {
				bw.write(linetxt[i-1]);
				bw.newLine();
				}
				bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//creates buffered writer
		
	}
	private static void initializeFile(File file, int line) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));//creates buffered writer
		for(int i = 1;i<= line; i++) {
		bw.write("");
		bw.newLine();
		}
		bw.close();
		}
	public static int LinesInFile(File file) {
		Scanner fileReader = null;
		try {
			fileReader= new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while(fileReader.hasNextLine())
		{
			i ++;
			fileReader.nextLine();
		}
		fileReader.close();
		
		return i;
	}
public static String ReadLine(File file, int line) {
		
		Scanner s = null;//Creates empty Scanner
		try {
			s = new Scanner(file);//tries to find file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 1; i < line; i ++) {//goes through the lines all the way up to the desired int line
			try {
				s.nextLine();
				}catch(NoSuchElementException e) {
				}
		}
		String text;
		try {
		text = s.nextLine();//sets the return text to the next line
		}catch(NoSuchElementException e) {
			text=null;
		}
		return text;
	}
public static File Choose(String dir) {
	File fe = new File(dir);
	fc.setCurrentDirectory(fe);
	int returnVal = fc.showOpenDialog(null);
	if(returnVal == JFileChooser.APPROVE_OPTION) {
		return fc.getSelectedFile();
	} else
	return null;
}
static public String SaveArray(int[] array) {
	String s = array.length +";";
	int c=0;
	int tmp = 0;
	int selected=array[0];
	while(c<array.length) {
		c++;
		if(array[c-1]==selected) {
			tmp++;
		}
		else {
			s+=tmp+","+selected+";";
			tmp=1;
			selected=array[c-1];
		}
	}
	s+=tmp+","+selected+";";
	return s;
}
static public int[] LoadArray(String s) {
	String[] ltmp = s.split(";");
	int[] i = new int[Integer.parseInt(ltmp[0])];
	int c = 0;	
	for(int l = 1; l<ltmp.length;l++) {
		int selected=Integer.parseInt(ltmp[l].split(",")[1]);
	for(int k = 0; k<Integer.parseInt(ltmp[l].split(",")[0]);k++) {
			i[c]=selected;
			c++;
			}
	}
	return i;
}
static public int[] Array3Dto1D(int[][][] dra) {
	int[] i = new int[dra.length*dra[0].length*dra[0][0].length];
	for(int j = 0; j<i.length;j++) {
		i[j]=dra[j%dra.length][(j/dra.length)%dra[0].length][j/(dra.length*dra[0].length)];
	}
	return i ;
}
static public int[] Array3Dto1D(byte[][][] dra) {
	int[] i = new int[dra.length*dra[0].length*dra[0][0].length];
	for(int j = 0; j<i.length;j++) {
		i[j]=dra[j%dra.length][(j/dra.length)%dra[0].length][j/(dra.length*dra[0].length)];
	}
	return i ;
}
static public int[][][] Array1Dto3D(int[] dra, int sx, int sy){
	int[][][] i = new int[sx][sy][dra.length/(sx*sy)];
	for(int j = 0; j< dra.length; j++) {
		i[j%sx][(j/sx)%sy][j/(sx*sy)] = dra[j];
	}
	return i;
}
static public byte[][][] Array1Dto3Db(int[] dra, int sx, int sy){
	byte[][][] i = new byte[sx][sy][dra.length/(sx*sy)];
	for(int j = 0; j< dra.length; j++) {
		i[j%sx][(j/sx)%sy][j/(sx*sy)] = (byte)dra[j];
	}
	return i;
}
}
