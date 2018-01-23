/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: limit.FinalAssembly.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link limit }</p>
 */
package limit;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link limit }</p>
 */
public class FinalAssembly {

	public static void main(String[] args) {
		LinkedHashMap<String,Runnable> mp = new RunProgram().addAll();
		
		Set<String> set = mp.keySet();
		String[] keys = set.toArray(new String[set.size()]);
		
		for(String key:keys) {
			mp.get(key).run();
		}
	}
	
	
}
