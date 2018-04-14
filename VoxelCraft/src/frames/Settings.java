/**
 * 
 */
package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dunemask.objects.DMediaPlayer;
import dunemask.util.FileUtil;
import mc.Minecraft;
import mplayer.PlaySound;
import mplayer.SoundEngine;

/**
 * @author Dunemask
 *
 */
public class Settings extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7743473342320749215L;
	private static JSlider volSlider;
	private static JLabel volLabel;
	private static JButton back=new JButton("Back");
	private static final double volSliderPerc=.15;

	/**
	 * 
	 */
	public Settings() {
		setBackground(Color.DARK_GRAY);
		this.setSize(600,600);
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resized(e);
            }
        });

		setLayout(null);
		
		volLabel = new JLabel("Volume");
		volLabel.setForeground(Color.WHITE);
		volLabel.setBackground(Color.DARK_GRAY);
		volLabel.setBounds(242, 24, 84, 32);
		volSlider = new JSlider();
		volSlider.setForeground(Color.DARK_GRAY);
		volSlider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		volSlider.setBounds(134, 80, 273, 71);
		volSlider.setMaximum(100);
		volSlider.setValue((int) (PlaySound.vol*100));
		volSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				double rv = (double)volSlider.getValue()/(double)volSlider.getMaximum();
				PlaySound.vol=rv;
				DMediaPlayer.getMediaPlayer().setVolume(PlaySound.vol);
				
			}});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainMenu();
			}
		});
		back.setForeground(Color.WHITE);
		back.setBackground(Color.BLACK);
		int pbch = this.getHeight()/10;
		
		back.setBounds(volSlider.getX(), this.getHeight()-pbch, volSlider.getWidth(),pbch);
		add(back);
		add(volLabel);
		add(volSlider);
		
	}

	
	/**
	 * 
	 */
	protected void mainMenu() {
		SoundEngine.handle("click");
		Minecraft.goToMain();

		
		
		
	}


	/**
	 * @return
	 */
	private String volCalc() {
		double vol = PlaySound.vol;
		vol=vol*100;
		return String.valueOf(vol);
	}


	/**
	 * @param e
	 */
	protected void resized(ComponentEvent e) {
		//Goes through and changes each component to fit the parent somewhat
				try {
			    	Component c = (Component)e.getSource();
			        // This is only called when the user releases the mouse button.
			    	BackPanel tmp = new BackPanel(FileUtil.getResourceURL("resources/mainmenu/panorama_3.png"),c.getWidth(),c.getHeight());
			    	//double ofset = c.getWidth()*this.pbXOffSetPercent;
			    	int pbch = this.getHeight()/10;
			    	Settings.volLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
			    	//int objsize;
			    	//exit.setBounds((int)ofset,  c.getHeight()-btchange-pbch, (int) (this.getWidth()-2*ofset),pbch);
			    	//objsize = this.getHeight()/6;
			    	int lby = pbch;
			    	
			        int vsx = (int) (this.getWidth()*Settings.volSliderPerc);
			    	int vsy = (int) lby+pbch;
			    	int vsw = (int) (this.getWidth()-(2*this.getWidth()*Settings.volSliderPerc));
			    	int vsh = pbch;
			    	volSlider.setBounds(vsx,vsy,vsw,vsh);
			    	int lbx=(int) (vsx+(vsw/2)-(volLabel.getPreferredSize().getWidth()/2));
			    	volLabel.setBounds(lbx, lby,(int) volLabel.getPreferredSize().getWidth(),(int)volLabel.getPreferredSize().getHeight());
			    	back.setBounds(vsx, this.getHeight()-pbch, vsw,pbch);
			    	
			    	this.repaint();
			        this.revalidate();
				}catch(Exception exc) {
					
				}
		
	}


}
