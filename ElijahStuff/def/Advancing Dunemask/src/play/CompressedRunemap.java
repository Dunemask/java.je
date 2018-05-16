/**
 * 
 */
package play;

import java.io.File;
import java.util.HashMap;

import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class CompressedRunemap {
	private HashMap<String,CompressedRune> index = new  HashMap<String,CompressedRune>();
	private CompressedScriptProcessor sp= new CompressedScriptProcessor();
	private File file;
	
	
	
	public void addElement(String url,String valName,Object val) {
		if(url.endsWith("/")) {
			url = StringUtil.replaceLast(url, "/", "");
		}
		try {
		sp.getRune(url);
		}catch(CompressedRuneException cirue) {
			CompressedRuneElement re = (CompressedRuneElement) internalAdd(url);
			re.getValues().put(valName, String.valueOf(val));
			sp.addRune(url,re);
			this.index.put(url, re);
			//cirue.printStackTrace();
		}
		
		
	}
	/** Based on url does stuff
	 * 
	 * */
	private CompressedRune internalAdd(String url) {
		CompressedRune cr = null;
		if(url.endsWith("/")) {
			cr = new CompressedRuneSlot(url);
		}else {
			cr = new CompressedRuneElement(url);
		}
		return cr;
		
	}
	
	
	/** Compressed Runemap instance
	 * 
	 *  */
	public CompressedRunemap() {
	}
	/** Compressed Runemap instance
	 *  @param f
	 * 
	 *  */
	public CompressedRunemap(File f) {
		file = f;
	}
	public void compressedWriteOut(File f) {
		sp.assemble();
		RW.write(f, sp.fullLine, 0);
	}
	
	public void compressedWrite() {
		sp.assemble();
		RW.write(this.file, sp.fullLine, 0);
	}
	
	public static CompressedRunemap parseCompressedRunemap(File f) {
		CompressedRunemap cr = null;
		/*String line = "";
		var lines = RW.readAll(RW.FTU(f));
		for(int i=0;i<lines.size();i++) {
			line+=lines.get(i);
		}
		sp = new CompressedScriptProcessor(line);
		sp.assemble();*/
		return cr;
	}
	public HashMap<String,CompressedRune> getIndex() {
		return index;
	}
	public void setIndex(HashMap<String,CompressedRune> index) {
		this.index = index;
	}
	/**
	 * @return the sp
	 */
	public CompressedScriptProcessor getSp() {
		return sp;
	}
	/**
	 * @param sp the sp to set
	 */
	public void setSp(CompressedScriptProcessor sp) {
		this.sp = sp;
	}

}
