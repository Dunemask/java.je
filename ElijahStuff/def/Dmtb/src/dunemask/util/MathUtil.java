/**
 * 
 */
package dunemask.util;

/**Dunemasking MathUtil is a tool for the IDE development
 * <p>Remove Decimal: {@link dunemask.util.MathUtil#removeDecimal(double)}</p>
 * @author Elijah
 *
 */
public class MathUtil {
	/***Version*/
    final static double version = 4.5;
	
	/**Rips the decimal
	 * @param doub The double you want the decimal removed from
	 * @return Returns double with only decimal value
	 * **/
	public static double getDecimal(double doub) {
		
		return	(doub - (int)doub);
		
	}
	/**Returns if number is even
	 * @param number Number
	 * @return return true if Number is even, false otherwise
	 * */
	public static boolean isEven(int number) {
		boolean isEven = false;
		if((number%2)==0) {
			  isEven = true;
		}
		return isEven;
	}
	
	/**Returns if number is odd
	 * @param number Number
	 * @return return true if Number is odd, false otherwise
	 * */
	public static boolean isOdd(int number) {	
		if(!isEven(number)) {
			return true;
		}else {
			return false;
		}
		
	}


}
