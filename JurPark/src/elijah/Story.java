/**
 * 
 */
package elijah;

import java.util.Scanner;

/**
 * @author Karib
 *
 */
public class Story {
	
	public static String name;
	public static int score;

	public static void start() {
		//Display info
		ReadStory.intro();
		
	}
	
	//Get Choices from user
	public static int getChoice(String[] choices) {
		int choice;
		Scanner in = new Scanner(System.in);
		//Invalid option
		do {
			//Print Choices
			for(int i=0;i<choices.length;i++) {
				System.out.println(i+1+"-"+choices[i]);
			}		
			choice = in.nextInt();
			//Exit if 0
			if(choice==0) {
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
			if(choice>choices.length||choice<0) {
				System.out.println("Invalid choice, please select again!");
			}
			
			
		}while(choice>choices.length||choice<0);
			return choice;
	}
	
	
	
	
}
