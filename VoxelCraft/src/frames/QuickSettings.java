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
public class QuickSettings extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7743473342320749215L;
	private JSlider volSlider;
	private JSlider renslider;
	private static JLabel renLabel;
	private static JLabel volLabel;
	private static JButton back=new JButton("Back");
	private static final double volSliderPerc=.15;
	private static int renderMax = 300;

	/**
	 * 
	 */
	public QuickSettings() {
		setBackground(Color.DARK_GRAY);
		this.setSize(600,600);
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	resized(e);
            }
        });

		setLayout(null);
		
		volLabel = new JLabel("Volume "+(int)(PlaySound.vol*100));
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
				volLabel.setText("Volume "+(int)(PlaySound.vol*100));
				repRev();
				
			}});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quickMenu();
			}
		});
		back.setForeground(Color.WHITE);
		back.setBackground(Color.BLACK);
		int pbch = this.getHeight()/10;
		
		back.setBounds(volSlider.getX(), this.getHeight()-pbch, volSlider.getWidth(),pbch);
		add(back);
		
		
		add(volLabel);
		add(volSlider);
    	int lby = pbch;
    	
        int vsx = (int) (this.getWidth()*QuickSettings.volSliderPerc);
    	int vsy = (int) lby+pbch;
    	int vsw = (int) (this.getWidth()-(2*this.getWidth()*QuickSettings.volSliderPerc));
    	int vsh = pbch;
    	volSlider.setBounds(vsx,vsy,vsw,vsh);
    	int lbx=(int) (vsx+(vsw/2)-(volLabel.getPreferredSize().getWidth()/2));
    	volLabel.setBounds(lbx, lby,(int) volLabel.getPreferredSize().getWidth(),(int)volLabel.getPreferredSize().getHeight());
		renLabel = new JLabel("Render "+Minecraft.renderVal);
		renLabel.setForeground(Color.WHITE);
		renLabel.setBackground(Color.DARK_GRAY);
		renLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		//renLabel.setBounds(volLabel.getX(),volLabel.getY()+2*pbch,volLabel.getWidth(), volLabel.getHeight());
    	renLabel.setBounds(lbx, lby+2*pbch,(int) renLabel.getPreferredSize().getWidth(),(int)renLabel.getPreferredSize().getHeight());
		renslider = new JSlider();
		renslider.setMaximum(renderMax);
		renslider.setMinimum(100);
		renslider.setValue(Minecraft.renderVal);
		renslider.setForeground(Color.DARK_GRAY);
		renslider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		renslider.setBounds(volSlider.getX(), volSlider.getY()+2*pbch, volSlider.getWidth(), volSlider.getHeight());
		renslider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				Minecraft.renderVal= renslider.getValue();
				renLabel.setText("Render "+Minecraft.renderVal);
				renSliderChanged();
			}});
		add(renslider);
		this.add(renLabel);
		
	}

	
	/**
	 * 
	 */
	protected void repRev() {
		this.repaint();
		this.revalidate();
		
	}


	/**
	 * 
	 */
	protected void renSliderChanged() {
		this.repRev();
		
	}


	/**
	 * 
	 */
	protected void quickMenu() {
		SoundEngine.handle("click");
		Minecraft.quickMenu();

		
		
		
	}





	/**
	 * @param e
	 */
	protected void resized(ComponentEvent e) {
		//Goes through and changes each component to fit the parent somewhat
				try {
			        // This is only called when the user releases the mouse button.
			    	//double ofset = c.getWidth()*this.pbXOffSetPercent;
			    	int pbch = this.getHeight()/10;
			    	QuickSettings.volLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
			    	//int objsize;
			    	//exit.setBounds((int)ofset,  c.getHeight()-btchange-pbch, (int) (this.getWidth()-2*ofset),pbch);
			    	//objsize = this.getHeight()/6;
			    	int lby = pbch;
			    	
			        int vsx = (int) (this.getWidth()*QuickSettings.volSliderPerc);
			    	int vsy = (int) lby+pbch;
			    	int vsw = (int) (this.getWidth()-(2*this.getWidth()*QuickSettings.volSliderPerc));
			    	int vsh = pbch;
			    	volSlider.setBounds(vsx,vsy,vsw,vsh);
			    	int lbx=(int) (vsx+(vsw/2)-(volLabel.getPreferredSize().getWidth()/2));
			    	volLabel.setBounds(lbx, lby,(int) volLabel.getPreferredSize().getWidth(),(int)volLabel.getPreferredSize().getHeight());
			    	back.setBounds(vsx, this.getHeight()-pbch, vsw,pbch);
					renslider.setBounds(volSlider.getX(),volSlider.getY()+2*pbch , volSlider.getWidth(), volSlider.getHeight());
					renLabel.setBounds(lbx, lby+2*pbch,(int) renLabel.getPreferredSize().getWidth(),(int)renLabel.getPreferredSize().getHeight());
			    	this.repaint();
			        this.revalidate();
				}catch(Exception exc) {
					
				}
		
	}


	/**
	 * @return the volSlider
	 */
	public JSlider getVolSlider() {
		return volSlider;
	}


	/**
	 * @param volSlider the volSlider to set
	 */
	public void setVolSlider(JSlider volSlider) {
		this.volSlider = volSlider;
	}


	/**
	 * @return the renslider
	 */
	public JSlider getRenslider() {
		return renslider;
	}


	/**
	 * @param renslider the renslider to set
	 */
	public void setRenslider(JSlider renslider) {
		this.renslider = renslider;
	}
}
