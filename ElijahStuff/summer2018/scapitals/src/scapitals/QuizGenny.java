/**
 * 
 */
package scapitals;

import java.io.File;

import dunemask.util.ResourceUtil;
import dunemask.util.rw.RW;

/**
 * @author Dunemask
 *
 */
public class QuizGenny {

	public static void main(String[] args) {
		File caps = ResourceUtil.writeUrl(QuizGenny.class.getResource("/scapitals/Caps.txt"));
		File states = ResourceUtil.writeUrl(QuizGenny.class.getResource("/scapitals/States.txt"));
		String[] clines = RW.readAll(caps);
		String[] slines = RW.readAll(states);
		
		
		
		
		
	}
}
