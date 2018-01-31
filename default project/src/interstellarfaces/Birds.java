/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: interstellarfaces.Birds.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package interstellarfaces;

import java.util.Scanner;

/**
 * @author karib
 *
 */
public class Birds {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		other();
		
		/*System.out.println("Create your own bird!");
		Scanner in = new Scanner(System.in);
		System.out.print("Type:");
		String name = in.nextLine();
		System.out.print("Color:");
		String color = in.nextLine();
		System.out.print("Say on Flap:");
		String onFlap = in.nextLine();
	
		in.close();
		Bird myBird = new Bird(name, color) { 
			@Override 
			public void fly() {
				System.out.println(onFlap);
			}
			   
		};
		myBird.fly();
		Bird peng = new Penguin() {
			@Override
			public void fly() {
				System.out.println("I'm a penguin and I can't fly so i am sad :(");
			}
		};
		
		Bird bj = new BlueJay();
		Bird pig = new Pigeon("Orange");
		
		peng.fly();
		bj.fly();
		pig.fly();*/
	

	}

	/**
	 * 
	 */
	private static void other() {
		Number num = new Number(7) {
			@Override 
			public int getNum() {
				return 42;
			}
		};
		System.out.println("Get Num:"+num.getNum());
		
		
	}

}
