package voxeltest;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Robot;

import Graphicstest.ImageReader;
import Graphicstest.Vector3;

public class VoxEn {
	Vector3 campos;
	int mx = 0;
	int my = 0;
	float rx = 0;
	float ry = 0;
	float fovc = 0.01f;
	int viewdist=3000;
	int density = 0;
	int siz = 1;
	Vector3 sel = new Vector3(0,0,0);
	String seld = "";
	ImageReader[] imgas = new ImageReader[5];
	ImageReader selecc = new ImageReader("src/voxeltest/select.png");
	byte[][] blkrndr = {
				{0,0,0,0,0,0},
				{0,2,1,1,1,1},
				{3,3,3,3,3,3},
				{4,4,4,4,4,4},
				{1,2,3,4,0,1}};
	VoxChunk[][][] vc = new VoxChunk[4][4][4];
	byte[][][] Voxels = new byte[100][100][18];
	public VoxEn(Vector3 vcam) {
		campos=vcam;
		resetCloud(1,4);
		//getimages
		for(int i = 0;i<imgas.length;i++) {
			imgas[i] = new ImageReader("src/voxeltest/img"+i+".png");
		}
	}
	public int GetPix(int x, int y) {
		int blue = 0;
		int green=0;
		int red = 0;
		int zlen = Voxels[0][0].length;
		int ylen = Voxels[0].length;
		int xlen = Voxels.length;
		int pix = 0;
		int val=0;
		int val2=0;
		byte bright=127;
		String side= "none";
		float mag = viewdist;
		Vector3 ve = new Vector3(0,0,-viewdist).rotate(Math.sqrt(x*x+y*y)*fovc,"x").rotate(Math.atan2(y, x), "z");
		ve = ve.rotate(ry, "y").rotate(rx, "z");
		float[] findub = new float[7];
		
		
		
		
		int zstrt = (int)campos.z;
		if(zstrt < 0) {
			zstrt=0;
		}
		for(int z = zstrt;z<zlen;z++) {
		Vector3 v3 = Vector3.add(campos, new Vector3(0,0,-z));
		if((v3.z<0&&Vector3.add(v3, ve).z>0)) {
		float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "z");
		if(0<doi[0]&&doi[0]<xlen&&0<doi[1]&&doi[1]<ylen) {
			val= Voxels[(int)doi[0]][(int)doi[1]][z];
			
			if(val>0) {
				float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
				if(mag>tmpmag) {
					mag=tmpmag;
					findub = doi;
					red = (val+128);
					green=(siz*(int)(tmpmag))%256;
					blue=0;
					side = "z1";
					z=zlen;
					val2=val;
				}
				}
			}
		
		}
		}
		zstrt = (int)campos.z;
		if(zstrt > zlen) {
			zstrt=zlen;
		}
		
		for(int z = zstrt-1;z>=0;z--) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,0,-1-z));
			if((v3.z>0&&Vector3.add(v3, ve).z<0)) {
			float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "z");
			if(0<doi[0]&&doi[0]<xlen&&0<doi[1]&&doi[1]<ylen) {
				val= Voxels[(int)doi[0]][(int)doi[1]][z];
				
				if(val>0) {
					float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						findub = doi;
						red = (val+128);
						green=(siz*(int)(tmpmag))%256;
						blue=0;
						side = "z2";
						z=-1;
						val2=val;
					}
					
					}
				}
			
			}
			}
		zstrt = (int)campos.y;
		if(zstrt <0) {
			zstrt=0;
		}
		for(int yy = zstrt;yy<ylen;yy++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,-yy,0));
			if((v3.y<0&&Vector3.add(v3, ve).y>0)) {
			float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "y");
			if(0<doi[0]&&doi[0]<xlen&&0<doi[2]&&doi[2]<zlen) {
				val= Voxels[(int)doi[0]][yy][(int)doi[2]];
				
				if(val>0) {
					float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						findub = doi;
						green = (val+128);
						blue=(siz*(int)(tmpmag))%256;
						red=0;
						side = "y1";
						yy=ylen;
						val2=val;
					}
					}
				}
			}
			}
		zstrt = (int)campos.y;
		if(zstrt > ylen) {
			zstrt=ylen;
		}
		for(int yy = zstrt-1;yy>=0;yy--) {
			Vector3 v3 = Vector3.add(campos, new Vector3(0,-1-yy,0));
			if((v3.y>0&&Vector3.add(v3, ve).y<0)) {
			float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "y");
			if(0<doi[0]&&doi[0]<xlen&&0<doi[2]&&doi[2]<zlen) {
				val= Voxels[(int)doi[0]][yy][(int)doi[2]];
				
				if(val>0) {
					float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						findub = doi;
						green = (val+128);
						blue=(siz*(int)(tmpmag))%256;
						red=0;
						side = "y2";
						yy=-1;
						val2=val;
					}
					}
				}
			}
			}
		zstrt = (int)campos.x;
		if(zstrt <0) {
			zstrt=0;
		}
		for(int xx = zstrt;xx<xlen;xx++) {
			Vector3 v3 = Vector3.add(campos, new Vector3(-xx,0,0));
			if((v3.x<0&&Vector3.add(v3, ve).x>0)) {
			float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "x");
			if(0<doi[1]&&doi[1]<ylen&&0<doi[2]&&doi[2]<zlen) {
				val= Voxels[xx][(int)doi[1]][(int)doi[2]];
				
				if(val>0) {
					float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						findub = doi;
						blue = (val+128);
						red=(siz*(int)(tmpmag))%256;
						green=0;
						side = "x1";
						xx=xlen;
						val2=val;
					}
					}
				}
			}
			}
		zstrt = (int)campos.x;
		if(zstrt > xlen) {
			zstrt=xlen;
		}
		for(int xx = zstrt-1;xx>=0;xx--) {
			Vector3 v3 = Vector3.add(campos, new Vector3(-1-xx,0,0));
			if((v3.x>0&&Vector3.add(v3, ve).x<0)) {
			float[] doi =Vector3.intersection(v3, Vector3.add(v3, ve), "x");
			if(0<doi[1]&&doi[1]<ylen&&0<doi[2]&&doi[2]<zlen) {
				val= Voxels[xx][(int)doi[1]][(int)doi[2]];
				
				if(val>0) {
					float tmpmag = Vector3.subtract(new Vector3(doi[0],doi[1],doi[2]), v3).magnitude();
					if(mag>tmpmag) {
						mag=tmpmag;
						findub = doi;
						blue = (val+128);
						red=(siz*(int)(tmpmag))%256;
						green=0;
						side = "x2";
						xx=-1;
						val2=val;
					}
					}
				}
			}
			}
		if(x==0&&y==0) {
			sel.x=(int)findub[0];
			sel.y=(int)findub[1];
			sel.z=(int)findub[2];
			sel.print();
			seld =side;
		}
		if(side=="x1") {
			bright=87;
			int[][] tdata = imgas[blkrndr[val2-1][4]].imagedat;
			pix = tdata[(int)Math.ceil(tdata.length*(1-findub[1]%1))-1][(int)Math.ceil(tdata[0].length*(1-findub[2]%1))-1];
		}
		if(side=="x2") {
			bright=87;
			int[][] tdata = imgas[blkrndr[val2-1][5]].imagedat;
			pix = tdata[(int)(tdata.length*(findub[1]%1))][(int)Math.ceil(tdata[0].length*(1-findub[2]%1))-1];
		}
		if(side=="y1") {
			bright=107;
			int[][] tdata = imgas[blkrndr[val2-1][2]].imagedat;
			pix = tdata[(int)(tdata.length*(findub[0]%1))][(int)Math.ceil(tdata[0].length*(1-findub[2]%1))-1];
		}
		if(side=="y2") {
			bright=107;
			int[][] tdata = imgas[blkrndr[val2-1][3]].imagedat;
			pix = tdata[(int)(tdata.length*(findub[0]%1))][(int)Math.ceil(tdata[0].length*(1-findub[2]%1))-1];
		}
		if(side=="z1") {
			bright=127;
			int[][] tdata = imgas[blkrndr[val2-1][0]].imagedat;
			pix = tdata[(int)(tdata.length*(findub[0]%1))][(int)(tdata[0].length*(findub[1]%1))];
		}
		if(side=="z2") {
			bright=127;
			int[][] tdata = imgas[blkrndr[val2-1][1]].imagedat;
			pix = tdata[(int)(tdata.length*(findub[0]%1))][(int)(tdata[0].length*(findub[1]%1))];
		}
		if(sel.x==(int)findub[0]&&sel.y==(int)findub[1]&&sel.z==(int)findub[2]) {
			if(seld == side) {
				int[][] tdata = selecc.imagedat;
				int dod=0;
				if(seld == "x1"||seld == "x2") 
				dod = tdata[(int)(tdata.length*(findub[1]%1))][(int)(tdata[0].length*(findub[2]%1))];
				if(seld == "y1"||seld == "y2") 
					dod = tdata[(int)(tdata.length*(findub[0]%1))][(int)(tdata[0].length*(findub[2]%1))];
				if(seld == "z1"||seld == "z2") 
					dod = tdata[(int)(tdata.length*(findub[0]%1))][(int)(tdata[0].length*(findub[1]%1))];
						
				if (dod!=0){
					//System.out.print(dod+" ");
					pix = dod;
				}
			}
		}
		if (pix==0) {
			pix = (red << 16) | (green << 8) | blue;
		}
		red = (pix >> 16) & 0xFF;
		green = (pix >> 8) & 0xFF;
		blue = pix & 0xFF;
		red = red*(bright+127)/256;
		green = green*(bright+127)/256;
		blue = blue*(bright+127)/256;
		pix = (red << 16) | (green << 8) | blue;
		return pix;
	}
	public void RotateCam(float dx, float dy) {
		rx +=dx;
		ry +=dy;
	}
	public void SetCam(float dx, float dy) {
		rx =dx;
		ry =dy;
	}
	public boolean MoveCam(Vector3 v3) {
		if(Inside(Vector3.add(v3, campos))<1) {
		campos=Vector3.add(campos, v3);
		return true;
		}else {
			return false;
		}
		
		
	}
	public void SetMouse(int xx,int yy) {
		mx = xx;
		my = yy;
	}
	public void MouseCam() {
		try {
			//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			Robot r = new Robot();
			//Robot s = new Robot();
			rx+=(mx-MouseInfo.getPointerInfo().getLocation().x)*-0.01;
			ry+=(my-MouseInfo.getPointerInfo().getLocation().y)*0.01;
			if(ry<0) {
				ry = 0;
			}
			if(ry>3.1415) {
				ry = 3.1415f;
			}
			r.mouseMove(mx, my);
		} catch (AWTException e) {
			e.printStackTrace();
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
	public void resetCloud(float weight, int count){
		
		
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
		for(int x = 1; x < Voxels.length-1; x++) {
			for(int y = 1; y < Voxels[0].length-1; y++) {
				for(int z = 1; z < Voxels[0][0].length-1; z++) {
					Voxels[x][y][z]=tmp[x][y][z];
				}
			}
		}
		}
		for(int x = 0; x < Voxels.length; x++) {
			for(int y = 0; y < Voxels[0].length; y++) {
				for(int z = 0; z < Voxels[0][0].length; z++) {
					if(Voxels[x][y][z]>density) {
						Voxels[x][y][z]=(byte) (Math.random()*4+1);
					}else {
						Voxels[x][y][z]=0;
					}
				}
			}
			//System.out.println("Done with plane "+x);
		}
		//Voxels= tmp;

		System.out.println("Done With Cloud");
		
		
		
		
		
	}
}
