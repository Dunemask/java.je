/**
 * 
 */
package dunemask.at;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author Dunemask
 *
 */
public class TuPlet extends Plet {

	private PublicKey pub;
	private PrivateKey prkey;
	/**
	 * 
	 */
	public TuPlet() {
		KeyPair kp = Crypto.getRSAKeyPair(2048);
		this.pub = kp.getPublic();
		this.prkey = kp.getPrivate();
	}
	
	public static TuPlet load(File f,PrivateKey prkey) {
		TuPlet p = new TuPlet();
		try {
			var stream = Crypto.getBytes(f.toURI());
			byte[] bytes = Crypto.decrypt(prkey, stream);
			var full = (new String(bytes,Crypto.UTF8));
			String plines[] = full.split(System.lineSeparator());
			var lines = new ArrayList<String>(Arrays.asList(plines));	
			String line;
			String uuid;
			String data;
			final var splitChar = ":{";
			int ind = 0;
			for(int i=0;i<lines.size();i++) {
				line = lines.get(i);
				ind = line.indexOf(splitChar);
				uuid = line.substring(0,ind);
				data = line.substring(ind+splitChar.length(), line.length());
				data = StringUtil.replaceLast(data, "}", "");
				p.getMap().put(uuid, data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	/** Uses the Public key to encrypt the plet
	 * <p>Write it out in the encrypted form</p>
	 * @param out file
	 * */
	@Override
	public void writeOut(File out) {
		var tmpf = new File("");
		try {
		tmpf =	 File.createTempFile("Tuplet"+new Random().nextInt(), ".plet.tmp");
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		ArrayList<String> lines = new ArrayList<String>(getMap().size());
		var keys = new ArrayList<String>(getMap().keySet());
		String format;
		String key;
		String data;
		for(int i=0;i<keys.size();i++) {
			key = keys.get(i);
			data = getMap().get(key).toString();
			format = key+":{"+data+"}"; 
			lines.add(format);
		}
		RW.writeAll(tmpf, lines);
		
		byte[] def = Crypto.getBytes(tmpf.toURI());
		try {
			var nout = Crypto.encrypt(this.pub, def);
			Crypto.writeBytes(nout, out);
			tmpf.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @return the pub
	 */
	public PublicKey getPub() {
		return pub;
	}

	/**
	 * @param pub the pub to set
	 */
	void setPub(PublicKey pub) {
		this.pub = pub;
	}

	/**
	 * @return the prkey
	 */
	public PrivateKey getPrkey() {
		return prkey;
	}

	/**
	 * @param prkey the prkey to set
	 */
	void setPrkey(PrivateKey prkey) {
		this.prkey = prkey;
	}
	
	
	
}
