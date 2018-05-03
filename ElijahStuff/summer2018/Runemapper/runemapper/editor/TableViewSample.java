package editor;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class TableViewSample extends Application {
 
    private TableView<Attr> table = new TableView<Attr>();
    private final ObservableList<Attr> data =
            FXCollections.observableArrayList(
            new Attr("Jacob", "Smith", "jacob.smith@example.com"),
            new Attr("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Attr("Ethan", "Williams", "ethan.williams@example.com"),
            new Attr("Emma", "Jones", "emma.jones@example.com"),
            new Attr("Michael", "Brown", "michael.brown@example.com"));
    final HBox hb = new HBox();
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(table.getWidth()/2);
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Attr, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Attr, String>>() {
                @Override
                public void handle(CellEditEvent<Attr, String> t) {
                    ((Attr) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                }
            }
        );
 
 
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(table.getWidth()/2);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Attr, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Attr, String>>() {
                @Override
                public void handle(CellEditEvent<Attr, String> t) {
                    ((Attr) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setVal(t.getNewValue());
                }
            }
        );
 
      
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
     
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Attr(
                        addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()));
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
            }
        });
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Attr {
 
        private final SimpleStringProperty Name;
        private final SimpleStringProperty val;
 
        private Attr(String fName, String lName) {
            this.Name = new SimpleStringProperty(fName);
            this.val = new SimpleStringProperty(lName);
        }
        
 
        public String getName() {
            return Name.get();
        }
        public void setName(String str) {
        	this.Name = new SimpleStringProperty(str);
        }
        public void setVal(String str) {
        	this.val = new SimpleStringProperty(str);
        }
 
        public String getVal(String val) {
            return this.val.get();
        }
 
    }
}