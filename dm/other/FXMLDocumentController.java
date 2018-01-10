/**
 * 
 */
package dunemask.other;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 * @author Elijah
 *
 */
public class FXMLDocumentController implements Initializable {
	
	@FXML
	private MediaView mediaView;
	
	
	private MediaPlayer mediaPlayer;
	
	
	
	private String filePath;

	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)","*.mp4");
		fileChooser.getExtensionFilters().add(filter);
		File file = fileChooser.showOpenDialog(null);
		filePath = file.toURI().toString();
		if(filePath !=null) {
			Media media = new Media(filePath);
			mediaPlayer = new MediaPlayer(media);
			mediaView = new MediaView(mediaPlayer);
			//mediaView.setMediaPlayer(mediaPlayer);
		
			mediaPlayer.play();
		}
		
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}

}
