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
	static int[] tree;
	public static void main(String[] args) {
		int siz=128;
		long starttime = System.currentTimeMillis();
		int[][][] sos = new int[siz][siz][siz];
		int[] sos2 = new int[siz*siz*siz];
		int len = 128*128*128+64*64*64+32*32*32+16*16*16+8*8*8+4*4*4+2*2*2+1*1*1;
		int c = (int) Math.ceil((Math.log10(siz)/Math.log10(2)));
		print(c);
		print(Math.pow(2, c+1));
		print(""+getlentree(c+1));
		print(len);
		tree=new int[getlentree(c+1)];
		
		
		
		
		ArrayList<Integer> Times = new ArrayList<Integer>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0;i<siz;i++) {
			for(int j = 0;j<siz;j++) {
				for(int k = 0;k<siz;k++) {
					sos[i][j][k]=(int)(Math.random()*siz);
				}
			}
			al.add((int)(Math.random()*siz));
		}
		for(int t = 0; t<timenum; t++) {
			
			int x = 0;
			int y = 0;
			int color;
			starttime = System.currentTimeMillis();
			while(x<1920) {
				y=0;
				while(y<1080) {
					for(int z=0;z<siz;z++) {
						
						
						//color=sos[x%100][y%100][z];
						color = sos2[(x%siz)*(siz*siz)+(y%siz)*(siz)+z];
						//color = getindex(sos2,x%siz,y%siz,z%siz,siz,siz);
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
	
	static int gentree(int i,int x,int y,int z,int[] d) {
		if(i>0) {
		int s = gentree(i-1,x*2+0,y*2+0,z*2+0,d)+
		gentree(i-1,x*2+1,y*2+0,z*2+0,d)+
		gentree(i-1,x*2+0,y*2+1,z*2+0,d)+
		gentree(i-1,x*2+1,y*2+1,z*2+0,d)+
		gentree(i-1,x*2+0,y*2+0,z*2+1,d)+
		gentree(i-1,x*2+1,y*2+0,z*2+1,d)+
		gentree(i-1,x*2+0,y*2+1,z*2+1,d)+
		gentree(i-1,x*2+1,y*2+1,z*2+1,d);
		
		
			return 1;
		}else {
			return 1;
		}
	}
	static int getdattree(int i,int x,int y,int z,int[] d) {
		return (d[getlentree(i)]+);
	}
	static int getlentree(int i) {
		int l =0;
		for(int j = 0; j<i;j++) {
			l+=(int)Math.pow(8, j);
		}
		return l;
	}
}
