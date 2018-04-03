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
	private ArrayList<String> lastParent;
	private File xml;
	
	public XMLMap(File xml,String body) {
		this.setXml(xml);
		ha =  new ArrayListState();
		holder = new ArrayList<String>();
		lastParent = new ArrayList<String>();
		holder.add(body);
		ha.addState(holder, body);
		lastParent.removeAll(lastParent);
		lastParent.addAll(ha.getState(body));
		holder.removeAll(holder);
		XMLRW.newXMLFile(xml, body);
	}
	
	
	
	public void addContainer(String name,ArrayList<String> parent) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, name);
		holder.removeAll(holder);
		
	}
	
	public void addContainer(String name,ArrayList<String> parent,String state) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, state);
		
		holder.removeAll(holder);
		
	}
	
	public void addElement(String name,ArrayList<String> parent,Object value) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, name);
		holder.removeAll(holder);
		
		
	}
	
	public void addElement(String name,ArrayList<String> parent,String state,Object value) {
		holder.removeAll(holder);
		if(parent!=null){holder.addAll(parent);}
		holder.add(name);
		ha.addState(holder, state);
		holder.removeAll(holder);
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
	 * @return the lastParent
	 */
	public ArrayList<String> getLastParent() {
		return lastParent;
	}

	/**
	 * @param lastParent the lastParent to set
	 */
	public void setLastParent(ArrayList<String> lastParent) {
		this.lastParent = lastParent;
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
	
}
