/**
 * 
 */
package play;

import java.util.HashMap;

/**
 * @author dunemask
 *
 */
public class CompressedRuneElement extends CompressedRune{

	private HashMap<String,String> values;

	/**
	 * 
	 */
	public CompressedRuneElement(String name,String valName,Object value) {
		super(name,false);
		this.setName(name);
		this.values = new HashMap<String,String>();
		this.getValues().put(valName,String.valueOf(value));
	}
	public CompressedRuneElement(String name) {
		super(name,false);
		this.values = new HashMap<String,String>();
	}




	/**
	 * @return the values
	 */
	public HashMap<String,String> getValues() {
		return values;
	}


	/**
	 * @param values the values to set
	 */
	public void setValues(HashMap<String,String> values) {
		this.values = values;
	}



}
