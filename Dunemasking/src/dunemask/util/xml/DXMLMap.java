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
public class DXMLMap {
	/***Version*/
    final static double version = 5.8;
    static final String URLStart = "$DXML$:/";
    private ArrayList<String> xmlurl;
    private HashMap<String,String> fullMap;
   // private XMLMap map;
	private File xml;
	public void tmp() {

		
		
	}
	/** Write an Element
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
			if(!this.isCont(lis)) {
				this.writeContainer(this.listTourl(lis));
			}
		}
		if(!this.itemExists(this.urlToList(url))) {
		this.writeContainer(url);
		}
		
		
	}
	
	/** Write an Element
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
			if(!this.isCont(lis)) {
				this.writeContainer(this.listTourl(lis));
			}
		}
		if(!this.itemExists(this.urlToList(url))) {
		this.writeElement(url, value);
		}
		
		
		
	}
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
	
	public void changeElement(String url,Object value) {
		this.getAllValues().put(url, value.toString());
		ArrayList<String> p = this.urlToList(url);
		if(p.size()!=1) {
			XMLRW.changeElement(getXml(),p.toArray(new String[p.size()]),value);

		}else {
			XMLRW.changeTopLevelElement(getXml(), p.get(0), value);
		}
		
	}
	public void removeElement(String url) {
		ArrayList<String> p = this.urlToList(url);
		XMLRW.removeElement(getXml(),p.toArray(new String[p.size()]));
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
		
		if(parent.size()==1) {
			if(!this.itemExists(parent)) {
			XMLRW.addTopLevelElement(getXml(), parent.get(0), value);
			}else {
				
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
				if(!this.isElement(this.urlToList(url))) {
					
				}
			}catch (Exception e) {
				throw new DMXMLException("Boom");
			}
			
			
			
		ArrayList<String> p = new ArrayList<String>(parent);
		p.remove(p.size()-1);
		
			if(!this.itemExists(this.urlToList(url))) {
				XMLRW.addElement(getXml(), p.toArray(new String[p.size()]), parent.get(parent.size()-1),value);
			}
		}
		addurl(url);
		this.addValue(url, String.valueOf(value));
	}
	/** Write a Container
	 * @param url Url
	 * @param value Value
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
			if(!this.itemExists(this.urlToList(url))) {
				XMLRW.addElement(getXml(), p.toArray(new String[p.size()]), parent.get(parent.size()-1),XMLRW.CONTAINER);
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
	/**
	 * */
	public String getParent(ArrayList<String> parent) {
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
	public DXMLMap(File file,String body) {
		this.setXml(file);
		createXML();
		setAllURLS(new ArrayList<String>());
		XMLRW.newXMLFile(file, body);
		addurl(DXMLMap.URLStart+body);
		this.fullMap = new HashMap<String,String>();

	}
	
	/**
	 * 
	 */
	private DXMLMap(File file) {
		this.xmlurl = new ArrayList<String>();
		this.fullMap = new HashMap<String,String>();
		this.setXml(file);
	}


	/** Load XML
	 * @param file File
	 * @param body Body Tag
	 * @return 
	 * 
	 */
	public static DXMLMap ParseDXMLMap(File file) {
		DXMLMap m = null;
		m = parseXML(file);
		
		return m;

	}
	

	/**
	 * 
	 */
	private void createXML() {
		
	}



	/**
	 * @param file 
	 * @return 
	 * 
	 */
	private static DXMLMap parseXML(File file) {
		int size = RW.readAll(file).length;
		ArrayList<String> allTop = new ArrayList<String>();
		for(int i=0;i<size;i++) {
			String x = XMLRW.getTopLayerComponent(file, i)[0];
			if(!allTop.contains(x)) {
				allTop.add(x);
			}
		}
		DXMLMap map = new DXMLMap(file);
		ArrayList<ArrayList<String>> lis = new ArrayList<ArrayList<String>>();
		for(int i=0;i<allTop.size();i++) {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(allTop.get(i));
			lis.addAll(getAllSub(file,tmp));
			tmp.removeAll(tmp);
			map.addurl(DXMLMap.URLStart+allTop.get(i));
			
		}

		map.setXml(file);
		for(int i=0;i<lis.size();i++) {
			map.addurl(listTourl(file,lis.get(i)));
			if(!map.isCont(lis.get(i))) {
				map.addValue(listTourl(file,lis.get(i)), map.getvalue(lis.get(i)));
			}
		}
		
		
		return map;
	}
	
	
	
	

	/**
	 * @param arrayList 
	 * @return
	 */
	public String getvalue(ArrayList<String> path) {
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
		if(!url.startsWith(DXMLMap.URLStart)) {
			throw new DMXMLException("INVALID url");
		}else {
			this.getAllURLS().add(url);
			
		}
		
	}
	/** Add all urls
	 * @param urls to be added
	 * 
	 * */
	public void addAllurl(ArrayList<String> urls) {
		for(int i=0;i<urls.size();i++) {
			if(!urls.get(i).startsWith(DXMLMap.URLStart)) {
				throw new DMXMLException("INVALID url @ Index:"+i+" Whose value is: "+ urls.get(i));
			}
		}
			this.getAllURLS().addAll(urls);

	}
	/** Add all urls
	 * @param urls to be added
	 * 
	 * 
	 * 
	 * */
	public void addAllurl(String[] urls) {
		for(int i=0;i<urls.length;i++) {
			if(!urls[i].startsWith(DXMLMap.URLStart)) {
				throw new DMXMLException("INVALID url @ Index:"+i+" Whose value is: "+ urls[i]);
			}
		}
			this.getAllURLS().addAll(Arrays.asList(urls));

	}
	public boolean isElement(ArrayList<String> path) { 
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
	
	public boolean isCont(ArrayList<String> path) { 
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
	
	
	
	public boolean isCont(String url) {
		if(url.endsWith("/")) {
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	public ArrayList<String> urlToList(String dxmlurl){
		ArrayList<String> tmp = new ArrayList<String>();
		dxmlurl = dxmlurl.replace(DXMLMap.URLStart, "");
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
	public String listTourl(ArrayList<String> list){
		String val = DXMLMap.URLStart;
		//GET to last one
		for(int i=0;i<list.size()-1;i++) {
			val+=list.get(i)+"/";
		}
		boolean cont = this.isCont(list);
		if(cont) {
			val+= list.get(list.size()-1)+"/";
			
		}else {
			val+= list.get(list.size()-1);
		}

		return val;
		
	}
	private static String	listTourl(File file,ArrayList<String> list){
		String val = DXMLMap.URLStart;
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



}
