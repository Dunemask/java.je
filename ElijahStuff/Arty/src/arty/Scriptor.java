/**
 * 
 */
package arty;

import dunemask.util.StringUtil;

/**
 * @author Dunemask
 *
 */
public class Scriptor {

	
	
	
	
	
	public static Object handle(String command) {
		if(!vdhandle(command)) {
			return rethandle(command);
		}else {
			return null;
		}
		
		
		
		
		
	}

	private static Object rethandle(String command) {
		// TODO Auto-generated method stub
		return null;
	}


	private static boolean vdhandle(String command) {
		boolean sw = false;
		if(command.startsWith("print(")) {
			String bef = command.substring("print(".length());
			System.out.println(StringUtil.replaceLast(bef, ");", ""));
		}
		
		
		
		return sw;
	}
	
	
}
