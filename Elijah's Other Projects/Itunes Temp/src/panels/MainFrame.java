/**
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dunemask.util.FileUtil;

/**
 * @author dunemask
 *
 */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852115628604464359L;
	public static JTextField txtSearchbox;
	public static JSlider slider;
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FileUtil.getResourceURL("resources/lightjtn.png")));
	    this.setSize(600, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTunes");
	    try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    } catch (InstantiationException e) {
	      e.printStackTrace();
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e) {
	      e.printStackTrace();
	    }
	    SwingUtilities.updateComponentTreeUI(this);
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel main = new JPanel();
	    getContentPane().add(main, BorderLayout.CENTER);
	    main.setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.LIGHT_GRAY);
	    panel.setBounds(0, 0, 594, 79);
	    main.add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblSongTitle = new JLabel("Song Title");
	    lblSongTitle.setFont(new Font("Verdana", Font.PLAIN, 18));
	    lblSongTitle.setBounds(236, 11, 99, 29);
	    panel.add(lblSongTitle);
	    
	    slider = new JSlider();
	    slider.setBounds(188, 42, 200, 26);
	    slider.setValue(0);
	    panel.add(slider);
	    
	    String normText = "Search...";
	    txtSearchbox = new JTextField();
	    txtSearchbox.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		if(!txtSearchbox.getText().equals(normText)) {
	    			search();
	    		}
	    	}
	    });
	    txtSearchbox.setText(normText);
	    txtSearchbox.setBounds(473, 59, 86, 20);
	    panel.add(txtSearchbox);
	    txtSearchbox.setColumns(10);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(Color.DARK_GRAY);
	    panel_1.setBounds(0, 80, 125, 491);
	    main.add(panel_1);
	    this.setVisible(true);
	    this.setResizable(false);
	    
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenu mnFile = new JMenu("File");
	    menuBar.add(mnFile);
	    
	    JMenuItem mntmAddFile = new JMenuItem("Add File");
	    mntmAddFile.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FileDialog fd = new FileDialog(null);
	    		fd.setDirectory(System.getProperty("user.home")+"/Desktop/");
	    		fd.setVisible(true);
	    	}
	    });
	    mnFile.add(mntmAddFile);
	}
	/**
	 * 
	 */
	protected void search() {
		
		
	}
}
