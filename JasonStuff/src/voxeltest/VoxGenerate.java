package voxeltest;

import java.util.ArrayList;

public class VoxGenerate {
	VoxEn v;
	byte[][][] tree = {
			{{7,6,6,6,7},{6,6,6,6,6},{6,6,5,6,6},{6,6,6,6,6},{7,6,6,6,7}},
			{{7,6,6,6,7},{6,6,6,6,6},{6,6,5,6,6},{6,6,6,6,6},{7,6,6,6,7}},
			{{0,0,7,0,0},{0,6,6,6,0},{0,6,5,6,0},{0,6,6,6,0},{0,0,7,0,0}},
			{{0,0,0,0,0},{0,7,6,7,0},{0,6,6,6,0},{0,7,6,7,0},{0,0,0,0,0}}
		}; 
	public VoxGenerate(VoxEn ven) {
		v = ven;
	}
	public void MakeTree(int x, int y) {
		try {
		for(int i=0; i < v.Voxels[0][0].length;i++) {
			if(v.Voxels[x][y][i]==2) {
				int h = 3+ (int)(Math.random()*5);
				for(int j = 1; j< h;j++ )
				v.Voxels[x][y][i+j]=5;
				for(int t = 0; t<5*5*4;t++) {
					//System.out.println("X: "+t%5+" Y: "+(t/5)%5+" Z: "+t/25);
					if(tree[t/25][(t/5)%5][t%5]>5) {
						if(tree[t/25][(t/5)%5][t%5]==6)
						v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=6;
						if(tree[t/25][(t/5)%5][t%5]==5)
						v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=5;
						if(tree[t/25][(t/5)%5][t%5]==7)
							if(Math.random()>0.5)
							v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=6;
					}
				}
			}
		}
		}catch(ArrayIndexOutOfBoundsException e) {
			//System.out.print("yolo");
		}
	}
	public void SpreadTree(float density, int x, int y, int s) {
		for(int i=0; i < s*s*2*2;i++) {
			if(Math.random()<density) {
				MakeTree(x-s+(i%(2*s)), y-s+i/(2*s));
			}
		}
	}
	public void MakePond(int x, int y) {
		for(int i=0; i < v.Voxels[0][0].length;i++) {
			if(v.Voxels[x][y][i]==2) {
				float size = (float)(4+Math.random()*4);
				ReplaceSpheroid(x,y,i,size,0,2);
				ReplaceSpheroid(x,y,i,size+2,0,1);
				FloodFillDown(x,y,i-3,7,0);
			}
		}
	}
	public void ReplaceSpheroid(int x, int y, int z, float size , int block, int replaced) {
		for(int xx=-(int)size; xx < size+1; xx ++) {
			for(int yy=-(int)size; yy < size+1; yy ++) {
				for(int zz=-(int)size; zz < size+1; zz ++) {
					if(size*size>xx*xx+yy*yy+zz*zz) {
						if(x+xx>0&&x+xx<v.Voxels.length&&y+yy>0&&y+yy<v.Voxels[0].length&&zz+z>0&&z+zz<v.Voxels[0][0].length)
						if(v.Voxels[x+xx][y+yy][z+zz]==replaced)
							v.Voxels[x+xx][y+yy][z+zz] = (byte)block;
					}
				}
			}
		}
	}
	public void FloodFillDown(int x, int y, int z, int block, int replaced) {
		ArrayList<int[]> al= new ArrayList<int[]>();
		ArrayList<int[]> nl= new ArrayList<int[]>();
		int[] i = {x,y,z};
		al.add(i);
		while(!al.isEmpty()) {
			nl.clear();
			for(int[] el: al) {
				if(el[0]<1||el[0]>v.Voxels.length-1||el[1]<1||el[1]>v.Voxels[0].length-1||el[2]<1||el[2]>v.Voxels[0][0].length-1) {
					//STUFF
				} else {
					v.Voxels[el[0]][el[1]][el[2]]=(byte)block;
					if (v.Voxels[el[0]+1][el[1]][el[2]]==replaced) {
						int[] j = {el[0]+1,el[1],el[2]};
						nl.add(j);
					}if (v.Voxels[el[0]-1][el[1]][el[2]]==replaced) {
						int[] j = {el[0]-1,el[1],el[2]};
						nl.add(j);
					}if (v.Voxels[el[0]][el[1]+1][el[2]]==replaced) {
						int[] j = {el[0],el[1]+1,el[2]};
						nl.add(j);
					}if (v.Voxels[el[0]][el[1]-1][el[2]]==replaced) {
						int[] j = {el[0],el[1]-1,el[2]};
						nl.add(j);	
					}if (v.Voxels[el[0]][el[1]][el[2]-1]==replaced) {
						int[] j = {el[0],el[1],el[2]-1};
						nl.add(j);
					}else {
						//Stuff
					}
				}
			}
			al=new ArrayList<int[]>(nl);
		}
	}
}
