/**
 * 
 */
package xmltest;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.xml.XMLRW;

/**
 * @author dunemask
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File(System.getProperty("user.home")+"/Desktop/tmp.xml");
		DXMLMap mp = DXMLMap.ParseDXMLMap(file);
		//mp.tmp();
		//mp.writeForcedElement("File/Asdf/Cookie", "Potato");
		//mp.writeElement("File/Asdf/potato", "asdf");
		//System.out.println(XMLRW.getElementValue(file, new String[] {"Cookie"}));
		//mp.writeForcedContainer("Amazing/Purple/Green/Orange");
		//mp.writeContainer("Amazing/Purple/Green/Orange");
		//mp.writeForcedElement("Cookie", "Just The best");
		mp.writeElement("Amazing/Purple/Green/Orange/Cookie", "Pink");
		//mp.changeElement("Cookie","Greatest");
		
		ArrayList<String> url = mp.getAllURLS();
		for(int i=0;i<url.size();i++) {
			if(!mp.isCont(url.get(i))) {
				
			}
		}
	}

}
