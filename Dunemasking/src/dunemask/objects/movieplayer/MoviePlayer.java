package dunemask.objects.movieplayer;
/**
 * 
 */


import java.io.File;
import dunemask.dunemasking.Capture;
import dunemask.util.FileUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	 * @param args Args
	 * @param file Starting File for moviePlayer
	 */
	public static void startPlayer(File file) {
		String path;
		try {
		path = file.toURI().toString();
		}catch(Exception e) {
			path = FileUtil.getWebFile("https://dunemask.github.io/resources/media/mp4/Two%20Guys%20On%20A%20Scooter.mp4").toURI().toString();
		}
		MainController.mediaPath = path;
		

		
		Capture.startConsole();
		String[] args = null;
		launch(args);
		updatePlayer = false;
		Capture.closeConsole();
		MainController.setup=false;
		
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


	

}
