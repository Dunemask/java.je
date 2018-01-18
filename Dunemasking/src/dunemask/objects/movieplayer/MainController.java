package dunemask.objects.movieplayer;
/**
 * 
 */


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button repeatButton;
	@FXML
	private MediaView mv;
	
	
	private Runnable onEndDefault = new Runnable() {
		@Override
		public void run() {
			 MoviePlayer.l.countDown();
		}
		
		
	};
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
				Runnable endOfMedia = mediaPlayer.getOnEndOfMedia();
				Stop(evt);
				mediaPath = filePath;
				System.out.println("Playing "+mediaPath.replace("%20", " "));
				media = new Media(filePath);
				mediaPlayer = new MediaPlayer(media);
				mv.setMediaPlayer(mediaPlayer);
				mediaPlayer.setRate(1);
				mediaPlayer.setOnEndOfMedia(endOfMedia);
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
	
	public static int externalSeekSlider=0;

	/** MoviePlayer Action*/
	public void ExternalSeek() {
		mediaPlayer.seek(new Duration(externalSeekSlider));
		mediaPlayer.play();
		
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
			MoviePlayer.playerReady.countDown();
			System.out.println("Playing "+mediaPath.replace("%20", " "));
			fixSlider();
			
			mediaPlayer.play();
			MoviePlayer.l = new CountDownLatch(1);
		}
		
	}
	
	/**MoviePlayer Action
	 * @param evt Event
	 *  
	 * */
	public void SetOnLoop(ActionEvent evt) {
		if(setup) {
			if(mediaPlayer.getOnEndOfMedia()==onEndDefault) {
				mediaPlayer.setOnEndOfMedia(new Runnable() {

					@Override
					public void run() {
						MoviePlayer.l.countDown();
						Restart(evt);
						MoviePlayer.l = new CountDownLatch(1);
						 
						 
					}
					
				});
				repeatButton.setStyle("-fx-background-color: #232323;"+" -fx-text-fill: #ffffff;");
				
			}else {
				repeatButton.setStyle("");
				mediaPlayer.setOnEndOfMedia(onEndDefault);
			}
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
			    		//MoviePlayer.playerReady.countDown();
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
		mediaPlayer.setRate(mediaPlayer.getRate()-.125);
		}
	}
	
	/** MoviePlayer Action
	 * @param evt Event
	 * */
	public void Fast(ActionEvent evt) {
		if(setup) {
		mediaPlayer.setRate(mediaPlayer.getRate()+.125);
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
			MoviePlayer.l = new CountDownLatch(1);
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
	
	 static volatile String prev;
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPlayer(mediaPath);
		mediaPlayer.setOnEndOfMedia(onEndDefault);
		
		
		 prev = mediaPath;
		Runnable run = new Runnable() {

			@Override
			public void run() {
		
				while(MoviePlayer.updatePlayer) {
					
					 if(prev!=mediaPath) {
						System.out.println("Media Externally changed!");
						prev = mediaPath;
						changeMedia(mediaPath);
					}
					 if(MoviePlayer.forcePlay) {
						 Play(null);
						 MoviePlayer.forcePlay = false;
					 }
					 if(MoviePlayer.forcePause) {
						 Pause(null);
						 MoviePlayer.forcePause = false;
					 }
					 if(MoviePlayer.forceStop) {
						 Stop(null);
						 MoviePlayer.forceStop = false;
					 }
					 if(MoviePlayer.forceToggleRepeat) {
						 SetOnLoop(null);
						 MoviePlayer.forceToggleRepeat = false;
					 }
					 if(MoviePlayer.forceRestart) {
						 Restart(null);
						 MoviePlayer.forceRestart = false;
					 }
					 if(MoviePlayer.forceSeek) {
						 ExternalSeek();
						 MoviePlayer.forceSeek = false;
					 }
					 if(MoviePlayer.currentMedia!=media) {
						 MoviePlayer.currentMedia = media;
					 }
						if(MoviePlayer.l.getCount()==0) {
							MoviePlayer.l = new CountDownLatch(1);
							MoviePlayer.mediaFinished.countDown();
							MoviePlayer.mediaFinished = new CountDownLatch(1);
						}
					 
				 }
				
			}
			
		};
		
		
		Thread update = new Thread(run);
		update.start();
	
	


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
	
	public void changeMedia(String mpath) {
		
		if(setup) {

			String filePath = mpath;
			
			if(filePath !=null) {
				Runnable endOfMedia = mediaPlayer.getOnEndOfMedia();
				mediaPlayer.seek(	mediaPlayer.getStartTime());
				mediaPlayer.stop();
				mediaPath = filePath;
				System.out.println("Playing "+mediaPath.replace("%20", " "));
				media = new Media(filePath);
				mediaPlayer = new MediaPlayer(media);
				mv.setMediaPlayer(mediaPlayer);
					mediaPlayer.setRate(1);
				mediaPlayer.setOnEndOfMedia(endOfMedia);
				mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {

					@Override
					public void invalidated(Observable arg0) {
						seekSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
				
					}});
			}
			
			mediaPlayer.seek(mediaPlayer.getStartTime());
			mediaPlayer.setOnReady(new Runnable() {

			        @Override
			        public void run() {


			            // play if you want
			        	seekSlider.setMin(0.0);
			        	seekSlider.setMax(media.getDuration().toSeconds());
			        	
			        }
			    });
			
			mediaPlayer.play();
			
		}
			
		
	}
}
