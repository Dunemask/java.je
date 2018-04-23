/**
 * 
 */
package test;

import java.util.Random;

/**
 * @author Karib
 *
 */
public class MonthDays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] daysthingy = new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31};
									//Jn,Fb,mr,ap,my,jn,jl,ag,sp,nv,dc
		Random r = new Random();
		int num;
		for(int i=0;i<5;i++) {
			num = r.nextInt(12)+1;
			System.out.println("INPUT:"+num+",Result:"+daysthingy[num]);
		}


		

	}

}
