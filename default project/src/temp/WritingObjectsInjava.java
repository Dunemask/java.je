/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: temp.WritingObjectsInjava.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package temp;

import java.io.File;

import dunemask.objects.Expression;
import dunemask.other.StringBasedEncryption;
import dunemask.util.FileUtil;
import dunemask.util.ObjectUtil;
import dunemask.util.RW;

/**
 * @author karib
 *
 */
public class WritingObjectsInjava {
	static String desktop = "C:/Users/karib/Desktop/";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String text = "These are Iphone Earbuds";
		Expression expr = StringBasedEncryption.RandomEncryptionKeyCode(2);
		int[] key = expr.getVari();
		//Scramble 
		text = StringBasedEncryption.encrypt(expr, text, key);
		//Makes a  Folder
		makeFolder();
		
		//Write objects
		writeObjects(key,expr,text);
		
		System.out.println(text);
		//readObjects
		key = (int[]) ObjectUtil.readObject(new File(desktop+"strEncr/key.DMEK"));
		expr = (Expression) ObjectUtil.readObject(new File(desktop+"strEncr/expr.DME"));
		text = (String) ObjectUtil.readObject(new File(desktop+"strEncr/code.txt"));
		/*for(int i = 0;i<Math.min(key.length, expr.getVari().length);i++) {
			System.out.println(expr.getVari()[i]+","+key[i]);
		}
		if(key.length!= expr.getVari().length) {
			System.out.println("Has more");
		}*/
		
		text = StringBasedEncryption.decrypt(expr, text, key);
		//System.out.println(text);
		

	}
	/**
	 * @param expr 
	 * @param key 
	 * @param text 
	 * 
	 */
	private static void writeObjects(int[] key, Expression expr, String text) {
		//Write Key
		ObjectUtil.writeObject(new File(desktop+"strEncr/key.DMEK"), key);
		//Write Expression
		ObjectUtil.writeObject(new File(desktop+"strEncr/expr.DME"), expr);
		//Write Scrambled Text
		ObjectUtil.writeObject(new File(desktop+"strEncr/code.txt"), text);
		
	}
	/**
	 * 
	 */
	private static void makeFolder() {
		File dtop = new File(desktop+"strEncr/");
		dtop.mkdirs();
		
		
	}

}
