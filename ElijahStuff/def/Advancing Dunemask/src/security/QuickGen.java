/**
 * 
 */
package security;

import java.io.File;

import dunemask.at.Crypto;

/**
 * @author Dunemask
 *
 */
public class QuickGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	var kp =	Crypto.getRSAKeyPair(16384);
	var pr = new File(System.getProperty("user.home")+"/Desktop/Private.dmk");
	var pu = new File(System.getProperty("user.home")+"/Desktop/Public.dmk");
	Crypto.writePrivKey(kp.getPrivate(), pr);
	Crypto.writePubKey(kp.getPublic(), pu);

	}

}
