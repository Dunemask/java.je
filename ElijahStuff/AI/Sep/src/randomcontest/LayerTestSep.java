package randomcontest;

import java.util.ArrayList;
import java.util.Random;

public class LayerTestSep {

	public static void main(String[] args) {
		var dx = .1f;
		var step = -2;
		var in = new Inode[3];
		var rand = new Random();
		var opm = rand.nextInt(3);
		//1 opponent did rock
		//2 opponent did paper
		//3 opponent did scissors
		if(opm==0) {
			in[0] = new Inode(0,1);
			in[1] = new Inode(1,0);
			in[2] = new Inode(2,0);
		}else if(opm==1) {
			in[0] = new Inode(0,0);
			in[1] = new Inode(1,1);
			in[2] = new Inode(2,0);
		}else {
			in[0] = new Inode(0,0);
			in[1] = new Inode(1,0);
			in[2] = new Inode(2,1);
		}
		var nl = new NetLayer(in, 3);
		nl.runLayer();
		var costs = new float[nl.getOnodes().length];
		for(int i=0;i<nl.getOnodes().length;i++) {
			int wval = 1;
			var tcost = cost(wval,nl.getOnodes()[i]);
			//System.out.println(nl.getOnodes()[i]);
			costs[i] = tcost;
		}
		var bi = 0; //Best Index
		for(int i=0;i<costs.length;i++) {
			if((1-costs[i])>(1-costs[bi])) {
				bi = i;
			}
		}
		//1 opponent did rock
		//2 opponent did paper
		//3 opponent did scissors
		System.out.println("SAW  1-R | 2-P | 3-S: "+opm);
		System.out.println("CHOSE:"+bi);
		System.out.println("WANTED: S1-2 | S2-3 | S3-1");
		
		var sumcost =0.0f;
		for(int i=0;i<costs.length;i++) {
			sumcost+=costs[i];
		}
		
		for(int i=0;i<nl.getConnections().length;i++) {

			var ncost = 
			
			
		}
		

	}
	
	
	
	public static float cost(float wval,Onode oval) {
		var ret = 0.0f;
		var ov = oval.getValue();
		ret = (float) Math.pow((wval-ov),2);
		return ret;
	}
	

}
