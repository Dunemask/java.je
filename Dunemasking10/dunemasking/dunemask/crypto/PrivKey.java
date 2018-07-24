/**
 * 
 */
package dunemask.crypto;

/**
 * @author Dunemask
 *
 */
public class PrivKey {

	private int uval;
	private int pval;
	private String val;
	/**Private Key
	 * @param pval Private Value
	 * @param uval Unlock Value
	 * 
	 */
	PrivKey(int pval,int uval) {
		this.pval = pval;
		this.uval = uval;
		this.val = CryptoUtil.Cipher.getCode(pval);
	}
	/**
	 * @return the uval
	 */
	int getUval() {
		return uval;
	}

	
	/**
	 * @return the pval
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
