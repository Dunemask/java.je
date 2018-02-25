package Graphicstest;

public class Vector3 {
	public double x;
	public double y;
	public double z;
	public Vector3(double xx, double yy, double zz) {
	x=xx;
	y=yy;
	z=zz;
	}
	public Vector3 rotate(double ang, String axis) {
		if(axis=="x") {
			return new Vector3(x,Math.cos(ang)*y-Math.sin(ang)*z,Math.sin(ang)*y+Math.cos(ang)*z);
		}else
		if(axis=="y") {
			return new Vector3(Math.cos(ang)*x-Math.sin(ang)*z,y,Math.sin(ang)*x+Math.cos(ang)*z);
		}else
		if(axis=="z") {
			return new Vector3(Math.cos(ang)*x-Math.sin(ang)*y,Math.sin(ang)*x+Math.cos(ang)*y,z);
		}else
		return new Vector3(Math.cos(ang)*z*Math.sin(Double.parseDouble(axis)),Math.cos(ang)*z*Math.cos(Double.parseDouble(axis)),Math.sin(ang)*z);
	}
	public double magnitude() {
		return (Math.sqrt(x*x+y*y+z*z));
	}
	public static Vector3 add(Vector3 v1,Vector3 v2) {
		return new Vector3 (v1.x+v2.x,v1.y+v2.y,v1.z+v2.z);
	}
	public static Vector3 subtract(Vector3 v1,Vector3 v2) {
		return new Vector3 (v1.x-v2.x,v1.y-v2.y,v1.z-v2.z);
	}
	public static Vector3 scalmultiply(Vector3 v1, double m) {
		return new Vector3 (v1.x*m,v1.y*m,v1.z*m);
	}
	public static Vector3 normal(Vector3 v1, Vector3 v2, Vector3 v3) {
		double normx=(v2.y-v1.y)*(v3.z-v1.z)-(v2.z-v1.z)*(v3.y-v1.y);
		double normy=(v2.z-v1.z)*(v3.x-v1.x)-(v2.x-v1.x)*(v3.z-v1.z);
		double normz=(v2.x-v1.x)*(v3.y-v1.y)-(v2.y-v1.y)*(v3.x-v1.x);
		return new Vector3(normx,normy,normz);
	}
	public static double[] intersection(Vector3 va,Vector3 vb,String plane) {
		double xint=0;
		double yint=0;
		double zint=0;
		if (plane == "z") {
			xint = va.x+(vb.x-va.x)*va.z/(va.z+vb.z);
			yint = va.y+(vb.y-va.y)*va.z/(va.z+vb.z);
			zint = 0;
		}
		if (plane == "y") {
			xint = va.x+(vb.x-va.x)*va.y/(va.y+vb.y);
			zint = va.z+(vb.z-va.z)*va.y/(va.y+vb.y);
			yint = 0;
		}
		if (plane == "x") {
			yint = va.y+(vb.y-va.y)*va.x/(va.x+vb.x);
			zint = va.z+(vb.z-va.z)*va.x/(va.x+vb.x);
			xint = 0;
		}
		double[] dub = {xint,yint,zint,0};
		return dub;
	}
	public static double[] intersection(Vector3 va,Vector3 vb,Vector3 v0,Vector3 v1,Vector3 v2){
		boolean intri=false;
		double xa=va.x;
		double ya=va.y;
		double za=va.z;
		double xb=vb.x;
		double yb=vb.y;
		double zb=vb.z;
		double x0=v0.x;
		double x1=v1.x;
		double x2=v2.x;
		double y0=v0.y;
		double y1=v1.y;
		double y2=v2.y;
		double z0=v0.z;
		double z1=v1.z;
		double z2=v2.z;

		double det = (x2-x0)*(ya-yb)*(z1-z0)+(x1-x0)*(y2-y0)*(za-zb)+(xa-xb)*(y1-y0)*(z2-z0);
		det-=        (xa-xb)*(y2-y0)*(z1-z0)+(x1-x0)*(ya-yb)*(z2-z0)+(x2-x0)*(y1-y0)*(za-zb);
		
		double tint= (((y1-y0)*(z2-z0)-(y2-y0)*(z1-z0))*(xa-x0)+((x2-x0)*(z1-z0)-(x1-x0)*(z2-z0))*(ya-y0)+((x1-x0)*(y2-y0)-(x2-x0)*(y1-y0))*(za-z0))/det;
		double uint= (((y2-y0)*(za-zb)-(ya-yb)*(z2-z0))*(xa-x0)+((xa-xb)*(z2-z0)-(x2-x0)*(za-zb))*(ya-y0)+((x2-x0)*(ya-yb)-(xa-xb)*(y2-y0))*(za-z0))/det;
		double vint= (((ya-yb)*(z1-z0)-(y1-y0)*(za-zb))*(xa-x0)+((x1-x0)*(za-zb)-(xa-xb)*(z1-z0))*(ya-y0)+((xa-xb)*(y1-y0)-(x1-x0)*(ya-yb))*(za-z0))/det;
		
		double xint=xa+(xb-xa)*tint;
		double yint=ya+(yb-ya)*tint;
		double zint=za+(zb-za)*tint;
		if(0<uint&&uint<1&&0<vint&&vint<1) {
			//if(uint+vint<1) {
				if(0<tint&&tint<1) {
					intri=true;
				}
			//}
		}
		double[] list = {tint,uint,vint,xint,yint,zint,0};
		if (intri) {
			list[6]=1;
		}
		return list;
	}
	public static Vector3 rand(int mag) {
		
		return new Vector3((Math.random()-0.5)*mag*2,(Math.random()-0.5)*mag*2,(Math.random()-0.5)*mag*2);
	}
	public static Vector3 foreward() {
		return new Vector3(1,0,0);
	}
	public static Vector3 backward() {
		return new Vector3(-1,0,0);
	}
	public static Vector3 rightward() {
		return new Vector3(0,1,0);
	}
	public static Vector3 leftward() {
		return new Vector3(0,-1,0);
	}
	public static Vector3 upward() {
		return new Vector3(0,0,1);
	}
	public static Vector3 downward() {
		return new Vector3(0,0,-1);
	}
}
