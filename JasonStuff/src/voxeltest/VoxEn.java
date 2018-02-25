package voxeltest;

import Graphicstest.Vector3;

public class VoxEn {
	Vector3 campos;
	double rx = 0;
	double ry = 0;
	double fovc = 0.01;
	int viewdist=3000;
	int[][][] Voxels = new int[100][100][100];
	public VoxEn(Vector3 vcam) {
		campos=vcam;
		resetCloud(1,10);
	}
	public int GetPix(int x, int y) {
		int blue = 0;
		int green=0;
		int red = 0;
		double mag = viewdist;
		Vector3 ve = new Vector3(0,0,-viewdist).rotate(Math.sqrt(x*x+y*y)*fovc,"x").rotate(Math.atan2(y, x), "z");
		ve = ve.rotate(ry, "y").rotate(rx, "z");
		for(int z = 0;z<Voxels[0][0].length;z++) {
		Vector3 v3 = Vector3.add(campos, new Vector3(0,0,0));
		if(ve.z>0) {
		double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "z");
		if(0<(int)doi[0]&&(int)doi[0]<100&&0<(int)doi[1]&&(int)doi[1]<100) {
			int val= Voxels[(int)doi[0]][(int)doi[1]][z];
			
			if(val>100) {
				double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
				if(mag>tmpmag) {
					mag=tmpmag;
					red = (val);
					green=5*(int)(tmpmag);
					//z=Voxels[0][0].length;
				}
				}
			}
		
		}
		}
		/*for(int yy = 0;yy<Voxels[0].length;yy++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,yy,0));
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "y");
			if(0<(int)doi[0]&&(int)doi[0]<100&&0<(int)doi[2]&&(int)doi[2]<100) {
				int val= Voxels[(int)doi[0]][yy][(int)doi[2]];
				
				if(val>100) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						green = (val);
						red=5*(int)(tmpmag);
						//z=Voxels[0][0].length;
					}
					}
				}
			
			}
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		return (red << 16) | (green << 8) | blue;
	}
	public void RotateCam(double dx, double dy) {
		rx +=dx;
		ry +=dy;
	}
	public void SetCam(double dx, double dy) {
		rx =dx;
		ry =dy;
	}
	public void MoveCam(Vector3 v3) {
		campos=Vector3.add(campos, v3);
	}
	public void resetCloud(double weight, int count){
		int[][][] tmp = new int[Voxels.length][Voxels[0].length][Voxels[0][0].length];
		for(int x = 0; x < Voxels.length; x++) {
			for(int y = 0; y < Voxels[0].length; y++) {
				for(int z = 0; z < Voxels[0][0].length; z++) {
					Voxels[x][y][z]=(int) (Math.random()*200);
				}
			}
			//System.out.println("Done with plane "+x);
		}
		for(int i = 0; i< count; i++) {
		for(int x = 1; x < Voxels.length-1; x++) {
			for(int y = 1; y < Voxels[0].length-1; y++) {
				for(int z = 1; z < Voxels[0][0].length-1; z++) {
					int sum = 0;
					sum+=Voxels[x+1][y][z];
					sum+=Voxels[x-1][y][z];
					sum+=Voxels[x][y+1][z];
					sum+=Voxels[x][y-1][z];
					sum+=Voxels[x][y][z+1];
					sum+=Voxels[x][y][z-1];
					tmp[x][y][z]=(int) (Voxels[x][y][z]*(1-(weight*6/7))+(sum*weight/7));
				}
			}
			//System.out.println("Done avging plane "+x);
		}
		}
		Voxels=tmp;
		System.out.println("Done With Cloud");
	}
}
