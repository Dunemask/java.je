package voxeltest;

import java.io.File;

public class Testing {

	public static void main(String[] args) {
		int[] a = {0,0,1,6,2,3,5,3,2,2,2,3,3,2,0,0,0,1,5,5,5,5,5,5,5,5,5,5,2,3,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,3};
		String s = FileStuff.SaveArray(a);
		System.out.println(s);
		System.out.println(a.toString());
		int[] b = FileStuff.LoadArray(s);
		for(int i = 0; i < b.length; i++) {
			System.out.print(" "+b[i]);
		}
		System.out.println();
		for(int i = 0; i < a.length; i++) {
			System.out.print(" "+a[i]);
		}
		System.out.println();
		int[][][] tree = {
				{{7,6,6,6,7,1},{6,6,6,6,6,1},{6,6,5,6,6,1},{6,6,6,6,6,1},{7,6,6,6,7,1}},
				{{7,6,6,6,7,2},{6,6,6,6,6,2},{6,6,5,6,6,2},{6,6,6,6,6,2},{7,6,6,6,7,2}},
				{{0,0,7,0,0,3},{0,6,6,6,0,3},{0,6,5,6,0,3},{0,6,6,6,0,3},{0,0,7,0,0,3}},
				{{0,0,0,0,0,4},{0,7,6,7,0,4},{0,6,6,6,0,4},{0,7,6,7,0,4},{0,0,0,0,0,4}}
			}; 
		int[] tree1d = FileStuff.Array3Dto1D(tree);
		for(int i = 0; i < tree1d.length; i++) {
			System.out.print(" "+tree1d[i]);
		}
		System.out.println();
		int[][][] tree2 = FileStuff.Array1Dto3D(tree1d, 4, 5);
		int[] tree2d  = FileStuff.Array3Dto1D(tree2);
		for(int i = 0; i < tree2d.length; i++) {
			System.out.print(" "+tree2d[i]);
		}
		File f = FileStuff.Choose("src\\voxeltest\\saves");
	}

}
