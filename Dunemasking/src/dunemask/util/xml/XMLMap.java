/**
 * 
 */
package dunemask.util.xml;

import java.io.File;
import java.util.ArrayList;

import dunemask.objects.ArrayListState;

/**
 * @author dunemask
 *
 */
public class XMLMap {
	private ArrayList<String> holder;
	private ArrayListState ha;
	private String lastState;
	private File xml;
	
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
	/** Adds Container to xml doc
	 * @param name List of container names
	 * @param parent Parent Path
	 * @param state List of states that can be called later
	 * 
	 * 
	 * */
	//public void addElement(String name,ArrayList<String> parent,String state,Object value) {
	public void addElements(ArrayList<String> name,ArrayList<String> parent,ArrayList<String> state,ArrayList<String> value) {
		for(int i=0;i<name.size();i++) {
			addElement(name.get(i),parent,state.get(i),value.get(i));
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
	
}
