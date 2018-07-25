/**
 * 
 */
package security;

import java.io.File;

import dunemask.util.IOUtil;
import dunemask.util.RW;
import duneternal.Github;

/**
 * @author Dunemask
 *
 */
public class SealedFileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*String dtop = System.getProperty("user.home")+"/Desktop/";
		var kp = KeyPair.gen();
		File pub = new File(dtop+"Public.dmk");
		File priv = new File(dtop+"Private.dmk");
		CryptoUtil.writePrivKey(kp.getPriv(),priv);
		CryptoUtil.writePubKey(kp.getPub(), pub);*/
		File i = Github.getSealedFile("Media/Docs/Password.txt");
		System.out.println(RW.readAll(IOUtil.FTU(i)));

	}

}
