package frames;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import jtunes.JSong;
import jtunes.JTunes;


public class DList extends JList{
	ArrayList<JSong> jSongs;
	ArrayList<String> s;
	
	DefaultListModel mod = new DefaultListModel();
	static ImageIcon noteIcon = new ImageIcon("src/resources/note.png");
	static ImageIcon notegIcon = new ImageIcon("src/resources/note_s.png");
	public DList(ArrayList<JSong> a) {
		this.setModel(mod);
		jSongs = a;
		for(int x = 0; x < jSongs.size(); x ++) {
			mod.addElement(x+": "+jSongs.get(x).getTitle());
			}
		dotherest();
	}	
	public DList() {
		this.setModel(mod);
		for(int x = 0; x<10;x++) {
		mod.addElement("Item "+x+" ");
		}
		dotherest();
	}
	public void dotherest() {
		this.setCellRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel a = new JLabel();
				String s = value.toString();
				a.setText(s);
				//a.setIcon(noteIcon);
				a.setLocation(0, 0);
				if (isSelected) {
					a.setBackground(list.getSelectionBackground());
					a.setForeground(list.getSelectionForeground());
					a.setIcon(notegIcon);
				}else {
					a.setBackground(list.getBackground());
					a.setForeground(list.getForeground());
					a.setIcon(noteIcon);
				}
				a.setEnabled(list.isEnabled());
				a.setFont(list.getFont());
				a.setOpaque(true);
				a.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(),BorderFactory.createLoweredSoftBevelBorder()));
				return a;
			}
			});
		this.setSelectionBackground(new Color(211,255,221));
		this.setSelectionForeground(new Color(50,50,50));
		this.setBackground(new Color(255,221,221));
		this.setForeground(new Color(0,0,0));
		this.repaint();
		this.revalidate();
	}
	public void SearchString(String str) {
		mod.clear();
		ArrayList<JSong> jSongs = JTunes.searchSongs(str);
		for(int i = 0;i<jSongs.size();i++) {
			if(jSongs.get(i).getTitle().toLowerCase().contains(str.toLowerCase())) {
				mod.addElement(i+": "+jSongs.get(i).getTitle());
			}
		}
	}
}
