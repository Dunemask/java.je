package minerender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class VoxPanel extends JPanel{
	public VoxEn env =null;
	public int res = getWidth()/140+1;
	int border = 6;
	//Paints the image to do whatever you want
	public void paint(Graphics g) {
		res = getWidth()/140+1;
		//System.out.println("PPaint");
		g.fillRect(0, 0, getWidth(), getHeight());
		BufferedImage img = RendImage();
		g.drawImage(img, 0, 0, img.getWidth()*res, img.getHeight()*res, this);
		
		
		int width = getSize().width;
	 	int height = width/9;
	 	int y=getSize().height-height;
	 	for (int i = 0;i<9;i++) {
	 	if(env.hotbar[i]>0) {
	 	int[] data2 = env.imgas[env.blks.get(env.hotbar[i]-1).image[1]].rawdat[0];
	 	int len = env.imgas[env.blks.get(env.hotbar[i]-1).image[1]].imagedat[0].length;
	    BufferedImage image2 = new BufferedImage(len, len, BufferedImage.TYPE_INT_RGB);
	    image2.setRGB(0, 0, len, len, data2, 0, len);
	    if(env.selected == i) {
	    g.drawImage(image2, i*height, y, height, height, this);
	    } else {
	    	g.drawImage(image2, i*height+border, y+border, height-2*border, height-2*border, this);
	    }
	    }
	 	}
	}
	
	
	//CREATES THE IMAGE USING EACH OF THE PIXELS:::
	public BufferedImage RendImage() {
		int width = getSize().width/res;
		int height = (getSize().height-getSize().width/9)/res;
		int[] data = new int [width*height];
		int i = 0;
		
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width;x++) {
				data[i++] = env.GetPix(x-width/2, y-height/2);
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, width, height, data, 0, width);
		return image;
	}
	public VoxPanel(VoxEn ven) {
		env= ven;
	}
	public VoxPanel() {
		
	}
	public void SetEnv(VoxEn en) {
		env = en;
	}

}
