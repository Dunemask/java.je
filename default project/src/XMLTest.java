import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.XMLRW;

/**
 * 
 */

/**
 * @author dunemask
 *
 */
public class XMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//test();
		//character();
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("1");
		HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		map.put("ORIGINAL", new ArrayList<String>().addAll(tmp));
		tmp.add("2");
		map.put("NEXT", new ArrayList<String>().addAll(tmp));
		for(String s:map.get("ORIGINAL")) {
			System.out.println(s);
		}
		for(String s:map.get("NEXT")) {
			System.out.println(s);
		}
	}

	/**
	 * 
	 */
	private static void character() {
		File file = new File(System.getProperty("user.home")+"/Desktop/Dunemask_Character.xml");
		XMLRW.newXMLFile(file, "Character");
		XMLRW.addElement(file, new String[] {"Character"},"Name", "Dunemask");
		XMLRW.addElementContainer(file, new String[] {"Character"},"Attributes");
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"HP",99);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"ATK",12);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"DEF",4);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"CRIT",100);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"BLOCK",87);
		XMLRW.addElement(file, new String[] {"Character","Attributes"},"GIF","C:/Ye");
		/*for(int i=0;i<archs.size();i++) {
			stats.add(XMLRW.getElementValue(file, new String[] {"Character","Attributes",archs.get(i)}));
		}
		for(String x:stats) {
			System.out.println(x);
		}*/
		/*ArrayList<String> vals = XMLRW.getElementsValues(file, new String[] {"Character","Attributes"}, archs);
		*/
		ArrayList<String> vals = XMLRW.getElementsValues(file, new String[] {"Character","Attributes"});
		vals.add(0,XMLRW.getElementValue(file, new String[] {"Character","Name"}));
		for(String x:vals) {
			System.out.println(x);
		}
		
		
		
	}

	/**
	 * 
	 */
	private static void test() {
		File xmlDoc = new File(System.getProperty("user.home")+"/Desktop/Test.xml");
		XMLRW.newXMLFile(xmlDoc, "Cookie");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie"}, "Type");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie","Type"}, "Potato");
		XMLRW.addElementContainer(xmlDoc, new String[] {"Cookie","Type"},"Ninja");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Potato"}, "Orange", "Pink");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Potato"}, "Vanilla","Flavorful");
		XMLRW.addElement(xmlDoc, new String[] {"Cookie","Type","Ninja"}, "Skilled","Null");
		
	}

}
