package minerender;

import java.io.File;
import dunemask.util.xml.XMLMap;
public class FileControl {
	public static void SaveFileAsXML(VoxEn vex, String name) {
		String Voxes = FileControl.SaveArray(FileControl.Array3Dto1D(vex.Voxels));
		File f = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/"+name+".xml");
		XMLMap xml = new XMLMap(f,"world");
		xml.writeContainer("world/position/");
		xml.writeElement("world/position/x", vex.campos.x);
		xml.writeElement("world/position/y", vex.campos.y);
		xml.writeElement("world/position/z", vex.campos.z);
		xml.writeElement("world/dx", vex.Voxels[0].length);
		xml.writeElement("world/dy", vex.Voxels[0][0].length);
		xml.writeElement("world/data", Voxes);
	}
	public static VoxEn LoadFileXML(String s) {
		File file = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/"+s+".xml");
		XMLMap xml = XMLMap.ParseDXMLMap(file);
		Vector3 v3 = new Vector3(Float.parseFloat(xml.pullValue("world/position/x")),Float.parseFloat(xml.pullValue("world/position/y")),Float.parseFloat(xml.pullValue("world/position/z")));
		String ds = xml.pullValue("world/data");
		int sizx = Integer.parseInt(xml.pullValue("world/dx"));
		int sizy =Integer.parseInt(xml.pullValue("world/dy"));
		byte[][][] c = FileControl.Array1Dto3Db(FileControl.LoadArray(ds),sizx,sizy);
		return new VoxEn(v3,c);
		
	}
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
	static public int[] Array3Dto1D(byte[][][] dra) {
		int[] i = new int[dra.length*dra[0].length*dra[0][0].length];
		for(int j = 0; j<i.length;j++) {
			i[j]=dra[j%dra.length][(j/dra.length)%dra[0].length][j/(dra.length*dra[0].length)];
		}
		return i ;
	}
	static public byte[][][] Array1Dto3Db(int[] dra, int sx, int sy){
		byte[][][] i = new byte[sx][sy][dra.length/(sx*sy)];
		for(int j = 0; j< dra.length; j++) {
			i[j%sx][(j/sx)%sy][j/(sx*sy)] = (byte)dra[j];
		}
		return i;
	}
}
