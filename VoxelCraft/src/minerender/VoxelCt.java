package minerender;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import dunemask.util.FileUtil;
import dunemask.util.StringUtil;
import mc.MineCommands;
import mc.Minecraft;
import mplayer.SoundEngine;

public class VoxelCt extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7546821983465423073L;
	private boolean developerMode = false;
	private  Inventory inv;
	private VoxPanel vp;
	private VoxEn ven;
	private KeyList key;
	float spd=0.2f;
	private JLabel crosshair=new JLabel();
	public static boolean mousedown;
	/**
	 * @return the vp
	 */
	public VoxPanel getVp() {
		return vp;
	}
	/**
	 * @param vp the vp to set
	 */
	public void setVp(VoxPanel vp) {
		this.vp = vp;
	}
	public static boolean rmousedown;
	public static int mouseswoosh;
	public int escape = 0;
	int einv = 0;
	public JFrame frame;
	/**
	 * @return the ven
	 */
	public VoxEn getVen() {
		return ven;
	}
	/**
	 * @param ven the ven to set
	 */
	public void setVen(VoxEn ven) {
		this.ven = ven;
	}
	public void printText(String text) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String ctext= display.getText();
				display.setText(text+"\n"+ctext);
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ctext= display.getText();
				ctext=StringUtil.replaceLast(ctext, text+"\n", "");
				display.setText(ctext);
			}
			
		}).start();

		
	}
	
	
	public static Vector3 vel;
	public int mode =1;
	int flymode=0;
	int flytime=0;
	int buildref = 0;
	Timer timer;
	/**Text Holder*/
	private JPanel th = new JPanel(null);
	private JTextArea display = new JTextArea();
	private JTextField textBar;
	public VoxelCt(VoxEn voxen,int Mode,boolean resume) {
		vel = new Vector3(0,0,0);
		mode=Mode;
		//System.out.print("HI");
		ven = voxen;
		//JPanel f = this;
		this.setVisible(true);
		this.setBackground(Color.black);
		//this.setLocationRelativeTo(null);
		//this.setSize(600,600);
		this.setLayout(null);
		this.setEnabled(true);
		this.setSize(600, 600);
		this.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseswoosh = e.getWheelRotation();
				//System.out.print("SDFJIO");
			}
		});
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				
			}
			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.getButton()==1)
					mousedown = true;
				if(e.getButton()==3)
					rmousedown = true;
				if(e.getButton()==2) {
					middleButtonClicked();
				}
				
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(e.getButton()==1)
					mousedown = false;
				if(e.getButton()==3)
					rmousedown = false;
			}
		});
		key =new KeyList();
		 this.addKeyListener(key);
		    this.setFocusable(true);
		    this.requestFocusInWindow();
	//	this.setAlwaysOnTop(true);
		    if(mode==VoxelCt.Creative) {// Creative
		    	inv= new Inventory(ven);
		    	if(!resume) {
		    	SoundEngine.stop(SoundEngine.allEngines);
		    	SoundEngine.start(SoundEngine.title);
		    	}
		    }else {
		    	if(!resume) {
		    	SoundEngine.stop(SoundEngine.allEngines);
		    	SoundEngine.start(SoundEngine.game);
		    	}
		    	inv= new Inventory(ven,1);
		    }
		    vp = new VoxPanel();
			vp.setBackground(Color.black);
			vp.SetEnv(ven);
			vp.setVisible(true);
			vp.setBounds(0,0,600,600);    
		    
		    
		 textBar =new JTextField();
		 th.setSize(vp.getWidth(), vp.getHeight());  
		 th.setOpaque(false);
		 th.add(textBar);
		 textBar.setVisible(false);
		 int tbw=th.getWidth();
		 textBar.setBackground(Color.DARK_GRAY);
		 textBar.setForeground(Color.WHITE);
		// textBar.setOpaque(false);
		textBar.setBounds(0, vp.getHeight()-120, tbw, 80);
		 textBar.setFont(new Font("Century Gothic", Font.PLAIN, textBar.getHeight()-40));
		 textBar.setCaretColor(Color.white);
		 display.setFont(textBar.getFont());
		 display.setOpaque(false);
		 display.setForeground(textBar.getForeground());
		 display.setCursor(Minecraft.getBlankCurosr());
		 MouseListener[] dml = display.getMouseListeners();
		 for(int i=0;i<dml.length;i++) {
			 display.removeMouseListener(dml[i]);
		 }
		 this.add(display);
		this.add(th);
		    
		this.add(inv);
		inv.setVisible(false);
		inv.getHotbar().setSelectedIndex(0);
		int mw = (vp.getWidth()/2)+1;
		int mh = (vp.getHeight()/2)+1;
		//System.out.println(mw+","+mh);
		int w = 42;
		int h = 42;
		this.crosshair.setBounds(mw-w, mh-h, w, h);
		BufferedImage img = this.resizeImage(FileUtil.getResourceURL("resources/textures/gui/overlay/crosshair.png"), w, h);
		this.crosshair.setIcon(new ImageIcon(img));
		this.add(crosshair);
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
            	panelResized();
            }
			
		});
		int dif = Math.abs(mh-textBar.getY())-10;	
		 display.setBounds(0, textBar.getY()-dif, tbw, dif);
		
		this.add(vp);
		this.repaint();
		this.add(coord,1);
		this.add(lookingat,1);
		this.revalidate();
		//System.out.println(this.getParent());
		//frame = (JFrame) this.getParent().getParent().getParent();

		timer= new Timer(60,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				timerCode();
			}});
		timer.start();

	}
	/**
	 * 
	 */
	protected void middleButtonClicked() {
		int blocknum = this.ven.getBlock(this.ven.sel);
		if(blocknum!=0) {
			this.inv.setSelectedVal(blocknum);
		}
		
	}
	private JLabel coord = new JLabel();
	private JLabel lookingat = new JLabel();
	/**
	 * 
	 */
	protected void panelResized() {
		vp.setSize(Minecraft.cf.getWidth(), Minecraft.cf.getHeight());
	 th.setSize(vp.getWidth(), vp.getHeight());  
		int mw = (vp.getWidth()/2)+1;
		int mh = (vp.getHeight()/2)+1;
		int w = 42;
		int h = 42;
		this.crosshair.setBounds(mw-w, mh-h, w, h);
		BufferedImage img = this.resizeImage(FileUtil.getResourceURL("resources/textures/gui/overlay/crosshair.png"), w, h);
		this.crosshair.setIcon(new ImageIcon(img));
		int tbw=th.getWidth();
		// textBar.setOpaque(false);
		textBar.setBounds(0, th.getHeight()-120, tbw, 80);
		 display.setForeground(textBar.getForeground());
		 int dif = Math.abs(mh-textBar.getY())-10;
			
			
			 display.setBounds(0, textBar.getY()-dif, tbw, dif);
		
	}
	private BufferedImage resizeImage(URL url,int width,int height) {
		Image back = new ImageIcon(url).getImage();
	    BufferedImage bimage = new BufferedImage(back.getWidth(null), back.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(back, 0, 0, null);
	    bGr.dispose();
	    
		BufferedImage resized = new BufferedImage(width, height, bimage.getType());
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bimage, 0, 0, width, height, 0, 0, bimage.getWidth(),
		    bimage.getHeight(), null);
		g.dispose();
		return resized;
		
	}
	private static int countSpaces(String str) {
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
		while (m.find())
		    list.add(m.group(1));
		
		return list.toArray(new String[list.size()]).length-1;
	}
	
	
	public boolean updateMouse = true;
	int cc=0;
	KeyListener tbl = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent arg0) {
			boolean rel = arg0.getKeyChar()=='\n';
			if(rel) {
				command();
				cc = MineCommands.commands.size()-1;
			}
			
				if(arg0.getKeyCode()==9&&textBar.getText().contains("/setblock ")) {//Tab needs to fill
					int spaces = countSpaces(textBar.getText());
					String cur = textBar.getText();
					if(spaces==0) {
						textBar.setText(cur+(int)ven.sel.x+"");
					}
					if(spaces==1) {
						textBar.setText(cur+(int)ven.sel.z+"");
					}
					if(spaces==2) {
						textBar.setText(cur+(int)ven.sel.y+"");
					}
				}
				if(arg0.getKeyCode()==9&&(textBar.getText().contains("/fill ")||textBar.getText().contains("/fill-replace "))) {//Tab needs to fill
					int spaces = countSpaces(textBar.getText());
					String cur = textBar.getText();
					if(spaces==0) {
						textBar.setText(cur+(int)ven.sel.x+"");
					}
					if(spaces==1) {
						textBar.setText(cur+(int)ven.sel.z+"");
					}
					if(spaces==2) {
						textBar.setText(cur+(int)ven.sel.y+"");
					}
					if(spaces==3) {
						textBar.setText(cur+(int)ven.sel.x+"");
					}
					if(spaces==4) {
						textBar.setText(cur+(int)ven.sel.z+"");
					}
					if(spaces==5) {
						textBar.setText(cur+(int)ven.sel.y+"");
					}
				}else if(arg0.getKeyCode()==9) {//Tab again
					textBar.setText(autoFill(textBar.getText()));
				}
			
				if(arg0.getKeyCode()==KeyEvent.VK_UP) {
					int lastc = cc;
					try {
						textBar.setText(MineCommands.getCommand(cc));
						cc--;
					}catch(Exception e) {
						cc = lastc;
					}
					
				}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
					int lastc = cc;
					try {
						cc++;
						textBar.setText(MineCommands.getCommand(cc));
					}catch(Exception e) {
					cc = lastc;
					}
				}

			
			if(arg0.getKeyCode()==27) {//Escape
				textBar.setText("");
				command();
			}
			//textBar.setText(textBar.getText()+arg0.getKeyChar());
			textBar.repaint();
			textBar.revalidate();
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {

			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {

			
		}
		
	};
	boolean stats = false;
	public boolean forceupdate = true;
	/**
	 * 
	 */
	protected void timerCode() {
		//ven.campos.print();
		
		//System.out.println(this.getParent().getParent().getParent().getParent());
		try {
			ven.mx =vp.getWidth()/2+this.getTopLevelAncestor().getLocation().x;
			ven.my =vp.getHeight()/2+this.getTopLevelAncestor().getLocation().y;
		}catch(java.lang.NullPointerException exc)	{
			
		}
		inv.setBounds(30, 30, this.getWidth()-60, this.getHeight()-60);
		
		if(key.Output()[17]==1) {//Sprint
			spd=0.24f;
		}else {
			spd=0.12f;
		}
		
		if(this.isDeveloperMode()) {
			if(key.Output()[KeyEvent.VK_UP]==1) {
				Minecraft.renderVal++;
				System.out.println(Minecraft.renderVal);
			}
			if(key.Output()[KeyEvent.VK_DOWN]==1) {
				Minecraft.renderVal--;
				System.out.println(Minecraft.renderVal);
			}
			if(key.Output()[73]==1) {//I
				this.getVen().fovc+=.001f;
				System.out.println(this.getVen().fovc);
			}
			if(key.Output()[79]==1) {//O
				this.getVen().fovc-=.001f;
				System.out.println(this.getVen().fovc);
			}
		}
		if(key.Output()[84]==1) {//T PUshed 
			this.textBar.setFocusTraversalKeysEnabled(false);
			this.updateMouse=false;
			this.textBar.setVisible(true);
			textBar.requestFocusInWindow();
			this.forceupdate=false;
			//this.setCursor(Cursor.getDefaultCursor());
			this.removeKeyListener(key);
			textBar.removeKeyListener(tbl);
			textBar.addKeyListener(tbl);
			this.repaint();
			this.revalidate();
			key.Output()[84]=0;

			
		}
		if(key.Output()[47]==1) {// / PUshed 
			this.textBar.setFocusTraversalKeysEnabled(false);
			this.updateMouse=false;
			this.textBar.setVisible(true);
			textBar.requestFocusInWindow();
			this.forceupdate=false;
			//this.setCursor(Cursor.getDefaultCursor());
			this.removeKeyListener(key);
			textBar.removeKeyListener(tbl);
			textBar.addKeyListener(tbl);
			//System.out.println("true");
			textBar.setText("/");
			this.repaint();
			this.revalidate();
			key.Output()[47]=0;

			
		}
		if(key.Output()[114]==1) {
			stats=!stats;
			key.Output()[114]=0;
		}

		if(stats) {
			String cordText = "X:"+(int)ven.campos.x+"Y:"+(int)ven.campos.z+"Z:"+(int)ven.campos.y;
			String lookingText = "Looking At: X:"+(int)ven.sel.x+"Y:"+(int)ven.sel.z+"Z:"+(int)ven.sel.y;
			coord.setFont(new Font("Century Gothic", Font.PLAIN, textBar.getHeight()-40));
			lookingat.setFont(coord.getFont());
			coord.setForeground(Color.WHITE);
			lookingat.setForeground(coord.getForeground());
			coord.setText(cordText);
			coord.setSize(coord.getPreferredSize());
			coord.setLocation(0, 0);
			lookingat.setText(lookingText);
			lookingat.setSize(lookingat.getPreferredSize());
			lookingat.setLocation(0, coord.getHeight());
		}else {
			coord.setText(null);
			lookingat.setText(null);
		}
		
		
		vp.setSize(this.getWidth(), this.getHeight());
		if(this.forceupdate==true) {
			//System.out.println("Request");
			this.requestFocusInWindow();
		}
		this.repaint();
		if(einv==0) {
			if(key.Output()[27]==1) {//Esc		
				timer.stop();
				escMenu();
			}
			if (mode==0){
				Walk();
			}else {
				if (flymode==0) {
					Move();
				}
				if(flymode==1) {
					Walk();
				}
				if(key.Output()[32]==1&&flytime==0) {
					flytime=1;
				}
				if(key.Output()[32]==0&&flytime>0) {
					flytime++;
				}
				if(key.Output()[32]==1&&flytime>1) {
					flymode=(flymode+1)%2;
					flytime=-1;
				}
				if(flytime>10) {
					flytime=-1;
				}
				if(flytime==-1&&key.Output()[32]==0) {
					flytime=0;
				}
				
			}
		
		if(key.Output()[27]==1) {
    		escape=1;
    	}
		if(mousedown&&buildref==0) {
			ven.breaktime=8;
			//ven.setBlock(0);
			buildref =2;
		}
		if(!mousedown) {
			ven.breaktime=0;
		}
		if(rmousedown&&buildref==0) {
			if(mode==0) {
			ven.setBlockOut(ven.hotbar[ven.selected],true);
			}else {
				ven.setBlockOut(ven.hotbar[ven.selected],false);

			}
			buildref =2;
		}
		if(mode==0) {
			if(ven.breaktime>0&&ven.getBlock(ven.GetSelecID()-1)!=null) {
			int time = (ven.getBlock(ven.GetSelecID()-1).getBreakTime());
			for(int i=0;i<2;i++) {
				try {
					Thread.sleep(time/2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SoundEngine.handle("block_break");
				
			}
		ven.breaktime-=3.9f/time;
		
		
		
		}
		if(ven.breaktime<0) {
			ven.breaktime=0;
			ven.setBlock(0,true);
		}
		}else {
			if(ven.breaktime==8)
			ven.setBlock(0,false);
			ven.breaktime=0;
		}
		if(mouseswoosh!=0) {
			ven.selected+=mouseswoosh;
			if(ven.selected<0)
				ven.selected = 8;
				inv.getHotbar().setSelectedIndex(ven.selected);
			if(ven.selected>8)
				ven.selected = 0;
				inv.getHotbar().setSelectedIndex(ven.selected);
			mouseswoosh = 0;
		}
		
		
		if(buildref>0&&ven.breaktime==0)
			buildref--;
		
		if(key.Output()[69]==1) {
			einv=1;
			inv.setVisible(true);
			this.setCursor(Cursor.getDefaultCursor());
			}
		}else {
			
		if(einv ==1 && key.Output()[69]==0) {
			einv=2;
		}
		if(einv ==2 && key.Output()[69]==1) {
			einv=3;
		}
		if(einv ==3 && (key.Output()[69]==0||key.Output()[27]==1)) {
			this.setCursor(Minecraft.getBlankCurosr());
			einv=0;
			inv.setVisible(false);
		}
		}
		
	}
	/**
	 * @param text
	 * @return
	 */
	protected String autoFill(String text) {
		
		if(countSpaces(text)<=0) {
			if(StringUtil.containsIgnoreCase(text, "/so")) {
				return "/soundengine";
			}
			if(StringUtil.containsIgnoreCase(text, "/setb")) {
				return "/setblock";
			}
			if(StringUtil.containsIgnoreCase(text, "/fill")) {
				return "/fill-replace";
			}
			if(StringUtil.containsIgnoreCase(text, "/f")) {
				return "/fill";
			}
			if(StringUtil.containsIgnoreCase(text, "/h")) {
				return "/help";
			}
			if(StringUtil.containsIgnoreCase(text, "/g")) {
				return "/gamemode";
			}
			
		}else if(countSpaces(text)==1) {
			if(StringUtil.containsIgnoreCase(text, "/soundengine p")) {
				return "/soundengine play";
			}
			if(StringUtil.containsIgnoreCase(text, "/soundengine s")) {
				return "/soundengine stopall";
			}
			if(StringUtil.containsIgnoreCase(text, "/soundengine on")) {
				return "/soundengine off";
			}
			if(StringUtil.containsIgnoreCase(text, "/soundengine off")) {
				return "/soundengine on";
			}
			if(StringUtil.containsIgnoreCase(text, "/soundengine o")) {
				return "/soundengine on";
			}
			if(StringUtil.containsIgnoreCase(text, "/help ")) {
				String add = autoFill(text.replace("/help ", "/"));
				return "/help " + add.substring(1, add.length());
			}
		}
		
		
		
		return text;
	}
	/**
	 * 
	 */
	protected void command() {
		//this.removeKeyListener(tbl);
		String command = textBar.getText();
		if(command.equals("")||command.equals(" ")) {
			command=null;
		}
		if(command!=null) {
			MineCommands.HandleCommand(command);
		}
		this.textBar.setText("");
		this.setCursor(Minecraft.getBlankCurosr());
		this.updateMouse=true;
		this.forceupdate=true;
		this.textBar.setVisible(false);
		this.requestFocus();
		key = new KeyList();
		this.addKeyListener(key);
		
	}
	/**
	 * 
	 */
	protected void escMenu() {
		timer.stop();
		Minecraft.quickMenu();
		
		
		
	}
	public void Move() {
		spd=0.5f;
		vel.z =1;
		if(Minecraft.cf.isFocused()&&this.updateMouse==true) {//I
			//oldMove();
			ven.MouseCam();
			if(key.Output()[87]==1)
				vel.x+=spd;
			if(key.Output()[83]==1)
				vel.x-=spd;
			if(key.Output()[68]==1)
				vel.y+=spd;
			if(key.Output()[65]==1)
				vel.y-=spd;
			if(key.Output()[32]==1)
				ven.MoveCam(Vector3.upward(spd));
			if(key.Output()[16]==1)
				ven.MoveCam(Vector3.downward(spd));
			ven.MoveCam(new Vector3(vel.rotate(ven.rx, "z").x,0,0));
			ven.MoveCam(new Vector3(0,vel.rotate(ven.rx, "z").y,0));
			//ven.MoveCam(new Vector3(0,0,vel.rotate(ven.rx, "z").z));
			
			vel.z *=0.9f;
			vel.x *=0.5f;
			vel.y *=0.5f;
			if(this.getVen().campos.z<-50) {
				exceededWorldBounds();
			}
				//this.getVen().campos.print();
		}else if(!Minecraft.cf.isFocused()){
			this.escMenu();
		}

	}
	/**
	 * 
	 */
	protected void oldMove() {
		ven.MouseCam();
		if(key.Output()[87]==1)
			ven.MoveCam(Vector3.foreward(spd).rotate(ven.rx, "z"));
			if(key.Output()[83]==1)
				ven.MoveCam(Vector3.backward(spd).rotate(ven.rx, "z"));
			if(key.Output()[68]==1)
				ven.MoveCam(Vector3.rightward(spd).rotate(ven.rx, "z"));
			if(key.Output()[65]==1)
				ven.MoveCam(Vector3.leftward(spd).rotate(ven.rx, "z"));
			if(key.Output()[32]==1)
				ven.MoveCam(Vector3.upward(spd));
			if(key.Output()[16]==1)
				ven.MoveCam(Vector3.downward(spd));
		
	}
	public void Walk() {
		if(Minecraft.cf.isFocused()&&this.updateMouse==true) {
			ven.MouseCam();
			if(key.Output()[87]==1)
				vel.x+=spd;
			if(key.Output()[83]==1)
				vel.x-=spd;
			if(key.Output()[68]==1)
				vel.y+=spd;
			if(key.Output()[65]==1)
				vel.y-=spd;
			ven.MoveCam(new Vector3(vel.rotate(ven.rx, "z").x,0,0));
			ven.MoveCam(new Vector3(0,vel.rotate(ven.rx, "z").y,0));
			if(ven.MoveCam(new Vector3(0,0,vel.z))) {
				vel.z-=0.3;
			}else {
				vel.z=-0.1f;
				
				if(key.Output()[32]==1)
					vel.z=0.8f;
			}
			vel.z *=0.9f;
			vel.x *=0.5f;
			vel.y *=0.5f;
			if(this.getVen().campos.z<-50) {
				exceededWorldBounds();
			}
			//if(key.Output()[32]==1)
				//ven.MoveCam(Vector3.upward(spd));
			//if(key.Output()[16]==1)
				//ven.MoveCam(Vector3.downward(spd));
		}else if(!Minecraft.cf.isFocused()){
			this.escMenu();
		}
		
	}
	/**
	 * 
	 */
	private void exceededWorldBounds() {
		System.out.println("Dead Dude!");
		this.getVen().campos = new Vector3(50,50,150);
		
		
	}
	
	public static final int Creative=1;
	public static final int Survival=0;
	/** Change mode
	 * 
	 * */
	public void changeMode(int mode) {
		this.mode=mode;
	    this.remove(inv);
	    //System.out.println("Changed Modes");
	    if(mode==VoxelCt.Creative) {// Creative
	    	inv= new Inventory(ven);
	    	SoundEngine.stop(SoundEngine.allEngines);
	    	SoundEngine.start(SoundEngine.title);
	    }else {
	    	SoundEngine.stop(SoundEngine.allEngines);
	    	SoundEngine.start(SoundEngine.game);
	    	inv= new Inventory(ven,1);
	    }
    	//inv.setLm(lm);
    	//inv.setHb(hb);
    	//inv.setHotbar(hotbar);
	    inv.getHotbar().setSelectedIndex(ven.selected);
    	inv.setBounds(30, 30, this.getWidth()-60, this.getHeight()-60);
    	inv.setVisible(false);
    	this.add(inv,0);
	}
	
	
	/**
	 * @return the crosshair
	 */
	public JLabel getCrosshair() {
		return crosshair;
	}
	/**
	 * @param crosshair the crosshair to set
	 */
	public void setCrosshair(JLabel crosshair) {
		this.crosshair = crosshair;
	}
	/**
	 * @return the textBar
	 */
	public JTextField getTextBar() {
		return textBar;
	}
	/**
	 * @param textBar the textBar to set
	 */
	public void setTextBar(JTextField textBar) {
		this.textBar = textBar;
	}
	/**
	 * @return the display
	 */
	public JTextArea getDisplay() {
		return display;
	}
	/**
	 * @param display the display to set
	 */
	public void setDisplay(JTextArea display) {
		this.display = display;
	}
	/**
	 * @return the developerMode
	 */
	public boolean isDeveloperMode() {
		return developerMode;
	}
	/**
	 * @param developerMode the developerMode to set
	 */
	public void setDeveloperMode(boolean developerMode) {
		this.developerMode = developerMode;
	}
}
