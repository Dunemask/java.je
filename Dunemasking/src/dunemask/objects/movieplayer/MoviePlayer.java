package dunemask.objects.movieplayer;
/**
 * 
 */


import java.io.File;
import java.util.concurrent.CountDownLatch;

import dunemask.dunemasking.Capture;
import dunemask.util.FileUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Run the start Player method
 * @author Elijah
 *
 */
public class MoviePlayer extends Application{
	/***Version*/
    final static double version = 4.3;
	public static boolean updatePlayer = true;
	public static boolean forcePlay = false;
	public static boolean forceStop = false;
	public static boolean forcePause = false;
	public static boolean forceToggleRepeat = false;
	public static boolean forceRestart = false;
	public static boolean forceSeek = false;
	
	public static CountDownLatch playerReady = new CountDownLatch(1);
	static CountDownLatch l = new CountDownLatch(1);	
	public static CountDownLatch mediaFinished = new CountDownLatch(1);
	
	public static Media currentMedia;
	/** Cause the player to play
	 * */
	public static void play() {
		forcePlay = true;
	}
	/**Stop mediaplayer
	 * */
	public static void stopMediaPlayer() {
		forceStop = true;
	}
	/** Pause MediaPlayer
	 * */
	public static void pause() {
		forcePause = true;
	}
	/**Set media Player on repeat
	 * */
	public static void setOnRepeat() {
		forceToggleRepeat = true;
	}
	/**Restart the mediaplayer
	 * */
	public static void restart() {
		forceRestart = true;
	}
	/**Seek a duration in milliseconds
	 * */
	public static void seek(int duration) {
		MainController.externalSeekSlider = duration;
		forceSeek = true;
	}
	/**Force Close The Player
	 * */
	public static void forceClose() {
		Platform.exit();
	}

	
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {

		
		
		Parent root = FXMLLoader.load(FileUtil.getResource("dunemask/objects/movieplayer/Main.fxml").toURI().toURL());
		Scene scene = new Scene(root,800,400,Color.BLACK);
		try {
		File icon = FileUtil.getWebFile("https://github.com/Dunemask/dunemask.github.io/raw/master/resources/media/images/DM-Dice.jpg");
		stage.getIcons().add(new Image(icon.toURI().toString()));
		} catch (Exception e) {
			
		}
		
		stage.setTitle("DM - Movie Player");
	
		stage.setScene(scene);
		stage.show();
		
		
		
	}
	

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
		MainController.mediaPath = path;
		
		if(newThread) {
			Thread mp= new Thread( () -> {
				
				Capture.startConsole();
				String[] args = null;
				launch(args);
				updatePlayer = false;
				Capture.closeConsole();
				MainController.setup=false;
				l.countDown();
				mediaFinished.countDown();
				
			});
			mp.start();
		}else {
			Capture.startConsole();
			String[] args = null;
			launch(args);
			updatePlayer = false;
			Capture.closeConsole();
			MainController.setup=false;
			l.countDown();
			mediaFinished.countDown();
		}
		

		
	}


	/**
	 * @param webFile
	 */
	public static void changeMedia(File file) {
		String path;
		try {
		path = file.toURI().toString();
		}catch(Exception e) {
			path = FileUtil.getWebFile("https://dunemask.github.io/resources/media/mp4/Two%20Guys%20On%20A%20Scooter.mp4").toURI().toString();
		}
		
		MainController.mediaPath = path;
	}

	/** Overide Controlls in the mainController
	 * */
	
	
	

}
