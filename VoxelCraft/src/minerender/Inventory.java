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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * Control of the inventory that has all of the blocks...
 * @author Roberts
 *
 */
public class Inventory extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VoxEn ven;
	Border raised = BorderFactory.createRaisedSoftBevelBorder();
	Border lowered = BorderFactory.createLoweredBevelBorder();
	public Border cool= BorderFactory.createCompoundBorder(raised,lowered);
	public DefaultListModel<String> lm = new DefaultListModel<String>();
	public DefaultListModel<String> hb = new DefaultListModel<String>();
	private final JList<String> list = new JList<String>(lm);
	private final JList<String> hotbar = new JList<String>(hb);
	
	/**
	 * Uses JLists and JScrollPanes to get the job done...
	 */
	public Inventory(VoxEn v) {
		ven = v;	
			this.setVisible(true);
			this.setBorder(cool);
			//this.addBoxes(10);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JScrollPane scroll = new JScrollPane(list);
			//scroll.setAlignmentX(CENTER_ALIGNMENT);
		this.add(scroll, BorderLayout.CENTER);
		this.add(hotbar,BorderLayout.CENTER);
		hotbar.setSelectedIndex(0);
		//	this.add(list);
		list.setPreferredSize(new Dimension(100,1000));
		//list.setSize(100,1000);	
		list.setVisibleRowCount(-1);
		//JButton jb = new JButton("Save");
		//jb.addActionListener("Saves");
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ven.hotbar[hotbar.getSelectedIndex()] = list.getSelectedIndex()+1;
			}
			
		});
		ListCellRenderer<Object> lcr = 		new ListCellRenderer<Object>() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
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
					ImageIcon i = new ImageIcon(FileControl.blockHandle(img));
					JLabel imgicon = new JLabel(i);
					imgicon.setAlignmentX(CENTER_ALIGNMENT);
					jp.add(imgicon);
					jp.add(jl);
					return jp;
				}
				
			};
			ListCellRenderer<Object> hbr = 		new ListCellRenderer<Object>() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
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
					ImageIcon i = new ImageIcon(FileControl.blockHandle(img));
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
			//Creates the lists
			for(int x=0;x<ven.blks.size();x++) {
				lm.addElement(""+x);
			}
			for(int x=0;x<9;x++) {
				hb.addElement(""+x);
			}
	}
	public Inventory(VoxEn v, int d) {
		ven = v;	
			this.setVisible(true);
			this.setBorder(cool);
			//this.addBoxes(10);
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JScrollPane scroll = new JScrollPane(list);
			//scroll.setAlignmentX(CENTER_ALIGNMENT);
		this.add(scroll, BorderLayout.CENTER);
		this.add(hotbar,BorderLayout.CENTER);
		//	this.add(list);
		hotbar.setSelectedIndex(0);
		list.setPreferredSize(new Dimension(100,1000));
		//list.setSize(100,1000);	
		list.setVisibleRowCount(-1);
		//JButton jb = new JButton("Save");
		//jb.addActionListener("Saves");
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ven.hotbar[hotbar.getSelectedIndex()] = list.getSelectedIndex()+1;
			}
			
		});
		ListCellRenderer<Object> lcr = 		new ListCellRenderer<Object>() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					JPanel jp = new JPanel();
					jp.setSize(60,60);
					jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
					jp.setBorder(cool);
					JLabel jl = new JLabel(ven.getBlock(index).name+": "+ven.inventory[index]) ;
					jl.setSize(50,10);
					jl.setAlignmentX(Component.CENTER_ALIGNMENT);
					jl.setFont(new Font("Serif", Font.PLAIN, 12));
					int img = ven.getBlock(index).image[2];
					ImageIcon i = new ImageIcon(FileControl.blockHandle(img));
					JLabel imgicon = new JLabel(i);
					imgicon.setAlignmentX(CENTER_ALIGNMENT);
					jp.add(imgicon);
					jp.add(jl);
					return jp;
				}
				
			};
			ListCellRenderer<Object> hbr = 		new ListCellRenderer<Object>() {

				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
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
					ImageIcon i = new ImageIcon(FileControl.blockHandle(img));
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
			//Creates the lists
			for(int x=0;x<ven.blks.size();x++) {
				lm.addElement(""+x);
			}
			for(int x=0;x<9;x++) {
				hb.addElement(""+x);
			}
	}
}
