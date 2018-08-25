/**
 * 
 */
package aug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		int ss = 1;
		int nodeSize = 3;
		int lc = 5;
		var subs = new ArrayList<FoodBot>(ss);
		
		var food = new ArrayList<Integer>();
		for(int i=0;i<3;i++) {
			food.add(rand.nextInt(8));
		}
		
		for(int i=0;i<ss;i++) {
			var fb = new FoodBot(nodeSize);
			var ls = new ArrayList<Float>();
			for(int c=0;c<fb.inodes.length;c++) {
				ls.add(Float.valueOf(fb.inodes[c]));
			}
			Collections.sort(ls);
			for(int c=0;c<ls.size();c++) {
				fb.inodes[c] = ls.get(c);
			}
			System.out.println(ls);
			subs.add(fb);
		}
		
		
		subs.get(0);
		var fb = subs.get(0);
		//int move = move(food,fb);
		/*for(int i=0;i<fb.inodes.length;i++) {
			System.out.println(fb.inodes[i]);
		}
		System.out.println("___________");
		for(int i=0;i<fb.onodes.length;i++) {
			System.out.println(fb.onodes[i]);
		}*/

		//System.out.println("POS:"+fb.pos+",MOVE"+move+",Loc:"+food);
		
		
		
		
		
		
		
		
		
	}
	
	
	public static int move(int food,FoodBot cb) {
		int move = 0;
		float[] con = new float[4];
		con[0]= 0;
		con[con.length-1]=0;
		
		if(food>cb.inodes[1]) {
			con[1] = .9f;
			con[2] = -.9f;
					
		}else {
			con[2] = .9f;
			con[1] = -.9f;
		}
		
		
		cb.runLayer(con);
		
		
		
		
		float res1=cb.onodes[0];
		float res2=cb.onodes[1];
		System.out.println(res1+"R,L"+res2);
		
		
		return move;
	}
	
	
	
	

}
