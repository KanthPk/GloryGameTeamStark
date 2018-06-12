/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import Server.Controller.MiddleTier;
import glory_schema.ConstantElement;
import java.io.IOException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TeamStark
 */
public class HomeController implements Initializable {
    //Global Variable,begin
    ConstantElement Const;
    MiddleTier ServerCall = new MiddleTier();
    //Global Variable,end
    
    @FXML
    private AnchorPane homeRoot;

    @FXML
    private Button btnPlay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] users = null;
        users = ServerCall.onlineUsers();
        int userIndex = 1;
        for (String user : users) {
            System.out.println(userIndex + ". " + user);
            userIndex++;
        }
    }

    public void getObject(ConstantElement login) {
        Const = login;
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        try {
            //should be validated
            //before play the game                     
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/Game.fxml"));
            Parent parentObject = (Parent) fxmlLoader.load();
            GameController gameObject= fxmlLoader.<GameController>getController();
            gameObject.getObject(Const);
            Scene sceneObject = new Scene(parentObject);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(sceneObject);
            //app_stage.setMaximized(true);
            app_stage.centerOnScreen();
            app_stage.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void closeApplication() {
        Boolean output;//needed for further implement      
        Platform.exit();
        //serverCallToLogout
        output = ServerCall.Logout(Const.get_userId(), Const.get_password());
        System.exit(0);
    }

    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) homeRoot.getScene().getWindow();
        stage.setIconified(true);
    }
}
