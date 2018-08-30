/**
 * 
 */
package randomcontest;

/**
 * @author Dunemask
 *
 */
public class Onode {

	private int ic;
	private float value;

	
	public void addTo(float in) {
		value+=in;
	}
	
	/**
	 * 
	 */
	public Onode(int ic,float value) {
		this.setIc(ic);
		this.setValue(value);
	}
	/**
	*	
	*/
	public Onode(int ic) {
		this.setIc(ic);
	}
	
	
	public void calcVal(Connection c) {
		this.value = c.getInode().getValue() * c.getVal();
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
