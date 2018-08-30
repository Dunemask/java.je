/**
 * 
 */
package randomcontest;

import java.util.Random;

/**
 * @author Dunemask
 *
 */
public class RandomnumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		while(true) {
			System.out.println(rand.nextInt(4)<4);
			
		}
		

	}

}
