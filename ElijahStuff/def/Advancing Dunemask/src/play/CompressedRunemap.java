/**
 * 
 */
package play;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.IOUtil;
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
	
	
	public ArrayList<CompressedRune> getChildren(String url) {
		if(!url.endsWith("/")) {
			throw new CompressedInvalidRuneUrlExcpetion("URL IS AN ELEMNT NOT A CONTAINER");
		}
		return ((CompressedRuneSlot)sp.getRune(url)).getChildren(); 
		
		
		
	}
	
	
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
		
		if(!this.index.containsKey(url)) {
			sp.addRune(url);	
			this.index.put(url, CompressedRune.CompressedRuneFromUrl(url));
		}else {
			System.err.println("ATTRIBUTE AT URL ALREADY EXISTS!");
		}
	}
	
	
	public void removeRune(String url) {
		this.index.remove(url);
		sp.removeRune(url);
		if(url.endsWith("/")) {
			url = StringUtil.replaceLast(url, "/", "");
			String parent = url.substring(0, url.lastIndexOf("/"));
			var kl = new ArrayList<String>(index.keySet());
			for(int i=0;i<kl.size();i++) {
				if(kl.get(i).startsWith(parent)) {
					kl.remove(kl.get(i));
				}
			}
		}
		
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
	
	/** Get Value based on url and name 
	 * @param url
	 * @param name
	 * 
	 * */
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
	 *
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
		
	} */
	
	
	public String getParentFromURL(String url) {
		String[] split = url.split("/");
		String parpath = "";
		for(int i=0;i<split.length-1;i++) {
			parpath+=(split[i]+"/");
		}
		return parpath;
		
		
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
	
	private static CompressedRunemap tcr = null;
	public static CompressedRunemap parseCompressedRunemap(File f) {
		tcr = null;
		String line = "";
		var lines = RW.readAll(IOUtil.FTU(f));
		//System.out.println(lines);
		for(int i=0;i<lines.size();i++) {
			line+=lines.get(i);
		}
		tcr=new CompressedRunemap(f);
		tcr.sp = new CompressedScriptProcessor(line);
		tcr.sp.assemble();
		for(int i=0;i<tcr.sp.getRunes().size();i++) {
			CompressedRune cor = tcr.sp.getRunes().get(i);
			if(cor.isContainer()) {
				String curl = cor.getName()+"/";
				tcr.index.put(curl, cor);
				mapChildren(curl,(CompressedRuneSlot)cor);
			}else {
				String curl = cor.getName();
				tcr.index.put(curl, cor);
			}
		}
		
		
		return tcr;
	}
	/**
	 * @param curl
	 * @param cor
	 */
	private static void mapChildren(String curl, CompressedRuneSlot cr) {
		for(int i=0;i<cr.getChildren().size();i++) {
			CompressedRune cor = cr.getChildren().get(i);
			if(cor.isContainer()) {
				String nurl = curl+cor.getName()+"/";
				tcr.index.put(nurl, cor);
				mapChildren(nurl,(CompressedRuneSlot)cor);
			}else {
				String nurl = curl+cor.getName();
				tcr.index.put(nurl, cor);
			}
		}
	}

	public HashMap<String,CompressedRune> getIndex() {
		return index;
	}
	public void setIndex(HashMap<String,CompressedRune> index) {
		this.index = index;
	}
	/** @param file the file to set
	 * 
	 * 
	 * */
	public void setFile(File file) {
		this.file = file;
	}
	/** return the file
	 * 
	 * */
	public File getFile() {
		return file;
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
