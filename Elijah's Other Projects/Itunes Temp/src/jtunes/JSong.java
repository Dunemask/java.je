package jtunes;
import java.io.File;
import java.net.URI;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class JSong {

	private String name;
	private File file;

	/**
	 * 
	 */
	public JSong(File file,String name) {
		this.setFile(file);
		this.setName(name);
	}
	/**
	 * 
	 */
	public JSong(URI uri,String name) {
		this.setFile(new File(uri));
		this.setName(name);
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

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
