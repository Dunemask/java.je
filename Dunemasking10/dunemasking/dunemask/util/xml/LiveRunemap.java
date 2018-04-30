/**
 * 
 */
package dunemask.util.xml;

import java.io.File;

/** Live {@link Runemap} is a live version of the Runemap that writes 
 * each time a new attribute is added
 * automatically updates
 * @param runemap
 * @author dunemask
 */
public class LiveRunemap extends Runemap {

	/** Live {@link Runemap} is a live version of the Runemap that writes 
	 * each time a new attribute is added
	 * automatically updates
	 * @param runemap
	 */
	public LiveRunemap(File runemap) {
		super(runemap);
		this.setLive(true);
	}
	
	
}
