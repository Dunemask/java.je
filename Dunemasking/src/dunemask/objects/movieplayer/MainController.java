package dunemask.objects.movieplayer;
/**
 * 
 */


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * @author Elijah
 *
 */
public class MainController implements Initializable {
	/***Version*/
    final static double version = 4.5;
    
    public static boolean setup=false;
    
	@FXML
	private MediaView mv;
	
	@FXML
	private Slider seekSlider;
	
	@FXML 
	private Slider volumeSlider;
	
	private MediaPlayer mediaPlayer;
	private Media media;
	
	public static String mediaPath;

	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void resetSpeed() {
		mediaPlayer.setRate(1);
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void changeMedia(ActionEvent evt) {
		if(setup) {
			FileChooser fileChooser = new FileChooser();
			ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<FileChooser.ExtensionFilter>();
			 filters.add(new FileChooser.ExtensionFilter("Select a Media ","*.mp4","*.mp3","*.wav","*.mpeg"));
			fileChooser.getExtensionFilters().addAll(filters);
			File file = fileChooser.showOpenDialog(null);
			String filePath = file.toURI().toString();
			
			if(filePath !=null) {
				Stop(evt);
				mediaPath = filePath;
				System.out.println(mediaPath);
				media = new Media(filePath);
				mediaPlayer = new MediaPlayer(media);
				mv.setMediaPlayer(mediaPlayer);
				mediaPlayer.setRate(1);
				mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {

					@Override
					public void invalidated(Observable arg0) {
						seekSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
				
					}});
				
				
				
			Restart(evt);
			}
			
			
			
		}
		
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void SkipAhead(ActionEvent evt) {
		if(setup) {
			double current = mediaPlayer.getCurrentTime().toMillis();
			double dur = media.getDuration().toMillis();
			double skipping = dur/50;
			mediaPlayer.seek(new Duration(current+skipping));	
		}
		
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void SkipBehind(ActionEvent evt) {
		if(setup) {
			double current = mediaPlayer.getCurrentTime().toMillis();
			double dur = media.getDuration().toMillis();
			double skipping = dur/50;
			mediaPlayer.seek(new Duration(current-skipping));	
			
		}
		
	}
	
	
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Play(ActionEvent evt) {
		if(setup) {
			System.out.println("Playing "+mediaPath.replace("%20", " "));
			fixSlider();
			
			mediaPlayer.play();
		}
		
	}
	
	/**MoviePlayer Action
	 * @param evt Event
	 *  
	 * */
	public void SetOnLoop(ActionEvent evt) {
		if(setup) {
			mediaPlayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					Restart(evt);
					
				}
				
			});
		}
	}
	
	/**
	 * 
	 */
	private void fixSlider() {
		  if(setup) {
			  mediaPlayer.setOnReady(new Runnable() {

			        @Override
			        public void run() {


			            // play if you want
			        	seekSlider.setMin(0.0);
			    		seekSlider.setMax(media.getDuration().toSeconds());
			    		Play(null);
			        }
			    });
		  }
		
		
		
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Slow(ActionEvent evt) {
		if(setup) {
		mediaPlayer.setRate(mediaPlayer.getRate()-.25);
		}
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Fast(ActionEvent evt) {
		if(setup) {
		mediaPlayer.setRate(mediaPlayer.getRate()+.25);
		}
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Start(ActionEvent evt) {
		if(setup) {
		fixSlider();
		mediaPlayer.seek(media.getDuration());
		mediaPlayer.setRate(1);
		}
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Pause(ActionEvent evt) {
		if(setup) {
			
			mediaPlayer.pause();
		}
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Restart(ActionEvent evt) {
		if(setup) {
			mediaPlayer.seek(mediaPlayer.getStartTime());
			fixSlider();
			mediaPlayer.play();
		}
	}
	
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Stop(ActionEvent evt) {
		if(setup) {
		mediaPlayer.seek(mediaPlayer.getStartTime());
		mediaPlayer.stop();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPlayer(mediaPath);


	}
	
	public void initPlayer(File media) {
		initPlayer(media.toURI().toString());
	}
	
	
	/**
	 * 
	 */
	public void initPlayer(String pathToMedia) {
		
		media = new Media(pathToMedia);
		mediaPlayer = new MediaPlayer(media);
		mv.setMediaPlayer(mediaPlayer);
	
		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
		
		volumeSlider.setValue(mediaPlayer.getVolume()*100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				mediaPlayer.setVolume(volumeSlider.getValue()/100);
				
			}
			
			
		});
		
		
		//seekSlider.setValue(0.0);
		
	  seekSlider.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable arg0) {
					if(seekSlider.getValue()!=mediaPlayer.currentTimeProperty().getValue().toSeconds()){
						mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
					}
					
					
					
				}
				  
			  });
		
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				seekSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
		
			}});
		
		setup=true;
		fixSlider();
	}
	

}