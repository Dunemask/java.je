package voxeltest;

import Graphicstest.Vector3;

public class VoxEn {
	Vector3 campos;
	double rx = 0;
	double ry = 0;
	double fovc = 0.01;
	int viewdist=3000;
	int density = 0;
	int siz = 1;
	VoxChunk[][][] vc = new VoxChunk[4][4][4];
	byte[][][] Voxels = new byte[16][16][16];
	public VoxEn(Vector3 vcam) {
		campos=vcam;
		resetCloud(1,50);
	}
	public int GetPix(int x, int y) {
		int blue = 0;
		int green=0;
		int red = 0;
		int zlen = Voxels[0][0].length;
		int ylen = Voxels[0].length;
		int xlen = Voxels.length;
		double mag = viewdist;
		Vector3 ve = new Vector3(0,0,-viewdist).rotate(Math.sqrt(x*x+y*y)*fovc,"x").rotate(Math.atan2(y, x), "z");
		ve = ve.rotate(ry, "y").rotate(rx, "z");
		for(int z = 0;z<zlen;z++) {
		Vector3 v3 = Vector3.add(campos, new Vector3(0,0,-z));
		if((v3.z<0&&Vector3.add(v3, ve).z>0)) {
		double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "z");
		if(0<(int)doi[0]&&(int)doi[0]<xlen&&0<(int)doi[1]&&(int)doi[1]<ylen) {
			int val= Voxels[(int)doi[0]][(int)doi[1]][z];
			
			if(val>density) {
				double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
				if(mag>tmpmag) {
					mag=tmpmag;
					red = (val+128);
					green=(siz*(int)(tmpmag))%256;
					blue=0;
				}
				}
			}
		
		}
		}
		for(int z = 0;z<zlen;z++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,0,-1-z));
			if((v3.z>0&&Vector3.add(v3, ve).z<0)) {
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "z");
			if(0<(int)doi[0]&&(int)doi[0]<xlen&&0<(int)doi[1]&&(int)doi[1]<ylen) {
				int val= Voxels[(int)doi[0]][(int)doi[1]][z];
				
				if(val>density) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						red = (val+128);
						green=(siz*(int)(tmpmag))%256;
						blue=0;
					}
					}
				}
			
			}
			}
		for(int yy = 0;yy<ylen;yy++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,-yy,0));
			if((v3.y<0&&Vector3.add(v3, ve).y>0)) {
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "y");
			if(0<(int)doi[0]&&(int)doi[0]<xlen&&0<(int)doi[2]&&(int)doi[2]<zlen) {
				int val= Voxels[(int)doi[0]][yy][(int)doi[2]];
				
				if(val>density) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						green = (val+128);
						blue=(siz*(int)(tmpmag))%256;
						red=0;
					}
					}
				}
			}
			}
		for(int yy = 0;yy<ylen;yy++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,-1-yy,0));
			if((v3.y>0&&Vector3.add(v3, ve).y<0)) {
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "y");
			if(0<(int)doi[0]&&(int)doi[0]<xlen&&0<(int)doi[2]&&(int)doi[2]<zlen) {
				int val= Voxels[(int)doi[0]][yy][(int)doi[2]];
				
				if(val>density) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						green = (val+128);
						blue=(siz*(int)(tmpmag))%256;
						red=0;
					}
					}
				}
			}
			}
		for(int xx = 0;xx<xlen;xx++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(-xx,0,0));
			if((v3.x<0&&Vector3.add(v3, ve).x>0)) {
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "x");
			if(0<(int)doi[1]&&(int)doi[1]<ylen&&0<(int)doi[2]&&(int)doi[2]<zlen) {
				int val= Voxels[xx][(int)doi[1]][(int)doi[2]];
				
				if(val>density) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						blue = (val+128);
						red=(siz*(int)(tmpmag))%256;
						green=0;
					}
					}
				}
			}
			}
		for(int xx = 0;xx<xlen;xx++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(-1-xx,0,0));
			if((v3.x>0&&Vector3.add(v3, ve).x<0)) {
			double[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "x");
			if(0<(int)doi[1]&&(int)doi[1]<ylen&&0<(int)doi[2]&&(int)doi[2]<zlen) {
				int val= Voxels[xx][(int)doi[1]][(int)doi[2]];
				
				if(val>density) {
					double tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						blue = (val+128);
						red=(siz*(int)(tmpmag))%256;
						green=0;
					}
					}
				}
			}
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
		if(Inside(Vector3.add(v3, campos))<density) {
		campos=Vector3.add(campos, v3);
		}
		
		
	}
	public byte Inside(Vector3 v3) {
		if(0<v3.x&&v3.x<Voxels.length&&0<v3.y&&v3.y<Voxels[0].length&&0<v3.z&&v3.z<Voxels[0][0].length) {
		//System.out.println(Voxels[(int)v3.x][(int)v3.y][(int)v3.z]+"  "+(int)v3.x);
			return Voxels[(int)v3.x][(int)v3.y][(int)v3.z];
		
		}else {
		return -128;
		} 
	}
	public void resetCloud(double weight, int count){
		
		
		byte[][][] tmp = new byte[Voxels.length][Voxels[0].length][Voxels[0][0].length];
		for(int x = 0; x < Voxels.length; x++) {
			for(int y = 0; y < Voxels[0].length; y++) {
				for(int z = 0; z < Voxels[0][0].length; z++) {
					Voxels[x][y][z]=(byte) (Math.random()*256-128);
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
					tmp[x][y][z]=(byte) (Voxels[x][y][z]*(1-(weight*6/7))+(sum*weight/7));
				}
			}
			//System.out.println("Done avging plane "+x);
		}
		}
		Voxels= tmp;
		System.out.println("Done With Cloud");
		
		
		
		
		
	}
}
