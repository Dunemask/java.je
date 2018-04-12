/**
 * 
 */
package xmltest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dunemask.util.xml.XMLMap;
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
		File ot = new File(System.getProperty("user.home")+"/Desktop/otmp.xml");
		//DXMLMap mp = DXMLMap.ParseDXMLMap(file);
		//DXMLMap mp =  new DXMLMap(ot,"None");
		XMLMap mp =  new XMLMap(file,"Body");
		mp.writeContainer("Top");
		mp.writeElement("Top/Master", "Branch");
		HashMap<String, String> map = mp.getAllValues();
		ArrayList<String> keys = new ArrayList<String>(map.keySet());
		//System.out.println(mp.getAllURLS().contains(keys.get(0)));
		//System.out.println(keys);
		//System.out.println(mp.getAllURLS());
		mp.removeElement("Top/Master");
		mp.writeForcedContainer("Top/Master/Cookie/");
		mp.removeContainer("Top");
		mp = XMLMap.ParseDXMLMap(file);
		mp.writeForcedContainer("ChickenScratch/Amazing/Ninja");
		mp.removeContainer("ChickenScratch/Amazing/");
		mp.writeForcedElement("Other/Amazing", "Just Yes");
		mp.writeForcedElement("People/Are/Freaking/Amazing/So/Don't/Eny/It", "(Cause you really Can't)");
		ArrayList<String> u = mp.getSubURLS("People/Are/Freaking/Amazing/So/Don't/Eny/");
		for(String s:u) {
			System.out.println(s);
		}
		
		//mp.tmp();
		//mp.writeForcedElement("File/Asdf/Cookie", "Potato");
		//mp.writeElement("File/Asdf/potato", "asdf");
		//System.out.println(XMLRW.getElementValue(file, new String[] {"Cookie"}));
		//mp.writeForcedContainer("Amazing/Purple/Green/Orange");
		//mp.writeContainer("Amazing/Purple/Green/Orange");
		//mp.writeForcedElement("Cookie", "Just The best");
		/*mp.writeElement("Cookie", "Just yes");
		mp.changeElement("Cookie", "No");
		mp.writeForcedElement("Cover/Sheets/Purple", "Flanel");
		mp.changeElement("Cover/Sheets/Purple", "Egyptian Cotten");
		mp.changeElement("Cover/Sheets/Purple", "Purple");
		mp.writeForcedElement("Other/Things/Only/For/The/Coolest/People", "Potat");
		mp.removeElement("Cookie");
		System.out.println("What The");
		/*HashMap<String, String> full = mp.getAllValues();
		ArrayList<String> key = new ArrayList<String>(full.keySet());
		for(int i=0;i<key.size();i++) {
			System.out.println(full.get(key.get(i)));
			
		}*/
		//System.out.println(mp.getvalue("Cookie"));
		/*ArrayList<String> urls = mp.getAllURLS();
		for(int i=0;i<urls.size();i++) {
			System.out.println(urls.get(i));
		}*/
		//mp.writeElement("Amazing/Purple/Green/Orange/Cookie", "Pink");
		//mp.changeElement("Cookie","Greatest");
		
		ArrayList<String> url = mp.getAllURLS();
		for(int i=0;i<url.size();i++) {
			if(!mp.isCont(url.get(i))) {
				
			}
		}
	}

}
