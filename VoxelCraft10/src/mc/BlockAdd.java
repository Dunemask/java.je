/**
 * 
 */
package mc;

import java.io.File;

import dunemask.util.xml.RuneMap;

/**
 * @author Dunemask
 *
 */
public class BlockAdd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File block = new File(System.getProperty("user.home")+"/Desktop/block.xml");
		RuneMap map = new RuneMap(block,"blocks");
		map.writeElement("blocks/0", "dirt");
		map.writeElement("blocks/1", "stone");
		map.writeElement("blocks/2", "grass");
		map.writeElement("blocks/3", "grass_side");
		map.writeElement("blocks/4", "log_top");
		map.writeElement("blocks/5", "log_side");
		map.writeElement("blocks/6", "brix");
		map.writeElement("blocks/7", "leaves");
		map.writeElement("blocks/8", "leaves_2");
		map.writeElement("blocks/9", "stone_brick");
		map.writeElement("blocks/10", "select");
		map.writeElement("blocks/11", "red");
		map.writeElement("blocks/12", "orange");
		map.writeElement("blocks/13", "yellow");
		map.writeElement("blocks/14", "green");
		map.writeElement("blocks/15", "sky_blue");
		map.writeElement("blocks/16", "dark_blue");
		map.writeElement("blocks/17", "magenta");
		map.writeElement("blocks/18", "cobblestone");
		map.writeElement("blocks/19", "bedrock");
		map.writeElement("blocks/20", "glass");
		map.writeElement("blocks/21", "light_gray_stained_glass");
		
		

	}

}
