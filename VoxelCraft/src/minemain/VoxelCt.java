package minemain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
	static VoxEn ven;
	static KeyList key;
	public static boolean mousedown;
	public static boolean rmousedown;
	public static int mouseswoosh;
	public int escape = 0;
	int einv = 0;
	public JFrame frame;
	public static Vector3 vel;
	public int mode =1;
	int flymode=0;
	int flytime=0;
	int buildref = 0;
	Timer timer;
	public VoxelCt(VoxEn voxen) {
		vel = new Vector3(0,0,0);
		//System.out.print("HI");
		ven = voxen;
		JPanel f = this;
		f.setVisible(true);
		f.setBackground(Color.black);
		//f.setLocationRelativeTo(null);
		//f.setSize(600,600);
		f.setLayout(null);
		f.setEnabled(true);
		f.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseswoosh = e.getWheelRotation();
				//System.out.print("SDFJIO");
			}
		});
		f.addMouseListener(new MouseListener() {
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
		 f.addKeyListener(key);
		    f.setFocusable(true);
		    f.requestFocus();
	//	f.setAlwaysOnTop(true);
		
		Inventory inv= new Inventory(50,50,100,100, ven);
		f.add(inv);
		inv.setVisible(false);
		
		VoxPanel vp = new VoxPanel();
		vp.setBackground(Color.black);
		vp.SetEnv(ven);
		vp.setVisible(true);
		vp.setBounds(0,0,600,600);
		f.add(vp);
		f.repaint();
		f.revalidate();
		//System.out.println(f.getParent());
		//frame = (JFrame) f.getParent().getParent().getParent();

		timer= new Timer(30,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ven.campos.print();
				
				//System.out.println(f.getParent().getParent().getParent().getParent());
				ven.mx =vp.getWidth()/2+f.getParent().getParent().getParent().getParent().getLocation().x;
				ven.my =vp.getHeight()/2+f.getParent().getParent().getParent().getParent().getLocation().y;
				inv.setBounds(30, 30, f.getWidth()-60, f.getHeight()-60);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(key.Output()[27]==1) {
					System.out.println(ven.getName());
					FileControl.SaveFileAsXML(ven, ven.getName());
					SoundEngine.stop(SoundEngine.game);
					SoundEngine.start(SoundEngine.title);
					timer.stop();
					Minecraft.goToSelect();
					//System.exit(0);
				}
				
				
				vp.setSize(f.getWidth(), f.getHeight());
				f.repaint();
				vp.repaint();
				f.requestFocus();
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
					ven.setBlockOut(ven.hotbar[ven.selected]);
					buildref =2;
				}
				if(mode==0) {
				if(ven.breaktime>0)
				ven.breaktime-=3.9f/(ven.getBlock(ven.GetSelecID()-1).getBreakTime());
				if(ven.breaktime<0) {
					ven.breaktime=0;
					ven.setBlock(0);
				}
				}else {
					if(ven.breaktime==8)
					ven.setBlock(0);
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
				
			}});
		timer.start();

	}
	public static void Move() {
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
	public static void Walk() {
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
