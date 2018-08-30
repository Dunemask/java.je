/**
 * 
 */
package randomcontest;

/**
 * @author Dunemask
 *
 */
public class Inode {

	private int ic;
	private float value;

	/**
	 * 
	 */
	public Inode(int ic,float value) {
		this.setIc(ic);
		this.setValue(value);
	}

	/**
	 * @return the ic
	 */
	public int getIc() {
		return ic;
	}

	/**
	 * @param ic the ic to set
	 */
	public void setIc(int ic) {
		this.ic = ic;
	}

	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}
	public String toString() {
		return "["+"NI:"+ic+",VAL:"+this.value+"]";
		
	}
}
