/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: dunemask.objects.movieplayer.MoviePlayer.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link dunemask.objects.movieplayer }</p>
 */
package dunemask.objects.movieplayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import dunemask.util.FileUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
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
 *  <p>Belongs to Package {@link dunemask.objects.movieplayer }</p>
 */
public class MoviePlayer implements Initializable  {
	
	public MoviePlayer(Media media){
		setMedia(media);
		
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		      JFXPanel p  = new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		     p.setEnabled(false);   
		    }
		});
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.volumeSlider  = new Slider();
		this.seekSlider = new Slider();
		seekSlider.setMax(this.media.getDuration().toSeconds());
		this.setPlayerReady(new CountDownLatch(1));
		
	}
	

	
	
	
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPlayer();
		
	}
	
	private CountDownLatch playerReady;
	private CountDownLatch mediaFinished;
	
    @FXML
    private Button repeatButton;
    
    @FXML 
    private Button playButton ;
    
    @FXML private Button stopButton;
	@FXML private Button forwardButton;
	@FXML private Button backButton;
	@FXML private Button pauseButton;
	@FXML private Button changeMediaButton;
	
	@FXML
	private MediaView mv;
	
	
	private static Runnable onEndDefault = new Runnable() {
		@Override
		public void run() {
			 
		}
		
		
	};
	
	@FXML
	private Slider seekSlider;
	

	@FXML 
	private Slider volumeSlider;
	
	private MediaPlayer mediaPlayer ;
	private Media media;
	


	/** MoviePlayer Action
	 * 
	 * */
	public void resetSpeed() {
		mediaPlayer.setRate(1);
	}
	
	/** MoviePlayer Action
	 * 
	 * */
	public void changeMediaWithChooser() {
		 
			FileChooser fileChooser = new FileChooser();
			ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<FileChooser.ExtensionFilter>();
			 filters.add(new FileChooser.ExtensionFilter("Select a Media ","*.mp4","*.mp3","*.wav","*.mpeg"));
			fileChooser.getExtensionFilters().addAll(filters);
			File file = fileChooser.showOpenDialog(null);
			String filePath = file.toURI().toString();
			
			if(filePath !=null) {
					changeMedia(new Media(filePath));
			}
			
			
			
		
		
	}
	
	
	public void changeMedia(Media newMedia) {
		setMedia(newMedia);
		
		Runnable endOfMedia = mediaPlayer.getOnEndOfMedia();
		Stop();
	
		//System.out.println("Playing "+newMedia.getSource().replace("%20", " "));
		this.media = newMedia;
		mediaPlayer = new MediaPlayer(media);
		mv.setMediaPlayer(mediaPlayer);
		mediaPlayer.setRate(1);
		mediaPlayer.setOnEndOfMedia(endOfMedia);
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
		
			@Override
			public void invalidated(Observable arg0) {
				seekSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
		
			}});
		
		
		
		Restart();
	}
	
	
	
	/**MoviePlayer Action
	 * 
	 * */
	public void SkipToEnd() {
		mediaPlayer.seek(mediaPlayer.getTotalDuration());
		
	}
	
	
	/** MoviePlayer Action
	 * 
	 * */
	public void SkipAhead() {
		 
			double current = mediaPlayer.getCurrentTime().toMillis();
			double dur = media.getDuration().toMillis();
			double skipping = dur/50;
			mediaPlayer.seek(new Duration(current+skipping));	
		
		
	}

	
	
	/** MoviePlayer Action
	 * 
	 * */
	public void SkipBehind() {
		 
			double current = mediaPlayer.getCurrentTime().toMillis();
			double dur = media.getDuration().toMillis();
			double skipping = dur/50;
			mediaPlayer.seek(new Duration(current-skipping));	
			
		
		
	}
	
	
	
	/** MoviePlayer Action
	 * 
	 * */
	public void Play() {
		 
	
			//System.out.println("Playing "+media.getSource().replace("%20", " "));
			fixSlider();
			
			mediaPlayer.play();
			
		
		
	}
	
	/**MoviePlayer Action
	 * 
	 *  
	 * */
	public void SetOnLoop() {
		 
			if(mediaPlayer.getOnEndOfMedia()==onEndDefault) {
				mediaPlayer.setOnEndOfMedia(new Runnable() {

					@Override
					public void run() {
						
						Restart();
						//MoviePlayer.l = new CountDownLatch(1);
						 
						 
					}
					
				});
				repeatButton.setStyle("-fx-background-color: #232323;"+" -fx-text-fill: #ffffff;");
				
			}else {
				repeatButton.setStyle("");
				mediaPlayer.setOnEndOfMedia(onEndDefault);
			}
		
	}
	
	/**
	 * 
	 */
	private void fixSlider() {
		   
			  mediaPlayer.setOnReady(new Runnable() {

			        @Override
			        public void run() {

			            // play if you want
			        	seekSlider.setMin(0.0);
			    		seekSlider.setMax(media.getDuration().toSeconds());
			    		
			    		//MoviePlayer.playerReady
			    		Play();
			    		getPlayerReady().countDown();
			        }
			    });
		  
		
		
		
	}
	
	/** MoviePlayer Action
	 * 
	 * */
	public void Slow() {
		 
		mediaPlayer.setRate(mediaPlayer.getRate()-.125);
		
	}
	
	/** MoviePlayer Action
	 * 
	 * */
	public void Fast() {
		 
		mediaPlayer.setRate(mediaPlayer.getRate()+.125);
		
	}
	

	
	/** MoviePlayer Action
	 * 
	 * */
	public void Pause() {
		 
			
			mediaPlayer.pause();
		
	}
	
	/** MoviePlayer Action
	 * 
	 * */
	public void Restart() {
		 
			mediaPlayer.seek(mediaPlayer.getStartTime());
			fixSlider();
			mediaPlayer.play();
		//	MoviePlayer.l = new CountDownLatch(1);
		
	}
	
	
	/** MoviePlayer Action
	 * 
	 * */
	public void Stop() {
		 
		mediaPlayer.seek(mediaPlayer.getStartTime());
		mediaPlayer.stop();
		
		
	}
	
	
	/**
	 * 
	 */
	private void initPlayer() {
		if(media==null) {
		media = new Media(FileUtil.getResource("dunemask/resources/media/Cool Beans.mp3").toURI().toString());
		}
		mediaPlayer = new MediaPlayer(getMedia());
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
		
		fixSlider();


		
		
	}

	public Button getRepeatButton() {
		return repeatButton;
	}

	public void setRepeatButton(Button repeatButton) {
		this.repeatButton = repeatButton;
	}

	public Button getPlayButton() {
		return playButton;
	}

	public void setPlayButton(Button playButton) {
		this.playButton = playButton;
	}

	public Button getStopButton() {
		return stopButton;
	}

	public void setStopButton(Button stopButton) {
		this.stopButton = stopButton;
	}

	public Button getForwardButton() {
		return forwardButton;
	}

	public void setForwardButton(Button forwardButton) {
		this.forwardButton = forwardButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(Button pauseButton) {
		this.pauseButton = pauseButton;
	}

	public Button getChangeMediaButton() {
		return changeMediaButton;
	}

	public void setChangeMediaButton(Button changeMediaButton) {
		this.changeMediaButton = changeMediaButton;
	}

	public MediaView getMv() {
		return mv;
	}

	public void setMv(MediaView mv) {
		this.mv = mv;
	}

	public Slider getSeekSlider() {
		return seekSlider;
	}

	public void setSeekSlider(Slider seekSlider) {
		this.seekSlider = seekSlider;
	}

	public Slider getVolumeSlider() {
		return volumeSlider;
	}

	public void setVolumeSlider(Slider volumeSlider) {
		this.volumeSlider = volumeSlider;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}







	/**
	 * @return the mediaFinished
	 */
	public CountDownLatch getMediaFinished() {
		return mediaFinished;
	}







	/**
	 * @param mediaFinished the mediaFinished to set
	 */
	public void setMediaFinished(CountDownLatch mediaFinished) {
		this.mediaFinished = mediaFinished;
	}







	/**
	 * @return the playerReady
	 */
	public CountDownLatch getPlayerReady() {
		return playerReady;
	}







	/**
	 * @param playerReady the playerReady to set
	 */
	public void setPlayerReady(CountDownLatch playerReady) {
		this.playerReady = playerReady;
	}

}
