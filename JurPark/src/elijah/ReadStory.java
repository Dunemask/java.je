/**
 * 
 */
package elijah;

import java.io.File;
import java.util.Scanner;

import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * @author Karib
 *
 */
public class ReadStory {
	/** Prints array of string with wait time
	 * */
	public static void printLines(String[] lines,int waitTime) {
		for(String s: lines) {
			System.out.println(s);
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**{@link elijah.ReadStory#printLines(String[], int)}
	 * <p>1000 on waittime</p>
	 * */
	public static void printLines(String[] lines) {
		printLines(lines,2000);
	}
	/**Inrto*/
	public static void intro() {
		File file = FileUtil.getResource("resources/story/elijah/intro.txt");
		String[] lines = RW.read(file, 1, 3);
		printLines(lines);
		System.out.println("What's your name!?");
		Story.name = new Scanner(System.in).nextLine();
		System.out.println("Well professor "++" welcome aboard!");
		//Dots if the dot printout
		int dots = 5;
		System.out.print("Loading");
		for(int i=0;i<=dots;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//Close catch
			System.out.print(".");
		}
		System.out.println();
	}
	
	
	
}
