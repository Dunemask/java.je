package voxeltest;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import Graphicstest.KeyList;
import Graphicstest.Vector3;

public class VoxelMain {
	public static VoxEn en3d;
	
	public static boolean mousedown;
	public static boolean rmousedown;
	public static int mouseswoosh;
	static boolean out = true;
	static int type= 0;
	public static String name;
	public static void main(String[] args) {
		//en3d = new VoxEn(new Vector3(10,10,90));
		/////GET THE THING THAT DOES STUFF AND GET FILE OR CREATE NEW ONE!!!
		try {
		File fs = FileStuff.Choose("src\\voxeltest\\saves");
		en3d = new VoxEn(new Vector3(10,10,90),fs);
		name = fs.getName().substring(0, fs.getName().length()-4);
		System.out.println(name);
		}catch(NullPointerException e) {
			System.out.println("What is the thing?");
			JFrame xd= new JFrame("NAME?");
			xd.setSize(350, 150);
			xd.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
			xd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			xd.setVisible(true);
			xd.setLayout(null);
			JTextField tf = new JTextField("Untitled");
			tf.setBounds(0,0,200,25);
			JTextField tfx = new JTextField("64");
			tfx.setBounds(0,25,30,25);
			JTextField tfy = new JTextField("64");
			tfy.setBounds(0,50,30,25);
			JTextField tfz = new JTextField("64");
			tfz.setBounds(0,75,30,25);
			JButton jb = new JButton("Normal");
			jb.setBounds(200,0,100,50);
			JButton jb2 = new JButton("Flat");
			jb2.setBounds(200,50,100,50);
			jb.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					out =false;
					type=1;
				}
			});
			jb2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					out =false;
					type=2;
				}
			});
			xd.add(tf);
			xd.add(jb);xd.add(jb2);
			xd.add(tfx);xd.add(tfy);xd.add(tfz);
			xd.repaint();
			while(out) {
				System.out.print("");
			}
			name = tf.getText();
			int x = Integer.parseInt(tfx.getText());
			int y = Integer.parseInt(tfy.getText());
			int z = Integer.parseInt(tfz.getText());
			xd.dispose();
			en3d = new VoxEn(new Vector3(10,10,90),x,y,z,type);
		}
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorImg, new Point(0, 0), "blank cursor");
		Cursor normCursor = Cursor.getDefaultCursor();
		
		
		
		JFrame frame = new JFrame("VoxPan");
		VoxPanel p = new VoxPanel();
		p.SetE3d(en3d);
		 frame.setSize(500, 500);
		frame.getContentPane().add(p);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    KeyList key = new KeyList();
	    frame.addKeyListener(key);
	    frame.setFocusable(true);
	    frame.requestFocus();
	    frame.repaint();
	    frame.getContentPane().setCursor(blankCursor);
	    frame.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		if(e.getButton()==1)
	    		mousedown = true;
	    		if(e.getButton()==3)
		    		rmousedown = true;
	    		
	    	}
	    	public void mouseReleased(MouseEvent e) {
	    		if(e.getButton()==1)
		    		mousedown = false;
		    		if(e.getButton()==3)
			    		rmousedown = false;
	    	}
	    });
	    frame.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				mouseswoosh = e.getWheelRotation();
			}
	    });
	    //Adds the stuff at the bottom
	    
	    
	    
	    
	    

	    int time = 0;
	    int escape = 0;
	    int buildref =0;
	    Vector3 v = new Vector3(0,0,0);
	    boolean up = false;
	    int mode = 1;
	    Timer t = new Timer(30, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	frame.repaint();
	            }    
	        });
	    t.start();
	    
	    
	    while(true) {
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	    frame.getContentPane().setCursor(blankCursor);
	    
	    en3d.SetMouse(frame.getLocation().x+(int)frame.getSize().getWidth()/2, frame.getLocation().y+(int)frame.getSize().getHeight()/2);
	    while(escape==0) {
	    	time++;
	    	en3d.MouseCam();
	    	try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	en3d.timer+=1;
	    	if(key.Output()[39]==1) {
	    		en3d.RotateCam(0.05f, 0);
	    		up=true;
	    	}
	    	
	    	if(key.Output()[38]==1) {
	    		en3d.RotateCam(0, 0.05f);
	    		up=true;
	    	}
	    	if(key.Output()[37]==1) {
	    		en3d.RotateCam(-0.05f, 0);
	    		up=true;
	    	}
	    	if(key.Output()[40]==1) {
	    		en3d.RotateCam(0, -0.05f);
	    		up=true;
	    	}
	    	//CREATIVE MODE
	    	if (mode ==0) {
	    	if(key.Output()[87]==1) {
	    		en3d.MoveCam(Vector3.foreward(0.2f).rotate(en3d.rx, "z"));
	    		up=true;
	    	}
	    	if(key.Output()[83]==1) {
	    		en3d.MoveCam(Vector3.backward(0.2f).rotate(en3d.rx, "z"));
	    		up=true;
	    	}
	    	if(key.Output()[68]==1) {
	    		en3d.MoveCam(Vector3.rightward(0.2f).rotate(en3d.rx, "z"));
	    		up=true;
	    	}
	    	if(key.Output()[65]==1) {
	    		en3d.MoveCam(Vector3.leftward(0.2f).rotate(en3d.rx, "z"));
	    		up=true;
	    	}
	    	if(key.Output()[32]==1) {
	    		en3d.MoveCam(Vector3.upward(0.2f));
	    		up=true;
	    	}
	    	if(key.Output()[16]==1) {
	    		en3d.MoveCam(Vector3.downward(0.2f));
	    		up=true;
	    	}
	    	}
	    	//SURVIVAL MODE
	    	if (mode ==1) {
	    		if(key.Output()[87]==1) {
	    			v = Vector3.add(v,Vector3.foreward(0.1f).rotate(en3d.rx, "z"));
	    		}
	    		if(key.Output()[83]==1) {
		    		v = Vector3.add(v,Vector3.backward(0.1f).rotate(en3d.rx, "z"));
		    	}
	    		if(key.Output()[68]==1) {
		    		v = Vector3.add(v,Vector3.rightward(0.1f).rotate(en3d.rx, "z"));
		    	}
	    		if(key.Output()[65]==1) {
		    		v = Vector3.add(v,Vector3.leftward(0.1f).rotate(en3d.rx, "z"));
		    	}
	    		v.y = v.y*0.7f;
	    		v.x = v.x*0.7f;
	    		v.z -= 0.025f;
		    		en3d.MoveCam(new Vector3(0,v.y,0));
		    		en3d.MoveCam(new Vector3(v.x,0,0));
		    		if(v.z<0) {
		    			if(!en3d.MoveCam(new Vector3(0,0,v.z))) {
		    				if(key.Output()[32]==1) {
		    		    		v.z =0.24f;
		    				}else{
		    		    		v.z=0;
		    				}}else{
		    				//v.z -= 0.1f;
		    			}
		    		}else {
		    			en3d.MoveCam(new Vector3(0,0,v.z));
		    		}
		    	if(key.Output()[16]==1) {
		    		en3d.MoveCam(Vector3.downward(0.2f));
		    		up=true;
		    	}
		    	}
	    	//SAVE

	    	if(key.Output()[77]==1) {
	    		File f = new File("src\\voxeltest\\saves\\"+name+".sav");
	    		try {
					if (f.createNewFile()){
						System.out.println("File is created!");
						}else{
						System.out.println("File already exists.");
						}
					int[] d = FileStuff.Array3Dto1D(en3d.Voxels);
					String s = FileStuff.SaveArray(d);
					FileStuff.Write(f, 1, ""+en3d.Voxels.length );
					FileStuff.Write(f, 2, ""+en3d.Voxels[0].length);
					FileStuff.Write(f, 3, ""+en3d.Voxels[0][0].length);
					FileStuff.Write(f, 4, ""+en3d.campos.x);
					FileStuff.Write(f, 5, ""+en3d.campos.y);
					FileStuff.Write(f, 6, ""+en3d.campos.z);
					FileStuff.Write(f, 7,s );
					System.out.println("File "+name+".sav saved succesfully at "+ f.getPath());
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	    	}
	    	//Escape
	    	if(key.Output()[27]==1) {
	    		escape=1;
	    	}
	    	//if(up) {
	    	//frame.repaint();
	    	up=false;
	    	//}
	    	if (time%3==0) {
	    		//System.out.println("X:"+(int)en3d.campos.x+" Y:"+(int)en3d.campos.y+" Z:"+(int)en3d.campos.z);
	    		//en3d.campos.print();
	    		//v.print();
	    		//System.out.println(mousedown);
	    	}
	    	///MOUSE SWOOSH
	    	if (mouseswoosh!=0) {
	    		en3d.selected=((en3d.selected+mouseswoosh)%9+9)%9;
	    		mouseswoosh=0;
	    	}
	    	///BUILD AND DESTROY BLOCKS
	    	if (mousedown&&buildref==0) {
	    		en3d.setBlock(0);
	    		buildref=6;
	    	}
	    	if (rmousedown&&buildref==0) {
	    		en3d.setBlockOut(en3d.hotbar[en3d.selected]);
	    		buildref=4;
	    	}
	    	if(buildref>0)
	    	buildref-=1;
	    }
	    frame.getContentPane().setCursor(normCursor);
	    while(key.Output()[27]==1) {
	    	System.out.print("");
	    }
	    while(escape==1) {
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	System.out.print("");
	    	if(key.Output()[27]==1) {
	    		escape=0;
	    	}
	    }
	    }
	}

}
