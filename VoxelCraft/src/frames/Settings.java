/**
 * 
 */
package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dunemask.objects.DMediaPlayer;
import dunemask.util.FileUtil;
import dunemask.util.xml.RuneMap;
import mc.Minecraft;
import mc.ResourceHandler;
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
	private JSlider volSlider;
	private JSlider renslider;
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


	private static JLabel renLabel;
	private static JLabel volLabel;
	private static JButton back=new JButton("Back");
	private static JButton loadPack = new JButton("Load Resource");
	private static final double volSliderPerc=.15;
	private static int renderMax = 300;

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
				mainMenu();
			}
		});
		back.setForeground(Color.WHITE);
		back.setBackground(Color.BLACK);
		int pbch = this.getHeight()/10;
		
		back.setBounds(volSlider.getX(), this.getHeight()-pbch, volSlider.getWidth(),pbch);
		add(back);
		
		loadPack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadResourcePack();
				
			}
			
		});
		loadPack.setForeground(Color.white);
		loadPack.setBackground(Color.BLACK);
		loadPack.setBounds(volSlider.getX(), this.getHeight()-2*pbch, volSlider.getWidth(),pbch);
		add(loadPack);
		
		add(volLabel);
		add(volSlider);
    	int lby = pbch;
    	
        int vsx = (int) (this.getWidth()*Settings.volSliderPerc);
    	int vsy = (int) lby+pbch;
    	int vsw = (int) (this.getWidth()-(2*this.getWidth()*Settings.volSliderPerc));
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
	protected void loadResourcePack() {
		JSystemFileChooser jfc = new JSystemFileChooser();
		jfc.setCurrentDirectory(new File(System.getProperty("user.home")+"/Documents/VoxelCraft/"));
		jfc.setDialogTitle("Select a Resource Pack Folder");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setMultiSelectionEnabled(false);
		jfc.showOpenDialog(this);
		File top = jfc.getSelectedFile();
		RuneMap info = RuneMap.ParseDXMLMap(new File(top.getAbsolutePath()+"\\info.xml"));
		String textures = FileUtil.filePathFix(top.getAbsolutePath())+"/"+info.pullValue("info/trelpath");
		ResourceHandler.sounds = new File(top.getAbsolutePath()+"\\sounds.xml");
		ResourceHandler.soundmap = RuneMap.ParseDXMLMap(ResourceHandler.sounds);
		ResourceHandler.blox = new File(top.getAbsolutePath()+"\\blocks.xml");
		ResourceHandler.blockmap = RuneMap.ParseDXMLMap(ResourceHandler.sounds);
		ResourceHandler.handlemap.writeElement("Handler/sounds", ResourceHandler.sounds);
		ResourceHandler.handlemap.writeElement("Handler/blox", ResourceHandler.blox);
		ResourceHandler.init();
		//System.out.println(ResourceHandler.blockmap.getXml().getAbsolutePath());
		//System.out.println(textures);
		String sounds =FileUtil.filePathFix(top.getAbsolutePath())+"/"+info.pullValue("info/srelpath");
		ResourceHandler.blockmap.removeElement("blocks/texturepath");
		ResourceHandler.blockmap.writeForcedElement("blocks/texturepath", textures+"/blocks/");
		ResourceHandler.soundmap.removeElement("Sounds/relpath");
		ResourceHandler.soundmap.writeForcedElement("Sounds/relpath",  sounds+"/");
		SoundEngine.stop(SoundEngine.allEngines);
		SoundEngine.start(SoundEngine.title);
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
	protected void mainMenu() {
		SoundEngine.handle("click");
		Minecraft.goToMain();

		
		
		
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
			    	loadPack.setBounds(vsx,this.getHeight()-2*pbch, vsw,pbch);
					renslider.setBounds(volSlider.getX(),volSlider.getY()+2*pbch , volSlider.getWidth(), volSlider.getHeight());
					renLabel.setBounds(lbx, lby+2*pbch,(int) renLabel.getPreferredSize().getWidth(),(int)renLabel.getPreferredSize().getHeight());
			    	this.repaint();
			        this.revalidate();
				}catch(Exception exc) {
					
				}
		
	}
}
