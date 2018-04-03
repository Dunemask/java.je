/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.objects.ArrayListState;

/**
 * @author dunemask
 *
 */
public class XMLMap {
	/***Version*/
    final static double version = 5.8;
	private ArrayList<String> holder;
	private ArrayListState ha;
	private String lastState;
	private File xml;
	/** Create new XML file 
	 * @param xml File
	 * @param body Body Container
	 * @param state State for Body Element
	 * */
	public XMLMap(File xml,String body,String state) {
		this.setXml(xml);
		ha =  new ArrayListState();
		holder = new ArrayList<String>();
		lastState = body;
		holder.add(body);
		ha.addState(holder, state);
		holder.removeAll(holder);
		XMLRW.newXMLFile(xml, body);
	}
	
	/** Create new XML file 
	 * @param xml File
	 * @param body Body Container
	 * 
	 * */
	public XMLMap(File xml,String body) {
		this.setXml(xml);
		ha =  new ArrayListState();
		holder = new ArrayList<String>();
		lastState = body;
		holder.add(body);
		ha.addState(holder, body);
		holder.removeAll(holder);
		XMLRW.newXMLFile(xml, body);
	}
	/** Get Components of the XML Map under the parent
	 * @param parent Parent Path
	 * @return list of Sub Components
	 * 
	 * */
	public ArrayList<String> getSubComponents(ArrayList<String> parent){
		return XMLRW.getElements(getXml(), parent.toArray(new String[parent.size()]));
	}
	
	
	/** Adds Container to xml doc
	 * @param name List of container names
	 * @param parent Parent Path
	 * 
	 * 
	 * */
	public void addContainers(ArrayList<String> name,ArrayList<String> parent) {
		for(int i=0;i<name.size();i++) {
			addContainer(name.get(i),parent);
		}
		
	}
	/** Adds Container to xml doc
	 * @param name List of container names
	 * @param parent Parent Path
	 * @param state List of states that can be called later
	 * 
	 * 
	 * */
	public void addContainers(ArrayList<String> name,ArrayList<String> parent,ArrayList<String> state) {
		for(int i=0;i<name.size();i++) {
			addContainer(name.get(i),parent,state.get(i));
		}

		
	}
	/** Adds Container to xml doc
	 * @param name List of container names
	 * @param parent Parent Path
	 * 
	 * 
	 * */
	//public void addElement(String name,ArrayList<String> parent,Object value) {
	public void addElements(ArrayList<String> name,ArrayList<String> parent,ArrayList<String> value) {
		for(int i=0;i<name.size();i++) {
			addElement(name.get(i),parent,value.get(i));
		}
		
	}
	/** Adds Elements to xml doc
	 * @param name List of container names
	 * @param parent Parent Path
	 * @param state List of states that can be called later
	 * @param value Value list
	 * 
	 * */
	//public void addElement(String name,ArrayList<String> parent,String state,Object value) {
	public void addElements(ArrayList<String> name,ArrayList<String> parent,ArrayList<String> state,ArrayList<String> value) {
		for(int i=0;i<name.size();i++) {
			addElement(name.get(i),parent,state.get(i),value.get(i));
		}

		
	}
	/** @param element Hashmap of elements
	 * @param parent Parent Path
	 * @param state List of state names
	 * 
	 * */
	public void addElements(HashMap<String,Object> element,ArrayList<String> parent) {
		String[] keys = element.keySet().toArray(new String[element.keySet().size()]);
		for(int i=0;i<element.keySet().size();i++) {
			addElement(keys[i],parent,keys[i],element.get(keys[i]));
		}
	}
	/** Add Eleemnts to xml doc 
	 * @param element Hashmap of elements
	 * @param parent Parent Path
	 * @param state List of state names
	 * 
	 * */
	public void addElements(HashMap<String,Object> element,ArrayList<String> parent,ArrayList<String> state) {
		String[] keys = element.keySet().toArray(new String[element.keySet().size()]);
		for(int i=0;i<element.keySet().size();i++) {
			addElement(keys[i],parent,state.get(i),element.get(keys[i]));
		}

		
	}
	
	
	
	/** Adds Container to xml doc
	 * @param name Name for Container
	 * @param parent Parent Path
	 * 
	 * 
	 * */
	public void addContainer(String name,ArrayList<String> parent) {
		holder.removeAll(holder);
		XMLRW.addElementContainer(getXml(), parent.toArray(new String[parent.size()]), name);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, name);
		lastState=name;
		holder.removeAll(holder);
		
	}
	
	/** Get Value from Element
	 * 
	 * */
	public String getElementFromDoc(ArrayList<String> path) {
		return XMLRW.getElementValue(this.getXml(), path.toArray(new String[path.size()]));
	}
	
	/** Get Values from all sub elements in a container
	 * 
	 * */
	public ArrayList<String> getElementsFromDoc(ArrayList<String> path) {
		
		return XMLRW.getElementsValues(this.getXml(), path.toArray(new String[path.size()]));
	}
	
	/** Get Values from all sub elements in a container
	 * 
	 * */
	public HashMap<String, String> getElementsAndKeys(ArrayList<String> path) {
		HashMap<String,String> map = new HashMap<String,String>();
		ArrayList<String> sub = XMLRW.getElements(getXml(), path.toArray(new String[path.size()]));
		ArrayList<String> val = new ArrayList<String>(sub.size());
		XMLMap.ParentBuilder.init(getXml(),path);
		for( int i=0;i<sub.size();i++) {
			ParentBuilder.addPiece(sub.get(i));
			if(XMLRW.isElement(getXml(), ParentBuilder.getParent().toArray(new String[ParentBuilder.getParent().size()]))) {
				val.add(XMLRW.getElementValue(getXml(),ParentBuilder.p.toArray(new String[ParentBuilder.p.size()])));
			}else {
				val.add("");
			}
			//System.out.print(sub.get(i)+",");
			//System.out.println(val.get(i));
			XMLMap.ParentBuilder.init(getXml(),path);
			map.put(sub.get(i), val.get(i));
		}
		
		return map;
	}
	/**
	 **/
	public ArrayList<String> getParent(ArrayList<String> child){
		child.remove(child.size()-1);
		return child;
		
	}
	
	
	
	/** Adds Container to xml doc
	 * @param name Name for Container
	 * @param parent Parent Path
	 * @param state State that can be called later
	 * 
	 * 
	 * */
	public void addContainer(String name,ArrayList<String> parent,String state) {
		holder.removeAll(holder);
		XMLRW.addElementContainer(getXml(), parent.toArray(new String[parent.size()]), name);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, state);
		lastState=state;
		holder.removeAll(holder);
		
	}
	
	/** Adds Element to xml doc
	 * @param name Name for Container
	 * @param parent Parent Path
	 * @param value Object to be stored in xml
	 * 
	 * 
	 * */
	public void addElement(String name,ArrayList<String> parent,Object value) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, name);
		holder.removeAll(holder);
		XMLRW.addElement(getXml(), parent.toArray(new String[parent.size()]), name,value);
		
		
	}
	/** Returns the arraylist by the last state that was indexed
	 * */
	public ArrayList<String> lastParent(){
		return this.getParentByState(this.getLastState());
		
	}
	
	/** Adds Element to xml doc
	 * @param name Name for Container
	 * @param parent Parent Path
	 * @param state State that can be called later
	 * @param value Object to be stored in xml
	 * 
	 * 
	 * */
	public void addElement(String name,ArrayList<String> parent,String state,Object value) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, state);
		holder.removeAll(holder);
		XMLRW.addElement(getXml(), parent.toArray(new String[parent.size()]), name,value);
	}
	
	
	/** Get an attribute from xml map based on the state, (not from doc but map) 
	 * @param state State called
	 * 
	 * */
	public ArrayList<String> getParentByState(String state){
			if(ha.getState(state)==null) {
			throw new RuntimeException("Invalid State");
			}
		return ha.getState(state);
	}
	
	
	
	/**
	 * @return the ha
	 */
	public ArrayListState getHa() {
		return ha;
	}

	/**
	 * @param ha the ha to set
	 */
	public void setHa(ArrayListState ha) {
		this.ha = ha;
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
	 * @return the lastState
	 */
	public String getLastState() {
		return lastState;
	}

	/**
	 * @param lastState the lastState to set
	 */
	public void setLastState(String lastState) {
		this.lastState = lastState;
	}
	public static class ParentBuilder{
		public static ArrayList<String> p;
		public static File xml;
		
		public static void addPiece(String level) {
			p.add(level);
		}
		public static void setParent(ArrayList<String> parent) {
			p = new ArrayList<String>();
			reset();
			p.addAll(parent);
		}
		public static ArrayList<String> getParent() {
			return p;
		}
		
		public static void init(File xml,ArrayList<String> parent) {
			ParentBuilder.xml = xml;
			p = new ArrayList<String>();
			reset();
			p.addAll(parent);
		}
		public static void init(File xml) {
			ParentBuilder.xml = xml;
			p = new ArrayList<String>();
			reset();
		}
		public static void reset() {
			p.removeAll(p);
		}
		
		
		
		
	}
}
