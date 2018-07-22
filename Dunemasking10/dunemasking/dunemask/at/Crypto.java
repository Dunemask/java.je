/**
 * 
 */
package dunemask.at;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author Dunemask
 *
 */
public class Crypto {
	/** UTF-8 Encoding Style 
	 * <p>Useage: String.getBytes(Crypto.UTF8)</p>
	 * 
	 * */
	public static final String UTF8 = "UTF-8";
	
	
	/** Encrypts the File with the Public Key
	 * @param f File
	 * @param pubkey Public Key
	 * 
	 * */
	public static void encryptFile(File f,PublicKey pubkey) {
		var in = Crypto.getBytes(f.toURI());
		byte[] scrambled = null;
		try {
			scrambled = Crypto.encrypt(pubkey, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		f.delete();
		Crypto.writeBytes(scrambled, f);
	}
	/**Decrypts a File with the PrivateKey
	 * @param f File
	 * @param prkey Private Key
	 * 
	 * 
	 * */
	public static void decryptFile(File f,PrivateKey prkey) {
		var in = Crypto.getBytes(f.toURI());
		byte[] unscrambled = null;
		try {
			unscrambled = Crypto.decrypt(prkey, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		f.delete();
		Crypto.writeBytes(unscrambled, f);
	}
	
	
	
	
	
	
	
	
	
	/** Write A Byte Array
	 * @param bytes
	 * @param out
	 * 
	 * */
	public static void writeBytes(byte[] bytes,File out) {
		try{
			FileOutputStream fos = new FileOutputStream(out);
			   fos.write(bytes);
			   fos.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	/** Read bytes from URI
	 * @param uri
	 * @return array of bytes from file
	 * 
	 * */
	public static byte[] getBytes(URI uri) {
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(Paths.get(uri));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	
	
	/**Get RSA Key Pair  
	 * @param keysize
	 * @return RSA Key Pair
	 * 
	 * */
	public static KeyPair getRSAKeyPair(int keysize) {
		KeyPair pair = null;
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(keysize); // key size specified here.
			pair = keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pair;

	}
	/** Write Private Key
	 * @param priv Private Key
	 * @param out Out File
	 * 
	 * */
	public static void writePrivKey(PrivateKey priv,File out) {
		byte[] key = priv.getEncoded();
		try {
			FileOutputStream keyfos = new FileOutputStream(out);
			keyfos.write(key);
			keyfos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/** Write Public Key
	 * @param pub Public Key
	 * @param out Out File
	 * 
	 * */
	public static void writePubKey(PublicKey pub,File out) {
		byte[] key = pub.getEncoded();
		try {
			FileOutputStream keyfos = new FileOutputStream(out);
			keyfos.write(key);
			keyfos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**Get the Public Key From file!
	 * @param uri
	 * @return 
	 * @return Public Key From File;
	 */
	public static PublicKey getPubKey(URI uri) {
		PublicKey out = null;
		try {
			byte[] kb = Files.readAllBytes(Paths.get(uri));
			 X509EncodedKeySpec spec =
				      new X509EncodedKeySpec(kb);
				    KeyFactory kf = KeyFactory.getInstance("RSA");
				    out = kf.generatePublic(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;
		
	}




	/** Get Private Key from file!
	 * @param uri
	 * @return Private Key From File
	 */
	public static PrivateKey getPrivKey(URI uri) {
		PrivateKey out = null;
		try {
			byte[] kb = Files.readAllBytes(Paths.get(uri));
			 PKCS8EncodedKeySpec spec =
				      new PKCS8EncodedKeySpec(kb);
				    KeyFactory kf = KeyFactory.getInstance("RSA");
				    out = kf.generatePrivate(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return out;
	}
	/** Encrypt bytes with public key
	 * @param pub
	 * @param decrypted
	 * @return encrypted byte array
	 * 
	 * */
	public static byte[] encrypt(PublicKey pub, byte[] decrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, pub);  

        return cipher.doFinal(decrypted);  
    }
	/** Decrypt bytes with private key
	 * @param priv
	 * @param encrypted
	 * @return decrypted byte array
	 * 
	 * */
   public static byte[] decrypt(PrivateKey priv, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, priv);
        
        return cipher.doFinal(encrypted);
    }
}
