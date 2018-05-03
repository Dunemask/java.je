package editor;
import java.io.File;

import javax.swing.JOptionPane;

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
		String filePath = System.getProperty("user.home")+"/Desktop/Temp.drm";
		File f = new File(filePath);
		Runemap rm = new Runemap(f);
		rm.writeForcedElement("Alpha/Beta/Gamma", "Greek alphabet");
		//rm.printAttr();
		rm.write();
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
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("other.fxml"));
		loader.setController(this.editController);
		try {
			this.getParameters().getRaw().get(0);
			if(this.getParameters().getRaw().get(0)!=null) {
				File f = new File(this.getParameters().getRaw().get(0).replace("\\", "/"));
				this.editController.setMap(Runemap.parseRunemap(f));
				stage.setTitle("DM Runemap Editor - "+f.getPath());
			}
		}catch(Exception e) {
			stage.setTitle("DM Runemap Editor");
		}  
		Parent root = loader.load();
		Scene scene = new Scene(root,800,400,Color.BLACK);
		stage.setScene(scene);
		
		
		stage.show();

		
		
		
		
	}

}
