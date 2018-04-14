package minerender;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class VoxPanel extends JPanel{
	public VoxEn env =null;
	public int res = 4;
	int border = 2;
	//Paints the image to do whatever you want
	public void paint(Graphics g) {
		//System.out.println("PPaint");

		BufferedImage img = RendImage();
		g.drawImage(img, 0, 0, img.getWidth()*res, img.getHeight()*res, this);
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
