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
		int ss = 2;
		int lc = 5;
		var subs = new ArrayList<FoodBot>(ss);
		for(int i=0;i<subs.size();i++) {
			var fb = new FoodBot(3);
			if(rand.nextBoolean()==true) {
				fb.pos = 1;
			}else {
				fb.pos = -1;
			}
			fb.lastMove = fb.pos;
			subs.add(fb);
		}
		
		int foodDis = rand.nextInt(10);
		System.out.println(subs.get(0).inodes.length);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public static int move(FoodBot cb) {
		int move = 0;
		/*int res1=0;
		int res2=0;
		if(warmer(res1,res2)) {
			move = cb.lastMove;
		}else {
			
		}*/
		
		
		return move;
	}
	
	/** Returns if Choice 1 is the prominent choice
	 * 
	 * */
	public static boolean warmer(int res1,int res2) {
		return res1>res2;
	}
	
	
	public static double connection() {
		int con = 0;
		return con;
	}
	
	
	
	
	
	

}
