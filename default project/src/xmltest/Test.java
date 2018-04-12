/**
 * 
 */
package xmltest;

import java.io.File;
import java.util.ArrayList;

import dunemask.util.xml.DXMLMap;
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
		//DXMLMap mp = DXMLMap.ParseDXMLMap(file);
		DXMLMap mp = new DXMLMap(new File(System.getProperty("user.home")+"/Desktop/otmp.xml"),"Master");
		//mp.tmp();
		//mp.writeForcedElement("File/Asdf/Cookie", "Potato");
		//mp.writeElement("File/Asdf/potato", "asdf");
		//System.out.println(XMLRW.getElementValue(file, new String[] {"Cookie"}));
		//mp.writeForcedContainer("Amazing/Purple/Green/Orange");
		//mp.writeContainer("Amazing/Purple/Green/Orange");
		//mp.writeForcedElement("Cookie", "Just The best");
		mp.writeElement("Cookie", "Just yes");
		mp.changeElement("Cookie", "No");
		mp.writeForcedElement("Cover/Sheets/Purple", "Flanel");
		mp.changeElement("Cover/Sheets/Purple", "Egyptian Cotten");
		mp.changeElement("Cover/Sheets/Purple", "Purple");
		System.out.println(mp.getvalue("Cookie"));
		ArrayList<String> urls = mp.getAllURLS();
		for(int i=0;i<urls.size();i++) {
			System.out.println(urls.get(i));
		}
		//mp.writeElement("Amazing/Purple/Green/Orange/Cookie", "Pink");
		//mp.changeElement("Cookie","Greatest");
		
		ArrayList<String> url = mp.getAllURLS();
		for(int i=0;i<url.size();i++) {
			if(!mp.isCont(url.get(i))) {
				
			}
		}
	}

}
