/**
 * 
 */
package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Dunemask
 *
 */
public class BackPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5569709313790349207L;
	private Image back;

	/**
	 * 
	 */ 
	public BackPanel(URL url,int width,int height) {
		Image back = new ImageIcon(url).getImage();
	    BufferedImage bimage = new BufferedImage(back.getWidth(null), back.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(back, 0, 0, null);
	    bGr.dispose();
	    
		BufferedImage resized = new BufferedImage(width, height, bimage.getType());
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bimage, 0, 0, width, height, 0, 0, bimage.getWidth(),
		    bimage.getHeight(), null);
		g.dispose();
		this.setBack(resized);
		
	}
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(getBack(), 0, 0, null);
	}
	/**
	 * @return the back
	 */
	public Image getBack() {
		return back;
	}
	/**
	 * @param back the back to set
	 */
	public void setBack(Image back) {
		this.back = back;
	}

}
