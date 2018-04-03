/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import dunemask.util.FileUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author dunemask
 *
 */
public class XMLRW {
	/***Version*/
    final static double version = 5.8;
    /** No Body for newXMLFile
     **/
    public static final String NOBODY = "$none$";
    
    /** Container Identifier
     * */
    static final String CONTAINER = "$container$";
    
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
    
    /** Creates a File in the specified location, (handles parent folders)
     * <p>Gives it a body as well, if no body is needed then use {@link dunemask.util.xml.XMLRW#NOBODY} For the arg</p>
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
    
    /** Adds a containers in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the container to be added (All Under Same parent)
     * @param container Name of Container
     * 
     * */
    public static void addElementContainers(File file,String[] parentElementChain,String container[]) {
    		for(int i=0;i<container.length;i++) {
    			addElement(file,parentElementChain,container[i],CONTAINER);
    		}
    	
    }
    
    
    /** Adds a containers in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the container to be added
     * @param container Name of Container
     * 
     * */
    public static void addElementContainers(File file,String[][] parentElementChain,String container[]) {
    		for(int i=0;i<container.length;i++) {
    			addElement(file,parentElementChain[i],container[i],CONTAINER);
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
	    		int tlow = FileUtil.findInDocumentBounds(file,element(element), low, high);
	    		int thigh = FileUtil.findInDocumentBounds(file,closeElement(element), low, high);
	    		low=tlow;
	    		high=thigh;
	    		tabs++;
	    	}
    	}
    	String tab = "";
    	for(int i=0;i<tabs;i++) {
    		tab+=StringUtil.tab;
    	}
    	//System.out.println("H:"+high+",L:"+low);
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
     */
    public static ArrayList<String> getElementsValues(File file,String[] parentElementChain){
    	ArrayList<String> containers = XMLRW.getElements(file, parentElementChain);
		return XMLRW.getElementsValues(file, parentElementChain, containers);
    }
    
    
    
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
    		vals.add(XMLRW.getElementValue(file, tmp.toArray(new String[tmp.size()])));
    	}
		return vals;
    }
    
    
    /** Get All Next Level Elements
     * @param file XML Doc
     * @param parentElementChain Highearchy down to top element who's sub-elements will be "Searched"
     * @return List of the Sub Elements (not values) To obtain the values for
     * 
     * */
    public static ArrayList<String> getElements(File file,String[] parentElementChain) {
    	ArrayList<String> elms = new ArrayList<String>();
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		int tlow = FileUtil.findInDocumentBounds(file,element(element), low, high);
    		int thigh = FileUtil.findInDocumentBounds(file,closeElement(element), low, high);
    		low=tlow;
    		high=thigh;
    	}
    	//System.out.println(RW.read(file, low)+","+RW.read(file, high));
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
    }
    
    
    /** Adds a element in the XML Doc
     * @param file XML File
     * @param parentElementChain Hiarchy down to the element to be added
     * @param newElement Name of new Element
     * @param value Value for new Element
     * 
     * */
    public static String getElementValue(File file,String[] parentElementChain) {
    	int low=0,high=FileUtil.linesInFile(file);
    	for(int i=0;i<parentElementChain.length;i++) {
    		String element = parentElementChain[i];
    		int tlow = FileUtil.findInDocumentBounds(file,element(element), low, high);
    		int thigh = FileUtil.findInDocumentBounds(file,closeElement(element), low, high);
    		low=tlow;
    		high=thigh;
    	}
    	String ob = null;
        String lastElement = parentElementChain[parentElementChain.length-1];
        //Low Should Equal HIgh
        if(high!=low) {
        	throw new RuntimeException("Invalid Dunemask XML Doc, Container "+ parentElementChain[parentElementChain.length-1]+" Found; not Element");
        }else {
        	String full =  RW.read(file, low);
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
        	ob = full;
        }
        
    	return ob;
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
    		int tlow = FileUtil.findInDocumentBounds(file,element(element), low, high);
    		int thigh = FileUtil.findInDocumentBounds(file,closeElement(element), low, high);
    		low=tlow;
    		high=thigh;
    	}
        if(high!=low) {
        	return false;
        }else {
        	return true;
        }
    }
    
	
	
}
