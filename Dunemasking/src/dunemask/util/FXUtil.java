/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: dunemask.util.FXUtil.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package dunemask.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;

/**
 * @author karib
 *
 */
public class FXUtil {
	static boolean setup = false;
	static File[] file = null;
	
	/** Javafx file chooser
	 * @param dir Init dir
	 * */
	public static File[] multiFileChooser(File dir) {
		
	
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				
				FileChooser fileChooser = new FileChooser();
				ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<FileChooser.ExtensionFilter>();
				 filters.add(new FileChooser.ExtensionFilter("Select a Media ","*.mp4","*.mp3","*.wav","*.mpeg"));
				fileChooser.getExtensionFilters().addAll(filters);
				fileChooser.setInitialDirectory(dir);
				List<File> files = fileChooser.showOpenMultipleDialog(null);
				if(files!=null) {
					file = files.toArray(new File[files.size()]);
				}
				
			}
			
		};
		Platform.runLater(run);
		return file;
	}
	
	public static void FXSetup() {
		if(!setup) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	
	
}
