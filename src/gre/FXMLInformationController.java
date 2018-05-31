/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gre;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.BufferedReader;
import javafx.scene.text.Text;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.net.URL;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Spectator Pioneer
 */
public class FXMLInformationController implements Initializable {

    private Stage stage;
    private Parent root;
    @FXML
    private Button dictionary;
    @FXML
    private Button information;
    @FXML
    private Button about;
    @FXML
    private ListView<Text> list;
    @FXML
    private TextArea T10;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button add;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        st1();
    }

    public void st1() {
        System.out.println("st1");
        String[] string = new String[200];
        String name = "";

        Font f77 = Font.font("Serif", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 50);
        File folder = new File("TEXT");
        File[] L = folder.listFiles();

        String se = L[0].getName();
        String ss = "";
        try {
            for (int i = 0; i < L.length; i++) {
                String f1 = L[i].getName(), f, f3;
                f = f1.replaceFirst(".txt", "");
                string[i] = f;

                ss = f;
                Text T = new Text();
                T.setFill(Color.DARKSLATEBLUE);
                T.setX(50);
                // f3=f+".txt";

                // Font f2 = Font.font("Serif", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 12);
                //   T.setFont(f2);
                T.setText(f);
                list.getItems().add(T);
            }
        } catch (NullPointerException e) {
        }

        //   Scene sc = new Scene(H, 1360, 720);
        try {
            list.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    if (list.getSelectionModel().getSelectedIndex() > -1) {

                        String f3 = list.getSelectionModel().getSelectedItem().getText();
                        File file = new File("TEXT/" + f3 + ".txt");
                        //   System.out.println("checking file name"+f3);
                        StringBuilder con = new StringBuilder();

                        try {

                            BufferedReader input = new BufferedReader(new FileReader(file));

                            try {
                                String line = null;
                                while ((line = input.readLine()) != null) {
                                    con.append(line);
                                    con.append(System.getProperty("line.separator"));
                                    T10.setText(con.toString());

                                }

                            } finally {
                                input.close();
                            }
                        } catch (IOException eb) {
                            eb.printStackTrace();
                        }
                    }
                }

            });
        } catch (NullPointerException e) {
        }
    }

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
    private void aboutClick(ActionEvent event) throws IOException {
        Button btn_3;
        btn_3 = (Button) event.getSource();
        stage = (Stage) btn_3.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/gre/FXMLAbout.fxml"));
        Scene scene_2 = new Scene(root);
        stage.setScene(scene_2);
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
        Button btn = new Button("check");
        // stage.setScene(btn);
        stage.show();
    }

    @FXML
    private void addinfo(ActionEvent event) throws IOException {
        System.out.println("addinfo starting");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gre/Add_file.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {

        }
        System.out.println("addinfo ending");
    }

    @FXML
    private void delete_action(ActionEvent event) {
        try {

            String delfilestr = "";
            delfilestr = list.getSelectionModel().getSelectedItem().getText();
            File delfile = new File("TEXT/" + delfilestr + ".txt");
            System.out.println("checking file name" + delfilestr);
            delfile.delete();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sucess");
            alert.showAndWait();
            /* refresh the information*/
            Button btn_5;
            btn_5 = (Button) event.getSource();
            stage = (Stage) btn_5.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/gre/FXMLInformation.fxml"));
            Scene scene_2 = new Scene(root);
            stage.setScene(scene_2);
            stage.show();

        } catch (Exception e) {

        }

    }

}
