/**
 * 
 */
package dunemask.crypto;

/**
 * @author Dunemask
 *
 */
public class PubKey {

	private int pval;
	private String val;
	/**
	 * 
	 */
	PubKey(int pval) {
		this.pval = pval;
		this.val = CryptoUtil.Cipher.getCode(pval);
	}
	
	
	/**
	 * @return the pubval
	 */
	 int getPval() {
		return pval;
	}
	
	@Override
	public String toString() {
		return val;
	}
	/** 
	 * @return the code Same as toString
	 * 
	 * */
	public String getCode() {
		return toString();
	}
}
