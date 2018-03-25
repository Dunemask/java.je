package voxeltest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class VoxPanel extends JComponent {
	public VoxEn e3d = null;
	public int res = 4;
	int border = 2;
	public void paint(Graphics g) {
		 	int width = getSize().width/res;
		    int height = (getSize().height-getSize().width/9)/res;
		    int[] data = new int[width * height];
		    int i = 0;
		    //e3d.UpdateAirs();
		    for (int y = 0; y < height; y++) {
		      //int red = (y * 255) / (height - 1);
		      for (int x = 0; x < width; x++) {
		        //int green = (x * 255) / (width - 1);
		        //int blue = 0;
		        //blue+=Graphicstest.TestMain.rx;
		        data[i++] = e3d.GetPix(x-width/2, y-height/2);
		      }
		    }
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    image.setRGB(0, 0, width, height, data, 0, width);
		    g.drawImage(image, 0, 0, width*res, height*res, this);
		    
		    
		    //HOTBAR
		    
			width = getSize().width;
		 	height = width/9;
		 	int y=getSize().height-height;
		 	for (i = 0;i<9;i++) {
		 	if(e3d.hotbar[i]>0) {
		 	int[] data2 = e3d.imgas[e3d.blkrndr[e3d.hotbar[i]-1][1]].rawdat[0];
		 	int len = e3d.imgas[e3d.blkrndr[e3d.hotbar[i]-1][1]].imagedat[0].length;
		    BufferedImage image2 = new BufferedImage(len, len, BufferedImage.TYPE_INT_RGB);
		    image2.setRGB(0, 0, len, len, data2, 0, len);
		    if(e3d.selected == i) {
		    g.drawImage(image2, i*height, y, height, height, this);
		    } else {
		    	g.drawImage(image2, i*height+border, y+border, height-2*border, height-2*border, this);
		    }
		    }
		 	}
	}
	public void SetE3d(VoxEn edd) {
		e3d=edd;
	}

}
