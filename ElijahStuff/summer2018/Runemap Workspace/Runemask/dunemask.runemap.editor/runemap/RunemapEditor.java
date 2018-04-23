/**
 * 
 */
package runemap;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JOptionPane;

import dunemask.util.FileUtil;
import dunemask.util.xml.Runemap;

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
		args = new String[1];
		args[0] = "C:\\Users\\dunemask\\Desktop\\alpha.drm";
		
		try {
			File f = new File(args[0].replace("\\", "/"));
			JOptionPane.showMessageDialog(null, "FILE:"+f.getAbsolutePath());
			File out = new File(f.getParentFile().getAbsolutePath()+File.separator+"temp.drm");
			JOptionPane.showMessageDialog(null,out.exists()+"@"+out.getAbsolutePath());
			map = Runemap.parseRunemap(f);
			System.out.println(map.getAllURLS());
			map.writeForcedElement("Test/Elements/Element", "ElementAlpha");
			map.write();
			FileUtil.writeFile(f, out);
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Sorry :(");
		}

		
		
		
		
	}

}
