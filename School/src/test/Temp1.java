/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: test.Temp1.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package test;

import dunemask.objects.movieplayer.MoviePlayer;

/**
 * @author karib
 *
 */
public class Temp1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//GitHubStation.Start();
		
	MoviePlayer.startPlayer(null, true);
	System.out.println("Moo");
	try {
		MoviePlayer.playerReady.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Foce Closed");
	MoviePlayer.forceClose();
	
	
		/*	JFrame frame = new JFrame("File Chooser");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.removeAll();
		frame.setLocationRelativeTo(null);*/


	/*	frame.repaint();
		frame.revalidate();*/
		
		

	}

}
