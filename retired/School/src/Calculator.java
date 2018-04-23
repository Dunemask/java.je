import java.util.Scanner;

import dunemask.dunemasking.Capture;
import dunemask.util.StringUtil;

/** 


 * 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: .Calculator.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */

/**
 * @author karib
 *
 */

public class Calculator {

		private static double ans;
		private static double[] nums = new double[2];
	/**
	 * @return
	 */
	public static int choose() {
		String msg = 
		"1 - Add\n"+
				"2 - Powering (^)\n"+
		"3 - Subtact\n"+
		"4 - Multiply\n"+
		"5 - Divide\n"+
		"6 - Square root\n"+
		"7 - Quit";
		System.out.println(msg);
		
		
		Scanner in = new Scanner(System.in);
		String choice = in.nextLine();
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
			default: System.exit(0);
			break;
		}
		

		return 0;
	}
	/**
	 * 
	 */
	private static void storeNums() {
		Scanner in = new Scanner(System.in);
		if(StringUtil.containsIgnoreCase(Capture.getInput("Use ans for num1? Y/N"),"y")) {
			nums[0]=ans;
		}else {
		System.out.print("Num1:");
		nums[0]=in.nextDouble();
		}
		System.out.println();
		if(StringUtil.containsIgnoreCase(Capture.getInput("Use ans for num2? Y/N"),"y")) {
			nums[1]=ans;
		}else {
		System.out.print("Num2:");
		nums[1]=in.nextDouble();
		}
		System.out.println();
		
		
	}

	
	
	
	
	
}
