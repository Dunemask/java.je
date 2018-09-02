/**
 * 
 */
package mc;

import java.io.File;

import dunemask.util.FileUtil;
import dunemask.util.xml.RuneMap;

/**
 * @author dunemask
 *
 */
public class ResourceHandler {
	public static final String vpath = System.getProperty("user.home")+"/Documents/VoxelCraft/";
	public static File handler;
	public static File blox;
	public static File sounds;
	public static RuneMap handlemap;
	public static RuneMap blockmap;
	public static RuneMap soundmap;
	
	/**
	 * 
	 */
	public static void init() {
		new File(vpath).mkdirs();
		new File(vpath+"resources/").mkdirs();
		File handler = new File(vpath+"resources/handler.xml");
		if(handler.exists()) {
			handlemap = RuneMap.ParseDXMLMap(handler);
			//System.out.println(handlemap.pullValue("Handler/blox"));
			try {
			blox = FileUtil.getResource(handlemap.pullValue("Handler/blox"));
			sounds = FileUtil.getResource(handlemap.pullValue("Handler/sounds"));
			}catch(RuntimeException e){
				handler.delete();
				init();
			}
		}else {
			File orig = FileUtil.getResource("resources/handler.xml");
			FileUtil.writeFile(orig, handler);
			blox = new File(vpath+"resources/blocks.xml");
			sounds = new File(vpath+"resources/sounds.xml");
			FileUtil.writeFile(FileUtil.getResource("resources/textures/blocks/block.xml"), blox);
			FileUtil.writeFile(FileUtil.getResource("resources/sounds/Index.xml"), sounds);
			handlemap = RuneMap.ParseDXMLMap(handler);
		}
		soundmap = RuneMap.ParseDXMLMap(sounds);
		blockmap = RuneMap.ParseDXMLMap(blox);
		
	}

	
}
