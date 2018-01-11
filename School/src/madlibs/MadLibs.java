/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: madlibs.MadLibs.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package madlibs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * @author karib
 *
 */
public class MadLibs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Retrieve answers
		ArrayList<String> answers = getAnswers();
		//Display Story
		displayStory(answers);
	}

	private static void displayStory(ArrayList<String> answers) {
		File story = FileUtil.getResource("madlibs/resources/Story.txt");
		String[] lines = RW.read(story, 0, FileUtil.linesInFile(story));
		for(int i=0;i<lines.length;i++) {
			lines[i] = lines[i].replace("%s", answers.get(i));
			lines[i] = lines[i].replace("%sed", answers.get(i)+"ed");
			System.out.println(lines[i]);
		}
		
	}

	private static ArrayList<String> getAnswers() {
		ArrayList<String> ans = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		String[] type = {"Verb","Noun","Adjective"};
		for(int c=0;c<6;c++) {
			for(int i=0;i<3;i++) {
				System.out.println("Please Provide a "+type[i]);
				ans.add(in.nextLine());
			}
		}
		
		
		return ans;
	}

}
