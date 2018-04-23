/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Karib
 *
 */
public class SortNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] numbers = new int[] {5,3,7};
		for(int i: sortNumbers(numbers)) {
			System.out.println(i);
			
		}

	}

	/**
	 * @param is
	 */
	private static ArrayList<Integer> sortNumbersRev(int[] is) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<is.length;i++) {
			al.add(is[i]);
		}
		Collections.sort(al, Collections.reverseOrder());
		
		
		
		return al;
		
		
	}
	
	/**
	 * @param is
	 */
	private static ArrayList<Integer> sortNumbers(int[] is) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<is.length;i++) {
			al.add(is[i]);
		}
		Collections.sort(al);
		
		
		
		return al;
		
		
	}

}
