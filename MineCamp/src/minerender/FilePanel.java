package minerender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import minemain.VoxelCt;

public class FilePanel extends JPanel {
	DefaultListModel lm = new DefaultListModel();
	JList list = new JList(lm);
	VoxEn ven;
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
		//btnPlay.setForeground(Color.BLACK);
		//btnPlay.setBackground(Color.GRAY);
		add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				start(list.getSelectedIndex());
			}
		});
		
		
		for(int x=0;x<24;x++) {
			lm.addElement("WORLD "+x);
		}
		
	}
	public void start(int world){
		this.setVisible(false);
		VoxelCt vct = new VoxelCt();
		this.getParent().add(vct.getContentPane());
		this.getParent().remove(this);
	}
	
}
