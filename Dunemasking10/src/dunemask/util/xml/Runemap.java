/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dunemask
 *
 */
public class Runemap {

	
	DXMLMap map;
	private boolean live = false;
	
	public static Runemap parseRunemap(File runemap) {
		Runemap map = new Runemap();
		map.xml = runemap;
		map.map = DXMLMap.ParseMap(runemap);
		map.mapAll();
		return map;
	}
	private void mapAll() {
		int siz =this.map.getAttributes().size();
		for(int i=0;i<siz;i++) {
			Attr cur = map.getAttributes().get(i);
			if(cur.isContainer()) {
				addurl(cur.getUrl());
				map(cur);
			}else {
				addurl(cur.getUrl());
				this.addValue(cur.getUrl(), cur.getValue());
			}
			
		}
		
	}
	private void map(Attr cur) {
		for(int i=0;i<cur.getChildren().size();i++) {
			Attr tmp = cur.getChildren().get(i);
			if(tmp.isContainer()) {
				addurl(tmp.getUrl());
				map(tmp);
			}else {
				addurl(tmp.getUrl());
				this.addValue(tmp.getUrl(), tmp.getValue());
			}
		}	

	}
	
	
	
	
	public Runemap(File runemap) {
		this.xml= runemap;
		runemap.delete();
		map = new DXMLMap();
		map.file = this.xml;
		this.xmlurl =(new ArrayList<String>());
		this.fullMap  = new HashMap<String,String>();
	}
	
	public Runemap() {
		map = new DXMLMap();
		this.xmlurl =(new ArrayList<String>());
		this.fullMap  = new HashMap<String,String>();
	}
	
    private ArrayList<String> xmlurl;
    private HashMap<String,String> fullMap;
   // private XMLMap map;
	private File xml;
	/** Return Keyset in ArrayList
	 * */
	public ArrayList<String> valKeySet(){
		return new ArrayList<String>(this.getAllValues().keySet());
	}
	
	
	/** Get Value from the indexer not the doc
	 * @param url
	 * @return String
	 * 
	 * */
	public String pullValue(String url) {
		return 	this.fullMap.get(url);
		
	}
	
	public void update() {
		this.map.update();
	}
	
	
	
	
	/** Write a Container and forces the Write (Creates parents)
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeForcedContainer(String url) {;
		String path = url;
		ArrayList<String> toMake = new ArrayList<String>();
		while(path!=null) {
			path = new Attr(path).getParent();
			if(path!=null) {
			toMake.add(path);
			}
			
		}
		//For all the ones we need to make if it hasn't been made make it
		
		for(int i=toMake.size()-1;i>=0;i--) {
			String tmprl=toMake.get(i);
			if(this.isCont(tmprl)&&!this.itemExists(tmprl)) {
				this.writeContainer(tmprl);
			}
		}
		
		this.writeContainer(url);
		
		
	}
	
	/**  Write an Element and forces the Write (Creates parents)
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeForcedElement(String url,Object value) {
		String path = url;
		ArrayList<String> toMake = new ArrayList<String>();
		while(path!=null) {
			path = new Attr(path).getParent();
			if(path!=null) {
			toMake.add(path);
			}
			
		}
		//For all the ones we need to make if it hasn't been made make it
		
		for(int i=toMake.size()-1;i>=0;i--) {
			String tmprl=toMake.get(i);
			if(this.isCont(tmprl)&&!this.itemExists(tmprl)) {
				this.writeContainer(tmprl);
			}
		}
		this.writeElement(url, value);
		
		
		
	}
	/** Returns true if found in the doc
	 * @param path Path
	 * @return true or false
	 * */
	private boolean itemExists(String url) {
			return map.itemExists(url);
		
	}
	
	
	ArrayList<String> getParentPath(String url){
		ArrayList<String> parent = this.urlToList(url);
		try {
		parent.remove(parent.size()-1);
		return parent;
		}catch(Exception e) {
			return null;
		}
	}
	
	ArrayList<String> getParentPath(ArrayList<String> path){
		try {
		path.remove(path.size()-1);
		return path;
		}catch(Exception e) {
			return null;
		}
	}
	/** Get the parent Url
	 * @param url Url
	 * @return parent url
	 * */
	public String getParentUrl(String url) {
		return new Attr(url).getParent();
	}

	public void writeOut() {
		map.writeOut();
	}
	public void writeOut(File f) {
		map.writeOut(f);
	}

	
	public void removeContainer(String url) {
		this.removeElement(url);
		

	}
	
	public void removeElement(String url) {
		map.removeAttr(url);
		
		for(int i=0;i<this.getAllURLS().size();i++) {
			if(this.getAllURLS().get(i).contains(url)) {
				this.getAllURLS().remove(i);
				i=0;
			}
		}
		ArrayList<String> keys = new ArrayList<String>(this.fullMap.keySet());
		for(int i=0;i<keys.size();i++) {
			if(keys.get(i).contains(url)) {
				this.fullMap.remove(keys.get(i));
				i=0;
			}
		}
		this.getXml().delete();
		if(live) {
			this.update();
			this.writeOut();
		}
		
		

	}
	
	/** Write an Element
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeElement(String url,Object value) {
		if(url.endsWith("/")) {
			url = url.substring(0, url.length()-1);
		}
		
		
		if(map.itemExists(url)) {
			map.removeAttr(url);
		}
		map.addElement(url, value);
		addurl(url);
		this.addValue(url, String.valueOf(value));
		this.getXml().delete();
		if(live) {
			this.update();
			this.writeOut();
		}
	}
	/** Write a Container
	 * @param url Url
	 * 
	 * */
	public void writeContainer(String url) {
		if(!url.endsWith("/")) {
			url+="/";
		}
		if(!map.itemExists(url)) {
		map.addContainer(url);
		}
		map.update();
		addurl(url);
		this.getXml().delete();
		if(live) {
			this.update();
			this.writeOut();
		}
	}
	
	/** Get the parent from the url
	 * @param url URL
	 * @return null if no parent else the parent;
	 * */
	public String getParent(String url) {
		ArrayList<String> parent = this.urlToList(url);
		try {
		return parent.get(parent.size()-2);
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	

	/** Returns all the sub Urls of the parent container (Only Direct Level Below)
	 * @param url Parent Url (Container)
	 * @return ArrayList of sub elements or null
	 * 
	 * */
	public ArrayList<String> getChildrenURLS(String url){
		
		if(!this.isCont(url)) {
			throw new DMXMLException("URL IS ELEMENT NOT CONTAINER");
		}
		
		ArrayList<String> match = new ArrayList<String>();
		ArrayList<String> keys = new ArrayList<String>(this.fullMap.keySet());
		for(int i=0;i<keys.size();i++) {
			if(this.getParentUrl(keys.get(i)).equals(url)) {
				match.add(keys.get(i));
			}else {
			}
		}
		try {
			match.get(0);
			
		}catch(Exception e) {
			match = null;
		}
		
		
		
		
		
		return match;
	}
	
	
	

	
	
	
	
	/**
	 * @param arrayList 
	 * @return
	 */
	public String getvalue(String url) {
		return map.getVal(url);
	}



	
	private void addurl(String url) {
		
			this.getAllURLS().add(url);
		
	}

	
	

	/** Tests if a URL is a container
	 * @param url URL
	 * @return isContainer
	 * 
	 * */
	public boolean isCont(String url) {
		try {
		return map.getAttr(url).isContainer();
		}catch(NullPointerException e) {
			return url.endsWith("/");
		}

	}
	
	
	/** Returns url in list form 
	 * @param url
	 * @return list form of the url
	 * */
	ArrayList<String> urlToList(String dxmlurl){
		ArrayList<String> tmp = new ArrayList<String>();
		String full = dxmlurl;
		for(int i=0;i<full.length();i++) {
			String c = String.valueOf(full.charAt(i));
			if(c.equals("/")) {
				tmp.add(full.substring(0, i));
				full=full.replace(full.substring(0, i+1), "");
				i=0;
			}else {
			}
		}
	
		if(!this.isCont(dxmlurl)) {
			tmp.add(full);
		}
		
		return tmp;
		
	}



	/** Does not "Add to doc" only index it
	 * 
	 * */
	private void addValue(String url,String value) {
		this.fullMap.put(url, value);
		
	}
	

	/**
	 * @return the xml
	 */
	public File getXml() {
		return xml;
	}







	/**
	 * @return the xmlurl
	 */
	public ArrayList<String> getAllURLS() {
		return xmlurl;
	}



	/**
	 * @return the fullMap
	 */
	public HashMap<String, String> getAllValues() {
		return fullMap;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	
	
}
