package minerender;

import minerender.VoxEn;

public class VoxGen {
	VoxEn v;
	byte[][][] tree = {
			{{7,6,6,6,7},{6,6,6,6,6},{6,6,5,6,6},{6,6,6,6,6},{7,6,6,6,7}},
			{{7,6,6,6,7},{6,6,6,6,6},{6,6,5,6,6},{6,6,6,6,6},{7,6,6,6,7}},
			{{0,0,7,0,0},{0,6,6,6,0},{0,6,5,6,0},{0,6,6,6,0},{0,0,7,0,0}},
			{{0,0,0,0,0},{0,7,6,7,0},{0,6,6,6,0},{0,7,6,7,0},{0,0,0,0,0}}
		}; 
	public VoxGen(VoxEn ven) {
		v = ven;
	}
	public void MakeTree(int x, int y) {
		try {
		for(int i=0; i < v.Voxels[0][0].length;i++) {
			if(v.Voxels[x][y][i]==2) {
				int h = 3+ (int)(Math.random()*5);
				for(int j = 1; j< h;j++ )
				v.Voxels[x][y][i+j]=4;
				for(int t = 0; t<5*5*4;t++) {
					//System.out.println("X: "+t%5+" Y: "+(t/5)%5+" Z: "+t/25);
					if(tree[t/25][(t/5)%5][t%5]>4) {
						if(tree[t/25][(t/5)%5][t%5]==6)
						v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=5;
						if(tree[t/25][(t/5)%5][t%5]==5)
						v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=4;
						if(tree[t/25][(t/5)%5][t%5]==7)
							if(Math.random()>0.5)
							v.Voxels[x-2+t%5][y-2+(t/5)%5][i+h+t/25]=5;
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

}
