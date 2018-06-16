/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

import glory_schema.ConstantElement;
import glory_services.NavigationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GroupController implements Initializable {

    @FXML
    private Button btnCreateGroup;

    @FXML
    private Button btnJoinGroup;

    /**
     * Initializes the controller class.
     */
    NavigationService navigationService;

    public GroupController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConstantElement.isPopedUp = true;
    }

    @FXML
    void btnCreateGroupClicked(ActionEvent event) {
        try {
            navigationService = new NavigationService("/UI/CreateGroup.fxml");
            navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @FXML
    void btnJoinGroupClicked(ActionEvent event) {
        try {
            navigationService = new NavigationService("/UI/JoinOnline.fxml");
            navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }
}
