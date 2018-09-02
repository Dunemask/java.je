package minerender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mc.Minecraft;
import mplayer.SoundEngine;
/**
 * This Is in charge of the panel that manages the world files.
 * @author Roberts
 *
 */
public class FilePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4812485565177991885L;
	DefaultListModel<String> lm = new DefaultListModel<String>();
	JList<String> list = new JList<String>(lm);
	VoxEn ven;
	JFrame frm;
	JToggleButton creative;
	Border raised = BorderFactory.createRaisedSoftBevelBorder();
	Border lowered = BorderFactory.createLoweredBevelBorder();
	public Border cool= BorderFactory.createCompoundBorder(raised,lowered);
	public FilePanel() {
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBorder(cool);
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		this.setBorder(cool);
		//this.setLocation(10, 10);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel lb = new JLabel("WORLD SELECTION");
		lb.setForeground(Color.WHITE);
		lb.setAlignmentX(CENTER_ALIGNMENT);
		lb.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		this.add(lb);
		this.add(scroll,BorderLayout.CENTER);
		
		
		
		list.setCellRenderer(new ListCellRenderer<Object>() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean hasFocus) {
				JPanel jp = new JPanel();
				jp.setBackground(Color.GRAY);
				JLabel jl = new JLabel(value.toString());
				jl.setForeground(Color.WHITE);
				jl.setFont(new Font("Century Gothic", Font.PLAIN, 15));
				jp.setBorder(cool);
				if(hasFocus) {
					jl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
				}
				if(isSelected) {
					jl.setFont(new Font("Century Gothic", Font.PLAIN, 18));
				}
				jp.add(jl);
				jp.repaint();
				jp.revalidate();
				return jp;
			}
			
		});
		
		//Delete Button
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setForeground(Color.BLACK);
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		deleteBtn.setAlignmentX(0.5f);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!list.isSelectionEmpty()) {
					delAction();
				}
				
			}
			
		});
		//Play
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnPlay = new JButton("PLAY");
		btnPlay.setForeground(Color.BLACK);
		btnPlay.setBackground(Color.LIGHT_GRAY);
		btnPlay.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnPlay.setAlignmentX(CENTER_ALIGNMENT);
		//btnPlay.setForeground(Color.BLACK);
		//btnPlay.setBackground(Color.GRAY);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SoundEngine.handle("click");
				start(list.getSelectedIndex());
			}
		});
		panel.add(btnPlay);
		panel.add(deleteBtn);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnPlay.setText(list.getSelectedValue()+"--PLAY");
				deleteBtn.setText(list.getSelectedValue()+"--Delete");
				
			}
			
		});
		JButton btnAdd =new JButton("New");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(Color.LIGHT_GRAY);
		

		
		btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		btnAdd.setAlignmentX(CENTER_ALIGNMENT);
		JPanel create = new JPanel();
		create.setVisible(false);
		create.setBackground(Color.LIGHT_GRAY);
		//create.setLayout();
		JTextField jtf = new JTextField("____Untitled____");
		jtf.setSize(100, 30);
		//jtf.setVisible(false);
		JLabel l1 = new JLabel("Size");
		JTextField jx = new JTextField("120");
		JTextField jy = new JTextField("120");
		JTextField jz = new JTextField("120");
		//JLabel l3 = new JLabel("Like");
		
				JButton back = new JButton("Back");
				back.setForeground(Color.BLACK);
				back.setBackground(Color.LIGHT_GRAY);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						SoundEngine.handle("click");
						Minecraft.goToMain();
					}
				});
				
				back.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				back.setAlignmentX(0.5f);
				panel.add(back);
		create.add(jtf);
		create.add(l1);
		create.add(jx);
		create.add(jy);
		create.add(jz);
		add(create);
		
		JToggleButton tglbtnSuperflat = new JToggleButton("Superflat");
		tglbtnSuperflat.setForeground(Color.BLACK);
		tglbtnSuperflat.setBackground(Color.LIGHT_GRAY);
		create.add(tglbtnSuperflat);
		
		creative = new JToggleButton("Creative");
		creative.setForeground(Color.BLACK);
		creative.setBackground(Color.LIGHT_GRAY);
		creative.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(creative);
		panel.add(btnAdd);

		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SoundEngine.handle("click");
				if(btnAdd.getText()=="New") {
					jtf.setText("____Untitled____");
				create.setVisible(true);
				btnAdd.setText("Create New World2");
				//System.out.println("JSDF");
				repaint();
				revalidate();
				jtf.setText("Untitled");
				}
				if(btnAdd.getText()=="Create New World") {
					create.setVisible(false);
					boolean flat = tglbtnSuperflat.isSelected();
					int i;
					if(flat) {
						i=2;
					}else {
						i=1;
					}
					VoxEn vex = new VoxEn(new Vector3(Integer.parseInt(jx.getText())/2,Integer.parseInt(jy.getText())/2,Integer.parseInt(jz.getText())),Integer.parseInt(jx.getText()),Integer.parseInt(jy.getText()),Integer.parseInt(jz.getText()),i,jtf.getText());
					FileControl.SaveFileAsXML(vex, jtf.getText());
					
					
					
					//jtf.setText("Untitled");
					btnAdd.setText("New");
					refreshlm();
					ListModel<String> mod = list.getModel();
					int wanted = -1;
					for(int c=0;c<mod.getSize();c++) {
						if(mod.getElementAt(c).toString().equals(jtf.getText())) {
							wanted=c;
						}
					}
					if(wanted==-1) {
						System.out.println("Fail");
					}else {
						list.setSelectedIndex(wanted);
					}
					
					//System.out.println("JSDF");
					repaint();
					revalidate();
					}
				if(btnAdd.getText()=="Create New World2") {
					btnAdd.setText("Create New World");
					btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 15));
				}
			}
			
		});
		
		File files = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/");
		if(!files.exists()) {
			new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves").mkdirs();
			VoxEn vex = new VoxEn(new Vector3(60,60,100),120,120,70,1,"Firstworld");
			FileControl.SaveFileAsXML(vex, "Firstworld");
			ven = vex;
		}
		
		File[] fs = (files.listFiles());

		for(int x=0;x<fs.length;x++) {
			lm.addElement(fs[x].getName().replaceAll(".xml", ""));
		}
		
	}
	/**
	 * 
	 */
	protected void delAction() {
		String s = list.getSelectedValue().toString();
		File file = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves/"+s+".xml");
		file.delete();
		refreshlm();
		this.repaint();
		this.revalidate();
		
	}
	

	public void start(int world){
		if(!list.isSelectionEmpty()) {
			//System.out.println("World:"+list.getSelectedValue().toString());
			
		ven = FileControl.LoadFileXML(list.getSelectedValue().toString());	
		
		this.setVisible(false);
		int cret;
		if(creative.isSelected()) {cret =1;}else {cret=0;}
		VoxelCt vct = new VoxelCt(ven,cret,false);//Bolean for resume
		Minecraft.loadWorld(vct,false);
		//this.getParent());
		//this.getParent().removeAll();
		this.getParent().remove(this);
		
		}
	}
	public void refreshlm() {
		lm.removeAllElements();
		File files = new File(System.getProperty("user.home")+"/Documents/VoxelCraft/Saves");
		File[] fs = (files.listFiles());

		for(int x=0;x<fs.length;x++) {
			lm.addElement(fs[x].getName().replaceAll(".xml", ""));
		}
	}
}
