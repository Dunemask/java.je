package minerender;

import java.io.File;
import dunemask.util.xml.XMLMap;
//File Control, Controls the saving and loading of worlds in the file...
public class FileControl {
	/**Saves the file as an XML
	 * @param vex
	 * @param name
	 */
	public static void SaveFileAsXML(VoxEn vex, String name) {
		String Voxes = FileControl.SaveArray(FileControl.Array3Dto1D(vex.Voxels));
		File f = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/"+name+".xml");
		currentXML = new XMLMap(f,"world");
		currentXML.writeContainer("world/position/");
		currentXML.writeElement("world/position/x", vex.campos.x);
		currentXML.writeElement("world/position/y", vex.campos.y);
		currentXML.writeElement("world/position/z", vex.campos.z);
		currentXML.writeElement("world/dx", vex.Voxels.length);
		currentXML.writeElement("world/dy", vex.Voxels[0].length);
		currentXML.writeElement("world/data", Voxes);
		String se = FileControl.SaveArray(vex.inventory);
		currentXML.writeElement("world/inventory", se);
	}
	public static XMLMap currentXML;
	/** Loads a world from an XML file.
	 * Do not use the .xml in the string.
	 * @param s Save Name
	 * 
	 * */
	public static VoxEn LoadFileXML(String s) {
		File file = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/"+s+".xml");
		currentXML = XMLMap.ParseDXMLMap(file);
		Vector3 v3 = new Vector3(Float.parseFloat(currentXML.pullValue("world/position/x")),Float.parseFloat(currentXML.pullValue("world/position/y")),Float.parseFloat(currentXML.pullValue("world/position/z")));
		String ds = currentXML.pullValue("world/data");
		int sizx = Integer.parseInt(currentXML.pullValue("world/dx"));
		int sizy =Integer.parseInt(currentXML.pullValue("world/dy"));
		byte[][][] c = FileControl.Array1Dto3Db(FileControl.LoadArray(ds),sizx,sizy);
		VoxEn sol = new VoxEn(v3,c,s);
		sol.setinventory(FileControl.LoadArray(currentXML.pullValue("world/inventory")));
		return sol;
		
	}
	/**Compiles an array into a String...
	 * @param Array
	 * @return String
	 */
	static public String SaveArray(int[] array) {
		String s = array.length +";";
		int c=0;
		int tmp = 0;
		int selected=array[0];
		while(c<array.length) {
			c++;
			if(array[c-1]==selected) {
				tmp++;
			}
			else {
				s+=tmp+","+selected+";";
				tmp=1;
				selected=array[c-1];
			}
		}
		s+=tmp+","+selected+";";
		return s;
	}
	/** Loads the array from the String...
	 * @param String
	 * @return array
	 */
	static public int[] LoadArray(String s) {
		String[] ltmp = s.split(";");
		int[] i = new int[Integer.parseInt(ltmp[0])];
		int c = 0;	
		for(int l = 1; l<ltmp.length;l++) {
			int selected=Integer.parseInt(ltmp[l].split(",")[1]);
		for(int k = 0; k<Integer.parseInt(ltmp[l].split(",")[0]);k++) {
				i[c]=selected;
				c++;
				}
		}
		return i;
	}
	/**Converts a 3D Array to a 1D Array
	 * @param Array
	 * @return Array
	 */
	static public int[] Array3Dto1D(byte[][][] dra) {
		int[] i = new int[dra.length*dra[0].length*dra[0][0].length];
		for(int j = 0; j<i.length;j++) {
			i[j]=dra[j%dra.length][(j/dra.length)%dra[0].length][j/(dra.length*dra[0].length)];
		}
		return i ;
	}
	/**Converts a 1D Array to a 3D Array...
	 * @param 1D Array
	 * @param Size in X
	 * @param Size in Y
	 * @return
	 */
	static public byte[][][] Array1Dto3Db(int[] dra, int sx, int sy){
		byte[][][] i = new byte[sx][sy][dra.length/(sx*sy)];
		for(int j = 0; j< dra.length; j++) {
			i[j%sx][(j/sx)%sy][j/(sx*sy)] = (byte)dra[j];
		}
		return i;
	}
}
