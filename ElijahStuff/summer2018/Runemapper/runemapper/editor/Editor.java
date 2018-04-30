package editor;
import java.io.File;

import dunemask.util.FileUtil;
import dunemask.util.xml.Runemap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Editor extends Application {
	
	public static void main(String[] args) {
		String filePath = System.getProperty("user.home"+"/Desktop/Temp.drm");
		File f = new File(filePath);
		Runemap rm = new Runemap();
		
		
		try {
			Editor.launch(args);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	EditController  editController = new EditController();
	@Override
	public void start(Stage stage) throws Exception {
		try {
			this.getParameters().getRaw().get(0);
			if(this.getParameters().getRaw().get(0)!=null) {
				File f = new File(this.getParameters().getRaw().get(0).replace("\\", "/"));
				this.editController.setMap(Runemap.parseRunemap(f));
			}
		}catch(Exception e) {
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("other.fxml"));
		loader.setController(this.editController);
		  
		Parent root = loader.load();
		Scene scene = new Scene(root,800,400,Color.BLACK);
		stage.setScene(scene);
		
		stage.setTitle("DM - Runemap Editor");
		
		
		stage.show();

		
		
		
		
	}

}
