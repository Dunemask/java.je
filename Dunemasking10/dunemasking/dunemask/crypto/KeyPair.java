/**
 * 
 */
package dunemask.crypto;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * @author Dunemask
 *
 */
public class KeyPair {
	private PrivKey priv;
	private PubKey pub;
	/**@param lval
	 * @param pub 
	 * 
	 */
	KeyPair(int priv, int pub) {
		var lval = priv*pub;
		this.priv = new PrivKey(priv,lval);
		this.pub = new PubKey(pub);
	}
	

	
	/** Generate a keypair with default size
	 * @return keypair
	*/
	public static KeyPair gen() {
		return gen(23170);
	}
	
	/** Generate a keypair
	 * @param difficulty
	 * <p>Bigger is more difficult to decrypt</p>
	 * 
	 * */
	static KeyPair gen(int max) {
		var pnum = calcPrime(max);
		var sr = new SecureRandom();
		int priv = pnum.get(sr.nextInt(pnum.size()));
		int pub = 0;
		do{
			pub = pnum.get(sr.nextInt(pnum.size()));
		}while(pub==priv); //Prevent them from being the same
		KeyPair kp = new KeyPair(priv,pub);
		return kp;
	}
	/** Test if the pair are indeed a pair ^^
	 * @param pub Public Key
	 * @param priv Private key
	 * @return pair || no pair
	 * */
	public static boolean verifyPair(PubKey pub,PrivKey priv) {
		int pubval = CryptoUtil.Cipher.getVal(pub.getCode());
		int prival = CryptoUtil.Cipher.getVal(priv.getCode());
		return ((pubval*prival)==priv.getUval());
		
	}
	
	
	
	
	
	private static ArrayList<Integer> calcPrime(int mx) {
		boolean[] numberList = new boolean[mx+1];
		ArrayList<Integer> nums = new ArrayList<Integer>(mx);
		for(int i=3;i<=Math.sqrt(mx);i+=2) {
			
			for(int j=i;i*j<=mx;j+=2) {
				numberList[i*j] = true;		
			}	
		}
		nums.add(2);
		for(int i=3;i<=mx;i+=2) {
			if(!numberList[i]){
				nums.add(i);
			}
			
			
		}
		
		
		return nums;
		
		
	}

	/**
	 * @return the Private Key
	 */
	public PrivKey getPriv() {
		return priv;
	}

	/**
	 * @return the Public Key
	 */
	public PubKey getPub() {
		return pub;
	}


}
