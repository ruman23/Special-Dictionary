/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gre;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PARICHEHR PRIANKA
 */
public class Add_fileController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private TextArea subject_field;
    @FXML
    private TextArea detiles_field;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.println("from add file controler");
    }    

    @FXML
    private void cancel_detiles(ActionEvent event) {
         Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save_detiles(ActionEvent event) {
          String subject_text = subject_field.getText();
        String detiles_text = detiles_field.getText();
        System.out.println(detiles_text);
        if (subject_text.isEmpty() || detiles_text.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("please fill the all field");
            alert.showAndWait();
            return;
        }
        ObservableList<CharSequence> paragraph = detiles_field.getParagraphs();
        Iterator<CharSequence> iter = paragraph.iterator();
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File("TEXT/" + subject_text + ".txt")));
            while (iter.hasNext()) {
                CharSequence seq = iter.next();
                bf.append(seq);
                bf.newLine();
            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Success");
        alert.show();
        //FXMLInformationController fx= new FXMLInformationController();
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
       /*refresh the information*/
    }
    
}
