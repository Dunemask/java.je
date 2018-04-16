/**
 * 
 */
package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mc.Minecraft;
import minemain.VoxelCt;
import minerender.VoxEn;
import mplayer.SoundEngine;

/**
 * @author dunemask
 *
 */
public class QuickMenuHandler {

	public static Rectangle center(int cw,int ch,double xOffSetlr,int totalComp,int compNumber,int verticalCompOffset) {
		int compch = ch/totalComp;
		double offset = xOffSetlr;
		int rx = (int) (cw*offset);
    	int ry = (int) compch*compNumber;
    	ry+=verticalCompOffset;
    	int rw = (int) (cw-(2*cw*offset));
    	int rh = compch;
    	Rectangle r = new Rectangle(rx,ry,rw,rh);
    	return r;
	}
	
	
	private static JPanel tlc;
	private static JButton btnResume;
	private static JButton btnQuick;
	private static JLabel back= new JLabel();
//	private static Graphics voxG;
	private static BufferedImage awtImage;
	/**
	 * @return
	 */
	public static JPanel getMenu() {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		if(awtImage==null) {
		awtImage = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
		VoxelCt tvox = Minecraft.vx;
		tvox.paintAll(awtImage.getGraphics());
		}
		btnResume = new JButton("Resume");
		btnResume.setBackground(Color.DARK_GRAY);
		btnResume.setForeground(Color.WHITE);
		tlc = new JPanel(null);
		/*int rx = (int) (Minecraft.cf.getWidth()*offset);
		int rw = (int) (Minecraft.cf.getWidth()-2*offset*Minecraft.cf.getWidth());
    	int vsy = (int) compch;
    	int vsw = (int) (Minecraft.cf.getWidth()-(2*Minecraft.cf.getWidth()*offset));
    	int vsh = compch;*/
    	//btnResume.setBounds(rx,vsy,vsw,vsh);
		btnResume.setBounds(center(cw,ch,offset,7,0,20));
		btnResume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resume();
				
			}
			
		});
		btnQuick = new JButton("Settings");
		btnQuick.setBackground(Color.DARK_GRAY);
		btnQuick.setForeground(Color.WHITE);
		tlc = new JPanel(null);
		/*int rx = (int) (Minecraft.cf.getWidth()*offset);
		int rw = (int) (Minecraft.cf.getWidth()-2*offset*Minecraft.cf.getWidth());
    	int vsy = (int) compch;
    	int vsw = (int) (Minecraft.cf.getWidth()-(2*Minecraft.cf.getWidth()*offset));
    	int vsh = compch;*/
    	//btnQuick.setBounds(rx,vsy,vsw,vsh);
		btnQuick.setBounds(center(cw,ch,offset,7,1,40));
		btnQuick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				quick();
				
			}
			
		});
		
	
		
		tlc.setOpaque(false);
		tlc.setSize(cw, ch);
		tlc.add(btnResume);
		tlc.add(btnQuick);
		back.setBounds(0, 0, cw, ch);
		ImageIcon imgico = new ImageIcon(awtImage);
				Image img = imgico.getImage();
		Image dimg = img.getScaledInstance(back.getWidth(), back.getHeight(),
		        Image.SCALE_SMOOTH);
		imgico = new ImageIcon(dimg);
		back.setIcon(imgico);
		tlc.add(back);
		
		tlc.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resized(e);
            }
        });
		return tlc;
	}




	/**
	 * 
	 */
	protected static void quick() {
		SoundEngine.handle("click");
		Minecraft.quickSettings();
		
	}




	/**
	 * 
	 */
	protected static void resume() {
		VoxEn ven = Minecraft.vx.getVen();
		VoxelCt vct = new VoxelCt(ven,Minecraft.vx.mode);
		Minecraft.loadWorld(vct);
		
	}




	/**
	 * @param e
	 */
	protected static void resized(ComponentEvent e) {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		tlc.setSize(cw, ch);
		System.out.println("Resized");
		btnResume.setBounds(center(cw,ch,offset,7,0,20));
		btnQuick.setBounds(center(cw,ch,offset,7,1,40));
		back.setBounds(0, 0, cw, ch);
		ImageIcon imgico = new ImageIcon(awtImage);
				Image img = imgico.getImage();
		Image dimg = img.getScaledInstance(cw, ch,
		        Image.SCALE_SMOOTH);
		imgico = new ImageIcon(dimg);
		back.setIcon(imgico);
	
		
	}

}
