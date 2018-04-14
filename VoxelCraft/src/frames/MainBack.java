/**
 * 
 */
package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dunemask.util.FileUtil;
import mc.Minecraft;
import mplayer.SoundEngine;

/**
 * @author Dunemask
 *
 */
public class MainBack extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 389046959966617546L;
	private JButton play;
	double pbXOffSetPercent = .15; 
	double pbYTopOffSetPercent = .50;
	private JButton btnMenu;
	private JButton exit;
	private JLabel lblMc;
	private Image back;
	/**
	 * 
	 */
	public MainBack() {
		this.setSize(600, 600);
		this.setBack(this.makeback(FileUtil.getResourceURL("resources/mainmenu/panorama_3.png"),this.getWidth(),this.getHeight()));
		this.setLayout(null);
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resized(e);
            }
        });
		play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				playButtonClicked();
			}
		});
		play.setBackground(Color.LIGHT_GRAY);
		play.setForeground(Color.DARK_GRAY);
		play.setFont(new Font("Tahoma", Font.PLAIN, 18));
		play.setBounds(65, 239, 450, 75);
		
		//this.add(bp);
		this.add(play);
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuClicked();
			}
		});
		btnMenu.setForeground(Color.DARK_GRAY);
		btnMenu.setFont(play.getFont());
		btnMenu.setBackground(Color.LIGHT_GRAY);
		btnMenu.setBounds(65, 327, 450, 76);
		this.add(btnMenu);
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
			
		});
		exit.setForeground(Color.DARK_GRAY);
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setBounds(65, 416, 450, 86);
		this.add(exit);
		
		lblMc = new JLabel("mc");
		lblMc.setBounds(84, 35, 431, 115);
		this.add(lblMc);
		

		exit.setFont(play.getFont());
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setBounds(65, 416, 450, 86);
		this.add(exit);
		lblMc = new JLabel("mc");
		lblMc.setBounds(84, 35, 431, 115);
		
		ImageIcon imgico = new ImageIcon(FileUtil.getResourceURL("resources/mainmenu/mc.png"));
		Image img = imgico.getImage();
		Image dimg = img.getScaledInstance(lblMc.getWidth(), lblMc.getHeight(),
		        Image.SCALE_SMOOTH);
		
		lblMc.setIcon(new ImageIcon(dimg));
		this.add(lblMc);

	}
	/**
	 * 
	 */
	protected void menuClicked() {
		SoundEngine.handle("click");
		Minecraft.goToSettings();
		
	}
	/**
	 * 
	 */
	protected void playButtonClicked() {
		SoundEngine.handle("click");
		Minecraft.goToSelect();
		
	}
	/**
	 * @param e
	 */
	protected void resized(ComponentEvent e) {
		//Goes through and changes each component to fit the parent somewhat
		//try {
	    	Component c = (Component)e.getSource();
	        // This is only called when the user releases the mouse button.
	    	//BackPanel tmp = new BackPanel(FileUtil.getResourceURL("resources/mainmenu/panorama_3.png"),c.getWidth(),c.getHeight());
	    	this.setBack(this.makeback(FileUtil.getResourceURL("resources/mainmenu/panorama_3.png"),this.getWidth(),this.getHeight()));
	    	this.repaint();
	    	this.revalidate();
	    	int btchange =this.getHeight()/30;
	    	double ofset = c.getWidth()*this.pbXOffSetPercent;
	    	int pbch = this.getHeight()/7;
	    	play.setFont(new Font("Tahoma", Font.PLAIN, 40));
	    	exit.setFont(play.getFont());
	    	btnMenu.setFont(play.getFont());
	    	exit.setBounds((int)ofset,  c.getHeight()-btchange-2*pbch, (int) (this.getWidth()-2*ofset),pbch);
	    	btnMenu.setBounds((int)ofset,  exit.getY()-btchange-pbch, (int) (this.getWidth()-2*ofset),pbch);
	    	play.setBounds((int)ofset, btnMenu.getY()-btchange-pbch, (int) (this.getWidth()-2*ofset),pbch);
	    	double mcoffset = c.getWidth()*this.pbXOffSetPercent-.05;
	    	int mcx=(int) mcoffset;
	    	int mcy=play.getY();
	    	int mcw= (int) (this.getWidth()-2*mcoffset);
	    	int mch = this.getHeight()/4;
			lblMc.setBounds(mcx,mcy-btchange-mch,mcw,mch);
	    	ImageIcon imgico = new ImageIcon(FileUtil.getResourceURL("resources/mainmenu/mc.png"));
			Image img = imgico.getImage();
			Image dimg = img.getScaledInstance(lblMc.getWidth(), lblMc.getHeight(),
			        Image.SCALE_SMOOTH);
			
			lblMc.setIcon(new ImageIcon(dimg));
	        this.repaint();
	        this.revalidate();
		//}catch(Exception exc) {
			
		//}
		
	}
	
	private BufferedImage makeback(URL url,int width,int height) {
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
		return resized;
		
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
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(getBack(), 0, 0, null);
	}
}
