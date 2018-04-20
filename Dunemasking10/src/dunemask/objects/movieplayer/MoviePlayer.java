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

import dunemask.util.FileUtil;
import dunemask.util.StringUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link dunemask.objects.movieplayer }</p>
 */
public class MoviePlayer implements Initializable  {
	/***Version*/
    final static double version = 4.0;
	
	public MoviePlayer(Media media){
		
		setMedia(media);
		
		/*
		this.volumeSlider  = new Slider();
		this.seekSlider = new Slider();
		seekSlider.setMax(this.media.getDuration().toSeconds());*/
		this.setPlayerReady(new CountDownLatch(1));
		
	}
	

	
	
	
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPlayer();
		
		
	}
	
	@FXML private VBox audBar;
	
	private CountDownLatch playerReady;
	private CountDownLatch mediaFinished;
	
    @FXML
    private Button repeatButton;
    
    @FXML 
    private Button playButton ;
    
    @FXML
    private SplitPane splitPane;
    @FXML
    private BorderPane borderPane;
    @FXML private StackPane stackPane;
    @FXML private Button stopButton;
	@FXML private Button forwardButton;
	@FXML private Button backButton;
	
	@FXML private Button changeMediaButton;
	
	@FXML
	private MediaView mv;
	
	
	private Runnable onEndDefault = new Runnable() {
		@Override
		public void run() {
			 
		}
		
		
	};
	
	/**
	 * @return the onEndDefault
	 */
	public Runnable getOnEndDefault() {
		
		return onEndDefault;
	}




	/**
	 * @param onEndDefault the onEndDefault to set
	 */
	public void setOnEndDefault(Runnable onEndDefault) {
		this.onEndDefault = onEndDefault;
	}

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
			if(file!=null) {
				String filePath = file.toURI().toString();
				if(filePath !=null) {
					changeMedia(new Media(filePath));
				}
			}

			
			
		
		
	}
	
	
	public void changeMedia(Media newMedia) {
		setMedia(newMedia);
		
		Runnable endOfMedia = mediaPlayer.getOnEndOfMedia();
		AudioSpectrumListener audSpct = mediaPlayer.getAudioSpectrumListener();
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
		
	/*	bands = mediaPlayer.getAudioSpectrumNumBands()-80;
		rects =  new javafx.scene.shape.Rectangle[bands];
		for(int i=0;i<rects.length;i++) {
			rects[i] = new javafx.scene.shape.Rectangle();
			//Random r = new Random();
			rects[i].setFill(Color.WHITESMOKE);
			rects[i].setStroke(Color.BLACK);
			rects[i].setStrokeType(StrokeType.INSIDE);
		
		}	*/
		
		mediaPlayer.setAudioSpectrumListener(audSpct);
		
		
		
		Restart();
	}
	
	
	
	/**MoviePlayer Action
	 * 
	 * */
	public void SkipToEnd() {
		mediaPlayer.seek(media.getDuration());
		seekSlider.setValue(seekSlider.getMax());
		
		
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
	
	/**For External Force Closing*/
	public void Pause() {
		mediaPlayer.pause();
	}
	/**Seek
	 * */
	public void seek(Duration dur) {
		mediaPlayer.seek(dur);
	}
	
	
	
	public Runnable pause  = new Runnable() {

		@Override
		public void run() {
			
			mediaPlayer.pause();
			getPlayButton().setText(">");
			getPlayButton().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent evt) {
					play.run();
					
				}
				
			}
			);
			
		}
		
	};
	
	public Runnable play = new Runnable() {

		@Override
		public void run() {
			fixSlider();
			mediaPlayer.play();
			getPlayButton().setText("| |");
			getPlayButton().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent evt) {
					pause.run();
					
				}
				
			}
			);
		}
		
	};
	
	public void playPause() {
		getPlayButton().getOnAction().handle(null);
		
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
			    		DoubleProperty height = mv.fitHeightProperty();
			    		audBar.setPrefHeight(height.get());
			    		//audBar.setMinWidth(seekSlider.getWidth());
			    		double bandHeight =  20;//audBar.getHeight()/(rects.length);
			    		for(javafx.scene.shape.Rectangle r: rects) {
			    			r.setHeight(bandHeight);
			    			//r.setHeight(2);
			    		}
			    		
			    		//MoviePlayer.playerReady
			    		play.run();
			    		getPlayerReady().countDown();
			    		Image img = (Image) media.getMetadata().get("image");
			    		if(img!=null&&StringUtil.containsIgnoreCase(media.getSource(), ".mp3")) {
							BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

						    borderPane.setBackground(new Background(new BackgroundImage(img,
						            BackgroundRepeat.NO_REPEAT,
						            BackgroundRepeat.NO_REPEAT,
						            BackgroundPosition.CENTER,
						            bSize)));
							
							
						//	borderPane.setStyle("-fx-background-image: url('"+imgPath+"');");
							//ap.setStyle(ap.getStyle()+ "-fx-background-repeat: no-repeat;");
						//	borderPane.setStyle(ap.getStyle()+ "-fx-background-size: cover;");
						//	borderPane.setStyle(ap.getStyle()+"-fx-background-position:center;");
						//	mv.setStyle(borderPane.getStyle());
							//borderPane.setCenter(ap);
			    		
			    		}else if(StringUtil.containsIgnoreCase(media.getSource(),"mp4")) {
			    		
			    		
			    			
			    		}
			    		
			        }
			    });
		  
		
		
		
	}
	
	/*private void swingImageUpdate() {
		Container p = MovieLauncher.frame.getContentPane();
		JFXPanel pan =  (JFXPanel) p.getComponent(0);
		if(pan.getComponentCount()>0) {
			pan.remove(0);
		}
		
		Image img = (Image) media.getMetadata().get("image");
		if(img!=null&&StringUtil.containsIgnoreCase(media.getSource(), ".mp3")) {
		BufferedImage bmg = SwingFXUtils.fromFXImage(img, null);
		
		

		
		
		

		
		int x=0;
		int y=0;
		int w=(int)mv.getScene().getWidth();
		int h=(int) (mv.getScene().getHeight()-(stackPane.getHeight()+40));
		
		JLabel ico = new JLabel(new StretchIcon(bmg));
		
		
	
		
		ico.setBounds(x,y,w,h);
		System.out.println(x+","+y+","+w);
		
		pan.add(ico);
		
		p.remove(p.getComponent(0));
		p.add(pan);
		
		MovieLauncher.frame.setContentPane(p);
		MovieLauncher.frame.repaint();
		MovieLauncher.frame.revalidate();

		}
	}*/
	
	
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
	public void Restart() {
		 
			mediaPlayer.seek(new Duration(0.0));
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
	
	private int bands;
	private javafx.scene.shape. Rectangle[] rects ;
	
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
		
		
		bands = mediaPlayer.getAudioSpectrumNumBands()-80;
		rects =  new javafx.scene.shape.Rectangle[bands];
		for(int i=0;i<rects.length;i++) {
			rects[i] = new javafx.scene.shape.Rectangle();
			//Random r = new Random();
			rects[i].setFill(Color.WHITESMOKE);
			rects[i].setStroke(Color.BLACK);
			rects[i].setStrokeType(StrokeType.INSIDE);
			audBar.getChildren().add(rects[i]);
		}
		
		
		mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {

			@Override
			public void spectrumDataUpdate(double arg0, double arg1, float[] sags, float[] floats1) {
				for(int i=0;i<rects.length;i++) {
					double w = sags[i]+70;
					boolean wks = w>10;
					
					//Node node =  borderPane.getBottom();
					double y = MovieLauncher.frame.getHeight()-splitPane.getHeight();
					
			;		//if Active
					
					if(wks&&rects[i].getLayoutY()<y) {
				
					rects[i].setWidth(w*mediaPlayer.getVolume());
					
					//If not active
					}else if(rects[i].getLayoutY()<y){
						rects[i].setWidth(5*mediaPlayer.getVolume());
						
					}else{
						rects[i].setWidth(0);
					}
				}
				
			}
			
		});
		
		
		
		
		
		//seekSlider.setValue(0.0);
		
	  seekSlider.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable arg0) {
				boolean notOnEnd = seekSlider.getValue()<seekSlider.getMax();
					if(seekSlider.getValue()!=mediaPlayer.currentTimeProperty().getValue().toSeconds()&&notOnEnd){
						mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
					}
					
					
					
				}
				  
			  });
		
		mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				seekSlider.setValue(mediaPlayer.currentTimeProperty().getValue().toSeconds());
		
			}});
		
		getPlayButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent evt) {
				play.run();
				
			}
			
		}
		);
		
		
	

	/*	javafx.scene.Node divider = splitPane.lookup(".split-pane:vertical>*.split-pane-divider");
		    if(divider!=null){
		        divider.setStyle("-fx-background-color: transparent;");
		     
		    }else {
		    	System.out.println("Boom");
		    	splitPane.setStyle(splitPane.getStyle()+"} *.split-pane-divider{-fx-background-color: #000000;}");
		    }
		    
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
			Capture.closeConsole();*/
			
		fixSlider();
		mediaPlayer.setOnEndOfMedia(onEndDefault);
		
		

		
		
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







	/**
	 * @return the splitPane
	 */
	public SplitPane getSplitPane() {
		return splitPane;
	}







	/**
	 * @param splitPane the splitPane to set
	 */
	public void setSplitPane(SplitPane splitPane) {
		this.splitPane = splitPane;
	}







	/**
	 * @return the borderPane
	 */
	public BorderPane getBorderPane() {
		return borderPane;
	}







	/**
	 * @param borderPane the borderPane to set
	 */
	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}


	/**
	 * @return the mediaView
	 */
	public MediaView getMediaView() {
		return mv;
	}






	




	/**
	 * @return the stackPane
	 */
	public StackPane getStackPane() {
		return stackPane;
	}







	/**
	 * @param stackPane the stackPane to set
	 */
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

}
