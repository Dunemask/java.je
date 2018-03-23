import dunemask.util.MathUtil;

/**
 * @author Karib
 *
 */
public class StudentDesks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ints = new int[] {20,21,22};
		double tmp=0;
		int totalDesks=0;
		
		for(int i=0;i<ints.length;i++) {
			if(MathUtil.isOdd(ints[i])) {
				tmp = Double.parseDouble(String.valueOf(ints[i]));
				tmp = (tmp/2)+ MathUtil.removeDecimal((tmp/2));

				totalDesks+=tmp;
				
			}else {
				totalDesks+= (ints[i]/2);
			}
		}
		System.out.println(totalDesks);
		
		
		

	}

}
