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
		
		//Really Kewly Prints out the date with numbers
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
		lines = new String[] {"Stay On Beach","Go To Da Jungle"};
		int choice = Story.getChoice(lines);
		//TODO Jason Start
		switch(choice){
		case 1: stayOnBeach();
		break;
		
		case 2: // TODO goToJungle();
			JasonReadStory.wallOfTrees();
		break;
			
		}
		
		
	}
	
	/**Story Stay On Beach**/
	public static void stayOnBeach() {
		Story.score+=10;
		System.out.println("Your stomache growls but you also feel really cold");
		String[] lines = new String[] {"Look For Food","Look For Wood"};
		int choice = Story.getChoice(lines);
		switch(choice){
		case 1: foodSearch();
		break;
		
		case 2: woodSearch();
		break;
			
		}
		
	}
	
	/** path whre you search for wood
	 * 
	 */
	public static void woodSearch() {
		Story.score+=10;
		File file = FileUtil.getResource("resources/story/elijah/WoodSearch.txt");
		String[] lines = RW.readAll(file);
		printLines(lines);
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour>12) {
			nightTime();
		}else {
			dayTime();
		}
		
	}
	/** Instance when it's day
	 * 
	 */
	private static void dayTime() {
		Story.score+=10;
		System.out.println("It's now daytime!");
		Story.showImage(FileUtil.getResourceURL("resources/day.jpg"));
		System.out.println("Your Stomache Growls, Do you eat the egg?");
		int c = Story.getChoice(new String[] {"Yes","No"});
		switch(c) {
		case 1:
			System.out.println(" The angry Velociraptor Momma comes and eats you \r\n because you were gonan eat her kid! You bully!");
			Story.score+=5;
			Story.youDead();
		break;
		case 2:
			System.out.println("Because you didn't eat the egg your stomache get's angry and kills you.\r\n Your stomache grows arms and legs " + 
					" and eats you from the inside out. ");
			Story.score+=50;
			Story.youDead();
	    break;
		}
	}
	/** Instance when it's night
	 * 
	 */
	private static void nightTime() {
		Story.score+=10;
		System.out.println("It's now nighttime!");
		Story.showImage(FileUtil.getResourceURL("resources/night.png"));
		System.out.println("Your Stomache Growls, Do you eat the egg?");
		int c = Story.getChoice(new String[] {"Yes","No"});
		switch(c) {
		case 1:
			System.out.println(" The angry Velociraptor Momma comes and eats you \r\n because you were gonan eat her kid! You bully!");
			Story.score+=5;
			Story.youDead();
		break;
		case 2:
			System.out.println("  You drift to sleep with the egg by your side, the mom finds her \r\n egg and runs away. You slowly drift away\r\n" + 
					" and freeze to death from Dinothermia, (The Lack of Dinosaur eggs)");
			Story.score+=15;
			Story.youDead();
	    break;
		}
		
	}
	/** Path where you look for food
	 * 
	 * */
	public static void foodSearch() {
		Story.score+=10;
		System.out.println("You decide to look around camp and see a small berry bush");
		System.out.println("Suddenly a wild Berry Bush Appears!");
		try {
			Interactive.eatBurry();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}//Close Catch
		
		System.out.println("It Dissapears,");
		enterPteraDactylElijah();
	}
	
	/** Path where you search for things anda  pteradacylt appears
	 * 
	 */
	public static void enterPteraDactylElijah() {
		Story.score+=10;
		File file = FileUtil.getResource("resources/story/elijah/SearchForFood.txt");
		String[] lines = RW.read(file, 1, FileUtil.linesInFile(file));
		printLines(lines);
		
		int c =  Story.getChoice(new String[] {"Play Dead","Run Away"});
		switch(c){
		case 1: System.out.println("The Pteradactyl realizes your still alive swoops down and eats you.  \r\n" + "Devouring your soul along with your body");
		Story.score+=10;
		Story.youDead();
		break;
		case 2: System.out.println("You run away from the Pteradactyl and bump \n\r into a Triceratops' horn and get impailed. ");
		elijahTriceratops();
		break;	
		}
		
	}
	/** Path when a triceratops inpales
	 * 
	 */
	public static void elijahTriceratops() {
		Story.score+=10;
		int c =  Story.getChoice(new String[] {"Try To Live?","Die with Dignity"});
		switch(c) {
		case 1: System.out.println("You gasp for breath through your punctured lungs. \r\n You can barely \r\n breathe as you stumble away \r\n" + 
				"	The Angry triceratops attax you and you dead.");
			
			Story.youDead();
			Story.score+=5;
			break;
		case 2: System.out.println("You decide to give up on life and die!");
			Story.youDead();
			Story.score+=20;
			break;
		
		}
		
	}
	
	
	
}
