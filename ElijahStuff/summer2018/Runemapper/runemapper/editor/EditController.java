package editor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dunemask.util.FileUtil;
import dunemask.util.xml.Runemap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class EditController implements Initializable {

	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem openFileMenuItem;
	@FXML
	private MenuItem saveFileMenuItem;
	@FXML
	private MenuItem saveFileAsMenuItem;
	@FXML
	private Pane backPane;
	@FXML
	private TableView<String> elmTable;
	
	private Runemap map;
	/** Open File
	 * 
	 * */
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		ArrayList<FileChooser.ExtensionFilter> filters = new ArrayList<FileChooser.ExtensionFilter>();
		filters.add(new FileChooser.ExtensionFilter("Select a Runemap","*.drm"));
		fileChooser.getExtensionFilters().addAll(filters);
		File file = fileChooser.showOpenDialog(null);
		if(file!=null) {
			map = Runemap.parseRunemap(file);
			((javafx.stage.Stage) this.backPane.getScene().getWindow()).setTitle("DM Runemap Editor - "+file.getPath());
			//System.out.println(((javafx.stage.Stage) this.backPane.getScene().getWindow()).getTitle());
		}
	}
	/** Saves the oppened file
	 * 
	 * */
	public void saveFile() {
		if(map==null) {
			this.saveFileAs();
		}else {
			//map.writeForcedElement("ANOTHER ELM", "666");
			map.write();
		}
		
	}
	/** Asks user where to save file
	 * 
	 * */
	public void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Runemap");
        try {
        File file = new File(FileUtil.removeExtension(fileChooser.showSaveDialog(null).getAbsolutePath())+".drm");
        	//map.writeForcedElement("This is an element", "DIS IS A VALUE!");
        	map.writeOut(file);
        }catch(Exception e) {
        	//e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "PLEASE CHOOSE A VALID FILE!");
        }
	}
	
	
	
	public EditController() {
		
	}
	private void setBackColor(Node n,String str) {
		n.setStyle("-fx-background-color: #"+str);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}


	/**
	 * @return the map
	 */
	public Runemap getMap() {
		return map;
	}


	/**
	 * @param map the map to set
	 */
	public void setMap(Runemap map) {
		this.map = map;
		load();
	}
	/**
	 * 
	 */
	private void load() {
		ArrayList<TableColumn<String,?>> tables = new ArrayList<TableColumn<String,?>>();
		TableColumn<String,?> temp = new TableColumn();
		temp.setOnEditStart(new EventHandler<Object>() {

			@Override
			public void handle(Event event) {
				
				
			}
			
		});
		tables.add(temp);
		this.elmTable.getColumns().addAll(tables);
	}

}
