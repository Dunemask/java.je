package xml;
import java.io.File;
import java.util.Scanner;

import dunemask.util.xml.LiveRunemap;
import dunemask.util.xml.Runemap;
public class ReadXML {
	 public static void main(String[] args)  {
		 String filepath = System.getProperty("user.home")+"/Desktop/file.xml";
		 File doc = new File(filepath);
		 Runemap map = new LiveRunemap(doc);
		 map.writeElement("So ye ^^", "Value1");
		 map.writeContainer("Staff id=1");
		 map.writeContainer("Staff id=2");
		 map.writeElement("Staff id=1/Name", "Elijah Parker");
		 map.writeElement(map.getParentUrl(map.getLastAttributeAccessed())+"Salary", "$500,000");
		//map.writeElement("<break>", "trial");
		// map.printAttr();
		map.write();
		new Scanner(System.in).nextLine();
		map = Runemap.parseRunemap(doc);
		 map.printAttr();
		 map.write();

	}


}
