/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import java.net.URL;
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
