/**
 * 
 */
package dunemask.objects;


import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** Creates a JLabel with the Scaled instance of the params defined
 * @author karib
 *
 */
public class ScaledImageLabel extends JLabel{
	/***Version*/
    final static double version = 4.5;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2954400563145965441L;
	/**The File that the image is being read from**/
	private File imageFile;
	/**The Absolute File Path to the Image Icon**/
	private String imageFilePath;
	/**The Scaled Instance of the Icon**/
	private Icon scaledImageIcon;
	
	/**
	 * @param imagePath String Path To the Image
	 * @param width Int for the width of the image
	 * @param height Int for the height of Image
	 */
	public ScaledImageLabel(String imagePath,int width,int height) {
		setImageFilePath(imagePath);
		this.setSize(width, height);
		Icon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		this.setIcon(icon);
		this.repaint();
		this.revalidate();

	}
	
	/**
	 * @param image image
	 * @param width Int for the width of the image
	 * @param height Int for the height of Image
	 */
	public ScaledImageLabel(Image image,int width,int height) {
		setImageFilePath(null);
		this.setSize(width, height);
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setIcon(icon);
		this.repaint();
		this.revalidate();

	}
	
	
	/**
	 * @param imagePath String Path To the Image
	 * @param width Int for the width of the image
	 * @param height Int for the height of Image
	 */
	public ScaledImageLabel(String imagePath,double width,double height) {
		setImageFilePath(imagePath);
		this.setSize((int)width, (int)height);
		Icon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT));
		this.setIcon(icon);
		this.repaint();
		this.revalidate();

	}
	
	/**
	 * @param image image
	 * @param width Int for the width of the image
	 * @param height Int for the height of Image
	 */
	public ScaledImageLabel(Image image,double width,double height) {
		
		setImageFilePath(null);
		this.setSize((int)width, (int)height);
		Icon icon = new ImageIcon(image.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH));
		this.setIcon(icon);
		this.repaint();
		this.revalidate();

	}

	/**
	 * @return the imageFile
	 */
	public File getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 * @return the imageFilePath
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}

	/**
	 * @param imageFilePath the imageFilePath to set
	 */
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	/**
	 * @return the scaledImageIcon
	 */
	public Icon getScaledImageIcon() {
		return scaledImageIcon;
	}

	/**
	 * @param scaledImageIcon the scaledImageIcon to set
	 */
	public void setScaledImageIcon(Icon scaledImageIcon) {
		this.scaledImageIcon = scaledImageIcon;
	}


}
