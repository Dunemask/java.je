import java.io.File;

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
		character();
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
