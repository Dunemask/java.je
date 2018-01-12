/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = true
 * File: lookhere.JASON.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package lookhere;

import dunemask.objects.Expression;
import dunemask.other.StringBasedEncryption;

/**
 * @author Elijah
 *
 */
public class JASON {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hello = "HELLO WORLD! - Elijah";
		System.out.println(hello);
		Expression exp = StringBasedEncryption.RandomEncryptionKeyCode(2);
		int[] keys = exp.getVari();
		String text = hello;
		text = StringBasedEncryption.encrypt(exp, text, keys);
		System.out.println("Encrypted State: "+ text);
		System.out.println("Decrypting....");
		text = StringBasedEncryption.decrypt(exp, text, keys);
		System.out.println("Decrypted State: "+ text);
		System.out.println("Dunemasking has all sorts of Tools!");
		System.out.println("Please Continue to Support Dunemasking!");
		System.out.println("For information on the products of Dunemasking please See the Dunemasking Changelog");
		System.out.println("It's located in dunemask.resources");
		//In Yo Face!
		
	}

}
