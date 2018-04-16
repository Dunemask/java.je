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
import minerender.FileControl;
import minerender.VoxEn;
import mplayer.SoundEngine;

/**
 * @author dunemask
 *
 */
public class QuickMenu extends JPanel{

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
	
	
	private static JButton btnResume;
	private static JButton btnQuick;
	private static JButton btnExit;
	private static JLabel back= new JLabel();
//	private static Graphics voxG;
	private static BufferedImage awtImage;
	
	
	public void reset() {
		awtImage = null;
		
	}
	public void redraw(VoxelCt vx) {
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		if(awtImage==null) {
		awtImage = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
		VoxelCt tvox = vx;
		tvox.paintAll(awtImage.getGraphics());
		}
	}
	
	/**
	 * @param vx 
	 * @return
	 */
	public QuickMenu(VoxelCt vx) {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		if(awtImage==null) {
		awtImage = new BufferedImage(cw, ch, BufferedImage.TYPE_INT_RGB);
		VoxelCt tvox = vx;
		tvox.paintAll(awtImage.getGraphics());
		}
		btnResume = new JButton("Resume");
		btnResume.setBackground(Color.DARK_GRAY);
		btnResume.setForeground(Color.WHITE);
		this.setLayout(null);
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
		btnQuick.setBounds(center(cw,ch,offset,7,1,40));
		btnQuick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				quick();
				
			}
			
		});
		
		btnExit = new JButton("Save and Exit");
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(center(cw,ch,offset,7,2,60));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
				
			}
			
		});
		
		
	
		
		this.setOpaque(false);
		this.setSize(cw, ch);
		this.add(btnResume);
		this.add(btnQuick);
		this.add(btnExit);
		back.setBounds(0, 0, cw, ch);
		ImageIcon imgico = new ImageIcon(awtImage);
				Image img = imgico.getImage();
		Image dimg = img.getScaledInstance(back.getWidth(), back.getHeight(),
		        Image.SCALE_SMOOTH);
		imgico = new ImageIcon(dimg);
		back.setIcon(imgico);
		this.add(back);
		
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resized(e);
            }
        });

	}




	/**
	 * 
	 */
	protected static void quick() {
		SoundEngine.handle("click");
		Minecraft.quickSettings();
		
	}



	protected static void exit() {
		SoundEngine.handle("click");
		FileControl.SaveFileAsXML(Minecraft.vx.getVen(), Minecraft.vx.getVen().getName());
		Minecraft.goToSelect();
	}
	/**
	 * 
	 */
	protected static void resume() {
		SoundEngine.handle("click");
		VoxEn ven = Minecraft.vx.getVen();
		VoxelCt vct = new VoxelCt(ven,Minecraft.vx.mode);
		Minecraft.loadWorld(vct);
		
	}




	/**
	 * @param e
	 */
	protected  void resized(ComponentEvent e) {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		this.setSize(cw, ch);
		System.out.println("Resized");
		btnResume.setBounds(center(cw,ch,offset,7,0,20));
		btnQuick.setBounds(center(cw,ch,offset,7,1,40));
		btnExit.setBounds(center(cw,ch,offset,7,2,60));
		back.setBounds(0, 0, cw, ch);
		ImageIcon imgico = new ImageIcon(awtImage);
				Image img = imgico.getImage();
		Image dimg = img.getScaledInstance(cw, ch,
		        Image.SCALE_SMOOTH);
		imgico = new ImageIcon(dimg);
		back.setIcon(imgico);
	
		
	}

}
