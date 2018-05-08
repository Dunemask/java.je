package rwadvance;

import java.io.File;

import dunemask.util.xml.LiveRunemap;
import dunemask.util.xml.Runemap;
public class RWAdvanceTest1 {
	static String algorithm = "DESede";

	public static void main(String[] args) throws Exception {
		String dtop = System.getProperty("user.home")+"/Desktop/";
		String fpath = dtop+"Break.jar";
		File f = new File(fpath);
		/*File n = new File(dtop+"Other.drm");
		Runemap r = new LiveRunemap(n);
		r.writeForcedElement("one/two/three/other", "HAHA!");*/
		File alt = dunemask.util.ArchiveUtil.getFileFromJar(f, "one/Other.drm",new File(dtop+"rip.txt"));
		Runemap map = Runemap.parseRunemap(alt);
		String val = map.getvalue("one/two/three/other");
		System.out.println(val);
		
	}
}
