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
		resetCloud(0.5,3);
	}
	public int GetPix(int x, int y) {
		int blue = y%256;
		int green=x%256;
		int red = 255;
		Vector3 ve = new Vector3(0,0,-viewdist).rotate(Math.sqrt(x*x+y*y)*fovc,"x").rotate(Math.atan2(y, x), "z");
		ve = ve.rotate(ry, "y").rotate(rx, "z");
		for(int z = 0;z<Voxels.length;z++) {
		double[] doi =Vector3.intersection(campos, Vector3.add(campos, ve), "z");
		
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
