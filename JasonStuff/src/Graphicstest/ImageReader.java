package Graphicstest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	public int[][][] imagedat;
	public int GetPixel(float x, float y, String image){
		BufferedImage img=null;
		File f = null;
		f = new File(image);
		try {
			img= ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width= img.getWidth();
		int height = img.getHeight();
		int p = img.getRGB((int)((x%1)*width), (int)((y%1)*height));
		return p;
	}
	public ImageReader(String image) {
		BufferedImage img=null;
		File f = null;
		f = new File(image);
		try {
			img= ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width= img.getWidth();
		int height = img.getHeight();
		imagedat = new int[height/width][width][width];
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				imagedat[y/width][x][y%width]=img.getRGB(x,y);
			}
		}
	}

}
