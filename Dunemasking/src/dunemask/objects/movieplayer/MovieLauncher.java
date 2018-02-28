/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: dunemask.objects.movieplayer.MovieLauncher.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link dunemask.objects.movieplayer }</p>
 */
package dunemask.objects.movieplayer;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.CountDownLatch;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import dunemask.dunemasking.Capture;
import dunemask.util.FileUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link limit }</p>
 */
public class MovieLauncher extends Application {
	
	/***Version*/
    final static double version = 7.0;
    
	public static MoviePlayer current;
	public static JFrame frame;

	static String tmpPath;

	
	/** Start Player
	 * @param newThread Start in new thread or not External commands cannot be performed without a new thread being created
	 * @param file Starting File for moviePlayer
	 */
	public static void startPlayer(File file,boolean newThread) {
		String path;
		try {
		path = file.toURI().toString();
		
		}catch(Exception e) {
			path = FileUtil.getWebFile("https://dunemask.github.io/resources/media/mp4/Two%20Guys%20On%20A%20Scooter.mp4").toURI().toString();

		}
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
		
		
		current= new MoviePlayer(new Media(path));
		
		//c = new MoviePlayer();
	 
		
		if(newThread) {
			Thread mp= new Thread( () -> {
				
			
				String[] args = null;
				launch(args);	
			
			
				
			});
			mp.start();

		}else {
			
			String[] args = null;
			launch(args);		
			

		}
		

		
	}
	
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Capture.setUpStreams();
		
		FXMLLoader loader = new FXMLLoader(FileUtil.getResource("dunemask/objects/movieplayer/DMPlayer.fxml").toURI().toURL());
		loader.setController(current);
		  
		Parent root = loader.load();
	
		Scene scene = new Scene(root,800,400,Color.BLACK);
		
		

		File icon = null;
		try {
		icon = FileUtil.getWebFile("https://github.com/Dunemask/dunemask.github.io/raw/master/resources/media/images/DM-Dice.jpg");
		
		} catch (Exception e) {
			
		}
		
		stage.setTitle("DM - Movie Player");
		
		//stage.setScene(scene);
		
		Capture.cleanUpStreams();
		//Key Event Listener if needed later
		  scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	String key = event.getCode().toString();
	                switch (key) {
	                }
	            }
	        });
		
		 frame = new JFrame("DM - Movie Player");
		 
			try {
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage(icon.toURI().toURL()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	        final JFXPanel fxPanel = new JFXPanel();
	        fxPanel.setScene(scene);
	        frame.add(fxPanel);
	        frame.setSize((int)scene.getWidth(),(int)scene.getHeight());
	        frame.setVisible(true);
	        frame.setLocationRelativeTo(null);
	        frame.setAlwaysOnTop(true);
	        Thread.sleep(50);
	        frame.setAlwaysOnTop(false);
	        frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					
					Platform.exit();			
					frame.dispose();
					
				}
			});
	        
	     
		
		
	
		
	}

	
}
