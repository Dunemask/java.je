/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: dunemask.dunemasking.StreamGobbler.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package dunemask.dunemasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Elijah
 *
 */
public class StreamGobbler extends Thread {
	/**Version*/
    final static double version = 4.5;
	
    InputStream is;

    /**Call start function
     * @param is Input Stream to be read*/
    public StreamGobbler(InputStream is) {
        this.is = is;
    }
    /**Call start function*/
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
                System.out.println(line);    
        } catch (IOException ioe) {
            ioe.printStackTrace();  
        }
    }
}


