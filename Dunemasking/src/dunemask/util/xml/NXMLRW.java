/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import dunemask.objects.ArrayListState;
import dunemask.util.FileUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class NXMLRW {
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
    		element = NXMLRW.removeUID(element);
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
     * <p>Gives it a body as well, if no body is needed then use {@link dunemask.util.xml.NXMLRW#NOBODY} For the arg</p>
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
     * @param value Value NXMLRW.container for container
     * 
     * */
    public static void addTopLevelElement(File file,String element,Object value) {
    	String[] lines = RW.readAll(file);
    	int l = lines.length;
    	if(value.equals(NXMLRW.CONTAINER)) {
    		RW.write(file, NXMLRW.element(element), l+1);
    		RW.write(file, NXMLRW.closeElement(element), l+2);
    	}else {
    		RW.write(file, NXMLRW.element(element)+value+NXMLRW.closeElement(element), l+1);
    	}
    	
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
    
    /** Removes a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static void changeElement(File file,String[] parentElementChain,Object value) {
    	if(NXMLRW.itemExists(file, parentElementChain)) {
    		int low=0,high=FileUtil.linesInFile(file);
        	int tabs =0;
        	if(parentElementChain!=null) {
    	    	for(int i=0;i<parentElementChain.length;i++) {
    	    		String element = parentElementChain[i];
    	    		element = NXMLRW.ripElement(element);
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
        	RW.write(file, tab+NXMLRW.element(element)+value+NXMLRW.closeElement(element), high);
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
	    		element = NXMLRW.ripElement(element);
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
    	
    	
    }
    
    public static ArrayList<ArrayList<String>> map(File file,ArrayList<String> pc) {
		ArrayList<ArrayList<String>> flist = new ArrayList<ArrayList<String>>();
    	//System.out.println("Mapping"+pc);
    	HashMap<String, ArrayList<String>> subComp = NXMLRW.getSubElementsAndContainers(file, pc.toArray(new String[pc.size()])).getMap();
    	ArrayList<String> key = new ArrayList<String>(subComp.keySet());
    	for(int i=0;i<key.size();i++){
    		ArrayList<String> s = subComp.get(key.get(i));
    		//if it's a container
    		if(!NXMLRW.isElement(file, s.toArray(new String[s.size()]))) {
    			flist.addAll(map(file,s));
    		}
    		flist.add(s);
    	}
    	return flist;
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
    	ArrayList<ArrayList<String>> map = NXMLRW.map(file, new ArrayList<String>(Arrays.asList(new String[]{parentElementChain[0]})));
    	for(int i=0;i<map.size();i++) {
    		if(map.get(i).size()==parentElementChain.length) {
    			System.out.println(map.get(i));
        		boolean passing = true;
        		boolean run = true;
        		int count = 0;
        		while(run&&passing) {
        			
        		}
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
    
    
    public static String[] getTopLayerComponent(File file,int count) {
    	String ret = "";
    	String[] lines = RW.readAll(file);
    	for(int i=0;i<lines.length;i++) {
    		//If Not Element container
    		if(lines[i].equals(lines[i].replace(StringUtil.tab, ""))) {
	    		if(!NXMLRW.isElement(file, new String[] {lines[i]})) {
	    			ret = lines[i];
	    			ret = NXMLRW.ripElement(ret);
	    			count--;
	    		}
	    		if(count==0) {
	    			i=lines.length;
	    		}
    		}
    		
    		
    	}
    	
    	return new String[] {ret};
    }
    
    public static int[] findHL(File file,String[] text) {
    	int low=0,high=FileUtil.linesInFile(file);
    	int tabs=0;
    	if(text!=null) {
	    	for(int i=0;i<text.length;i++) {
	    		String element = text[i];
	    		element = NXMLRW.ripElement(element);
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
    	
    	return new int[] {low,high};
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
	    		element = NXMLRW.ripElement(element);
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
    		//l = NXMLRW.removeUID(l);
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
    				uid = NXMLRW.ripElement(full);
    			}
    			full = NXMLRW.ripElement(full);
    			if(map.getMap().containsKey(uid)) {
    			int r =	new Random().nextInt(Integer.MAX_VALUE);
    			uid+=String.valueOf(r);
    			}
    			
    	    	full = NXMLRW.ripElement(full);
	    		//System.out.println("Call:"+full);
    			ArrayList<String> tmp = new ArrayList<String>();
    			tmp.addAll(par);
    			tmp.add(full);
    			map.addState(tmp,uid);
    		}
    		
    	}
    	
    	return map;
    }
    /** Get The level directly below
     *  @param file XMLDoc
     *  @param parentElementChain Chain down to desired location
     * @return ArrayListState of all found (HashMap<String,ArrayList<String>>)
     * 
     * 
     * */
    public static ArrayList<ArrayList<String>> getSubElementsAndContainersList(File file,String[] parentElementChain) {
    	ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
    	ArrayList<String> par = new ArrayList<String>(Arrays.asList(parentElementChain));
    	int low=0,high=FileUtil.linesInFile(file);
    	int tabs=0;
    	if(parentElementChain!=null) {
	    	for(int i=0;i<parentElementChain.length;i++) {
	    		String element = parentElementChain[i];
	    		element = NXMLRW.ripElement(element);
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
    		//l = NXMLRW.removeUID(l);
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
    				uid = NXMLRW.ripElement(full);
    			}
    			full = NXMLRW.ripElement(full);
    			if(map.contains(uid)) {
    			int r =	new Random().nextInt(Integer.MAX_VALUE);
    			uid+=String.valueOf(r);
    			}
    			
    	    	full = NXMLRW.ripElement(full);
	    		//System.out.println("Call:"+full);
    			ArrayList<String> tmp = new ArrayList<String>();
    			tmp.addAll(par);
    			tmp.add(full);
    			map.add(tmp);
    		}
    		
    	}
    	
    	return map;
    }
    
    
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static String getElementValue(File file,String[] parentElementChain) {
    	String ob = null;
        
    	return ob;
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
    		element = NXMLRW.ripElement(element);
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
    		element = NXMLRW.ripElement(element);
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
    
   
    
	
	
}
