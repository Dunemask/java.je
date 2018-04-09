package frames;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CellRend extends JLabel implements ListCellRenderer<Object>{
	final static ImageIcon longIcon = new ImageIcon("src/resources/long.png");
    final static ImageIcon shortIcon = new ImageIcon("src/resources/short.png");
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Object> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		String s = value.toString();
		setText(s);
		setIcon((s.length() > 10) ? longIcon:shortIcon);
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		return this;
	}

}
