/**
 * 
 */
package play;

import java.util.ArrayList;

/**
 * @author dunemask
 *
 */
public class CompressedRuneSlot extends CompressedRune{

	private ArrayList<CompressedRune> children;

	/**
	 * 
	 */
	public CompressedRuneSlot(String name) {
		super(name,true);
		this.children = new ArrayList<CompressedRune>(1);
	}


	/**
	 * @return the children
	 */
	public ArrayList<CompressedRune> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<CompressedRune> children) {
		this.children = children;
	}

}
