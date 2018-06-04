/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane homeRoot;

    @FXML
    private Button btnPlay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            URL oracle = new URL("https://kanthpk.000webhostapp.com/sample.php");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
                  
            while ((inputLine = in.readLine()) != null) {
                System.out.println("pk............."+inputLine);
                String[] animals = inputLine.split("\\s");
                int animalIndex = 1;
                for (String animal : animals) {
                System.out.println(animalIndex + ". " + animal);
                animalIndex++;
                }
            }
            in.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        try {
            //should be validated
            //before play the game
            Parent parentObject = FXMLLoader.load(getClass().getResource("/UI/Game.fxml"));
            Scene sceneObject = new Scene(parentObject);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(sceneObject);
            //app_stage.setMaximized(true);
            app_stage.centerOnScreen();
            app_stage.show();

        } catch (Exception e) {
        }
    }

    @FXML
    private void closeApplication() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) homeRoot.getScene().getWindow();
        stage.setIconified(true);
    }
}
