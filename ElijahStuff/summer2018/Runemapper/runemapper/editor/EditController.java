package editor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dunemask.util.FileUtil;
import dunemask.util.xml.Runemap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;

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
	private MenuItem addMenuItem;
	@FXML
	private Pane backPane;
	@FXML
	public TableView<Record> elmTable;
	@FXML Label selParentLabel;
	
	Runemap map;
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
			//Sets map and loads it
			this.setMap(Runemap.parseRunemap(file));
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
		elmTable.setPrefWidth(600);
		this.selParentLabel.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	selParentLabel.setPrefWidth(selParentLabel.getText().length() * 7); // why 7? Totally trial number.
		    }
		});

	}
	public ObservableList<Record> open = FXCollections.observableArrayList();
	
	public void updateSize() {
		elmTable.setPrefWidth(elmTable.getScene().getWidth());
		elmTable.setPrefHeight(this.elmTable.getScene().getHeight());
		var cols = elmTable.getColumns();
		for(int i=0;i<cols.size();i++) {
			elmTable.getColumns().get(i).setPrefWidth(elmTable.getPrefWidth()/cols.size());
		}
		
	}
	
	public void updateTable() {
		elmTable.setEditable(true);
		Callback<TableColumn, TableCell> cellFactory = 
                new Callback<TableColumn, TableCell>() {
                          
                          @Override
                          public TableCell call(TableColumn p) {
                              return new EditingCell();
                          }
                      };
        
        TableColumn colChild = new TableColumn("Child");
        colChild.setCellValueFactory(new PropertyValueFactory<Record,String>("fieldChild"));
        colChild.setPrefWidth(elmTable.getPrefWidth()/2);
        
        TableColumn columnValue = new TableColumn("Value");
        columnValue.setCellValueFactory(
                new PropertyValueFactory<Record,String>("fieldValue"));
        columnValue.setPrefWidth(elmTable.getPrefWidth()/2);
        
     
        //--- Add for Editable Cell of Value field, in String
        columnValue.setCellFactory(cellFactory);
        columnValue.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, String>>() {
                    
                    @Override public void handle(TableColumn.CellEditEvent<Record, String> t) {
                        ((Record)t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setFieldValue(t.getNewValue());
                    }
                });
        

      
        //---
        elmTable.setItems(open);
        elmTable.getColumns().addAll(colChild, columnValue);
	}
	
	public void newFile() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Runemap");
        try {
        File file = new File(FileUtil.removeExtension(fileChooser.showSaveDialog(null).getAbsolutePath())+".drm");
        map = new Runemap(file);
        this.load();
        ((javafx.stage.Stage) this.backPane.getScene().getWindow()).setTitle("DM Runemap Editor - "+file.getPath());
        }catch(Exception e) {
        	//e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "PLEASE CHOOSE A VALID FILE!");
        }
		
	}
	
	public void navigateDown() {
		Record r = elmTable.getSelectionModel().getSelectedItem();
		if(r!=null) {
			if(this.map.isCont(r.getFieldURL())) {
				this.curParent = r.getFieldURL();
				this.selParentLabel.setText("Parent:"+curParent);
				this.loadChildren(r.getFieldURL());
			}else {
				JOptionPane.showMessageDialog(null, "PLEASE SELECT A CONTAINER!");
			}
		}else {
			JOptionPane.showMessageDialog(null, "PLEASE SELECT A CONTAINER!");
		}
	}
	public void navigateUp() {
		String r = this.curParent;
		if(r!=null) {
		this.curParent = map.getParentUrl(r);
		this.selParentLabel.setText("Parent:"+curParent);
		this.loadChildren(curParent);
		}else {
			JOptionPane.showMessageDialog(null, "AT TOP OR NO MAP SELECTED!");
		}
		
		
	}
	
	
	/**
	 * @return the map
	 */
	public Runemap getMap() {
		return map;
	}
	
	
	public boolean alreadyContains(String s) {
		boolean cont = false;
		for(int i=0;i<open.size();i++) {
			if(open.get(i).getFieldChild().equals(s)) {
				cont = true;
				i=open.size();
			}
			
		}
		return cont;
		
	}
	public void deleteMenuPushed() {
		Record r = elmTable.getSelectionModel().getSelectedItem();
		if(r==null) {
			JOptionPane.showMessageDialog(null, "NO ITEM SELECTED!");
			return;
		}
		
		open.remove(r);
		if(r.getFieldURL().endsWith("/")) {
			map.removeContainer(r.getFieldURL());
		}else {
			map.removeElement(r.getFieldURL());
		}
	}
	
	public void addMenuPushed() {
		if(map==null) {
			JOptionPane.showMessageDialog(null, "NO MAP SELECTED!");
			return;
		}
		
		String name  = JOptionPane.showInputDialog("Name For new Attribute");
		String isElmChar = JOptionPane.showInputDialog("Is this an Element (Y/n)");
		/**Handles top laevel elm*/
		var ph = curParent;
		if(ph==null) {
			ph = "";
		}
		boolean isElm = true;
		if(name==null||isElmChar==null) {
			return;
		}
		if(isElmChar.toLowerCase().contains("n")) {
			isElm = false;
		}
		if(isElm&&!this.alreadyContains(name)) {
			open.add(new Record(ph+name,name,"TEMP"));
			map.writeElement(ph+name, "TEMP");	
		}else if(isElm){
			JOptionPane.showMessageDialog(null, "ELEMENT ALREADY EXISTS!");
		}else if(!isElm&&!this.alreadyContains(name)) {
			String url = "";
			if(!name.endsWith("/")) {
				url=ph+name+"/";
			}else {
				JOptionPane.showMessageDialog(null, "INVALID NAME!");
				return;
			}
		
			open.add(new Record(url,name,null));
			map.writeContainer(ph+name);
		}else {
			JOptionPane.showMessageDialog(null, "CONTAINER ALREADY EXISTS!");
		}
		
		
		
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Runemap map) {
		this.map = map;
		load();
	}
	String curParent;
	
	void loadChildren(String parent) {
		if(parent!=null) {
			var top = map.getChildrenURLS(parent);
			open= FXCollections.observableArrayList();
			if(map.isCont(parent)&&top!=null) {
				for(int i=0;i<top.size();i++) {
					if(map.isCont(top.get(i))) {
						open.add(new Record(top.get(i),map.getName(top.get(i)),null));
					}else {
						open.add(new Record(top.get(i),map.getName(top.get(i)),map.getvalue(top.get(i))));
					}
				}
				this.updateTable();
			}else if(map.isCont(parent)){
				this.updateTable();
			}
		}else {
			var top = map.getTop();
			open= FXCollections.observableArrayList();
			for(int i=0;i<top.size();i++) {
				if(map.isCont(top.get(i))) {
					open.add(new Record(top.get(i),map.getName(top.get(i)),null));
				}else {
					open.add(new Record(top.get(i),map.getName(top.get(i)),map.getvalue(top.get(i))));
				}
			}
			this.updateTable();
		}
	}
	
	/**
	 * 
	 */
	void load() {
		ArrayList<String> top = map.getTop();
		if(top.size()==1&&map.isCont(top.get(0))) {
			curParent=top.get(0);
			loadChildren(curParent);
		}else {
			open= FXCollections.observableArrayList();
			for(int i=0;i<top.size();i++) {
				if(map.isCont(top.get(i))) {
					open.add(new Record(top.get(i),map.getName(top.get(i)),null));
				}else {
					open.add(new Record(top.get(i),map.getName(top.get(i)),map.getvalue(top.get(i))));
				}
			}
			this.updateTable();
			
		}
		this.selParentLabel.setText("Parentt:"+curParent);
		
	}
	
	 class EditingCell extends TableCell<String, String> {
	        private TextField textField;
	        public EditingCell() {}
	        
	        @Override
	        public void startEdit() {
	            super.startEdit();
	            
	            if (textField == null) {
	                createTextField();
	            }
	            
	            setGraphic(textField);
	            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	            textField.selectAll();
	        }
	        
	        @Override
	        public void cancelEdit() {
	            super.cancelEdit();
	            
	            setText(String.valueOf(getItem()));
	            setContentDisplay(ContentDisplay.TEXT_ONLY);
	        }
	        
	        @Override
	        public void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty) {
	                setText(null);
	                setGraphic(null);
	            } else {
	                if (isEditing()) {
	                    if (textField != null) {
	                        textField.setText(getString());
	                    }
	                    
	                    setGraphic(textField);
	                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	                } else {
	                    setText(getString());
	                    setContentDisplay(ContentDisplay.TEXT_ONLY);
	                    //System.out.println("Would have Editted");
	                }
	            }
	        }
	        
	        private void createTextField() {
	            textField = new TextField(getString());
	            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
	            textField.setOnKeyPressed(new EventHandler() {

					@Override
					public void handle(Event evt) {
						KeyEvent t = (KeyEvent) evt;
			        	Record r = Editor.editController.elmTable.getSelectionModel().getSelectedItem();
						 if (t.getCode() == KeyCode.ENTER&&!Editor.editController.getMap().isCont(r.getFieldURL())) {
							 commitEdit(textField.getText());
							 Editor.editController.map.removeElement(r.getFieldURL());
							 Editor.editController.map.writeElement(r.getFieldURL(), textField.getText());
		                 } else if (t.getCode() == KeyCode.ESCAPE) {
		                     cancelEdit();
		                 }else if(t.getCode() == KeyCode.ENTER&&Editor.editController.getMap().isCont(r.getFieldURL())) {
		                	 JOptionPane.showMessageDialog(null, "CANNOT ASSIGN VALUE TO CONTAINER!");
		                	 cancelEdit();
		                 }
						
					}
	            });
	        }

			private String getString() {
	            return getItem() == null ? "" : getItem().toString();
	        }
	    }

	 public class Record{
	        private SimpleStringProperty fieldChild;
	        private SimpleStringProperty fieldValue;
	        private SimpleStringProperty fieldURL;

	        
	        Record(String fieldURL,String fieldChild, String fieldValue){
	            this.fieldChild= new SimpleStringProperty(fieldChild);
	            this.fieldValue= new SimpleStringProperty(fieldValue);
	            this.fieldURL = new SimpleStringProperty(fieldURL);

	        }

	        public String getFieldURL() {
	        	return fieldURL.get();
	        }
	        public void setFieldURL(String fieldURL) {
	        	this.fieldURL = new SimpleStringProperty(fieldURL); 
	        }

			public String getFieldChild() {
				return fieldChild.get();
			}


			public void setFieldChild(String fieldChild) {
				this.fieldChild = new SimpleStringProperty(fieldChild);
			}


			public String getFieldValue() {
				return fieldValue.get();
			}


			public void setFieldValue(String fieldValue) {
				this.fieldValue = new SimpleStringProperty(fieldValue);
			}
	        
	       
	    }
}
