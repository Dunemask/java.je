package editor;
import java.io.File;

import javax.swing.JOptionPane;

import dunemask.util.FileUtil;
import dunemask.util.xml.Runemap;
import editor.EditController.Record;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Editor extends Application {
	
	public static void main(String[] args) {
		try {
			Editor.launch(args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static EditController  editController = new EditController();
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("other.fxml"));
		loader.setController(this.editController);
		Parent root = loader.load();
		try {
			this.getParameters().getRaw().get(0);
			if(this.getParameters().getRaw().get(0)!=null) {
				File f = new File(this.getParameters().getRaw().get(0).replace("\\", "/"));
				Editor.editController.setMap(Runemap.parseRunemap(f));
				stage.setTitle("DM Runemap Editor - "+f.getPath());
			}
		}catch(Exception e) {
			stage.setTitle("DM Runemap Editor");
		}  
		Scene scene = new Scene(root,800,400,Color.BLACK);
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        Editor.editController.updateSize();
		    }

		});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        Editor.editController.updateSize();
		    }
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	var kc = event.getCode();
            	if(kc==KeyCode.S&&event.isControlDown()&&!event.isShiftDown()) {
            		editController.saveFile();
            	}else if(kc==KeyCode.S&&event.isControlDown()&&event.isShiftDown()) {
            		editController.saveFileAs();
            	}else if(kc==KeyCode.ENTER) {
            		Record r = editController.elmTable.getSelectionModel().getSelectedItem();
            		if(r!=null) {
            			if(editController.map.isCont(r.getFieldURL())) {
            				editController.curParent = r.getFieldURL();
            				editController.selParentLabel.setText("Parent:"+editController.curParent);
            				editController.loadChildren(r.getFieldURL());
            			}else {
            				JOptionPane.showMessageDialog(null, "PLEASE SELECT A CONTAINER!");
            			}
            		}
            	}
            	
            }
        });
		this.editController.updateSize();
		stage.setScene(scene);
		stage.getIcons().add(new Image(Editor.class.getResourceAsStream("RM.png")));
		
		
		stage.show();
		if(editController.map!=null) {
		editController.load();
		}

		
		
		
		
	}

}
