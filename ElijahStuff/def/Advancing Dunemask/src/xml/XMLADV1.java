package xml;

import java.io.File;

import dunemask.util.xml.Runemap;

public class XMLADV1 {

	public static void main(String[] args) {
		Runemap map = Runemap.parseRunemap(new File(System.getProperty("user.home")+"/Desktop/Alpha.drm"));
		map.writeElement("Main/tmp", 20);
		map.write();

	}

}
