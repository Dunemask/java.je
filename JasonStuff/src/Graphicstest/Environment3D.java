package Graphicstest;
import java.util.*;
public class Environment3D {
	Vector3 campos;
	double rx = 0;
	double ry = 0;
	double fovc = 0.01;
	int viewdist = 3000;
	ImageReader imgrd = new ImageReader("src/Graphicstest/img.png");
	ArrayList<Vector3[]> faces = new ArrayList<Vector3[]>();
	public Environment3D(Vector3 vcam, int xr, int yr) {
		
	campos= vcam;
	}
	public int GetPix(int x,int y) {
		Vector3 ve = new Vector3(0,0,-viewdist).rotate(Math.sqrt(x*x+y*y)*fovc,"x").rotate(Math.atan2(y, x), "z");
		ve = ve.rotate(ry, "y").rotate(rx, "z");
		int blue = 0;
		int green=0;
		int red = 0;
		double[] db = null;
		for(int i=0; i<faces.size(); i++) {
		
		double[] dub = Vector3.intersection(campos, Vector3.add(campos, ve), faces.get(i)[0], faces.get(i)[1], faces.get(i)[2]);
		if(dub[6]==1) {
			db = dub;
		}
		}
		int pix = (red << 16) | (green << 8) | blue;
		if(db!=null&&db[6]==1) {
			red=255;
			pix = (red << 16) | (green << 8) | blue;
			pix = imgrd.imagedat[(int)(db[1]*imgrd.imagedat.length)][(int)(db[2]*imgrd.imagedat[0].length)];
		}
		
		
		return pix;
	}
	public void AddFace(Vector3 v1, Vector3 v2, Vector3 v3) {
		Vector3[] v4 = new Vector3[3];
		v4[0]=v1;
		v4[1]=v2;
		v4[2]=v3;
		faces.add(v4);
	}
	public void ClearFaces() {
		faces.clear();
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
	
}
