/**
 * 
 */
package randomcontest;

import java.util.Random;

/**
 * @author Dunemask
 *
 */
public class NetLayer {

	private Inode[] inodes;
	private Onode[] onodes;
	private Connection[] connections;


	/**
	 * 
	 */
	public NetLayer(Inode[] inodes, int onodeSize) {
		this.setInodes(inodes);
		this.onodes = new Onode[onodeSize];
		for(int i=0;i<onodes.length;i++) {
			onodes[i] = new Onode(i,0);
			
		}
		int max = inodes.length*onodes.length;
		int conCount = new Random().nextInt(max-1)+1;
		this.setConnections(randomCon(inodes, onodes,conCount));
		/*for(int i=0;i<connections.length;i++) {
			System.out.println(connections[i]);
		}*/
	}

	public void runLayer() {
		for(int i=0;i<connections.length;i++) {
			float inVal = connections[i].getInode().getValue();
			float con = connections[i].getVal();
			connections[i].getOnode().addTo(inVal*con);
		}
	}
	
	
	private static Connection[] randomCon(Inode[] inodes,Onode[] onodes,int connectionCount){
		var maxcon = inodes.length * onodes.length;
		if(connectionCount>maxcon) {
			throw new RuntimeException("To many connections for the network");
		}
	
		var out = new Connection[connectionCount];
		
		for(int i=0;i<connectionCount;i++) {
			//System.out.println(i+",OUTOF:"+connectionCount);
			out[i] = getBlankCon(inodes, onodes, out);
		}
		return out;

		
	}
	
	private static boolean isDuplicate(Connection[] cons,Connection c) {
		boolean isDup = false;
		int ic = c.getInode().getIc();
		int oc = c.getOnode().getIc();
		for(int i=0;i<cons.length;i++) {
			if(cons[i] !=null){
				int tic = cons[i].getInode().getIc();
				int toc = cons[i].getOnode().getIc();
				if(ic==tic&&oc==toc) {
					isDup=true; i = cons.length; //Skip To The end :D
				}
				
			}
			
		}
		return isDup;
	}

	
	private static Connection getBlankCon(Inode[] inodes,Onode[] onodes,Connection[] cons ) {
		Random rand = new Random();
		Connection c = null;
		do {
			c = new Connection(inodes[rand.nextInt(inodes.length)],onodes[rand.nextInt(onodes.length)],rand.nextFloat());
		}while(isDuplicate(cons,c));
		
		return c;
		
	}


	/**
	 * @return the connections
	 */
	public Connection[] getConnections() {
		return connections;
	}


	/**
	 * @param connections the connections to set
	 */
	public void setConnections(Connection[] connections) {
		this.connections = connections;
	}


	/**
	 * @return the inodes
	 */
	public Inode[] getInodes() {
		return inodes;
	}


	/**
	 * @param inodes the inodes to set
	 */
	public void setInodes(Inode[] inodes) {
		this.inodes = inodes;
	}
	
}
