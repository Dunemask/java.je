package aug;

import java.util.Random;

public class FoodBot {

	public int health;
	public int pos;
	public float[] inodes;
	public float[] onodes;

	public FoodBot(int inputSize) {
		Random r = new Random();
		this.setHealth(10);
		int max = 20;
		int min = 0;
		this.pos = (int)(Math.random() * ((max - min) + 1)) + min;
		this.inodes = new float[inputSize];
		this.onodes = new float[inputSize-1];
		for(int i=0;i<inodes.length;i++) {
			inodes[i] = r.nextInt(10);
		}
		for(int i=0;i<onodes.length;i++) {
			onodes[i] = 0;
		}
		
		
		
	}

	public void runLayer(float[] str) {
		this.onodes = getOutnodes(this.inodes,str);

	}
	
	
	
	
	

	/** Should have 2* the outnodes count which is one less than the innode count
	 * @return 
	 * 
	 * 
	 * */
	static float[] getOutnodes(float[] inodes,float[] str) {
		float[] onodes = new float[inodes.length-1];
		if(str.length!=2*(onodes.length)) {
			throw new RuntimeException("Invalid Connection Count!");
		}
		/* Input Node = IN,%
		 * Output Node= ON,&
		 * Connection = c,---,/,\
		 * 
		 * inode[0] connects to onode[0] with str[0]
		 * inode[1] connects to onode[0] with str[1] 
		 * inode[1] connects to onode[1] with str[2]
		 * inode[2] connects to onode[1] with str[3]
		 * 
		 * inode[2] connects to onode[2] with str[4]
		 * And so forth, so in theory we do the first and last ones as they will have odd connections
		 * 
		 * */
		
			//onodes[0] = this.inodes[0]*str[0];
			//onodes[onodes.length-1] = this.inodes[inodes.length-1]*str[str.length-1];
			for(int i=0;i<onodes.length;i++) {
				//The count or I represents the connection number we're on //C stands for count, if is inode first, isc is second inode sc is what we need
				var ifc = i;
				var isc = i+1;
				var fcc = i+ifc;
			    var scc = i+isc;
			    var first = inodes[ifc]*str[fcc];
			    var sec = inodes[isc]*str[scc];	    
			   onodes[i] = first+sec;
			}

		return onodes;
		
		
		
		
	}
	
	public void makeChoice() {
		
		
		
		
		inodes = rankify(inodes); //Comment for default, ordinally sorts list 
		
	}
	
	
	
	
    /** @param A Floatlist
     *
     * 
     * This code is contributed by Swetank Modi
     * */
    static float[] rankify(float A[])
    {
    	int n = A.length;
        // Rank Vector
        float R[] = new float[n];
     
        // Sweep through all elements in A
        // for each element count the number
        // of less than and equal elements
        // separately in r and s
        for (int i = 0; i < n; i++) {
            int r = 1, s = 1;
             
            for (int j = 0; j < n; j++) 
            {
                if (j != i && A[j] < A[i])
                    r += 1;
                     
                if (j != i && A[j] == A[i])
                    s += 1;     
            }
         
        // Use formula to obtain  rank
        R[i] = r + (float)(s - 1) / (float) 2;
     
        } 
     
       /* for (int i = 0; i < n; i++) {
            System.out.print(R[i] + "  ");
        }*/
        return R;
         
    }

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	public int lastMove;
}
