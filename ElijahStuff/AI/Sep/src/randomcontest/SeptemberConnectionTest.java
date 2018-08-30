/**
 * 
 */
package randomcontest;

import java.util.Random;

/**
 * @author Dunemask
 *
 */
public class SeptemberConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		var in = new Inode[5];
		for(int i=0;i<in.length;i++) {
			in[i] = new Inode(i,/*new Random().nextInt(0)*/1);
		}
		var nl = new NetLayer(in,2);
		nl.runLayer();
		var con = nl.getConnections();
		/*for(int i=0;i<con.length;i++) {
			System.out.println(con[i]);
		}*/
		var onodes = nl.getOnodes();
		for(int i=0;i<onodes.length;i++) {
			System.out.println(onodes[i]);
		}
		System.out.println("__________________");
		System.out.println("NODE:"+getBest(onodes));
		
	}
	
	public static int getBest(Onode[] nodes) {
		var index = 0;
		
		
		
		for(int i=0;i<nodes.length;i++) {
			
			
		}
		
		
		
		
		
		return index;
		
		
	}
	

	
}
