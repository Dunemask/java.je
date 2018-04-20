/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dunemask.objects.ArrayListState;
import dunemask.util.FileUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class OXMLRW {
	/***Version*/
    final static double version = 5.8;
    /** No Body for newXMLFile
     **/
    public static final String NOBODY = "$none$";
    
    /** Container Identifier
     * */
    public static final String CONTAINER = "$container$";
    
    /**@param element
     * @return Element in XML Element Form
     * */
    public static String element(String element) {
    	return "<"+element+">";
    }
    
    /**@param element
     * @return Element in XML Closing Element Form
     * */
    public static String closeElement(String element) {
    	return "</"+element+">";
    }
    
    /**@param element
     * @return Element in XML Closing Element Form
     * */
    public static String ripElement(String element) {
    	//System.out.println("IN:"+element);
    	if(element.startsWith("</")) {
    		element = element.substring(element.indexOf("</")+2, element.length()-1);
    		
    	}else if(element.contains(">")){

    		element = element.substring(1,element.indexOf(">"));
    	}
    	
    	if(element.contains("UID=")) {
    		//System.out.println("True");
    		element = OXMLRW.removeUID(element);
    	}
    	//System.out.println("OUT:"+element);
    	
    	return element;
    }
    public static String removeUID(String rippedElement) {
    	String elm = rippedElement;
    	//System.out.println("Elm Full:"+elm);
    	int ind = elm.indexOf(" UID=");
    	elm = elm.substring(0, ind);
    	//System.out.println("Changed:"+elm);
    	
    	
    	return elm;
    	
    }
    
    
    
    
    /** Creates a File in the specified location, (handles parent folders)
     * <p>Gives it a body as well, if no body is needed then use {@link dunemask.util.xml.OXMLRW#NOBODY} For the arg</p>
     * @param file File
     * @param body Body Element
     * 
     **/
    public static void newXMLFile(File file,String body) {
    	file.delete();
    	if(body.equalsIgnoreCase(NOBODY)||body==null){
    		RW.write(file,"",RW.firstLine);
    	}else {
    		RW.writeAll(file, new String[] {element(body),closeElement(body)});
    	}
    	
    }
    
    /** @param file File
     * @param element Element
     * @param value Value XMLRW.container for container
     * 
     * */
    public static void addTopLevelElement(File file,String element,Object value) {
    	String[] lines = RW.readAll(file);
    	int l = lines.length;
    	if(value.equals(OXMLRW.CONTAINER)) {
    		RW.write(file, OXMLRW.element(element), l+1);
    		RW.write(file, OXMLRW.closeElement(element), l+2);
    	}else {
    		RW.write(file, OXMLRW.element(element)+value+OXMLRW.closeElement(element), l+1);
    	}
    	
    }

    /** @param file File
     * @param element Element
     * @param value Value
     * @param uid UID
     * 
     * */
    public static void addTopLevelElementWithUID(File file,String element,Object value,String uid) {
    	String[] lines = RW.readAll(file);
    	int l = lines.length;
    	if(element.equals(OXMLRW.CONTAINER)) {
    		RW.write(file, OXMLRW.element(element), l+1);
    		RW.write(file, OXMLRW.closeElement(element), l+2);
    	}else {
    		RW.write(file, OXMLRW.element(element+" UID="+uid)+value+OXMLRW.closeElement(element), l+1);
    	}
    	
    }
    
    
    
    /** Adds a container in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the container to be added
     * @param container Name of Container
     * 
     * */
    public static void addElementContainerWithUID(File file,String[] parentElementChain,String container,String uid) {
    	addElementWithUID(file,parentElementChain,container,CONTAINER,uid);
    	
    }
    
    /** Adds a container in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the container to be added
     * @param container Name of Container
     * 
     * */
    public static void addElementContainer(File file,String[] parentElementChain,String container) {
    	addElement(file,parentElementChain,container,CONTAINER);
    	
    }
    
   /* public static ArrayList<String> getContainerByUID(File file,String uid){
    	int line = FileUtil.containsInDocument(file, "UID="+uid);
    	int tabCount=0;
    	String fv = RW.read(file, line);
    	String element = fv.replace(" UID="+uid, "");
    	while(element!=element.replace(StringUtil.tab, "")) {
    		////System.out.println(element);
    		element=element.replaceFirst(StringUtil.tab, "");
    		tabCount++;
    	}
    	System.out.println(element);
    	ArrayList<String> path = new ArrayList<String>();
    	path.add(element.replace("<", "").replace(">", ""));
    	String[] lines = RW.read(file, 0, line-1);
    	////System.out.println(lines[lines.length-1]);
    	for(int i=1;i<tabCount;i++) {
    		String ful = lines[lines.length-tabCount];
			if(ful.contains(" UID=")) {
				int index = ful.indexOf(" UID=");
				ful = ful.replace(StringUtil.tab,"").substring(1, index);
				////System.out.println("Changed:"+ful);
			}
			String simp=ful.replace(" ", "");
    		////System.out.println(simp);
    		path.add(0, simp);
    	}
    	////System.out.println(path);
    	return path;
    	
    	
    	
    }
    
    public static ArrayList<String> getElementByUID(File file,String uid){
    	int line = FileUtil.containsInDocument(file, "UID="+uid);
    	int tabCount=0;
    	String fv = RW.read(file, line);
    	String element = fv.replace(" UID="+uid, "");
    	while(element!=element.replace(StringUtil.tab, "")) {
    		////System.out.println(element);
    		element=element.replaceFirst(StringUtil.tab, "");
    		tabCount++;
    	}
    	//System.out.println("Wanted El:"+element);
    	int ind = element.indexOf(">");
    	ArrayList<String> path = new ArrayList<String>();
    	path.add(element.substring(0,ind).replace("<", ""));
    	String[] lines = RW.read(file, 0, line-1);
    	////System.out.println(lines[lines.length-1]);
    	for(int i=1;i<tabCount;i++) {
    		String ful = lines[lines.length-tabCount];
			if(ful.contains(" UID=")) {
				int index = ful.indexOf(" UID=");
				ful = ful.replace(StringUtil.tab,"").substring(1, index);
			}
			String simp=ful.replace(" ", "");
    		////System.out.println(simp);
    		path.add(0, simp);
    	}
    	//System.out.println("FinalPath Too: "+path);
    	return path;
    	
    	
    	
    }*/
    
    /** Removes a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void changeElement(File file,String[] parentElementChain,Object value) {
    	if(OXMLRW.itemExists(file, parentElementChain)) {
    		int low=0,high=FileUtil.linesInFile(file);
        	int tabs =0;
        	if(parentElementChain!=null) {
    	    	for(int i=0;i<parentElementChain.length;i++) {
    	    		String element = parentElementChain[i];
    	    		element = OXMLRW.ripElement(element);
    	    		int tlow = FileUtil.containsInDocumentBounds(file,element(element), low, high);
    	    		int thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
    	    		low=tlow;
    	    		high=thigh;
    	    		tabs++;
    	    	}
        	}
        	System.out.println("HIgh:"+high);
        	String tab = "";
        	for(int i=0;i<tabs;i++) {
        		tab+=StringUtil.tab;
        	}
        	String element = parentElementChain[parentElementChain.length-1];
        	//Sequential
        	RW.write(file, tab+OXMLRW.element(element)+value+OXMLRW.closeElement(element), high);
    	}else {
    		throw new RuntimeException("NOBEUENO! IT DON'T EXIST!");
    	}
    	

    }
    
    
    /** Removes a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void removeElement(File file,String[] parentElementChain) {
    	int low=0,high=FileUtil.linesInFile(file);
    	if(parentElementChain!=null) {
	    	for(int i=0;i<parentElementChain.length;i++) {
	    		String element = parentElementChain[i];
	    		element = OXMLRW.ripElement(element);
	    		int tlow = FileUtil.containsInDocumentBounds(file,element(element), low, high);
	    		int thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
	    		low=tlow;
	    		high=thigh;
	    	}
    	}
    	
    	//Sequential
    	if(low+1==high) {
    		RW.write(file, "", high);
    	}else if(low+1==high) {
    		RW.write(file, "", high);
    	}else if(low<high) {
    		RW.write(file, "", high);
    	}
    }
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void addElementWithUID(File file,String[] parentElementChain,String newElement,Object value,String uid) {
    	//System.out.println(uid);
    	int low=0,high=FileUtil.linesInFile(file);
    	int tabs=0;
    	if(parentElementChain!=null) {
	    	for(int i=0;i<parentElementChain.length;i++) {
	    		String element = parentElementChain[i];
	    		int tlow;
	    		int thigh;
	    		try {
	    		tlow = FileUtil.containsInDocumentBounds(file,element(element), low, high);
	    		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
	    		}catch(RuntimeException e) {
	    			String op = element(element);
	    			tlow = FileUtil.containsInDocumentBounds(file,op.substring(0,element.length()-1), low, high);
	        		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
	    		}
	    		low=tlow;
	    		high=thigh;
	    		tabs++;
	    	}
    	}
    	String tab = "";
    	for(int i=0;i<tabs;i++) {
    		tab+=StringUtil.tab;
    	}
    	////System.out.println("H:"+high+",L:"+low);
    	//Sequential
    	if(low+1==high&&!value.toString().equalsIgnoreCase(CONTAINER)) {
    		RW.insertLine(file, tab+element(newElement+" UID="+uid)+value+closeElement(newElement), high);
    	}else if(low+1==high) {
    		value = System.lineSeparator();
    		RW.insertLine(file, tab+element(newElement+" UID="+uid)+value+tab+closeElement(newElement), high);
    	}else if(low<high&&!value.toString().equalsIgnoreCase(CONTAINER)) {
    		RW.insertLine(file, tab+element(newElement+" UID="+uid)+value+closeElement(newElement), high);
    	}else if(value.toString().equalsIgnoreCase(CONTAINER)){
    		value = System.lineSeparator();
    		RW.insertLine(file, tab+element(newElement+" UID="+uid)+value+tab+closeElement(newElement), high);
    	}
    	
    	if(low==high) {
    		throw new RuntimeException("Invalid Dunemask XML Doc");
    	}
    	
    	
    }
    
    
    
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void addElement(File file,String[] parentElementChain,String newElement,Object value) {
    	int low=0,high=FileUtil.linesInFile(file);
    	int tabs=0;
    	if(parentElementChain!=null) {
	    	for(int i=0;i<parentElementChain.length;i++) {
	    		String element = parentElementChain[i];
	    		element = OXMLRW.ripElement(element);
	    		String se = element(element);
	    		String ce = closeElement(element);
	    		se = se.substring(0, se.length()-1);
	    		int tlow;
	    		int thigh;
	    		try {
	    		tlow = FileUtil.findInDocumentBounds(file,se, low, high);
	    		thigh = FileUtil.findInDocumentBounds(file,ce, low, high);
	    		}catch(RuntimeException e) {
		    		tlow = FileUtil.containsInDocumentBounds(file,se, low, high);
		    		thigh = FileUtil.containsInDocumentBounds(file,ce, low, high);
	    		}
	    		low=tlow;
	    		high=thigh;
	    		tabs++;
	    	}
    	}
    	String tab = "";
    	for(int i=0;i<tabs;i++) {
    		tab+=StringUtil.tab;
    	}
    	//System.out.println(low+","+high);
    	////System.out.println("H:"+high+",L:"+low);
    	//Sequential
    	if(low+1==high&&!value.toString().equalsIgnoreCase(CONTAINER)) {
    		RW.insertLine(file, tab+element(newElement)+value+closeElement(newElement), high);
    	}else if(low+1==high) {
    		value = System.lineSeparator();
    		RW.insertLine(file, tab+element(newElement)+value+tab+closeElement(newElement), high);
    	}else if(low<high&&!value.toString().equalsIgnoreCase(CONTAINER)) {
    		RW.insertLine(file, tab+element(newElement)+value+closeElement(newElement), high);
    	}else if(value.toString().equalsIgnoreCase(CONTAINER)){
    		value = System.lineSeparator();
    		RW.insertLine(file, tab+element(newElement)+value+tab+closeElement(newElement), high);
    	}
    	
    	if(low==high) {
    		throw new RuntimeException("Invalid Dunemask XML Doc");
    	}
    	
    	
    }
    /** Adds a elements in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added (All Under the same Hiarchy)
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void addElements(File file,String[] parentElementChain,String[] newElement,Object[] value) {
    	for(int i=0;i<value.length;i++) {
    		addElement(file,parentElementChain,newElement[i],value[i]);
    	}
    	
    }
    
    
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void addElements(File file,String[][] parentElementChain,String[] newElement,Object[] value) {
    	for(int i=0;i<value.length;i++) {
    		addElement(file,parentElementChain[i],newElement[i],value[i]);
    	}
    	
    }
    
    /** Returns elements from the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the top container of a series of Elements
     * @return ArrayList of Elements
     *
    public static ArrayList<String> getElementsValues(File file,String[] parentElementChain){
    	ArrayList<String> containers = XMLRW.getElements(file, parentElementChain);
		return XMLRW.getElementsValues(file, parentElementChain, containers);
    }*/
    
    
    
    /** Returns elements from the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the top container of a series of Elements
     * @param cont ArrayList of Each Container
     */
    public static ArrayList<String> getElementsValues(File file,String[] parentElementChain,ArrayList<String> cont){
    	ArrayList<String> fullArch = new ArrayList<String>(Arrays.asList(parentElementChain));
    	ArrayList<String> vals = new ArrayList<String>();
    	for(int i=0;i<cont.size();i++) {
    		ArrayList<String> tmp = new ArrayList<String>();
    		tmp.addAll(fullArch);
    		tmp.add(cont.get(i));
    		if(OXMLRW.isElement(file, tmp.toArray(new String[tmp.size()]))) {
    		vals.add(OXMLRW.getElementValue(file, tmp.toArray(new String[tmp.size()])));
    		}
    	}
		return vals;
    }
    
    
    public static String[] getTopLayerComponent(File file,int count) {
    	String ret = "";
    	String[] lines = RW.readAll(file);
    	for(int i=0;i<lines.length;i++) {
    		//If Not Element container
    		if(lines[i].equals(lines[i].replace(StringUtil.tab, ""))) {
	    		if(!OXMLRW.isElement(file, new String[] {lines[i]})) {
	    			ret = lines[i];
	    			ret = OXMLRW.ripElement(ret);
	    			count--;
	    		}
	    		if(count==0) {
	    			i=lines.length;
	    		}
    		}
    		
    		
    	}
    	
    	return new String[] {ret};
    }
    
    
    /** Get The level directly below
     *  @param file XMLDoc
     *  @param parentElementChain Chain down to desired location
     * @return ArrayListState of all found (HashMap<String,ArrayList<String>>)
     * 
     * 
     * */
    public static ArrayListState getSubElementsAndContainers(File file,String[] parentElementChain) {
    	ArrayListState map = new ArrayListState();
    	ArrayList<String> par = new ArrayList<String>(Arrays.asList(parentElementChain));
    	int low=0,high=FileUtil.linesInFile(file);
    	int tabs=0;
    	if(parentElementChain!=null) {
	    	for(int i=0;i<parentElementChain.length;i++) {
	    		String element = parentElementChain[i];
	    		element = OXMLRW.ripElement(element);
	    		String se = element(element);
	    		String ce = closeElement(element);
	    		se = se.substring(0, se.length()-1);
	    		//System.out.println(element);
	    		int tlow;
	    		int thigh;
	    		try {
	    		tlow = FileUtil.findInDocumentBounds(file,se, low, high);
	    		thigh = FileUtil.findInDocumentBounds(file,ce, low, high);
	    		}catch(RuntimeException e) {
		    		tlow = FileUtil.containsInDocumentBounds(file,se, low, high);
		    		thigh = FileUtil.containsInDocumentBounds(file,ce, low, high);
	    		}
	    		low=tlow;
	    		high=thigh;
	    		tabs++;
	    	}
    	}
    	//System.out.println(tabs);
    	for(int i=1;i<high-low;i++) {//lines inbetween
    		String l = RW.read(file, low+i);
    		//System.out.print(l);
    		int t = 0;
    		while(l.replaceAll(StringUtil.tab, "")!=l) {
    			l=l.replaceFirst(StringUtil.tab, "");
    			t++;
    		}
    		//l = XMLRW.removeUID(l);
    		if(t!=tabs) {
    		//System.out.println("Text:"+l+" Failed in comparison tabs:"+tabs+"text:"+t);
    		}else if(l.charAt(1)=='/'){
    		//System.out.println("Caught");
    		}else {
    			String full = l;
    			String uid="";
    			if(full.contains("=UID")) {//If it has we need to get the uid
    				int endIndexOfUID = full.indexOf("=UID")+4;
    				if(full.contains(">")) {
    					uid=  full.substring(endIndexOfUID, full.indexOf(">"));
    				}else {
    					uid=  full.substring(endIndexOfUID);
    				}
    			}else {//Else doesnt have uid
    				uid = OXMLRW.ripElement(full);
    			}
    			full = OXMLRW.ripElement(full);
    			if(map.getMap().containsKey(uid)) {
    			int r =	new Random().nextInt(Integer.MAX_VALUE);
    			uid+=String.valueOf(r);
    			}
    			
    	    	full = OXMLRW.ripElement(full);
	    		//System.out.println("Call:"+full);
    			ArrayList<String> tmp = new ArrayList<String>();
    			tmp.addAll(par);
    			tmp.add(full);
    			map.addState(tmp,uid);
    		}
    		
    	}
    	
    	return map;
    }

    
    /** Get All Next Level Elements
     * @param file XML Doc
     * @param parentElementChain Highearchy down to top element who's sub-elements will be "Searched"
     * @return List of the Sub Elements (not values) To obtain the values for
     * 
     * 
    public static ArrayList<String> getElements(File file,String[] parentElementChain) {
    	ArrayList<String> elms = new ArrayList<String>();
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		int tlow;
    		int thigh;
    		try {
    		tlow = FileUtil.containsInDocumentBounds(file,element(element), low, high);
    		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
    		}catch(RuntimeException e) {
    			String op = element(element);
    			tlow = FileUtil.containsInDocumentBounds(file,op.substring(0,element.length()-1), low, high);
        		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
    		}
    		low=tlow;
    		high=thigh;
    	}
    	////System.out.println(RW.read(file, low)+","+RW.read(file, high));
    	String[] lines = RW.read(file, low+1, high-1);
    	for(int i=0;i<lines.length;i++) {
    		String full = lines[i];
    		int fmark=0;
    		for(int c=0;c<full.length();c++) {
    			fmark++;
    			if(String.valueOf(full.charAt(c)).equalsIgnoreCase("<")) {
    				c=full.length();
    			}
    		}
    		
    		int smark=0;
    		for(int c=0;c<full.length();c++) {
    			smark++;
    			if(String.valueOf(full.charAt(c)).equalsIgnoreCase(">")) {
    				c=full.length();
    			}
    		}
    		smark--;
    		if(!full.substring(fmark, fmark+1).equalsIgnoreCase("/")) {
    			elms.add(full.substring(fmark, smark));
    		}
    	}
    	return elms;
    }*/
    
    
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static String getElementValue(File file,String[] parentElementChain) {
    	//System.out.println(Arrays.asList(parentElementChain));
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		int tlow;
    		int thigh;
    		element = OXMLRW.ripElement(element);
    		tlow = FileUtil.containsInDocumentBounds(file,element(element).substring(0, element.length()), low, high);
    		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
    		
    		low=tlow;
    		high=thigh;
    	}
    	String ob = null;
        //Low Should Equal HIgh
        if(high!=low) {
        	throw new RuntimeException("Invalid Dunemask XML Doc, Container "+ parentElementChain[parentElementChain.length-1]+" Found; not Element");
        }else {
        	String full =  RW.read(file, low);
        	/*
        	int mark=0;
        	for(int i=0;i<full.length();i++) {
        		mark++;
        		if(String.valueOf(full.charAt(i)).equalsIgnoreCase("<")) {
        			i=full.length();
        		}
        	}
        	full = full.substring(mark-1);
        	full= full.replaceAll("<"+lastElement+">", "");
        	full =full.replace("</"+lastElement+">", "");
        	ob = full;*/
        	full = full.replaceFirst("<", "");
        	int ind1 = full.indexOf(">");
        	int ind2 = full.indexOf("<");
        	ob = full.substring(ind1+1, ind2);
        	
        }
        
    	return ob;
    }
    public static String getValueByUID(File file,String uid) {
    	int line = FileUtil.containsInDocument(file, " UID="+uid);
    	String full =  RW.read(file, line);
    	/*
    	int mark=0;
    	for(int i=0;i<full.length();i++) {
    		mark++;
    		if(String.valueOf(full.charAt(i)).equalsIgnoreCase("<")) {
    			i=full.length();
    		}
    	}
    	full = full.substring(mark-1);
    	full= full.replaceAll("<"+lastElement+">", "");
    	full =full.replace("</"+lastElement+">", "");
    	ob = full;*/
    	full = full.replaceFirst("<", "");
    	int ind1 = full.indexOf(">");
    	int ind2 = full.indexOf("<");
    	return full.substring(ind1+1, ind2);
    }
    /** Tests if directed path is element
     * @param file XML doc
     * @param parentElementChain Hiarchy down to the element to be added
     * @return if Directed Path is element, assuming path is accurate
     * */
    public static boolean itemExists(File file,String[] parentElementChain) {
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		element = OXMLRW.ripElement(element);
    		//System.out.println(element);
    		int tlow; int thigh;
    		try {
	    		String se = element(element);
	    		String ce = closeElement(element);
	    		se = se.substring(0, se.length()-1);
	    		try {
	    		tlow = FileUtil.findInDocumentBounds(file,se, low, high);
	    		thigh = FileUtil.findInDocumentBounds(file,ce, low, high);
	    		}catch(RuntimeException e) {
		    		tlow = FileUtil.containsInDocumentBounds(file,se, low, high);
		    		thigh = FileUtil.containsInDocumentBounds(file,ce, low, high);
	    		}
    		}catch(java.lang.RuntimeException e) {
					return false;
    		}
    		
    		low=tlow;
    		high=thigh;
    	}
    	return true;
    }
    
    
    
    
    
    
    
    
    /** Tests if directed path is element
     * @param file XML doc
     * @param parentElementChain Hiarchy down to the element to be added
     * @return if Directed Path is element, assuming path is accurate
     * */
    public static boolean isElement(File file,String[] parentElementChain) {
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		element = OXMLRW.ripElement(element);
    		//System.out.println(element);
    		int tlow; int thigh;
    		try {
	    		String se = element(element);
	    		String ce = closeElement(element);
	    		se = se.substring(0, se.length()-1);
	    		try {
	    		tlow = FileUtil.findInDocumentBounds(file,se, low, high);
	    		thigh = FileUtil.findInDocumentBounds(file,ce, low, high);
	    		}catch(RuntimeException e) {
		    		tlow = FileUtil.containsInDocumentBounds(file,se, low, high);
		    		thigh = FileUtil.containsInDocumentBounds(file,ce, low, high);
	    		}
    		}catch(java.lang.RuntimeException e) {
					throw new RuntimeException("Could Not Find:"+element);

    			//return false;
    		}
    		
    		low=tlow;
    		high=thigh;
    	}

        //Low Should Equal HIgh
        if(high!=low) {
        	//System.out.println(RW.read(file, low));
        	//System.out.println(RW.read(file, high));
        	return false;
        }else {
        	//System.out.println(RW.read(file, low));
        	return true;
        }
    }
    
    /** Tests if directed path has UID
     * @param file XML doc
     * @param parentElementChain Hiarchy down to the element to be added
     * @return if Directed Path is element, assuming path is accurate
     * */
    public static boolean hasUID(File file,String[] parentElementChain) {
    	String uid = getUID(file, parentElementChain);
    	if(uid!=null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    /** Tests if directed path has UID
     * @param file XML doc
     * @param parentElementChain Hiarchy down to the element to be added
     * @return Null if none found, otherwise UID
     * */
    public static String getUID(File file,String[] parentElementChain) {
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		element = OXMLRW.ripElement(element);
    		int tlow;
    		int thigh;
    		tlow = FileUtil.containsInDocumentBounds(file,element(element), low, high);
    		thigh = FileUtil.containsInDocumentBounds(file,closeElement(element), low, high);
    		
    		
    		low=tlow;
    		high=thigh;
    	}
    	
    	String full = RW.read(file, low);
    	if(StringUtil.containsIgnoreCase(full,"UID=")){
    		int ind1 = full.indexOf("UID=");
    		int ind2 = full.indexOf(">");
    		full = full.substring(ind1+4, ind2);
    		return full;
    	}else {
    		return null;
    	}
    }
    
	
	
}
