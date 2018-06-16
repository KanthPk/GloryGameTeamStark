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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class GroupViewController implements Initializable {

    @FXML
    private AnchorPane ancherGropuView;

    @FXML
    private ListView<?> listGroupMembers;

    @FXML
    private Button btnProceed;

    @FXML
    private Button btnLeave;

    private NavigationService navigationService;

    public GroupViewController() {
        navigationService = new NavigationService("/UI/GroupLoadingForGame.fxml");
    }

    @FXML
    void btnLeaveClicked(ActionEvent event) {
        try {
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            Stage stage = (Stage) btnLeave.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    void btnProceedClicked(ActionEvent event) {
        try {
            navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
