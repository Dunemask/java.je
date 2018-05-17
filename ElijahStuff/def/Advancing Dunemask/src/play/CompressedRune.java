/**
 * 
 */
package play;

import dunemask.util.StringUtil;

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
	/** Instance of Compressed rune that derives itself from it's child classes
	 * @see CompressedRuneElement
	 * @see CompressedRuneSlot
	 * @param url
	 * */
	public static CompressedRune CompressedRuneFromUrl(String url) {
		CompressedRune rune = null;
		if(url.endsWith("/")) {
			url = StringUtil.replaceLast(url, "/", "");
			String[] split = url.split("/");
			String name = split[split.length-1];
			rune = new CompressedRuneSlot(name);
		}else {
			String[] split = url.split("/");
			String name = split[split.length-1];
			rune = new CompressedRuneElement(name);
		}
		return rune;
		
	}
	

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


}
