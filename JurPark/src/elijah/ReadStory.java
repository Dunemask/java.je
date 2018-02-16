/**
 * 
 */
package elijah;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
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
		Story.score+=10;
		File file = FileUtil.getResource("resources/story/elijah/intro.txt");
		String[] lines = RW.read(file, 1, 3);
		printLines(lines);
		System.out.println("What's your name!?");
		Story.name = new Scanner(System.in).nextLine();
		System.out.println("Well professor "+Story.name+" welcome aboard!");
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
		try {
			Calendar c = Calendar.getInstance();
			System.out.print(c.get(Calendar.MONTH));
			Thread.sleep(1000);
			System.out.print(" : "+c.get(Calendar.DAY_OF_MONTH));
			Thread.sleep(1000);
			System.out.println(" : "+ c.get(Calendar.YEAR));
			Thread.sleep(1000);
			System.out.print(c.get(Calendar.DAY_OF_WEEK));
			Thread.sleep(1000);
			System.out.println(" @ "+c.getTime().getHours()+":"+c.getTime().getMinutes());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lines= RW.read(file, 4, FileUtil.linesInFile(file));
		printLines(lines);
		lines = new String[] {"1-Stay on the beach","2-Goto DA JUNGLE!"};
		int choice = Story.getChoice(lines);
		//TODO Jason Start
		switch(choice){
		case 1: stayOnBeach();
		break;
		
		case 2: // TODO goToJungle();
		break;
			
		}
		
		
	}
	
	/**Story Stay On Beach**/
	public static void stayOnBeach() {
		Story.score+=10;
		File file = FileUtil.getResource("resources/story/elijah/SearchForFood.txt");
		String[] lines = RW.read(file, 1, 3);
		printLines(lines);
		try {
			Interactive.eatBurry();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}//Close Catch
		
	}
	
	
	
}
