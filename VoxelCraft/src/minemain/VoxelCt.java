package minemain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import mc.Minecraft;
import minerender.FileControl;
import minerender.Inventory;
import minerender.Vector3;
import minerender.VoxEn;
import minerender.VoxPanel;
import mplayer.SoundEngine;

public class VoxelCt extends JPanel{
	private static  Inventory inv;
	private VoxPanel vp;
	private VoxEn ven;
	static KeyList key;
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
	public static Vector3 vel;
	public int mode =1;
	int flymode=0;
	int flytime=0;
	int buildref = 0;
	Timer timer;
	public VoxelCt(VoxEn voxen,int Mode) {
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
		this.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseswoosh = e.getWheelRotation();
				//System.out.print("SDFJIO");
			}
		});
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.getButton()==1)
					mousedown = true;
				if(e.getButton()==3)
					rmousedown = true;
				
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
		    this.requestFocus();
	//	this.setAlwaysOnTop(true);
		   
		    if(mode==1) {
		inv= new Inventory(ven);
		}else {
		inv= new Inventory(ven,1);
		}
		this.add(inv);
		inv.setVisible(false);
		
		vp = new VoxPanel();
		vp.setBackground(Color.black);
		vp.SetEnv(ven);
		vp.setVisible(true);
		vp.setBounds(0,0,600,600);
		this.add(vp);
		this.repaint();
		this.revalidate();
		//System.out.println(this.getParent());
		//frame = (JFrame) this.getParent().getParent().getParent();

		timer= new Timer(30,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			timerCode();
				
			}});
		timer.start();

	}
	/**
	 * 
	 */
	protected void timerCode() {
		//ven.campos.print();
		
		//System.out.println(this.getParent().getParent().getParent().getParent());
		ven.mx =vp.getWidth()/2+this.getTopLevelAncestor().getLocation().x;
		ven.my =vp.getHeight()/2+this.getTopLevelAncestor().getLocation().y;
		inv.setBounds(30, 30, this.getWidth()-60, this.getHeight()-60);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(key.Output()[27]==1) {//Esc
			escMenu();
			
			//timer.stop();
			//Minecraft.goToSelect();
			//System.exit(0);
		}
		if(key.Output()[KeyEvent.VK_UP]==1) {
			Minecraft.renderVal++;
			System.out.println(Minecraft.renderVal);
		}
		if(key.Output()[KeyEvent.VK_DOWN]==1) {
			Minecraft.renderVal--;
			System.out.println(Minecraft.renderVal);
		}
		if(key.Output()[73]==1) {//I//TODO
			VoxEn.fovc+=.001f;
			System.out.println(VoxEn.fovc);
		}
		if(key.Output()[79]==1) {//O//TODO
			VoxEn.fovc-=.001f;
			System.out.println(VoxEn.fovc);
		}
		
		
		vp.setSize(this.getWidth(), this.getHeight());
		this.repaint();
		vp.repaint();
		this.requestFocus();
		if(einv==0) {
		
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
			if(ven.breaktime>0) {
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
			if(ven.selected>8)
				ven.selected = 0;
			mouseswoosh = 0;
		}
		
		
		if(buildref>0&&ven.breaktime==0)
			buildref--;
		
		if(key.Output()[69]==1) {
			einv=1;
			inv.setVisible(true);
			}
		}else {
			
		if(einv ==1 && key.Output()[69]==0) {
			einv=2;
		}
		if(einv ==2 && key.Output()[69]==1) {
			einv=3;
		}
		if(einv ==3 && key.Output()[69]==0) {
			einv=0;
			inv.setVisible(false);
		}
		}
		
	}
	/**
	 * 
	 */
	protected void escMenu() {
		timer.stop();
		FileControl.SaveFileAsXML(ven, ven.getName());
		Minecraft.quickMenu();
		
		
		
	}
	public void Move() {
		float spd=0.5f;
		vel.z =1;
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
		float spd=0.2f;
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
		//if(key.Output()[32]==1)
			//ven.MoveCam(Vector3.upward(spd));
		//if(key.Output()[16]==1)
			//ven.MoveCam(Vector3.downward(spd));
	}
}
