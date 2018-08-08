/**
 * 
 */
package dunemask.crypto;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import dunemask.util.IOUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author Dunemask
 *
 */
public class CryptoUtil {

	/** Encrypts a file using the Public Key
	 * @param key Public Key
	 * @param file File
	 * 
	 * */
	public static void encryptFile(PubKey key,File file) {
		var in = IOUtil.getBytes(IOUtil.FTU(file));
		byte[] encrypted = null;
		try {
		encrypted = CryptoUtil.encrypt(key, in);
		}catch(java.lang.ArithmeticException e) {
			throw new RuntimeException("Ensure data is less than 200MB!");
		}
		
		file.delete();
		IOUtil.writeBytes(encrypted, file);
	}
	/** Decrypts a file using the Private Key
	 * @param key Private Key
	 * @param file File
	 * 
	 * */
	public static void decryptFile(PrivKey key,File file) {
		var in = IOUtil.getBytes(IOUtil.FTU(file));
		var decrypted = CryptoUtil.decrypt(key, in);
		file.delete();
		IOUtil.writeBytes(decrypted, file);
	}
	
	
	
	
	/** Write Public Key
	 * @param key Public Key
	 * @param file File
	 * 
	 * */
	public static void writePubKey(PubKey key,File file) {
		String code = key.getCode();
		String pform = "DMPUBKey:{"+code+"}";
		RW.writeAll(file, new ArrayList<String>(Arrays.asList(new String[] {pform})));
	}
	
	/** Write Private Key
	 * @param key Private Key
	 * @param file File
	 * 
	 * */
	public static void writePrivKey(PrivKey key,File file) {
		String code = key.getCode();
		String ucode = CryptoUtil.Cipher.getCode(key.getUval());
		String pform = "DMPKey:{"+code+"}";
		String uform = "DMKey:{"+ucode+"}";
		RW.writeAll(file, new ArrayList<String>(Arrays.asList(new String[] {pform,uform})));
	}
	/** Read Private Key
	 * @parm url
	 * @return Private Key
	 * 
	 * */
	public static PrivKey readPrivKey(URL url) {
		var lines = RW.readAll(url);
		var pcode = StringUtil.replaceLast(lines.get(0).replace("DMPKey:{", ""),"}","");
		var ucode = StringUtil.replaceLast(lines.get(1).replace("DMKey:{", ""),"}","");
		int pval = Cipher.getVal(pcode);
		int uval = Cipher.getVal(ucode);
		return new PrivKey(pval,uval);
	}
	
	/** Read Public Key
	 * @parm url
	 * @return pubkey
	 * 
	 * */
	public static PubKey readPubKey(URL url) {
		var lines = RW.readAll(url);
		var code = StringUtil.replaceLast(lines.get(0).replace("DMPUBKey:{", ""),"}","");
		int val = Cipher.getVal(code);
		return new PubKey(val);
	}

	
	/** Encrypt data
	 * @param pub Public Key
	 * @param data Data
	 * @return encrypted form of data(Use private key to decrypt)
	 * 
	 * 
	 * 
	 * */
	public static byte[] encrypt(PubKey pub,byte[] data) {
		byte[] ret = null;
		var init = new BigInteger(data);
		var mult = BigInteger.valueOf(pub.getPval());
		var out = init.multiply(mult);
		ret = out.toByteArray();
		return ret;
	}
	/** Decrypt Data
	 * @param pkey Private Key
	 * @param data
	 * @return Decrypted form of Data
	 * 
	 * 
	 * 
	 * */
	public static byte[] decrypt(PrivKey pkey,byte[] data) {
		byte[] ret = null;
		var dat = new BigInteger(data);
		var pubval = BigInteger.valueOf(pkey.getUval()/pkey.getPval());
		var out = dat.divide(pubval);
		ret = out.toByteArray();	
		return ret;
	}
	
	static class Cipher{

		
		
		static final int base = 33;
		static int getVal(String code) {
			int val = 0;
			var cval = code.toCharArray();
			var spl = new ArrayList<String>(cval.length);
			for(int i=0;i<cval.length;i++) {
				spl.add(String.valueOf(cval[i]));
			}
			String dig ="";
			int tmp = 0;
			int cout;
			char c;
			for(int i=0;i<spl.size();i++) {
				c = spl.get(i).toCharArray()[0]; //Each Is only 1
				tmp = (int)c;
				cout = (tmp-base)/9;
				dig+=(String.valueOf(cout));
			}
			val = Integer.parseInt(dig);		
			return val;
			
			
			
		}
		
		static String getCode(int pval) {
			String code = null;
			var cval = String.valueOf(pval).toCharArray();
			var dig = new ArrayList<String>(cval.length);
			for(int i=0;i<cval.length;i++) {
				dig.add(String.valueOf(cval[i]));
			}
			code = "";
			
			int tmp = 0;
			char c;
			for(int i=0;i<dig.size();i++) {
				tmp = Integer.valueOf(dig.get(i));
				 c = (char)(base+(9*tmp));
				code+= Character.toString(c);
			}		
			return code;
			
			
		}
		
		
		
		
	}
}

