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
	
	
	/** Add Value to a Compressed Runemap
	 *  @param url
	 * @param valName
	 * @param val
	 * 
	 * 
	 * */
	public void addValue(String url,String valName,Object val) {
		if(url.endsWith("/")) {
			url = StringUtil.replaceLast(url, "/", "");
		}
		if(url.contains(" ")) {
			throw new CompressedInvalidRuneUrlExcpetion("YOUR URL CONTAINS A SPACE!");
		}
		if(valName.contains(" ")) {
			throw new CompressedInvalidRuneUrlExcpetion("YOUR VALUENAME CONTAINS A SPACE!");
		}
		CompressedRuneElement re =null;
		//try {
		((CompressedRuneElement)sp.getRune(url)).getValues().put(valName, String.valueOf(val));
		((CompressedRuneElement)this.index.get(url)).getValues().put(valName, String.valueOf(val));
		/*}catch(CompressedRuneException cirue) {
			
			re = (CompressedRuneElement) internalAdd(url);
			re.getValues().put(valName, String.valueOf(val));
			sp.addRune(url,re);
			this.index.put(url, re);
			//cirue.printStackTrace();
		}*/
		
		
	}
	
	public void addRune(String url) {
		if(url.contains(" ")) {
			throw new CompressedInvalidRuneUrlExcpetion("YOUR URL CONTAINS A SPACE!");
		}
		sp.addRune(url);
		
		
	}
	
	
	public void removeRune(String url) {
		this.index.remove(url);
		sp.removeRune(url);
		
	}
	
	public void removeValue(String url,String name) {
			if(url.endsWith("/")) {
				throw new CompressedInvalidRuneUrlExcpetion("URL IS A CONTAINER NOT ELEMENT!");
			}
			
			CompressedRuneElement r =  ((CompressedRuneElement)sp.getRune(url));
			CompressedRuneElement secr = (CompressedRuneElement)this.index.get(url);
			r.getValues().remove(name);
			secr.getValues().remove(name);
	}
	
	
	public String getValue(String url,String name) {
		if(url.endsWith("/")) {
			throw new CompressedInvalidRuneUrlExcpetion("URL IS A CONTAINER NOT ELEMENT!");
		}
		//sp.printAll();
		return ((CompressedRuneElement)sp.getRune(url)).getValues().get(name);
		
	}
	
	public String pullValue(String url,String name) {
		String val = null;
		if(url.endsWith("/")) {
			throw new CompressedInvalidRuneUrlExcpetion("URL IS A CONTAINER NOT ELEMENT!");
		}
		var cre = (CompressedRuneElement)index.get(url);
		val = cre.getValues().get(name);
		return val;
		
		
		
	}
	
	/** Based on url does stuff
	 * 
	 * */
	private CompressedRune internalAdd(String url) {
		CompressedRune cr = null;
		String[] split = url.split("/");
		String name = split[split.length-1];
		if(url.endsWith("/")) {
			cr = new CompressedRuneSlot(name);
		}else {
			cr = new CompressedRuneElement(name);
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
		f.delete();
		RW.write(f, sp.fullLine, 0);
	}
	
	public void compressedWrite() {
		sp.assemble();
		this.file.delete();
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
