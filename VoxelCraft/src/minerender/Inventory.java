package minerender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dunemask.util.FileUtil;

public class Inventory extends JPanel{
	VoxEn ven;
	Border raised = BorderFactory.createRaisedSoftBevelBorder();
	Border lowered = BorderFactory.createLoweredBevelBorder();
	public Border cool= BorderFactory.createCompoundBorder(raised,lowered);
	public DefaultListModel lm = new DefaultListModel();
	public DefaultListModel hb = new DefaultListModel();
	private final JList list = new JList(lm);
	private final JList hotbar = new JList(hb);
	public Inventory(int x, int y, int sx, int sy, VoxEn v) {
		ven = v;	
		this.setBounds(x,y,sx,sy);
			
			this.setVisible(true);
			this.setBorder(cool);
			//this.addBoxes(10);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JScrollPane scroll = new JScrollPane(list);
			//scroll.setAlignmentX(CENTER_ALIGNMENT);
		this.add(scroll, BorderLayout.CENTER);
		this.add(hotbar,BorderLayout.CENTER);
		//	this.add(list);
		list.setPreferredSize(new Dimension(100,1000));
		//list.setSize(100,1000);	
		list.setVisibleRowCount(-1);
		JButton jb = new JButton("Save");
		//jb.addActionListener("Saves");
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ven.hotbar[hotbar.getSelectedIndex()] = list.getSelectedIndex()+1;
			}
			
		});
		ListCellRenderer lcr = 		new ListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					JPanel jp = new JPanel();
					jp.setSize(60,60);
					jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
					jp.setBorder(cool);
					JLabel jl = new JLabel(ven.getBlock(index).name);
					jl.setSize(50,10);
					jl.setAlignmentX(Component.CENTER_ALIGNMENT);
					jl.setFont(new Font("Serif", Font.PLAIN, 12));
					int img = ven.getBlock(index).image[2];
					ImageIcon i = new ImageIcon(FileUtil.getResourceURL("resources/blocks/img"+img+".png"));
					JLabel imgicon = new JLabel(i);
					imgicon.setAlignmentX(CENTER_ALIGNMENT);
					jp.add(imgicon);
					jp.add(jl);
					return jp;
				}
				
			};
			ListCellRenderer hbr = 		new ListCellRenderer() {

				@Override
				public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					JPanel jp = new JPanel();
					jp.setSize(60,60);
					jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
					jp.setBorder(cool);
					JLabel jl = new JLabel(ven.getBlock(ven.hotbar[index]-1).name);
					jl.setSize(50,10);
					jl.setAlignmentX(Component.CENTER_ALIGNMENT);
					jl.setFont(new Font("Serif", Font.PLAIN, 12));
					if (isSelected) {
						jl.setForeground(Color.BLUE);
					}
					int img = ven.getBlock(ven.hotbar[index]-1).image[2];
					ImageIcon i = new ImageIcon(FileUtil.getResourceURL("resources/blocks/img"+img+".png"));
					JLabel imgicon = new JLabel(i);
					imgicon.setAlignmentX(CENTER_ALIGNMENT);
					jp.add(imgicon);
					jp.add(jl);
					return jp;
				}
				
			};
			list.setCellRenderer(lcr);
			hotbar.setCellRenderer(hbr);
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			hotbar.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			hotbar.setVisibleRowCount(1);
			scroll.setSize(100,100);

			for(x=0;x<ven.blks.size();x++) {
				lm.addElement(""+x);
			}
			for(x=0;x<9;x++) {
				hb.addElement(""+x);
			}
	}
	public void addBoxes(int boxes) {
		
	}

}
