package voxeltest;

import javax.swing.JFrame;

import Graphicstest.KeyList;
import Graphicstest.Vector3;

public class VoxelMain {
	public static VoxEn en3d = new VoxEn(new Vector3(0,0,10));
	public static void main(String[] args) {
		JFrame frame = new JFrame("VoxPan");
		VoxPanel p = new VoxPanel();
		p.SetE3d(en3d);
		frame.getContentPane().add(p);
	    frame.setSize(400, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    KeyList key = new KeyList();
	    frame.addKeyListener(key);
	    frame.setFocusable(true);
	    frame.requestFocus();
	    frame.repaint();
	    int time = 0;
	    while(true) {
	    	time++;
	    	
	    	try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	en3d.RotateCam(0.000, 0.000);
	    	if(key.Output()[39]==1) {
	    		en3d.RotateCam(0.05, 0);
	    	}
	    	if(key.Output()[38]==1) {
	    		en3d.RotateCam(0, 0.05);
	    	}
	    	if(key.Output()[37]==1) {
	    		en3d.RotateCam(-0.05, 0);
	    	}
	    	if(key.Output()[40]==1) {
	    		en3d.RotateCam(0, -0.05);
	    	}
	    	if(key.Output()[87]==1) {
	    		en3d.MoveCam(Vector3.foreward().rotate(en3d.rx, "z"));
	    	}
	    	if(key.Output()[83]==1) {
	    		en3d.MoveCam(Vector3.backward().rotate(en3d.rx, "z"));
	    	}
	    	if(key.Output()[68]==1) {
	    		en3d.MoveCam(Vector3.rightward().rotate(en3d.rx, "z"));
	    	}
	    	if(key.Output()[65]==1) {
	    		en3d.MoveCam(Vector3.leftward().rotate(en3d.rx, "z"));
	    	}
	    	if(key.Output()[32]==1) {
	    		en3d.MoveCam(Vector3.upward());
	    	}
	    	if(key.Output()[16]==1) {
	    		en3d.MoveCam(Vector3.downward());
	    	}
	    	frame.repaint();
	    	if (time%3==0) {
	    		System.out.println("X:"+(int)en3d.campos.x+" Y:"+(int)en3d.campos.y+" Z:"+(int)en3d.campos.z);
	    	}
	    }
	}

}
