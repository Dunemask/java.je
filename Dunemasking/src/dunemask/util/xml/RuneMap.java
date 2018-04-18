/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import dunemask.util.RW;
import dunemask.util.xml.XMLRW;
/** Simple tool for writing and reading DXML's
 *  This may or may not work for other xmls
 * 
 * */
public class RuneMap {
	/***Version*/
    final static double version = 5.8;
    static final String URLStart = "$DXML$:/";
    private ArrayList<String> xmlurl;
    private HashMap<String,String> fullMap;
   // private XMLMap map;
	private File xml;
	public void tmp() {

		
		
	}
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
		if(!url.startsWith(RuneMap.URLStart)) {
			url = RuneMap.URLStart+url;
		}

		return 	this.fullMap.get(url);
		
	}
	
	
	
	
	
	
	/** Write a Container and forces the Write (Creates parents)
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeForcedContainer(String url) {
		ArrayList<String> path = this.urlToList(url);
		ArrayList<String[]> toMake = new ArrayList<String[]>();
		while(path!=null) {
			path = this.getParentPath(path);
			//System.out.println(path);
			try {
			path.get(0);
			toMake.add(path.toArray(new String[path.size()]));
			
			}catch(Exception e) {
				
			}
			
		}
		//For all the ones we need to make if it hasn't been made make it
		for(int i=toMake.size()-1;i>=0;i--) {
			ArrayList<String> lis =  new ArrayList<String>(Arrays.asList(toMake.get(i)));
			if(!this.isContByDoc(lis)||!this.itemExists(lis)) {
				this.writeContainer(this.listTourl(lis));
			}
		}
		if(!this.itemExists(this.urlToList(url))) {
		this.writeContainer(url);
		}
		
		
	}
	
	/**  Write an Element and forces the Write (Creates parents)
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeForcedElement(String url,Object value) {
		ArrayList<String> path = this.urlToList(url);
		ArrayList<String[]> toMake = new ArrayList<String[]>();
		while(path!=null) {
			path = this.getParentPath(path);
			//System.out.println(path);
			try {
			path.get(0);
			toMake.add(path.toArray(new String[path.size()]));
			
			}catch(Exception e) {
				
			}
			
		}
		//For all the ones we need to make if it hasn't been made make it
		for(int i=toMake.size()-1;i>=0;i--) {
			ArrayList<String> lis =  new ArrayList<String>(Arrays.asList(toMake.get(i)));
			if(!this.isContByDoc(lis)) {
				this.writeContainer(this.listTourl(lis));
			}
		}
		if(!this.itemExists(this.urlToList(url))) {
		this.writeElement(url, value);
		}
		
		
		
	}
	/** Returns true if found in the doc
	 * @param path Path
	 * @return true or false
	 * */
	public boolean itemExists(ArrayList<String> path) {
		return XMLRW.itemExists(getXml(), path.toArray(new String[path.size()]));
		
	}
	
	
	public ArrayList<String> getParentPath(String url){
		ArrayList<String> parent = this.urlToList(url);
		try {
		parent.remove(parent.size()-1);
		return parent;
		}catch(Exception e) {
			return null;
		}
	}
	
	public ArrayList<String> getParentPath(ArrayList<String> path){
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
		boolean container = false;
		if(url.endsWith("/")) {
			container = true;
		}
		String s = this.listTourl(this.getParentPath(url));
		if(container&&!s.endsWith("/")) {
			s+="/";
		}
		
		if(s!=null) {
			return s;
		}else {
			return null;
		}
	}
	/** Get the parent Url
	 * @param path path
	 * @return parent url
	 * */
	public String getParentUrl(ArrayList<String> path) {
		String s = this.listTourl(this.getParentPath(path));
		if(s!=null) {
			return s;
		}else {
			return null;
		}
	}
	
	/** Changes the specified Element
	 * 
	 * */
	public void changeElement(String url,Object value) {
		if(!url.startsWith(RuneMap.URLStart)) {
			url = RuneMap.URLStart+url;
		}
		this.getAllValues().remove(url);
		this.getAllValues().put(url, value.toString());
		ArrayList<String> p = this.urlToList(url);
		if(p.size()!=1) {
			XMLRW.changeElement(getXml(),p.toArray(new String[p.size()]),value);

		}else {
			XMLRW.changeTopLevelElement(getXml(), p.get(0), value);
		}
		
	}
	
	public void removeContainer(String url) {
		if(!url.startsWith(RuneMap.URLStart)) {
			url = RuneMap.URLStart+url;
		}
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
		
		if(this.itemExists(this.urlToList(url))) {
			ArrayList<String> p = this.urlToList(url);
			if(p.size()==1) {
				dunemask.util.xml.XMLRW.removeTopContainer(getXml(), p.get(0));
			}else {
				
				XMLRW.removeContainer(getXml(),p.toArray(new String[p.size()]));
			}
		}
		

	}
	
	public void removeElement(String url) {
		if(!url.startsWith(RuneMap.URLStart)) {
			url = RuneMap.URLStart+url;
		}
			this.getAllURLS().remove(url);
		if(this.isElementByDoc(this.urlToList(url))) {
			this.getAllValues().remove(url);
		}
		if(this.itemExists(this.urlToList(url))) {
			ArrayList<String> p = this.urlToList(url);
			if(p.size()==1) {
				dunemask.util.xml.XMLRW.removeTopElement(getXml(), p.get(0));
			}else {
				XMLRW.removeElement(getXml(),p.toArray(new String[p.size()]));
			}
		}
		

	}
	
	/** Write an Element
	 * @param url Url
	 * @param value Value
	 * 
	 * */
	public void writeElement(String url,Object value) {
		ArrayList<String> parent;
		if(url==null) {
			parent = new ArrayList<String>(Arrays.asList(new String[] {url}));
		}else {
			if(!url.contains(URLStart)) {
				url = URLStart + url;
			}
			parent = this.urlToList(url);

		}
			if(url.endsWith("/")) {
			url=url.substring(0,url.length()-1);
			}
		
		if(parent.size()==1) {
			if(!this.itemExists(parent)) {
			XMLRW.addTopLevelElement(getXml(), parent.get(0), value);
			}else {
				XMLRW.changeTopLevelElement(getXml(), parent.get(0), value);
			}
		}else {
			try {
				if(this.isCont(url)){
					throw new DMXMLException("TRIED TO WRITE ELEMENT USING CONTAINER FUNCTION");
				}
			}catch (Exception e) {
				throw new DMXMLException("TRIED TO WRITE ELEMENT USING CONTAINER FUNCTION (This is probably triggered because a parent element doesn't work Try to use the forcebuild method instead)");
			}
			try {
				if(!this.isElementByDoc(this.urlToList(url))) {
					
				}
			}catch (Exception e) {
				throw new DMXMLException("Boom");
			}
			
			
			
			ArrayList<String> p = new ArrayList<String>(parent);
			
			try {
				if(!this.itemExists(this.urlToList(url))) {
					p.remove(p.size()-1);
					XMLRW.addElement(getXml(), p.toArray(new String[p.size()]), parent.get(parent.size()-1),value);
				}else {
					XMLRW.changeElement(getXml(), p.toArray(new String[p.size()]),value);
				}
			}catch(Exception e) {
				throw new DMXMLException("Element "+url+" Could not be written. Try Forcing the path");
			}
		}
		addurl(url);
		this.addValue(url, String.valueOf(value));
	}
	/** Write a Container
	 * @param url Url
	 * 
	 * */
	public void writeContainer(String url) {
		ArrayList<String> parent;
		if(url==null) {
			parent = new ArrayList<String>(Arrays.asList(new String[] {url}));
		}else {
			if(!url.contains(URLStart)) {
				url = URLStart + url;
			}
			
			if(!url.endsWith("/")) {
				url+="/";

			}
			String tryelm = url.substring(0,url.length()-1);

			if(this.isElementByDoc(this.urlToList(tryelm))) {
				System.err.println("Object "+tryelm +" is Element");
				throw new DMXMLException("Item Doesn't Exist");
			}
			
			
		parent = this.urlToList(url);
			
		}
	
		if(parent.size()==1) {
			if(!this.itemExists(parent)) {
			XMLRW.addTopLevelElement(getXml(), parent.get(0), XMLRW.CONTAINER);
			}else {
				
			}
		}else {
			try {
				if(!this.isCont(this.getParentUrl(url))){
					throw new DMXMLException("TRIED TO WRITE CONTAINER  USING ELEMENT FUNCTION");
				}
				}catch (Exception e) {
					System.err.println("Tried to make container @"+this.getParentUrl(url));
					throw new DMXMLException("TRIED TO WRITE CONTAINER USING ELEMENT FUNCTION (This is probably triggered because a parent element doesn't work Try to use the forcebuild method instead)");
				}
			ArrayList<String> p = new ArrayList<String>(parent);
			p.remove(p.size()-1);
			try {
				if(!this.itemExists(this.urlToList(url))) {
					XMLRW.addElement(getXml(), p.toArray(new String[p.size()]), parent.get(parent.size()-1),XMLRW.CONTAINER);
				}
			}catch(RuntimeException e) {
				String tryelm = url.substring(0,url.length()-1);
				System.err.println("Object "+tryelm +" is Element or Doesn't Exist");
				
			}
		}
		addurl(url);
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
	
	
	
	/** Create an XML with the specified Body
	 * @param file File
	 * @param body Body Tag
	 * 
	 */
	public RuneMap(File file,String body) {
		this.setXml(file);
		setAllURLS(new ArrayList<String>());
		XMLRW.newXMLFile(file, body);
		addurl(RuneMap.URLStart+body);
		this.fullMap = new HashMap<String,String>();

	}
	
	/** Returns all the sub Urls of the parent container (Only Direct Level Below)
	 * @param url Parent Url (Container)
	 * @return ArrayList of sub elements or null
	 * 
	 * */
	public ArrayList<String> getSubURLS(String url){
		if(!url.startsWith(RuneMap.URLStart)) {
			url = RuneMap.URLStart+url;
		}
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
	 * 
	 */
	private RuneMap(File file) {
		this.xmlurl = new ArrayList<String>();
		this.fullMap = new HashMap<String,String>();
		this.setXml(file);
	}


	/** Load XML
	 * @param file File
	 * @return for Parsing DXML
	 * 
	 */
	public static RuneMap ParseDXMLMap(File file) {
		RuneMap m = null;
		m = parseXML(file);
		
		return m;

	}
	


	/**
	 * @param file 
	 * @return 
	 * 
	 */
	private static RuneMap parseXML(File file) {
		int size = RW.readAll(file).length;
		ArrayList<String> allTop = new ArrayList<String>();
		for(int i=0;i<size;i++) {
			String x = XMLRW.getTopLayerComponent(file, i)[0];
			if(!allTop.contains(x)) {
				allTop.add(x);
			}
		}
		RuneMap map = new RuneMap(file);
		ArrayList<ArrayList<String>> lis = new ArrayList<ArrayList<String>>();
		for(int i=0;i<allTop.size();i++) {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(allTop.get(i));
			lis.addAll(getAllSub(file,tmp));
			tmp.removeAll(tmp);
			map.addurl(RuneMap.URLStart+allTop.get(i));
			
		}

		map.setXml(file);
		for(int i=0;i<lis.size();i++) {
			map.addurl(listTourl(file,lis.get(i)));
			if(!map.isContByDoc(lis.get(i))) {
				map.addValue(listTourl(file,lis.get(i)), map.getValueFromXML(lis.get(i)));
			}
		}
		
		
		return map;
	}
	
	
	
	

	/**
	 * @deprecated
	 * Rip Directly from
	 * @param path 
	 * @return
	 */
	public String getValueFromXML(ArrayList<String> path) {
		if(path.size()==1) {
			return XMLRW.getTopLevelElement(getXml(), path.get(0));
		}else {
			return XMLRW.getElementValue(getXml(), path.toArray(new String[path.size()]));
		}
	}
	
	/**
	 * @param arrayList 
	 * @return
	 */
	public String getvalue(String url) {
		ArrayList<String> path = this.urlToList(url);
		if(path.size()==1) {
			return XMLRW.getTopLevelElement(getXml(), path.get(0));
		}else {
			return XMLRW.getElementValue(getXml(), path.toArray(new String[path.size()]));
		}
	}


	/**
	 * @param allTop
	 */
	private static ArrayList<ArrayList<String>> getAllSub(File xml,ArrayList<String> path) {
		ArrayList<ArrayList<String>> flist = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<String>> sub = XMLRW.getSubElementsAndContainers(xml, path.toArray(new String[path.size()])).getMap();
		ArrayList<String> subKey = new ArrayList<String>(sub.keySet());
		for( int i=0;i<subKey.size();i++) {
			ArrayList<String> p = sub.get(subKey.get(i));
			if(!XMLRW.isElement(xml, p.toArray(new String[p.size()]))) {
				flist.addAll(getAllSub(xml, p));
			}
				flist.add(p);
			
		}
		return flist;
	}
	
	private void addurl(String url) {
		if(!url.startsWith(RuneMap.URLStart)) {
			throw new DMXMLException("INVALID url");
		}else {
			this.getAllURLS().add(url);
			
		}
		
	}
	/** Add all urls
	 * @param urls to be added
	 * 
	 * */
/*	public void addAllurl(ArrayList<String> urls) {
		for(int i=0;i<urls.size();i++) {
			if(!urls.get(i).startsWith(XMLMap.URLStart)) {
				throw new DMXMLException("INVALID url @ Index:"+i+" Whose value is: "+ urls.get(i));
			}
		}
			this.getAllURLS().addAll(urls);

	}*/
	/** Add all urls
	 * @param urls to be added
	 * 
	 * 
	 * 
	 * */
/*	public void addAllurl(String[] urls) {
		for(int i=0;i<urls.length;i++) {
			if(!urls[i].startsWith(XMLMap.URLStart)) {
				throw new DMXMLException("INVALID url @ Index:"+i+" Whose value is: "+ urls[i]);
			}
		}
			this.getAllURLS().addAll(Arrays.asList(urls));

	}*/
	/** Scans doc for element and returns if it's an element
	 * @param path Path
	 * @return isElement
	 * */
	public boolean isElementByDoc(ArrayList<String> path) { 
		try {
		if(XMLRW.isElement(getXml(), path.toArray(new String[path.size()]))) {
			return !false;
		}else {
			return !true;
		}
		}catch(Exception e) {
			return false;
		}
	}
	/** Scans doc for container and returns if it's a container
	 * @param path Path
	 * @return isContainer
	 * */
	public boolean isContByDoc(ArrayList<String> path) { 
		try {
		if(XMLRW.isElement(getXml(), path.toArray(new String[path.size()]))) {
			return false;
		}else {
			return true;
		}
		}catch(Exception e) {
			return false;
		}
	}
	
	
	/** Tests if a URL is a container
	 * @param url URL
	 * @return isContainer
	 * 
	 * */
	public boolean isCont(String url) {
		if(url.endsWith("/")) {
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	/** Returns url in list form 
	 * @param url
	 * @return list form of the url
	 * */
	public ArrayList<String> urlToList(String dxmlurl){
		ArrayList<String> tmp = new ArrayList<String>();
		dxmlurl = dxmlurl.replace(RuneMap.URLStart, "");
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
	/** List to url
	 * @param list Elemenet
	 * @return DXML string
	 * 
	 * */
	public String listTourl(ArrayList<String> list){
		String val = RuneMap.URLStart;
		//GET to last one
		for(int i=0;i<list.size()-1;i++) {
			val+=list.get(i)+"/";
		}
		boolean cont = this.isContByDoc(list);
		if(cont) {
			val+= list.get(list.size()-1)+"/";
			
		}else {
			val+= list.get(list.size()-1);
		}

		return val;
		
	}
	private static String	listTourl(File file,ArrayList<String> list){
		String val = RuneMap.URLStart;
		for(int i=0;i<list.size()-1;i++) {
			val+=list.get(i)+"/";
		}
		boolean cont = !(XMLRW.isElement(file, list.toArray(new String[list.size()])));
		if(cont) {
			val+= list.get(list.size()-1)+"/";
		}else {
			val+= list.get(list.size()-1);
		}
		
		
		return val;
		
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
	 * @param xml the xml to set
	 */
	public void setXml(File xml) {
		this.xml = xml;
	}


	/**
	 * @return the xmlurl
	 */
	public ArrayList<String> getAllURLS() {
		return xmlurl;
	}

	/**
	 * @param xmlurl the xmlurl to set
	 */
	public void setAllURLS(ArrayList<String> xmlurl) {
		this.xmlurl = xmlurl;
	}

	/**
	 * @return the fullMap
	 */
	public HashMap<String, String> getAllValues() {
		return fullMap;
	}
	/** Use for externalling editing lists/urls
	 * 
	 * */
	static class URLMagic {
		
		
		/** Returns url in list form 
		 * @param url
		 * @return list form of the url
		 * */
		public static ArrayList<String> urlToList(String dxmlurl){
			ArrayList<String> tmp = new ArrayList<String>();
			dxmlurl = dxmlurl.replace(RuneMap.URLStart, "");
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
		
			if(!dxmlurl.endsWith("/")) {
				tmp.add(full);
			}
			
			return tmp;
			
		}
		/** List to url
		 * @param list Elemenet
		 * @return DXML string
		 * 
		 * */
		public static String listTourl(ArrayList<String> list,boolean container){
			String val = RuneMap.URLStart;
			//GET to last one
			for(int i=0;i<list.size()-1;i++) {
				val+=list.get(i)+"/";
			}
			if(container) {
				val+= list.get(list.size()-1)+"/";
				
			}else {
				val+= list.get(list.size()-1);
			}

			return val;
			
		}
	}


}
