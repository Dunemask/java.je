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
		var in = new Inode[3];
		for(int i=0;i<in.length;i++) {
			in[i] = new Inode(i,new Random().nextInt(50));
		}
		var nl = new NetLayer(in,3);
		nl.runLayer();
		var con = nl.getConnections();
		for(int i=0;i<con.length;i++) {
			System.out.println(con[i]);
		}
		

	}

	
}
