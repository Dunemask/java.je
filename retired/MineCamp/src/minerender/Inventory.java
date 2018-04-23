package minerender;

import java.awt.BorderLayout;
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

public class Inventory extends JPanel{
	VoxEn ven;
	Border raised = BorderFactory.createRaisedSoftBevelBorder();
	Border lowered = BorderFactory.createLoweredBevelBorder();
	public Border cool= BorderFactory.createCompoundBorder(raised,lowered);
	public DefaultListModel lm = new DefaultListModel();
	private final JList list = new JList(lm);
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
		//	this.add(list);
		list.setPreferredSize(new Dimension(100,1000));
		//list.setSize(100,1000);	
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ven.selected = list.getSelectedIndex();
			}
			
		});
		list.setCellRenderer(new ListCellRenderer() {

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
					ImageIcon i = new ImageIcon("src/resources/blocks/img"+img+".png");
					JLabel imgicon = new JLabel(i);
					imgicon.setAlignmentX(CENTER_ALIGNMENT);
					jp.add(imgicon);
					jp.add(jl);
					return jp;
				}
				
			});
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			scroll.setSize(100,100);

			for(x=0;x<5;x++) {
				lm.addElement(""+x);
			}
	}
	public void addBoxes(int boxes) {
		
	}

}
