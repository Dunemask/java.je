/**
 * 
 */
package play;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.StringUtil;

/**
 * @author Dunemask
 *
 */
public class RuneScroll {
	private CompressedRunemap rm;
	
	/** Delete a Container and all Children
	 * 
	 * */
	public void delContainer(String url) {
		if(!url.endsWith("/")){
			url+="/";
		}
		boolean noexist = this.rm.getIndex().get(url)==null;
		if(!noexist) {
			this.rm.getIndex().remove(url);
		}
	}
	
	public void addContainer(String url) {
		if(!url.endsWith("/")){
			url+="/";
		}
		//If Contains parent
		String par = rm.getParentFromURL(url);
		if(rm.getIndex().containsKey(par)||par.equals("")) {
			this.rm.addRune(url);
		}else {
			throw new CompressedRuneException("PARENT:"+par+" DOES NOT EXIST");
		}
	}
	public void addObject(String url) {
		if(url.endsWith("/")){
			url=StringUtil.replaceLast(url, "/", "");
		}
		this.rm.addRune(url);
	}
	public void addElement(String objectUrl,String elementId,Object value) {
		boolean nexist = this.rm.getIndex().get(objectUrl)==null;
		if(nexist) {
			this.addObject(objectUrl);
		}
		this.rm.addValue(objectUrl, elementId, value);
	}
	/** Get element from scroll {@link RuneScroll#getElement(String, String)}
	 *  <p>But Instead call it as a single url with the last seperator being the div between the ObjectUrl and the ElementId </p>  
	 * @param fullUrl
	 * @return value from url;
	 * */
	public String getElement(String fullUrl) {
		String oburl = fullUrl.substring(0, fullUrl.lastIndexOf("/"));
		String elmid = fullUrl.substring( fullUrl.lastIndexOf("/")+1,fullUrl.length());
		return this.getElement(oburl,elmid);
	}
	/** Write the Scroll
	 * 
	 * */
	public void write() {
		this.rm.compressedWrite();
	}
	/** WRite the Scroll to a file
	 * @param f
	 * 
	 * */
	public void write(File f) {
		this.rm.compressedWriteOut(f);
	}
	
	/** Get Element From Object 
	 * @param objectUrl
	 * @param elementId
	 * @return val;
	 * */
	public String getElement(String objectUrl,String elementId) {
		return this.rm.getValue(objectUrl, elementId);
	}
	public ArrayList<String> getChildren(String parentUrl){
		if(!parentUrl.endsWith("/")){
			parentUrl+="/";
		}
		var fl = this.rm.getChildren(parentUrl);
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0;i<fl.size();i++) {
			al.add(parentUrl+fl.get(i).getName());
		}
		return al;
		
	}
	
	RuneScroll()
	{
		rm = new CompressedRunemap();
	}
	/**
	 * 
	 */
	public RuneScroll(File f) {
		rm = new CompressedRunemap(f);
		
	}
	
	/** Get Parent From Url
	 * 
	 * */
	public String getParentFromURL(String parentUrl) {
		return rm.getParentFromURL(parentUrl);
	}
	/**
	 * @param parseCompressedRunemap
	 */
	 RuneScroll(CompressedRunemap rm) {
		this.rm = rm;
	}
	 /** Parse RuneScroll from a file;
	  * @param f
	  * @return RuneScroll
	  * 
	  * */
	public static RuneScroll ParseScroll(File f) {
		RuneScroll scroll = new RuneScroll(CompressedRunemap.parseCompressedRunemap(f));
		return scroll;
	}
	
	public void changeElement(String objectUrl,String elementId,Object value) {
		boolean nexist = this.rm.getIndex().get(objectUrl)==null;
		if(!nexist) {
			this.rm.removeValue(objectUrl, elementId);
			this.rm.addValue(objectUrl, elementId, value);
		}else {
			throw new RuntimeException("RUNE DOES NOT EXIST!");
		}

	}
	/**
	 * @return the rm
	 */
	public CompressedRunemap getRm() {
		return rm;
	}
	/**
	 * @param rm the rm to set
	 */
	public void setRm(CompressedRunemap rm) {
		this.rm = rm;
	}
}
