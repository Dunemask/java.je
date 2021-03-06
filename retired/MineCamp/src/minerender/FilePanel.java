package minerender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import minemain.VoxelCt;

public class FilePanel extends JPanel {
	DefaultListModel lm = new DefaultListModel();
	JList list = new JList(lm);
	VoxEn ven;
	JFrame frm;
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
		
		
		
		list.setCellRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
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
		JButton btnPlay = new JButton("PLAY");
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnPlay.setText(list.getSelectedValue()+"--PLAY");
				
			}
			
		});
		btnPlay.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnPlay.setAlignmentX(CENTER_ALIGNMENT);
		JButton btnAdd =new JButton("New");
		btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnAdd.setAlignmentX(CENTER_ALIGNMENT);
		//btnPlay.setForeground(Color.BLACK);
		//btnPlay.setBackground(Color.GRAY);
		add(btnPlay);
		add(btnAdd);
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
		JLabel l3 = new JLabel("Like");
		create.add(jtf);
		create.add(l1);
		create.add(jx);
		create.add(jy);
		create.add(jz);
		add(create);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				start(list.getSelectedIndex());
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
					VoxEn vex = new VoxEn(new Vector3(60,60,70),120,120,70,1);
					FileControl.SaveFileAsXML(vex, jtf.getText());
					
					
					
					//jtf.setText("Untitled");
					btnAdd.setText("New");
					refreshlm();
					//System.out.println("JSDF");
					repaint();
					revalidate();
					}
				if(btnAdd.getText()=="Create New World2") {
					btnAdd.setText("Create New World");
					}
			}
			
		});
		
		File files = new File(System.getProperty("user.home")+"/Documents/Saves");
		File[] fs = (files.listFiles());

		for(int x=0;x<fs.length;x++) {
			lm.addElement(fs[x].getName().replaceAll(".xml", ""));
		}
		
	}
	public void start(int world){
		this.setVisible(false);
		VoxelCt vct = new VoxelCt();
		//this.getParent());
		//this.getParent().removeAll();
		this.getParent().remove(this);
	}
	public void refreshlm() {
		lm.removeAllElements();
		File files = new File(System.getProperty("user.home")+"/Documents/Saves");
		File[] fs = (files.listFiles());

		for(int x=0;x<fs.length;x++) {
			lm.addElement(fs[x].getName().replaceAll(".xml", ""));
		}
	}
}
