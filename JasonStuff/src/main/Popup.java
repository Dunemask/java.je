/**
 * 
 */
package main;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;

public class Popup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is some cool dudes");
		//BasicFrame f = new BasicFrame(0,0);
		Popup t = new Popup();
		BasicFrame fram = t.Createnewframe(500,500);
		while(true) {
			try {
				Thread.sleep(100);
				fram.Increm();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public BasicFrame Createnewframe(int x,int y) {
		BasicFrame g =  new BasicFrame(x,y,this);
		return g;
	}

}
