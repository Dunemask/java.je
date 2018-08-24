/**
 * 
 */
package aug;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Dunemask
 *
 * Hot and Cold not right or wrong
 *
 */
public class AugTest {
	
	public static void main(String[] args) {
		var rand = new Random();
		int ss = 10;
		int lc = 5;
		var subs = new ArrayList<FoodBot>(ss);
		for(int i=0;i<subs.size();i++) {
			var fb = new FoodBot();
			if(rand.nextBoolean()==true) {
				fb.pos = 1;
			}else {
				fb.pos = -1;
			}
			subs.add(fb);
		}
		
		int foodDis = rand.nextInt(10);
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public static double connection() {
		int con = 0;
		return con;
	}
	
	
	
	
	
	

}
