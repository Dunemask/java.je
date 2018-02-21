/**
 * 
 */
package elijah;

import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Karib
 *
 */
public class Story {
	
	public static String name;
	public static int score;

	public static void start() {
		//Display info
		//ReadStory.intro();
		//ReadStory.stayOnBeach();
		//ReadStory.enterPteraDactylElijah();
		JasonReadStory.findAnotherWay();
		
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
			System.out.print("Choice:");
			choice = in.nextInt();
			System.out.println();
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
	
	public static void youDead() {
		System.out.println("YOU DEAD!");
		System.out.println("Your Score was:"+score);
		System.exit(0);
	}
	
	public static void showImage(File file) {
		JLabel lab = new JLabel(new ImageIcon("src/resources/alby.jpg"));
		lab.setSize(500, 500);
		lab.setLocation(0, 0);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(lab.getWidth()+20, lab.getHeight()+40);
		JPanel jip = new JPanel();
		jip.setLayout(null);
		jip.add(lab);
		f.add(jip);
		f.setVisible(true);
		f.setAlwaysOnTop(true);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
