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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AshanPerera
 */
public class CreateGroupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane AnchrcreateGroup;

    @FXML
    private TextField txtGroupName;

    @FXML
    private TextField txtNoOfPlayers;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnLeave;

    private NavigationService navigationService;

    public CreateGroupController() {
        navigationService = new NavigationService("/UI/GroupView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());
            btnCreate.disableProperty().bind(txtNoOfPlayers.textProperty().isEmpty());

            txtNoOfPlayers.setTextFormatter(new TextFormatter<>((TextFormatter.Change t) -> {
                t.setText(t.getText().replaceAll(".*[^0-9].*", "").toUpperCase());
                return t;
            }));

        } catch (Exception e) {
        }
    }

    @FXML
    private void btnCreateClicked(ActionEvent event) {
        try {
            navigationService.OneDropNavigation(event);
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnLeaveClicked(ActionEvent event) {
        try {
            ConstantElement.isPopedUp = false;
            ConstantElement.isDisableBtnPlay = false;
            Stage stage = (Stage) btnLeave.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }
}
