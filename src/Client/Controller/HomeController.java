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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AshanPerera
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

    @FXML
    private ImageView imgSettings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnPlay.setDisable(ConstantElement.isDisableBtnPlay);
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
            if (!ConstantElement.isDisableBtnPlay && !ConstantElement.isPopedUp) {
                route("group", "/UI/Group.fxml");
            }
        } catch (IOException e) {
        }
    }

    @FXML
    private void closeApplication() {
        Boolean output;//needed for further implement      
        Platform.exit();
        //serverCallToLogout
        output = ServerCall.Logout(ConstantElement.GlobalUserName, ConstantElement.GlobalPassowrd);
        System.exit(0);
    }

    @FXML
    private void imgMinimizeApplication() {
        Stage stage = (Stage) homeRoot.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void imgSettingsOnPress(MouseEvent event) {
        System.out.println("Hello");
    }

    private void route(String id, String path) throws IOException {
        AnchorPane layout;
        Stage stage;
        switch (id) {
            case "group":
                layout = null;
                stage = null;
                layout = FXMLLoader.load(getClass().getResource(path));
                stage = new Stage();
                stage.centerOnScreen();
                stage.setScene(new Scene(layout));
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                break;
        }
    }
}
