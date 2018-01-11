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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import dunemask.util.FileUtil;
import dunemask.util.RW;

/**
 * @author karib
 *
 */
public class MadLibs {
	static boolean choose;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("1 - Choose, n - auto?");
		switch(new Scanner(System.in).nextInt()){
		case 1: choose = true;
		break;
		default: choose = false;
		break;
		}
		
		
		//Retrieve answers
		ArrayList<String> answers = getAnswers();
		//Display Story
		displayStory(answers);
	}

	private static void saveDoc() {
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(new File("src/madlibs/resources/output.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(out);	
		
	}

	private static void displayStory(ArrayList<String> answers) {
		System.out.println("1 - Save Output To File, n - Run Normal");
		switch(new Scanner(System.in).nextInt()){
		case 1:  saveDoc();
		break;
		}
		
		File story = FileUtil.getResource("madlibs/resources/Story.txt");
		String[] lines = RW.read(story, 0, FileUtil.linesInFile(story)-1);
		String[] ans = answers.toArray(new String[answers.size()]);
		for(int i=0;i<ans.length;i++) {
			lines[i] = lines[i].replace("%s", ans[i]);
			lines[i] = lines[i].replace("%sed", ans[i]+"ed");
			System.out.println(lines[i]);
		}
	
		
	}

	private static ArrayList<String> getAnswers() {
		ArrayList<String> ans = new ArrayList<String>();
		
		InputStream stream = System.in;
		if(!choose){
			File fixed = FileUtil.getResource("madlibs/resources/ans.txt");
			try {
				stream = new FileInputStream(fixed);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		Scanner in = new Scanner(stream);
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
