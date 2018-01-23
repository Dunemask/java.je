/**
 * 
 */
package oneclass;

import java.io.File;
import java.util.Scanner;

import dunemask.dunemasking.Capture;
import dunemask.dunemasking.GitHub;
import dunemask.objects.movieplayer.MovieLauncher;
import dunemask.objects.movieplayer.MoviePlayer;
import dunemask.util.StringUtil;

/**
 * @author Elijah
 *
 */
public class Calulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String tmp = Capture.getInput("Listen to groovy Music? (Y/N)");
		boolean player = false;
		if(StringUtil.containsIgnoreCase(tmp, "y")) {
			player = true;
			File file = GitHub.gitFile("tmp", "resources/media/mp4/ppap.mp4");
			MovieLauncher.startPlayer(file, true);
			try {
				MovieLauncher.current.getPlayerReady().await();
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			Capture.closeConsole();
			System.out.println("Math Couldn't be made any more simple!");
			MovieLauncher.current.SetOnLoop();
		}
		
		
		
		
		boolean run = true;
		Capture.getInput("Math Performed as such: num1 operand num2 (Click Ok To Continue)");
		while(run) {
			run = calculator();
			
		}
		
		if(player) {
			String store = Capture.getInput("Close Player? (Y/N)");
			if(StringUtil.containsIgnoreCase(store, "y")) {
				System.exit(0);
			}else {
				Capture.getInput("Application Will Automatically Close on the close of the player window");
			}
		}
		
	
	}
	
	private static double ans=0;
	private static double[] nums = new double[2];

	/**
	 * @return true if run again, and not quit
	 */
	private static boolean calculator() {
		boolean run = true;;
		String msg = 
		"1 - Add\n"+
				"2 - Powering (^)\n"+
		"3 - Subtact\n"+
		"4 - Multiply\n"+
		"5 - Divide\n"+
		"6 - Square root\n"+
		"7 - Quit";
		
		Scanner in = new Scanner(System.in);
		String choice = Capture.getInput(msg);
		int input;
		//Catch Malformed Input
		try {
		 input = Integer.parseInt(choice);
		}catch(java.lang.NumberFormatException e) {
			input = 7;
		}
	
		
		switch(input) {
			case 1: storeNums(); 
				ans = nums[0] + nums[1]; 
				System.out.println("Your Answer is "+ ans);
				
			break;
			case 2: storeNums(); 
				ans = Math.pow(nums[0], nums[1]);
				System.out.println("Your Answer is "+ ans);
			
			break;
			case 3: storeNums(); 
			ans = nums[0] - nums[1]; 
			System.out.println("Your Answer is "+ ans);
			break;
			case 4:storeNums(); 
			ans = nums[0] * nums[1]; 
			System.out.println("Your Answer is "+ ans);
			break;
			case 5:storeNums(); 
			ans = nums[0] / nums[1]; 
			System.out.println("Your Answer is "+ ans);
			break;
			case 6:storeNums(); 
			Capture.getInput("Only Num1 will be used (Click Ok To Continue)");
			ans = Math.sqrt(nums[0]); 
			System.out.println("Your Answer is "+ ans);
			break;
			
			default: run = false;	
			break;
		}
		
		//System.out.println("RUn:"+run);
		return run;

	}
	public static void storeNums() {
		
		
		String store = Capture.getInput("Use prev ans for num 1 (Y/N)");
		if(StringUtil.containsIgnoreCase(store, "y")) {
			nums[0] = ans;
		}else {
			nums[0] = Integer.parseInt(Capture.getInput("First Number"));
		}
		
		 store = Capture.getInput("Use prev ans for num 2 (Y/N)");
		if(StringUtil.containsIgnoreCase(store, "y")) {
			nums[1] = ans;
		}else {
			nums[1] = Integer.parseInt(Capture.getInput("Second Number"));
		}
	
		
	}


}
