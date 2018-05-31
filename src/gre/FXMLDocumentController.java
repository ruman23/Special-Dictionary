package gre;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import gre.database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Spectator Pioneer
 */
public class FXMLDocumentController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();
    private Stage stage;
    private Parent root;
    @FXML
    private Button dictionary;
    @FXML
    private Button information;
    @FXML
    private Button about;
    @FXML
    private TextField word;
    @FXML
    private TextField banglameaning;
    @FXML
    private TextField englishmeaning;
    @FXML
    private Button add;
    DatabaseHandler databaseHandler;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> wordCol;
    @FXML
    private TableColumn<Book, String> banglaCol;
    @FXML
    private TableColumn<Book, String> englishCol;
   // @FXML
    //private TableColumn<Book, Boolean> delCol;
    @FXML
    private TextField sreach_box;
    @FXML
    private Button delete_word;

    @FXML

    private void dictionaryClick(ActionEvent event) throws IOException {
        Button btn;
        btn = (Button) event.getSource();
        stage = (Stage) btn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void informationClick(ActionEvent event) throws IOException {
        Button btn_2;
        btn_2 = (Button) event.getSource();
        stage = (Stage) btn_2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLInformation.fxml"));
        Scene scene_2 = new Scene(root);
        stage.setScene(scene_2);
        stage.show();
    }

    @FXML
    private void aboutClick(ActionEvent event) throws IOException {
        Button btn_2;
        btn_2 = (Button) event.getSource();
        stage = (Stage) btn_2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLAbout.fxml"));
        Scene scene_2 = new Scene(root);
        stage.setScene(scene_2);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = new DatabaseHandler();
        initCol();
        loadData("spectator_pioneer");
        // tableView.getItems().clear();
        System.out.println("initialize");

    }

    private void initCol() {
        wordCol.setCellValueFactory(new PropertyValueFactory<>("word"));
        banglaCol.setCellValueFactory(new PropertyValueFactory<>("banglameaning"));
        englishCol.setCellValueFactory(new PropertyValueFactory<>("englishmeaning"));
       // delCol.setCellValueFactory(new PropertyValueFactory<>("isAvail"));
    }

    private void loadData(String ld) {
        list.removeAll(list);
        System.out.println("ld=====" + ld);
        DatabaseHandler handler = DatabaseHandler.getInstance();
        System.out.println("testing 2");
        String qu;
        if (ld == "spectator_pioneer") {
            qu = "SELECT * FROM BOOK";
        } else {
            qu = "SELECT * FROM BOOK WHERE word='" + ld + "'";
        }
        ResultSet rs = handler.execQuery(qu);
        System.out.println("testing 3");
        try {
            while (rs.next()) {
                String word = rs.getString("word");

                String banglameaning = rs.getString("banglameaning");
                String englishmeaning = rs.getString("englishmeaning");
                Boolean isAvail = rs.getBoolean("isAvail");
                list.add(new Book(word, englishmeaning, banglameaning, isAvail));

            }
        } catch (SQLException ex) {
            // Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.getItems().setAll(list);

    }

    @FXML
    private void text_area_input(KeyEvent event) {
        System.out.println("text_area_input");
        try {
            // FXMLDocumentController.this.initCol();
            tableView.getItems().clear();
            String srch = sreach_box.getText();
            System.out.println("checking srch===" + srch);
            FXMLDocumentController.this.initCol();
            if (srch.isEmpty()) {
                FXMLDocumentController.this.loadData("spectator_pioneer");
            } else {
                FXMLDocumentController.this.loadData(srch);
            }

        } catch (NullPointerException e) {
        }
        System.out.println("text_area_input exit");
    }

    @FXML
    private void delete_word_action(ActionEvent event) throws IOException {
        /* get selectd cell data from the table*/
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        System.out.println("Selected Value" + val);
        /*delete the data from the data base*/
        String qu = "DELETE FROM BOOK WHERE"
                + " word='" + val + "' "
                + "OR banglameaning='" + val + "'"
                + "OR englishmeaning='" + val + "'";
        System.out.println(qu);
        if (databaseHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else //Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
        /*refresh the dictionary page*/
        
          Button btn_7;
        btn_7 = (Button) event.getSource();
        stage = (Stage) btn_7.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static class Book {

        private final SimpleStringProperty word;
        private final SimpleStringProperty englishmeaning;
        private final SimpleStringProperty banglameaning;
        private final SimpleBooleanProperty availabilty;

        public Book(String word, String englishmeaning, String banglameaning, Boolean isAvail) {
            this.word = new SimpleStringProperty(word);
            this.englishmeaning = new SimpleStringProperty(englishmeaning);
            this.banglameaning = new SimpleStringProperty(banglameaning);

            this.availabilty = new SimpleBooleanProperty(isAvail);
        }

        public String getWord() {
            return word.get();
        }

        public String getEnglishmeaning() {
            return englishmeaning.get();
        }

        public String getBanglameaning() {
            return banglameaning.get();
        }

        public Boolean getAvailabilty() {
            return availabilty.get();
        }

    }

    @FXML
    private void addClick(ActionEvent event) throws IOException {
        String greWord = word.getText();
        String grebanglameaning = banglameaning.getText();
        String greenglishmeaning = englishmeaning.getText();

        if (greWord.isEmpty() || grebanglameaning.isEmpty() || greenglishmeaning.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("please fill the all field");
            alert.showAndWait();
            return;
        }
        System.out.println(greWord + grebanglameaning + greenglishmeaning);
        String qu = "INSERT INTO BOOK VALUES ("
                + "'" + greWord + "',"
                + "'" + grebanglameaning + "',"
                + "'" + greenglishmeaning + "',"
                + "" + "true" + ""
                + ")";
        System.out.println(qu);
        if (databaseHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else //Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
        Button btn;
        btn = (Button) event.getSource();
        stage = (Stage) btn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
