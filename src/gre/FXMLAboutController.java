/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PARICHEHR PRIANKA
 */
public class FXMLAboutController implements Initializable {

    private  Stage stage;
    private Parent root;
    @FXML
    private Button dictionary;
     @FXML
    private Button information;
      @FXML
    private Button about;
     @FXML

    private void dictionaryClick(ActionEvent event) throws IOException {
       Button btn;
       btn=(Button) event.getSource();
       stage=(Stage) btn.getScene().getWindow();
       root=FXMLLoader.load(getClass().getResource("/gre/FXMLDocument.fxml"));
       Scene scene =new Scene(root);
       stage.setScene(scene);
       stage.show();
    }
     @FXML
     private void informationClick(ActionEvent event) throws IOException {
       Button btn_2;
       btn_2=(Button) event.getSource();
       stage=(Stage) btn_2.getScene().getWindow();
       root=FXMLLoader.load(getClass().getResource("/gre/FXMLInformation.fxml"));
       Scene scene_2 =new Scene(root);
       stage.setScene(scene_2);
       stage.show();
    }
     @FXML
     private void aboutClick(ActionEvent event) throws IOException {
       Button btn_3;
       btn_3=(Button) event.getSource();
       stage=(Stage) btn_3.getScene().getWindow();
       root=FXMLLoader.load(getClass().getResource("/gre/FXMLAbout.fxml"));
       Scene scene_3 =new Scene(root);
       stage.setScene(scene_3);
       stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
   
}
