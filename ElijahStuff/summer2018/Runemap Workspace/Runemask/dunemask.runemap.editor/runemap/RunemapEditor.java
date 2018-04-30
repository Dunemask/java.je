/**
 * 
 */
package runemap;

import java.io.File;
import dunemask.util.xml.Runemap;
import utils.JConsole;
/**
 * @author dunemask
 *
 */
public class RunemapEditor {
	public static Runemap map;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			JConsole.startConsole();	
		try {
			File f = new File(args[0].replace("\\", "/"));
			System.out.println("FILE:"+f.getAbsolutePath());
			File out = new File(f.getParentFile().getAbsolutePath()+File.separator+"temp.drm");
			System.out.println(out.exists()+"@"+out.getAbsolutePath());
			map = Runemap.parseRunemap(f);
			System.out.println(map.getAllURLS());
			map.writeForcedElement("Test/Elements/Element", "ElementAlpha");
			map.write();
			//FileUtil.writeFile(f, out);
			map.writeOut(out,8192);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Would open up default editor now");
		}

		
		
		
		
	}

}
