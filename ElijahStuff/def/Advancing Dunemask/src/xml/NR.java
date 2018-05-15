/**
 * 
 */
package xml;

import java.util.HashMap;

import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class NR {
	private boolean container;
	private String url;
	private String parent;
	private int listing;
	private String value;
	/** This Value will be blank if it was a double value type
	 * @see NR.value
	 * 
	 * */
	private HashMap<String,String> sub = new HashMap<String,String>();

	/**
	 * 
	 */
	public NR(int listing) {
		this.setListing(listing);
	}

	
	public void solve(String fullLine) {
		fullLine = fullLine.replace(StringUtil.tab, "");
		boolean sval = fullLine.endsWith("/>");
		boolean dval = fullLine.contains("</");
		if(!sval||!dval) {
			this.container = true;
		}
		System.out.println("****");
		var scheck = fullLine.indexOf(" ")!=-1;
		int vert = 0;
		if(!scheck) {
			vert = fullLine.indexOf(">");
		}else {
			vert = fullLine.indexOf(" ");
		}
		
		String name = fullLine.substring(fullLine.indexOf("<")+1,vert);
		String tmp=fullLine.substring(name.length()+1);
		String altTmp=tmp;
		System.out.println(altTmp+"-Start");
			for(int i=name.length()+1;i<tmp.length();i++) {
				tmp = tmp.substring(tmp.indexOf(" ")+1);
				String sname = null;
				String val = null;
				if(tmp.contains("=\"")) {
					sname = tmp.substring(0, tmp.indexOf("=\""));
					tmp = tmp.substring(tmp.indexOf("=\"")+2,tmp.length());
					val = tmp.substring(0,tmp.indexOf("\""));
					tmp = tmp.substring(tmp.indexOf("\"")+1,tmp.length());
					this.sub.put(sname, val);
				}
				
				
			}
			if(dval&&!altTmp.equals(">")) {
			altTmp = altTmp.substring(0,altTmp.lastIndexOf("</"));
			altTmp = altTmp.substring(altTmp.indexOf(">")+1,altTmp.length());
			this.value = altTmp;
			}else if(dval) {
				throw new RuntimeException("You should probably catch the second one here");
			}
		
		
		
	}
	
	
	
	
	
	/**@see NR#sub
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * @return the listing
	 */
	public int getListing() {
		return listing;
	}

	/**
	 * @param listing the listing to set
	 */
	public void setListing(int listing) {
		this.listing = listing;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the container
	 */
	public boolean isContainer() {
		return container;
	}


	/**
	 * @param container the container to set
	 */
	public void setContainer(boolean container) {
		this.container = container;
	}


	/**
	 * @return the sub
	 */
	public HashMap<String,String> getSub() {
		return sub;
	}


	/**
	 * @param sub the sub to set
	 */
	public void setSub(HashMap<String,String> sub) {
		this.sub = sub;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
