/**
 * 
 */
package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import mc.Minecraft;

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
	private static Graphics voxG;
	/**
	 * @return
	 */
	public static JPanel getMenu() {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		voxG = Minecraft.vx.getGraphics();
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
		tlc.setOpaque(false);
		tlc.setSize(cw, ch);
		tlc.add(btnResume);
		
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
	protected static void resume() {
	
		
	}




	/**
	 * @param e
	 */
	protected static void resized(ComponentEvent e) {
		double offset = .2;
		int cw = Minecraft.cf.getWidth();
		int ch = Minecraft.cf.getHeight();
		System.out.println("Resized");
		btnResume.setBounds(center(cw,ch,offset,7,0,20));
		Minecraft.loadWorld(Minecraft.vx);
		
	}

}
