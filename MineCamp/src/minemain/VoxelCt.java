package minemain;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import minerender.Inventory;
import minerender.Vector3;
import minerender.VoxEn;
import minerender.VoxPanel;

public class VoxelCt extends JFrame{
	static VoxEn ven;
	static KeyList key;
	public static boolean mousedown;
	public static boolean rmousedown;
	public static int mouseswoosh;
	public VoxelCt() {
		System.out.print("HI");
		ven = new VoxEn(new Vector3(25,25,55),50,50,50,1);
		JFrame f = this;
		//f.setVisible(true);
		f.setSize(600,600);
		f.setLayout(null);
		f.setEnabled(true);
		f.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseswoosh = e.getWheelRotation();
				System.out.print("SDFJIO");
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
				System.out.print("SDFJOJ");
				
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
		//f.setAlwaysOnTop(true);
		
		Inventory inv= new Inventory(50,50,100,100, ven);
		f.add(inv);
		inv.setVisible(false);
		
		
		
		
		
		VoxPanel vp = new VoxPanel();
		vp.setBackground(Color.black);
		vp.SetEnv(ven);
		vp.setVisible(true);
		vp.setBounds(0,0,400,400);
		f.add(vp);
		//JLabel jl = new JLabel("AAAH");
		//jl.setBounds(10,10,100,100);
		//f.add(jl);
		f.repaint();
		f.revalidate();
		int escape = 0;
		int buildref = 0;
		int einv = 0;
		while(escape==0) {
			ven.mx =vp.getWidth()/2+f.getLocation().x;
			ven.my =vp.getHeight()/2+f.getLocation().y;
			inv.setBounds(30, 30, f.getWidth()-60, f.getHeight()-60);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f.repaint();
			vp.repaint();
			if(einv==0) {
			Move();
			
			if(key.Output()[27]==1) {
	    		escape=1;
	    	}
			if(mousedown&&buildref==0) {
				ven.breaktime=8;
				//ven.setBlock(0);
				buildref =3;
			}
			if(!mousedown) {
				ven.breaktime=0;
			}
			if(rmousedown&&buildref==0) {
				ven.setBlockOut(2);
				buildref =3;
			}
			if(ven.breaktime>0)
			ven.breaktime-=4.0f/(ven.getBlock(ven.GetSelecID()-1).getBreakTime());
			if(ven.breaktime<0) {
				ven.breaktime=0;
				ven.setBlock(0);
			}
			if(buildref>0&&ven.breaktime==0)
				buildref--;
			System.out.println("YOLO"+buildref+"   "+ven.breaktime);
			
			if(key.Output()[69]==1) {
				einv=1;
				inv.setVisible(true);
				}
			}else {
			if(key.Output()[69]==1) {
				einv=0;
				inv.setVisible(false);
			}
			}
		}
	}
	public static void Move() {
		float spd=0.5f;
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
}
