/**
 * 
 */
package main;

import java.util.ArrayList;

/**
 * @author Jason Roberts
 *
 */
public class TimingTests {
	static int timenum = 10;
	public static void main(String[] args) {
		
		long starttime = System.currentTimeMillis();
		int[][][] sos = new int[100][100][100];
		int[] sos2 = new int[100*100*100];
		ArrayList<Integer> Times = new ArrayList<Integer>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				for(int k = 0;k<10;k++) {
					sos[i][j][k]=(int)(Math.random()*100);
				}
			}
			al.add((int)(Math.random()*100));
		}
		for(int t = 0; t<timenum; t++) {
			
			int x = 0;
			int y = 10;
			int color;
			starttime = System.currentTimeMillis();
			while(x<1920) {
				y=0;
				while(y<1080) {
					for(int z=0;z<100;z++) {
						
						
						//color=sos[x%100][y%100][z];
						color = sos2[(x%100)*(100*100)+(y%100)*(100)+z];
						//color = getindex(sos2,x%100,y%100,z%100,100,100);
						if(color<50) {
							
						}else {
							z=100;
						}
					
					}
					y++;
				}
				x++;
			}
			print(System.currentTimeMillis()-starttime);
			Times.add((int) (System.currentTimeMillis()-starttime));
		}
		int tot = 0;
		for(int t=0; t<timenum;t++) {
			tot+= Times.get(t);
		}
		print(tot);
		
		
	}
	static void print(Object s) {
		System.out.println(s);
	}
	static boolean[][] truncatesos(int[][] input, int split) {
		boolean[][] il = new boolean[(input.length+1)/2][(input[0].length+1)/2];
		for(int x = 0;x<il.length;x++) {
			
		}
		
		return il;
	}
	static int getindex(int[] i, int x, int y, int z,int len, int wid) {
		return i[(x)*(len*wid)+(y)*(len)+z];
	}
}
