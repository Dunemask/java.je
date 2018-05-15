/**
 * 
 */
package play;

/**
 * @author dunemask
 *
 */
public abstract class CompressedRune {

	private boolean container;
	private String name;

	/**
	 * 
	 */
	public CompressedRune(String name,boolean container) {
		this.setContainer(container);
		this.setName(name);
	}

	/**
	 * @return the container
	 */
	public boolean isContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(boolean container) {
		this.container = container;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


}
