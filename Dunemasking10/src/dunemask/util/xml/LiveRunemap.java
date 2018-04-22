/**
 * 
 */
package dunemask.util.xml;

import java.io.File;

/**
 * @author dunemask
 *
 */
public class LiveRunemap extends Runemap {

	/**
	 * @param runemap
	 */
	public LiveRunemap(File runemap) {
		super(runemap);
		this.setLive(true);
	}


}
